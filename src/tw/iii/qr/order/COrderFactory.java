package tw.iii.qr.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.DTO.COrderDetail;
import tw.iii.qr.order.DTO.COrderMaster;
import tw.iii.qr.order.DTO.COrders;
import tw.iii.qr.order.DTO.ShipmentRecord;

//orderdate paydate shippingdate schema confirm
public class COrderFactory extends COrders {

	public COrderFactory() {
	}

	// 所有搜尋的共用方法
	public static LinkedList<COrders> orders(HttpServletRequest request,String status)
			throws SQLException ,Exception{
		Connection conn= new DataBaseConn().getConn();
		// 整理查詢參數
		String eBayAccount = request.getParameter("eBayAccount");
		String ebayNO = request.getParameter("ebayNO");
		String paypal_id = request.getParameter("paypal_id");
		String QR_id = request.getParameter("QR_id");
		String guestAccount = request.getParameter("guestAccount");
		String trackingCode = request.getParameter("trackingCode");
		String guestName = request.getParameter("guestLastName");
		String SKU = request.getParameter("SKU");
		String payDateMin = request.getParameter("payDateMin");
		String payDateMax = request.getParameter("payDateMax");
		String productName = request.getParameter("productName");
		String shippingDateMin = request.getParameter("shippingDateMin");
		String shippingDateMax = request.getParameter("shippingDateMax");

		String strSql = "SELECT distinct m.order_id, m.platform, m.guestAccount, m.orderDate, m.shippingDate,"
				+ " m.logistics, m.orderstatus, m.totalPrice,m.staffName, m.comment, m.eBayAccount, m.payDate,"
				+ " m.QR_id, m.currency, r.country, m.ebayItemNO, m.paypalmentId, m.ebayNO,g.guestFirstName,g.guestLastName"
				+ " FROM  orders_master as m left join  orders_detail as d on m.QR_id = d.QR_id"
				+ " left join  orders_guestinfo as g on m.QR_id = g.QR_id"
				+ " left join  order_recieverinfo as r on m.QR_id = r.QR_id"
				+ " where '1' = '1' and (isCombine != '1' or isCombine is null) ";

		strSql += isNullorEmpty(eBayAccount) ? "" : " and m.eBayAccount = ? ";
		strSql += isNullorEmpty(ebayNO) ? "" : " and m.ebayNO like ? ";
		strSql += isNullorEmpty(paypal_id) ? "" : " and m.paypal_id like ? ";
		strSql += isNullorEmpty(QR_id) ? "" : " and m.QR_id like ? ";
		strSql += isNullorEmpty(guestAccount) ? "" : " and m.guestAccount like ? ";
		strSql += isNullorEmpty(trackingCode) ? "" : " and m.trackingCode like ? ";
		strSql += isNullorEmpty(guestName) ? "" : " and ( g.guestLastName like ? or g.guestFirstName like ? ) ";
		strSql += isNullorEmpty(SKU) ? "" : " and m.SKU like ? ";
		strSql += isNullorEmpty(payDateMin) ? "" : " and m.payDate >= ? ";
		strSql += isNullorEmpty(payDateMax) ? "" : " and m.payDate <= ? ";
		strSql += isNullorEmpty(productName) ? "" : " and d.productName like ? ";
		strSql += isNullorEmpty(shippingDateMin) ? "" : " and m.shippingDate >= ? ";
		strSql += isNullorEmpty(shippingDateMax) ? "" : " and m.shippingDate <= ? ";

		// 訂單狀態
		if (status != null && status != "") {
			strSql += " and ( orderStatus = N'" + status + "' ";
		} else {

			String waitProcess = request.getParameter("waitProcess");
			String processing = request.getParameter("processing");
			String pickup = request.getParameter("pickup");
			String shipped = request.getParameter("shipped");
			String finished = request.getParameter("finished");
			String refund = request.getParameter("refund");
			String others = request.getParameter("oothers");
			String deducted = request.getParameter("deducted");
			String undo = request.getParameter("undo");

			strSql += checkboxAreUnchecked(waitProcess, processing, pickup, shipped, finished, refund, others, deducted,
					undo) ? " and ( '1' = '1' " : " and ( orderStatus is null ";
			
			strSql += isNullorEmpty(waitProcess) ? "" : " or orderStatus = N'待處理' ";
			strSql += isNullorEmpty(processing) ? "" : " or orderStatus = N'處理中' ";
			strSql += isNullorEmpty(pickup) ? "" : " or orderStatus = N'揀貨中' ";
			strSql += isNullorEmpty(shipped) ? "" : " or orderStatus = N'已出貨' ";
			strSql += isNullorEmpty(finished) ? "" : " or orderStatus = N'已完成' ";
			strSql += isNullorEmpty(refund) ? "" : " or orderStatus = N'退款' ";
			strSql += isNullorEmpty(others) ? "" : " or orderStatus = N'其他' ";
			strSql += isNullorEmpty(deducted) ? "" : " or orderStatus = N'退貨中' ";
			strSql += isNullorEmpty(undo) ? "" : " or orderStatus = N'未完成' ";

		}
		strSql += " ) ";
		String logistics = request.getParameter("logistics");
		if (logistics == "USPS1")
			logistics = "USPS寄倉";
		if (logistics == "USPS2")
			logistics = "USPS集運";

		if ("ALL".equals(logistics) || "".equals(logistics) || logistics==null) {
			strSql += "";
		} else if ("lothers".equals(logistics)) {
			strSql += " and logistics <> 'DHL' ";
			strSql += " and logistics <> 'EMS' ";
			strSql += " and logistics <> 'AP' ";
			strSql += " and logistics <> 'RA' ";
			strSql += " and logistics <> 'Fedex' ";
			strSql += " and logistics <> N'USPS寄倉' ";
			strSql += " and logistics <> N'USPS集運' ";
		}
		else if (!"ALL".equals(logistics)) {
			strSql += " and logistics = N'" + logistics + "' ";
		}

		strSql += "order by QR_id desc";
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
		if (!isNullorEmpty(guestName)) {
			ps.setString(param, "%" + guestName + "%");
			param++;
			ps.setString(param, "%" + guestName + "%");
			param++;
		}
		if (!isNullorEmpty(SKU)) {
			ps.setString(param, "%" + SKU + "%");
			param++;
		}
		if (!isNullorEmpty(payDateMin)) {
			ps.setString(param, payDateMin);
			param++;
		}
		if (!isNullorEmpty(payDateMax)) {
			ps.setString(param, payDateMax + " 23:59:59");
			param++;
		}
		if (!isNullorEmpty(productName)) {
			ps.setString(param, "%" + productName + "%");
			param++;
		}
		if (!isNullorEmpty(shippingDateMin)) {
			ps.setString(param, shippingDateMin);
			param++;
		}
		if (!isNullorEmpty(shippingDateMax)) {
			ps.setString(param, shippingDateMax + " 23:59:59");
			param++;
		}

		System.out.println(status);
		System.out.println(strSql);
		ResultSet rs = ps.executeQuery();
		LinkedList<COrders> orderList = new LinkedList<COrders>();
		while (rs.next()) {
			orderList.add(putorder(conn, rs));
		}
		conn.close();
		return orderList;
	}

