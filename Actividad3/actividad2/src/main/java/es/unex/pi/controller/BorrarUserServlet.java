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
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        UserDAO userDAO = new JDBCUserDAOImpl();
        userDAO.setConnection(conn);

//        try{
//            String id = request.getParameter("id");
//            long oid = 0;
//            oid = Long.parseLong(id); 
//            User user = userDAO.get(oid);
//            if (user != null){
//                HttpSession session = request.getSession();
//                session.setAttribute("user",user);
//                request.setAttribute("CheckType", "Borrar Usuario");
//
//                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Borrar.jsp");
//                view.forward(request,response);
//            }
//            else { 
//
//                response.sendRedirect("RegistroServlet.do");
//            }	
//        }
//        catch (Exception e) {
//            logger.info("parameter id is not a number");
//
//
//            response.sendRedirect("RegistroServlet.do");
//        }
        
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		request.setAttribute("CheckType", "Borrar Usuario");

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Borrar.jsp");
		view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Handling POST");
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        UserDAO userDAO = new JDBCUserDAOImpl();
        userDAO.setConnection(conn);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user!=null) {

            userDAO.delete(user.getId());

            session.removeAttribute("user");
            user=null;
        }


        response.sendRedirect("LoginServlet.do");
    }

}
