/**
 * 
 */
package entityClass;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import DAO.*;

/**
 * @author Ashish
 *
 */
public class User {
	protected Integer id;
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected Date dateOfBirth;
	protected List<Comment> comments = new ArrayList<Comment>();
	protected UserManager usermanager;
	
	public User(){
	}
		
	public User(UserManager usermanager, String username, String password, String firstName,
			String lastName, String email, Date dateOfBirth) {
		this.usermanager = usermanager;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<Comment> getComments() {
		comments = usermanager.getComments(id);
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	

}
