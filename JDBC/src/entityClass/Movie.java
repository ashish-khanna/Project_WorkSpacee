package entityClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.MovieManager;

public class Movie {
	protected Integer id;
	protected String movieId;
	protected String title;
	protected String posterImage;
	protected Date releaseDate;
	protected List<Comment> comments = new ArrayList<Comment>();
	protected List<Cast> casts = new ArrayList<Cast>();
	protected MovieManager moviemanager;
	
	public Movie(){
	}
	
	public Movie(MovieManager moviemanager, String movieId, String title, String posterImage,
			Date releaseDate) {
		this.moviemanager = moviemanager;
		this.movieId = movieId;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
	}

	public Integer getId() {
		return id;
	}
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosterImage() {
		return posterImage;
	}
	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<Comment> getComments() {
		comments = moviemanager.getComments(id);
		return comments;
	}
	public List<Cast> getCasts() {
		casts = moviemanager.getCasts(id);
		return casts;
	}
	
}
