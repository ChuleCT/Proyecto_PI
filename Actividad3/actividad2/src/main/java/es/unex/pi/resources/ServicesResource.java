package es.unex.pi.resources;

import java.sql.Connection;
import java.util.List;
import es.unex.pi.model.Service;
import es.unex.pi.dao.JDBCServiceDAOImpl;
import es.unex.pi.dao.ServiceDAO;
import es.unex.pi.dao.JDBCPropertiesServicesDAOImpl;
import es.unex.pi.dao.PropertiesServicesDAO;


import jakarta.ws.rs.Path;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;


@Path("/services")
public class ServicesResource {

	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Service> getAllServices() {
    	List<Service> services = null;
    	Connection conn = (Connection) sc.getAttribute("dbConn");
    	
    	ServiceDAO serviceDao = new JDBCServiceDAOImpl();
    	serviceDao.setConnection(conn);
    	
    	services = serviceDao.getAll();
    	
    	return services; 
    }

    @SuppressWarnings("null")
	@GET
    @Path("/p/{propertyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Service> getServicesByPropertyId(@PathParam("propertyId") long propertyId) {
        List<Service> services = new java.util.ArrayList<Service>();
        Connection conn = (Connection) sc.getAttribute("dbConn");
        
        ServiceDAO serviceDao = new JDBCServiceDAOImpl();
        serviceDao.setConnection(conn);
        
        
        PropertiesServicesDAO propertiesServicesDao = new JDBCPropertiesServicesDAOImpl();
        propertiesServicesDao.setConnection(conn);
        List<String> checkedServices = propertiesServicesDao.getCheckedServices(propertyId);
        
        System.out.println("LLega bien hasta aqui");

		for (String s : checkedServices) {
			Service service = serviceDao.get(s);
			services.add(service);
	        System.out.println("sigue llegando bien hasta aqui");
		}
		return services;
    }

  
}


