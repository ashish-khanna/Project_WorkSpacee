package projectDAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
public class VehicleShiftMapping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private String shift;
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	@JsonIgnore
	private Vehicle vehicle;
	
	/**
	 * 
	 */
	public VehicleShiftMapping() {
		super();
	}

	/**
	 * @param id
	 * @param shift
	 * @param vehicle
	 */
	public VehicleShiftMapping(int id, String shift, Vehicle vehicle) {
		super();
		this.id = id;
		this.shift = shift;
		this.vehicle = vehicle;
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
	
	
}
