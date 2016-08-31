package tw.iii.purchase;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.iii.qr.DataBaseConn;

import org.json.JSONObject;

@WebServlet("/AutoCompleteServlet")
public class AutoCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			try {
				process(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		
		HashMap<String, String> hm = new HashMap<String, String>();		
	
		try {
			DataBaseConn jdbc = new DataBaseConn();
			conn = jdbc.getConn();			
			
			String strSql = "SELECT  a.P_name, a.spec, a.color, b.warehousePosition1, b.warehousePosition2 FROM quickreach.product as a inner join quickreach.storage as b where a.SKU = b.SKU and a.SKU = ?;";

			
			ps = conn.prepareStatement(strSql);
			
			ps.setString(1, autoCompleteNumber);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
		
			hm.put("pName", rs.getString(1));
			hm.put("spec", rs.getString(2));
			hm.put("color", rs.getString(3));
				
			hm.put("warehousePosition", rs.getString(4)+"-"+rs.getString(5));
	
			
			}
			
			System.out.println("skuName:\n"+autoCompleteNumber);
			System.out.println("search:\n"+searchSku);
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
