package tw.iii.warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;


public class WarehouseFactory {

	public WarehouseFactory() {
	}
	
	public void insertWarehouse(Connection conn, HttpServletRequest request)	{

		CWarehouse cwr = new CWarehouse();

		cwr.setWarehouse(request.getParameter("warehouse")); 
		cwr.setWarehouseName(request.getParameter("warehouseName"));
		System.out.println("processInsert:"+request.getParameter("warehouse"));
		
		String sqlstr = "insert into warehouse values(?,?)"; 
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sqlstr);
			ps.setString(1, cwr.getWarehouse()); 
			ps.setString(2, cwr.getWarehouseName());
			
			ps.executeUpdate();
			
			System.out.println("processInsert");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public  CWarehouse searchDetail(Connection conn,HttpServletRequest request)  {
		String id = request.getParameter("p");
		CWarehouse cwr = new CWarehouse();
		String strsql = "SELECT  *  FROM   warehouse where id = '"+ id +"'";

		System.out.println("get id:" + id);
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(strsql);
			
			ResultSet rs = ps.executeQuery(); 

			while (rs.next()) {
				cwr.setId(rs.getString(1)); // item
				cwr.setWarehouse(rs.getString(2)); // 1st
				cwr.setWarehouseName(rs.getString(3)); //
				
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));//

			}
			
			rs.close();
			ps.close();
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cwr;
	

	}

	public void editWarehouse(Connection conn, HttpServletRequest request){

		CWarehouse cwr = new CWarehouse();
		
		cwr.setWarehouse(request.getParameter("id"));
		cwr.setWarehouse(request.getParameter("warehouse"));
		cwr.setWarehouseName(request.getParameter("warehouseName")); 
		

		String sqlstr = "UPDATE warehouse set  warehouse = ?, warehouseName = ? where id = ?"; // 

		try {
			PreparedStatement ps = conn.prepareStatement(sqlstr);
			
			ps.setString(1, cwr.getWarehouse());
			ps.setString(2, cwr.getWarehouseName());
			ps.setString(3, cwr.getId());
		
			ps.executeUpdate();
			
			
			ps.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		System.out.println(sqlstr);
		System.out.println("id:"+request.getParameter("id"));
		System.out.println("Hi~ processEdit");
	}

	public LinkedList<CWarehouse> searchWarehouse(Connection conn,HttpServletRequest request) {
		LinkedList<CWarehouse> list = new LinkedList<CWarehouse>();
		;

		String strsql = "select * from  warehouse ";

		try {
			PreparedStatement ps = conn.prepareStatement(strsql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CWarehouse cwr = new CWarehouse();

				cwr.setId(rs.getString(1));
				cwr.setWarehouse(rs.getString(2));//
				cwr.setWarehouseName(rs.getString(3));//

				list.add(cwr);
			}
			
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	
		return list;
	}
	
	

}
