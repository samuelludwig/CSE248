package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	
	private static final String SQCONN = "jdbc:sqlite:C:/Users/SAMUE/Documents/H4HDB.sqlite";
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(SQCONN);
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
