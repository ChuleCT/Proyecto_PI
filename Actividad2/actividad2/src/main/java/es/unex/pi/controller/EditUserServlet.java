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
 * Servlet implementation class EditUserServlet
 */
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se obtiene la conexión con la base de datos del ServletContext

        Connection conn = (Connection) getServletContext().getAttribute("dbConn");

        //Se crea un objeto UserDAOImpl y se le asigna la conexión a la base de datos

        UserDAO userDAO = new JDBCUserDAOImpl();
        userDAO.setConnection(conn);

        //Se obtiene el usuario de la sesión
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        
        //Se redirige a la página de edición de usuario
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
        view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        //se establece la conexión con la base de datos
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        UserDAO userDAO = new JDBCUserDAOImpl();
        userDAO.setConnection(conn);

        User user = new User();

        logger.info("The actual name is: " + request.getParameter("name"));

        user.setId(Long.parseLong(request.getParameter("id")));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        //se validan los datos del usuario
        Map<String, String> messages = new HashMap<String, String>();
        if (user.validate(messages)) {

            // se actualiza el usuario en la base de datos

            userDAO.update(user);

            logger.info("The updated name is: "+user.getName());

            //eliminamos el atributo user de la sesión
            request.getSession().removeAttribute("user");

            //se vuelven a cargar los datos del usuario

            request.getSession().setAttribute("user", user);

            response.sendRedirect("BusquedaServlet.do");
        } else {
            request.setAttribute("messages", messages);
            request.setAttribute("user", user);
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
            view.forward(request, response);
        }
    }

}
