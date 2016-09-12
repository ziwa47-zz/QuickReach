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

import org.json.JSONObject;


import tw.iii.qr.DataBaseConn;

@WebServlet(name = "JQueryAutoCompleteSKUData", urlPatterns = { "/JQueryAutoCompleteSKUData" })
public class JQueryAutoCompleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			processSKU(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			processSKU(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void processSKU(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PreparedStatement ps = null;
		
		String searchSKU = request.getParameter("term");
		System.out.println("term:"+searchSKU);
		
		HashMap<String, String> hm = new HashMap<String, String>();
	
		try {
			DataBaseConn jdbc = new DataBaseConn();
			conn = jdbc.getConn();			
			
			String strSql = "SELECT distinct SKU FROM  product where SKU like ?";
			
			ps = conn.prepareStatement(strSql);
			ps.setString(1, "%"+searchSKU+"%");
			
			ResultSet rs = ps.executeQuery();
			int i = 1;
			while(rs.next()){
				hm.put(String.valueOf(i), rs.getString(1));	
				
				i++;			
			}
			
		
			
			
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


