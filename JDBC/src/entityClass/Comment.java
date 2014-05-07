package entityClass;

import java.util.Date;

import DAO.CommentManager;

public class Comment {
	protected Integer id;
	protected String comment;
	protected Date date;
	protected User user;
	protected Movie movie;
	protected Integer userId;
	protected Integer movieId;
	protected CommentManager commentmanager;
	
	public Comment(){
	}
	
	public Comment(CommentManager commentmanager, String comment, Date date, User user, Movie movie) {
		this.commentmanager = commentmanager;
		this.comment = comment;
		this.date = date;
		this.user = user;
		this.movie = movie;
	}
	
	public Comment(CommentManager commentmanager, String comment, Date date, Integer userId, Integer movieId) {
		this.commentmanager = commentmanager;
		this.comment = comment;
		this.date = date;
		this.userId = userId;
		this.movieId = movieId;
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
