package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.ProvisionalBookings;

public class JDBCProvisionalBookingsDAOImpl implements ProvisionalBookingsDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCPropertiesServicesDAOImpl.class.getName());

	@Override
	public List<ProvisionalBookings> getAll() {

		if (conn == null)
			return null;

		ArrayList<ProvisionalBookings> provisionalBookingsList = new ArrayList<ProvisionalBookings>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ProvisionalBookings");

			while (rs.next()) {
				ProvisionalBookings provisionalBooking = new ProvisionalBookings();
				fromRsToProvisionalBookingsObject(rs, provisionalBooking);
				provisionalBookingsList.add(provisionalBooking);
				logger.info("fetching all ProvisionalBookings: " + provisionalBooking.getIda() + " "
						+ provisionalBooking.getNum());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provisionalBookingsList;
	}
	
	@Override
	public ProvisionalBookings get(long ida) {
		if (conn == null)
			return null;

		ProvisionalBookings provisionalBooking = new ProvisionalBookings();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ProvisionalBookings WHERE ida=" + ida);

			if (!rs.next())
				return null;

			fromRsToProvisionalBookingsObject(rs, provisionalBooking);
			logger.info(
					"fetching ProvisionalBookings: " + provisionalBooking.getIda() + " " + provisionalBooking.getNum());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return provisionalBooking;
	}

	@Override
	public boolean add(ProvisionalBookings provisionalBooking) {
		if (conn == null)
			return false;

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO ProvisionalBookings (ida, num, idp, price, name) VALUES ('"
					+ provisionalBooking.getIda() + "', '" + provisionalBooking.getNum() + "', '"
					+ provisionalBooking.getIdp() + "', '" + provisionalBooking.getPrice() + "', '"
					+ provisionalBooking.getName() + "')");
			logger.info("adding ProvisionalBookings: " + provisionalBooking.getIda() + " " + provisionalBooking.getNum());
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(long ida) {
		if (conn == null)
			return false;

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM ProvisionalBookings WHERE ida=" + ida);
			logger.info("deleting ProvisionalBookings: " + ida);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteAll() {
		if (conn == null)
			return false;

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("DELETE FROM ProvisionalBookings");
			logger.info("deleting all ProvisionalBookings");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void fromRsToProvisionalBookingsObject(ResultSet rs, ProvisionalBookings provisionalBooking)
			throws SQLException {
		try {
			provisionalBooking.setIda(rs.getLong("ida"));
			provisionalBooking.setNum(rs.getInt("num"));
			provisionalBooking.setIdp(rs.getLong("idp"));
			provisionalBooking.setPrice(rs.getLong("price"));
			provisionalBooking.setName(rs.getString("name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
}
