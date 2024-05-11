package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.ProvisionalBookings;



public interface ProvisionalBookingsDAO {
	
	/**
     * set the database connection in this DAO.
     * 
     * @param conn
     *            database connection.
    */
    public void setConnection(Connection conn);

    /**
     * Gets all the provisional bookings from the database.
     * 
     * @return List of all the provisional bookings from the database.
    */

    public List<ProvisionalBookings> getAll();
    
    /**
     * Adds a new provisional booking to the database.
     * 
     * @param provisionalBooking
     *           Provisional booking to be added.
     * 
     * @return True if the provisional booking was added successfully, false otherwise.
    */
    
    public ProvisionalBookings get(long ida);

	/**
	 * Gets a provisional booking from the database.
	 * 
	 * @param ida (accommodation identifier)
	 * 
	 * @return Provisional booking from the database.
	 */
    public boolean add(ProvisionalBookings provisionalBooking);
    
    
    
	/**
	 * Deletes a provisional booking from the database.
	 * 
	 * @param ida (accommmodation identifier)
	 * 
	 * @return True if the provisional booking was deleted successfully, false otherwise.
	 */
    public boolean delete (long ida);
    
    
	/**
	 * Deletes all provisional bookings from the database.
	 * 
	 * @return True if all provisional bookings were deleted successfully, false
	 *         otherwise.
	 */
    public boolean deleteAll();

}
