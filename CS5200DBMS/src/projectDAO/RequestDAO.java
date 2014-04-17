package projectDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import projectEntity.*;

@Path("/request")
public class RequestDAO {

	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
		
	@GET
	@Path("/allreq")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse getAllReq(){
		List<Request> req = getAllRequest(4);
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("requests", req);
		JsonResponse jsonRes = new JsonResponse("SUCCESS", "", data);
		return jsonRes;		
	}

	public List<Request> getAllRequest(int id){
		List<Request> requests = new ArrayList<Request>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select request from Request request WHERE request.user = :userid");
		
		User user = new User();
		user.setId(id);
		
		query.setParameter("userid", user);
		requests = query.getResultList();
		
		em.getTransaction().commit();
		em.close();		
		
		return requests;
	}
	
	 @POST
	@Path("/createreq")
	public JsonResponse createReq(@FormParam("shiftDate") String shiftDate, @FormParam("shiftTime") String shiftTime,  @FormParam("dropPoint") String dropPoint) {
		
			Request req = new Request();
			req.setShiftDate(java.sql.Date.valueOf(shiftDate));
			req.setShift(shiftTime);
			 
			String reqStatus = createRequest(4, req);
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("requestStatus", reqStatus);
			JsonResponse jsonRes = new JsonResponse("SUCCESS", "", data);
			return jsonRes;
	 }
	 
	public String createRequest(int userId, Request newRequest){
		VehicleShiftMappingDAO vsmDAO = new VehicleShiftMappingDAO();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		User user = em.find(User.class, userId);
		
		newRequest.setUser(user);
				
		String shift = newRequest.getShift();
		
		List<Vehicle> vehicles = vsmDAO.getVehiclesForShift(shift);
		
		// loop through vehicles and assign as and when vacancy is found
		
		for (Vehicle vehicle : vehicles) {
			newRequest.setStatus("Pending");
			
			Query query = em.createQuery("select count(r.id) from Request r where r.shift = :rshift AND r.shiftDate = :rDate AND r.status =  :rStatus AND r.vehicle = :rVehicle");
			query.setParameter("rshift", shift);
			query.setParameter("rDate", newRequest.getShiftDate());
			query.setParameter("rStatus", "Confirm");
			query.setParameter("rVehicle", vehicle);
			Long bookedSeat = (Long)query.getSingleResult();
				
			if(bookedSeat < vehicle.getCapacity()){
				newRequest.setStatus("Confirm");
				newRequest.setVehicle(vehicle);
				break;
			}
		
		}
		
		em.persist(newRequest);
				
	    em.getTransaction().commit();
		em.close();
		
		return newRequest.getStatus();
	}
	
	/*
	public static void main(String[] args) {
		
		RequestDAO reqDAO = new  RequestDAO();
		Request req = new Request();
		req.setShift("1830");
		req.setShiftDate(Date.valueOf("2014-04-25"));
		
		reqDAO.createRequest(4, req);
	}*/

}
