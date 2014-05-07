package entityClass;

import DAO.CastManager;

public class Cast {
	protected Integer id;
	protected String characterName;
	protected Movie movie;
	protected Actor actor;
	protected CastManager castmanager;
	protected Integer movieid;
	protected Integer actorid;
	
	
	public Cast(){
	}
	
	public Cast(CastManager castmanager, String characterName, Movie movie, Actor actor) {
		this.castmanager = castmanager;;
		this.characterName = characterName;
		this.movie = movie;
		this.actor = actor;
	}

	public Cast(CastManager castmanager, String characterName, Integer movieid, Integer actorid) {
		this.castmanager = castmanager;;
		this.characterName = characterName;
		this.movieid = movieid;
		this.actorid = actorid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
