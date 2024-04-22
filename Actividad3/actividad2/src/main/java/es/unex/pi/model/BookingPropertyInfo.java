package es.unex.pi.model;
import java.util.Map;
import java.util.HashMap;
import es.unex.pi.model.BookingsAccommodations;

public class BookingPropertyInfo {
    private Booking booking;
    private Property property;
    
    // Mapa que asocia el nombre la habitacion con el numero de habitaciones que ha cogido de ese tipo
    private Map<String, Integer> rooms = new HashMap<String, Integer>();

    public BookingPropertyInfo(Booking booking, Property property) {
        this.booking = booking;
        this.property = property;
    }

    // Getters y setters para booking y property
    
	public Booking getBooking() {
		return booking;
	}
	
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	public Property getProperty() {
		return property;
	}
	
	public void setProperty(Property property) {
		this.property = property;
	}
	
	public Map<String, Integer> getRooms() {
		return rooms;
	}
	
	public void setRooms(Map<String, Integer> rooms) {
		this.rooms = rooms;
	}
	
	// Añadir una habitacion al mapa de habitaciones
	public void addRoom(String roomType, int quantity) {
		rooms.put(roomType, quantity);
	}
}