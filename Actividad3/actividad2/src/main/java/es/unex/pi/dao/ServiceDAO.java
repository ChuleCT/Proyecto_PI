package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.Service;


public interface ServiceDAO {

	/**
	 * Sets the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);

	/**
	 * Gets an Service from the DB using ids.
	 * 
	 * @param id
	 *            Service Identifier.
	 * 
	 * @return Service object with that id.
	 */
	public Service get(long id);

	/**
	 * Gets an Service from the DB using name.
	 * 
	 * @param name
	 *            Name of the Service.
	 * 
	 * @return Service object with that name.
	 */
	public Service get(String name);
	
	/**
	 * Gets all the Services from the database.
	 * 
	 * @return List of all the Services from the database.
	 */
	public List<Service> getAll();

	/**
	 * Adds an Service to the database.
	 * 
	 * @param Service
	 *            Service object with the Service details.
	 * 
	 * @return Service identifier or -1 in case the operation failed.
	 */
	public long add(Service service);

	/**
	 * Updates an existing Service in the database.
	 * 
	 * @param Service
	 *            Service object with the new details of the existing Service.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean update(Service service);

	/**
	 * Deletes an Service from the database.
	 * 
	 * @param ids
	 *            Service identifier.
	 * 
	 * @return True if the operation was made and False if the operation failed.
	 */
	public boolean delete(long ids);
}
