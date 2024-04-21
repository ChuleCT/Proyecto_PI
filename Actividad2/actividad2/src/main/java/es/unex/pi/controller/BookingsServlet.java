package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import es.unex.pi.dao.BookingDAO;
import es.unex.pi.dao.JDBCBookingDAOImpl;
import es.unex.pi.model.Booking;
import es.unex.pi.model.BookingPropertyInfo;
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.User;

import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl;

import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import es.unex.pi.model.Accommodation;

import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.model.Property;

/**
 * Servlet implementation class BookingsServlet
 */
public class BookingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(BookingsServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtener la conexión de la base de datos desde el contexto de servlet
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");

		// Crear instancias de los DAO necesarios
		BookingDAO bookingDAO = new JDBCBookingDAOImpl();
		bookingDAO.setConnection(conn);

		BookingsAccommodationsDAO bookingsAccommodationsDAO = new JDBCBookingsAccommodationsDAOImpl();
		bookingsAccommodationsDAO.setConnection(conn);

		AccommodationDAO accommodationDAO = new JDBCAccommodationDAOImpl();
		accommodationDAO.setConnection(conn);

		PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
		propertyDAO.setConnection(conn);

		// Obtener la sesión y el usuario actual
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// Obtener todas las reservas del usuario
		List<Booking> bookings = bookingDAO.getAllByIdu(user.getId());

		// Crear una lista de BookingPropertyInfo para almacenar la información de la
		// reserva y la propiedad

		List<BookingPropertyInfo> bookingsProperties = new ArrayList<BookingPropertyInfo>();

		//muestra el tamaño de la lista de reservas
		
				logger.info("\n\n\n\n\n\n\n\n\n");
				
				logger.info("Tamaño de la lista de reservas: " + bookings.size());
				
				logger.info("\n\n\n\n\n\n\n\n\n");

		if (bookings != null) {
			for (Booking booking : bookings) {
				// Por cada reserva obtener la propiedad asociada
				List<BookingsAccommodations> bookingsAccommodations = bookingsAccommodationsDAO
						.getAllByBooking(booking.getId());

				// Verificar si la lista de bookingsAccommodations está vacía
				if (!bookingsAccommodations.isEmpty()) {
					BookingsAccommodations bookingAccommodation = bookingsAccommodations.get(0);
					Accommodation accommodation = accommodationDAO.get(bookingAccommodation.getIdacc());
					Property property = propertyDAO.get(accommodation.getIdp());
					BookingPropertyInfo bookingPropertyInfo = new BookingPropertyInfo(booking, property);
					bookingsProperties.add(bookingPropertyInfo);
				} else {
					// Manejar el caso donde no hay ninguna bookingAccommodation asociada a la
					// reserva
					logger.info("No hay ninguna bookingAccommodation asociada a la reserva con ID: " + booking.getId());
				}
			}
		} else {
			logger.info("No se encontraron reservas para el usuario con ID: " + user.getId());
		}
		
		
		//muestra el tamaño de la lista de reservas
		
		logger.info("\n\n\n\n\n\n\n\n\n");
		
		logger.info("Tamaño de la lista de reservas: " + bookingsProperties.size());
		
		logger.info("\n\n\n\n\n\n\n\n\n");

		// Colocar la lista de BookingPropertyInfo en el atributo de la solicitud
		request.setAttribute("bookingsProperties", bookingsProperties);

		// Enviar la solicitud y la respuesta al JSP para mostrar los datos
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Reservas.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
