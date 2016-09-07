package tw.iii.qr.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

public class CStockFactory extends CStock {
	public CStockFactory() {
	}
	public LinkedList<CStock> searchDetailStock(String sku,Connection conn) throws SQLException {

	String strsql = "select warehouse,warehousePosition1,warehousePosition2,qty,3,qty-3,purchaseDate,comment from  storage where sku = ? ";
	//	String strsql = "select warehouse,warehousePosition,qty,3,qty-3,purchaseDate,comment from  storage where sku = ? ";
		PreparedStatement ps = null;

		ps = conn.prepareStatement(strsql);

		ps.setString(1, sku);

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
			stockDetail.setQtyremain(rs.getInt(6)); // qty
			stockDetail.setLastpurchasedate(rs.getDate(7)); // purchasedate
			stockDetail.setComment(rs.getString(8)); // comment
			stockmaster.add(stockDetail);
		}
		return stockmaster;

	}
	public LinkedList<CStock> searchDetailStock2(String sku,Connection conn) throws SQLException {

		String strsql = "select warehouse,warehousePosition1,warehousePosition2,qty,3,qty-3,purchaseDate,comment from  storage where sku = ? ";
		//	String strsql = "select warehouse,warehousePosition,qty,3,qty-3,purchaseDate,comment from  storage where sku = ? ";
			PreparedStatement ps = null;

			ps = conn.prepareStatement(strsql);

			ps.setString(1, sku);

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
				stockDetail.setQtyremain(rs.getInt(6)); // qty
				stockDetail.setLastpurchasedate(rs.getDate(7)); // purchasedate
				stockDetail.setComment(rs.getString(8)); // comment
				stockmaster.add(stockDetail);
			}
			return stockmaster;

		}
	public LinkedList<CStock> searchStorage(HttpServletRequest request, Connection conn) throws SQLException {
		String strsql = " select distinct sku,brand,subbrand,p_name,spec,color from  product inner join  storage using (sku) where '1' = '1' ";
		int param = 1;

		PreparedStatement ps = null;
		if (!isNullorEmpty(request.getParameter("pname"))) {
			strsql += " and p_name like ? ";
		}
		if (!isNullorEmpty(request.getParameter("brand"))) {
			strsql += " and brand like ? ";
		}
		if (!isNullorEmpty(request.getParameter("subbrand"))) {
			strsql += " and subbrand like ? ";
		}
		if (!isNullorEmpty(request.getParameter("sku"))) {
			strsql += " and sku like ? ";
		}
		if (!isNullorEmpty(request.getParameter("spec"))) {
			strsql += " and spec like ? ";
		}
		if (!isNullorEmpty(request.getParameter("color"))) {
			strsql += " and color like ? ";
		}
		if (!isNullorEmpty(request.getParameter("date1"))) {
			strsql += " and createDate  >= ? ";
		}
		if (!isNullorEmpty(request.getParameter("date2"))) {
			strsql += " and createDate  <= ? ";
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
			storageall.add(storage);
			
		}

		return storageall;
	}

	public boolean isNullorEmpty(String s) {
		
			if (s == null || s.length()==0) 
				return true;
			
		return false;
	}

}
