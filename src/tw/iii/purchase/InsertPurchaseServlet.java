package tw.iii.purchase;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.mysql.jdbc.ReplicationMySQLConnection;

import tw.iii.qr.DataBaseConn;


@WebServlet("/TestJdbcMvcServlet")
public class InsertPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	
	LinkedList<String> values = new LinkedList<String>();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//processJdbcAction(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//processJdbcAction(request,response);
		try {
			processInsert(request,response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	private void processInsert(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		DataBaseConn jdbc = new DataBaseConn();
		try {
			conn = jdbc.getConn();
			purchaseFactory pcf = new purchaseFactory();
			pcf.insertIntoPurchaseTest(request, conn); 
			//pcf.insertIntoPurchase(request, conn); 
			conn.close();
			
			response.sendRedirect("QRProduct/PurchasePage.jsp");
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	

}
