package es.unex.pi.resources;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.pi.model.User;
import es.unex.pi.model.Property;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.dao.JDBCAccommodationDAOImpl;
import es.unex.pi.dao.AccommodationDAO;
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

@Path("/accommodations")

public class AccommodationsResource {
	
	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;
	
	// Get que devuelve las habitaciones de una propiedad (ahora mismo esto es ambiguo, ya que no sabe si el ID que se le pasa es de una propiedad o de una habitación(por el GET de abajo))
//	@GET
//	@Path("/{propertyid:[0-9]+}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Accommodation> getAccommodationsJSON(@PathParam("propertyid") long propertyid) {
//        List<Property> properties = null;
//        Connection conn = (Connection) sc.getAttribute("dbConn");
//        
//        PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
//        propertyDao.setConnection(conn);
//        
//        Property property = propertyDao.get(propertyid);
//        
//        List<Accommodation> accommodations = null;
//        
//        if (property != null) {
//            AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
//            accommodationDao.setConnection(conn);
//            
//            accommodations = accommodationDao.getAllByProperty(property.getId());
//     
//        } else {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//
//        return accommodations;
//	}
	
	// Get que devuelve una habitación en concreto
	@GET
	@Path("/{accommodationid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Accommodation getAccommodationJSON(@PathParam("accommodationid") long accommodationid) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);

		Accommodation accommodation = accommodationDao.get(accommodationid);

		if (accommodation != null) {
			return accommodation;
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
	
	// Post para añadir una habitación a una propiedad
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(Accommodation accommodation, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);

		accommodationDao.add(accommodation);

		String message = "Accommodation added";

		return Response.status(Response.Status.CREATED)
				.entity("{\"status\" : \"201\", \"message\" : \"" + message + "\"}")
				.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(accommodation.getId())).build())
				.build();
	}
	
	// Put para modificar una habitación
	@PUT
	@Path("/{accommodationid:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(Accommodation accommodation, @PathParam("accommodationid") long accommodationid) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);

		Accommodation oldAccommodation = accommodationDao.get(accommodationid);

		if (oldAccommodation != null) {
			accommodation.setId(accommodationid);
			accommodationDao.update(accommodation);

			String message = "Accommodation updated";

			return Response.status(Response.Status.OK)
					.entity("{\"status\" : \"200\", \"message\" : \"" + message + "\"}")
					.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(accommodationid)).build())
					.build();
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
	// Delete para borrar una habitación
	@DELETE
	@Path("/{accommodationid:[0-9]+}")
	public Response delete(@PathParam("accommodationid") long accommodationid) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);

		Accommodation accommodation = accommodationDao.get(accommodationid);

		if (accommodation != null) {
			accommodationDao.delete(accommodationid);

			String message = "Accommodation deleted";

			return Response.status(Response.Status.OK)
					.entity("{\"status\" : \"200\", \"message\" : \"" + message + "\"}").build();
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}
