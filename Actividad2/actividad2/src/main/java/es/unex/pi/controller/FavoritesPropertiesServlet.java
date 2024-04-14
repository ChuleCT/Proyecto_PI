package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import es.unex.pi.model.User;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.model.Property;
import es.unex.pi.dao.UserFavoritesPropertiesDAO;
import es.unex.pi.dao.JDBCUserFavoritesPropertiesDAOImpl;
import es.unex.pi.model.UserFavoritesProperties;


/**
 * Servlet implementation class FavoritesPropertiesServlet
 */
public class FavoritesPropertiesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(ListaAlojamientosServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoritesPropertiesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long id = user.getId();

        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        UserFavoritesPropertiesDAO userFavoritesPropertiesDAO = new JDBCUserFavoritesPropertiesDAOImpl();
        userFavoritesPropertiesDAO.setConnection(conn);
        List <UserFavoritesProperties> ListaFavoritos = userFavoritesPropertiesDAO.getAllByUser(id);
        List <Property> ListaAlojamientos = new ArrayList<Property>();

        PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
        propertyDAO.setConnection(conn);
        for (UserFavoritesProperties iteration : ListaFavoritos) {
            Property property = propertyDAO.get(iteration.getIdp());
            ListaAlojamientos.add(property);
        }

        request.setAttribute("ListaAlojamientos", ListaAlojamientos);
        request.setAttribute("size", ListaAlojamientos.size());

        request.setAttribute("CheckType", "Mis alojamientos favoritos");
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/AlojamientosUsuario.jsp");
        view.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        UserFavoritesPropertiesDAO userFavoritesPropertiesDAO = new JDBCUserFavoritesPropertiesDAOImpl();
        userFavoritesPropertiesDAO.setConnection(conn);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long id = user.getId();

        String alojamientoID = request.getParameter("propertieId");
        long oid = Long.parseLong(alojamientoID);

        String fav = request.getParameter("favorito" + alojamientoID);

        try {
            if (request.getParameter("desdeAlojamiento").equals("si")){
                if (fav != null && fav.equals("on")) {
                    UserFavoritesProperties userFavoritesProperties = new UserFavoritesProperties();
                    userFavoritesProperties.setIdu(user.getId());
                    userFavoritesProperties.setIdp(oid);
                    userFavoritesPropertiesDAO.add(userFavoritesProperties);
                    System.out.println("Agregado a favoritos.");
                }
                else {
                    userFavoritesPropertiesDAO.delete(user.getId(), oid);
                    System.out.println("Eliminado de favoritos.");
                }
                String opcionDisponibilidad = request.getParameter("opcionParaFavoritos");

                Boolean seOrdena = Boolean.parseBoolean(request.getParameter("seOrdena"));

                response.sendRedirect("AlojamientosServlet.do?opcion=" + opcionDisponibilidad + "&seOrdena=" + seOrdena);

            }
            else{
                userFavoritesPropertiesDAO.delete(user.getId(), oid);
                System.out.println("Eliminado de favoritos.");
                doGet(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
