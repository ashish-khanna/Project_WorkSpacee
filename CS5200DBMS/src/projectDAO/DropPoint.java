package projectDAO;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="DropPoint.getAllLocations", query = "select d from DropPoint d")
public class DropPoint {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String locName;
	@OneToMany(mappedBy="dropPoint", cascade=CascadeType.ALL)
	
	private List<Request> requests = new ArrayList<Request>();
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
	 * @return the locName
	 */
	public String getLocName() {
		return locName;
	}
	/**
	 * @param locName the locName to set
	 */
	public void setLocName(String locName) {
		this.locName = locName;
	}
	
	/**
	 * 
	 */
	public DropPoint() {
		super();
	}
	
}
