package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import es.unex.pi.dao.PropertyDAO;

/**
 * Servlet implementation class ListaAlojamientosServlet
 */
public class ListaAlojamientosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaAlojamientosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        long oid = 0;
        oid = Long.parseLong(id); 

        //Se obtiene la conexión con la base de datos del ServletContext
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        PropertyDAO propertyDAO = new JDBCPropertyDAOImpl();
        propertyDAO.setConnection(conn);
        List<Property> ListaAlojamientos = propertyDAO.getAllByUser(oid); 

        request.setAttribute("ListaAlojamientos", ListaAlojamientos);
        request.setAttribute("size", ListaAlojamientos.size());

        request.setAttribute("CheckType", "Mis alojamientos");
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Reserva.jsp");
        view.forward(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
