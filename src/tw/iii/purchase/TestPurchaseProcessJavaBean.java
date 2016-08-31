package tw.iii.purchase;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;



public class TestPurchaseProcessJavaBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Connection conn;
	
	private LinkedList<String> list = new LinkedList<String>();

	
	public TestPurchaseProcessJavaBean() {
	}
	
	private void add() throws SQLException{
	
	}
	
	public LinkedList<String> purchaseInfo(HttpServletRequest request){
		LinkedList<String> details = new LinkedList<String>();
		
		details.add(request.getParameter("company"));
		details.add(request.getParameter("date"));
		details.add(request.getParameter("sku"));
		
		
		return details;
		
		
	}
	
	

}
