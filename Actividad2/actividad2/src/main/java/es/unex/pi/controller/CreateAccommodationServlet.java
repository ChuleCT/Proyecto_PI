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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import es.unex.pi.dao.JDBCPropertiesServicesDAOImpl;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertiesServicesDAO;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.PropertiesServices;
import es.unex.pi.model.Property;
import es.unex.pi.model.Service;
import es.unex.pi.model.User;

/**
 * Servlet implementation class CreateAccommodation
 */
public class CreateAccommodationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAccommodationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		try {

			String id = request.getParameter("idp");
			long idp = 0;
			idp = Long.parseLong(id);
			PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
			propertyDao.setConnection(conn);
			Property property = propertyDao.get(idp);

			if (property != null) {

				// Controlo que las habitaciones pertenecen a un alojamiento del usuario
				if (property.getIdu() != user.getId()) {
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/ErrorPermiso.jsp");
					view.forward(request, response);
				} else {
					request.setAttribute("idp", idp);
					request.setAttribute("CheckType", "Creación de Habitación");
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EdicionHabitacion.jsp");
					view.forward(request, response);
				}

			} else {
				response.sendRedirect("ManageAccommodationsServlet.do");
			}

		} catch (NumberFormatException e) {
			logger.info("parameter id is not a number");
			response.sendRedirect("ManageAccommodationsServlet.do");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);

		String idp = request.getParameter("idp");
		long oid = 0;
		oid = Long.parseLong(idp);

		Accommodation accommodation = new Accommodation();

		accommodation.setIdp(oid);
		accommodation.setName(request.getParameter("name"));
		accommodation.setPrice(Integer.parseInt(request.getParameter("price")));
		accommodation.setDescription(request.getParameter("description"));
		accommodation.setNumAccommodations(Integer.parseInt(request.getParameter("numAccommodations")));

		Map<String, String> messages = new HashMap<String, String>();
		if (accommodation.validate(messages)) {
			accommodationDao.add(accommodation);

			// Si la propiedad tiene el campo available a 0, se actualiza a 1 (si el Number
			// of Accommodations es mayor que 0)

			PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
			propertyDao.setConnection(conn);

			Property property = propertyDao.get(accommodation.getIdp());
			if (property.getAvailable() == 0 && accommodation.getNumAccommodations() > 0) {
				property.setAvailable(1);
				propertyDao.update(property);
			}

			response.sendRedirect("ListaAlojamientosServlet.do");
		} else {
//	            request.setAttribute("CheckType", "Creación");
//	            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
//	            view.forward(request, response);
			request.setAttribute("messages", messages);
			doGet(request, response);
		}
	}

}
