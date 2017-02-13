package tw.iii.qr.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import tw.iii.qr.CreateOrderId;
import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.DTO.COrderCombine;
import tw.iii.qr.order.DTO.COrderCombineDetail;
import tw.iii.qr.order.DTO.COrderDetail;
import tw.iii.qr.order.DTO.COrderGuestInfo;
import tw.iii.qr.order.DTO.COrderMaster;
import tw.iii.qr.order.DTO.COrderReciever;
import tw.iii.qr.order.DTO.GuestAccountAndOrder;

public class COrderCombineFactory {
	public COrderCombineFactory() {
	}

	// Read
	public LinkedList<COrderCombine> canCombine(HttpServletRequest request, Connection conn) throws Exception {

		// select m1.orderDate as 'M1 orderDate',m2.orderDate as 'M2 orderDate',
		// m1.ebayNO,m2.ebayNO,m1.guestAccount,m1.ebayNO from orders_master m1
		// inner join orders_master m2 on m1.guestAccount=m2.guestAccount where
		// m1.QR_id<m2.QR_id and DATEDIFF(Day,m1.orderDate,m2.orderDate)<14 and
		// DATEDIFF(Day,m1.orderDate,m2.orderDate) >-14 and
		// DATEDIFF(MONTH,m1.orderDate,GETDATE()) <1 order by m1.orderDate Desc

		// 一個月裡面 同一個人兩周內下了兩次以上的訂單 而且尚未合併 (大放送 之後要註解掉)
		String sqlstr = " select  m1.guestAccount,m1.QR_id, m1.ebayNO,m1.payDate,m2.QR_id,m2.ebayNO,m2.payDate "
				+ "from orders_master m1 inner join orders_master m2 on m1.guestAccount=m2.guestAccount "
				+ "where m1.QR_id<m2.QR_id  " + "and isnull(m1.isCombine,0) !=1 " + "and isnull(m2.isCombine,0) !=1 "
				+ "and DATEDIFF(Day,m1.orderDate,m2.orderDate)<14 " + "and DATEDIFF(Day,m1.orderDate,m2.orderDate)>-14 "
				+ "and DATEDIFF(MONTH,m1.orderDate,GETDATE()) <5  " + "order  by m1.payDate Desc";

		// // 同一個人1周內下了兩次以上的訂單 而且尚未合併 (正確篩選 但先註解)
		// String sqlstr = " select m1.guestAccount,m1.QR_id,
		// m1.ebayNO,m1.payDate,m2.QR_id,m2.ebayNO,m2.payDate "
		// + "from orders_master m1 inner join orders_master m2 on
		// m1.guestAccount=m2.guestAccount "
		// + "where m1.QR_id<m2.QR_id " + "and isnull(m1.isCombine,0) !=1 " +
		// "and isnull(m2.isCombine,0) !=1 "
		// + "and DATEDIFF(Day,m1.orderDate,m2.orderDate)<7 " + "and
		// DATEDIFF(Day,m1.orderDate,m2.orderDate)>-7 "
		// + " and m1.orderStatus != '待處理' and m2.orderStatus != '待處理' " +
		// "order by m1.payDate Desc";

		PreparedStatement ps = conn.prepareStatement(sqlstr);
		ResultSet rs = ps.executeQuery();

		COrderCombine coc = new COrderCombine();
		LinkedList<COrderCombine> list = new LinkedList<COrderCombine>();
		while (rs.next()) {
			coc = new COrderCombine();
			coc.setGuestAccount(rs.getString(1));
			coc.setQR_Id1(rs.getString(2));
			coc.setEbayNO1(rs.getInt(3));
			coc.setPicturePath1(GetPic(rs.getString(2), conn));
			coc.setPayTime1(rs.getDate(4));
			coc.setQR_Id2(rs.getString(5));
			coc.setEbayNO2(rs.getInt(6));
			coc.setPicturePath2(GetPic(rs.getString(5), conn));
			coc.setPayTime2(rs.getDate(7));
			list.add(coc);
		}
		return list;

	}

