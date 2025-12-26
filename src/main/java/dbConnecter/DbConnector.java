package dbConnecter;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {

	private static Connection conn;
	
	public static Connection getDbConnection(){
		
		try {
			if(conn ==null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytechportal","root","aakash");
			System.out.println("Connection Created");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
		
	}
}
