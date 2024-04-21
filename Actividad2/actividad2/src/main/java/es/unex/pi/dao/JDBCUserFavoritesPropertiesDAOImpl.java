package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.UserFavoritesProperties;
import es.unex.pi.dao.UserFavoritesPropertiesDAO;
import es.unex.pi.model.UserFavoritesProperties;
import es.unex.pi.model.Property;
import es.unex.pi.model.Service;

public class JDBCUserFavoritesPropertiesDAOImpl implements UserFavoritesPropertiesDAO {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(JDBCUserFavoritesPropertiesDAOImpl.class.getName());

    @Override
    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<UserFavoritesProperties> getAllByUser(long idu){

        if (conn == null) return null;

        ArrayList<UserFavoritesProperties> favoritesProperties = new ArrayList<UserFavoritesProperties>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserFavoritesProperties");

            while ( rs.next() ) {
                UserFavoritesProperties userFavoritesProperties = new UserFavoritesProperties();
                fromRsToUserFavoritesPropertiesObject(rs,userFavoritesProperties);
                favoritesProperties.add(userFavoritesProperties);
                logger.info("fetching all UserFavoritesProperties: "+userFavoritesProperties.getIdu()+" "+userFavoritesProperties.getIdp());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return favoritesProperties;

    }


    @Override
    public boolean add(UserFavoritesProperties userFavoritesProperties){
        boolean done = false;
        if (conn != null){

            Statement stmt;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO userFavoritesProperties (idu,idp) VALUES('"+
                    userFavoritesProperties.getIdu()+"','"+
                    userFavoritesProperties.getIdp()+"')");

                logger.info("creating userFavoritesProperties:("+userFavoritesProperties.getIdu()+" "+userFavoritesProperties.getIdp());
                done= true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return done;

    }

    @Override
    public boolean delete(long idu, long idp){
        boolean done = false;
        if (conn != null){

            Statement stmt;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM UserFavoritesProperties WHERE idu ="+idu+" AND idp="+idp);
                logger.info("deleting UserFavoritesProperties: "+idu+" , idp="+idp);
                done= true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return done;
    }
    public void fromRsToUserFavoritesPropertiesObject(ResultSet rs, UserFavoritesProperties userFavoritesProperties) throws SQLException {
        userFavoritesProperties.setIdu(rs.getInt("idu"));
        userFavoritesProperties.setIdp(rs.getInt("idp"));


    }
}
