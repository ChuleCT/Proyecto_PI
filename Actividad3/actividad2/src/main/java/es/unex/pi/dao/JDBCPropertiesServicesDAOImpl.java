package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.PropertiesServices;
import es.unex.pi.model.Service;

public class JDBCPropertiesServicesDAOImpl implements PropertiesServicesDAO {

    private Connection conn;
    private static final Logger logger = Logger.getLogger(JDBCPropertiesServicesDAOImpl.class.getName());

    @Override
    public List<PropertiesServices> getAll() {

        if (conn == null) return null;

        ArrayList<PropertiesServices> propertiesServicesList = new ArrayList<PropertiesServices>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PropertiesServices");

            while ( rs.next() ) {
                PropertiesServices propertiesServices = new PropertiesServices();
                fromRsToPropertiesServicesObject(rs,propertiesServices);
                propertiesServicesList.add(propertiesServices);
                logger.info("fetching all PropertiesServices: "+propertiesServices.getIdp()+" "+propertiesServices.getIds());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return propertiesServicesList;
    }

    @Override
    public List<String> getCheckedServices(Long idp){

        if (conn == null) return null;

        List<PropertiesServices> propertiesServicesList;
        propertiesServicesList = getAllByProperty(idp);

        List<String> checkedServices = new ArrayList<String>();

        for (PropertiesServices ps: propertiesServicesList){
            ServiceDAO serviceDAO = new JDBCServiceDAOImpl();
            serviceDAO.setConnection(conn);
            Service s = serviceDAO.get(ps.getIds()); // Obtiene el servicio
            logger.info("\n\n\n\nfetching all PropertiesServices by idp: "+ps.getIds()+ "el name del servicio es: "+ s.getName() + "\n\n\n\n");
            checkedServices.add(s.getName());
        }
        return checkedServices;
    }
    
    @Override
    public List<Service> getCheckedServices2(Long idp){

        if (conn == null) return null;

        List<PropertiesServices> propertiesServicesList;
        propertiesServicesList = getAllByProperty(idp);

        List<Service> checkedServices = new ArrayList<Service>();

        for (PropertiesServices ps: propertiesServicesList){
            ServiceDAO serviceDAO = new JDBCServiceDAOImpl();
            serviceDAO.setConnection(conn);
            Service s = serviceDAO.get(ps.getIds()); // Obtiene el servicio
            logger.info("\n\n\n\nfetching all PropertiesServices by idp: "+ps.getIds()+ "el name del servicio es: "+ s.getName() + "\n\n\n\n");
            checkedServices.add(s);
        }
        return checkedServices;
    }

    @Override
    public List<PropertiesServices> getAllByService(long ids) {

        if (conn == null) return null;

        ArrayList<PropertiesServices> propertiesServicesList = new ArrayList<PropertiesServices>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PropertiesServices WHERE ids="+ids);

            while ( rs.next() ) {
                PropertiesServices propertiesServices = new PropertiesServices();
                fromRsToPropertiesServicesObject(rs,propertiesServices);
                propertiesServicesList.add(propertiesServices);
                logger.info("fetching all PropertiesServices by idp: "+propertiesServices.getIdp()+"->"+propertiesServices.getIds());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return propertiesServicesList;
    }

    @Override
    public List<PropertiesServices> getAllByProperty(long idp) {

        if (conn == null) return null;

        ArrayList<PropertiesServices> propertiesServicesList = new ArrayList<PropertiesServices>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PropertiesServices WHERE idp="+idp);

            while ( rs.next() ) {
                PropertiesServices propertiesServices = new PropertiesServices();
                fromRsToPropertiesServicesObject(rs,propertiesServices);
                propertiesServicesList.add(propertiesServices);
                logger.info("fetching all PropertiesServices by ids: "+propertiesServices.getIds()+"-> "+propertiesServices.getIdp());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return propertiesServicesList;
    }


    @Override
    public PropertiesServices get(long idp,long ids) {
        if (conn == null) return null;

        PropertiesServices propertiesServices = null;		

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PropertiesServices WHERE idp="+idp+" AND ids="+ids);			 
            if (!rs.next()) return null;
            propertiesServices= new PropertiesServices();
            fromRsToPropertiesServicesObject(rs,propertiesServices);
            logger.info("fetching PropertiesServices by idp: "+propertiesServices.getIdp()+"  and ids: "+propertiesServices.getIds());


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return propertiesServices;
    }

    @Override
    public boolean add(PropertiesServices propertiesServices) {
        boolean done = false;
        if (conn != null){

            Statement stmt;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO PropertiesServices (idp,ids) VALUES('"+
                    propertiesServices.getIdp()+"','"+
                    propertiesServices.getIds()+"')");

                logger.info("creating PropertiesServices:("+propertiesServices.getIdp()+" "+propertiesServices.getIds());
                done= true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return done;
    }

    @Override
    public boolean update(PropertiesServices dbObject, PropertiesServices newObject) {
        boolean done = false;
        if (conn != null){

            Statement stmt;
            try {
                stmt = conn.createStatement();

                stmt.executeUpdate("UPDATE PropertiesServices SET ids="+newObject.getIds()
                    +" WHERE idp = "+dbObject.getIdp() + " AND ids = " + dbObject.getIds());

                logger.info("updating PropertiesServices:("+dbObject.getIdp()+" "+dbObject.getIds());

                done= true;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return done;
    }

    @Override
    public boolean delete(long idp, long ids) {
        boolean done = false;
        if (conn != null){

            Statement stmt;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM PropertiesServices WHERE idp ="+idp+" AND ids="+ids);
                logger.info("deleting PropertiesServices: "+idp+" , ids="+ids);
                done= true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return done;
    }

    @Override
    public boolean deleteByIdp(long idp) {
        boolean done = false;
        if (conn != null){

            Statement stmt;
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM PropertiesServices WHERE idp ="+idp);
                logger.info("deleting PropertiesServices: "+idp);
                done= true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return done;
    }

    public void fromRsToPropertiesServicesObject(ResultSet rs, PropertiesServices propertiesServices) throws SQLException {
        propertiesServices.setIdp(rs.getInt("idp"));
        propertiesServices.setIds(rs.getInt("ids"));


    }

    @Override
    public void setConnection(Connection conn) {
        // TODO Auto-generated method stub
        this.conn = conn;
    }

}
