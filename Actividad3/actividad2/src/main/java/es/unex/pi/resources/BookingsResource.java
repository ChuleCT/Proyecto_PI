package es.unex.pi.resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.pi.model.User;
import es.unex.pi.model.Property;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.BookingPropertyInfo;
import es.unex.pi.dao.JDBCBookingDAOImpl;
import es.unex.pi.dao.BookingDAO;
import es.unex.pi.model.Booking;
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl;
import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import es.unex.pi.dao.AccommodationDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/bookings")

public class BookingsResource {

	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;

	// Get que devuelve todos los BookingPropertyInfo de un usuario
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BookingPropertyInfo> getBookingsJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		// Se crean instancias de los DAO necesarios

		BookingDAO bookingDao = new JDBCBookingDAOImpl();
		bookingDao.setConnection(conn);

		BookingsAccommodationsDAO bookingsAccommodationsDao = new JDBCBookingsAccommodationsDAOImpl();
		bookingsAccommodationsDao.setConnection(conn);

		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		// Se obtiene el usuario de la sesion
		User user = (User) request.getSession().getAttribute("user");

		// Se obtienen todas las reservas del usuario
		List<Booking> bookings = bookingDao.getAllByIdu(user.getId());
		
		System.out.println("Numero de reservas: " + bookings.size());

		// Se crea una lista de BookingPropertyInfo para almacenar la informacion de la
		// reserva y la propiedad
		List<BookingPropertyInfo> bookingsInfo = new ArrayList<BookingPropertyInfo>();

		for (Booking booking : bookings) {
			// Por cada reserva se obtiene la propiedad
			List<BookingsAccommodations> bookingsAccommodations = bookingsAccommodationsDao
					.getAllByBooking(booking.getId());
			if (!bookingsAccommodations.isEmpty()) {
				BookingsAccommodations bookingAccommodation = bookingsAccommodations.get(0);
				Accommodation accommodation = accommodationDao.get(bookingAccommodation.getIdacc());
				Property property = propertyDao.get(accommodation.getIdp());
				BookingPropertyInfo bookingInfo = new BookingPropertyInfo(booking, property);
				bookingsInfo.add(bookingInfo);
			}

		}

		return bookingsInfo;

	}
}
