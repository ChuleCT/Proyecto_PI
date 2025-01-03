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
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.Arrays;

import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;

import es.unex.pi.dao.JDBCServiceDAOImpl;
import es.unex.pi.dao.ServiceDAO;
import es.unex.pi.model.Service;
import es.unex.pi.model.User;
import es.unex.pi.dao.JDBCPropertiesServicesDAOImpl;
import es.unex.pi.dao.PropertiesServicesDAO;
import es.unex.pi.model.PropertiesServices;

/**
 * Servlet implementation class EditPropertyServlet
 */
public class EditPropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(EditPropertyServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditPropertyServlet() {
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
		PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
		propertyDAO.setConnection(conn);
		ServiceDAO serviceDAO = new JDBCServiceDAOImpl();
		serviceDAO.setConnection(conn);

		List<Service> services = serviceDAO.getAll();
		request.setAttribute("services", services);

		PropertiesServicesDAO propertiesServicesDAO = new JDBCPropertiesServicesDAOImpl();
		propertiesServicesDAO.setConnection(conn);
		List<String> checkedServices = new ArrayList<String>();
		checkedServices = propertiesServicesDAO.getCheckedServices(Long.parseLong(request.getParameter("id")));
		request.setAttribute("checkedServices", checkedServices);

		try {
			String id = request.getParameter("id");
			logger.info("get parameter id (" + id + ")");
			long oid = 0;
			oid = Long.parseLong(id);
			Property property = propertyDAO.get(oid);
			if (property != null) {

				// Controlo que yo sea el propietario del alojamiento
				if (property.getIdu() != user.getId()) {
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/ErrorPermiso.jsp");
					view.forward(request, response);
				} else {
					request.setAttribute("property", property);
					request.setAttribute("CheckType", "Edición");
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
					view.forward(request, response);
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

		request.setCharacterEncoding("UTF-8");

		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
		propertyDAO.setConnection(conn);
		ServiceDAO serviceDAO = new JDBCServiceDAOImpl();
		serviceDAO.setConnection(conn);
		PropertiesServicesDAO propertiesServicesDAO = new JDBCPropertiesServicesDAOImpl();
		propertiesServicesDAO.setConnection(conn);

		Property property = new Property();

		logger.info("The actual name of the property is: " + request.getParameter("name"));

		property = propertyDAO.get(Long.parseLong(request.getParameter("id")));
		double gradesAverage = property.getGradesAverage();

		property.setId(Long.parseLong(request.getParameter("id")));
		property.setIdu(Long.parseLong(request.getParameter("idu")));
		property.setCity(request.getParameter("city"));
		property.setGradesAverage(gradesAverage);
		property.setName(request.getParameter("name"));
		property.setAddress(request.getParameter("address"));
		property.setTelephone(request.getParameter("telephone"));
		property.setCenterDistance(Double.parseDouble(request.getParameter("centerDistance")));
		property.setDescription(request.getParameter("description"));
		property.setPetFriendly(Integer.parseInt(request.getParameter("petFriendly")));

		List<String> oldCheckedServices = new ArrayList<String>();
		oldCheckedServices = propertiesServicesDAO.getCheckedServices(Long.parseLong(request.getParameter("id")));// Vieja
																													// lista
																													// de
																													// servicios
																													// seleccionados

		String[] checkedServices = request.getParameterValues("listServices");// Nueva lista de servicios seleccionados

		if (checkedServices == null) {
			checkedServices = new String[0];
		}else {
			// ACTUALIZA LOS SERVICIOS SELECCIONADOS
			for (String service : checkedServices) {
				if (!oldCheckedServices.contains(service)) {
					// Si el servicio no estaba seleccionado antes, se añade
					Service s = serviceDAO.get(service);
					PropertiesServices ps = new PropertiesServices();
					ps.setIds(s.getId());
					ps.setIdp(property.getId());
					propertiesServicesDAO.add(ps);
				}
			}
			for (String service : oldCheckedServices) {
				if (!Arrays.asList(checkedServices).contains(service)) {
					// Si el servicio estaba seleccionado antes y ya no lo está, se elimina
					Service s = serviceDAO.get(service);
					PropertiesServices ps = propertiesServicesDAO.get(property.getId(), s.getId());
					propertiesServicesDAO.delete(ps.getIdp(), ps.getIds());
				}
			}
		}

		Map<String, String> messages = new HashMap<String, String>();
		if (property.validate(messages)) {

			propertyDAO.update(property);

			logger.info("The updated name of the property is: " + property.getName());

			request.getSession().removeAttribute("property");
			request.getSession().setAttribute("property", property);

			response.sendRedirect("ListaAlojamientosServlet.do");
		} else {
			request.setAttribute("messages", messages);
			request.setAttribute("CheckType", "Edición");
//            request.setAttribute("property", property);
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
//            view.forward(request, response);
			doGet(request, response);
		}
	}

}
