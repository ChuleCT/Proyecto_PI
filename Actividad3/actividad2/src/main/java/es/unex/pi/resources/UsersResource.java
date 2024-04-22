package es.unex.pi.resources;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import es.unex.pi.model.User;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;

@Path("/users")
public class UsersResource {
	
	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("/{userid: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserJSON(@PathParam("userid") long userid, @Context HttpServletRequest request){
        
        List<User> users = null;
        
        Connection conn = (Connection) sc.getAttribute("dbConn");
        
        UserDAO userDao = new JDBCUserDAOImpl();
        userDao.setConnection(conn);
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if(user != null && user.getId() == userid) {
            User u = userDao.get(userid);
            if(u != null) {
                return u;
            }
            else {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        }
        else {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(User user, @Context HttpServletRequest request) throws Exception{
		Response response = null;

		Connection conn = (Connection) sc.getAttribute("dbConn");

		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		long id = userDao.add(user);
		
		String message = "User added";
		return Response.status(Response.Status.CREATED)
				.entity("{\"status\" : \"201\", \"message\" : \"" + message + "\"}")
				.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build())
				.build();
	}
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response post(MultivaluedMap<String, String> formParams, @Context HttpServletRequest request) {
		Response response = null;

		Connection conn = (Connection) sc.getAttribute("dbConn");

		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		User user = new User();
		user.setName(formParams.getFirst("name"));
		user.setPassword(formParams.getFirst("password"));
		user.setSurname(formParams.getFirst("surname"));
		user.setEmail(formParams.getFirst("email"));

		long id = userDao.add(user);

		String message = "User added";
		return Response.status(Response.Status.CREATED)
				.entity("{\"status\" : \"201\", \"message\" : \"" + message + "\"}")
				.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build()).build();
	}
	
	@PUT
	@Path("/{userid: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(User user, @PathParam("userid") long userid, @Context HttpServletRequest request) {
		Response response = null;

		Connection conn = (Connection) sc.getAttribute("dbConn");

		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		User u = userDao.get(userid);
		if (u != null) {
			user.setId(userid);
			userDao.update(user);
			String message = "User updated";
			return Response.status(Response.Status.OK)
					.entity("{\"status\" : \"200\", \"message\" : \"" + message + "\"}")
					.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(userid)).build()).build();
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
	@DELETE
	@Path("/{userid: [0-9]+}")
	public Response delete(@PathParam("userid") long userid, @Context HttpServletRequest request) {
		Response response = null;

		Connection conn = (Connection) sc.getAttribute("dbConn");

		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		User u = userDao.get(userid);
		if (u != null) {
			userDao.delete(userid);
			String message = "User deleted";
			return Response.status(Response.Status.OK)
					.entity("{\"status\" : \"200\", \"message\" : \"" + message + "\"}").build();
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
        
}
