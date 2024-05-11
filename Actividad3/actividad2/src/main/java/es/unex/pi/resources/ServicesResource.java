package es.unex.pi.resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import es.unex.pi.model.PropertiesServices;
import es.unex.pi.model.Service;
import es.unex.pi.dao.JDBCServiceDAOImpl;
import es.unex.pi.dao.ServiceDAO;
import es.unex.pi.dao.JDBCPropertiesServicesDAOImpl;
import es.unex.pi.dao.PropertiesServicesDAO;

import jakarta.ws.rs.Path;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
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

		for (String s : checkedServices) {
			Service service = serviceDao.get(s);
			services.add(service);
		}
		return services;
	}

	@PUT
	@Path("{propertyId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(@PathParam("propertyId") long propertyId, List<Service> services,
			@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		
		PropertiesServicesDAO propertiesServicesDao = new JDBCPropertiesServicesDAOImpl();
		propertiesServicesDao.setConnection(conn);
		ServiceDAO serviceDao = new JDBCServiceDAOImpl();
		serviceDao.setConnection(conn);

		List<String> oldCheckedServices = propertiesServicesDao.getCheckedServices(propertyId);
		oldCheckedServices = propertiesServicesDao.getCheckedServices(propertyId);
		
		System.out.println("oldCheckedServices: " + oldCheckedServices);
		for (Service s : services) {
			if (!oldCheckedServices.contains(s.getName())) {
				PropertiesServices ps = new PropertiesServices();
				ps.setIdp(propertyId);
				ps.setIds(s.getId());
				propertiesServicesDao.add(ps);
			}
		}
		
	    for (String oldServiceName : oldCheckedServices) {
	        boolean found = false;
	        // Iteramos sobre los servicios nuevos
	        for (Service newService : services) {
		        System.out.println("El servicio antiguo es: " + oldServiceName + " y el nuevo es: " + newService.getName());
	            if (oldServiceName.equals(newService.getName())) {
	                found = true;
	            }
	        }
	        // Si el servicio antiguo no está en la lista de servicios nuevos, lo eliminamos
	        if (!found) {
				System.out.println("Eliminando servicio: " + oldServiceName);
	        	Service aux = serviceDao.get(oldServiceName);
	            propertiesServicesDao.delete(propertyId, aux.getId());
	        }
	    }
		return null;
	
	}
	@POST 
	@Path("{propertyId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(@PathParam("propertyId") long propertyId, List<Service> services,
			@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		PropertiesServicesDAO propertiesServicesDao = new JDBCPropertiesServicesDAOImpl();
		propertiesServicesDao.setConnection(conn);
		ServiceDAO serviceDao = new JDBCServiceDAOImpl();
		serviceDao.setConnection(conn);
		
		System.out.println("Si entra en el post de services");

		if (services == null) {
			System.out.println("No hay servicios");
			return null;
		}
		for (Service s : services) {
			PropertiesServices ps = new PropertiesServices();
			ps.setIdp(propertyId);
			ps.setIds(s.getId());
			propertiesServicesDao.add(ps);
		}
		System.out.println("Si sale del post de services");
		return null;
	}
	
	
	

}