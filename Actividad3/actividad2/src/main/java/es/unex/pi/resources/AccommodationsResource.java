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
	
	@GET
	@Path("/{propertyid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Accommodation> getAccommodationsJSON(@PathParam("propertyid") long propertyid) {
        Connection conn = (Connection) sc.getAttribute("dbConn");
        
        PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
        propertyDao.setConnection(conn);
        
        Property property = propertyDao.get(propertyid);
        
        List<Accommodation> accommodations = null;
        
        if (property != null) {
            AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
            accommodationDao.setConnection(conn);
            
            accommodations = accommodationDao.getAllByProperty(property.getId());
     
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
		for (Accommodation a : accommodations) {
			System.out.println(a.toString());
		}

        return accommodations;
	}
	
	// Get que devuelve una habitación en concreto
	@GET
	@Path("/accommodation/{accommodationid:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Accommodation getAccommodationJSON(@PathParam("accommodationid") long accommodationid) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);

		Accommodation accommodation = accommodationDao.get(accommodationid);

		if (accommodation != null) {
			System.out.println(accommodation.toString());
			return accommodation;
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
	
	
	// Post para añadir una habitación a una propiedad
	@POST
	@Path("/{idp:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(Accommodation accommodation, @PathParam("idp") long idp, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		AccommodationDAO accommodationDao = new JDBCAccommodationDAOImpl();
		accommodationDao.setConnection(conn);
		
		//comprobar que la propiedad existe y me pertenece
		accommodation.setIdp(idp);

		accommodationDao.add(accommodation);

		String message = "Accommodation added";
		
		// Si la habitacion que se ha añadido tiene numAccommodations > 0 y la disponibilidad de la propiedad es false, se cambia a true
		
		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);
		
		Property property = propertyDao.get(idp);
		
		if (accommodation.getNumAccommodations() > 0 && property.getAvailable() == 0) {
			property.setAvailable(1);
			propertyDao.update(property);
		}

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
			
			
		    // Si la habitacion que se ha modificado tiene numAccommodations > 0 y la disponibilidad de la propiedad es false, se cambia a true
			PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
			propertyDao.setConnection(conn);
			
			Property property = propertyDao.get(oldAccommodation.getIdp());
			
			if (accommodation.getNumAccommodations() > 0 && property.getAvailable() == 0) {
				property.setAvailable(1);
				propertyDao.update(property);
			}
			
			// Si la habitacion que se ha modificado tiene numAccommodations = 0 y el resto de habitaciones de la propiedad también, se cambia a false en caso de que estuviera a true
			
			if (!accommodationDao.checkAvailability(accommodation.getIdp()) && property.getAvailable() == 1) {
				property.setAvailable(0);
				propertyDao.update(property);
			}

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
			
		PropertyDAO propertyDao = new JDBCPropertyDAOImpl();
		propertyDao.setConnection(conn);
		
		Property property = propertyDao.get(accommodation.getIdp());
			
	    if(!accommodationDao.checkAvailability(accommodation.getIdp()) && property.getAvailable() == 1) {
	    	property.setAvailable(0);
	    	propertyDao.update(property);
	    }

			return Response.status(Response.Status.OK)
					.entity("{\"status\" : \"200\", \"message\" : \"" + message + "\"}").build();
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}
