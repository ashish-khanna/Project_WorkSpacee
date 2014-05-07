package JPAManagerFile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import JPAClassFile.Movie;
import JPAClassFile.Review;
import JPAClassFile.User;

public class UserManager {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA3");
	
	public void createUser(User newUser){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(newUser);

		em.getTransaction().commit();
		em.close();
	}
	
	
	public List<Review> getReviewsForUser(int userId){
		List<Review> reviews = new ArrayList<Review>();
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		User user = em.find(User.class, userId);
		reviews = user.getReviews();

		em.getTransaction().commit();
		em.close();
		
		return reviews;
	}

	public void addReviewForMovie(int userId, int movieId, Review review){
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
	
	public static void main(String[] args) {
	/*
	Date dob = new Date(88, 2, 16);	
		
	User user = new User();
	user.setFirstName("Prvn");
	user.setLastName("salitra");
	user.setUsername("salitra");
	user.setPassword("password");
	user.setEmail("salitra.praveen@gmail.com");
	user.setDateOfBirth(dob);
	
	UserManager usermanager = new UserManager();
	
	//usermanager.createUser(user);
	
	Review review = new Review();
	review.setComment("Wondrful movie");
	review.setDateCommented(dob);
	review.setStars(4);
	
	// usermanager.addReviewForMovie(1, 2, review);
	
	List<Review> reviews = new ArrayList<Review>();
	reviews = usermanager.getReviewsForUser(1);
	
	for(Review rw : reviews){
		System.out.println(rw.getComment() + "Rating -" + rw.getStars());
	}
	*/
	}
}
