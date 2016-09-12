package tw.iii.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		purchaseMaster.setStaffId(request.getParameter("staffId"));
		purchaseMaster.setWarehouse(request.getParameter("oldWarehouse"));
		purchaseMaster.setWarehouse2(request.getParameter("NewWarehouse"));
		purchaseMaster.setComment(request.getParameter("purchaseMasterComment"));

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
			detail.setComment(request.getParameter(("comment" + i)));
			detail.setWarehouse(request.getParameter("warehouse"));

			list.add(detail);
		}
		return list;

		// insert data

	}
	
	public void insertIntoStockTransfer(HttpServletRequest request, Connection conn) throws SQLException {

		String stockTransferId = request.getParameter("stockTransferId");
		Cpurchase_master prepareTransferMaster = prepareTransferMaster(request);
		
		System.out.println("stockTransferId:"+stockTransferId);


		String sqlstr1 = "Insert Into stockTransferlog_master(stockTransferId,date,staffName,oldWarehouse,newWarehouse,comment,stockStatus) Values(?,getdate(),?,?,?,?,3)";
		PreparedStatement ps = conn.prepareStatement(sqlstr1);
		ps.setString(1, stockTransferId);

		ps.setString(2, prepareTransferMaster.getStaffId()); // staffName
		ps.setString(3, prepareTransferMaster.getWarehouse()); // warehouse
		ps.setString(4, prepareTransferMaster.getWarehouse2()); // warehouse2
		ps.setString(5, prepareTransferMaster.getComment()); // purchaseMasterComment

		ps.executeUpdate();
		
		

		LinkedList<Cpurchase_detail> prepareTransferDetail = prepareTransferDetail(request) ;

		// purchaseLog_Detail
		System.out.println("-------------stockTransferDeatail資料筆數:" + prepareTransferDetail.size());

		for (int i = 0; i < prepareTransferDetail.size(); i++) {
			String sqlstr2 = "Insert Into stockTransferlog_detail(purchaseId,SKU,qty,price,warehousePosition1,warehousePosition2,comment,stockStatus,warehouse)"
					+ "Values(?,?,?,?,?,?,?,1,?)";

			ps = conn.prepareStatement(sqlstr2);
			ps.setString(1, stockTransferId);
			ps.setString(2, prepareTransferDetail.get(i).getSKU());
			ps.setInt(3, prepareTransferDetail.get(i).getQty());
			ps.setDouble(4, prepareTransferDetail.get(i).getPrice());
			ps.setString(5, prepareTransferDetail.get(i).getWarehousePosition());
			ps.setString(6, prepareTransferDetail.get(i).getWarehousePosition2());
			ps.setString(7, prepareTransferDetail.get(i).getComment());
			ps.setString(8, prepareTransferDetail.get(i).getWarehouse());

			ps.executeUpdate();
			// 更新product資料表 該品項之成本

			String sqlstr5 = "Update product set cost=? where SKU=?";
			ps = conn.prepareStatement(sqlstr5);
			ps.setDouble(1, prepareTransferDetail.get(i).getPrice());
			ps.setString(2, prepareTransferDetail.get(i).getSKU());
			ps.executeUpdate();

			// 更新庫存
			
			String sqlsql = "select * from storage where sku = ? and warehouse = ?";
			ps = conn.prepareStatement(sqlsql);
			ps.setString(1, prepareTransferDetail.get(i).getSKU());
			ps.setString(2, prepareTransferDetail.get(i).getWarehouse());

			ResultSet rs = ps.executeQuery();
			if (rs.wasNull()){
				String sqlstr3 = "Insert into storage values(?,?,?,?,?,?,getdate())";
				ps = conn.prepareStatement(sqlstr3);
				ps.setString(1,prepareTransferDetail.get(i).getSKU());
				ps.setString(2, prepareTransferDetail.get(i).getWarehouse());
				ps.setString(3, prepareTransferDetail.get(i).getWarehousePosition());
				ps.setString(4, prepareTransferDetail.get(i).getWarehousePosition2());
				ps.setInt(5, prepareTransferDetail.get(i).getQty());
				ps.setString(6, prepareTransferDetail.get(i).getComment());
			
				ps.executeUpdate();
			}else{
			
			String sqlstr6 = "Update  storage set qty=qty+? where SKU=? and warehouse =?";
			ps = conn.prepareStatement(sqlstr6);
			ps.setInt(1, Integer.valueOf(prepareTransferDetail.get(i).getQty()));
			ps.setString(2, prepareTransferDetail.get(i).getSKU());
			ps.setString(3, prepareTransferDetail.get(i).getWarehouse());

			ps.executeUpdate();

			}
		}
		ps.close();
	}

}
