package projectDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import projectEntity.*;

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
	
	public static void main(String[] args) {
		
		ShiftTimeDAO shiftDAO = new ShiftTimeDAO();
			
		try{
		shiftDAO.deleteShift("2130");
		}catch(Exception e){
			System.out.println("Selected shift does not exists");
		}
		/*
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
		newShift.setShift("2130");
		shiftDAO.createShift(newShift);
		*/
	}

}
