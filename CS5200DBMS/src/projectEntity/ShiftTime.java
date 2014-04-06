package projectEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShiftTime {

	@Id
	String shift;

	/**
	 * 
	 */
	public ShiftTime() {
		super();
	}

	/**
	 * @param shift
	 */
	public ShiftTime(String shift) {
		super();
		this.shift = shift;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
	
	
}
