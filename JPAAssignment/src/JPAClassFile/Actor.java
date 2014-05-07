package JPAClassFile;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Actor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private List<Cast> moviesIn;
	
	public Actor() {
		super();
	}

	public Actor(int id, String firstName, String lastName, Date dateOfBirth,
			List<Cast> moviesIn) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.moviesIn = moviesIn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public List<Cast> getMoviesIn() {
		return moviesIn;
	}

	public void setMoviesIn(List<Cast> moviesIn) {
		this.moviesIn = moviesIn;
	}
	
	

}
