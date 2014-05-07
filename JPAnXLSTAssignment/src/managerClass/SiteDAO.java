package managerClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import entityClass.*;

public class SiteDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPAnXLSTAssignment");
	
	public Site findSite(int siteId){
		Site s = new Site();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		s = em.find(Site.class, siteId);
		
		em.getTransaction().commit();
		em.close();
			
		return s;
	}
	
	public List<Site> findAllSites(){
		List<Site> sites = new ArrayList<Site>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("findAllSites");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sites;
	}
	
	public void exportSiteDatabaseToXmlFile(SiteDatabase database, String xmlFileName){
		File xmlFile = new File(xmlFileName);
		try{
			JAXBContext jaxb = JAXBContext.newInstance(SiteDatabase.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(database, xmlFile);
		}catch(JAXBException e){
			e.printStackTrace();
		}
		
	}
	
	public void convertXmlFileToOutputFile(String inputXmlFileName, String outputXmlFileName, String xsltFileName){
		File inputFile = new File(inputXmlFileName);
		File outputFile = new File(outputXmlFileName);
		File xsltFile = new File(xsltFileName);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		
		StreamSource inputXML = new StreamSource(inputFile);
		StreamSource xsltXML = new StreamSource(xsltFile);
		StreamResult outputXML = new StreamResult(outputFile);
		
		try {
			Transformer tx = factory.newTransformer(xsltXML);
			tx.transform(inputXML, outputXML);
			
			
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static void main(String[] args){
		SiteDAO dao = new SiteDAO();
			
		Site site = dao.findSite(1);
		//System.out.println(site.getName());
		
		/*
		List<Site> sites = dao.findAllSites();
		for(Site s : sites){
			System.out.println(s.getName());
		}
		
		
		
		SiteDatabase sb = new SiteDatabase();
		sb.setSites(sites);
				
		dao.exportSiteDatabaseToXmlFile(sb, "xml/sites.xml");
		
		*/
		
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
	}
}
