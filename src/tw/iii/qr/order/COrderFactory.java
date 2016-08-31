package tw.iii.qr.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tw.iii.qr.order.COrderDetail;
import tw.iii.qr.order.COrders;
import tw.iii.qr.stock.CProduct;


public class COrderFactory extends COrders {

	public COrderFactory() {
	}
	
	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}
	
	public boolean checkboxIsNullorEmpty(String a,String b,String c,String d) {

		if (isNullorEmpty(a)&&isNullorEmpty(b)&&isNullorEmpty(c)&&isNullorEmpty(d))
			return true;

		return false;
	}

	//言勳
	public LinkedList<COrders> orderProcessingPageSearch(HttpServletRequest request, Connection conn)
			throws SQLException {
		String a = request.getParameter("waitProcess");
		String b = request.getParameter("processing");
		String c = request.getParameter("pickup");
		String d = request.getParameter("shipped");

		String strSql = "SELECT distinct m.order_id, platform, guestAccount, orderDate, shippingDate,"
				+ " logistics, orderstatus, totalPrice, staffName, SKU, productName, m.comment, m.eBayAccount, m.payDate "
				+ " FROM quickreach.orders_master as m inner join quickreach.orders_detail as d"
				+ " using (order_id) where '1' = '1' ";

		if (!isNullorEmpty(request.getParameter("all"))) {
			strSql += "  and m.orderstatus = 1 or m.orderstatus is not null";
		} else if(!checkboxIsNullorEmpty(a, b, c, d)) {		
			strSql += " and m.orderstatus = 1 ";
			if (!isNullorEmpty(request.getParameter("waitProcess"))) {
				strSql += "  or m.orderstatus = ?";
			}
			if (!isNullorEmpty(request.getParameter("processing"))) {
				strSql += "  or m.orderstatus = ?";
			}
			if (!isNullorEmpty(request.getParameter("pickup"))) {
				strSql += "  or m.orderstatus = ?";
			}
			if (!isNullorEmpty(request.getParameter("shipped"))) {
				strSql += "  or m.orderstatus = ?";
			}
		} 
		///////
		if (!isNullorEmpty(request.getParameter("orderStatus"))) {
			strSql += " and orderStatus = ? ";
		}
		if (!isNullorEmpty(request.getParameter("productName"))) {
			strSql += " and productName like ? ";
		}
		if (!isNullorEmpty(request.getParameter("eBayAccount"))) {
			strSql += " and eBayAccount = ? ";
		}
		if (!isNullorEmpty(request.getParameter("guestAccount"))) {
			strSql += " and guestAccount like ? ";
		}
		if (!isNullorEmpty(request.getParameter("SKU"))) {
			strSql += " and SKU like ? ";
		}
		if (!isNullorEmpty(request.getParameter("payDateMin"))) {
			strSql += " and payDate >= ? ";
		}
		if (!isNullorEmpty(request.getParameter("payDateMax"))) {
			strSql += " and payDate <= ? ";
		}
		if (!isNullorEmpty(request.getParameter("shippingDateMin"))) {
			strSql += " and shippingDate >= ? ";
		}
		if (!isNullorEmpty(request.getParameter("shippingDateMax"))) {
			strSql += " and shippingDate <= ? ";
		}
		if (!isNullorEmpty(request.getParameter("logistics"))) {
			strSql += " and logistics = ? ";
		}
		if (!isNullorEmpty(request.getParameter("logisticsId"))) {
			strSql += " and logisticsId like ? ";
		}

		int param = 1;
		PreparedStatement ps = conn.prepareStatement(strSql);

		if (isNullorEmpty(request.getParameter("all"))) {

			if (!isNullorEmpty(request.getParameter("waitProcess"))) {
				ps.setString(param, "待處理");
				param++;
			}
			if (!isNullorEmpty(request.getParameter("processing"))) {
				ps.setString(param, "處理中");
				param++;
			}
			if (!isNullorEmpty(request.getParameter("pickup"))) {
				ps.setString(param, "揀貨中");
				param++;
			}
			if (!isNullorEmpty(request.getParameter("shipped"))) {
				ps.setString(param, "已出貨");
				param++;
			}
		}

		if (!isNullorEmpty(request.getParameter("orderStatus"))) {
			ps.setString(param, request.getParameter("orderStatus"));
			param++;
		}
		if (!isNullorEmpty(request.getParameter("productName"))) {
			ps.setString(param, request.getParameter("productName"));
			param++;
		}
		if (!isNullorEmpty(request.getParameter("eBayAccount"))) {
			ps.setString(param, request.getParameter("eBayAccount"));
			param++;
		}
		if (!isNullorEmpty(request.getParameter("guestAccount"))) {
			ps.setString(param, "%" + request.getParameter("guestAccount") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("SKU"))) {
			ps.setString(param, "%" + request.getParameter("SKU") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("payDateMin"))) {
			ps.setString(param, "%" + request.getParameter("payDateMin") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("payDateMax"))) {
			ps.setString(param, "%" + request.getParameter("payDateMax") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("shippingDateMin"))) {
			ps.setString(param, "%" + request.getParameter("shippingDateMin") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("shippingDateMax"))) {
			ps.setString(param, "%" + request.getParameter("shippingDateMax") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("shippingDateMin"))) {
			ps.setString(param, "%" + request.getParameter("shippingDateMin") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("logistics"))) {
			ps.setString(param, "%" + request.getParameter("logistics") + "%");
			param++;
		}
		if (!isNullorEmpty(request.getParameter("logisticsId"))) {
			ps.setString(param, "%" + request.getParameter("logisticsId") + "%");
			param++;
		}

		System.out.println(strSql);
		ResultSet rs = ps.executeQuery();
		LinkedList<COrders> orderList = new LinkedList<COrders>();
		COrders order = new COrders();
		while (rs.next()) {
			order = new COrders();
			order.COrderMaster.setOrder_id(rs.getString(1));
			order.COrderMaster.setPlatform(rs.getString(2));
			order.COrderMaster.setGuestAccount(rs.getString(3));
			order.COrderMaster.setOrderDate(rs.getDate(4));
			order.COrderMaster.setShippingDate(rs.getDate(5));
			order.COrderMaster.setLogistics(rs.getString(6));
			order.COrderMaster.setOrderStatus(rs.getString(7));
			order.COrderMaster.setTotalPrice(rs.getDouble(8));
			order.COrderMaster.setStaffName(rs.getString(9));
			order.COrderDetail.setSKU(rs.getString(10));
			order.COrderDetail.setProductName(rs.getString(11));
			order.COrderMaster.setComment(rs.getString(12));
			order.COrderMaster.setEbayAccount(rs.getString(13));
			order.COrderMaster.setPayDate(rs.getDate(14));
			System.out.println(order);
			orderList.add(order);
		}
		

		return orderList;

	}
	
	public LinkedList<COrders> orders(HttpServletRequest request, Connection conn, String status) throws SQLException {

		String strSql = "SELECT distinct m.order_id, platform, guestAccount, orderDate, shippingDate,"
				+ " logistics, orderstatus, totalPrice, staffName, SKU, productName, m.comment, m.eBayAccount, m.payDate "
				+ " FROM quickreach.orders_master as m inner join quickreach.orders_detail as d"
				+ " using (order_id) where '1' = '1' and m.orderstatus = ?";


		System.out.println(strSql);
		
		PreparedStatement ps = conn.prepareStatement(strSql);

		
	
			ps.setString(1, status);
		
	

		ResultSet rs = ps.executeQuery();
		LinkedList<COrders> orderList = new LinkedList<COrders>();
		COrders order = new COrders();
		while (rs.next()) {
			order = new COrders();
			order.COrderMaster.setOrder_id(rs.getString(1));
			order.COrderMaster.setPlatform(rs.getString(2));
			order.COrderMaster.setGuestAccount(rs.getString(3));
			order.COrderMaster.setOrderDate(rs.getDate(4));
			order.COrderMaster.setShippingDate(rs.getDate(5));
			order.COrderMaster.setLogistics(rs.getString(6));
			order.COrderMaster.setOrderStatus(rs.getString(7));
			order.COrderMaster.setTotalPrice(rs.getDouble(8));
			order.COrderMaster.setStaffName(rs.getString(9));
			order.COrderDetail.setSKU(rs.getString(10));
			order.COrderDetail.setProductName(rs.getString(11));
			order.COrderMaster.setComment(rs.getString(12));
			order.COrderMaster.setEbayAccount(rs.getString(13));
			order.COrderMaster.setPayDate(rs.getDate(14));
			System.out.println(order);
			orderList.add(order);
		}
		

		System.out.println(order.toString());

		return orderList;

	}
	//
	//嘉南
	public boolean checkOrderIdOrderStatus(HttpServletRequest request, Connection conn) throws Exception{
		String strSql = "select order_id, orderStatus from quickreach.orders_master";
		PreparedStatement ps = conn.prepareStatement(strSql);
		System.out.println(strSql);
		ResultSet rs = ps.executeQuery();
		
		LinkedList<COrders> orderList = new LinkedList<COrders>();
		COrders order = new COrders();
		while(rs.next()){
			order = new COrders();
			order.COrderMaster.setOrder_id(rs.getString(1));
			order.COrderMaster.setOrderStatus(rs.getString(2));
			orderList.add(order);
		}
		
		
		for(int i=0; i<orderList.size(); i++)
		{
			if(request.getParameter("orderId").equals(orderList.get(i).getCOrderMaster().getOrder_id().toString())){
				System.out.println("orderID:true");
				if(orderList.get(i).getCOrderMaster().getOrderStatus().toString().equals("已出貨"))
					return true;
			}
		}
		System.out.println("orderId is invalid or wrong order status.");
		return false;
	}
	
	public void updateToFinished(HttpServletRequest request, Connection conn) throws Exception{
			String strSql = "update quickreach.orders_master"
					+ " set orderStatus = '已完成' "
					+ " where order_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, request.getParameter("orderId"));
			ps.executeUpdate();
	}

	public void deductStock(HttpServletRequest request, Connection conn) throws Exception{

		LinkedList<COrderDetail> condition = getCondition(request, conn);
		for(int i=0; i<condition.size(); i++){
			String strSql = "update quickreach.storage"
					+ " set qty = ? "
					+ " where sku = ? and warehouse = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setInt(1, condition.get(i).getQty());
			ps.setString(2, condition.get(i).getSKU());
			ps.setString(3, condition.get(i).getWarehouse());
			ps.executeUpdate();
		}
	}
	
	public LinkedList<COrderDetail> getCondition(HttpServletRequest request, Connection conn) throws Exception{
		
		LinkedList<COrderDetail> condition = getSkuAndWarehouse(request, conn);
		LinkedList<COrderDetail> result = new LinkedList<COrderDetail>();
		for (int i=0; i<condition.size(); i++){
			String strSql = "select distinct s.SKU, s.qty, d.qty, s.warehouse"
					+ " from quickreach.storage as s inner join quickreach.orders_detail as d"
					+ " using (SKU) where order_id = ? and sku = ? and s.warehouse = ?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			
			ps.setString(1, request.getParameter("orderId"));
			ps.setString(2, condition.get(i).getSKU());
			ps.setString(3, condition.get(i).getWarehouse());
			System.out.println(strSql);
			ResultSet rs = ps.executeQuery();
			COrderDetail detail = new COrderDetail();
			while(rs.next()){
				detail.setSKU(rs.getString(1));
				detail.setQty(rs.getInt(2)-rs.getInt(3));
				detail.setWarehouse(rs.getString(4));
				result.add(detail);
			}
		}
		System.out.println(result);
		System.out.println(result);
		System.out.println(result.get(0).toString());
		return result;
	}
	
	public LinkedList<COrderDetail> getSkuAndWarehouse(HttpServletRequest request, Connection conn) throws Exception{
		
		String strSql = "select SKU, warehouse"
				+ " from quickreach.orders_master as m inner join quickreach.orders_detail as d"
				+ " using (order_id) where order_id = ?";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("orderId"));
		ResultSet rs = ps.executeQuery();
		LinkedList<COrderDetail> result = new LinkedList<COrderDetail>();
		COrderDetail detail = new COrderDetail();
		while(rs.next()){
			detail = new COrderDetail();
			detail.setSKU(rs.getString(1));
			detail.setWarehouse(rs.getString(2));
			result.add(detail);
		}
		return result;
		
	}
	
	public void insertIntoShippingLog(HttpServletRequest request, Connection conn) throws SQLException{
		
		String strSql = "insert into shippinglog (order_id, date, trackingCode, staffName)"
				+ " values( ?, now(), ?, ?)";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("orderId"));
		ps.setString(2, request.getParameter("trackingCode"));
		ps.setString(3, "system");
		
		ps.executeUpdate();
	}
	//

}
