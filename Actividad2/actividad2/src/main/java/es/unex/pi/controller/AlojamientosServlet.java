package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import es.unex.pi.model.Property;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.model.User;
import es.unex.pi.dao.UserFavoritesPropertiesDAO;
import es.unex.pi.dao.JDBCUserFavoritesPropertiesDAOImpl;
import es.unex.pi.model.UserFavoritesProperties;


import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servlet implementation class AlojamientosServlet
 */
public class AlojamientosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlojamientosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	String destino = (String) session.getAttribute("destino");

    	Connection conn = (Connection) getServletContext().getAttribute("dbConn");
    	PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
    	propertyDAO.setConnection(conn);

    	String opcionDisponibilidad = request.getParameter("opcion");
    	List<Property> ListaAlojamientos = new ArrayList<Property>();
    

    	if ((opcionDisponibilidad == null || opcionDisponibilidad.equals("Todos"))){
    		opcionDisponibilidad = "Todos";
    		ListaAlojamientos = propertyDAO.getAllBySearchDestination(destino); 
    	}
    	
    	else if (opcionDisponibilidad.equals("Sin disponibilidad")) {
    		ListaAlojamientos = propertyDAO.getAllBySearchDestinationAndNotAvailable(destino);
    	}
    	else if (opcionDisponibilidad.equals("Con disponibilidad")) {
    		logger.info("Con disponibilidad");
    		ListaAlojamientos = propertyDAO.getAllBySearchDestinationAndAvailable(destino);
    	}
    	UserFavoritesPropertiesDAO userFavoritesPropertiesDAO = new JDBCUserFavoritesPropertiesDAOImpl();
    	userFavoritesPropertiesDAO.setConnection(conn);

    	boolean seOrdena = Boolean.parseBoolean(request.getParameter("seOrdena"));
    	if (seOrdena) {
    		Collections.sort(ListaAlojamientos, Comparator.comparing(Property::getGradesAverage).reversed());
    	}

    	request.setAttribute("ListaAlojamientos", ListaAlojamientos);
    	request.setAttribute("size", ListaAlojamientos.size());
    	request.setAttribute("opcionDisponibilidad", opcionDisponibilidad);

    	User user = (User) session.getAttribute("user");
    	

    	if (user != null) {
    		Long id = user.getId();

    		// Recuperar lista de favoritos
    		List <UserFavoritesProperties> ListaFavoritos = userFavoritesPropertiesDAO.getAllByUser(id);
    		request.setAttribute("ListaFavoritos", ListaFavoritos);
    	}


    	RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Alojamientos.jsp");
    	view.forward(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String destino = request.getParameter("destino");

        HttpSession session = request.getSession();
        session.setAttribute("destino",destino);

        response.sendRedirect("AlojamientosServlet.do");
    }

}