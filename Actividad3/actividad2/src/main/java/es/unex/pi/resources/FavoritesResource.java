package es.unex.pi.resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.model.UserFavoritesProperties;
import es.unex.pi.dao.JDBCUserFavoritesPropertiesDAOImpl;
import es.unex.pi.dao.UserFavoritesPropertiesDAO;

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

@Path("/favorites")
public class FavoritesResource {
	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;

	// Get para todas las propiedades favoritas de un usuario
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<UserFavoritesProperties> getFavoritesJSON(@Context HttpServletRequest request) {
//		List <UserFavoritesProperties> favorites = null;
//		Connection conn = (Connection) sc.getAttribute("dbConn");
//		
//		UserFavoritesPropertiesDAO favoritesDao = new JDBCUserFavoritesPropertiesDAOImpl();
//		favoritesDao.setConnection(conn);
//		
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");
//		
//		System.out.println("El id del usuario es: " + user.getId());
//		favorites = favoritesDao.getAllByUser(user.getId());
//		
//		return favorites;
//		
//	}
	
	@SuppressWarnings("null")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Long> getFavoritesIdsJSON(@Context HttpServletRequest request) {
		List<UserFavoritesProperties> favorites = null;
		Connection conn = (Connection) sc.getAttribute("dbConn");

		UserFavoritesPropertiesDAO favoritesDao = new JDBCUserFavoritesPropertiesDAOImpl();
		favoritesDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		favorites = favoritesDao.getAllByUser(user.getId());

		List <Long> listIdp = new ArrayList<Long>();
		for (UserFavoritesProperties f : favorites) {
			listIdp.add(f.getIdp());
		}

		return listIdp;

	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(Long propertyId, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserFavoritesPropertiesDAO favoritesDao = new JDBCUserFavoritesPropertiesDAOImpl();
		favoritesDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		UserFavoritesProperties favorite = new UserFavoritesProperties();
		favorite.setIdp(propertyId);
		favorite.setIdu(user.getId());

		boolean done = favoritesDao.add(favorite);
		System.out.println("El valor de done es: " + done);
		
		if (!done)
			throw new WebApplicationException(Response.Status.NOT_FOUND);

		System.out.println("Va a salir del post  de favorites");

		return Response.status(Response.Status.OK).entity("{\"status\" : \"200\", \"message\" : \"Property updated\"}")
				.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(propertyId)).build())
                .build();
	}
	
	@DELETE
	@Path("/{propertyid: [0-9]+}")
	public Response delete(@PathParam("propertyid") long propertyId, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserFavoritesPropertiesDAO favoritesDao = new JDBCUserFavoritesPropertiesDAOImpl();
		favoritesDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		boolean done = favoritesDao.delete(user.getId(), propertyId);

		if (!done)
			throw new WebApplicationException(Response.Status.NOT_FOUND);

		String message = "Favorite property deleted";

		return Response.status(Response.Status.OK)
				.entity("{\"status\" : \"200\", \"message\" : \"Property deleted\"}").build();
	}	
	
}