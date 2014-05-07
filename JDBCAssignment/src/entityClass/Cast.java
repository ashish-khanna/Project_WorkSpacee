package entityClass;

public class Cast {
	protected Integer id;
	protected String characterName;
	protected Movie movie;
	protected Actor actor;
	
	public Cast(){
	}
	
	public Cast(Integer id, String characterName, Movie movie, Actor actor) {
		this.id = id;
		this.characterName = characterName;
		this.movie = movie;
		this.actor = actor;
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
