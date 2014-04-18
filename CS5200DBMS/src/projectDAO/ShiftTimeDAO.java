package projectDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import projectEntity.*;

@Path("/shifttime")
public class ShiftTimeDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
	
	public void createShift(ShiftTime newShift){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(newShift);
		
	    em.getTransaction().commit();
		em.close();
	}
		
	public void deleteShift(String shift){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		ShiftTime newShift = new ShiftTime();
		newShift = em.find(ShiftTime.class, shift);
		em.remove(newShift);
		
	    em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/allshift")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse getAllShift(){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		List<ShiftTime> shifts = new ArrayList<ShiftTime>();
		
		Query query = em.createNamedQuery("ShiftTime.findAllShift", ShiftTime.class);
		shifts = query.getResultList();
				
	    em.getTransaction().commit();
		em.close();
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("shifts", shifts);
		JsonResponse jsonRes = new JsonResponse("SUCCESS", "", data);
		return jsonRes;
	}
	
	public static void main(String[] args) {
		
		ShiftTimeDAO shiftDAO = new ShiftTimeDAO();
	
		/*
		List<ShiftTime> shifts = shiftDAO.getAllShift();
		
		for(ShiftTime s : shifts){
			System.out.println(s.getShift());
		}
		
		
		try{
		shiftDAO.deleteShift("2130");
		}catch(Exception e){
			System.out.println("Selected shift does not exists");
		}
		*/
		
		/*
		ShiftTime newShift = new ShiftTime();
		
		newShift.setShift("1800");
		shiftDAO.createShift(newShift);
		newShift.setShift("1830");
		shiftDAO.createShift(newShift);
		newShift.setShift("1900");
		shiftDAO.createShift(newShift);
		newShift.setShift("1930");
		shiftDAO.createShift(newShift);
		newShift.setShift("2000");
		shiftDAO.createShift(newShift);
		newShift.setShift("2030");
		shiftDAO.createShift(newShift);
		newShift.setShift("2100");
		shiftDAO.createShift(newShift);
		newShift.setShift("2200");
		shiftDAO.createShift(newShift);
		newShift.setShift("2230");
		shiftDAO.createShift(newShift);
		newShift.setShift("2300");
		shiftDAO.createShift(newShift);
		newShift.setShift("2330");
		shiftDAO.createShift(newShift);
		*/
	}

}
