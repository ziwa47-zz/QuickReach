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

		// stockTransferLog_Detail
		System.out.println("-------------stockTransferDeatail資料筆數:" + prepareTransferDetail.size());

		for (int i = 0; i < prepareTransferDetail.size(); i++) {
			String sqlstr2 = "Insert Into stockTransferlog_detail("
					+ "stockTransferId,SKU,qty,"//3
					+ "oldWarehouse,oldWarehousePosition1,oldWarehousePosition2,"//3
					+ "newWarehouse,newWarehousePosition1,newWarehousePosition2,"//3
					+ "comment,stockStatus)"//2
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
			ps.setString(3, prepareTransferDetail.get(i).getWarehouse());

			
			ps.executeUpdate();
			
			
			//判別將轉入之庫別有無此商品庫存記錄
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
					String sqlstr5 = "Insert into storage values(?,?,?,?,?,?,getdate())";
					ps = conn.prepareStatement(sqlstr5);
					ps.setString(1, prepareTransferDetail.get(i).getSKU());
					ps.setString(2, prepareTransferMaster.getWarehouse2());
					ps.setString(3, prepareTransferDetail.get(i).getNewWarehousePosition());
					ps.setString(4, prepareTransferDetail.get(i).getNewWarehousePosition2());
					ps.setInt(5, prepareTransferDetail.get(i).getQty());
					ps.setString(6, prepareTransferDetail.get(i).getComment());

					ps.executeUpdate();
				} else {
				
					//增加該品項轉倉庫存
					
					String sqlstr5 = "Update  storage set qty=qty+? where SKU= ? and warehouse = ?";
					ps = conn.prepareStatement(sqlstr5);
					ps.setInt(1, Integer.valueOf(prepareTransferDetail.get(i).getQty()));
					ps.setString(2, prepareTransferDetail.get(i).getSKU());
					ps.setString(3, prepareTransferDetail.get(i).getWarehouse2());
					
					ps.executeUpdate();
				}
			}
			
		
			
		
			
			System.out.println("qty:"+Integer.valueOf(prepareTransferDetail.get(i).getQty()) );
			System.out.println("sku:"+prepareTransferDetail.get(i).getSKU() );
			System.out.println("newWarehouse:"+prepareTransferDetail.get(i).getWarehouse2());
			
			
		}
		ps.close();
	}

}
