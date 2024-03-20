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

import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;

/**
 * Servlet implementation class CrearPropiedadServlet
 */
public class CrearPropiedadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearPropiedadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String alojamiento = (String) session.getAttribute("property");

        if (alojamiento != null) {
            session.removeAttribute("property");
        }

        request.setAttribute("CheckType", "Creación");
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
        view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
        propertyDAO.setConnection(conn);

        Property property = new Property();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        property.setIdu(user.getId());
        property.setCity(request.getParameter("city"));
        property.setName(request.getParameter("name"));
        property.setAddress(request.getParameter("address"));
        property.setTelephone(request.getParameter("telephone"));
        property.setCenterDistance(Double.parseDouble(request.getParameter("centerDistance")));
        property.setGradesAverage(Double.parseDouble(request.getParameter("gradesAverage")));
        property.setDescription(request.getParameter("description"));

        Map<String, String> messages = new HashMap<String, String>();
        if (property.validate(messages)){
            propertyDAO.add(property);

            request.getSession().removeAttribute("property");
            response.sendRedirect("ListaAlojamientosServlet.do");
        }
        else {
            request.setAttribute("CheckType", "Creación");
            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
            view.forward(request, response);
        }

    }

}
