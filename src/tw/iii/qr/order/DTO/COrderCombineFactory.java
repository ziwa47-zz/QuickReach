package tw.iii.qr.order.DTO;

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

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.CreateOrderId;

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
				+ "and DATEDIFF(MONTH,m1.orderDate,GETDATE()) <5 " + "order  by m1.payDate Desc";

		// // 同一個人1周內下了兩次以上的訂單 而且尚未合併 (正確篩選 但先註解)
		// String sqlstr = " select m1.guestAccount,m1.QR_id,
		// m1.ebayNO,m1.payDate,m2.QR_id,m2.ebayNO,m2.payDate "
		// + "from orders_master m1 inner join orders_master m2 on
		// m1.guestAccount=m2.guestAccount "
		// + "where m1.QR_id<m2.QR_id " + "and isnull(m1.isCombine,0) !=1 " +
		// "and isnull(m2.isCombine,0) !=1 "
		// + "and DATEDIFF(Day,m1.orderDate,m2.orderDate)<7 " + "and
		// DATEDIFF(Day,m1.orderDate,m2.orderDate)>-7 "
		// + "order by m1.payDate Desc";

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
			System.out.println(rs2.getString(1));
		}
		return pic;
	}

	// create
	public String CombineOrders(HttpServletRequest request, Connection conn) throws Exception {
		// 新增合併訂單
		// insert into comebineorder values('合併001','2016121603ebay015')
		// insert into comebineorder values('合併001','2016121603ebay104')
		// 修改合併狀態
		// update orders_master set isCombine=1 where QR_id =
		// '2016121603ebay015'
		// update orders_master set isCombine=1 where QR_id =
		// '2016121603ebay104'

		conn.setAutoCommit(false);
		Savepoint sp = null;

		// 檢查guestAccount是否大於1 超過就回家

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
				System.out.println(sqlstr);
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

			// 新增合併訂單的Detail
			InsertCombineDetail(param, conn);
			// 修改合併狀態

			// 修改狀態跟補上CQRID
			for (int i = 0; i < specqrId.size(); i++) {
				String sqlstr2 = " update orders_master set isCombine=1 ,CombineSku=? where QR_id = ?";
				System.out.println(specqrId.get(i));
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
			conn.rollback(sp);
			return "Failed 取消";
		}
		return "success";
	}

	// update
	public void UpdateCombineOrder(HttpServletRequest request, Connection conn) {

		// 選許已合併訂單以修改
		String strsql = "select * from orders_master where isCombine =1";
		// 合併訂單內容
		String strsql2 = "select * from orders_master m inner join comebineorder c on m.combinesku = c.m_sku";
	}

	// Delete
	public void DeCombine(HttpServletRequest request, Connection conn) {
		// 解除合併
		String sqlstr = " update orders_master set isCombine=0 where QR_id = ?";
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
			cocd = new  COrderCombineDetail();
			cocd.setM_cqrid(rs.getString(1));
			cocd.setD_qrid(rs.getString(2));
			cocd.setD_ebayno(rs.getString(3));
			cocd.setGuestAccount(rs.getString(4));
			cocd.setCombineDate(rs.getDate(5));
			list.add(cocd);
		}
		return list;
	}

	private void InsertCombineDetail(HashMap<String, Object> param, Connection conn) {
		// 其實還是寫到原本的Orders_Detail 不過 QRID 是 C開頭所以沒影響

		// param.put("CQRID", CQRID);
		// param.put("QRID", specqrId);
		// param.put("EBAYNO", specEbayNO);
		// param.put("GUESTACCOUNT", specguestAccount);
		// param.put("DATE", new java.sql.Date(date));

		// 先準備參數
		PreparedStatement ps = null;
		ResultSet rs = null;
		COrderDetail od= new COrderDetail();
		String cQrId = (String) param.get("CQRID");
		LinkedList<String> qRIDs = (LinkedList<String>) param.get("QRID");
		LinkedList<String> eBayNos = (LinkedList<String>) param.get("EBAYNO");
		String guestAccount = (String) param.get("GUESTACCOUNT");
		java.sql.Date date = (java.sql.Date) param.get("DATE");

		// 先找出各個QRID的DETAIL SKU
		try {
			for (int i = 0; i < qRIDs.size(); i++) {
				String strsqlDetail = "select SKU,productName,invoiceName,price,invoicePrice,qty,warehouse,comment from  orders_detail where QR_id = ?";
				ps = conn.prepareStatement(strsqlDetail);
				ps.setString(1, qRIDs.get(i));
				
				rs = ps.executeQuery();
				
				while(rs.next()){
					od.setSKU(rs.getString(1));
					
				}
			}
		} catch (Exception e) {

		}

		// 寫入Orders_Detail
		String strSql = "INSERT INTO orders_detail (QR_id, order_id, SKU, productName, invoiceName"
				+ ", price, invoicePrice, qty, comment )" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	}

}
