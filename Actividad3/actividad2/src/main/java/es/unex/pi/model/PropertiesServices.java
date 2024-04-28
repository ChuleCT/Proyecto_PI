package es.unex.pi.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PropertiesServices {

	private long idp;
	private long ids;
	
	public long getIdp() {
		return idp;
	}
	
	public void setIdp(long idr) {
		this.idp = idr;
	}
	
	public long getIds() {
		return ids;
	}
	
	public void setIds(long ids) {
		this.ids = ids;
	}
}
