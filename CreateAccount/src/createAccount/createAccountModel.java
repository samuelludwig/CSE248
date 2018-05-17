package createAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class createAccountModel {

Connection connection;
	
	public createAccountModel() {
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
	
	public boolean isUsernameTaken(String user) throws Exception {
		PreparedStatement pr = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM H4HUserTable where username = ?";
		
		try {
			pr = this.connection.prepareStatement(sql);
			pr.setString(1, user);
			rs = pr.executeQuery();
			
			if(rs.next()) {
				pr.close();
				rs.close();
				return true;
			} else { 
				pr.close();
				rs.close();
				return false;
			}
			
		} catch(SQLException ex) {
			return false;
		}
		
	}
}
