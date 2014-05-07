package entityClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movie {
	protected Integer id;
	protected String title;
	protected String posterImage;
	protected Date releaseDate;
	protected List<Comment> comments = new ArrayList<Comment>();
	protected List<Cast> casts = new ArrayList<Cast>();
	
	public Movie(){
	}
	
	public Movie(Integer id, String title, String posterImage,
			Date releaseDate, List<Comment> comments, List<Cast> casts) {
		this.id = id;
		this.title = title;
		this.posterImage = posterImage;
		this.releaseDate = releaseDate;
		this.comments = comments;
		this.casts = casts;
	}

	public Integer getId() {
		return id;
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
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Cast> getCasts() {
		return casts;
	}
	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}
	
}
