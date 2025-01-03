package es.unex.pi.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Service {

    private long id;
    private String name;
    private String icon;

    
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
}