	private static COrders putorder(Connection conn, ResultSet rs) throws SQLException {
		COrders order = new COrders();
		order.COrderMaster.setOrder_id(rs.getString(1));
		order.COrderMaster.setPlatform(rs.getString(2));
		order.COrderMaster.setGuestAccount(rs.getString(3));
		order.COrderMaster.setOrderDate(rs.getDate(4));
		order.COrderMaster.setShippingDate(rs.getDate(5));
		order.COrderMaster.setLogistics(rs.getString(6));
		order.COrderMaster.setOrderStatus(rs.getString(7));
		order.COrderMaster.setTotalPrice(rs.getDouble(8));
		order.COrderMaster.setStaffName(rs.getString(9));
		order.COrderMaster.setQR_id(rs.getString(13));
		order.COrderMaster.setCurrency(rs.getString(14));
		order.COrderReciever.setCountry(rs.getString(15));
		order.setCOrderDetail(putDetail(conn, order.COrderMaster.getQR_id()));
		order.COrderMaster.setComment(rs.getString(10));
		order.COrderMaster.setEbayAccount(rs.getString(11));
		order.COrderMaster.setPayDate(rs.getDate(12));
		order.COrderMaster.setEbayItemNO(rs.getString(16));
		order.COrderMaster.setPaypalmentId(rs.getString(17));
		order.COrderMaster.setEbayNO(rs.getString(18));
		return order;
	}

	private static LinkedList<COrderDetail> putDetail(Connection conn, String qrid) throws SQLException {
		LinkedList<COrderDetail> orderDetails = new LinkedList<>();
		String strSql2 = "SELECT SKU, productName, warehouse" + " FROM  orders_detail" + " where QR_id = ?";
		PreparedStatement ps2 = conn.prepareStatement(strSql2);
		ps2.setString(1, qrid);
		ResultSet rs2 = ps2.executeQuery();
		while (rs2.next()) {
			COrderDetail COrderDetail = new COrderDetail();
			COrderDetail.setSKU(rs2.getString(1));
			COrderDetail.setProductName(rs2.getString(2));
			COrderDetail.setWarehouse(rs2.getString(3));
			getPicAndLocation(COrderDetail, conn);
			orderDetails.add(COrderDetail);
		}
		return orderDetails;
	}

