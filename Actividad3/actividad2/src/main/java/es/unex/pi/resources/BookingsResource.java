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
import es.unex.pi.model.ProvisionalBookings;
import es.unex.pi.dao.JDBCProvisionalBookingsDAOImpl;
import es.unex.pi.dao.ProvisionalBookingsDAO;
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
	
	// Get que devuelve las reservas provisionales de un usuario (aunque no hace falta el usuario)
	
	@GET
	@Path("/provisional")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProvisionalBookings> getProvisionalBookingsJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		ProvisionalBookingsDAO provisionalBookingDao = new JDBCProvisionalBookingsDAOImpl();
		provisionalBookingDao.setConnection(conn);

		List<ProvisionalBookings> provisionalBookings = provisionalBookingDao.getAll();

		return provisionalBookings;

	}
	
	//El id que se le pasa por parametro es de una habitacion
	
	@GET
	@Path("/{ida:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Property getProperty(@PathParam("ida") long ida) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);
		
		Accommodation accommodation = accommodationDao.get(ida);
		
		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);
		
		Property property = propertyDao.get(accommodation.getIdp());

		return property;
	}
	
	
	@POST
	@Path("/provisional/{ida:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postProvisionalBookings(int num, @PathParam("ida") long ida,
			@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		ProvisionalBookingsDAO provisionalBookingDao = new JDBCProvisionalBookingsDAOImpl();
		provisionalBookingDao.setConnection(conn);
		
		ProvisionalBookings provisionalBooking = new ProvisionalBookings();
		
		//Si ya hay una reserva provisional con ese ida, borramos la anterior
		if (provisionalBookingDao.get(ida) != null) {
			provisionalBookingDao.delete(ida);
		}
		
		//Se crea una nueva reserva provisional
		
		provisionalBooking = new ProvisionalBookings();

		provisionalBooking.setIda(ida);
		provisionalBooking.setNum(num);
		provisionalBookingDao.add(provisionalBooking);

		return Response.created(uriInfo.getAbsolutePath()).build();
	}
	
	
	@DELETE
	@Path("/provisional/{ida:[0-9]+}")
	public Response deleteProvisionalBookings(@PathParam("ida") long ida, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		ProvisionalBookingsDAO provisionalBookingDao = new JDBCProvisionalBookingsDAOImpl();
		provisionalBookingDao.setConnection(conn);

		provisionalBookingDao.delete(ida);

		return Response.noContent().build();
	}
	
	
	@DELETE
	@Path("/provisional")
	public Response deleteAllProvisionalBookings(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		ProvisionalBookingsDAO provisionalBookingDao = new JDBCProvisionalBookingsDAOImpl();
		provisionalBookingDao.setConnection(conn);

		provisionalBookingDao.deleteAll();

		return Response.noContent().build();
	}
}
