package entityClass;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Actor {
	protected Integer id;
	protected String firstName;
	protected String lastName;
	protected Date dateOfBirth;
	protected List<Cast> casts = new ArrayList<Cast>();
	
	public Actor(){
	}
	
	public Actor(Integer id, String firstName, String lastName,
			Date dateOfBirth, List<Cast> casts) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.casts = casts;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		return casts;
	}
	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}
}
