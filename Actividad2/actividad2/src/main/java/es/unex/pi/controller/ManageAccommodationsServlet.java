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

import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.User;

import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;

import java.util.List;
import java.util.logging.Logger;

/**
 * Servlet implementation class ManageAcommodationsServlet
 */
public class ManageAccommodationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(ManageAccommodationsServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageAccommodationsServlet() {
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

//        String id = request.getParameter("id");
//        long idp = 0;
//        idp = Long.parseLong(id);
//
//        AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
//        accommodationDao.setConnection(conn);
//        List<Accommodation> accommodationsList = accommodationDao.getAllByProperty(idp);
//
//        request.setAttribute("accommodationsList", accommodationsList);
//        request.setAttribute("idp", idp);
//        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/GestionarHabitaciones.jsp");
//        rd.forward(request, response);

		// Meto todo el código en un try-catch para controlar posibles errores
		try {

			String id = request.getParameter("id");
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
					// Se obtiene la conexión con la base de datos del ServletContext
					AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
					accommodationDao.setConnection(conn);
					List<Accommodation> accommodationsList = accommodationDao.getAllByProperty(idp);

					request.setAttribute("accommodationsList", accommodationsList);
					request.setAttribute("idp", idp);
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/GestionarHabitaciones.jsp");
					rd.forward(request, response);
				}

			} else {
				response.sendRedirect("ListaAlojamientosServlet.do");
			}

		} catch (NumberFormatException e) {
			logger.info("parameter id is not a number");
			response.sendRedirect("ListaAlojamientosServlet.do");
		}
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