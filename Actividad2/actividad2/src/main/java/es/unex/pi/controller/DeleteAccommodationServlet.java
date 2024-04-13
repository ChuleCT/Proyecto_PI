package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Accommodation;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import java.sql.Connection;
import jakarta.servlet.RequestDispatcher;

import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.JDBCPropertyDAOImpl;

/**
 * Servlet implementation class DeleteAccommodationServlet
 */
public class DeleteAccommodationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(EditPropertyServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAccommodationServlet() {
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
		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);

		try {
			String id = request.getParameter("id");
			long oid = 0;
			oid = Long.parseLong(id);

			Accommodation accommodation = accommodationDao.get(oid);
            PropertyDAO PropertyDAO = new JDBCPropertyDAOImpl();
            PropertyDAO.setConnection(conn);
            Property property = PropertyDAO.get(accommodation.getIdp());

			if (property != null) {

				if (property.getIdu() != user.getId()) {
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/ErrorPermiso.jsp");
					view.forward(request, response);
				} else {
						request.setAttribute("accommodation", accommodation);
						RequestDispatcher view = request.getRequestDispatcher("WEB-INF/BorrarHabitacion.jsp");
						view.forward(request, response);
				}
			} else {
				response.sendRedirect("ListaAlojamientosServlet.do");
			}

		} catch (Exception e) {
			logger.info("parameter id is not a number");

			// TODO: Redirect to the ListOrderServlet.do

			response.sendRedirect("ListaAlojamientosServlet.do");
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

		String id = request.getParameter("id");
		long oid = 0;
		oid = Long.parseLong(id);

		Accommodation accommodation = accommodationDao.get(oid);

		if (accommodation != null) {

			long idp = accommodation.getIdp();
			accommodationDao.delete(oid);

			// Actualizar la disponibilidad de la propiedad

			PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
			propertyDao.setConnection(conn);
			Property property = propertyDao.get(idp);

			// Si era la última habitación de la propiedad, marcar la propiedad como no

			if (property.getAvailable() == 1) {
				List<Accommodation> accommodations = accommodationDao.getAllByProperty(property.getId());
				int numAccommodations = 0;
				for (Accommodation a : accommodations) {
					numAccommodations += a.getNumAccommodations();
				}
				if (numAccommodations == 0) {
					property.setAvailable(0);
					propertyDao.update(property);
				}
			}
		}
		response.sendRedirect("ListaAlojamientosServlet.do");
	}

}