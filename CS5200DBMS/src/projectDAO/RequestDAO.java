package projectDAO;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import projectEntity.*;

public class RequestDAO {

	Map<String, Integer> cache = new HashMap<String, Integer>();
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
	
	public void createRequest(int userId, Request newRequest){
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
		
		//int totalCapacity = 0;
		//for(Vehicle v : vehicles){
		//	totalCapacity = totalCapacity + v.getCapacity();
		//}
		
		/*Query query = em.createQuery("select count(r.ID) from Request r where r.shift = :rhift AND r.Date = :rDate AND r.Status =  :rStatus");
		query.setParameter("rshift", shift);
		query.setParameter("rDate", newRequest.getShiftDate());
		query.setParameter("rStatus", "Confirm");
		int occupiedCount = (int)query.getSingleResult();
		
		if(occupiedCount < totalCapacity){
			newRequest.setStatus("Confirm");
		}
		*/
		em.persist(newRequest);
				
	    em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
		
		RequestDAO reqDAO = new  RequestDAO();
		Request req = new Request();
		req.setShift("1830");
		req.setShiftDate(Date.valueOf("2014-04-25"));
		
		reqDAO.createRequest(4, req);
	}

}