	public LinkedList<String> GetPic(String qrid, Connection conn) throws Exception {
		LinkedList<String> pic = new LinkedList<>();
		String strsql2 = "select p.picturePath from product p inner join orders_detail d on p.SKU=d.SKU"
				+ " where  d.QR_id =  ?";
		PreparedStatement ps2 = conn.prepareStatement(strsql2);
		ps2.setString(1, qrid);
		ResultSet rs2 = ps2.executeQuery();
		while (rs2.next()) {
			pic.add(rs2.getString(1));
			// System.out.println(rs2.getString(1));
		}
		return pic;
	}

	// create
	public String CombineOrders(HttpServletRequest request, Connection conn) throws Exception {

		conn.setAutoCommit(false);
		Savepoint sp = null;

		// 處理前端傳過來的 EBAYNO,EBAYNO,GuestACCOUNT陣列
		String[] EbayNOs = request.getParameterValues("EbayNO");
		LinkedList<String> EbayNO = new LinkedList<>();
		LinkedList<String> qrId = new LinkedList<>();
		HashSet<String> guestAccount = new HashSet<>();
		for (int i = 0; i < EbayNOs.length; i++) {
			String[] colle = EbayNOs[i].split(",");
			System.out.println(
					colle.length + " " + colle[0] + " " + colle[1] + " " + colle[2] + " " + colle[3] + " " + colle[4]);
			EbayNO.add(colle[0]);
			EbayNO.add(colle[1]);
			guestAccount.add(colle[2]);
			qrId.add(colle[3]);
			qrId.add(colle[4]);
		}
		String specguestAccount = "";
		// 如果GA不一樣就回家
		if (guestAccount.size() > 1) {
			return guestAccount.size() + " Failed 請選擇相同的帳號";
		} else {
			Iterator iterator = guestAccount.iterator();
			while (iterator.hasNext())
				specguestAccount = (String) iterator.next();
		}

		// 如果EBAYNO重複就不要
		LinkedList<String> specEbayNO = new LinkedList<>();
		for (String EN : EbayNO) {
			if (Collections.frequency(specEbayNO, EN) < 1)
				specEbayNO.add(EN);

		}
		// 去掉相同的QRID
		LinkedList<String> specqrId = new LinkedList<>();
		for (String qrid : qrId) {
			if (Collections.frequency(specqrId, qrid) < 1)
				specqrId.add(qrid);

		}

		long date = new java.util.Date().getTime();

		try {

			// 給 CQRID
			String CQRID = "C" + CreateOrderId.generateCQR_Id();

			PreparedStatement ps = null;

			sp = conn.setSavepoint();

			for (int i = 0; i < specEbayNO.size(); i++) {
				// 新增合併訂單
				String sqlstr = "insert into comebineorder values(?,?,?,?,?)";
				// System.out.println(sqlstr);
				ps = conn.prepareStatement(sqlstr);

				ps.setString(1, CQRID);
				ps.setString(2, specqrId.get(i));
				ps.setString(3, specEbayNO.get(i));
				ps.setString(4, specguestAccount);
				ps.setDate(5, new java.sql.Date(date));
				ps.execute();

			}
			// 準備要給DETAIL的參數
			HashMap<String, Object> param = new HashMap<>();
			param.put("CQRID", CQRID);
			param.put("QRID", specqrId);
			param.put("EBAYNO", specEbayNO);
			param.put("GUESTACCOUNT", specguestAccount);
			param.put("DATE", new java.sql.Date(date));

			// 新增合併訂單的Master
			boolean isInsertMaster = InsertCombineMaster(param, conn);
			if (!isInsertMaster) {
				throw new Exception("寫入ORDERMaster失敗");
			}

			// 新增合併訂單的Detail
			boolean isInsertDetail = InsertCombineDetail(param, conn);
			if (!isInsertDetail) {
				throw new Exception("寫入ORDERDetail失敗");
			}
			// 新增合併訂單的Reciver
			boolean isInsertReciverInfo = InsertCombineReciverInfo(param, conn);
			if (!isInsertReciverInfo) {
				throw new Exception("寫入ORDERReciver失敗");
			}
			// 新增合併訂單的Guest
			boolean isInsertGuestInfo = InsertCombineGuestInfo(param, conn);
			if (!isInsertGuestInfo) {
				throw new Exception("寫入ORDERGuest失敗");
			}

			// 修改合併狀態

			// 修改狀態跟補上CQRID
			for (int i = 0; i < specqrId.size(); i++) {
				String sqlstr2 = " update orders_master set isCombine=1 ,CombineSku=? where QR_id = ?";
				// System.out.println(specqrId.get(i));
				ps = conn.prepareStatement(sqlstr2);
				ps.setString(1, CQRID);
				ps.setString(2, specqrId.get(i));
				ps.executeUpdate();
			}
			ps.close();
			conn.commit();
			conn.close();
			System.out.println(CQRID + " success");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback(sp);
			return "Failed 取消";
		}
		return "success";
	}

