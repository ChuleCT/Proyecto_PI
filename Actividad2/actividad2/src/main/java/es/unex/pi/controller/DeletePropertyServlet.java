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

import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;

/**
 * Servlet implementation class DeletePropertyServlet
 */
public class DeletePropertyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(DeletePropertyServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
    */
    public DeletePropertyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        PropertyDAO propertydDao = new JDBCPropertyDAOImpl();
        propertydDao.setConnection(conn);

        try {
            String id = request.getParameter("id");
            logger.info("get parameter id (" + id + ")");
            long oid = 0;
            oid = Long.parseLong(id);
            logger.info("get parameter id (" + id + ") and casting " + oid);
            Property property = propertydDao.get(oid);
            if (property != null) {
                HttpSession session = request.getSession();
                session.setAttribute("property", property);
                request.setAttribute("CheckType", "Borrar Alojamiento");
                // TODO: Dispatch the request to the Borrar.jsp

                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Borrar.jsp");
                view.forward(request, response);
            } else {
                // TODO Redirect to ListOrderServlet.do

                response.sendRedirect("ListaAlojamientosServlet.do");
            }
        } catch (Exception e) {
            logger.info("parameter id is not a number");

            // TODO: Redirect to the ListOrderServlet.do

            response.sendRedirect("ListaAlojamientosServlet.do");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        logger.info("Handling POST");
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        PropertyDAO propertydDao = new JDBCPropertyDAOImpl();
        propertydDao.setConnection(conn);

        HttpSession session = request.getSession();
        logger.info("Confirmed property to delete with session id: " + session.getId());
        Property property = (Property) session.getAttribute("property");

        if (property != null) {
            propertydDao.delete(property.getId());
            session.removeAttribute("property");
            property = null;
        }
        response.sendRedirect("ListaAlojamientosServlet.do");
    }

}
