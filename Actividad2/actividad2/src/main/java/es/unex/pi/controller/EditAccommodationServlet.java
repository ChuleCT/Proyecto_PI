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

import es.unex.pi.model.Accommodation;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.dao.JDBCAccommodationDAOImpl;

import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.JDBCPropertyDAOImpl;


/**
 * Servlet implementation class EditAccommodationServlet
 */
public class EditAccommodationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(EditPropertyServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccommodationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
        accommodationDao.setConnection(conn);

        try {
            
            String id = request.getParameter("id");
            long oid = 0;
            oid = Long.parseLong(id);
            logger.info("El id de la habitación es: " +  oid);
            
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
		                request.setAttribute("CheckType", "Edición de Habitación");
		                request.setAttribute("idp", accommodation.getIdp());
		                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EdicionHabitacion.jsp");
		                view.forward(request, response);
				}
			} else {
				response.sendRedirect("ManageAccommodationsServlet.do");
			}
        } catch (Exception e) {
            logger.info("Parameter id is not a number");
            response.sendRedirect("ManageAccommodationsServlet.do");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
        accommodationDao.setConnection(conn);

        Accommodation accommodation = new Accommodation();

        String id = request.getParameter("id");
        long oid = 0;
        oid = Long.parseLong(id);
        accommodation.setId(oid);
        accommodation.setName(request.getParameter("name"));
        accommodation.setPrice(Integer.parseInt(request.getParameter("price")));
        accommodation.setDescription(request.getParameter("description"));
        accommodation.setIdp(Long.parseLong(request.getParameter("idp")));
        accommodation.setNumAccommodations(Integer.parseInt(request.getParameter("numAccommodations")));
        

        Map<String, String> messages = new HashMap<String, String>();
        if (accommodation.validate(messages)){
            accommodationDao.update(accommodation);
            logger.info("The updated name of the accommodation is: "+accommodation.getName());
            
         // Distintos casos en los que hay que poner available a 1
            // Si el número de alojamientos es mayor que 0
            
            PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
            propertyDao.setConnection(conn);
            
            Property property = propertyDao.get(accommodation.getIdp());
    		if (property.getAvailable() == 0 && accommodation.getNumAccommodations() > 0) {
    			property.setAvailable(1);
    			propertyDao.update(property);
    		}
    		
    		// Si reduzco el número de alojamientos a 0, pongo available a 0 si no hay ninguna otra habitación con alojamientos
    		
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

            response.sendRedirect("ListaAlojamientosServlet.do");
        }else{
//            request.setAttribute("accommodation", accommodation);
//            request.setAttribute("CheckType", "Edición de ");
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EdicionHabitacion.jsp");
//            view.forward(request, response);
        	request.setAttribute("messages", messages);
        	doGet(request, response);
        }
    }

}