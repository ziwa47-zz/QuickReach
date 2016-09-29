package tw.iii.qr.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import tw.iii.qr.DataBaseConn;

public class getJSON {

	public getJSON() {

	}

	public JSONArray pickupResults() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		JSONArray ja = new JSONArray();
		Connection conn = new DataBaseConn().getConn();

		String strSql = "select d.QR_id from orders_detail as d inner join orders_master as m on m.QR_id = d.QR_id"
				+ " where m.orderStatus= N'揀貨中'";

		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			hm.put("QR_id", rs.getString(1));
			JSONObject jo = new JSONObject(hm);
			ja.put(jo);
		}
		return ja;
	}

	public JSONArray pickupDetail(HttpServletRequest request)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		HashMap<String, String> hm = new HashMap<String, String>();
		JSONArray ja = new JSONArray();
		Connection conn = new DataBaseConn().getConn();

		String strSql = "select QR_id, d.SKU, brand, subBrand, productName, s.warehouse, warehousePosition1, warehousePosition2, d.qty,isnull(p.picturePath,'')"
				+ " from orders_detail as d   inner join storage as s on d.SKU = s.SKU"
				+ " inner join product as p on d.SKU = p.SKU  where d.QR_id = ? and s.warehouse =  ("
				+ " select warehouse  from orders_detail  where QR_id = ?)";

		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("QR_id"));
		ps.setString(2, request.getParameter("QR_id"));
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			hm.put("QR_id", rs.getString(1));
			hm.put("SKU", rs.getString(2));
			hm.put("brand", rs.getString(3));
			hm.put("subbrand", rs.getString(4));
			hm.put("productName", rs.getString(5));
			hm.put("warehouse", rs.getString(6));
			hm.put("warehousePosition1", rs.getString(7));
			hm.put("warehousePosition2", rs.getString(8));
			hm.put("Qty", rs.getString(9));
			hm.put("pic", rs.getString(10));
			JSONObject jo = new JSONObject(hm);
			ja.put(jo);
		}
		conn.close();
		return ja;
	}

	public JSONArray searchalready(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		
		HashMap<String, String> hm = new HashMap<String, String>();
		JSONArray ja = new JSONArray();
		String strSql;
		

		strSql = "SELECT distinct  logistics, orderstatus, totalPrice, m.eBayAccount, m.QR_id, m.ebayItemNO "
				+" FROM  orders_master as m inner join  orders_detail as d on m.QR_id = d.QR_id "
				+" where '1' = '1' and orderstatus = N'已出貨' order by QR_id desc";

		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			hm.put("logistics", rs.getString(1));
			hm.put("orderstatus", rs.getString(2));
			hm.put("totalPrice", rs.getString(3));
			hm.put("eBayAccount", rs.getString(4));
			hm.put("QR_id", rs.getString(5));
			hm.put("ebayItemNO", rs.getString(6));
		
			JSONObject jo = new JSONObject(hm);
			ja.put(jo);
		}
		conn.close();
		return ja;
	
		
	}
	public void updateToFinished(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		
		HashMap<String, String> hm = new HashMap<String, String>();
		JSONArray ja = new JSONArray();
		String strSql;
		
		
		

		
	
		
	}

}
