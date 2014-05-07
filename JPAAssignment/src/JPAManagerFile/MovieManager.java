package JPAManagerFile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import JPAClassFile.*;

public class MovieManager {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA3");
	
	public List<Movie> listAllMovies(){
		List<Movie> movies = new ArrayList<Movie>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select movie from Movie movie");
		movies = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return movies;
	}
	
	public List<Review> getReviewsForMovie(int movieId){
		List<Review> reviews = new ArrayList<Review>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, movieId);
		reviews = movie.getReviews();
		
		em.getTransaction().commit();
		em.close();
				
		return reviews;
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

	
	
	public static void main(String[] args) {

		/*
		MovieManager moviemanager = new MovieManager();
		//List<Movie> movies = new ArrayList<Movie>();
		
		
		List<Movie> movies = moviemanager.listAllMovies();
		for(Movie movie : movies){
			System.out.println(movie.getTitle());
		}
		List<Review> reviews = moviemanager.getReviewsForMovie(2);
		for(Review review : reviews){
			System.out.println(review.getComment());
		}
		List<Cast> casts = moviemanager.getCastForMovie(2);
		for(Cast cast : casts){
			System.out.println(cast.getCharacter());
		}
		
		
		List<Cast> casts = moviemanager.getCastForMovie(2);
		for(Cast cast : casts){
			System.out.println(cast.getCharacter());
		}
		*/	
	}

}
