package es.unex.pi.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProvisionalBookings {
	
	private long ida;
	private int num;
	private long idp;
	private long price;
	private String name;
	
	public long getIda() {
		return ida;
	}
	
	public void setIda(long ida) {
		this.ida = ida;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public long getIdp() {
		return idp;
	}
	
	public void setIdp(long idp) {
		this.idp = idp;
	}
	
	public long getPrice() {
		return price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
