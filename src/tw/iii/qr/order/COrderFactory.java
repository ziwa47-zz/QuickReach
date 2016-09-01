package tw.iii.qr.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.COrderDetail;
import tw.iii.qr.order.COrders;
import tw.iii.qr.stock.CProduct;

//orderdate paydate shippingdate schema confirm
public class COrderFactory extends COrders {

	public COrderFactory() {
	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public boolean checkboxIsNullorEmpty(String a, String b, String c, String d) {

		if (isNullorEmpty(a) && isNullorEmpty(b) && isNullorEmpty(c) && isNullorEmpty(d))
			return true;

		return false;
	}

	public boolean checkboxAreUnchecked(String a, String b, String c, String d, String e, String f, String g, String h,
			String i, String j, String k) {
		if (isNullorEmpty(a) && isNullorEmpty(b) && isNullorEmpty(c) && isNullorEmpty(d) && isNullorEmpty(e)
				&& isNullorEmpty(f) && isNullorEmpty(h) && isNullorEmpty(i) && isNullorEmpty(j) && isNullorEmpty(k))
			return true;

		return false;
	}
	public LinkedList<COrders> orderProcessingPageSearch(HttpServletRequest request, Connection conn)
			throws SQLException {

		String strSql = "SELECT distinct m.order_id, platform, m.guestAccount, orderDate, shippingDate,"
				+ " logistics, orderstatus, totalPrice, staffName, m.comment, m.eBayAccount, m.payDate "
				+ " FROM quickreach.orders_master as m inner join quickreach.orders_detail as d using (order_id)"
				+ " inner join quickreach.orders_guestinfo as g using (order_id)" + " where '1' = '1' ";

		String eBayAccount = request.getParameter("eBayAccount");
		if (!isNullorEmpty(eBayAccount)) {
			strSql += " and eBayAccount = ? ";
		}
		String ebayNO = request.getParameter("ebayNO");
		if (!isNullorEmpty(ebayNO)) {
			strSql += " and eBayAccount like ? ";
		}
		String paypal_id = request.getParameter("paypal_id");
		if (!isNullorEmpty(paypal_id)) {
			strSql += " and paypal_id like ? ";
		}
		String QR_id = request.getParameter("QR_id");
		if (!isNullorEmpty(QR_id)) {
			strSql += " and QR_id like ? ";	
		}
		String guestAccount = request.getParameter("guestAccount");
		if (!isNullorEmpty(guestAccount)) {
			strSql += " and m.guestAccount like ? ";
		}
		String trackingCode = request.getParameter("trackingCode");
		if (!isNullorEmpty(trackingCode)) {
			strSql += " and trackingCode like ? ";
		}
		String guestLastName = request.getParameter("guestLastName");
		if (!isNullorEmpty(guestLastName)) {
			strSql += " and guestLastName like ? ";
		}
		String SKU = request.getParameter("SKU");
		if (!isNullorEmpty(SKU)) {
			strSql += " and SKU like ? ";
		}
		String payDateMin = request.getParameter("payDateMin");
		if (!isNullorEmpty(payDateMin)) {
			strSql += " and payDate >= ? ";
		}
		String payDateMax = request.getParameter("payDateMax");
		if (!isNullorEmpty(payDateMax)) {
			strSql += " and payDate <= ? ";
		}
		String productName = request.getParameter("productName");
		if (!isNullorEmpty(productName)) {
			strSql += " and productName like ? ";
		}
		String shippingDateMin = request.getParameter("shippingDateMin");
		if (!isNullorEmpty(shippingDateMin)) {
			strSql += " and payDate >= ? ";
		}
		String shippingDateMax = request.getParameter("shippingDateMax");
		if (!isNullorEmpty(shippingDateMax)) {
			strSql += " and payDate <= ? ";
		}

		String waitProcess = request.getParameter("waitProcess");
		String processing = request.getParameter("processing");
		String pickup = request.getParameter("pickup");
		String finished = request.getParameter("finished");
		String refund = request.getParameter("refund");
		String others = request.getParameter("oothers");
		String deducted = request.getParameter("deducted");

		if (!checkboxAreUnchecked(waitProcess, processing, pickup, finished, refund, others, deducted, null, null, null,
				null)) {
			strSql += " and ( orderStatus is null ";
		} else {
			strSql += " and ( '1' = '1' ";
		}

		if (!isNullorEmpty(waitProcess)) {
			strSql += " or orderStatus = '待處理' ";
		}
		if (!isNullorEmpty(processing)) {
			strSql += " or orderStatus = '處理中' ";
		}
		if (!isNullorEmpty(pickup)) {
			strSql += " or orderStatus = '揀貨中' ";
		}
		if (!isNullorEmpty(finished)) {
			strSql += " or orderStatus = '已完成' ";
		}
		if (!isNullorEmpty(refund)) {
			strSql += " or orderStatus = '退款' ";
		}
		if (!isNullorEmpty(others)) {
			strSql += " or orderStatus = '其他' ";
		}
		if (!isNullorEmpty(deducted)) {
			strSql += " or orderStatus = '退貨中' ";
		}

		String DHL = request.getParameter("DHL");
		String EMS = request.getParameter("EMS");
		String Fedex = request.getParameter("Fedex");
		String AP = request.getParameter("AP");
		String RA = request.getParameter("RA");
		String USPS1 = request.getParameter("USPS1");
		String USPS2 = request.getParameter("USPS2");
		String seven = request.getParameter("seven");
		String familyMart = request.getParameter("familyMart");
		String post = request.getParameter("post");
		String lothers = request.getParameter("lothers");

		if (!checkboxAreUnchecked(DHL, EMS, Fedex, AP, RA, USPS1, USPS2, seven, familyMart, post, lothers)) {
			strSql += ") and ( logistics is null ";
		} else {
			strSql += ") and ( '1' = '1' ";
		}

		if (!isNullorEmpty(DHL)) {
			strSql += " or logistics = 'DHL' ";
		}
		if (!isNullorEmpty(Fedex)) {
			strSql += " or logistics = 'Fedex' ";
		}
		if (!isNullorEmpty(EMS)) {
			strSql += " or logistics = 'EMS' ";
		}
		if (!isNullorEmpty(AP)) {
			strSql += " or logistics = 'AP' ";
		}
		if (!isNullorEmpty(RA)) {
			strSql += " or logistics = 'RA' ";
		}
		if (!isNullorEmpty(USPS1)) {
			strSql += " or logistics = 'USPS1' ";
		}
		if (!isNullorEmpty(USPS2)) {
			strSql += " or logistics = 'USPS2' ";
		}
		if (!isNullorEmpty(seven)) {
			strSql += " or logistics = 'seven' ";
		}
		if (!isNullorEmpty(familyMart)) {
			strSql += " or logistics = 'familyMart' ";
		}
		if (!isNullorEmpty(post)) {
			strSql += " or logistics = 'post' ";
		}
		if (!isNullorEmpty(lothers)) {
			strSql += " or logistics = 'lothers' ";
		}
		strSql += ")";

		int param = 1;
		PreparedStatement ps = conn.prepareStatement(strSql);

		if (!isNullorEmpty(eBayAccount)) {
			ps.setString(param, eBayAccount);
			param++;
		}
		if (!isNullorEmpty(ebayNO)) {
			ps.setString(param, "%" + ebayNO + "%");
			param++;
		}
		if (!isNullorEmpty(paypal_id)) {
			ps.setString(param, "%" + paypal_id + "%");
			param++;
		}
		if (!isNullorEmpty(QR_id)) {
			ps.setString(param, "%" + QR_id + "%");
			param++;
		}
		if (!isNullorEmpty(guestAccount)) {
			ps.setString(param, "%" + guestAccount + "%");
			param++;
		}
		if (!isNullorEmpty(trackingCode)) {
			ps.setString(param, "%" + trackingCode + "%");
			param++;
		}
		if (!isNullorEmpty(guestLastName)) {
			ps.setString(param, "%" + guestLastName + "%");
			param++;
		}
		if (!isNullorEmpty(SKU)) {
			ps.setString(param, "%" + SKU + "%");
			param++;
		}
		if (!isNullorEmpty(payDateMin)) {
			ps.setString(param, "%" + payDateMin + "%");
			param++;
		}
		if (!isNullorEmpty(payDateMax)) {
			ps.setString(param, "%" + payDateMax + "%");
			param++;
		}
		if (!isNullorEmpty(productName)) {
			ps.setString(param, "%" + productName + "%");
			param++;
		}
		if (!isNullorEmpty(shippingDateMin)) {
			ps.setString(param, "%" + shippingDateMin + "%");
			param++;
		}
		if (!isNullorEmpty(shippingDateMax)) {
			ps.setString(param, "%" + shippingDateMax + "%");
			param++;
		}

		System.out.println(strSql);
		ResultSet rs = ps.executeQuery();
		LinkedList<COrders> orderList = new LinkedList<COrders>();
		LinkedList<COrderDetail> orderDetails = new LinkedList<COrderDetail>();
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

			String strSql2 = "SELECT SKU, productName"
					+ " FROM quickreach.orders_detail"
					+ " where order_id = ?";

			PreparedStatement ps2 = conn.prepareStatement(strSql2);
			ps2.setString(1, rs.getString(1));
			System.out.println(strSql2);
			ResultSet rs2 = ps2.executeQuery();
			orderDetails = new LinkedList<>();
			while(rs2.next()){
				COrderDetail COrderDetail = new COrderDetail();
				COrderDetail.setSKU(rs2.getString(1));
				COrderDetail.setProductName(rs2.getString(2));
				orderDetails.add(COrderDetail);
			}
			
			order.setCOrderDetail(orderDetails);
			order.COrderMaster.setComment(rs.getString(10));
			order.COrderMaster.setEbayAccount(rs.getString(11));
			order.COrderMaster.setPayDate(rs.getDate(12));
			//System.out.println(order);
			orderList.add(order);
		}

		return orderList;
	}

