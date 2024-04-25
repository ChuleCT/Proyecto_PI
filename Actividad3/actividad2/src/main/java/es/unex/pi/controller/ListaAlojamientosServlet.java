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
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;

/**
 * Servlet implementation class ListaAlojamientosServlet
 */
public class ListaAlojamientosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static Logger looger = Logger.getLogger(ListaAlojamientosServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
    */
    public ListaAlojamientosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        looger.info("get parameter id (" + id + ")");

        // Se obtiene la conexión con la base de datos del ServletContext
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
        propertyDAO.setConnection(conn);
        List<Property> ListaAlojamientos = propertyDAO.getAllByUser(id);

        request.setAttribute("ListaAlojamientos", ListaAlojamientos);
        request.setAttribute("size", ListaAlojamientos.size());

        request.setAttribute("CheckType", "Mis alojamientos");
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/AlojamientosUsuario.jsp");
        view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
