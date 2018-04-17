package addProductToList;

import java.sql.Connection;
import java.sql.SQLException;

import dbUtil.DBConnection;

public class AddProductModel {
Connection connection;
	
	public AddProductModel() {
		try {
			this.connection = DBConnection.getConnection();
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
