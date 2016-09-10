package tw.iii.purchase;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;


public class purchaseFactory {

	public purchaseFactory() {

	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	// staffName<select>
	public LinkedList<LinkedList<String>> accountSelectOption(Connection conn)
			throws ClassNotFoundException, SQLException, Exception {

		LinkedList<LinkedList<String>> Alllist = new LinkedList<>();
		LinkedList<String> account = new LinkedList<>();

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

		return Alllist;
	}

	// company<select>
	public LinkedList<LinkedList<String>> companySelectOption(Connection conn)
			throws ClassNotFoundException, SQLException, Exception {

		LinkedList<LinkedList<String>> Alllist = new LinkedList<>();
		LinkedList<String> companyName = new LinkedList<>();

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

		return Alllist;
	}

	// wareHouse <select>
	public LinkedList<LinkedList<String>> warehouseSelectOption(Connection conn)
			throws ClassNotFoundException, SQLException, Exception {

		LinkedList<LinkedList<String>> Alllist = new LinkedList<>();
		LinkedList<String> wareHouseName = new LinkedList<>();

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

		return Alllist;
	}

	
	
	

	public String getDay() {
		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String time = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		return time;
	}



	public String purchaseGetDay() {
		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String time = dateFormat.format(date);
		System.out.println(dateFormat.format(date));
		return time;
	}

	
	public LinkedList<LinkedList<String>> searchPurchase(Connection conn,HttpServletRequest request) {
		
		LinkedList<LinkedList<String>> Alllist = new LinkedList<>();
		LinkedList<String> list = new LinkedList<>();
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String purchaseRecord = request.getParameter("purchaseRecord");
		String outRecord = request.getParameter("outRecord");
		  
		String purchaseId = request.getParameter("purchaseId");

		String date1 = request.getParameter("dateMin");
		String date2= request.getParameter("dateMax");

		String sku = request.getParameter("sku");
		String pname = request.getParameter("pName");

		String companyName = request.getParameter("companyName");
		String owner = request.getParameter("owner");
		String wareHouse = request.getParameter("wareHouse");

		String warehousePositionOne = request.getParameter("warehousePositionOne");
		String warehousePositionTwo = request.getParameter("warehousePositionTwo");
		String qty = request.getParameter("qty");
		String price = request.getParameter("price");

		
		System.out.println("I need stockRecord status:" + purchaseRecord + "," + outRecord);

		//
		if (purchaseRecord == null && outRecord == null) {
			return null;
		}
		String sqlstr1 = makeSqlString(purchaseRecord, outRecord, purchaseId, date1, date2, pname, sku, companyName,
				owner, wareHouse, warehousePositionOne, warehousePositionTwo, qty, price);

		System.out.println(sqlstr1);
		try {
			PreparedStatement ps = conn.prepareStatement(sqlstr1);

			rs = ps.executeQuery();

			while (rs.next()) {
				list = new LinkedList<>();

				if ("1".equals(rs.getString(14))) {
					list.add("進貨");

				} else if ("2".equals(rs.getString(14))) {
					list.add("出貨");

				} // 進貨or出貨

				list.add(rs.getString(1));// a.purchaseId
				list.add(rs.getString(2));// a.SKU
				list.add(rs.getString(3));// c.P_name
				list.add(rs.getString(4));// a.qty
				list.add(rs.getString(6));// a.warehouse

				list.add(rs.getString(7) + "-" + rs.getString(8));// a.warehousePosition1+a.warehousePosition2
				list.add(rs.getString(9));// c.owner
				list.add(dateFormat.format(rs.getDate(10)));// date
				list.add(rs.getString(11));// b.companyName
				list.add(rs.getString(12));// b.staffName

				list.add(rs.getString(13));// a.comment
				list.add(rs.getString(5));// a.price

				Alllist.add(list);
			}

			rs.close();
			ps.close();

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

	public String makeSqlString(String purchaseRecord, String outRecord, String purchaseId, String date1, String date2,
			String pname, String sku, String companyName, String owner, String wareHouse, String warehousePositionOne,
			String warehousePositionTwo, String qty, String price) {
		String sqlstr1 = "select distinct a.purchaseId,a.SKU,c.P_name,"
				+ " a.qty,a.price,a.warehouse,a.warehousePosition1,a.warehousePosition2,c.owner,"
				+ " b.date,b.companyName,b.staffName,a.comment,a.stockStatus"
				+ " from  purchaselog_detail as a inner join  purchaselog_master as b on a.purchaseId =b.purchaseId inner join  product as c on a.SKU = c.SKU  ";
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


	public void insertIntoPurchaseTest(HttpServletRequest request, Connection conn) throws SQLException {

		String purchaseId = request.getParameter("purchaseId");
		Cpurchase_master preparePurchaseMaster = preparePurchaseMaster(request);

		System.out.println("Tell me who you are?:" + purchaseId);

		String sqlstr1 = "Insert Into purchaselog_master(purchaseId,date,companyId,companyName,staffName,warehouse,comment,stockStatus) Values(?,getdate(),?,(select C_name from company where C_id=?),?,?,?,1)";
		PreparedStatement ps = conn.prepareStatement(sqlstr1);
		ps.setString(1, purchaseId);
		ps.setString(2, preparePurchaseMaster.getCompanyId()); // companyId
		ps.setString(3, preparePurchaseMaster.getCompanyId()); // select C_name from
												// quickreach.company where
												// C_id=?
		ps.setString(4, preparePurchaseMaster.getStaffId()); // staffId
		ps.setString(5, preparePurchaseMaster.getWarehouse()); // warehouse
		ps.setString(6, preparePurchaseMaster.getComment()); // purchaseMasterComment

		ps.executeUpdate();
		
		

		LinkedList<Cpurchase_detail> preparePurchaseDetail = preparePurchaseDetail(request) ;

		// purchaseLog_Detail
		System.out.println("-------------purchaseDeatail資料筆數:" + preparePurchaseDetail.size());

		for (int i = 0; i < preparePurchaseDetail.size(); i++) {
			String sqlstr2 = "Insert Into purchaselog_detail(purchaseId,SKU,qty,price,warehousePosition1,warehousePosition2,comment,stockStatus,warehouse)"
					+ "Values(?,?,?,?,?,?,?,1,?)";

			ps = conn.prepareStatement(sqlstr2);
			ps.setString(1, purchaseId);
			ps.setString(2, preparePurchaseDetail.get(i).getSKU());
			ps.setInt(3, preparePurchaseDetail.get(i).getQty());
			ps.setDouble(4, preparePurchaseDetail.get(i).getPrice());
			ps.setString(5, preparePurchaseDetail.get(i).getWarehousePosition());
			ps.setString(6, preparePurchaseDetail.get(i).getWarehousePosition2());
			ps.setString(7, preparePurchaseDetail.get(i).getComment());
			ps.setString(8, preparePurchaseDetail.get(i).getWarehouse());

			ps.executeUpdate();
			// 更新product資料表 該品項之成本

			String sqlstr5 = "Update product set cost=? where SKU=?";
			ps = conn.prepareStatement(sqlstr5);
			ps.setDouble(1, preparePurchaseDetail.get(i).getPrice());
			ps.setString(2, preparePurchaseDetail.get(i).getSKU());
			ps.executeUpdate();

			// 更新庫存
			
			String sqlsql = "select * from storage where sku = ? and warehouse = ?";
			ps = conn.prepareStatement(sqlsql);
			ps.setString(1, preparePurchaseDetail.get(i).getSKU());
			ps.setString(2, preparePurchaseDetail.get(i).getWarehouse());

			ResultSet rs = ps.executeQuery();
			if (rs.wasNull()){
				String sqlstr3 = "Insert into storage values(?,?,?,?,?,?,getdate())";
				ps = conn.prepareStatement(sqlstr3);
				ps.setString(1,preparePurchaseDetail.get(i).getSKU());
				ps.setString(2, preparePurchaseDetail.get(i).getWarehouse());
				ps.setString(3, preparePurchaseDetail.get(i).getWarehousePosition());
				ps.setString(4, preparePurchaseDetail.get(i).getWarehousePosition2());
				ps.setInt(5, preparePurchaseDetail.get(i).getQty());
				ps.setString(6, preparePurchaseDetail.get(i).getComment());
			
				ps.executeUpdate();
			}else{
			
			String sqlstr6 = "Update  storage set qty=qty+? where SKU=? and warehouse =?";
			ps = conn.prepareStatement(sqlstr6);
			ps.setInt(1, Integer.valueOf(preparePurchaseDetail.get(i).getQty()));
			ps.setString(2, preparePurchaseDetail.get(i).getSKU());
			ps.setString(3, preparePurchaseDetail.get(i).getWarehouse());

			ps.executeUpdate();

			}
		}
		ps.close();
	}

	public LinkedList<Cpurchase_detail> preparePurchaseDetail(HttpServletRequest request) {
		LinkedList<Cpurchase_detail> list = new LinkedList<Cpurchase_detail>();
		Cpurchase_detail detail = new Cpurchase_detail();

		int count = Integer.parseInt(request.getParameter("count"));
		LinkedList<Integer> times = new LinkedList<>();
		for (String s : request.getParameterValues("times")) {
			times.add(Integer.parseInt(s));
		}

		for (int i = 1; i <= count; i++) {
			if (times.indexOf(i) == -1)
				continue;

			detail = new Cpurchase_detail();

			detail.setSKU(request.getParameter(("sku" + i)));
			detail.setQty(Integer.valueOf(request.getParameter(("qty" + i))));
			detail.setPrice(Double.valueOf(request.getParameter(("price" + i))));
			detail.setWarehousePosition(request.getParameter(("warehousePositionOne" + i)));
			detail.setWarehousePosition2(request.getParameter(("warehousePositionTwo" + i)));
			detail.setComment(request.getParameter(("comment" + i)));
			detail.setWarehouse(request.getParameter("warehouse"));

			list.add(detail);
			System.out.println("newMethod:"+list);
		}
		return list;

		// insert data
	}

	public Cpurchase_master preparePurchaseMaster(HttpServletRequest request) {
		Cpurchase_master purchaseMaster = new Cpurchase_master();

		purchaseMaster.setCompanyId(request.getParameter("companyId"));
		purchaseMaster.setStaffId(request.getParameter("staffId"));
		purchaseMaster.setWarehouse(request.getParameter("warehouse"));
		purchaseMaster.setComment(request.getParameter("purchaseMasterComment"));

		System.out.println("newMethod:"+purchaseMaster);
		return purchaseMaster;

	}


	
	// 輝哥
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
