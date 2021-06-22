package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;



public class DBUtil {
	public static String pr_url="jdbc:mysql://localhost:3306/paqu?useSSL=false";
	public static String pr_user="root";
	public static String pr_pass="Inazuma";
	
	public static Connection getConn() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(pr_url, pr_user, pr_pass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//×¢Òâ¹Ø±ÕË³Ðò
	public static void close(Statement state,Connection conn) throws SQLException {
		if(state != null) {
			try {
				state.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//×¢Òâ¹Ø±ÕË³Ðò
	public static void close (ResultSet rs, Statement state, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (state != null) {
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
