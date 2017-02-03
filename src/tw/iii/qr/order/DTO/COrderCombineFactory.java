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

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import tw.iii.qr.order.CreateOrderId;

public class COrderCombineFactory {
	public COrderCombineFactory() {
	}

	// Read
	public LinkedList<COrderCombine> canCombine(HttpServletRequest request, Connection conn) throws SQLException {

		// select m1.orderDate as 'M1 orderDate',m2.orderDate as 'M2 orderDate',
		// m1.ebayNO,m2.ebayNO,m1.guestAccount,m1.ebayNO from orders_master m1
		// inner join orders_master m2 on m1.guestAccount=m2.guestAccount where
		// m1.QR_id<m2.QR_id and DATEDIFF(Day,m1.orderDate,m2.orderDate)<14 and
		// DATEDIFF(Day,m1.orderDate,m2.orderDate) >-14 and
		// DATEDIFF(MONTH,m1.orderDate,GETDATE()) <1 order by m1.orderDate Desc

		// 選擇的天數 7 14 30
		int selectDate = 7;
		// 一個月裡面 同一個人兩周內下了兩次以上的訂單 而且尚未合併
		String sqlstr = " select  m1.guestAccount,m1.QR_id, m1.ebayNO,m1.payDate,m2.QR_id,m2.ebayNO,m2.orderDate "
				+ "from orders_master m1 inner join orders_master m2 on m1.guestAccount=m2.guestAccount "
				+ "where m1.QR_id<m2.QR_id  " + "and isnull(m1.isCombine,0) !=1 " + "and isnull(m2.isCombine,0) !=1 "
				+ "and DATEDIFF(Day,m1.orderDate,m2.orderDate)<14 " + "and DATEDIFF(Day,m1.orderDate,m2.orderDate)>-14 "
				+ "and DATEDIFF(MONTH,m1.orderDate,GETDATE()) <5 " + "order  by m1.orderDate Desc";
		PreparedStatement ps = conn.prepareStatement(sqlstr);
		ResultSet rs = ps.executeQuery();

		COrderCombine coc = new COrderCombine();
		LinkedList<COrderCombine> list = new LinkedList<COrderCombine>();
		while (rs.next()) {
			coc = new COrderCombine();
			coc.setGuestAccount(rs.getString(1));
			coc.setQR_Id1(rs.getString(2));
			coc.setEbayNO1(rs.getInt(3));
			coc.setPayTime1(rs.getDate(4));
			coc.setQR_Id2(rs.getString(5));
			coc.setEbayNO2(rs.getInt(6));
			coc.setPayTime2(rs.getDate(7));
			list.add(coc);
		}
		return list;

	}

	public void OrderInGuestAccount() {

		// 1個月內該帳號的訂單
		// Select * from orders_master where guestAccount ='silv3456' and
		// Month(GETDATE())-Month(orderDate) <1
		String sqlstr = "Select * from orders_master " + "where guestAccount =? "
				+ "and Month(GETDATE())-Month(orderDate) <1";

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
			System.out.println(colle.length + " " + colle[0] + " " + colle[1] + " " + colle[2]+ " " + colle[3]+ " " + colle[4]);
			EbayNO.add(colle[0]);
			EbayNO.add(colle[1]);
			guestAccount.add(colle[2]);
			qrId.add(colle[3]);
			qrId.add(colle[4]);
		}
		String specguestAccount ="";
		// 如果GA不一樣就回家
		if (guestAccount.size() > 1) {
			return guestAccount.size() + " Failed 請選擇相同的帳號";
		}else{
			Iterator iterator = guestAccount.iterator(); 
			while(iterator.hasNext())
				specguestAccount=(String) iterator.next();
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
				String sqlstr = "insert into comebineorder values(?,?,?,?)";
				System.out.println(sqlstr);
				ps = conn.prepareStatement(sqlstr);

				ps.setString(1, CQRID);
				ps.setString(2, specEbayNO.get(i));
				ps.setString(3, specguestAccount);
				ps.setDate(4,new java.sql.Date(date));
				ps.execute();

			}

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
	public void ReadCombineOrder(HttpServletRequest request, Connection conn){
		
		
		
	}

}
