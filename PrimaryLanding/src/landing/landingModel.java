package landing;

import java.sql.Connection;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class landingModel {

Connection connection;
	
	public landingModel() {
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
