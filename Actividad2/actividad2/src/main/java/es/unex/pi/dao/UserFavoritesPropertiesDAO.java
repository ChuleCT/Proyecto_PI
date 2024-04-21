package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.Property;
import es.unex.pi.model.UserFavoritesProperties;


public interface UserFavoritesPropertiesDAO {

    /**
     * set the database connection in this DAO.
     * 
     * @param conn
     *            database connection.
    */
    public void setConnection(Connection conn);


    /**
     * Gets all the favorites from the database.
     * 
     * @return List of all the favorites properties of the user from the database.
    */
    public List<UserFavoritesProperties> getAllByUser(long idu);

    /**
     * Adds a property to the database.
     * 
     * @param property
     *            Property object with the property details.
     * 
     * @return Property identifier or -1 in case the operation failed.
    */
    public boolean add(UserFavoritesProperties userFavoritesProperties);

    /**
     * Deletes a property from the database.
     * 
     * @param id
     *            Property identifier.
     * 
     * @return True if the operation was made and False if the operation failed.
    */
    public boolean delete(long idu, long idp);
}
