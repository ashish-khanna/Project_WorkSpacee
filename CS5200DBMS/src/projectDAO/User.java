package projectDAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@NamedQuery(name="User.getUserDetails", query="Select u From User u Where u.email = :uEmail")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fName;
	private String lName;
	private String email;
	private String password;
	private String role;
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	
	private List<Request> requests = new ArrayList<Request>();
	/**
	 * 
	 */
	public User() {
		super();
	}
	/**
	 * @param id
	 * @param fName
	 * @param lName
	 * @param email
	 * @param password
	 * @param role
	 * @param requests
	 */
	public User(int id, String fName, String lName, String email,
			String password, String role, List<Request> requests) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.requests = requests;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}
	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}
	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the requests
	 */
	public List<Request> getRequests() {
		return requests;
	}
	/**
	 * @param requests the requests to set
	 */
	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	
	

}
