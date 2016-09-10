package tw.iii.purchase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.iii.qr.DataBaseConn;

/**
 * Servlet implementation class ProcessMasterWarehouse
 */
@WebServlet("/ProcessMasterWarehouse")
public class ProcessMasterWarehouse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processWarehouse(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processWarehouse(request, response);
	}

	public void processWarehouse(HttpServletRequest request, HttpServletResponse response) {
		
		PreparedStatement ps = null;
		
		
		
			 String autoWarehouse =request.getParameter("warehouse");
			 int date = Integer.valueOf(request.getParameter("purchaseDate")) ;
		
		
		HashMap<String, String> hm = new HashMap<String, String>();		
	
		try {
			
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			String checkPurchaseId = date+"01"+autoWarehouse;
			System.out.println(checkPurchaseId+"%");
			
			int count = 0;
			DecimalFormat df = new DecimalFormat("000");
			
			DataBaseConn jdbc = new DataBaseConn();
			conn = jdbc.getConn();			
			
			String strSql = " select count(*) from  purchaselog_master where purchaseId like ? ";

			
			ps = conn.prepareStatement(strSql);
			
			
			ps.setString(1, checkPurchaseId+"%");
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				
				count = rs.getInt(1);				
		
			hm.put("warehousePurchaseId", "01"+autoWarehouse+df.format(count+1));
			
			}
			
			System.out.println("warehouse:\n"+autoWarehouse);
			System.out.println("count:\n"+count);
			System.out.println("fffffinally"+autoWarehouse+df.format(count+1));
			System.out.println("HashMap:\n"+hm);
			JSONObject responseJSONObject = new JSONObject(hm);
			PrintWriter out = response.getWriter();
			System.out.println("strSql:\n"+strSql);

			out.println(responseJSONObject);
			System.out.println("JSONObject:\n"+responseJSONObject);
			
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception e) {
			
		}
	
	}
	
	

}
