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

import es.unex.pi.dao.JDBCPropertiesServicesDAOImpl;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertiesServicesDAO;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;
import es.unex.pi.model.Service;

import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Accommodation;

/**
 * Servlet implementation class PropertyDetailsServlet
 */
public class PropertyDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(EditPropertyServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropertyDetailsServlet() {
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
		PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
		propertyDAO.setConnection(conn);
		try {
			String id = request.getParameter("id");
			logger.info("get parameter id (" + id + ")");
			long oid = 0;
			oid = Long.parseLong(id);
			Property property = propertyDAO.get(oid);
			if (property != null) {

				// Obtengo todos los servivicios de la propiedad

				PropertiesServicesDAO propertiesServicesDAO = new JDBCPropertiesServicesDAOImpl();
				propertiesServicesDAO.setConnection(conn);

				List<String> services = propertiesServicesDAO.getCheckedServices(oid);
				request.setAttribute("services", services);

				// Obtengo las habitaciones de la propiedad

				AccommodationDAO accommodationDAO = new JDBCAccommodationDAOImpl();
				accommodationDAO.setConnection(conn);

				List<Accommodation> accommodations = accommodationDAO.getAllByProperty(oid);
				request.setAttribute("accommodations", accommodations);

				request.setAttribute("property", property);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/DetalleAlojamiento.jsp");
				view.forward(request, response);
			} else {
				response.sendRedirect("AlojamientosServlet.do");
			}
		} catch (NumberFormatException e) {
			logger.info("parameter id is not a number");
			response.sendRedirect("AlojamientosServlet.do");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		AccommodationDAO accommodationDAO = new JDBCAccommodationDAOImpl();
		accommodationDAO.setConnection(conn);

		PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
		propertyDAO.setConnection(conn);
		
		Property property = propertyDAO.get(Long.parseLong(request.getParameter("id")));
		request.setAttribute("property", property);
		
		List<Accommodation> accommodations2 = accommodationDAO.getAllByProperty(property.getId());
		
		Map<Accommodation, Integer> accommodationQuantityMap = new HashMap<>();

		// Recorre la lista de alojamientos de la propiedad y recupera la cantidad seleccionada de cada uno
		
		for (int i = 0; i < accommodations2.size(); i++) {
			String cantidadParam = request.getParameter("cantidad" + i); 
			int cantidad = Integer.parseInt(cantidadParam);
			Accommodation accommodation = accommodations2.get(i);

			// Asocia el alojamiento con su cantidad en el mapa
			accommodationQuantityMap.put(accommodation, cantidad);
		}
		
		logger.info("\n\n\n\n\n\n\n\n\n\n\n");
		for (Map.Entry<Accommodation, Integer> entry : accommodationQuantityMap.entrySet()) {
			Accommodation accommodation = entry.getKey();
			int cantidad = entry.getValue();
			logger.info("Alojamiento: " + accommodation.getId() + "Nombre:" + accommodation.getName() + " Cantidad: " + cantidad);
		}
		logger.info("\n\n\n\n\n\n\n");
		
		HttpSession session = request.getSession();
		session.setAttribute("accommodationQuantityMap", accommodationQuantityMap);

		response.sendRedirect("ShoppingCartServlet.do");
	}

}
