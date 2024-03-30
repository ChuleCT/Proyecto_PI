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
import es.unex.pi.dao.PropertiesServicesDAO;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.PropertiesServices;
import es.unex.pi.model.Property;
import es.unex.pi.model.Service;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Connection conn = (Connection) getServletContext().getAttribute("dbConn");
    	String idp = request.getParameter("idp");
    	long oid = 0;
    	oid = Long.parseLong(idp);

    	request.setAttribute("idp", oid);
    	request.setAttribute("CheckType", "Creación de Habitación");
    	RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EdicionHabitacion.jsp");
    	view.forward(request, response);
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	        if (accommodation.validate(messages)){
	            accommodationDao.add(accommodation);

	            response.sendRedirect("ListaAlojamientosServlet.do");
	        }
	        else {
//	            request.setAttribute("CheckType", "Creación");
//	            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
//	            view.forward(request, response);
	        	request.setAttribute("messages", messages);
	        	doGet(request, response);
	        }
	}

}
