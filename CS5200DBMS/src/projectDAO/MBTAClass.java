package projectDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import projectDAO.JsonResponse;
import projectDAO.Mbtaroute;
import projectDAO.Stops;


@Path("/mbta")
public class MBTAClass {

	@POST
	@Path("/searchmbta")
	public JsonResponse searchNextTrain(@FormParam("sourceStation") String sourceStation, @FormParam("destStation") String destStation){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
		Mbtaroute mbtaSource = new Mbtaroute();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query sourceQuery = em.createQuery("select a FROM Mbtaroute a inner join Mbtaroute b on a.routeId = b.routeId"
				+ " WHERE a.parentStationName= :source AND b.parentStationName = :dest");
		sourceQuery.setParameter("source", sourceStation);
		sourceQuery.setParameter("dest", destStation);
		
		mbtaSource = (Mbtaroute) sourceQuery.getSingleResult();
		
		Query destQuery = em.createQuery("select m FROM Mbtaroute m"
				+ " WHERE m.parentStationName= :dest AND m.routeId = :routeid");
		destQuery.setParameter("routeid", mbtaSource.getRouteId());
		destQuery.setParameter("dest", destStation);
		
		Mbtaroute mbtaDest = (Mbtaroute) destQuery.getSingleResult();
		
		int sourceStop = Integer.parseInt(mbtaSource.getStopOrder());
		int destStop = Integer.parseInt(mbtaDest.getStopOrder());
		
		Integer direction;
		
		if(sourceStop > destStop)
			direction = 1;
		else
			direction = 0;
		
		String dir = direction.toString();
		
		List<String> sourceTimes = searchRoute(mbtaSource.getRouteId(), dir, sourceStation);
		
		em.getTransaction().commit();
		em.close();
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("sourceTimes", sourceTimes);
		JsonResponse jsonRes = new JsonResponse("SUCCESS", "", data);
		return jsonRes;
	
	}
	
	public List<String> searchRoute(String route, String direction, String soureStation){
		String urlStr = "http://realtime.mbta.com/developer/api/v1/schedulebyroute?";
		String api_key = "dC9qH0qOL0KntwwZI2ssFg";
		List<String> epocTimeList = new ArrayList<String>();
		
		
		urlStr += "api_key="+api_key;
		urlStr += "&route="+route;
		urlStr += "&direction="+direction;
		String output ="";
		
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
			InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			BufferedReader buffer = new BufferedReader(reader);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(connection.getInputStream());
			
			NodeList nodes = doc.getElementsByTagName("stop");
			
			for (int i=0; i < nodes.getLength(); i++) {
				Node node =	nodes.item(i);
					
					String stopName = node.getAttributes().getNamedItem("stop_name").getNodeValue();
					
					if(stopName.equals(soureStation))
						epocTimeList.add(node.getAttributes().getNamedItem("sch_arr_dt").getNodeValue());
			}

			for(String op : epocTimeList) {
				Date dt = new Date(Long.valueOf(op) * 1000);
				System.out.println("Time -->"+dt.getHours()+": "+dt.getMinutes()+": "+dt.getSeconds());
				//System.out.println("Epoch time-->"+op);
			}
				
		} catch (MalformedURLException e) {
			System.out.println("Malform exception - " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO exception - " + e.getMessage());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return epocTimeList;
		
	}
	
	
	public void loadMasterData(String routeid){
		String urlStr = "http://realtime.mbta.com/developer/api/v1/stopsbyroute?";
		String api_key = "dC9qH0qOL0KntwwZI2ssFg";
		
		urlStr += "api_key="+api_key;
		urlStr += "&route="+routeid;
		try {
			URL url = new URL(urlStr);
			
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
			InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			BufferedReader buffer = new BufferedReader(reader);

			StringBuilder temp = new StringBuilder();
			String output = null;
			while ((output = buffer.readLine()) != null)
			{
				temp.append(output);
			}
			
			File f = new File("input.xml");
			
			FileWriter fw = new FileWriter(f);
			fw.write(temp.toString());
			fw.flush();
			
			connection.getInputStream().close();
			
					
			File inputFile = new File("xml/input.xml");
			File xsltFile = new File("xml/stopList.xslt");
			File outputFile = new File("xml/outxml.xml");
			
			TransformerFactory factory = TransformerFactory.newInstance();
			
			StreamSource inputXml = new StreamSource(inputFile);
			StreamSource xsltXml  = new StreamSource(xsltFile);
			StreamResult outputXml = new StreamResult(outputFile);
			
			Transformer tx = factory.newTransformer(xsltXml);
			tx.transform(inputXml, outputXml);
			
			outputFile = new File("xml/outxml.xml");
			
			try {
				JAXBContext jaxb = JAXBContext.newInstance(Stops.class);
				Unmarshaller unmarshaller = jaxb.createUnmarshaller();
				Stops mbta = (Stops) unmarshaller.unmarshal(outputFile);
		
				List<Mbtaroute> stops = mbta.getStops();
				
				EntityManagerFactory factoryManager = Persistence.createEntityManagerFactory("CS5200DBMS");
				EntityManager em = factoryManager.createEntityManager();
				em.getTransaction().begin();
				
				
				for (Mbtaroute mbtaroute : stops) {
					em.persist(mbtaroute);
				}
				
				em.getTransaction().commit();
				em.close();
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}
	
	
	public static void main(String[] args) {

		Mbtaroute m = new Mbtaroute();
		MBTAClass mbtaObj = new MBTAClass();
		
		// mbtaObj.loadMasterData("880_");
		
		//mbtaObj.searchRoute();
		
		//List<String> s =  mbtaObj.searchNextTrain("Boylston Station - Outbound", "Northeastern - Outbound");
		
		//System.out.println(m.getDirectionId()+ " - " + m.getParentStationName() + " - " + m.getStopOrder());
	}

}