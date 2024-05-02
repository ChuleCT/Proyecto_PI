package es.unex.pi.resources;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
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

@Path("/properties")
public class PropertiesResource {

	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;

	// Get para todas las propiedades de un usuario
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Property> getPropertiesJSON(@Context HttpServletRequest request) {
		List<Property> properties = null;
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		properties = propertyDao.getAllByUser(user.getId());

		return properties;
	}

	// Get para todas las propiedades por el destino (getALLBySearchDestination)
	@GET
	@Path("/{search: [a-zA-Z0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Property> getPropertiesByDestinationJSON(@PathParam("search") String search, @Context HttpServletRequest request) {
		List<Property> properties = null;
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		properties = propertyDao.getAllBySearchDestination(search);
		
		for (Property p : properties) {
			System.out.println("Property: " + p);
		}

		return properties;
	}

	// Get para una propiedad en concreto
	@GET
	@Path("/2/{propertyid: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Property getPropertyJSON(@PathParam("propertyid") long propertyid, @Context HttpServletRequest request) {
		Property property = null;
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		property = propertyDao.get(propertyid);
		return property;
	}

	// Post para añadir una propiedad
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response post(Property property, @Context HttpServletRequest request) {
			Response res = null;
			Connection conn = (Connection) sc.getAttribute("dbConn");

			PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
			propertyDao.setConnection(conn);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			property.setIdu(user.getId());
			long id = propertyDao.add(property);

			String message = "Property added";
			
			// Devuelve el id de la propiedad creada
			return Response.status(Response.Status.CREATED)
					.entity("{\"status\" : \"201\", \"message\" : \"" + message + "\", \"id\" : " + id + "}")
					.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build()).build();
		}
	
	// Post para añadir una propiedad con un formulario
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response post(MultivaluedMap<String, String> formParams, @Context HttpServletRequest request) {
		Response res = null;
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Property property = new Property();
		property.setIdu(user.getId());
		property.setName(formParams.getFirst("name"));
		property.setAddress(formParams.getFirst("address"));
		property.setTelephone(formParams.getFirst("telephone"));
		property.setGradesAverage(Double.parseDouble(formParams.getFirst("gradesAverage")));
		property.setCity(formParams.getFirst("city"));
		property.setCenterDistance(Double.parseDouble(formParams.getFirst("centerDistance")));
		property.setDescription(formParams.getFirst("description"));
		property.setPetFriendly(Integer.parseInt(formParams.getFirst("petFriendly")));
		property.setAvailable(Integer.parseInt(formParams.getFirst("available")));
		long id = propertyDao.add(property);

		String message = "Property added";
		return Response.status(Response.Status.CREATED)
				.entity("{\"status\" : \"201\", \"message\" : \"" + message + "\"}")
				.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build()).build();
	}

	// Put para modificar una propiedad
	@PUT
	@Path("/{propertyid: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(Property property, @PathParam("propertyid") long propertyid,
			@Context HttpServletRequest request) {
		Response res = null;
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Property oldProperty = propertyDao.get(propertyid);

		if (oldProperty != null && oldProperty.getIdu() == user.getId()) {
			property.setId(propertyid);
			propertyDao.update(property);
			return Response.status(Response.Status.OK).entity("{\"status\" : \"200\", \"message\" : \"Property updated\"}")
					.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(propertyid)).build())
                    .build();
		} else {
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
	}

	// Delete para borrar una propiedad
	@DELETE
	@Path("/{propertyid: [0-9]+}")
	public Response delete(@PathParam("propertyid") long propertyid, @Context HttpServletRequest request) {
		Response res = null;
		Connection conn = (Connection) sc.getAttribute("dbConn");

		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Property property = propertyDao.get(propertyid);

		if (property != null && property.getIdu() == user.getId()) {
			propertyDao.delete(propertyid);
			String message = "Property deleted";
			return Response.status(Response.Status.OK)
					.entity("{\"status\" : \"200\", \"message\" : \"Property deleted\"}").build();
		} else {
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
	}

}