package tw.iii.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

public class stockTransferFactory {

	public stockTransferFactory() {
	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public Cpurchase_master prepareTransferMaster(HttpServletRequest request) {
		Cpurchase_master purchaseMaster = new Cpurchase_master();
		purchaseMaster.setStaffId(request.getParameter("stockTransferId"));
		purchaseMaster.setStaffId(request.getParameter("staffId"));
		purchaseMaster.setWarehouse(request.getParameter("warehouse"));
		purchaseMaster.setWarehouse2(request.getParameter("newWarehouse"));
		purchaseMaster.setComment(request.getParameter("transferMasterComment"));

		return purchaseMaster;

	}

	public LinkedList<Cpurchase_detail> prepareTransferDetail(HttpServletRequest request) {
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

			detail.setQty(Integer.valueOf(request.getParameter(("qtyTwo" + i))));
			detail.setWarehousePosition(request.getParameter(("warehousePositionOne" + i)));
			detail.setWarehousePosition2(request.getParameter(("warehousePositionTwo" + i)));
			detail.setNewWarehousePosition(request.getParameter(("newWarehousePositionOne" + i)));
			detail.setNewWarehousePosition2(request.getParameter(("newWarehousePositionTwo" + i)));
			detail.setComment(request.getParameter(("comment" + i)));

			list.add(detail);
		}
		return list;

		// insert data

	}