	public LinkedList<COrders> orders(HttpServletRequest request, Connection conn, String status) throws SQLException {

		String strSql = "SELECT distinct order_id, platform, guestAccount, orderDate, shippingDate,"
				+ " logistics, orderstatus, totalPrice, staffName, comment, eBayAccount, payDate "
				+ " FROM quickreach.orders_master "
				+ " where '1' = '1' and orderstatus = ?";

		System.out.println(status);
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, status);
		System.out.println(strSql);

		ResultSet rs = ps.executeQuery();
		LinkedList<COrders> orderList = new LinkedList<COrders>();
		LinkedList<COrderDetail> orderDetails = new LinkedList<COrderDetail>();
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

			String strSql2 = "SELECT SKU, productName"
					+ " FROM quickreach.orders_detail"
					+ " where order_id = ?";

			PreparedStatement ps2 = conn.prepareStatement(strSql2);
			ps2.setString(1, rs.getString(1));
			System.out.println(strSql2);
			ResultSet rs2 = ps2.executeQuery();
			orderDetails = new LinkedList<>();
			while(rs2.next()){
				COrderDetail COrderDetail = new COrderDetail();
				COrderDetail.setSKU(rs2.getString(1));
				COrderDetail.setProductName(rs2.getString(2));
				orderDetails.add(COrderDetail);
			}
			
