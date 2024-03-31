package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import es.unex.pi.dao.JDBCReviewDAOImpl;
import es.unex.pi.dao.ReviewDAO;
import es.unex.pi.model.Review;
import es.unex.pi.model.User;

/**
 * Servlet implementation class ReviewServlet
 */
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EditPropertyServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		long idu = u.getId();
		ReviewDAO reviewDAO = new JDBCReviewDAOImpl();
		reviewDAO.setConnection(conn);
		
		// Obtengo el id de la propiedad que se ha valorado
		long idp = Long.parseLong(request.getParameter("idPropertyReviewed"));
		
		boolean seBorra = Boolean.parseBoolean(request.getParameter("seBorra"));
		if (seBorra) {
			reviewDAO.delete(idp, idu);
		}
		else {
			// Obtengo el comentario
			String review = request.getParameter("comment");
			// Obtengo la puntuación
			int grade = Integer.parseInt(request.getParameter("grade"));
			
			Review r = new Review();
			r.setIdp(idp);
			r.setIdu(idu);
			r.setReview(review);
			r.setGrade(grade);

			// Compruebo si el usuario ya ha valorado la propiedad
			Review reviewExistente = reviewDAO.get(idp, idu);
			if (reviewExistente != null) {
				reviewDAO.update(r);
			}
			else {
				reviewDAO.add(r);
			}
		}

		
		
		// Redirijo a la página de la propiedad
		response.sendRedirect("PropertyDetailsServlet.do?id=" + idp);
	}

}
