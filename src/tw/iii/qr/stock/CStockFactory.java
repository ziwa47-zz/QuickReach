package tw.iii.qr.stock;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.stock.DTO.CStock;

public class CStockFactory extends CStock {
	public CStockFactory() {
	}

	public LinkedList<CStock> searchDetailStock(String sku,Connection conn) throws SQLException {

		String strsql = "select warehouse,warehousePosition1,warehousePosition2,qty,(select isnull(sum(qty),0) as 待處理量  from orders_detail as d inner join orders_master as m on d.Qr_id = m.Qr_id  where sku = ?  and (m.orderStatus = N'待處理' or m.orderStatus = N'處理中' or m.orderStatus = N'揀貨中' or m.orderStatus = N'已出貨')),purchaseDate,comment,isnull( company,'') from  storage where sku = ? order by purchaseDate desc ";
		//	String strsql = "select warehouse,warehousePosition,qty,3,qty-3,purchaseDate,comment from  storage where sku = ? ";
			PreparedStatement ps = null;

			ps = conn.prepareStatement(strsql);

			ps.setString(1, sku);
			ps.setString(2, sku);

			ResultSet rs = ps.executeQuery();

			LinkedList<CStock> stockmaster = new LinkedList<>();
			CStock stockDetail = new CStock();

			while (rs.next()) {
				stockDetail = new CStock();
				stockDetail.setWareHouse(rs.getString(1)); // warehouse
				stockDetail.setPosition1(rs.getString(2)); // warehousePosition
				stockDetail.setPosition2(rs.getString(3)); // warehousePosition
				stockDetail.setQty(rs.getInt(4)); // qty
				stockDetail.setQtysold(rs.getInt(5)); // sould be 待處理庫存
				stockDetail.setLastpurchasedate(rs.getDate(6)); // purchasedate
				stockDetail.setComment(rs.getString(7)); // Comment
				stockDetail.setCompany(rs.getString(8)); // Company
				stockmaster.add(stockDetail);
			}
			return stockmaster;

		}
	public LinkedList<CStock> searchStorage(HttpServletRequest request, Connection conn) throws SQLException {
		String strsql = " select distinct p.sku,p.brand,p.subbrand,p.p_name,p.spec,p.color, isnull(sum(qty),0),isnull(p.securedQty,0) as AllStock from  product p left join  storage s on p.sku=s.sku group by p.sku,p.brand,p.subbrand,p.p_name,p.spec,p.color,p.createDate,p.securedQty having '1' = '1'  ";
		int param = 1;

		PreparedStatement ps = null;
		if (!isNullorEmpty(request.getParameter("pname"))) {
			strsql += " and p.p_name like ? ";
		}
		if (!isNullorEmpty(request.getParameter("brand"))) {
			strsql += " and p.brand like ? ";
		}
		if (!isNullorEmpty(request.getParameter("subbrand"))) {
			strsql += " and p.subbrand like ? ";
		}
		if (!isNullorEmpty(request.getParameter("sku"))) {
			strsql += " and p.sku like ? ";
		}
		if (!isNullorEmpty(request.getParameter("spec"))) {
			strsql += " and p.spec like ? ";
		}
		if (!isNullorEmpty(request.getParameter("color"))) {
			strsql += " and p.color like ? ";
		}
		if (!isNullorEmpty(request.getParameter("date1"))) {
			strsql += " and p.createDate  >= ? ";
		}
		if (!isNullorEmpty(request.getParameter("date2"))) {
			strsql += " and p.createDate  <= ? ";
		}
//		if (!isNullorEmpty(request.getParameter("location1")) && !isNullorEmpty(request.getParameter("location2"))) {
//			strsql += " and warehouseposition  <= ? ";
//			strsql += " and warehouseposition  >= ? ";
//		}
		System.out.println(strsql);
		ps = conn.prepareStatement(strsql);
		if (!isNullorEmpty(request.getParameter("pname"))) {
			ps.setString(param, "%"+request.getParameter("pname")+"%");
			param++;

		}
		if (!isNullorEmpty(request.getParameter("brand"))) {
			ps.setString(param, "%"+request.getParameter("brand")+"%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("subbrand"))) {
			ps.setString(param, "%"+request.getParameter("subbrand")+"%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("sku"))) {
			ps.setString(param, "%"+request.getParameter("sku")+"%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("spec"))) {
			ps.setString(param, "%"+request.getParameter("spec")+"%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("color"))) {
			ps.setString(param, "%"+request.getParameter("color")+"%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("date1"))) {
			ps.setString(param, request.getParameter("date1"));
			param++;
		}
		if (!isNullorEmpty(request.getParameter("date2"))) {
			ps.setString(param, request.getParameter("date2"));
			param++;
		}
//		if (!isNullorEmpty(request.getParameter("location1")) && !isNullorEmpty(request.getParameter("location2"))) {
//			ps.setString(param, request.getParameter("location1"));
//			param++;
//			ps.setString(param, request.getParameter("location2"));
//			param++;
//		}
		ResultSet rs = ps.executeQuery();
		LinkedList<CStock> storageall = new LinkedList<CStock>();
		CStock storage = new CStock();
		int no = 1;
		while (rs.next()) {
			// sku,brand,subbrand,p_name,spec,color
			storage = new CStock();
			storage.setItem(String.valueOf(no++));
			storage.setSKU(rs.getString(1));// sku
			storage.setBrand(rs.getString(2));// brand
			storage.setSubBrand(rs.getString(3));// subbrand
			storage.setP_name(rs.getString(4));// p_name
			storage.setSpec(rs.getString(5));// spec
			storage.setColor(rs.getString(6));// color
			storage.setAllStock(rs.getInt(7));// allstock
			storage.setSecuredQty(rs.getInt(8));//SecuredQty
			storageall.add(storage);
			
		}

		return storageall;
	}
	public JSONArray iosSearchSecuredno(){
		JSONObject jo = null;
		JSONArray ja = new JSONArray();
		try{
		Connection conn = new DataBaseConn().getConn();
		
		String strsql = "select p.sku,p.securedQty,sum(qty) as sum1 from product as p inner join storage as s on p.sku=s.sku group by p.sku,p.p_name,p.spec,p.securedQty having p.securedQty >sum(qty) ";
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		HashMap<String, String> hm = new HashMap<String, String>();
		
		while(rs.next()){
			hm= new HashMap<String, String>();
			
			
			hm.put("Sku", rs.getString(1));
		
			hm.put("SecureNo",String.valueOf(rs.getInt(2)));
			hm.put("Storage", String.valueOf(rs.getInt(3)));
			
			jo = new JSONObject(hm);
			ja.put(jo);
			
		}
		
		rs.close();
		
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return ja;
		
	}
	
	public JSONArray iosSearchproductstock(HttpServletRequest request){
		JSONObject jo = null;
		JSONArray ja = new JSONArray();
		try{
		Connection conn = new DataBaseConn().getConn();
		
		String strsql = "select p.sku,p.securedQty,sum(qty) as sum1 from product as p inner join storage as s on p.sku=s.sku group by p.sku,p.p_name,p.spec,p.securedQty having p.sku like ?";
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ps.setString(1, "%"+request.getParameter("q")+"%");
		ResultSet rs = ps.executeQuery();
		HashMap<String, String> hm = new HashMap<String, String>();
		
		while(rs.next()){
			hm= new HashMap<String, String>();
	
			hm.put("Sku", rs.getString(1));			
			hm.put("SecureNo",String.valueOf(rs.getInt(2)));
			hm.put("Storage", String.valueOf(rs.getInt(3)));
			
			jo = new JSONObject(hm);
			ja.put(jo);
			
		}
		
		rs.close();
		
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return ja;
		
	}
	
public JSONArray iosstockDetail(HttpServletRequest request) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		HashMap<String, String> hm = new HashMap<String, String>();
		JSONArray ja = new JSONArray();
		Connection conn = new DataBaseConn().getConn();
		
		String strSql = "select p.sku,p.p_name,p.brand,p.subbrand,p.spec,p.color,s.warehouse,s.warehousePosition1,s.warehousePosition2,s.qty,(select isnull(sum(qty),0) as 待處理量  from orders_detail as d inner join orders_master as m on d.Qr_id = m.Qr_id  where sku = ? and (m.orderStatus = N'待處理' or m.orderStatus = N'處理中' or m.orderStatus = N'揀貨中')),company,isnull(picturePath,'') from  storage s inner join product p on p.sku = s.sku where p.sku = ?";
				
		
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("stock"));
		ps.setString(2, request.getParameter("stock"));
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			
			hm.put("sku", rs.getString(1));
			hm.put("pname", rs.getString(2));
			hm.put("brand", rs.getString(3));
			hm.put("subbrand", rs.getString(4));
			hm.put("spec", rs.getString(5));
			hm.put("color", rs.getString(6));
			hm.put("warehouse", rs.getString(7));
			hm.put("warehouse1", rs.getString(8));
			hm.put("warehouse2", rs.getString(9));
			hm.put("stock", rs.getString(10));
			hm.put("wait", rs.getString(11));
			hm.put("company", rs.getString(12));
			hm.put("pic", rs.getString(13));
			JSONObject jo = new JSONObject(hm);
			ja.put(jo);
		}
		
		rs.close();
		ps.close();
		conn.close();
		return ja;
	}
	
	public boolean isNullorEmpty(String s) {
		
			if (s == null || s.length()==0) 
				return true;
			
		return false;
	}
	public LinkedList<String> getwarehouseforcounting() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		LinkedList<String> ware = new LinkedList<String>();
		Connection con = new DataBaseConn().getConn();
		String strsql ="select warehousename from warehouse";
		PreparedStatement ps = con.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			ware.add(rs.getString(1));
			System.out.println(rs.getString(1));
		}
		rs.close();
		ps.close();
		con.close();
		return ware;
		
		
	}

	public LinkedList<CStock> searchforcounting(HttpServletRequest request, Connection conn) throws SQLException {
		LinkedList<CStock> counting =  new LinkedList<>();
		
		String strsql= "select s.sku,s.warehousePosition1 ,s.warehousePosition2 ,p.P_name,p.spec,p.color,s.qty from product p left join storage s on p.sku = s.sku inner join warehouse w on s.warehouse = w.warehouse where 1=1 and w.warehousename = ? and s.sku like ?"	;
		
		
		PreparedStatement ps = null;

			ps = conn.prepareStatement(strsql);
			ps.setString(1, request.getParameter("warehousename"));
			ps.setString(2, "%"+request.getParameter("sku")+"%");
			System.out.println(request.getParameter("warehousename"));
			System.out.println(request.getParameter("sku"));
			ResultSet rs = ps.executeQuery();

			CStock count = new CStock();

			while (rs.next()) {
				count = new CStock();
				count.setSKU(rs.getString(1));// sku
				count.setPosition1(rs.getString(2)); // warehousePosition1
				count.setPosition2(rs.getString(3)); // warehousePosition1
				count.setP_name(rs.getString(4)); // pname
				count.setSpec(rs.getString(5)); // spec
				count.setColor(rs.getString(6)); // color
				count.setQty(rs.getInt(7)); // qty
				counting.add(count);
				System.out.println(rs.getString(1));
			}
		
		
		return counting;
	}

	public void modifyStorage(int count,String comment, String sku , String warehouse)throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		DataBaseConn dbc = new DataBaseConn();
		Connection conn = dbc.getConn() ;
		

		String sqlstr = "UPDATE storage  SET qty = qty+? ,comment = ? ,purchaseDate = getdate() WHERE SKU= ? and warehouse = ? ";

		PreparedStatement preparedState = conn.prepareStatement(sqlstr);		

		preparedState.setInt(1, count);
		preparedState.setString(2, comment);
		preparedState.setString(3, sku);
		preparedState.setString(4, warehouse);
		
		preparedState.execute();
		preparedState.close();
		
		conn.close();

	}
	public void modifyStorageLogMaster(String stockTransferId,String comment, String staffName , String oldWarehouse)throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		DataBaseConn dbc = new DataBaseConn();
		Connection conn = dbc.getConn() ;
		

		String sqlstr = "INSERT INTO stockTransferlog_master (stockTransferId,date,staffName,stockStatus,oldWarehouse,comment) VALUES (?,getdate(),?,'4',?,?)";
		PreparedStatement preparedState = conn.prepareStatement(sqlstr);		

		preparedState.setString(1, stockTransferId);
		preparedState.setString(2, staffName);
		preparedState.setString(3, oldWarehouse);
		preparedState.setString(4, comment);
		
		preparedState.execute();
		preparedState.close();
		
		conn.close();

	}
	
	public void modifyStorageLogDetail(String stockTransferId,String comment, String SKU , String oldWarehouse ,int qty)throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		DataBaseConn dbc = new DataBaseConn();
		Connection conn = dbc.getConn() ;
		

		String sqlstr = "INSERT INTO stockTransferlog_detail (stockTransferId,SKU,stockStatus,oldWarehouse,comment,qty) VALUES (?,?,'4',?,?,?)";
		PreparedStatement preparedState = conn.prepareStatement(sqlstr);		

		preparedState.setString(1, stockTransferId);
		preparedState.setString(2, SKU);
		preparedState.setString(3, oldWarehouse);
		preparedState.setString(4, comment);
		preparedState.setInt(5, qty);
		
		preparedState.execute();
		preparedState.close();
		
		conn.close();

	}
}
