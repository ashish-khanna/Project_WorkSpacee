package com.neushuttle.projectDAO;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="User.getUserDetails", query="Select u From User u Where u.email = :uEmail")
public class User implements Serializable {
	
	private static final long serialVersionUID = -7018641497430041669L;
	
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
	
	public void setHashedPassword(String password) {
		this.password = hashPassword(password);
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
	
	private String hashPassword (String password){
		String SALT = "NorTHEAStern UNIversITY";
		String pwdSalt = password + SALT;
		byte[] hashedPassword = "".getBytes();
		try {
		      MessageDigest sha = MessageDigest.getInstance("SHA-256");
		      hashedPassword = sha.digest(pwdSalt.getBytes());
		      sha.reset();
		    }
		    catch (NoSuchAlgorithmException ex){
		      System.out.println("Error " +ex);
		    }
		return hexEncode(hashedPassword);
	}
	
	
	/**
	  * The byte[] returned by MessageDigest does not have a nice
	  * textual representation, so some form of encoding is usually performed.
	  *
	  * This implementation follows the example of David Flanagan's book
	  * "Java In A Nutshell", and converts a byte array into a String
	  * of hex characters.
	  */
	   private String hexEncode( byte[] aInput){
	    StringBuffer result = new StringBuffer();
	    char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	    for (int idx = 0; idx < aInput.length; ++idx) {
	      byte b = aInput[idx];
	      result.append( digits[ (b&0xf0) >> 4 ] );
	      result.append( digits[ b&0x0f] );
	    }
	    return result.toString();
	  }  

}
