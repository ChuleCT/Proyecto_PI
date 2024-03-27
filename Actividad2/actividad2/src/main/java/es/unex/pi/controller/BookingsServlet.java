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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());

        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        BookingDAO bookingDAO = new JDBCBookingDAOImpl();
        bookingDAO.setConnection(conn);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //Obtenemos todas las reservas del usuario
        List<Booking> bookings = bookingDAO.getAllByIdu(user.getId());

        BookingsAccommodationsDAO bookingsAccommodationsDAO = new JDBCBookingsAccommodationsDAOImpl();
        bookingsAccommodationsDAO.setConnection(conn);

        //Obtenemos las habitaciones reservadas
        List<BookingsAccommodations> bookingsAccommodations = new ArrayList<BookingsAccommodations>();
        for (Booking booking : bookings) {
            bookingsAccommodations.addAll(bookingsAccommodationsDAO.getAllByBooking(booking.getId()));
        }		

        //Se obtienen las propiedades a las que pertenecen las habitaciones reservadas
        List<Accommodation> accommodations = new ArrayList<Accommodation>();
        AccommodationDAO accommodationDAO = new JDBCAccommodationDAOImpl();
        accommodationDAO.setConnection(conn);

        for (BookingsAccommodations ba : bookingsAccommodations) {
            accommodations.add(accommodationDAO.get(ba.getIdacc()));
        }

        //Se obtienen las propiedades a las que pertenecen las habitaciones reservadas
        Set<Property> properties = new HashSet<Property>();
        PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
        propertyDAO.setConnection(conn);

        for (Accommodation a : accommodations) {
            properties.add(propertyDAO.get(a.getIdp()));
        }

        //Se asocia en un mapa cada reserva con la propiedad a la que pertenece
        Map<Booking, Property> bookingsProperties = new HashMap<Booking, Property>();
        for (Booking b : bookings) {
            for (BookingsAccommodations ba : bookingsAccommodations) {
            if (b.getId() == ba.getIdb()) {
                    for (Accommodation a : accommodations) {
                        if (ba.getIdacc() == a.getId()) {
                            for (Property p : properties) {
                                if (a.getIdp() == p.getId()) {
                                bookingsProperties.put(b, p);
                                }
                            }
                        }
                    }
                }
            }
        }

        logger.info("\n\n\n\n\n\n\nEl tamaño de bookingsProperties es: " + bookingsProperties.size());
        for (Map.Entry<Booking, Property> entry : bookingsProperties.entrySet()) {
            logger.info(
                "La reserva " + entry.getKey().getId() + " pertenece a la propiedad " + entry.getValue().getName());
        }
        logger.info("\n\n\n\n\n\n\n");

        request.setAttribute("bookingsProperties", bookingsProperties);

        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Reservas.jsp");
        view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