	public void insertIntoStockTransfer(HttpServletRequest request, Connection conn) throws SQLException {

		String stockTransferId = request.getParameter("stockTransferId");
		Cpurchase_master prepareTransferMaster = prepareTransferMaster(request);

		System.out.println("stockTransferId:" + stockTransferId);

		String sqlstr1 = "Insert Into stockTransferlog_master(stockTransferId,date,staffName,oldWarehouse,newWarehouse,comment,stockStatus) Values(?,(select dateadd(hour,8,getdate())),?,?,?,?,3)"; //getdate(0)
		PreparedStatement ps = conn.prepareStatement(sqlstr1);
		ps.setString(1, stockTransferId);

		ps.setString(2, prepareTransferMaster.getStaffId()); // staffName
		ps.setString(3, prepareTransferMaster.getWarehouse()); // warehouse
		ps.setString(4, prepareTransferMaster.getWarehouse2()); // warehouse2
		ps.setString(5, prepareTransferMaster.getComment()); // purchaseMasterComment

		ps.executeUpdate();

		LinkedList<Cpurchase_detail> prepareTransferDetail = prepareTransferDetail(request);

		// stockTransferLog_Detail
		System.out.println("-------------stockTransferDeatail資料筆數:" + prepareTransferDetail.size());

		for (int i = 0; i < prepareTransferDetail.size(); i++) {
			String sqlstr2 = "Insert Into stockTransferlog_detail(" + "stockTransferId,SKU,qty,"// 3
					+ "oldWarehouse,oldWarehousePosition1,oldWarehousePosition2,"// 3
					+ "newWarehouse,newWarehousePosition1,newWarehousePosition2,"// 3
					+ "comment,stockStatus)"// 2
					+ "Values(?,?,?,?,?,?,?,?,?,?,3)";

			ps = conn.prepareStatement(sqlstr2);
			ps.setString(1, stockTransferId);
			ps.setString(2, prepareTransferDetail.get(i).getSKU());
			ps.setInt(3, prepareTransferDetail.get(i).getQty());

			ps.setString(4, prepareTransferMaster.getWarehouse());
			ps.setString(5, prepareTransferDetail.get(i).getWarehousePosition());
			ps.setString(6, prepareTransferDetail.get(i).getWarehousePosition2());

			ps.setString(7, prepareTransferMaster.getWarehouse2());
			ps.setString(8, prepareTransferDetail.get(i).getNewWarehousePosition());
			ps.setString(9, prepareTransferDetail.get(i).getNewWarehousePosition2());

			ps.setString(10, prepareTransferDetail.get(i).getComment());

			ps.executeUpdate();

			// 更新庫存 扣該品項原有庫存
			String sqlstr3 = "Update  storage set qty=qty-? where SKU= ? and warehouse = ?";
			ps = conn.prepareStatement(sqlstr3);
			ps.setInt(1, Integer.valueOf(prepareTransferDetail.get(i).getQty()));
			ps.setString(2, prepareTransferDetail.get(i).getSKU());
			ps.setString(3, prepareTransferMaster.getWarehouse());

			ps.executeUpdate();
			
			System.out.println("原倉"+prepareTransferMaster.getWarehouse());
			

			// 判別將轉入之庫別有無此商品庫存記錄
			System.out.println("PRE storage");
			String sqlsql = "select count(sku) from storage where sku = ? and warehouse = ?";
			ps = conn.prepareStatement(sqlsql);
			ps.setString(1, prepareTransferDetail.get(i).getSKU());
			ps.setString(2, prepareTransferMaster.getWarehouse2());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ps = null;
				int count = rs.getInt(1);
				if (count == 0) {
					String sqlstr5 = "Insert into storage values(?,?,?,?,?,?,(select dateadd(hour,8,getdate())))";
					ps = conn.prepareStatement(sqlstr5);
					ps.setString(1, prepareTransferDetail.get(i).getSKU());
					ps.setString(2, prepareTransferMaster.getWarehouse2());
					ps.setString(3, prepareTransferDetail.get(i).getNewWarehousePosition());
					ps.setString(4, prepareTransferDetail.get(i).getNewWarehousePosition2());
					ps.setInt(5, prepareTransferDetail.get(i).getQty());
					ps.setString(6, prepareTransferDetail.get(i).getComment());

					ps.executeUpdate();
				} else {

					// 增加該品項轉倉庫存

					String sqlstr5 = "Update  storage set qty=qty+? where SKU= ? and warehouse = ?";
					ps = conn.prepareStatement(sqlstr5);
					ps.setInt(1, Integer.valueOf(prepareTransferDetail.get(i).getQty()));
					ps.setString(2, prepareTransferDetail.get(i).getSKU());
					ps.setString(3, prepareTransferMaster.getWarehouse2());

					ps.executeUpdate();
				}
			}

			System.out.println("qty:" + Integer.valueOf(prepareTransferDetail.get(i).getQty()));
			System.out.println("sku:" + prepareTransferDetail.get(i).getSKU());
			System.out.println("新倉:" + prepareTransferMaster.getWarehouse2());

		}
		ps.close();
	}

	public LinkedList<Cpurchase> searchStockTransfer(Connection conn, HttpServletRequest request) {
		
		LinkedList<Cpurchase> list = new LinkedList<Cpurchase>();
		
		ResultSet rs = null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String stockTransferId = request.getParameter("stockTransferId");

		String date1 = request.getParameter("dateMin");
		String date2 = request.getParameter("dateMax");

		String sku = request.getParameter("sku");

		String oldwareHouse = request.getParameter("oldWareHouse");
		String newWareHouse = request.getParameter("newWareHouse");
		
		String staff = request.getParameter("staff");
		
		String sqlstr1 = makeSqlString(stockTransferId, date1, date2, sku, oldwareHouse, newWareHouse,staff);

		System.out.println("sssssss:"+date2);
		System.out.println(sqlstr1);
		try {
			PreparedStatement ps = conn.prepareStatement(sqlstr1);

			rs = ps.executeQuery();

			while (rs.next()) {
				Cpurchase cpr = new Cpurchase();

				cpr.CPurchase_master.setStockTransferId(rs.getString(1));
				cpr.CPurchase_detailsSingle.setSKU(rs.getString(2));
				cpr.CPurchase_master.setWarehouse(rs.getString(3));
				cpr.CPurchase_detailsSingle.setWarehousePosition(rs.getString(4));
				cpr.CPurchase_master.setWarehouse2(rs.getString(5));
				cpr.CPurchase_detailsSingle.setNewWarehousePosition(rs.getString(6));
				cpr.CPurchase_master.setStaffId(rs.getString(7));
				cpr.CPurchase_detailsSingle.setQty(rs.getInt(8));
				cpr.CPurchase_detailsSingle.setComment(rs.getString(9));;

				list.add(cpr);
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list.isEmpty()) {
			System.out.println("gogogo");
		} else {
			System.out.println(list.toString());
		}
		return list;

	}

	public String makeSqlString(String stockTransferId, String date1, String date2,
			 String sku,String newWareHouse, String oldWareHouse,String staff) {
		String sqlstr1 = "select a.stockTransferId,b.SKU,b.oldWarehouse,b.oldWarehousePosition1+'-'+b.oldWarehousePosition2,b.newWarehouse,b.newWarehousePosition1+'-'+b.newWarehousePosition2,a.staffName,b.qty,b.comment"
				+ " from stockTransferlog_master as a  inner join stockTransferlog_detail  as b on  a.stockTransferId = b.stockTransferId  ";

	

		if (!isNullorEmpty(stockTransferId)) {
			sqlstr1 += " and a.stockTransferId  ='" + stockTransferId + "'";
			System.out.println(stockTransferId);
		}

		if (!isNullorEmpty(date1)) {
			sqlstr1 += " and a.date  >= '" + date1 + "'";
			System.out.println(date1);
		}
		if (!isNullorEmpty(date2)) {
			sqlstr1 += " and a.date  <= '" + (Integer.valueOf(date2)+1)+ "'";
			System.out.println(date2);
		}

		if (!isNullorEmpty(sku)) {
			sqlstr1 += " and b.SKU like '%" + sku + "%'";
			System.out.println(sku);
		}
		if (!isNullorEmpty(newWareHouse)) {
			sqlstr1 += " and newWareHouse = " + newWareHouse ;
			System.out.println(newWareHouse);
		}
	
		if (!isNullorEmpty(oldWareHouse)) {
			sqlstr1 += " and oldWareHouse " + oldWareHouse ;
			System.out.println(oldWareHouse);
		}
		
		if (!isNullorEmpty(staff)) {
			sqlstr1 += " and staffNmae " + staff ;
			System.out.println(staff);
		}
	

		sqlstr1 += " order by 1 desc";
		return sqlstr1;
	}

}
