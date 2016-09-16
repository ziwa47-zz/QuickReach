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
@WebServlet("/AjaxProcessMasterWarehouse")
public class AjaxProcessMasterWarehouse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	String isStockTransfer;
	String autoWarehouse;
	int date;

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

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public void processWarehouse(HttpServletRequest request, HttpServletResponse response) {

		isStockTransfer = request.getParameter("newWarehouse");
		autoWarehouse = request.getParameter("warehouse");
		date = Integer.valueOf(request.getParameter("purchaseDate"));
		
//		System.out.println("newWarehouse?  ----->"+isStockTransfer);

		try {

			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			DataBaseConn jdbc = new DataBaseConn();
			conn = jdbc.getConn();
			

			if (isNullorEmpty(isStockTransfer)) {
				purchase(response);

			} else {
				stockTransfer(response);

			}

			
			conn.close();
		} catch (Exception e) {

		}

	}

	public void purchase(HttpServletResponse response) {

		HashMap<String, String> hm = new HashMap<String, String>();
		String checkPurchaseId = date + "01" + autoWarehouse;
//		System.out.println(checkPurchaseId + "%");

		int count = 0;
		DecimalFormat df = new DecimalFormat("000");
	
		try {

			String strSql = " select count(*) from  purchaselog_master where purchaseId like ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);

			ps.setString(1, checkPurchaseId + "%");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				count = rs.getInt(1);

				hm.put("warehousePurchaseId", "01" + autoWarehouse + df.format(count + 1));
			}
			
//			System.out.println("warehouse:\n" + autoWarehouse);
//			System.out.println("count:\n" + count);
//			System.out.println("fffffinally" + autoWarehouse + df.format(count + 1));
//			System.out.println("HashMap:\n" + hm);
			JSONObject responseJSONObject = new JSONObject(hm);
			PrintWriter out = response.getWriter();
//			System.out.println("strSql:\n" + strSql);

			out.println(responseJSONObject);
//			System.out.println("JSONObject:\n" + responseJSONObject);

			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void stockTransfer(HttpServletResponse response) {

		HashMap<String, String> hm = new HashMap<String, String>();
		String checkPurchaseId = date + "03" + autoWarehouse;

		int count = 0;
		DecimalFormat df = new DecimalFormat("000");
	
		try {

			String strSql = " select count(*) from  stockTransferlog_master where stockTransferId like ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);

			ps.setString(1, checkPurchaseId + "%");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				count = rs.getInt(1);

				hm.put("stockTransferId", "03" + autoWarehouse + df.format(count + 1));
			}
			
//			System.out.println("warehouse:\n" + autoWarehouse);
//			System.out.println("count:\n" + count);
//			System.out.println("fffffinally" + autoWarehouse + df.format(count + 1));
//			System.out.println("HashMap:\n" + hm);
			JSONObject responseJSONObject = new JSONObject(hm);
			PrintWriter out = response.getWriter();
//			System.out.println("strSql:\n" + strSql);

			out.println(responseJSONObject);
//			System.out.println("JSONObject:\n" + responseJSONObject);

			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
