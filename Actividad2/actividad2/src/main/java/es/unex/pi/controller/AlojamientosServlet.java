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
import java.io.IOException;
import java.sql.Connection;
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
        List<Property> ListaAlojamientos = propertyDAO.getAllBySearchDestination(destino); 

        request.setAttribute("ListaAlojamientos", ListaAlojamientos);
        request.setAttribute("size", ListaAlojamientos.size());

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