	private static void getPicAndLocation(tw.iii.qr.order.DTO.COrderDetail cOrderDetail, Connection conn) {
		String strsql = "select p.picturePath,s.warehousePosition1,s.warehousePosition2 "
				+ " from product p inner join storage s on p.SKU =s.SKU inner join orders_detail od on p.sku= od.SKU and od.warehouse = s.warehouse "
				+ " where p.sku= ? and od.warehouse = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(strsql);
			ps.setString(1, cOrderDetail.getSKU());
			ps.setString(2, cOrderDetail.getWarehouse());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cOrderDetail.setPicPath(rs.getString(1));
				cOrderDetail.setWarehouseLocation(rs.getString(2) + "-" + (rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public COrders getOrderAllInfo(String QR_id) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String strsql = "SELECT distinct g.guestFirstName, g.guestLastName, g.guestAccount, g.email, g.tel1, g.tel2"
				+ ", g.mobile, g.birthday, g.company, g.address, g.country, g.postcode, g.gender"
				+ ", r.recieverFirstName, r.recieverLastName, r.tel1, r.tel2, r.address, r.country, r.postcode"

				+ ", m.outsideCode, m.orderStatus, m.order_id, m.QR_id, m.company, m.platform, m.eBayAccount, "
				+ " m.orderDate, m.payDate, m.logisticsId, m.logistics, m.paypal_id, m.payment,"
				+ " m.shippingDate, m.shippingFees, m.refundShippingFees, m.otherFees, m.ebayFees, m.paypalFees,"
				+ " m.insurance, m.insurancePrice, m.insuranceTotal, m.currency, m.weight, m.totalWeight,"
				+ " m.FedexService, m.staffName, m.size, m.totalPrice, m.ebayNO, m.trackingCode, m.payWay,"
				+ " m.size, m.comment" + " from  orders_master as m inner join  orders_detail as d on m.QR_id = d.QR_id"
				+ " left join  orders_guestinfo as g on m.QR_id = g.QR_id "
				+ " left join  order_recieverinfo as r on m.QR_id = r.QR_id" + " where m.QR_id = ?";

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

			orderInfo.COrderMaster.setOutsideCode(rs.getString(21));
			orderInfo.COrderMaster.setOrderStatus(rs.getString(22));
			orderInfo.COrderMaster.setOrder_id(rs.getString(23));
			orderInfo.COrderMaster.setQR_id(rs.getString(24));
			orderInfo.COrderMaster.setCompany(rs.getString(25));
			orderInfo.COrderMaster.setPlatform(rs.getString(26));
			orderInfo.COrderMaster.setEbayAccount(rs.getString(27));
			orderInfo.COrderMaster.setOrderDate(rs.getDate(28));
			orderInfo.COrderMaster.setPayDate(rs.getDate(29));
			orderInfo.COrderMaster.setLogisticsID(rs.getString(30));
			orderInfo.COrderMaster.setLogistics(rs.getString(31));
			orderInfo.COrderMaster.setPaypalId(rs.getString(32));
			orderInfo.COrderMaster.setPayment(rs.getInt(33));
			orderInfo.COrderMaster.setShippingDate(rs.getDate(34));
			orderInfo.COrderMaster.setShippingFees(rs.getDouble(35));
			orderInfo.COrderMaster.setRefundShippingFees(rs.getDouble(36));
			orderInfo.COrderMaster.setOtherFees(rs.getDouble(37));
			orderInfo.COrderMaster.setEbayFees(rs.getDouble(38));
			orderInfo.COrderMaster.setPaypalFees(rs.getDouble(39));
			orderInfo.COrderMaster.setInsurance(rs.getBoolean(40));
			orderInfo.COrderMaster.setInsurancePrice(rs.getDouble(41));
			orderInfo.COrderMaster.setInsuranceTotal(rs.getDouble(42));
			orderInfo.COrderMaster.setCurrency(rs.getString(43));
			orderInfo.COrderMaster.setWeight(rs.getDouble(44));
			orderInfo.COrderMaster.setTotalWeight(rs.getDouble(45));
			orderInfo.COrderMaster.setFedexService(rs.getString(46));
			orderInfo.COrderMaster.setStaffName(rs.getString(47));
			orderInfo.COrderMaster.setSize(rs.getString(48));
			orderInfo.COrderMaster.setTotalPrice(rs.getDouble(49));
			orderInfo.COrderMaster.setEbayNO(rs.getString(50));
			orderInfo.COrderMaster.setTrackingCode(rs.getString(51));
			orderInfo.COrderMaster.setPayWay(rs.getString(52));
			orderInfo.COrderMaster.setSize(rs.getString(53));
			orderInfo.COrderMaster.setComment(rs.getString(54));
		}
		conn.close();
		return orderInfo;

	}

	public LinkedList<COrderDetail> getOrderDetails(String QR_id) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String strsql = "SELECT distinct"
				+ " d.sku, d.productName, d.invoiceName, d.price, d.invoicePrice, d.qty, d.warehouse, d.comment, d.Item, d.QR_id"
				+ " from  orders_master as m inner join  orders_detail as d on m.QR_id = d.QR_id"
				+ " left join  orders_guestinfo as g on m.QR_id = g.QR_id "
				+ " left join  order_recieverinfo as r  on m.QR_id = r.QR_id" + " where m.QR_id = ?";

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
			detail.setItem(rs.getInt(9));
			detail.setQR_id(rs.getString(10));
			detailList.add(detail);
		}
		conn.close();
		return detailList;

	}

	public void updateOrderGuestInfo(HttpServletRequest request, Connection conn, String staffName, String QR_id)
			throws SQLException {
		String strSql = "update orders_guestinfo"
				+ " set orderStatus = N'已完成', shippingDate = getdate() , trackingCode = ? " + " where QR_id = ? ";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("trackingCode"));
		ps.setString(2, request.getParameter("QR_id"));
		ps.executeUpdate();

	}

	public void updateOrderRecieverInfo(HttpServletRequest request, Connection conn, String staffName, String QR_id)
			throws SQLException {

	}

	public void updateOrderMaster(HttpServletRequest request, Connection conn, String staffName, String QR_id)
			throws SQLException {

	}

	public void updateOrderDetail(HttpServletRequest request, String staffName, String QR_id)
			throws SQLException,Exception {

		String[] itemList = request.getParameterValues("item");
		String[] SKUList = request.getParameterValues("SKU");
		String[] invoiceNameList = request.getParameterValues("invoiceName");
		String[] priceList = request.getParameterValues("price");
		String[] invoicePriceList = request.getParameterValues("invoicePrice");
		String[] qtyList = request.getParameterValues("qty");
		String[] commentList = request.getParameterValues("comment");
		String[] warehouseList = request.getParameterValues("warehouse");

		LinkedList<String> items = new LinkedList<String>(Arrays.asList(itemList));
		LinkedList<String> SKUs = new LinkedList<String>(Arrays.asList(SKUList));
		LinkedList<String> invoiceNames = new LinkedList<String>(Arrays.asList(invoiceNameList));
		LinkedList<String> prices = new LinkedList<String>(Arrays.asList(priceList));
		LinkedList<String> invoicePrices = new LinkedList<String>(Arrays.asList(invoicePriceList));
		LinkedList<String> qtys = new LinkedList<String>(Arrays.asList(qtyList));
		LinkedList<String> comments = new LinkedList<String>(Arrays.asList(commentList));
		LinkedList<String> warehouses = new LinkedList<String>(Arrays.asList(warehouseList));

		Connection conn = new DataBaseConn().getConn();
		for (int i = 0; i < items.size(); i++) {
			String strSql = "update orders_detail"
					+ " SET invoiceName= ?, price= ?, invoicePrice= ?, qty= ?, comment=?, warehouse=? "
					+ " WHERE SKU= ? and item= ?;";
			// System.out.println(strSql);
			COrderDetail od = new COrderDetail();
			od.setInvoiceName(invoiceNames.get(i));
			od.setPrice(Double.valueOf(prices.get(i)));
			od.setInvoicePrice(Double.valueOf(invoicePrices.get(i)));
			od.setQty(Integer.valueOf(qtys.get(i)));
			od.setComment(comments.get(i));
			od.setItem(Integer.valueOf(items.get(i)));
			od.setSKU(SKUs.get(i));
			od.setWarehouse(warehouses.get(i));
			System.out.println(items.get(i));
			System.out.println(SKUs.get(i));
			PreparedStatement ps = conn.prepareStatement(strSql);

			ps.setString(1, od.getInvoiceName());
			ps.setDouble(2, od.getPrice());
			ps.setDouble(3, od.getInvoicePrice());
			ps.setInt(4, od.getQty());
			ps.setString(5, od.getComment());
			ps.setString(6, od.getWarehouse());
			ps.setString(7, od.getSKU());
			ps.setInt(8, od.getItem());

			ps.executeUpdate();
		}
		String strSql2 = "update orders_master" + " Set staffName = ?" + " where QR_id = ?";
		PreparedStatement ps2 = conn.prepareStatement(strSql2);
		ps2.setString(1, staffName);
		ps2.setString(2, QR_id);
		ps2.executeUpdate();
		conn.close();
	}

	public void updateMainOrderDetail(HttpServletRequest request, String QR_id)
			throws SQLException, Exception {
		Connection conn = new DataBaseConn().getConn();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String strSql = "update orders_master"
				+ " Set OutsideCode =?, OrderStatus=?, EbayNO=?, TrackingCode=?, Company=?, Platform=?,"
				+ " EbayAccount=?, OrderDate=?, PayDate=?, PayWay=?, PaypalmentId=?, ShippingDate=?, Logistics=?,"
				+ " ShippingFees=?, RefundShippingFees=?, OtherFees=?, EbayFees=?, InsuranceTotal=?, PaypalFees=?, Weight=?,"
				+ " TotalWeight=?, Size=?, Comment=?, TotalPrice=?" + " where QR_id = ?";
		PreparedStatement ps = conn.prepareStatement(strSql);
		String orderDate = request.getParameter("OrderDate");
		if (orderDate.isEmpty() || orderDate == null) {
			orderDate = sdf1.format(new java.util.Date());
		}
		String PayDate = request.getParameter("PayDate");
		if (PayDate.isEmpty() || PayDate == null) {
			PayDate = sdf1.format(new java.util.Date());
		}
		String ShippingDate = request.getParameter("ShippingDate");
		if (ShippingDate.isEmpty() || ShippingDate == null) {
			ShippingDate = sdf1.format(new java.util.Date());
		}
		ps.setString(1, request.getParameter("OutsideCode"));
		ps.setString(2, request.getParameter("OrderStatus"));
		ps.setString(3, request.getParameter("EbayNO"));
		ps.setString(4, request.getParameter("TrackingCode"));
		ps.setString(5, request.getParameter("MCompany"));
		ps.setString(6, request.getParameter("Platform"));
		ps.setString(7, request.getParameter("EbayAccount"));
		ps.setDate(8, new java.sql.Date(sdf1.parse(orderDate).getTime()));
		ps.setDate(9, new java.sql.Date(sdf1.parse(PayDate).getTime()));
		ps.setString(10, request.getParameter("PayWay"));
		ps.setString(11, request.getParameter("PaypalId"));
		ps.setDate(12, new java.sql.Date(sdf1.parse(ShippingDate).getTime()));
		ps.setString(13, request.getParameter("Logistics"));
		ps.setDouble(14, Double.valueOf(request.getParameter("ShippingFees")));
		ps.setDouble(15, Double.valueOf(request.getParameter("RefundShippingFees")));
		ps.setDouble(16, Double.valueOf(request.getParameter("OtherFees")));
		ps.setDouble(17, Double.valueOf(request.getParameter("EbayFees")));
		ps.setDouble(18, Double.valueOf(request.getParameter("InsuranceTotal")));
		ps.setDouble(19, Double.valueOf(request.getParameter("PaypalFees")));
		ps.setDouble(20, Double.valueOf(request.getParameter("Weight")));
		ps.setDouble(21, Double.valueOf(request.getParameter("TotalWeight")));
		ps.setString(22, request.getParameter("Size"));
		ps.setString(23, request.getParameter("Comment"));
		ps.setDouble(24, Double.valueOf(request.getParameter("TotalPrice")));
		ps.setString(25, QR_id);
		ps.executeUpdate();

		String strSql2 = "update orders_guestinfo"
				+ " Set GuestFirstName =?, GuestLastName=?, GuestAccount=?, Email=?, Tel1=?, Tel2=?, Mobile=?,"
				+ " Company=?, Address=?, Country=?, Postcode=?, Gender=?" + " where QR_id = ?";
		PreparedStatement ps2 = conn.prepareStatement(strSql2);
		ps2.setString(1, request.getParameter("GuestFirstName"));
		ps2.setString(2, request.getParameter("GuestLastName"));
		ps2.setString(3, request.getParameter("GuestAccount"));
		ps2.setString(4, request.getParameter("Email"));
		ps2.setString(5, request.getParameter("GTel1"));
		ps2.setString(6, request.getParameter("GTel2"));
		ps2.setString(7, request.getParameter("Mobile"));
		ps2.setString(8, request.getParameter("Company"));
		ps2.setString(9, request.getParameter("GAddress"));
		ps2.setString(10, request.getParameter("GCountry"));
		ps2.setString(11, request.getParameter("GPostcode"));
		ps2.setString(12, request.getParameter("Gender"));
		ps2.setString(13, QR_id);
		ps2.executeUpdate();

		String strSql3 = "update order_recieverinfo"
				+ " Set RecieverFirstName=?, RecieverLastName=?, Tel1=?, Tel2=?, Address=?, Country=?, PostCode=?"
				+ " where QR_id = ?";
		PreparedStatement ps3 = conn.prepareStatement(strSql3);
		ps3.setString(1, request.getParameter("RecieverFirstName"));
		ps3.setString(2, request.getParameter("RecieverLastName"));
		ps3.setString(3, request.getParameter("RTel1"));
		ps3.setString(4, request.getParameter("RTel2"));
		ps3.setString(5, request.getParameter("RAddress"));
		ps3.setString(6, request.getParameter("RCountry"));
		ps3.setString(7, request.getParameter("RPostCode"));
		ps3.setString(8, QR_id);
		ps3.executeUpdate();
		
		conn.close();
	}

	public void insertOrderDetail(HttpServletRequest request, String QR_id) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String[] strSKUArray = request.getParameterValues("SKU");
		LinkedList<String> SKUs = new LinkedList<String>(Arrays.asList(strSKUArray));
		String[] strProductName = request.getParameterValues("productName");
		LinkedList<String> productNames = new LinkedList<String>(Arrays.asList(strProductName));

		for (int i = 0; i < SKUs.size(); i++) {
			String strSql = "insert into orders_detail"
					+ " (QR_id, SKU, productName, invoiceName, price, invoicePrice, qty)"
					+ " values( ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(strSql);

			ps.setString(1, QR_id);
			ps.setString(2, SKUs.get(i));
			ps.setString(3, productNames.get(i));
			ps.setString(4, productNames.get(i));
			ps.setDouble(5, 0.0);
			ps.setDouble(6, 0.0);
			ps.setInt(7, 0);
			ps.executeUpdate();
		}
		conn.close();
	}

	public void deleteFromOrderDetail(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String strSql = "delete from orders_detail" + " where item = ?";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("item"));
		ps.executeUpdate();
		conn.close();
	}

	public void updateToProcessing(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		@SuppressWarnings("unchecked")
		LinkedList<COrders> orderslist = (LinkedList<COrders>) tw.iii.qr.daliy.servletContext.getAttribute("ndbs");
		String[] strQR_idArray = request.getParameterValues("QR_id");
		String[] strLogisticsArray = request.getParameterValues("logistics");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_idArray));
		LinkedList<String> Logistics = new LinkedList<String>(Arrays.asList(strLogisticsArray));
		// iterate over each element in LinkedList and show what is in the list.
