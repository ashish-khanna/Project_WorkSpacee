package entityClass;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DAO.*;

public class Actor {
	protected Integer id;
	protected String actorId;
	protected String firstName;
	protected String lastName;
	protected Date dateOfBirth;
	protected List<Cast> casts = new ArrayList<Cast>();
	protected ActorManager actormanager = new ActorManager();
	
	public Actor(){
	}
	
	public Actor(ActorManager actormanager, String actorid, String firstName, String lastName,
			Date dateOfBirth) {
		this.actormanager = actormanager;
		this.actorId = actorid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Cast> getCasts() {
		casts = actormanager.getCasts(id);
		return casts;
	}
	
}
