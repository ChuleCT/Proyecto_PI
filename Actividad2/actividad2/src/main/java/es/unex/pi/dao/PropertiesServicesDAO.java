package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.PropertiesServices;


public interface PropertiesServicesDAO {

    /**
     * set the database connection in this DAO.
     * 
     * @param conn
     *            database connection.
    */
    public void setConnection(Connection conn);

    /**
     * Gets all the property and the categories related to them from the database.
     * 
     * @return List of all the property and the categories related to them from the database.
    */

    public List<PropertiesServices> getAll();

    /**
     * Gets all the services associated to a property.
     * 
     * @return List of all the services associated to a property.
    */
    public List<String> getCheckedServices(Long idp);

    /**
     *Gets all the PropertyService that are related to a service.
     * 
     * @param ids
     *            service identifier
     * 
     * @return List of all the PropertyService related to a service.
    */
    public List<PropertiesServices> getAllByService(long ids);

    /**
     * Gets all the PropertyService that contains an specific property.
     * 
     * @param idr
     *            Property Identifier
     * 
     * @return List of all the PropertyService that contains an specific property
    */
    public List<PropertiesServices> getAllByProperty(long idr);

    /**
     * Gets a PropertyService from the DB using idr and ids.
     * 
     * @param idr 
     *            property identifier.
     *            
     * @param ids
     *            service Identifier
     * 
     * @return PropertyService with that idr and ids.
    */

    public PropertiesServices get(long idr,long ids);

    /**
     * Adds an PropertyService to the database.
     * 
     * @param PropertyService
     *            PropertyService object with the details of the relation between the property and the service.
     * 
     * @return property identifier or -1 in case the operation failed.
    */

    public boolean add(PropertiesServices PropertyService);

    /**
     * Updates an existing PropertyService in the database.
     * 
     * @param dbObject
     *            PropertyService object that is going to be updated in the database 
     * @param newObject
     *            PropertyService object with the new details of the existing relation between the property and the service. 
     * 
     * @return True if the operation was made and False if the operation failed.
    */

    public boolean update(PropertiesServices dbObject, PropertiesServices newObject);

    /**
     * Deletes an PropertyService from the database.
     * 
     * @param idr
     *            Property identifier.
     *            
     * @param ids
     *            service Identifier
     * 
     * @return True if the operation was made and False if the operation failed.
    */

    public boolean delete(long idr, long ids);

    /**
     * Deletes all the PropertyService that contains a specific property.
     * 
     * @param idp
     *            Property identifier.
     * 
     * @return True if the operation was made and False if the operation failed.
    */
    public boolean deleteByIdp(long idp);
}
