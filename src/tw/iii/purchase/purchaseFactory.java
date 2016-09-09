package tw.iii.purchase;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import tw.iii.qr.DataBaseConn;

public class purchaseFactory {
	public purchaseFactory() {

	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	//staffName<select>
	public LinkedList<LinkedList<String>> accountSelectOption() throws ClassNotFoundException, SQLException, Exception {

		LinkedList<LinkedList<String>> Alllist = new LinkedList<>();
		LinkedList<String> account = new LinkedList<>();

		Connection conn = new DataBaseConn().getConn();
		String strsql = "SELECT account FROM  accountinfo";
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			account = new LinkedList<>();
			account.add(rs.getString(1));

			Alllist.add(account);
		}
		rs.close();
		ps.close();
		conn.close();

		return Alllist;
	}
	// company<select>
	public LinkedList<LinkedList<String>> companySelectOption() throws ClassNotFoundException, SQLException, Exception {

		LinkedList<LinkedList<String>> Alllist = new LinkedList<>();
		LinkedList<String> companyName = new LinkedList<>();

		Connection conn = new DataBaseConn().getConn();
		String strsql = "SELECT C_id,C_name FROM  company";
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			companyName = new LinkedList<>();
			companyName.add(rs.getString(1));
			companyName.add(rs.getString(2));

			Alllist.add(companyName);
		}
		rs.close();
		ps.close();
		conn.close();

		return Alllist;
	}

	// wareHouse <select>
	public LinkedList<LinkedList<String>> warehouseSelectOption()
			throws ClassNotFoundException, SQLException, Exception {

		LinkedList<LinkedList<String>> Alllist = new LinkedList<>();
		LinkedList<String> wareHouseName = new LinkedList<>();

		Connection conn = new DataBaseConn().getConn();
		String strsql = "SELECT warehouse, warehouseName FROM  warehouse";
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			wareHouseName = new LinkedList<>();
			wareHouseName.add(rs.getString(1));
			wareHouseName.add(rs.getString(2));

			Alllist.add(wareHouseName);
		}
		rs.close();
		ps.close();
		conn.close();

		return Alllist;
	}
<<<<<<< HEAD
	
	
	//retire
	public String processStorageRecord(String status) throws IllegalAccessException, ClassNotFoundException, Exception {

		String time = getDay();

		DecimalFormat df = new DecimalFormat("000");
		int no = 1;
		String strsql = " select purchaseid,date from  purchaselog_master where (date < now()) and  date >= curdate() and warehouse='KHH' order by date desc limit 0,1 ";
		Connection conn = new DataBaseConn().getConn();
		PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		String srno = "";

		String check = null;
		while (rs.next()) {
			check = rs.getString(1);
		}
		if (check != null) {
			if (no <= Integer.valueOf(check.substring(13, check.length()))) {
				int i = Integer.valueOf(check.substring(13, check.length()));
				no = i + 1;
			}
			srno = time + status + "KHH" + df.format(no);

		} else {
			String srnoDate = getDay();
			srno = srnoDate + "01KHH001";
		}
		System.out.println(1 + srno);
		return srno;
	}
=======



	// end

	// �脣���� yyyyMMdd statusId US/KH 瘚偌���
	
