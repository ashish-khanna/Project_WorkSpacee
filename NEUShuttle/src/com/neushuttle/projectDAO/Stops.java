package com.neushuttle.projectDAO;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class Stops {
	
	@XmlElement(name="stop")
	List<Mbtaroute> stops;

	/**
	 * 
	 */
	public Stops() {
		super();
	}

	/**
	 * @return the stops
	 */
	public List<Mbtaroute> getStops() {
		return stops;
	}

	/**
	 * @param stops the stops to set
	 */
	public void setStops(List<Mbtaroute> stops) {
		this.stops = stops;
	}

}
