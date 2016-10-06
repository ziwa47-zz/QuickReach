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




	//String urlstr = "jdbc:sqlserver://qrdata.database.windows.net:1433;database=qrdata;user=qruser;password=P@ssword;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	  String urlstr = "jdbc:sqlserver://192.168.1.128:1433;database=qrdata;user=sa;password=as;loginTimeout=30;";
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		


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
