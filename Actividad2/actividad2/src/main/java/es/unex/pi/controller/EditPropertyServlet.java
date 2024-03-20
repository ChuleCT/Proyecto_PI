package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;

import es.unex.pi.dao.JDBCServiceDAOImpl;
import es.unex.pi.dao.ServiceDAO;
import es.unex.pi.model.Service;

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
		PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
		propertyDAO.setConnection(conn);
		ServiceDAO serviceDAO = new JDBCServiceDAOImpl();
		serviceDAO.setConnection(conn);
		
		List<Service> services = serviceDAO.getAll();
		request.setAttribute("services", services);

		try {
			String id = request.getParameter("id");
			logger.info("get parameter id (" + id + ")");
			long oid = 0;
			oid = Long.parseLong(id);
			Property property = propertyDAO.get(oid);
			if (property != null) {
				request.setAttribute("property", property);
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
				view.forward(request, response);
			} else {
				response.sendRedirect("ListaAlojamientosServlet.do");
			}
		} catch (NumberFormatException e) {
			logger.info("parameter id is not a number");
			response.sendRedirect("ListaAlojamientosServlet.do");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
		propertyDAO.setConnection(conn);
		
		Property property = new Property();
		
		logger.info("The actual name of the property is: "+request.getParameter("name"));
		
		property.setId(Long.parseLong(request.getParameter("id")));
		property.setIdu(Long.parseLong(request.getParameter("idu")));
		property.setCity(request.getParameter("city"));
		property.setName(request.getParameter("name"));
		property.setAddress(request.getParameter("address"));
		property.setTelephone(request.getParameter("telephone"));
		property.setCenterDistance(Double.parseDouble(request.getParameter("centerDistance")));
		property.setGradesAverage(Double.parseDouble(request.getParameter("gradesAverage")));
		property.setDescription(request.getParameter("description"));
		
		Map<String, String> messages = new HashMap<String, String>();
		if (property.validate(messages)){
			
			propertyDAO.update(property);
			
			logger.info("The updated name of the property is: "+property.getName());
			
			request.getSession().removeAttribute("property");
			request.getSession().setAttribute("property", property);
			
			response.sendRedirect("ListaAlojamientosServlet.do");
		}else{
			request.setAttribute("messages", messages);
			request.setAttribute("property", property);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
			view.forward(request, response);
		}
	}

}
