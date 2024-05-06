package es.unex.pi.model;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProvisionalBookings {
	
	private long ida;
	private int num;
	
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

}