>>>>>>> testziwa

	public String getDay() {
		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String time = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		return time;
	}
	
	

	public LinkedList<LinkedList<String>> searchPurchase(Connection conn, String purchaseRecord, String outRecord,
			String purchaseId, String date1, String date2, String pname, String sku, String companyName, String owner, String wareHouse,
			String warehousePositionOne, String warehousePositionTwo, String qty, String price) {
		LinkedList<LinkedList<String>> Alllist = new LinkedList<>();
		LinkedList<String> list = new LinkedList<>();
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("I need stockRecord status:" + purchaseRecord + "," + outRecord);
		
		//
		if(purchaseRecord==null && outRecord==null){
			return null;
		}
		String sqlstr1 = makeSqlString(purchaseRecord, outRecord, purchaseId, date1, date2, pname, sku, companyName, owner,
				wareHouse, warehousePositionOne, warehousePositionTwo, qty, price);

		System.out.println(sqlstr1);
		try {
			PreparedStatement ps = conn.prepareStatement(sqlstr1);

			rs = ps.executeQuery(sqlstr1);
			
			//int count = ps.executeUpdate(sqlstr1);
			
			
			

			while (rs.next()) {
				list = new LinkedList<>();

				

				if ("1".equals(rs.getString(14))) {
					list.add("進貨");

				} else if ("2".equals(rs.getString(14))){
					list.add("出貨");

				}//進貨or出貨
				
				list.add(rs.getString(1));//a.purchaseId
				list.add(rs.getString(2));//a.SKU
				list.add(rs.getString(3));// c.P_name
				list.add(rs.getString(4));//a.qty
				list.add(rs.getString(6));//a.warehouse
				
				list.add(rs.getString(7)+"-"+ rs.getString(8));//a.warehousePosition1+a.warehousePosition2
				list.add(rs.getString(9));//c.owner
				list.add(dateFormat.format(rs.getDate(10)));//date
				list.add(rs.getString(11));//b.companyName
				list.add(rs.getString(12));//b.staffName
				
				list.add(rs.getString(13));//a.comment
				
				list.add(rs.getString(5));//a.price
				
			
				
				Alllist.add(list);

			}

			rs.close();
			ps.close();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		if (Alllist.isEmpty()) {
			System.out.println("gogogo");
		} else {

			System.out.println(Alllist);
		}
		return Alllist;

	}
	
	public String makeSqlString(String purchaseRecord, String outRecord,String purchaseId, String date1, String date2, String pname,
			String sku, String companyName, String owner, String wareHouse, String warehousePositionOne,
			String warehousePositionTwo, String qty, String price) {
		String sqlstr1 = "select distinct a.purchaseId,a.SKU,c.P_name,"
				+ " a.qty,a.price,a.warehouse,a.warehousePosition1,a.warehousePosition2,c.owner,"
				+ " b.date,b.companyName,b.staffName,a.comment,a.stockStatus"
				+ " from  purchaselog_detail as a inner join  purchaselog_master as b inner join  product as c where a.purchaseId =b.purchaseId and a.SKU = c.SKU  ";
		System.out.println(sku);

		if (!isNullorEmpty(purchaseRecord) && !isNullorEmpty(outRecord)) {
			sqlstr1 += "and (a.stockStatus = 1 or a.stockStatus = 2)";
			System.out.println("both:" + purchaseRecord + outRecord);
		} else {

			if (!isNullorEmpty(purchaseRecord)) {
				sqlstr1 += "and a.stockStatus = 1";
				System.out.println("進貨:" + purchaseRecord);
			}

			if (!isNullorEmpty(outRecord)) {
				sqlstr1 += "and a.stockStatus = 2";
				System.out.println("出貨:" + outRecord);
			}

		}
		
		if (!isNullorEmpty(purchaseId)) {
			sqlstr1 += " and a.purchaseId  like'%" + purchaseId + "%'";
			System.out.println(purchaseId);
		}

		if (!isNullorEmpty(date1)) {
			sqlstr1 += " and b.date  >= '" + date1 + "'";
			System.out.println(date1);
		}
		if (!isNullorEmpty(date2)) {
			sqlstr1 += " and b.date  <= '" + date2 + "'";
			System.out.println(date2);
		}

		if (!isNullorEmpty(pname)) {
			sqlstr1 += " and a.P_name like '%" + pname + "%'";
			System.out.println(pname);
		}
		if (!isNullorEmpty(sku)) {
			sqlstr1 += " and a.SKU like '%" + sku + "%'";
			System.out.println(sku);
		}

		if (!isNullorEmpty(companyName)) {
			sqlstr1 += " and b.companyName like '%" + companyName + "%'";
			System.out.println(companyName);
		}
		if (!isNullorEmpty(wareHouse)) {
			sqlstr1 += " and a.warehouse like '%" + wareHouse + "%'";
			System.out.println(wareHouse);
		}
		if (!isNullorEmpty(owner)) {
			sqlstr1 += " and b.staffName like '%" + owner + "%'";
			System.out.println(owner);
		}
		if (!isNullorEmpty(warehousePositionOne)) {
			sqlstr1 += " and a.warehousePosition1 like '%" + warehousePositionOne + "%'";
			System.out.println(warehousePositionOne);
		}

		if (!isNullorEmpty(warehousePositionTwo)) {
			sqlstr1 += " and a.warehousePosition2 like '%" + warehousePositionTwo + "%'";
			System.out.println(warehousePositionTwo);
		}

		if (!isNullorEmpty(qty)) {
			sqlstr1 += " and a.qty like '%" + qty + "%'";
			System.out.println(qty);
		}

		if (!isNullorEmpty(price)) {
			sqlstr1 += " and a.price like '%" + price + "%'";
			System.out.println(price);
		}
		
		sqlstr1 += " order by 1";
		return sqlstr1;
	}

<<<<<<< HEAD
	
	
=======
	

	//retire
	

	

>>>>>>> testziwa
	public LinkedList<LinkedList<String>> checkvalue(HttpServletRequest request) {
		LinkedList<String> values = new LinkedList<String>();

		int count = Integer.parseInt(request.getParameter("count"));
		LinkedList<Integer> times = new LinkedList<>();
		for (String s : request.getParameterValues("times")) {
			times.add(Integer.parseInt(s));
		}
		LinkedList<LinkedList<String>> Allist = new LinkedList<>();

		for (int i = 1; i <= count; i++) {
			if (times.indexOf(i) == -1)
				continue;
			values = new LinkedList<>();

			values.add(request.getParameter(("sku" + i)));
			values.add(request.getParameter(("qty" + i)));
			values.add(request.getParameter(("price" + i)));
			values.add(request.getParameter(("warehousePositionOne" + i)));
			values.add(request.getParameter(("warehousePositionTwo" + i)));
			values.add(request.getParameter(("comment" + i)));
			values.add(request.getParameter("warehouse"));

			Allist.add(values);
		}
		return Allist;

		// insert data
	}

	public LinkedList<String> purchaseMaster(HttpServletRequest request) {
		LinkedList<String> pInfo = new LinkedList<String>();

		pInfo.add(request.getParameter("companyId"));
		pInfo.add(request.getParameter("staffId"));
		pInfo.add(request.getParameter("warehouse"));
		pInfo.add(request.getParameter("purchaseMasterComment"));

		return pInfo;

	}

	//輝哥
	public LinkedList<Cpurchase_detail> details(String sku, Connection conn) {
		LinkedList<Cpurchase_detail> list = new LinkedList<>();
		Cpurchase_detail d = new Cpurchase_detail();
		String strsql = "select m.purchaseId,m.stockStatus,qty,date,m.warehouse from purchaselog_master as m inner join purchaselog_detail  where 1 = 1 "
				+ " and sku = ?";
		// if(check1=="on" ){
		// strsql += " and stockStatus = 1";
		// }
		// if(check2=="on"){
		// strsql += " and stockStatus = 2";
		// }
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(strsql);
			ps.setString(1, sku);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				d = new Cpurchase_detail();
				d.setPurchaseId(rs.getString(1));
				if ("1".equals(rs.getString(2))) {
					d.setStockStatus("進貨");
				} else if ("2".equals(rs.getString(2))) {
					d.setStockStatus("出貨");
				}
				d.setQty(rs.getInt(3));
				d.setDate(rs.getDate(4));
				d.setWarehouse(rs.getString(5));
				list.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

}
