package projectDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import projectEntity.*;

public class UserDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
	
	public void createUser(User newUser){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(newUser);
		
	    em.getTransaction().commit();
		em.close();
	}

	
	
	public void template(){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

	    em.getTransaction().commit();
		em.close();
	}

	public static void main(String[] args) {
	
		UserDAO userDAO = new UserDAO();
		User newUser = new User();
		
		newUser.setfName("Ashish");
		newUser.setlName("Khanna");
		newUser.setEmail("khanna.a@husky.neu.edu");
		newUser.setPassword("Acc12345");
		newUser.setRole("Student");
		
		//userDAO.createUser(newUser);
		
		newUser.setfName("Kevin");
		newUser.setlName("Abrahim");
		newUser.setEmail("kevin@husky.neu.edu");
		newUser.setPassword("Acc12345");
		newUser.setRole("Student");
		
		//userDAO.createUser(newUser);
		
		newUser.setfName("Avaneesh");
		newUser.setlName("Murthy");
		newUser.setEmail("murthy.a@husky.neu.edu");
		newUser.setPassword("Acc12345");
		newUser.setRole("Student");
		
		//userDAO.createUser(newUser);
	}

}
