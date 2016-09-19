package tw.iii.qr.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import tw.iii.qr.DataBaseConn;

public class getJSON {

	public getJSON(){
		
	}
	
	public JSONArray pickupResults() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		HashMap<String, String> hm = new HashMap<String, String>();
		JSONArray ja = new JSONArray();
		Connection conn = new DataBaseConn().getConn();
		
		String strSql = "select d.QR_id, SKU, qty from orders_detail as d inner join orders_master as m on m.QR_id = d.QR_id"
				+ " where m.orderStatus= N'揀貨中'";
		
		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			hm.put("pName", rs.getString(1));
			hm.put("spec", rs.getString(2));
			hm.put("cosonlor", rs.getString(3));
			JSONObject jo = new JSONObject(hm);
			ja.put(jo);
		}
		
		return ja;
		
	}
}
