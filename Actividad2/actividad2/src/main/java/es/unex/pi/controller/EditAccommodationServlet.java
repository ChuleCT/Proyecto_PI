package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import es.unex.pi.model.Accommodation;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.dao.JDBCAccommodationDAOImpl;



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
        AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
        accommodationDao.setConnection(conn);

        try {
            // long id = Long.parseLong(request.getParameter("id"));
            String id = request.getParameter("id");
            long oid = 0;
            oid = Long.parseLong(id);
            logger.info("El id de la habitación es: " +  oid);
            Accommodation accommodation = accommodationDao.get(oid);

            if (accommodation != null) {
                request.setAttribute("accommodation", accommodation);
                RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EdicionHabitacion.jsp");
                view.forward(request, response);
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

            response.sendRedirect("ListaAlojamientosServlet.do");
        }else{
            request.setAttribute("accommodation", accommodation);
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EdicionHabitacion.jsp");
            view.forward(request, response);
        }
    }

}
