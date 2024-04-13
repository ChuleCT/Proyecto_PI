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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import es.unex.pi.dao.JDBCPropertiesServicesDAOImpl;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Accommodation;

import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertiesServicesDAO;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.dao.JDBCBookingDAOImpl;
import es.unex.pi.dao.BookingDAO;
import es.unex.pi.model.Booking;
import es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl;
import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.model.BookingsAccommodations;

/**
 * Servlet implementation class ShoppingCartServlet
 */
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(EditPropertyServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");

		HttpSession session = request.getSession();
		Map<Accommodation, Integer> shoppingCart = (Map<Accommodation, Integer>) session
				.getAttribute("accommodationQuantityMap");

		if (!shoppingCart.isEmpty()) {
			// Cojo la primera accommodation para ver la property

			Accommodation accommodation = shoppingCart.keySet().iterator().next();

			PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
			propertyDAO.setConnection(conn);
			Property property = propertyDAO.get(accommodation.getIdp());

			request.setAttribute("property", property);

			// Servicios de la property

			PropertiesServicesDAO propertiesServicesDAO = new JDBCPropertiesServicesDAOImpl();
			propertiesServicesDAO.setConnection(conn);

			List<String> services = propertiesServicesDAO.getCheckedServices(property.getId());
			request.setAttribute("services", services);

			// Parte de "Has seleccionado" (me quedo solo con los accommodations con
			// cantidad > 0)

			Map<Accommodation, Integer> selectedAccommodations = new HashMap<Accommodation, Integer>();

			for (Map.Entry<Accommodation, Integer> entry : shoppingCart.entrySet()) {
				Accommodation accommodation2 = entry.getKey();
				int quantity = entry.getValue();
				selectedAccommodations.put(accommodation2, quantity);
			}

			request.setAttribute("selectedAccommodations", selectedAccommodations);

			// Calculo el precio total

			int totalPrice = 0;
			for (Map.Entry<Accommodation, Integer> entry : selectedAccommodations.entrySet()) {
				Accommodation accommodation2 = entry.getKey();
				int quantity = entry.getValue();
				totalPrice += accommodation2.getPrice() * quantity;
			}

			request.setAttribute("totalPrice", totalPrice);
		}

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Carrito.jsp");
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Verifica si se debe eliminar una habitación de la lista de reserva
		boolean eliminarHabitacion = Boolean.parseBoolean(request.getParameter("eliminarHabitacion"));
		if (eliminarHabitacion) {
			HttpSession session = request.getSession();
			Map<Accommodation, Integer> shoppingCart = (Map<Accommodation, Integer>) session
					.getAttribute("accommodationQuantityMap");

			session.removeAttribute("accommodationQuantityMap");

			String accommodationIdToDelete = request.getParameter("accommodationId");

			// Elimina la habitación de la lista de reserva
			for (Iterator<Map.Entry<Accommodation, Integer>> iterator = shoppingCart.entrySet().iterator(); iterator
					.hasNext();) {
				Map.Entry<Accommodation, Integer> entry = iterator.next();
				Accommodation accommodation = entry.getKey();
				if (String.valueOf(accommodation.getId()).equals(accommodationIdToDelete)) {
					iterator.remove();
				}
			}

			session.setAttribute("accommodationQuantityMap", shoppingCart);
			response.sendRedirect("ShoppingCartServlet.do");
		} else {

			Connection conn = (Connection) getServletContext().getAttribute("dbConn");
			BookingDAO bookingDAO = new JDBCBookingDAOImpl();
			bookingDAO.setConnection(conn);

			// Recupero el usuario de la sesion y el precio para añadir la reserva a la base
			// de datos
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			logger.info("User " + user.getId() + " is checking the shopping cart");
			String price = request.getParameter("totalPrice");
			int totalPrice = Integer.parseInt(price);

			Booking booking = new Booking();
			booking.setIdu(user.getId());
			booking.setTotalPrice(totalPrice);

			bookingDAO.add(booking);

			// Se añade la reserva a la tabla bookingsAccommodations

			booking = bookingDAO.getLatestBooking(user.getId(), totalPrice);
			BookingsAccommodationsDAO bookingsAccommodationsDAO = new JDBCBookingsAccommodationsDAOImpl();
			bookingsAccommodationsDAO.setConnection(conn);

			BookingsAccommodations bookingAccommodation = new BookingsAccommodations();
			bookingAccommodation.setIdb(booking.getId());

			AccommodationDAO accommodationDAO = new JDBCAccommodationDAOImpl();
			accommodationDAO.setConnection(conn);

			Map<Accommodation, Integer> shoppingCart = (Map<Accommodation, Integer>) session
					.getAttribute("accommodationQuantityMap");
			
			// Cojo la primera accommodation para ver la property
			PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
			propertyDAO.setConnection(conn);
			Property property = propertyDAO.get(shoppingCart.keySet().iterator().next().getIdp());

			for (Map.Entry<Accommodation, Integer> entry : shoppingCart.entrySet()) {
				Accommodation accommodation = entry.getKey();
				int quantity = entry.getValue();
				if (quantity > 0) {
					bookingAccommodation.setIdacc(accommodation.getId());
					bookingAccommodation.setNumAccommodations(quantity);
					bookingsAccommodationsDAO.add(bookingAccommodation);

					// Resto la cantidad de alojamientos reservados a la cantidad disponible
					accommodation.setNumAccommodations(accommodation.getNumAccommodations() - quantity);
					accommodationDAO.update(accommodation);
				}
			}

			// Si reduzco el número de alojamientos a 0, pongo available a 0 si no hay ninguna otra habitación con alojamientos

			if (property.getAvailable() == 1) {
				List<Accommodation> accommodations = accommodationDAO.getAllByProperty(property.getId());
				int numAccommodations = 0;
				for (Accommodation a : accommodations) {
					numAccommodations += a.getNumAccommodations();
				}
				if (numAccommodations == 0) {
					property.setAvailable(0);
					propertyDAO.update(property);
				}
			}

			session.removeAttribute("accommodationQuantityMap");
			// Se crea el mapa para que no de error al entrar al carrito sin haber añadido
			// alojamientos
			Map<Accommodation, Integer> accommodationQuantityMap = new HashMap<>();
			session.setAttribute("accommodationQuantityMap", accommodationQuantityMap);

			response.sendRedirect("BookingsServlet.do");
		}
	}

}