			order.setCOrderDetail(orderDetails);
			order.COrderMaster.setComment(rs.getString(10));
			order.COrderMaster.setEbayAccount(rs.getString(11));
			order.COrderMaster.setPayDate(rs.getDate(12));
			//System.out.println(order);
			orderList.add(order);
		}
		return orderList;
	}

	public COrders getOrderAllInfo(String orderId, Connection conn) throws SQLException {

		String strsql = "SELECT distinct g.guestFirstName, g.guestLastName, g.guestAccount, g.email, g.tel1, g.tel2"
				+ ", g.mobile, g.birthday, g.company, g.address, g.country, g.postcode, g.gender"
				+ ", r.recieverFirstName, r.recieverLastName, r.tel1, r.tel2, r.address, r.country, r.postcode"
				+ ", d.sku, d.productName, d.invoiceName, d.price, d.invoicePrice, d.qty, d.warehouse, d.comment"
				+ " from quickreach.orders_master as m inner join quickreach.orders_detail as d using (order_id)"
				+ " inner join quickreach.orders_guestinfo as g using (order_id) "
				+ " inner join quickreach.order_recieverinfo as r using (order_id)" + " where order_id = ?";

		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, orderId);

		ResultSet rs = ps.executeQuery();
		System.out.println(strsql);
		System.out.println(rs);
		COrders orderInfo = new COrders();
		while (rs.next()) {
			orderInfo.COrderGuestInfo.setGuestFirstName(rs.getString(1));
			orderInfo.COrderGuestInfo.setGuestLastName(rs.getString(2));
			orderInfo.COrderGuestInfo.setGuestAccount(rs.getString(3));
			orderInfo.COrderGuestInfo.setEmail(rs.getString(4));
			orderInfo.COrderGuestInfo.setTel1(rs.getString(5));
			orderInfo.COrderGuestInfo.setTel2(rs.getString(6));
			orderInfo.COrderGuestInfo.setMobile(rs.getString(7));
			orderInfo.COrderGuestInfo.setMobile(rs.getString(8));
			orderInfo.COrderGuestInfo.setCompany(rs.getString(9));
			orderInfo.COrderGuestInfo.setAddress(rs.getString(10));
			orderInfo.COrderGuestInfo.setCountry(rs.getString(11));
			orderInfo.COrderGuestInfo.setPostcode(rs.getString(12));
			orderInfo.COrderGuestInfo.setGender(rs.getString(13));

			orderInfo.COrderReciever.setRecieverFirstName(rs.getString(14));
			orderInfo.COrderReciever.setRecieverLastName(rs.getString(15));
			orderInfo.COrderReciever.setTel1(rs.getString(16));
			orderInfo.COrderReciever.setTel2(rs.getString(17));
			orderInfo.COrderReciever.setAddress(rs.getString(18));
			orderInfo.COrderReciever.setCountry(rs.getString(19));
			orderInfo.COrderReciever.setPostCode(rs.getString(20));

			orderInfo.COrderDetail.setSKU(rs.getString(21));
			orderInfo.COrderDetail.setProductName(rs.getString(22));
			orderInfo.COrderDetail.setInvoiceName(rs.getString(23));
			orderInfo.COrderDetail.setPrice(rs.getDouble(24));
			orderInfo.COrderDetail.setInvoicePrice(rs.getDouble(25));
			orderInfo.COrderDetail.setQty(rs.getInt(26));
			orderInfo.COrderDetail.setWarehouse(rs.getString(27));
			orderInfo.COrderDetail.setComment(rs.getString(28));
		}

		return orderInfo;

	}

	public LinkedList<COrderDetail> getOrderDetails(String orderId, Connection conn) throws SQLException {

		String strsql = "SELECT distinct"
				+ " d.sku, d.productName, d.invoiceName, d.price, d.invoicePrice, d.qty, d.warehouse, d.comment"
				+ " from quickreach.orders_master as m inner join quickreach.orders_detail as d using (order_id)"
				+ " inner join quickreach.orders_guestinfo as g using (order_id) "
				+ " inner join quickreach.order_recieverinfo as r using (order_id)" + " where order_id = ?";

		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, orderId);

		ResultSet rs = ps.executeQuery();
		System.out.println(strsql);
		System.out.println(rs);
		LinkedList<COrderDetail> detailList = new LinkedList<COrderDetail>();
		while (rs.next()) {
			COrderDetail detail = new COrderDetail();
			detail.setSKU(rs.getString(1));
			detail.setProductName(rs.getString(2));
			detail.setInvoiceName(rs.getString(3));
			detail.setPrice(rs.getDouble(4));
			detail.setInvoicePrice(rs.getDouble(5));
			detail.setQty(rs.getInt(6));
			detail.setWarehouse(rs.getString(7));
			detail.setComment(rs.getString(8));
			detailList.add(detail);
		}
		return detailList;

	}

	public void insertIntoShippingLog(HttpServletRequest request, Connection conn) throws SQLException {

		String strSql = "insert into quickreach.shippinglog (order_id, date, trackingCode, staffName)"
				+ " values( ?, now(), ?, ?)";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("orderId"));
		ps.setString(2, request.getParameter("trackingCode"));
		ps.setString(3, "system");

		ps.executeUpdate();
	}

	public void InsertOrUpdateOrderDetail(HttpServletRequest request, Connection conn) throws SQLException {

		String strSql = "update UPDATE quickreach.orders_detail"
				+ " SET SKU=?, productName=?, invoiceName=?, price=?, invoicePrice=?, qty=?, warehouse=?, comment=?, owner=?"
				+ " WHERE order_id= ? and SKU= ?;";

		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("orderId"));
		ps.executeUpdate();
	}

	public void updateOrderDetail(HttpServletRequest request, Connection conn) throws SQLException {
		String strSql = "update UPDATE quickreach.orders_detail"
				+ " SET invoiceName= ?, price= ?, invoicePrice= ?, qty= ?, comment= ? "
				+ " WHERE order_id= ? and SKU= ?;";

		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("invoiceName"));
		ps.setString(2, request.getParameter("price"));
		ps.setString(3, request.getParameter("invoicePrice"));
		ps.setString(4, request.getParameter("qty"));
		ps.setString(5, request.getParameter("comment"));
		ps.setString(6, request.getParameter("orderId"));
		ps.setString(7, request.getParameter("SKU"));
		ps.executeUpdate();
	}

	public void updateToProcessing(HttpServletRequest request, Connection conn) throws SQLException {

		String[] strOrderIds = request.getParameterValues("orderId");
		// convert array to LinkedList
		LinkedList orderIds = new LinkedList(Arrays.asList(strOrderIds));
		// iterate over each element in LinkedList and show what is in the list.
		Iterator iterator = orderIds.iterator();
		while (iterator.hasNext()) {
			// Print element to console
			System.out.println((String) iterator.next());
		}
		for (int i = 0; i < orderIds.size(); i++) {
			String strSql = "update quickreach.orders_master" + " set orderStatus = '處理中' " + " where order_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, (String) orderIds.get(i));
			ps.executeUpdate();
		}
	}

	public void updateToPickUp(HttpServletRequest request, Connection conn) throws SQLException {

		String[] strOrderIds = request.getParameterValues("orderId");
		// convert array to LinkedList
		LinkedList orderIds = new LinkedList(Arrays.asList(strOrderIds));
		// iterate over each element in LinkedList and show what is in the list.
		Iterator iterator = orderIds.iterator();
		while (iterator.hasNext()) {
			// Print element to console
			System.out.println((String) iterator.next());
		}
		for (int i = 0; i < orderIds.size(); i++) {
			String strSql = "update quickreach.orders_master" + " set orderStatus = '揀貨中' " + " where order_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, (String) orderIds.get(i));
			ps.executeUpdate();
		}
	}

	public void updateToComplete(HttpServletRequest request, Connection conn) throws SQLException {

		String[] strOrderIds = request.getParameterValues("orderId");
		// convert array to LinkedList
		LinkedList orderIds = new LinkedList(Arrays.asList(strOrderIds));
		// iterate over each element in LinkedList and show what is in the list.
		Iterator iterator = orderIds.iterator();
		while (iterator.hasNext()) {
			// Print element to console
			System.out.println((String) iterator.next());
		}
		for (int i = 0; i < orderIds.size(); i++) {
			String strSql = "update quickreach.orders_master" + " set orderStatus = '已出貨' " + " where order_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, (String) orderIds.get(i));
			ps.executeUpdate();
		}
	}

	public boolean checkOrderIdOrderStatus(HttpServletRequest request, Connection conn) throws Exception {
		String strSql = "select order_id, orderStatus from quickreach.orders_master";
		PreparedStatement ps = conn.prepareStatement(strSql);
		System.out.println(strSql);
		ResultSet rs = ps.executeQuery();

		LinkedList<COrders> orderList = new LinkedList<COrders>();
		COrders order = new COrders();
		while (rs.next()) {
			order = new COrders();
			order.COrderMaster.setOrder_id(rs.getString(1));
			order.COrderMaster.setOrderStatus(rs.getString(2));
			orderList.add(order);
		}

		for (int i = 0; i < orderList.size(); i++) {
			if (request.getParameter("orderId").equals(orderList.get(i).getCOrderMaster().getOrder_id().toString())) {
				System.out.println("orderID:true");
				if (orderList.get(i).getCOrderMaster().getOrderStatus().toString().equals("已出貨"))
					return true;
			}
		}
		System.out.println("orderId is invalid or wrong order status.");
		return false;
	}

	public void updateToFinished(HttpServletRequest request, Connection conn) throws Exception {
		String strSql = "update quickreach.orders_master" + " set orderStatus = '已完成' " + " where order_id = ? ";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("orderId"));
		ps.executeUpdate();
	}

	public void deductStock(HttpServletRequest request, Connection conn) throws Exception {

		LinkedList<COrderDetail> condition = getCondition(request, conn);
		for (int i = 0; i < condition.size(); i++) {
			String strSql = "update quickreach.storage" + " set qty = ? " + " where sku = ? and warehouse = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setInt(1, condition.get(i).getQty());
			ps.setString(2, condition.get(i).getSKU());
			ps.setString(3, condition.get(i).getWarehouse());
			ps.executeUpdate();
		}
	}

	public LinkedList<COrderDetail> getCondition(HttpServletRequest request, Connection conn) throws Exception {

		LinkedList<COrderDetail> condition = getSkuAndWarehouse(request, conn);
		LinkedList<COrderDetail> result = new LinkedList<COrderDetail>();
		for (int i = 0; i < condition.size(); i++) {
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
			while (rs.next()) {
				detail.setSKU(rs.getString(1));
				detail.setQty(rs.getInt(2) - rs.getInt(3));
				detail.setWarehouse(rs.getString(4));
				result.add(detail);
			}
		}
		System.out.println(result);
		System.out.println(result);
		System.out.println(result.get(0).toString());
		return result;
	}

	public LinkedList<COrderDetail> getSkuAndWarehouse(HttpServletRequest request, Connection conn) throws Exception {

		String strSql = "select SKU, warehouse"
				+ " from quickreach.orders_master as m inner join quickreach.orders_detail as d"
				+ " using (order_id) where order_id = ?";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("orderId"));
		ResultSet rs = ps.executeQuery();
		LinkedList<COrderDetail> result = new LinkedList<COrderDetail>();
		COrderDetail detail = new COrderDetail();
		while (rs.next()) {
			detail = new COrderDetail();
			detail.setSKU(rs.getString(1));
			detail.setWarehouse(rs.getString(2));
			result.add(detail);
		}
		return result;
	}
	
	public void checkUrlToRemoveSession(HttpServletRequest request, HttpSession session){
		String referer = request.getHeader("Referer");
		
		if(referer == null ){
			return;
		}
		
		if(!(referer.substring(0,referer.lastIndexOf("p"))+"p").equals
		(request.getRequestURL().toString()))
		{

		System.out.println(referer.substring(0,referer.lastIndexOf("p"))+"p");
		System.out.println(request.getRequestURL().toString());
		session.removeAttribute("SearchOrdersResult");
		} 
	}
}
