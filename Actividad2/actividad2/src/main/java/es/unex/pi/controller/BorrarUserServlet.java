package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;
/**
 * Servlet implementation class BorrarUserServlet
 */
public class BorrarUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());   


    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarUserServlet() {
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

        String id = request.getParameter("id");
        long oid = 0;
        oid = Long.parseLong(id); 
        User user = userDAO.get(oid);

        request.setAttribute("CheckType", "DeleteUser");

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
