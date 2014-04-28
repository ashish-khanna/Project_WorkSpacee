package com.neushuttle.projectDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/request")
public class RequestDAO {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("CS5200DBMS");

	
	
	@GET
	@Path("/allreq")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse getAllReq(@Context HttpServletRequest request) {
		HttpSession session =  request.getSession(false);
		User user = (User) session.getAttribute("USER");
		if(user != null)
		{
			List<Request> req = getAllRequest(user.getId());	
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("requests", req);
			JsonResponse jsonRes = new JsonResponse("SUCCESS", "", data);
			return jsonRes;
		}
		else
		{
			JsonResponse jsonRes = new JsonResponse("ERROR", "Please login.", null);
			return jsonRes;
		}
		
	}

	public List<Request> getAllRequest(int id) {
		List<Request> requests = new ArrayList<Request>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em
				.createQuery("select request from Request request WHERE request.user = :userid");

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
	public JsonResponse createReq(@FormParam("shiftDate") String shiftDate,
			@FormParam("shiftTime") String shiftTime,
			@FormParam("dropPoint") int dropPoint, @Context HttpServletRequest request) {
		
		HttpSession session =  request.getSession(false);
		User user = (User) session.getAttribute("USER");

		if(user != null)
		{
			DropPoint dp = new DropPoint();
			dp.setId(dropPoint);

			Request req = new Request();
			req.setShiftDate(java.sql.Date.valueOf(shiftDate));
			req.setShift(shiftTime);
			req.setDropPoint(dp);

			String reqStatus = createRequest(user.getId(), req);

			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("requestStatus", reqStatus);
			JsonResponse jsonRes = new JsonResponse("SUCCESS", "", data);
			return jsonRes;
		}
		else
		{
			JsonResponse jsonRes = new JsonResponse("ERROR", "Please login.", null);
			return jsonRes;
		}
		
	}

	public String createRequest(int userId, Request newRequest) {
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

			Query query = em
					.createQuery("select count(r.id) from Request r where r.shift = :rshift AND r.shiftDate = :rDate AND r.status =  :rStatus AND r.vehicle = :rVehicle");
			query.setParameter("rshift", shift);
			query.setParameter("rDate", newRequest.getShiftDate());
			query.setParameter("rStatus", "Confirm");
			query.setParameter("rVehicle", vehicle);
			Long bookedSeat = (Long) query.getSingleResult();

			if (bookedSeat < vehicle.getCapacity()) {
				newRequest.setStatus("Confirm");
				newRequest.setVehicle(vehicle);
				
				String mailId =  newRequest.getUser().getEmail();
				String subject = "Request Status - " + newRequest.getStatus().toUpperCase();
				String msg = "Dear "+ user.getfName().toUpperCase() +","
						+"\n\n Your request for transport service is Confirmed. Please find details below -" 
						+ "\n\n Cab# - " + newRequest.getVehicle().getRegNo()
						+ "\n Travel Date - " + newRequest.getShiftDate()
						+ "\n Travel Time - " + newRequest.getShift()
						+ "\n Pick Up point - Snell Library"
						+ "\n\n\n\n\n Thanking You for using NU Cool Shuttle service.";
				
				MailerComponent mailer = new MailerComponent();
				mailer.sendMail(mailId, subject, msg);
				break;
			}

		}

		em.persist(newRequest);

		em.getTransaction().commit();
		em.close();

		return newRequest.getStatus();
	}

	@POST
	@Path("/cancelreq")
	public JsonResponse cancelRequest(@FormParam("reqIds") String reqIds,  @Context HttpServletRequest request) {


		HttpSession session =  request.getSession(false);
		User user = (User) session.getAttribute("USER");
		
		if(user != null)
		{		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		
		String [] idArray = reqIds.split(",");
		
		for (String id : idArray) {
			Request req = em.find(Request.class, Integer.parseInt(id));

			String shift = req.getShift();
			Date shiftdate = req.getShiftDate();

			List<Request> reqList = new ArrayList<Request>();
			if (req.getStatus() == "Pending") {
				em.remove(req);
			} else {

				Query query = em
						.createQuery("select r from Request r where r.shiftDate = :shiftD and r.shift = :shiftT and r.status =:stat order by r.id ");
				query.setParameter("shiftD", shiftdate);
				query.setParameter("shiftT", shift);
				query.setParameter("stat", "Pending");
				query.setMaxResults(1);
				reqList = query.getResultList();

				if (!reqList.isEmpty()) {
					Request reqToModify = reqList.get(0);
					reqToModify.setStatus("Confirm");
					reqToModify.setVehicle(req.getVehicle());

					em.merge(reqToModify);
					
					String mailId = reqToModify.getUser().getEmail();
					String subject = "Request Status - " + reqToModify.getStatus().toUpperCase();
					String msg = "Dear "+ reqToModify.getUser().getfName().toUpperCase() +","
							+"\n\n Your pending request for transport service is now Confirmed. Please find details below -" 
							+ "\n\n Cab# - " + reqToModify.getVehicle().getRegNo()
							+ "\n Travel Date - " + reqToModify.getShiftDate()
							+ "\n Travel Time - " + reqToModify.getShift()
							+ "\n Pick Up point - Snell Library"
							+ "\n\n\n\n\n Thanking You for using NU Cool Shuttle service.";
					
					MailerComponent mailer = new MailerComponent();
					mailer.sendMail(mailId, subject, msg);
					
				}

				em.remove(req);
			}

		}

		em.getTransaction().commit();
		em.close();
		
		

		List<Request> req = getAllRequest(user.getId());
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("requests", req);
		JsonResponse jsonRes = new JsonResponse("SUCCESS", "", data);
		return jsonRes;
		}
		else{
			JsonResponse jsonRes = new JsonResponse("ERROR", "Please login", null);
			return jsonRes;
			
		}
	}

	public static void main(String[] args) {

		RequestDAO reqDAO = new RequestDAO();
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(21);
//		idList.add(13);
//		idList.add(14);
//		idList.add(15);
//		idList.add(16);
//		idList.add(17);
//		idList.add(22);
		//reqDAO.cancelRequest(idList);
//		Request req = new Request();
//		req.setShift("1830");
//		req.setShiftDate(Date.valueOf("2014-04-25"));

//		reqDAO.createRequest(4, req);
	}

}
