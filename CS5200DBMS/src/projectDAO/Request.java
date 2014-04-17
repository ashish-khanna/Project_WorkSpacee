package projectDAO;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
public class Request {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Date shiftDate;
	private String shift;
	private String status;
	@ManyToOne
	@JoinColumn(name="drop_id")
	private DropPoint dropPoint;
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	/**
	 * 
	 */
	public Request() {
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
	 * @return the shiftDate
	 */
	public Date getShiftDate() {
		return shiftDate;
	}

	/**
	 * @param shiftDate the shiftDate to set
	 */
	public void setShiftDate(Date shiftDate) {
		this.shiftDate = shiftDate;
	}

	/**
	 * @return the shift
	 */
	public String getShift() {
		return shift;
	}

	/**
	 * @param shift the shift to set
	 */
	public void setShift(String shift) {
		this.shift = shift;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}


	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	/**
	 * @return the dropPoint
	 */
	public DropPoint getDropPoint() {
		return dropPoint;
	}


	/**
	 * @param dropPoint the dropPoint to set
	 */
	public void setDropPoint(DropPoint dropPoint) {
		this.dropPoint = dropPoint;
	}


	
	
	

}
