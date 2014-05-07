package entityClass;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class SiteDatabase {

	@XmlElement(name="site")
	private List<Site> sites;

	/**
	 * 
	 */
	public SiteDatabase() {
		super();
	}

	/**
	 * @param sites
	 */
	public SiteDatabase(List<Site> sites) {
		super();
		this.sites = sites;
	}

	/**
	 * @return the sites
	 */
	public List<Site> getSites() {
		return sites;
	}

	/**
	 * @param sites the sites to set
	 */
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
	
}
