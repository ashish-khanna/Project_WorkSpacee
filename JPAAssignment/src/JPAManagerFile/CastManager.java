package JPAManagerFile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import JPAClassFile.*;

public class CastManager {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA3");
	
	public void createCast(int actorId, int movieId, Cast cast){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(cast);
		
		Actor actor = em.find(Actor.class, actorId);
		Movie movie = em.find(Movie.class, movieId);
		
		cast.setActorInMovie(actor);
		cast.setMovieActedIn(movie);
		em.merge(cast);
		
		actor.getMoviesIn().add(cast);
		em.merge(actor);
		
		movie.getCast().add(cast);
		em.merge(movie);
		
		em.getTransaction().commit();
		em.close();
	}

	public Cast getCast(int castId){
		Cast cast = new Cast();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		cast = em.find(Cast.class, castId);
		
		em.getTransaction().commit();
		em.close();

		return cast;
	}
	
	public List<Cast> getCastForMovie(int movieId){
		List<Cast> casts = new ArrayList<Cast>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Movie movie = em.find(Movie.class, movieId);
		casts = movie.getCast();
		
		em.getTransaction().commit();
		em.close();
		
		return casts;
				
	}

	public void changeCharacterForCast(int castId, String newCharacter){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Cast cast = new Cast();
		cast = em.find(Cast.class, castId);
		
		cast.setCharacter(newCharacter);
		em.merge(cast);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
		/*
		CastManager castmanager = new CastManager();
		
		Cast cast = new Cast();
		//cast.setCharacter("Sexo");

		//castmanager.createCast(3, 1, cast);
		
		//cast = castmanager.getCast(2);
		//System.out.println(cast.getCharacter());
		
		List<Cast> casts = new ArrayList<Cast>();
		casts = castmanager.getCastForMovie(2);
		
		for(Cast c: casts){
			
			System.out.println(c.getCharacter());
		}
		
		//castmanager.changeCharacterForCast(2, "Testing");
			
		*/
	}

}
