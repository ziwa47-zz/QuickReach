package tw.iii.purchase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.iii.qr.DataBaseConn;

import org.apache.poi.hssf.model.ConvertAnchor;
import org.json.JSONObject;

@WebServlet("/AjaxProcessAutoComplete")
public class AjaxProcessAutoComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			try {
				process(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			process(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PreparedStatement ps = null;
		String searchSku = "";
		
		
			 String autoCompleteNumber =request.getParameter("autoCompleteNumber");
			 //searchSku = request.getParameter(autoCompleteNumber);
		
		
		HashMap<String, Object> hm = new HashMap<String, Object>();		
	
		try {
			DataBaseConn jdbc = new DataBaseConn();
			conn = jdbc.getConn();			
			
			String strSql = "SELECT  a.P_name, a.spec, a.color, a.owner,a.cost FROM  product as a left join  storage as b on a.SKU = b.SKU where a.SKU = ? ;";

			
			ps = conn.prepareStatement(strSql);
			
			ps.setString(1, autoCompleteNumber);
	
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
		
			hm.put("pName", rs.getString(1));
			hm.put("spec", rs.getString(2));
			hm.put("color", rs.getString(3));
			hm.put("owner", rs.getString(4));
			hm.put("cost", rs.getDouble(5));
			
			
				
		
			
			}
			rs.close();
			
			
			String strSql2 = "Select warehousePosition1,warehousePosition2,qty from storage where SKU = ? and warehouse = ?";
			ps = conn.prepareStatement(strSql2);
			ps.setString(1, autoCompleteNumber);
			ps.setString(2, request.getParameter("warehouse"));
//			System.out.println("倉庫:"+request.getParameter("warehouse"));
			ResultSet rs2 = ps. executeQuery();
			if(rs2.next()){
				hm.put("warehousePosition", rs2.getString(1));
				hm.put("warehousePosition2", rs2.getString(2));	
				hm.put("qty", rs2.getString(3));
			}
			rs2.close();
			

			String strSql3 = "Select warehousePosition1,warehousePosition2 from storage where SKU = ? and warehouse = ?";
			ps = conn.prepareStatement(strSql3);
			ps.setString(1, autoCompleteNumber);
			ps.setString(2, request.getParameter("newWarehouse"));
//			System.out.println("倉庫:"+request.getParameter("warehouse"));
			ResultSet rs3 = ps. executeQuery();
			if(rs3.next()){
				hm.put("newWarehousePosition", rs3.getString(1));
				hm.put("newWarehousePosition2", rs3.getString(2));	
			}
			rs3.close();
			
			
			
			
			
//			System.out.println("skuName:\n"+autoCompleteNumber);
//			System.out.println("search:\n"+searchSku);
//			System.out.println("HashMap:\n"+hm);
			JSONObject responseJSONObject = new JSONObject(hm);
			PrintWriter out = response.getWriter();
//			System.out.println("strSql:\n"+strSql);

			out.println(responseJSONObject);
//			System.out.println("JSONObject:\n"+responseJSONObject);
			
		
			ps.close();
			conn.close();
		} catch (Exception e) {
			
		}
	}

}
