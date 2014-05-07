package entityClass;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Tower {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	
	@XmlAttribute
	private String name;
	@XmlAttribute
	private Double height;
	@XmlAttribute
	private int sides;
	@ManyToOne
	@JoinColumn(name="siteid")
	@XmlTransient
	private Site site;
	@OneToMany(mappedBy="tower", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="equipment")
	private List<Equipment> equipments;
	/**
	 * 
	 */
	public Tower() {
		super();
	}
	/**
	 * @param id
	 * @param name
	 * @param height
	 * @param sides
	 * @param site
	 * @param equipments
	 */
	public Tower(int id, String name, Double height, int sides, Site site,
			List<Equipment> equipments) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.sides = sides;
		this.site = site;
		this.equipments = equipments;
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
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * @return the sides
	 */
	public int getSides() {
		return sides;
	}
	/**
	 * @param sides the sides to set
	 */
	public void setSides(int sides) {
		this.sides = sides;
	}
	/**
	 * @return the site
	 */
	public Site getSite() {
		return site;
	}
	/**
	 * @param site the site to set
	 */
	public void setSite(Site site) {
		this.site = site;
	}
	/**
	 * @return the equipments
	 */
	public List<Equipment> getEquipments() {
		return equipments;
	}
	/**
	 * @param equipments the equipments to set
	 */
	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	} 
}
