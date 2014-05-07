package JPAClassFile;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String comment;
	private int stars;
	private Date dateCommented;
	private Movie movieReviewed;
	private User reviewer;
	
	public Review() {
		super();
	}

	public Review(int id, String comment, int stars, Date dateCommented,
			Movie movieReviewed, User reviewer) {
		super();
		this.id = id;
		this.comment = comment;
		this.stars = stars;
		this.dateCommented = dateCommented;
		this.movieReviewed = movieReviewed;
		this.reviewer = reviewer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public Date getDateCommented() {
		return dateCommented;
	}

	public void setDateCommented(Date dateCommented) {
		this.dateCommented = dateCommented;
	}

	public Movie getMovieReviewed() {
		return movieReviewed;
	}

	public void setMovieReviewed(Movie movieReviewed) {
		this.movieReviewed = movieReviewed;
	}

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}

	
}
