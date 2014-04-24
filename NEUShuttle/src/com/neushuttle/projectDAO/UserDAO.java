package com.neushuttle.projectDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
	
	@GET
	@Path("/checkloginstatus")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse checkLoginStatus(@Context HttpServletRequest request) {
		HttpSession session =  request.getSession(false);
		if(session != null)
		{
			User user = (User) session.getAttribute("USER");
			if(user != null)
			{
				JsonResponse jsonRes = new JsonResponse("SUCCESS", "", null);
				return jsonRes;
			}
			else
			{
				JsonResponse jsonRes = new JsonResponse("ERROR", "Please login.", null);
				return jsonRes;
			}	
		}
		else
		{
			JsonResponse jsonRes = new JsonResponse("ERROR", "Please login.", null);
			return jsonRes;
		}
			
	}
	
	@POST
	@Path("/createuser")
	public JsonResponse createNewUser(@FormParam("fname") String fName, @FormParam("lname") String lName, @FormParam("email") String email, @FormParam("password") String password, @FormParam("role") String role) {
		User newUser = new User();
		newUser.setfName(fName);
		newUser.setlName(lName);
		newUser.setEmail(email);
		newUser.setRole(role);
		newUser.setHashedPassword(password);
		
		createUser(newUser);

		JsonResponse jsonRes = new JsonResponse("SUCCESS", "", null);
		return jsonRes;
	}
	
	
	public void createUser(User newUser){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(newUser);
		
	    em.getTransaction().commit();
		em.close();
	}

	@POST
	@Path("/userlogin")
	public JsonResponse userLogin(@FormParam("email") String email, @FormParam("password") String password, @Context HttpServletRequest request) {
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setHashedPassword(password);

		User user=null;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select u from User u Where u.email = :uEmail and u.password = :uPassword");
		query.setParameter("uEmail", newUser.getEmail());
		query.setParameter("uPassword", newUser.getPassword());
		
		try {
			user = (User) query.getSingleResult();	
			HttpSession session = request.getSession(true);
			session.setAttribute("USER", user);
			JsonResponse jsonRes = new JsonResponse("SUCCESS", "", null);
			return jsonRes;
		}
		catch (NoResultException nre)
		{
			JsonResponse errorRes = new JsonResponse("ERROR", "Invalid Login!", null);
			return errorRes;
		}
		finally {
			em.getTransaction().commit();
			em.close();			
		}
	}

	@POST
	@Path("/userlogout")
	public void userLogout(@Context HttpServletRequest request) {
		HttpSession session =  request.getSession(false);
		if(session != null)
		{
			session.removeAttribute("USER");
			session.invalidate();
		}
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