	private boolean InsertCombineReciverInfo(HashMap<String, Object> param, Connection conn) {
		// 其實還是寫到原本的Orders_Detail 不過 QRID 是 C開頭所以沒影響

		// param.put("CQRID", CQRID);
		// param.put("QRID", specqrId);
		// param.put("EBAYNO", specEbayNO);
		// param.put("GUESTACCOUNT", specguestAccount);
		// param.put("DATE", new java.sql.Date(date));

		// 先準備參數
		PreparedStatement ps = null;
		ResultSet rs = null;
		COrderReciever cor = new COrderReciever();
		String cQrId = (String) param.get("CQRID");
		LinkedList<String> qRIDs = (LinkedList<String>) param.get("QRID");
		LinkedList<String> eBayNos = (LinkedList<String>) param.get("EBAYNO");
		String guestAccount = (String) param.get("GUESTACCOUNT");
		java.sql.Date date = (java.sql.Date) param.get("DATE");

		// 先找出各個QRID的DETAIL SKU
		cor = getInsertDataToOrderReciever(conn, cQrId, qRIDs);

		return insertCOrderReciever(conn, cor) ? true : false;

	}

	private boolean InsertCombineGuestInfo(HashMap<String, Object> param, Connection conn) {
		// 其實還是寫到原本的Orders_Detail 不過 QRID 是 C開頭所以沒影響

		// param.put("CQRID", CQRID);
		// param.put("QRID", specqrId);
		// param.put("EBAYNO", specEbayNO);
		// param.put("GUESTACCOUNT", specguestAccount);
		// param.put("DATE", new java.sql.Date(date));

		// 先準備參數
		PreparedStatement ps = null;
		ResultSet rs = null;
		COrderGuestInfo og = new COrderGuestInfo();
		String cQrId = (String) param.get("CQRID");
		LinkedList<String> qRIDs = (LinkedList<String>) param.get("QRID");
		LinkedList<String> eBayNos = (LinkedList<String>) param.get("EBAYNO");
		String guestAccount = (String) param.get("GUESTACCOUNT");
		java.sql.Date date = (java.sql.Date) param.get("DATE");

		// 先找出各個QRID的DETAIL SKU
		og = getInsertDataToOrderGuest(conn, cQrId, qRIDs);

		return insertCOrderGuest(conn, og) ? true : false;

	}

	private boolean insertCOrderGuest(Connection conn, COrderGuestInfo og) {
		String strSql = "INSERT INTO orders_guestinfo (QR_id,guestFirstName, guestLastName, guestAccount, email)"
				+ " VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(strSql);
			ps.setString(1, og.getQR_id());
			ps.setString(2, og.getGuestFirstName());
			ps.setString(3, og.getGuestLastName());
			ps.setString(4, og.getGuestAccount());
			ps.setString(5, og.getEmail());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		System.out.println("OGuest寫入成功!");
		return true;
	}

