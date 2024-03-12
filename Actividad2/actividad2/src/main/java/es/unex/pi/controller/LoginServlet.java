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

import es.unex.pi.model.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/Login.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        UserDAO userDao = new JDBCUserDAOImpl();
        userDao.setConnection(conn);

        String email = request.getParameter("email");
        String password = request.getParameter("password");		

        logger.info("credentials: "+email+" - "+password);

        User user = userDao.getUserByEmail(email);

        if ((user != null) 
        && (user.getPassword().equals(password))){
            HttpSession session = request.getSession(); //Obtenemos la sesion
            session.setAttribute("user", user); // Establecemos el usuario en la sesión

            // Redirige al Busqueda servlet.do
            // response.sendRedirect("BusquedaServlet.do");
        } 
        else {
            request.setAttribute("messages","Wrong username or password!!");
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/Login.jsp");
            view.forward(request,response);
        }
    }

}