//		Iterator<String> iterator = QR_ids.iterator();
//		Iterator<String> iterator2 = Logistics.iterator();
//		while (iterator.hasNext()) {
//			// Print element to console
//			System.out.println(iterator.next());
//		}
//		while (iterator2.hasNext()) {
//			// Print element to console
//			System.out.println(iterator2.next());
//		}
		for (int i = 0; i < QR_ids.size(); i++) {
			String strSql = "update  orders_master" + " set orderStatus = N'處理中', logistics= ? " + " where QR_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, Logistics.get(i));
			ps.setString(2, QR_ids.get(i));
			ps.executeUpdate();
			for (int j = 0; j < orderslist.size(); j++) {
				if (QR_ids.get(i).equals(orderslist.get(j).getCOrderMaster().getQR_id())) {
					orderslist.remove(j);
					break;
				}
			}
			tw.iii.qr.daliy.servletContext.setAttribute("ndbs", orderslist);
		}
		conn.close();
	}

	public void updateToPickUp(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String[] strQR_idArray = request.getParameterValues("QR_id");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_idArray));
		// iterate over each element in LinkedList and show what is in the list.
//		Iterator<String> iterator = QR_ids.iterator();
//		while (iterator.hasNext()) {
//			// Print element to console
//			System.out.println(iterator.next());
//		}
		for (int i = 0; i < QR_ids.size(); i++) {
			String strSql = "update  orders_master" + " set orderStatus = N'揀貨中' " + " where QR_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, QR_ids.get(i));
			ps.executeUpdate();
		}
		conn.close();
	}

	public void updateToComplete(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String[] strQR_ids = request.getParameterValues("QR_id");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_ids));
		// iterate over each element in LinkedList and show what is in the list.
