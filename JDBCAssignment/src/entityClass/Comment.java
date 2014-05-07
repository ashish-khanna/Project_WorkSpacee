package entityClass;

import java.util.Date;

public class Comment {
	protected Integer id;
	protected String comment;
	protected Date date;
	protected User user;
	protected Movie movie;
	
	public Comment(){
	}
	
	public Comment(Integer id, String comment, Date date, User user, Movie movie) {
		this.id = id;
		this.comment = comment;
		this.date = date;
		this.user = user;
		this.movie = movie;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
