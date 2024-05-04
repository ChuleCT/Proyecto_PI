package es.unex.pi.resources;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.pi.model.User;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.model.Property;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Review;
import es.unex.pi.dao.JDBCReviewDAOImpl;
import es.unex.pi.dao.ReviewDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/reviews")

public class ReviewsResource {
	
	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;
	
	// Get que te devuelve las reviews de una propiedad que no sean del propietario de esa propiedad
	@GET
	@Path("/{propertyid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Review, String> getReviewsJSON(@PathParam("propertyid") long propertyid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		
		Map<Review, String> map = new HashMap<Review, String>();

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		Property property = propertyDao.get(propertyid);
		
		List<Review> reviews = null;

		if (property != null) {
			ReviewDAO reviewDao = new JDBCReviewDAOImpl();
			reviewDao.setConnection(conn);

			reviews = reviewDao.getAllByProperty(property.getId());

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			User useraux = new User();
			for (int i = 0; i < reviews.size(); i++) {
				    if (reviews.get(i).getIdu() != user.getId()) {
				    	useraux = userDao.get(reviews.get(i).getIdu()); 
				    	map.put(reviews.get(i), useraux.getName());
				}
			}
		}

		return map;
	}
	
	// Get que obtiene la review del usuario de la sesion en una propiedad
	@GET
	@Path("/myReview/{propertyid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Review getReviewJSON(@PathParam("propertyid") long propertyid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		Property property = propertyDao.get(propertyid);

		Review review = null;

		if (property != null) {
			ReviewDAO reviewDao = new JDBCReviewDAOImpl();
			reviewDao.setConnection(conn);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			if (user != null) {
				review = reviewDao.get(property.getId(), user.getId());
			}
		}
		
		System.out.println("Mi review: "+review);

		return review;
	}
	
	// Post para añadir una review a una propiedad
	@POST
	@Path("/{propertyid:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(Review review, @PathParam("propertyid") long propertyid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		Property property = propertyDao.get(propertyid);

		if (property != null) {
			ReviewDAO reviewDao = new JDBCReviewDAOImpl();
			reviewDao.setConnection(conn);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			if (user != null) {
				review.setIdp(property.getId());
				review.setIdu(user.getId());
				reviewDao.add(review);
			}
		}
		
		String message = "Review added";

		return Response.status(Response.Status.CREATED)
				.entity("{\"status\" : \"201\", \"message\" : \"" + message + "\"}")
				.build();
	}
	
	// Put para modificar una review
	@PUT
	@Path("/{propertyid:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(Review review, @PathParam("propertyid") long propertyid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		Property property = propertyDao.get(propertyid);

		if (property != null) {
			ReviewDAO reviewDao = new JDBCReviewDAOImpl();
			reviewDao.setConnection(conn);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			if (user != null) {
				Review oldReview = reviewDao.get(property.getId(), user.getId());

				if (oldReview != null) {
					review.setIdp(property.getId());
					review.setIdu(user.getId());
					reviewDao.update(review);
				}
			}
		}

		String message = "Review updated";

		return Response.status(Response.Status.OK).entity("{\"status\" : \"200\", \"message\" : \"" + message + "\"}")
				.build();
	}
	
	// Delete para borrar una review
	@DELETE
	@Path("/{propertyid:[0-9]+}")
	public Response delete(@PathParam("propertyid") long propertyid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		Property property = propertyDao.get(propertyid);

		if (property != null) {
			ReviewDAO reviewDao = new JDBCReviewDAOImpl();
			reviewDao.setConnection(conn);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			if (user != null) {
				Review review = reviewDao.get(property.getId(), user.getId());

				if (review != null) {
					reviewDao.delete(property.getId(), user.getId());
				}
			}
		}

		String message = "Review deleted";

		return Response.status(Response.Status.OK).entity("{\"status\" : \"200\", \"message\" : \"" + message + "\"}")
				.build();
	}

}
