package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Service;

public class JDBCServiceDAOImpl implements ServiceDAO {

    private Connection conn;
    private static final Logger logger = Logger.getLogger(JDBCServiceDAOImpl.class.getName());

    @Override
    public Service get(long id) {
        if (conn == null) return null;

        Service Service = null;		

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM services WHERE id ="+id);			 
            if (!rs.next()) 
            return null; 

            Service  = new Service();
            fromRsToServiceObject(rs,Service);
            logger.info("fetching Service by id: "+id+" -> "+Service.getId()+" "+Service.getName()+" "+Service.getIcon());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Service;
    }

    @Override
    public Service get(String name) {
        if (conn == null) return null;

        Service Service = null;		

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM services WHERE name ='"+name+"'");			 
            if (!rs.next()) return null; 
            Service  = new Service();	 
            fromRsToServiceObject(rs,Service);
            logger.info("fetching Service by name: "+ name + " -> "+ Service.getId()+" "+Service.getName()+" "+Service.getIcon());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Service;
    }


    public List<Service> getAll() {

        if (conn == null) return null;

        ArrayList<Service> Services = new ArrayList<Service>();
        try {
            Statement stmt;
            ResultSet rs;
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM services");
            while ( rs.next() ) {
                Service Service = new Service();
                fromRsToServiceObject(rs,Service);		
                Services.add(Service);
                logger.info("fetching Services: "+Service.getId()+" "+Service.getName()+" "+Service.getIcon());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Services;
    }


    @Override
    public long add(Service Service) {
        long id=-1;
        long lastidu=-1;
        if (conn != null){

            Statement stmt;

            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='services'");			 
                if (!rs.next()) return -1; 
                lastidu=rs.getInt("seq");

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO services (name,icon) VALUES('"
                    +Service.getName()+"','"
                    +Service.getIcon()+"')");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='services'");			 
                if (!rs.next()) return -1; 
                id=rs.getInt("seq");
                if (id<=lastidu) return -1;

                logger.info("CREATING Service("+id+"): "+Service.getName()+" "+Service.getIcon());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return id;
    }

    @Override
    public boolean update(Service Service) {
        boolean done = false;
        if (conn != null){

            Statement stmt;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("UPDATE services SET name='"+Service.getName()
                    +"', icon='"+Service.getIcon()
                    +"' WHERE id = "+Service.getId());
                logger.info("updating Service: "+Service.getId()+" "+Service.getName()+" "+Service.getIcon());
                done= true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return done;

    }

    @Override
    public boolean delete(long id) {
        boolean done = false;
        if (conn != null){

            Statement stmt;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM services WHERE id ="+id);
                logger.info("deleting Service: "+id);
                done= true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return done;
    }

    public void fromRsToServiceObject(ResultSet rs, Service Service) throws SQLException {		
        Service.setId(rs.getInt("id"));
        Service.setName(rs.getString("name"));
        Service.setIcon(rs.getString("icon"));
    }

    @Override
    public void setConnection(Connection conn) {
        // TODO Auto-generated method stub
        this.conn = conn;
    }


}
