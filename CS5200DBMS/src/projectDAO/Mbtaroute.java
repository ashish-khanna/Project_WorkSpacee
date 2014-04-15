package projectDAO;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the mbtaroute database table.
 * 
 */
@Entity
@NamedQuery(name="Mbtaroute.findAll", query="SELECT m FROM Mbtaroute m")
@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Mbtaroute {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlTransient
	private int id;
	
	@XmlElement(name="directionid")
	@Column(name="direction_id")
	private String directionId;

	@XmlElement(name="station_name")
	@Column(name="parent_station_name")
	private String parentStationName;

	@XmlElement(name="routeid")
	@Column(name="route_id")
	private String routeId;

	@XmlElement(name="stopid")
	@Column(name="stop_id")
	private String stopId;

	@XmlElement(name="stoplat")
	@Column(name="stop_lat")
	private float stopLat;

	@XmlElement(name="stoplon")
	@Column(name="stop_lon")
	private float stopLon;

	@XmlElement(name="stoporder")
	@Column(name="stop_order")
	private String stopOrder;

	
	public Mbtaroute() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirectionId() {
		return this.directionId;
	}

	public void setDirectionId(String directionId) {
		this.directionId = directionId;
	}

	public String getParentStationName() {
		return this.parentStationName;
	}

	public void setParentStationName(String parentStationName) {
		this.parentStationName = parentStationName;
	}

	public String getRouteId() {
		return this.routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getStopId() {
		return this.stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public float getStopLat() {
		return this.stopLat;
	}

	public void setStopLat(float stopLat) {
		this.stopLat = stopLat;
	}

	public float getStopLon() {
		return this.stopLon;
	}

	public void setStopLon(float stopLon) {
		this.stopLon = stopLon;
	}

	public String getStopOrder() {
		return this.stopOrder;
	}

	public void setStopOrder(String stopOrder) {
		this.stopOrder = stopOrder;
	}

}