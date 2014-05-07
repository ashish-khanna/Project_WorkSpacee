package entityClass;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@NamedQueries(value = { @NamedQuery(
		name = "findAllSites",
		query = "select s from Site s") })
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Site {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private Double latitude;
	@XmlAttribute
	private Double longitude;
	@XmlElement(name="tower")
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Tower> towers;
	
	
	/**
	 * 
	 */
	public Site() {
		super();
	}
	/**
	 * @param id
	 * @param name
	 * @param latitude
	 * @param longitude
	 * @param towers
	 */
	public Site(int id, String name, Double latitude, Double longitude,
			List<Tower> towers) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.towers = towers;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the towers
	 */
	public List<Tower> getTowers() {
		return towers;
	}
	/**
	 * @param towers the towers to set
	 */
	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	
	

}
