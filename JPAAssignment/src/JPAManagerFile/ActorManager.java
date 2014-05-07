package JPAManagerFile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JPAClassFile.*;

public class ActorManager {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA3");
	
	public void createActor(Actor newActor){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(newActor);
		
		em.getTransaction().commit();
		em.close();
	}

	public List<Actor> getAllActors(){
		List<Actor> actors = new ArrayList<Actor>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select actor from Actor actor");
		actors = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return actors;
	}
	
	public List<Cast> getCastForActor(int actorId){
		List<Cast> casts = new ArrayList<Cast>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, actorId);
		casts = actor.getMoviesIn();
		
		em.getTransaction().commit();
		em.close();
		
		return casts;
	}

	public List<Movie> getMoviesForActor(int actorId){
		List<Movie> movies = new ArrayList<Movie>();
		List<Cast> casts = new ArrayList<Cast>();
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, actorId);
		casts = actor.getMoviesIn();
		
		for(Cast cs : casts){
			movies.add(cs.getMovieActedIn());
		}
		
		em.getTransaction().commit();
		em.close();
		
		return movies;
	}
	
	public static void main(String[] args) {
		/*
		ActorManager actormanager = new ActorManager();
		Actor actor = new Actor();
		
		actor.setFirstName("Ashish");
		actor.setLastName("K");
		
		//actormanager.createActor(actor);
		
		actor.setFirstName("Praveen");
		actor.setLastName("S");
		
		//actormanager.createActor(actor);
		
		actor.setFirstName("Jyoti");
		actor.setLastName("S");
		
		//actormanager.createActor(actor);
		
		
		List<Actor> actors = new ArrayList<Actor>();
		actors = actormanager.getAllActors();
		
		for(Actor a : actors){
			System.out.println(a.getFirstName());
		}
		
		List<Cast> casts = new ArrayList<Cast>();
		casts = actormanager.getCastForActor(1);
		
		for(Cast c : casts){
			System.out.println(c.getCharacter());
		}
			
			
		List<Movie> movies = new ArrayList<Movie>();
		movies = actormanager.getMoviesForActor(1);
		
		for(Movie m : movies){
			System.out.println(m.getTitle());
		}
		*/	
	}

}
