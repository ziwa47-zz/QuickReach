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

import com.mysql.fabric.Response;

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
				+ " logistics, orderstatus, totalPrice, staffName, m.comment, m.eBayAccount, m.payDate," + " QR_id "
				+ " FROM  orders_master as m inner join  orders_detail as d using (QR_id)"
				+ " inner join  orders_guestinfo as g using (QR_id)" + " where '1' = '1' ";

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
		String shipped = request.getParameter("shipped");
		String finished = request.getParameter("finished");
		String refund = request.getParameter("refund");
		String others = request.getParameter("oothers");
		String deducted = request.getParameter("deducted");

		if (!checkboxAreUnchecked(waitProcess, processing, pickup, shipped, finished, refund, others, deducted, null,
				null, null)) {
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
		if (!isNullorEmpty(shipped)) {
			strSql += " or orderStatus = '已出貨' ";
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

			String strSql2 = "SELECT SKU, productName" + " FROM  orders_detail" + " where QR_id = ?";

			PreparedStatement ps2 = conn.prepareStatement(strSql2);
			ps2.setString(1, rs.getString(13));
			// System.out.println(strSql2);
			ResultSet rs2 = ps2.executeQuery();
			orderDetails = new LinkedList<>();
			while (rs2.next()) {
				COrderDetail COrderDetail = new COrderDetail();
				COrderDetail.setSKU(rs2.getString(1));
				COrderDetail.setProductName(rs2.getString(2));
				orderDetails.add(COrderDetail);
			}

			order.setCOrderDetail(orderDetails);
			order.COrderMaster.setComment(rs.getString(10));
			order.COrderMaster.setEbayAccount(rs.getString(11));
			order.COrderMaster.setPayDate(rs.getDate(12));
			order.COrderMaster.setQR_id(rs.getString(13));
			// System.out.println(order);
			orderList.add(order);
		}

		return orderList;
	}

	public LinkedList<COrders> orders(HttpServletRequest request, Connection conn, String status) throws SQLException {

		String strSql = "SELECT distinct order_id, platform, guestAccount, orderDate, shippingDate,"
				+ " logistics, orderstatus, totalPrice, staffName, comment, eBayAccount, payDate," + " QR_id "
				+ " FROM  orders_master " + " where '1' = '1' and orderstatus = ?";

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

			String strSql2 = "SELECT SKU, productName" + " FROM  orders_detail" + " where QR_id = ?";

			PreparedStatement ps2 = conn.prepareStatement(strSql2);
			ps2.setString(1, rs.getString(13));
			//System.out.println(strSql2);
			ResultSet rs2 = ps2.executeQuery();
			orderDetails = new LinkedList<>();
			while (rs2.next()) {
				COrderDetail COrderDetail = new COrderDetail();
				COrderDetail.setSKU(rs2.getString(1));
				COrderDetail.setProductName(rs2.getString(2));
				orderDetails.add(COrderDetail);
			}

			order.setCOrderDetail(orderDetails);
			order.COrderMaster.setComment(rs.getString(10));
			order.COrderMaster.setEbayAccount(rs.getString(11));
			order.COrderMaster.setPayDate(rs.getDate(12));
			order.COrderMaster.setQR_id(rs.getString(13));
			// System.out.println(order);
			orderList.add(order);
		}
		return orderList;
	}

	public COrders getOrderAllInfo(String QR_id, Connection conn) throws SQLException {

		String strsql = "SELECT distinct g.guestFirstName, g.guestLastName, g.guestAccount, g.email, g.tel1, g.tel2"
				+ ", g.mobile, g.birthday, g.company, g.address, g.country, g.postcode, g.gender"
				+ ", r.recieverFirstName, r.recieverLastName, r.tel1, r.tel2, r.address, r.country, r.postcode"
				+ ", d.sku, d.productName, d.invoiceName, d.price, d.invoicePrice, d.qty, d.warehouse, d.comment"
				+ ", m.QR_id"
				+ " from  orders_master as m inner join  orders_detail as d using (QR_id)"
				+ " inner join  orders_guestinfo as g using (QR_id) "
				+ " inner join  order_recieverinfo as r using (QR_id)" + " where QR_id = ?";

		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, QR_id);

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

			orderInfo.COrderDetailSingle.setSKU(rs.getString(21));
			orderInfo.COrderDetailSingle.setProductName(rs.getString(22));
			orderInfo.COrderDetailSingle.setInvoiceName(rs.getString(23));
			orderInfo.COrderDetailSingle.setPrice(rs.getDouble(24));
			orderInfo.COrderDetailSingle.setInvoicePrice(rs.getDouble(25));
			orderInfo.COrderDetailSingle.setQty(rs.getInt(26));
			orderInfo.COrderDetailSingle.setWarehouse(rs.getString(27));
			orderInfo.COrderDetailSingle.setComment(rs.getString(28));
			
			orderInfo.COrderMaster.setQR_id(rs.getString(29));
		}

		return orderInfo;

	}

	public LinkedList<COrderDetail> getOrderDetails(String QR_id, Connection conn) throws SQLException {

		String strsql = "SELECT distinct"
				+ " d.sku, d.productName, d.invoiceName, d.price, d.invoicePrice, d.qty, d.warehouse, d.comment, d.Item, d.QR_id"
				+ " from  orders_master as m inner join  orders_detail as d using (QR_id)"
				+ " inner join  orders_guestinfo as g using (QR_id) "
				+ " inner join  order_recieverinfo as r using (QR_id)" + " where QR_id = ?";

		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, QR_id);

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
			detail.setItem(Integer.valueOf(rs.getString(9)));
			detail.setQR_id(rs.getString(10));
			detailList.add(detail);
		}
		return detailList;

	}


	public void updateOrderDetail(HttpServletRequest request, Connection conn) throws SQLException {
		
		String[] itemList = request.getParameterValues("item");
		String[] SKUList = request.getParameterValues("SKU");
		String[] invoiceNameList = request.getParameterValues("invoiceName");
		String[] priceList = request.getParameterValues("price");
		String[] invoicePriceList = request.getParameterValues("invoicePrice");
		String[] qtyList = request.getParameterValues("qty");
		String[] commentList = request.getParameterValues("comment");
		
		LinkedList<String> items = new LinkedList<String>(Arrays.asList(itemList));
		LinkedList<String> SKUs = new LinkedList<String>(Arrays.asList(SKUList));
		LinkedList<String> invoiceNames = new LinkedList<String>(Arrays.asList(invoiceNameList));
		LinkedList<String> prices = new LinkedList<String>(Arrays.asList(priceList));
		LinkedList<String> invoicePrices = new LinkedList<String>(Arrays.asList(invoicePriceList));
		LinkedList<String> qtys = new LinkedList<String>(Arrays.asList(qtyList));
		LinkedList<String> comments = new LinkedList<String>(Arrays.asList(commentList));
		
		for(int i=0; i<items.size(); i++){
			String strSql = "update orders_detail"
					+ " SET invoiceName= ?, price= ?, invoicePrice= ?, qty= ?, comment=? "
					+ " WHERE SKU= ? and item= ?;";
			System.out.println(strSql);
			COrderDetail od = new COrderDetail();
			od.setInvoiceName(invoiceNames.get(i));
			od.setPrice(Double.valueOf(prices.get(i)));
			od.setInvoicePrice(Double.valueOf(invoicePrices.get(i)));
			od.setQty(Integer.valueOf(qtys.get(i)));
			od.setComment(comments.get(i));
			od.setItem(Integer.valueOf(items.get(i)));
			od.setSKU(SKUs.get(i));
			System.out.println(items.get(i));
			System.out.println(SKUs.get(i));
			PreparedStatement ps = conn.prepareStatement(strSql);
	
			ps.setString(1, od.getInvoiceName());
			ps.setDouble(2, od.getPrice());
			ps.setDouble(3, od.getInvoicePrice());
			ps.setInt(4, od.getQty());
			ps.setString(5, od.getComment());
			ps.setString(6, od.getSKU());
			ps.setInt(7, od.getItem());
			int x = ps.executeUpdate();
		}
	}

	public void updateToProcessing(HttpServletRequest request, Connection conn) throws SQLException {
		
		String[] strQR_idArray = request.getParameterValues("QR_id");
		String[] strLogisticsArray = request.getParameterValues("logistics");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_idArray));
		LinkedList<String> Logistics = new LinkedList<String>(Arrays.asList(strLogisticsArray));
		// iterate over each element in LinkedList and show what is in the list.
		Iterator<String> iterator = QR_ids.iterator();	
		Iterator<String> iterator2 = Logistics.iterator();	
		while (iterator.hasNext()) {
			// Print element to console
			System.out.println(iterator.next());
		}
		while (iterator2.hasNext()) {
			// Print element to console
			System.out.println(iterator2.next());
		}
		for (int i = 0; i < QR_ids.size(); i++) {
			String strSql = "update  orders_master" + " set orderStatus = '處理中', logistics= ? "
					+ " where QR_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, Logistics.get(i));
			ps.setString(2, QR_ids.get(i));
			ps.executeUpdate();
		}
	}

	public void updateToPickUp(HttpServletRequest request, Connection conn) throws SQLException {

		String[] strQR_idArray = request.getParameterValues("QR_id");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_idArray));
		// iterate over each element in LinkedList and show what is in the list.
		Iterator<String> iterator = QR_ids.iterator();
		while (iterator.hasNext()) {
			// Print element to console
			System.out.println(iterator.next());
		}
		for (int i = 0; i < QR_ids.size(); i++) {
			String strSql = "update  orders_master" + " set orderStatus = '揀貨中' " + " where QR_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, QR_ids.get(i));
			ps.executeUpdate();
		}
	}

	public void updateToComplete(HttpServletRequest request, Connection conn) throws SQLException {

		String[] strQR_ids = request.getParameterValues("QR_id");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_ids));
		// iterate over each element in LinkedList and show what is in the list.
		Iterator<String> iterator = QR_ids.iterator();
		while (iterator.hasNext()) {
			// Print element to console
			System.out.println(iterator.next());
		}
		for (int i = 0; i < QR_ids.size(); i++) {
			String strSql = "update  orders_master" + " set orderStatus = '已出貨' " + " where QR_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, QR_ids.get(i));
			ps.executeUpdate();
		}
	}
	
	public void updateToFinished(HttpServletRequest request, Connection conn) throws Exception {
		String strSql = "update  orders_master" + " set orderStatus = '已完成' " + " where QR_id = ? ";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("QR_id"));
		ps.executeUpdate();
	}

	public boolean checkOrderIdOrderStatus(HttpServletRequest request, Connection conn) throws Exception {
		String strSql = "select QR_id, orderStatus from  orders_master";
		PreparedStatement ps = conn.prepareStatement(strSql);
		System.out.println(strSql);
		ResultSet rs = ps.executeQuery();

		LinkedList<COrders> orderList = new LinkedList<COrders>();
		COrders order = new COrders();
		while (rs.next()) {
			order = new COrders();
			order.COrderMaster.setQR_id(rs.getString(1));
			order.COrderMaster.setOrderStatus(rs.getString(2));
			orderList.add(order);
		}

		for (int i = 0; i < orderList.size(); i++) {
			if (request.getParameter("QR_id").equals(orderList.get(i).getCOrderMaster().getQR_id().toString())) {
				System.out.println(request.getParameter("QR_id"));
				if ("已出貨".equals(orderList.get(i).getCOrderMaster().getOrderStatus().toString()))
					return true;
			}
		}
		System.out.println("QRId is invalid or wrong order status.");
		return false;
	}
	
	public void insertIntoShippingLog(HttpServletRequest request, Connection conn) throws SQLException {
		
		LinkedList<ShipmentRecord> ShippingLog = new LinkedList<ShipmentRecord>();
		for(int i=0; i<ShippingLog.size(); i++){
			String strSql = "insert into  shippinglog (QR_id, date, trackingCode, staffName)"
					+ " values( ?, now(), ?, ?)";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, ShippingLog.get(i).getQR_id());
			ps.setString(2, ShippingLog.get(i).getTrackingCode());
			ps.setString(3, ShippingLog.get(i).getStaffName());
	
			ps.executeUpdate();
			System.out.println(ShippingLog.get(i).getQR_id());
			System.out.println(ShippingLog.get(i).getTrackingCode());
			System.out.println(ShippingLog.get(i).getStaffName());
		}
	}

	public void deductStock(HttpServletRequest request, Connection conn) throws Exception {

		LinkedList<COrderDetail> condition = getCondition(request, conn);
		for (int i = 0; i < condition.size(); i++) {
			String strSql = "update  storage" + " set qty = ? " + " where sku = ? and warehouse = ? and item= ?";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setInt(1, condition.get(i).getQty());
			ps.setString(2, condition.get(i).getSKU());
			ps.setString(3, condition.get(i).getWarehouse());
			ps.setInt(4, condition.get(i).getItem());
			ps.executeUpdate();
			
		}
	}

	public LinkedList<COrderDetail> getCondition(HttpServletRequest request, Connection conn) throws Exception {

		LinkedList<COrderDetail> condition = getSkuAndWarehouse(request, conn);
		LinkedList<COrderDetail> result = new LinkedList<COrderDetail>();
		for (int i = 0; i < condition.size(); i++) {
			String strSql = "select distinct s.SKU, s.qty, d.qty, s.warehouse, s.item"
					+ " from  storage as s inner join  orders_detail as d"
					+ " using (SKU) where QR_id = ? and sku = ? and s.warehouse = ?";
			PreparedStatement ps = conn.prepareStatement(strSql);

			ps.setString(1, request.getParameter("QR_id"));
			ps.setString(2, condition.get(i).getSKU());
			ps.setString(3, condition.get(i).getWarehouse());
			System.out.println(strSql);
			ResultSet rs = ps.executeQuery();
			COrderDetail detail = new COrderDetail();
			while (rs.next()) {
				detail.setSKU(rs.getString(1));
				detail.setQty(rs.getInt(2) - rs.getInt(3));
				detail.setWarehouse(rs.getString(4));
				detail.setItem(Integer.valueOf(rs.getString(5)));
				result.add(detail);
			}
		}
		return result;
	}

	public LinkedList<COrderDetail> getSkuAndWarehouse(HttpServletRequest request, Connection conn) throws Exception {

		String strSql = "select SKU, warehouse"
				+ " from  orders_master as m inner join  orders_detail as d"
				+ " using (QR_id) where QR_id = ?";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("QR_id"));
		ResultSet rs = ps.executeQuery();
		LinkedList<COrderDetail> result = new LinkedList<COrderDetail>();
		COrderDetail detail = new COrderDetail();
		while (rs.next()) {
			detail = new COrderDetail();
			detail.setSKU(rs.getString(1));
			detail.setWarehouse(rs.getString(2));
			result.add(detail);
		}
		System.out.println("true");
		return result;
	}

	public void checkUrlToRemoveSession(HttpServletRequest request, HttpSession session) {
		String referer = request.getHeader("Referer");

		if (referer == null) {
			return;
		}

		if (!(referer.substring(0, referer.lastIndexOf("p")) + "p").equals(request.getRequestURL().toString())) {

			System.out.println(referer.substring(0, referer.lastIndexOf("p")) + "p");
			System.out.println(request.getRequestURL().toString());
			session.removeAttribute("SearchOrdersResult");
		}
	}
	
	public LinkedList<String> unSelectedList (HttpServletRequest request) {
		
		String[] strQR_idArray = request.getParameterValues("QR_id");
		String[] strLogisticsArray = request.getParameterValues("logistics");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_idArray));
		LinkedList<String> Logistics = new LinkedList<String>(Arrays.asList(strLogisticsArray));
		// iterate over each element in LinkedList and show what is in the list.
		Iterator<String> iterator = QR_ids.iterator();	
		Iterator<String> iterator2 = Logistics.iterator();
		
		LinkedList<String> unSelected = new LinkedList<>();
		for(int i=0;i<QR_ids.size();i++){
			if(Logistics.get(i) != "請選擇"){
				
			} else {
				unSelected.add(QR_ids.get(i));
			}
		}
		return unSelected;
	}
	
	public LinkedList<ShipmentRecord> getShipmentRecord (HttpServletRequest request, Connection conn) throws Exception {
		
		String strSql = "select s.date, s.QR_id, s.type, m.eBayAccount, d.SKU, d.productName, d.qty,"
				+ " r.country, d.owner, d.warehouse, m.staffName, s.comment"
				+ " from orders_master as m inner join shippinglog as s"
				+ " inner join order_recieverinfo as r"
				+ " inner join orders_detail as d";
		
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("QR_id"));
		ResultSet rs = ps.executeQuery();

	}
}
