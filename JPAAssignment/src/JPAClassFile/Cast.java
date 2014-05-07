package JPAClassFile;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cast{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Movie movieActedIn;
	private Actor actorInMovie;
	private String characters;
	private Date dateActedInMovie;
	
	public Cast() {
		super();
	}

	public Cast(int id, Movie movieActedIn, Actor actorInMovie,
			String character, Date dateActedInMovie) {
		super();
		this.id = id;
		this.movieActedIn = movieActedIn;
		this.actorInMovie = actorInMovie;
		this.characters = character;
		this.dateActedInMovie = dateActedInMovie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Movie getMovieActedIn() {
		return movieActedIn;
	}

	public void setMovieActedIn(Movie movieActedIn) {
		this.movieActedIn = movieActedIn;
	}

	public Actor getActorInMovie() {
		return actorInMovie;
	}

	public void setActorInMovie(Actor actorInMovie) {
		this.actorInMovie = actorInMovie;
	}

	public String getCharacter() {
		return characters;
	}

	public void setCharacter(String character) {
		this.characters = character;
	}

	public Date getDateActedInMovie() {
		return dateActedInMovie;
	}

	public void setDateActedInMovie(Date dateActedInMovie) {
		this.dateActedInMovie = dateActedInMovie;
	}
	
	

}