//		Iterator<String> iterator = QR_ids.iterator();
//		while (iterator.hasNext()) {
//			// Print element to console
//			System.out.println(iterator.next());
//		}
		for (int i = 0; i < QR_ids.size(); i++) {
			String strSql = "update orders_master" + " set orderStatus = N'已出貨' " + " where QR_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, QR_ids.get(i));
			ps.executeUpdate();
		}
		conn.close();
	}

	public void updateToFinished(COrderMaster corder, Connection conn) throws Exception {
		String strSql = "update orders_master"
				+ " set orderStatus = N'已完成', shippingDate = getdate() , trackingCode = ? " + " where QR_id = ? ";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, corder.getTrackingCode());
		ps.setString(2, corder.getQR_id());
		ps.executeUpdate();
	}

	public void updateToRefund(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String[] strQR_ids = request.getParameterValues("QR_id");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_ids));
		// iterate over each element in LinkedList and show what is in the list.
//		Iterator<String> iterator = QR_ids.iterator();
//		while (iterator.hasNext()) {
//			// Print element to console
//			System.out.println(iterator.next());
//		}
		for (int i = 0; i < QR_ids.size(); i++) {
			String strSql = "update orders_master" + " set orderStatus = N'退貨' " + " where QR_id = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, QR_ids.get(i));
			ps.executeUpdate();
		}
		conn.close();
	}

	public void updateNewToCompelete(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String strSql = "update orders_master"
				+ " set orderStatus = N'已完成', shippingDate = getdate() , trackingCode = ? " + " where QR_id = ? ";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("trackingCode"));
		ps.setString(2, request.getParameter("QR_id"));
		ps.executeUpdate();
		conn.close();
	}

	public void revertTo(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String[] strQR_idArray = request.getParameterValues("QR_id");
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_idArray));
		String strSql = "update orders_master" + " set orderStatus = ? " + " where QR_id = ? ";
		for (int i = 0; i < QR_ids.size(); i++) {
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, request.getParameter("status"));
			ps.setString(2, QR_ids.get(i));
			ps.executeUpdate();
		}
		conn.close();
	}

	public void deleteUndoOrder(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String[] strQR_idArray = request.getParameterValues("QR_id");
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_idArray));
		String strSql = "delete from orders_master" + " where QR_id = ? ";
		for (int i = 0; i < QR_ids.size(); i++) {
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, QR_ids.get(i));
			ps.executeUpdate();
		}
		conn.close();
	}

	public LinkedList<COrderMaster> checkOrderIdOrderStatus(COrderMaster origincdm) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		// 準備參數 檢查前用
		boolean isCombine = false;
		LinkedList<COrderMaster> orderList = new LinkedList<COrderMaster>();
		COrderMaster order = new COrderMaster();
		// 檢查後回傳用
		LinkedList<COrderMaster> CombineOrders = new LinkedList<COrderMaster>();
		COrderMaster corder = new COrderMaster();

		String strSql = "select QR_id, orderStatus from  orders_master where QR_id = ?";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, origincdm.getQR_id());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			order = new COrderMaster();
			if (rs.getString(1).toLowerCase().startsWith("c")) {
				System.out.println("這是合併訂單 " + rs.getString(1));
				isCombine = true;
			}
			if ("已出貨".equals(rs.getString(2))) {
				order.setQR_id(rs.getString(1));
				orderList.add(order);
			}
		}
		// 如果是合併訂單 要重新找這些參數
		if (isCombine) {
			String strsqlc = "select d_qrid from comebineorder where m_cqrid = ?";
			PreparedStatement ps2 = conn.prepareStatement(strsqlc);
			ps2.setString(1, order.getQR_id());
			ResultSet rs2 = ps2.executeQuery();
			while (rs2.next()) {
				getDqrid(origincdm.getTrackingCode(), CombineOrders,rs2);
			}
		} else {
			corder = new COrderMaster();
			corder.setQR_id(order.getQR_id());
			corder.setEbayAccount(origincdm.getEbayAccount());
			corder.setOrder_id(origincdm.getOrder_id());
			corder.setLogistics(origincdm.getLogistics());
			corder.setStaffName(origincdm.getStaffName());
			CombineOrders.add(corder);
		}
		conn.close();
		return CombineOrders;

	}

	private void getDqrid(String trackingCode, LinkedList<COrderMaster> CombineOrders,ResultSet rs) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		COrderMaster corder;
		String strSqlQRId = "select om.QR_id,om.ebayaccount,om.Order_id,om.logistics,om.staffName "
				+ "from orders_master om inner join comebineorder co on om.QR_id=co.d_qrid " + "where co.d_qrid = ?";
		PreparedStatement ps = conn.prepareStatement(strSqlQRId);
		ps.setString(1, rs.getString(1));
		ResultSet rs3 = ps.executeQuery();
		while (rs3.next()) {
			corder = new COrderMaster();
			corder.setQR_id(rs3.getString(1));
			corder.setEbayAccount(rs3.getString(2));
			corder.setOrder_id(rs3.getString(3));
			corder.setLogistics(rs3.getString(4));
			corder.setStaffName(rs3.getString(5));
			corder.setTrackingCode(trackingCode);
			CombineOrders.add(corder);
		}
		conn.close();
	}

	public void insertIntoShippingLog(COrderMaster corder, Connection conn) throws Exception {
		// LinkedList<ShipmentRecord> ShippingLog = new
		// LinkedList<ShipmentRecord>();
		String strSql = "insert into  shippinglog (QR_id, date, trackingCode, staffName)"
				+ " values( ?, getdate(), ?, ?)";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, corder.getQR_id());
		ps.setString(2, corder.getTrackingCode());
		ps.setString(3, corder.getStaffName());

		ps.executeUpdate();
	}

	public void isBundleAddBackToStock(COrderMaster corder) throws Exception {
		
		LinkedList<COrderDetail> condition = getCondition(corder);

		for (int i = 0; i < condition.size(); i++) {

			//System.out.println(condition.get(i).getSKU());

			if ("B00".equals(condition.get(i).getSKU().substring(0, 3))) {

				System.out.println("bundle");
				plusBundleAddBackToStock(condition.get(i));

			} else if (!"B00".equals(condition.get(i).getSKU().substring(0, 3))) {

				System.out.println("single");
				addBackToStock(condition.get(i));
			}
		}
		
	}

	public void plusBundleAddBackToStock(COrderDetail condition) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		LinkedList<String> skulist = new LinkedList<String>();
		LinkedList<Integer> qty = new LinkedList<Integer>();
		String strsql = " select p_sku,qty from bundles where '1' = '1' and m_sku = ? ";
		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, condition.getSKU());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			skulist.add(rs.getString(1));
			System.out.println(rs.getString(1));
			qty.add(rs.getInt(2));
			System.out.println(rs.getInt(2));
		}

		rs = null;
		ps = null;
		String strSql = "update  storage set qty = (select (isnull( (select qty from storage  where sku = ?  and warehouse = ? ),0) +  (? * ?))) where sku = ? and warehouse = ?";

		for (int j = 0; j < skulist.size(); j++) {
			ps = null;
			ps = conn.prepareStatement(strSql);
			ps.setString(1, skulist.get(j));
			ps.setString(2, condition.getWarehouse());
			ps.setInt(3, qty.get(j));
			ps.setInt(4, condition.getQty());
			ps.setString(5, skulist.get(j));
			ps.setString(6, condition.getWarehouse());
			ps.executeUpdate();

		}

		ps.close();
		conn.close();
	}

	public void addBackToStock(COrderDetail condition) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String strSql = "update  storage set qty =(select qty from storage  where sku = ? and warehouse = ? ) +  ? "
				+ " where sku = ? and warehouse = ?";
		PreparedStatement ps = conn.prepareStatement(strSql);

		ps.setString(1, condition.getSKU());
		ps.setString(2, condition.getWarehouse());
		ps.setInt(3, condition.getQty());
		ps.setString(4, condition.getSKU());
		ps.setString(5, condition.getWarehouse());

		ps.executeUpdate();
		conn.close();
	}

	public void isBundledeductStock(COrderMaster corder, Connection conn) throws Exception {
		LinkedList<COrderDetail> condition = getCondition(corder);
		for (int i = 0; i < condition.size(); i++) {
			// System.out.println(condition.get(i).getSKU());
			if ("B00".equals(condition.get(i).getSKU().substring(0, 3))) {
				// System.out.println("bundle");
				plusBundledeductStock(condition.get(i));
			} else {
				// System.out.println("single");
				deductStock(condition.get(i));
			}
		}

	}

	public void plusBundledeductStock(COrderDetail condition) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		LinkedList<String> skulist = new LinkedList<String>();
		LinkedList<Integer> qty = new LinkedList<Integer>();
		String strsql = " select p_sku,qty from bundles where '1' = '1' and m_sku = ? ";
		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, condition.getSKU());
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			skulist.add(rs.getString(1));
			// System.out.println(rs.getString(1));
			qty.add(rs.getInt(2));
			// System.out.println(rs.getInt(2));
		}

		rs = null;
		ps = null;
		String strSql = "update  storage set qty = (select (isnull( (select qty from storage  where sku = ?  and warehouse = ? ),0) -  (? * ?))) where sku = ? and warehouse = ?";

		for (int j = 0; j < skulist.size(); j++) {
			ps = null;
			ps = conn.prepareStatement(strSql);
			ps.setString(1, skulist.get(j));
			ps.setString(2, condition.getWarehouse());
			ps.setInt(3, qty.get(j));
			ps.setInt(4, condition.getQty());
			ps.setString(5, skulist.get(j));
			ps.setString(6, condition.getWarehouse());
			ps.executeUpdate();

		}

		ps.close();
		conn.close();
	}

	public void deductStock(COrderDetail condition) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String strSql = "update  storage set qty =(select qty from storage  where sku = ? and warehouse = ? ) -  ? "
				+ " where sku = ? and warehouse = ?";
		PreparedStatement ps = conn.prepareStatement(strSql);

		ps.setString(1, condition.getSKU());
		ps.setString(2, condition.getWarehouse());
		ps.setInt(3, condition.getQty());
		ps.setString(4, condition.getSKU());
		ps.setString(5, condition.getWarehouse());

		ps.executeUpdate();
		conn.close();

	}

	public LinkedList<COrderDetail> getCondition(COrderMaster corder) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		LinkedList<COrderDetail> result = new LinkedList<COrderDetail>();

		String strSql = "select d.SKU,d.qty, d.warehouse, d.item, p.productType "
				+ " from orders_detail as d left join product p on d.sku = p.sku   where d.QR_id = ? ";

		PreparedStatement ps = conn.prepareStatement(strSql);

		ps.setString(1, corder.getQR_id());

		// System.out.println(strSql);
		ResultSet rs = ps.executeQuery();
		COrderDetail detail = new COrderDetail();

		while (rs.next()) {
			detail = new COrderDetail();
			if (!"調貨類".equals(rs.getString(5))) {
				detail.setSKU(rs.getString(1));
				detail.setQty(rs.getInt(2));
				detail.setWarehouse(rs.getString(3));
				detail.setItem(Integer.valueOf(rs.getString(4)));
				result.add(detail);
			}
		}
		conn.close();
		return result;
	}

	public void insertIntoPurchaseLogFromOrders(tw.iii.qr.order.DTO.COrderMaster corder, Connection conn)
			throws Exception {
		LinkedList<COrderDetail> condition = getCondition(corder);
		COrders orderInfo = getOrderAllInfo(corder.getQR_id());
		LinkedList<COrderDetail> orderDetailInfo = getOrderDetails(corder.getQR_id());
		System.out.println(orderInfo.getCOrderMaster().getQR_id());
		System.out.println(orderInfo.getCOrderDetailSingle().getSKU());

		String strSql = "insert into purchaselog_master (purchaseId, date, staffName, comment, stockStatus)"
				+ " values( ?, getdate(), ?, ?, ?)";
		// System.out.println(strSql);
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, orderInfo.getCOrderMaster().getQR_id());
		ps.setString(2, orderInfo.getCOrderMaster().getStaffName());
		ps.setString(3, orderInfo.getCOrderMaster().getComment());
		ps.setString(4, "2");
		ps.executeUpdate();
		for (int i = 0; i < condition.size(); i++) {

			if ("B00".equals(condition.get(i).getSKU().substring(0, 3))) {

				LinkedList<String> skulist = new LinkedList<String>();
				LinkedList<String> warehouse = new LinkedList<String>();
				LinkedList<Integer> qty = new LinkedList<Integer>();
				LinkedList<Double> cost = new LinkedList<Double>();
				String strsql = " select p.SKU, b.qty,warehouse , p.cost from bundles b inner join product p on b.p_SKU=p.SKU left join storage s on p.sku = s.sku where '1' = '1' and m_sku = ? ";
				ps = conn.prepareStatement(strsql);
				ps.setString(1, condition.get(i).getSKU());
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					skulist.add(rs.getString(1));
					qty.add(rs.getInt(2));
					warehouse.add(rs.getString(3));
					cost.add(rs.getDouble(4));
				}

				rs = null;
				ps = null;

				String strSql2 = "insert into purchaselog_detail (purchaseId, SKU, warehouse, qty, price, stockStatus)"
						+ " values( ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps2 = conn.prepareStatement(strSql2);
				for (int j = 0; j < skulist.size(); j++) {
					ps2.setString(1, orderInfo.getCOrderMaster().getQR_id());
					ps2.setString(2, skulist.get(j));
					ps2.setString(3, warehouse.get(j));
					ps2.setInt(4, qty.get(j) * orderDetailInfo.get(i).getQty());
					ps2.setDouble(5, cost.get(j));
					ps2.setString(6, "2");
					ps2.executeUpdate();
				}
			} else if (!"B00".equals(condition.get(i).getSKU().substring(0, 3))) {

			}
		}

		String strSql2 = "insert into purchaselog_detail (purchaseId, SKU, warehouse, qty, price, stockStatus)"
				+ " values( ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps2 = conn.prepareStatement(strSql2);
		for (int i = 0; i < orderDetailInfo.size(); i++) {
			ps2.setString(1, orderInfo.getCOrderMaster().getQR_id());
			ps2.setString(2, orderDetailInfo.get(i).getSKU());
			ps2.setString(3, orderDetailInfo.get(i).getWarehouse());
			ps2.setInt(4, orderDetailInfo.get(i).getQty());
			ps2.setDouble(5, orderDetailInfo.get(i).getPrice());
			ps2.setString(6, "2");
			ps2.executeUpdate();
		}
		
		ps.close();
		ps2.close();
		conn.close();
	}

	public LinkedList<String> getWarehouse(HttpServletRequest request) {
		if (request.getParameterValues("warehouse") != null) {
			String[] strWarehouse = request.getParameterValues("warehouse");
			// convert array to LinkedList
			LinkedList<String> Warehouses = new LinkedList<String>(Arrays.asList(strWarehouse));
			// iterate over each element in LinkedList and show what is in the
			// list.
			return Warehouses;
		}
		return null;
	}

	public void checkUrlToRemoveSession(HttpServletRequest request, HttpSession session) {
		String referer = request.getHeader("Referer");
		if (referer == null) {
			return;
		}
		if (!(referer.substring(0, referer.lastIndexOf("p")) + "p").equals(request.getRequestURL().toString())) {
			// System.out.println(referer.substring(0, referer.lastIndexOf("p"))
			// + "p");
			// System.out.println(request.getRequestURL().toString());
			session.removeAttribute("SearchOrdersResult");
		}
	}

	public LinkedList<String> unSelectedList(HttpServletRequest request) {

		String[] strQR_idArray = request.getParameterValues("QR_id");
		String[] strLogisticsArray = request.getParameterValues("logistics");
		// convert array to LinkedList
		LinkedList<String> QR_ids = new LinkedList<String>(Arrays.asList(strQR_idArray));
		LinkedList<String> Logistics = new LinkedList<String>(Arrays.asList(strLogisticsArray));
		// iterate over each element in LinkedList and show what is in the list.

		LinkedList<String> unSelected = new LinkedList<>();
		for (int i = 0; i < QR_ids.size(); i++) {
			if (Logistics.get(i) != "請選擇") {

			} else {
				unSelected.add(QR_ids.get(i));
			}
		}
		return unSelected;
	}

	public static LinkedList<ShipmentRecord> searchShipmentRecord(HttpServletRequest request) throws Exception {
		Connection conn = new DataBaseConn().getConn();
		String strSql = "select s.date, s.QR_id, s.type, m.eBayAccount, d.SKU, d.productName, d.qty,"
				+ " r.country, d.owner, d.warehouse, m.staffName, s.comment, s.trackingCode"
				+ " from orders_master as m inner join shippinglog as s on m.QR_id = s.QR_id"
				+ " inner join order_recieverinfo as r on m.QR_id = r.QR_id"
				+ " inner join orders_detail as d on m.QR_id = d.QR_id" + " where '1' = '1' ";

		String eBayAccount = request.getParameter("eBayAccount");
		if (!isNullorEmpty(eBayAccount)) {
			strSql += " and m.eBayAccount = ? ";
		}
		;
		String type = request.getParameter("type");
		if (!isNullorEmpty(type)) {
			strSql += " and s.type like ? ";
		}
		;
		String owner = request.getParameter("owner");
		if (!isNullorEmpty(owner)) {
			strSql += " and d.owner like ? ";
		}
		;
		String QR_id = request.getParameter("QR_id");
		if (!isNullorEmpty(QR_id)) {
			strSql += " and m.QR_id like ? ";
		}
		;
		String country = request.getParameter("country");
		if (!isNullorEmpty(country)) {
			strSql += " and r.country like ? ";
		}
		;
		String SKU = request.getParameter("SKU");
		if (!isNullorEmpty(SKU)) {
			strSql += " and d.SKU like ? ";
		}
		;
		String productName = request.getParameter("productName");
		if (!isNullorEmpty(productName)) {
			strSql += " and d.productName like ? ";
		}
		;
		String warehouse = request.getParameter("warehouse");
		if (!isNullorEmpty(warehouse)) {
			strSql += " and d.warehouse like ? ";
		}
		;
		String staffName = request.getParameter("staffName");
		if (!isNullorEmpty(staffName)) {
			strSql += " and m.staffName like ? ";
		}
		;
		String comment = request.getParameter("comment");
		if (!isNullorEmpty(comment)) {
			strSql += " and m.comment like ? ";
		}
		;
		String shippingDateMin = request.getParameter("shippingDateMin");
		if (!isNullorEmpty(shippingDateMin)) {
			strSql += " and m.shippingDate >= ? ";
		}
		String shippingDateMax = request.getParameter("shippingDateMax");
		if (!isNullorEmpty(shippingDateMax)) {
			strSql += " and m.shippingDate <= ? ";
		}

		LinkedList<ShipmentRecord> shipmentRecord = new LinkedList<ShipmentRecord>();
		PreparedStatement ps = conn.prepareStatement(strSql);
		int param = 1;

		if (!isNullorEmpty(eBayAccount)) {
			ps.setString(param, eBayAccount);
			param++;
		}
		if (!isNullorEmpty(type)) {
			ps.setString(param, "%" + type + "%");
			param++;
		}
		if (!isNullorEmpty(owner)) {
			ps.setString(param, "%" + owner + "%");
			param++;
		}
		if (!isNullorEmpty(QR_id)) {
			ps.setString(param, "%" + QR_id + "%");
			param++;
		}
		if (!isNullorEmpty(country)) {
			ps.setString(param, "%" + country + "%");
			param++;
		}
		if (!isNullorEmpty(SKU)) {
			ps.setString(param, "%" + SKU + "%");
			param++;
		}
		if (!isNullorEmpty(productName)) {
			ps.setString(param, "%" + productName + "%");
			param++;
		}
		if (!isNullorEmpty(warehouse)) {
			ps.setString(param, "%" + warehouse + "%");
			param++;
		}
		if (!isNullorEmpty(staffName)) {
			ps.setString(param, "%" + staffName + "%");
			param++;
		}
		if (!isNullorEmpty(comment)) {
			ps.setString(param, "%" + comment + "%");
			param++;
		}
		if (!isNullorEmpty(shippingDateMin)) {
			ps.setString(param, shippingDateMin);
			param++;
		}
		if (!isNullorEmpty(shippingDateMax)) {
			ps.setString(param, shippingDateMax);
			param++;
		}

		System.out.println(strSql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			ShipmentRecord record = new ShipmentRecord();
			record.setShippingDate(rs.getDate(1));
			record.setQR_id(rs.getString(2));
			record.setType(rs.getString(3));
			record.setEbayAccount(rs.getString(4));
			record.setSKU(rs.getString(5));
			record.setProductName(rs.getString(6));
			record.setQty(rs.getInt(7));
			record.setCountry(rs.getString(8));
			record.setOwner(rs.getString(9));
			record.setWarehouse(rs.getString(10));
			record.setStaffName(rs.getString(11));
			record.setComment(rs.getString(12));
			record.setTrackingCode(rs.getString(13));
			shipmentRecord.add(record);
			
		}
		conn.close();
		return shipmentRecord;
	}

	public LinkedList<String> getGuestAccounts() throws Exception {
		Connection conn = new DataBaseConn().getConn();
		LinkedList<String> guestAccounts = new LinkedList<String>();
		String strSql = "select guestaccount,count(*)" + " from orders_master" + " group by guestaccount"
				+ " having count(*) > 1";

		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			guestAccounts.add(rs.getString(1));
		}
		return guestAccounts;
	}

	public LinkedList<String> getEbayAccounts() throws Exception {
		Connection conn = new DataBaseConn().getConn();
		LinkedList<String> getEbayAccounts = new LinkedList<String>();
		String strSql = "select ebayid" + " from ebayaccount" + " where status = 'ON'";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			getEbayAccounts.add(rs.getString(1));
		}
		return getEbayAccounts;
	}

	public LinkedList<String> getWarehouses(HttpServletRequest request, String SKU)
			throws IllegalAccessException, ClassNotFoundException, Exception {

		Connection conn = new DataBaseConn().getConn();
		LinkedList<String> warehouses = new LinkedList<String>();
		// CStock myCStock = new CStock();
		String strSql = "select d.SKU, s.warehouse" + " from orders_detail as d left join storage s on d.sku = s.sku"
				+ " where d.QR_id = ? and d.SKU = ?";

		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, request.getParameter("QR_id"));
		ps.setString(2, SKU);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			warehouses.add(rs.getString(2));
		}
		return warehouses;
	}

	private static boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public boolean checkboxIsNullorEmpty(String a, String b, String c, String d) {

		if (isNullorEmpty(a) && isNullorEmpty(b) && isNullorEmpty(c) && isNullorEmpty(d))
			return true;

		return false;
	}

	// private boolean checkboxAreUnchecked(String a, String b, String c, String
	// d, String e, String f, String g, String h,
	// String i, String j, String k) {
	// if (isNullorEmpty(a) && isNullorEmpty(b) && isNullorEmpty(c) &&
	// isNullorEmpty(d) && isNullorEmpty(e)
	// && isNullorEmpty(f) && isNullorEmpty(h) && isNullorEmpty(i) &&
	// isNullorEmpty(j) && isNullorEmpty(k))
	// return true;
	//
	// return false;
	// }

	private static boolean checkboxAreUnchecked(String a, String b, String c, String d, String e, String f, String g,
			String h, String i) {
		if (isNullorEmpty(a) && isNullorEmpty(b) && isNullorEmpty(c) && isNullorEmpty(d) && isNullorEmpty(e)
				&& isNullorEmpty(f) && isNullorEmpty(h) && isNullorEmpty(i))
			return true;
		return false;
	}
}
