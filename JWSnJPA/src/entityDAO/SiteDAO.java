package entityDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entityClass.Site;

@Path("/site")
public class SiteDAO {

	EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("JWSnJPA");

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId) {
		Site site = new Site();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		site = em.find(Site.class, siteId);

		em.getTransaction().commit();
		em.close();

		return site;
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites() {
		List<Site> sites = new ArrayList<Site>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findAll", Site.class);
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();

		return sites;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(int siteId, Site site){
		List<Site> sites = new ArrayList<Site>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		site.setId(siteId);
		em.merge(site);
		
		em.getTransaction().commit();
		em.close();
		
		sites = findAllSites();
		
		return sites;
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(int siteId){
		List<Site> sites = new ArrayList<Site>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Site site = em.find(Site.class, siteId);
		em.remove(site);
		
		em.getTransaction().commit();
		em.close();
		
		return findAllSites();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(site);
		
		em.getTransaction().commit();
		em.close();
		
		return findAllSites();
	}

	public static void main(String[] args) {
		List<Site> sites = new ArrayList<Site>();
		SiteDAO sDao = new SiteDAO();

// FIND ALL SITES		
//		sites = sDao.findAllSites();

// FIND SITE BY ID		
//		Site site = sDao.findSite(1);
//		System.out.println(site.getName());
		
// UPDATE SITE		
//		Site s = new Site();
//		s.setLatitude(1234.4321);
//		s.setLongitude(987.789);
//		s.setName("Site Developed");
//		s.setTowers(site.getTowers());
//		sites = sDao.updateSite(1, s);
		
// CREATE SITE
//		sDao.createSite(s);
		
// DELETE FROM SITE
//		sDao.removeSite(4);
		
//		for(Site i : sites){
//		System.out.print(i.getName());
//	}
	
		
	}

}
