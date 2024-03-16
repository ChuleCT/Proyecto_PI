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
import java.util.Map;
import java.util.logging.Logger;

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class RegistroServlet
 */
public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        
    	//Obtenemos la sesión y comprobamos si existe un usuario en la sesión
    	
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	
    	//Si existe un usuario en la sesión, lo eliminamos
    	
		if (user != null) {
			session.removeAttribute("user");
		}
		
		//Redirigimos a la vista de registro
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
		view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Atendiendo GET");

        request.setCharacterEncoding("UTF-8");

        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        UserDAO userDAO = new JDBCUserDAOImpl();
        userDAO.setConnection(conn);

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String surname = request.getParameter("surname");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setSurname(surname);
        
        Map<String,String> messages = new HashMap<String,String>();
        
		if (user.validate(messages)) {
			
			//se añade el usuario a la base de datos
			
			userDAO.add(user);
			
			//se obtiene el usuario recien añadido a la base de datos para cargar el id
			user = userDAO.getUserByEmail(email);
			
			//se obtiene la sesión y se añade el usuario a la misma
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			logger.info("Usuario registrado: "+user.getId());
			
			response.sendRedirect("BusquedaServlet.do");
		} else {
			request.setAttribute("messages", messages);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
			view.forward(request, response);
		}
    }
}