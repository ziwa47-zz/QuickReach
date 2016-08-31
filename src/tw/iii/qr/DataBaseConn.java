package tw.iii.qr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConn {

	private Connection conn;
	private boolean isclosed;
	public Connection getConn() throws SQLException, Exception, IllegalAccessException, ClassNotFoundException {
		String urlstr = "jdbc:mysql://localhost:3306/quickreach?zeroDateTimeBehavior=convertToNull&user=root&password=cr3321&Unicode=true&characterEncoding=utf-8";

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		conn = DriverManager.getConnection(urlstr);
		isclosed = !conn.isClosed();
		return conn;
	}
	public void connclose(Connection conn) throws SQLException{
		if(conn!=null){
			conn.close();
		}
	}
}