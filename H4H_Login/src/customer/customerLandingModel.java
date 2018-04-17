package customer;

import java.sql.Connection;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class customerLandingModel {
Connection connection;
	
	public customerLandingModel() {
		try {
			this.connection = dbConnection.getConnection();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		if (this.connection == null) {
			System.exit(1);
		}
	}
	
	public boolean isConnected() {
		return this.connection != null;
	}
}
