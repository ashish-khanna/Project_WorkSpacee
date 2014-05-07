package JPAManagerFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import JPAClassFile.*;

public class ReviewManager {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA3");
	
	public void createReview(int userId, int movieId, Review review){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(review);
		
		User user = em.find(User.class, userId);
		Movie movie = em.find(Movie.class, movieId);
	
		review.setMovieReviewed(movie);
		review.setReviewer(user);
		em.merge(review);
		
		user.getReviews().add(review);
		em.merge(user);
		
		movie.getReviews().add(review);
		em.merge(movie);
		
		em.getTransaction().commit();
		em.close();
	}

	public Review getReview(int reviewId){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Review review = new Review();
		review = em.find(Review.class, reviewId);
		
		em.getTransaction().commit();
		em.close();
				
		return review;
	}
	
	public void updateReview(int reviewId, Review review){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Review rw = new Review();
		rw = em.find(Review.class, reviewId);
		
		if(review.getComment() != null)
		rw.setComment(review.getComment());
		
		if(review.getDateCommented() != null)
		rw.setDateCommented(review.getDateCommented());
		
		if(review.getMovieReviewed() != null)
		rw.setMovieReviewed(review.getMovieReviewed());
		
		if(review.getReviewer() != null)
		rw.setReviewer(review.getReviewer());
		
		if(review.getStars() > 0)
		rw.setStars(review.getStars());
		
		em.merge(rw);
				
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteReview(int reviewId){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Review review = new Review();
		review = em.find(Review.class, reviewId);
		
		User user = review.getReviewer();
		Movie movie = review.getMovieReviewed();
		
		user.getReviews().remove(review);
		movie.getReviews().remove(review);
		
		em.remove(review);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
		
		ReviewManager reviewmanager = new ReviewManager();
		
		Review review = new Review();
		//review.setComment("lovely mind");
		//review.setStars(5);
		//reviewmanager.createReview(4, 2, review); 

		reviewmanager.deleteReview(6);
		
		//System.out.println(review.getComment());
		
	}

}
