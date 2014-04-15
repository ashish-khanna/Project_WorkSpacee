package projectDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import projectEntity.*;

@Path("/user")
public class UserDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
	
	public void createUser(User newUser){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(newUser);
		
	    em.getTransaction().commit();
		em.close();
	}

	@GET
	@Path("/getuser/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("email") String email){
		User user = new User();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		//Query query = em.createQuery("Select u From User u Where u.Email = :uEmail");
		Query query = em.createNamedQuery("User.getUserDetails", User.class);
		query.setParameter("uEmail", email);
		user = (User) query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		return user;
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
