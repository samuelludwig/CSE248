package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.dbConnection;

public class H4HLoginModel {
	Connection connection;
	
	public H4HLoginModel() {
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
	
	public boolean isLoggedIn(String user, String password, String option) throws Exception {
		PreparedStatement pr = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM H4HUserTable where username = ? and password = ? and division = ?";
		
		try {
			pr = this.connection.prepareStatement(sql);
			pr.setString(1, user);
			pr.setString(2, password);
			pr.setString(3, option);
			rs = pr.executeQuery();
			
			if(rs.next()) {
				return true;
			} else { 
				return false;
			}
			
		} catch(SQLException ex) {
			return false;
		}
		
		finally {
			{
			pr.close();
			rs.close();
			}
		}
	}
}