	private COrderGuestInfo getInsertDataToOrderGuest(Connection conn, String cQrId, LinkedList<String> qRIDs) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		COrderGuestInfo og = new COrderGuestInfo();
		String strsqlDetail = " select guestFirstName, guestLastName, guestAccount, email from orders_guestinfo where QR_id = ?";
		try {
			for (int i = 0; i < qRIDs.size(); i++) {
				ps = conn.prepareStatement(strsqlDetail);
				ps.setString(1, qRIDs.get(i));
				rs = ps.executeQuery();
				while (rs.next()) {
					og = new COrderGuestInfo();
					og.setQR_id(cQrId);
					og.setGuestFirstName(rs.getString(1));
					og.setGuestLastName(rs.getString(2));
					og.setGuestAccount(rs.getString(3));
					og.setEmail(rs.getString(4));
				}
			}
			return og;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean insertCOrderReciever(Connection conn, COrderReciever cor) {
		String strSql = "INSERT INTO order_recieverinfo (QR_id, recieverFirstName, tel1, address, country, postCode)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(strSql);
			ps.setString(1, cor.getQR_id());
			ps.setString(2, cor.getRecieverFirstName());
			ps.setString(3, cor.getTel1());
			ps.setString(4, cor.getAddress());
			ps.setString(5, cor.getCountry());
			ps.setString(6, cor.getPostCode());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		System.out.println("OReciever寫入成功!");
		return true;
	}

	private COrderReciever getInsertDataToOrderReciever(Connection conn, String cQrId, LinkedList<String> qRIDs) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		COrderReciever cor = new COrderReciever();
		String strsqlDetail = " select recieverFirstName,tel1,address,country,postCode from order_recieverinfo where QR_id = ?";
		try {
			for (int i = 0; i < qRIDs.size(); i++) {
				ps = conn.prepareStatement(strsqlDetail);
				ps.setString(1, qRIDs.get(i));
				rs = ps.executeQuery();
				while (rs.next()) {
					COrderDetail od = new COrderDetail();
					cor.setQR_id(cQrId);
					cor.setRecieverFirstName(rs.getString(1));
					cor.setTel1(rs.getString(2));
					cor.setAddress(rs.getString(3));
					cor.setCountry(rs.getString(4));
					cor.setPostCode(rs.getString(5));
				}
			}
			return cor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean InsertCombineMaster(HashMap<String, Object> param, Connection conn) {
		// 其實還是寫到原本的Orders_Master 不過 QRID 是 C開頭所以沒影響

		// param.put("CQRID", CQRID);
		// param.put("QRID", specqrId);
		// param.put("EBAYNO", specEbayNO);
		// param.put("GUESTACCOUNT", specguestAccount);
		// param.put("DATE", new java.sql.Date(date));

		// 先準備參數
		PreparedStatement ps = null;
		ResultSet rs = null;
		COrderMaster om = new COrderMaster();
		String cQrId = (String) param.get("CQRID");
		LinkedList<String> qRIDs = (LinkedList<String>) param.get("QRID");
		LinkedList<String> eBayNos = (LinkedList<String>) param.get("EBAYNO");
		String guestAccount = (String) param.get("GUESTACCOUNT");
		java.sql.Date date = (java.sql.Date) param.get("DATE");

		// 先找出各個QRID的Master SKU
		om = getInsertDataToOrderMaster(conn, cQrId, qRIDs);

		return insertCOrderMaster(conn, om) ? true : false;
	}

	private boolean insertCOrderMaster(Connection conn, COrderMaster om) {
		String strSql = "INSERT INTO orders_master(QR_id, platform,eBayAccount, guestAccount, "
				+ " orderStatus,paypal_id,payment,paypalFees, "
				+ " ebayFees, totalPrice, currency, ebayPrice, paypalNet,isCombine )"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(strSql);
			ps.setString(1, om.getQR_id());
			ps.setString(2, "ebay");
			ps.setString(3, om.getEbayAccount());
			ps.setString(4, om.getGuestAccount());
			ps.setString(5, om.getOrderStatus());
			ps.setString(6, om.getPaypalId());
			ps.setDouble(7, om.getPayment());
			ps.setDouble(8, om.getPaypalFees());
			ps.setDouble(9, om.getEbayFees());
			ps.setDouble(10, om.getTotalPrice());
			ps.setString(11, om.getCurrency());
			ps.setDouble(12, om.getEbayPrice());
			ps.setDouble(13, om.getPaypalNet());
			ps.setString(14, "2");

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		System.out.println("OMaster寫入成功!");
		return true;
	}

	private COrderMaster getInsertDataToOrderMaster(Connection conn, String cQrId, LinkedList<String> qRIDs) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		COrderMaster om = new COrderMaster();
		Double payment = 0.0;
		Double paypalFees = 0.0;
		Double ebayFees = 0.0;
		Double totalPrice = 0.0;
		Double ebayPrice = 0.0;
		Double paypalNet = 0.0;

		String strsqlMaster = "select QR_id, eBayAccount, guestAccount,paypal_id,payment,paypalFees,ebayFees, totalPrice, currency, ebayPrice, paypalNet from orders_master where QR_id = ?";
		try {
			for (int i = 0; i < qRIDs.size(); i++) {
				ps = conn.prepareStatement(strsqlMaster);
				ps.setString(1, qRIDs.get(i));
				rs = ps.executeQuery();
				while (rs.next()) {
					om = new COrderMaster();
					om.setEbayAccount(rs.getString(2));
					om.setGuestAccount(rs.getString(3));
					om.setPaypalId(rs.getString(4));
					payment += rs.getDouble(5);
					paypalFees += rs.getDouble(6);
					ebayFees += rs.getDouble(7);
					totalPrice += rs.getDouble(8);
					om.setCurrency(rs.getString(9));
					ebayPrice += rs.getDouble(10);
					paypalNet += rs.getDouble(11);
				}
			}
			om.setQR_id(cQrId);
			om.setPayment(payment);
			om.setPaypalFees(paypalFees);
			om.setEbayFees(ebayFees);
			om.setTotalPrice(totalPrice);
			om.setEbayPrice(ebayPrice);
			om.setPaypalNet(paypalNet);

			return om;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public LinkedList<GuestAccountAndOrder> HasCombineOrderGuest(HttpServletRequest request, Connection conn)
			throws Exception {

		// 找到七天內有合併訂單的使用者
		// select distinct guestAccount from comebineorder where
		// DATEDIFF(day,combineDate,getDate())<7
		String datecount = request.getParameter("DATECOUNT");
		PreparedStatement ps = null;
		String sqlstr = "";
		System.out.println("查" + request.getParameter("DATECOUNT") + "天內的合併訂單");
		// radio button value 7 14 -1 預設7天
		if (Integer.parseInt(datecount) > 0) {
			// 如果選7或14天
			sqlstr = "select distinct guestAccount,m_cqrid from comebineorder where DATEDIFF(DAY,combineDate,getDate())< ? order by m_cqrid desc";
			ps = conn.prepareStatement(sqlstr);
			ps.setString(1, datecount);
		} else {
			// 查詢全部合併訂單
			sqlstr = "select distinct guestAccount,m_cqrid from comebineorder where DATEDIFF(DAY,combineDate,getDate())>=0 order by m_cqrid desc";
			ps = conn.prepareStatement(sqlstr);
		}

		ResultSet rs = ps.executeQuery();
		LinkedList<GuestAccountAndOrder> guestAccount = new LinkedList<>();
		GuestAccountAndOrder ga;
		while (rs.next()) {
			ga = new GuestAccountAndOrder();
			ga.setGuestAccount(rs.getString(1));
			ga.setOrder(rs.getString(2));
			guestAccount.add(ga);
		}
		return guestAccount;

	}

	public LinkedList<COrderCombineDetail> ReadCombineOrder(HttpServletRequest request, Connection conn)
			throws Exception {
		// 前端傳來的參數 下拉式選單的值
		String cqrid = request.getParameter("CQRID");

		String strsql = "select m_cqrid,d_qrid,d_ebayno,guestAccount,combineDate from comebineorder where m_cqrid = ?";
		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, cqrid);
		ResultSet rs = ps.executeQuery();
		LinkedList<COrderCombineDetail> list = new LinkedList<>();
		COrderCombineDetail cocd = new COrderCombineDetail();
		while (rs.next()) {
			cocd = new COrderCombineDetail();
			cocd.setM_cqrid(rs.getString(1));
			cocd.setD_qrid(rs.getString(2));
			cocd.setD_ebayno(rs.getString(3));
			cocd.setGuestAccount(rs.getString(4));
			cocd.setCombineDate(rs.getDate(5));
			list.add(cocd);
		}
		return list;
	}

	private Boolean InsertCombineDetail(HashMap<String, Object> param, Connection conn) throws Exception {
		// 其實還是寫到原本的Orders_Detail 不過 QRID 是 C開頭所以沒影響

		// param.put("CQRID", CQRID);
		// param.put("QRID", specqrId);
		// param.put("EBAYNO", specEbayNO);
		// param.put("GUESTACCOUNT", specguestAccount);
		// param.put("DATE", new java.sql.Date(date));

		// 先準備參數
		PreparedStatement ps = null;
		ResultSet rs = null;
		COrderDetail od = new COrderDetail();
		String cQrId = (String) param.get("CQRID");
		LinkedList<String> qRIDs = (LinkedList<String>) param.get("QRID");
		LinkedList<String> eBayNos = (LinkedList<String>) param.get("EBAYNO");
		String guestAccount = (String) param.get("GUESTACCOUNT");
		java.sql.Date date = (java.sql.Date) param.get("DATE");

		// 先找出各個QRID的DETAIL SKU
		LinkedList<COrderDetail> ods = getInsertDataToOrderDetail(conn, cQrId, qRIDs);

		return insertCOrderDetail(conn, ods) ? true : false;

	}

	private boolean insertCOrderDetail(Connection conn, LinkedList<COrderDetail> ods) throws Exception {

		String strSql = "INSERT INTO orders_detail (QR_id, SKU, productName, invoiceName"
				+ ", price, invoicePrice, qty, comment )" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;

		for (COrderDetail od : ods) {
			try {
				ps = conn.prepareStatement(strSql);
				ps.setString(1, od.getQR_id());
				ps.setString(2, od.getSKU());
				ps.setString(3, od.getProductName());
				ps.setString(4, od.getInvoiceName());
				ps.setDouble(5, od.getPrice());
				ps.setDouble(6, od.getInvoicePrice());
				ps.setInt(7, od.getQty());
				ps.setString(8, od.getComment());
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("ODetail寫入成功!");
		return true;
	}

	private LinkedList<COrderDetail> getInsertDataToOrderDetail(Connection conn, String cQrId, LinkedList<String> qRIDs)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		LinkedList<COrderDetail> ods = new LinkedList<>();
		String strsqlDetail = "select SKU,productName,invoiceName,price,invoicePrice,qty,comment from  orders_detail where QR_id = ?";
		for (int i = 0; i < qRIDs.size(); i++) {
			ps = conn.prepareStatement(strsqlDetail);
			ps.setString(1, qRIDs.get(i));
			rs = ps.executeQuery();
			while (rs.next()) {
				COrderDetail od = new COrderDetail();
				od.setQR_id(cQrId);
				od.setSKU(rs.getString(1));
				od.setProductName(rs.getString(2));
				od.setInvoiceName(rs.getString(3));
				od.setPrice(rs.getDouble(4));
				od.setInvoicePrice(rs.getDouble(5));
				od.setQty(rs.getInt(6));
				od.setComment(rs.getString(7));
				ods.add(od);
			}
		}
		rs.close();
		ps.close();
		return ods;
	}

}
