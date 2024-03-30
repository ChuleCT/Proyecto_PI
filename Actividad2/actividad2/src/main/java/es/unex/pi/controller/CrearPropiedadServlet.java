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
import es.unex.pi.model.PropertiesServices;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.dao.JDBCServiceDAOImpl;
import es.unex.pi.dao.ServiceDAO;
import es.unex.pi.model.Service;
import java.util.List;
import es.unex.pi.dao.JDBCPropertiesServicesDAOImpl;
import es.unex.pi.dao.PropertiesServicesDAO;


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
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        HttpSession session = request.getSession();
        Property alojamiento = (Property) session.getAttribute("property");
        ServiceDAO serviceDAO = new JDBCServiceDAOImpl();
        serviceDAO.setConnection(conn);

        if (alojamiento != null) {
            session.removeAttribute("property");
        }

        request.setAttribute("CheckType", "Creación");
        List<Service> services = serviceDAO.getAll();
        request.setAttribute("services", services);
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
        ServiceDAO serviceDAO = new JDBCServiceDAOImpl();
        serviceDAO.setConnection(conn);

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
        property.setPetFriendly(Integer.parseInt(request.getParameter("petFriendly")));


        Map<String, String> messages = new HashMap<String, String>();
        if (property.validate(messages)){

            propertyDAO.add(property);

            List <Property> properties = propertyDAO.getAll();
            Property p = properties.get(properties.size()-1);

            String [] checkedServices = request.getParameterValues("listServices");
            for (String service : checkedServices) {
                Service s = serviceDAO.get(service);
                PropertiesServices ps = new PropertiesServices();
                ps.setIdp(p.getId());
                ps.setIds(s.getId());
                PropertiesServicesDAO psDAO = new JDBCPropertiesServicesDAOImpl();
                psDAO.setConnection(conn);
                if (psDAO.add(ps)){
                    logger.info("\n\n\n\nAñadido servicio "+s.getName()+" al alojamiento "+p.getName());
                }
                else{
                    logger.info("\n\n\n\nError al añadir servicio "+s.getName()+" al alojamiento "+p.getName());
                }
            }
            request.getSession().removeAttribute("property");
            response.sendRedirect("ListaAlojamientosServlet.do");
        }
        else {
            request.setAttribute("CheckType", "Creación");
            request.setAttribute("messages", messages);
//            RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Edicion.jsp");
//            view.forward(request, response);
            doGet(request, response);
        }

    }

}
