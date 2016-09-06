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
		String urlstr = "jdbc:mysql://ap-cdbr-azure-east-c.cloudapp.net:3306/qrdata?zeroDateTimeBehavior=convertToNull&user=b80eb20f3a035f&password=79b419f4&Unicode=true&characterEncoding=utf-8";

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