/**
 * 
 */
package projectDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author Ashish
 *
 */
public class DropPointDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CS5200DBMS");
	/**
	 * @param args
	 */
	
	public void createDropPoint(String dropPoint){
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		DropPoint dp = new DropPoint();
		dp.setLocName(dropPoint);
		
		em.persist(dp);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<DropPoint> getAllLocs(){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query q = em.createNamedQuery("DropPoint.getAllLocations", DropPoint.class);
		List<DropPoint> dpList = q.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return dpList;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DropPointDAO dpDao = new DropPointDAO();
//		dpDao.createDropPoint("Boylston Street");
//		dpDao.createDropPoint("Mcgreevy Street");
//		dpDao.createDropPoint("Huntington Street");
//		dpDao.createDropPoint("ward Street");
//		dpDao.createDropPoint("blackSmith Street");
//		dpDao.createDropPoint("alphonsus Street");
		
		List<DropPoint> dplist = dpDao.getAllLocs();
		
		for (DropPoint dropPoint : dplist) {
			System.out.println(dropPoint.getId());
			System.out.println(dropPoint.getLocName());
		}
	}

}
