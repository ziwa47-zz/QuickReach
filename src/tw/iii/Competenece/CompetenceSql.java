package tw.iii.Competenece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import tw.iii.qr.DataBaseConn;

public class CompetenceSql {

	
	private Statement state;
	public LinkedList<Competence> lct ;
	
	
	public void insetCompetence(Competence ct) throws IllegalAccessException, ClassNotFoundException, Exception{
		
		DataBaseConn dbc = new DataBaseConn();
		Connection conn = dbc.getConn() ;
		String sqlstr = "insert into competencelv values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedState = conn.prepareStatement(sqlstr);
		
		
		preparedState.setString(1, ct.CompetenceLv);
		preparedState.setInt(2, ct.productManage);
		preparedState.setInt(3, ct.purchaseManage);
		preparedState.setInt(4, ct.inventoryManage);
		preparedState.setInt(5, ct.inventoryInfoEdit);
		preparedState.setInt(6, ct.clientManage);
		preparedState.setInt(7, ct.entireOrders);
		preparedState.setInt(8, ct.ordersInvoiceDownload);
		preparedState.setInt(9, ct.priceChange);
		preparedState.setInt(10, ct.pendingOrdersEdit);
		preparedState.setInt(11, ct.totalAmountEdit);
		preparedState.setInt(12, ct.ordersManage);
		preparedState.setInt(13, ct.chartView); 
		preparedState.setInt(14, ct.productProfitView);
		preparedState.setInt(15, ct.reportView); 
		preparedState.setInt(16, ct.productCostView); 
		preparedState.setInt(17, ct.accountInfoEdit); 		
		preparedState.setInt(18, ct.ebayPaypalAccountEdit); 
		preparedState.setInt(19, ct.paramSettingEdit); 
		preparedState.setInt(20, ct.inventoryCostView); 
		
		
		preparedState.execute();
		preparedState.close();
		conn.close();
	}
	
	public void print1 () throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		DataBaseConn dbc = new DataBaseConn();		
		Connection conn = dbc.getConn() ;
		state = conn.createStatement();
		String sqlstr = "Select competenceLv From competencelv";
		ResultSet rs = state.executeQuery(sqlstr);
		//lct =new LinkedList<Competence>();
		Competence ct = new Competence();
		
		while(rs.next()){
			System.out.println(rs.getString(1));/*; ct.setCompetenceLv(rs.getString(1));
			ct.setProductManage(rs.getInt(2));
			ct.setPurchaseManage(rs.getInt(3));
			ct.setInventoryManage(rs.getInt(4));
			ct.setInventoryInfoEdit(rs.getInt(5));
			ct.setClientManage(rs.getInt(6));
			ct.setEntireOrders(rs.getInt(7));
			ct.setOrdersInvoiceDownload(rs.getInt(8));
			ct.setPriceChange(rs.getInt(9));
			ct.setPendingOrdersEdit(rs.getInt(10));
			ct.setTotalAmountEdit(rs.getInt(11));
			ct.setOrdersManage(rs.getInt(12));
			ct.setChartView(rs.getInt(13));
			ct.setProductProfitView(rs.getInt(14)); 
			ct.setReportView(rs.getInt(15)); 	
			ct.setProductCostView(rs.getInt(16)); 
			ct.setAccountInfoEdit(rs.getInt(17)); 
			ct.setEbayPaypalAccountEdit(rs.getInt(18)); 	
			ct.setParamSettingEdit(rs.getInt(19)); 
			ct.setInventoryCostView(rs.getInt(20)); */			
			//lct.add(ct);
		}
		
		rs.close();
		state.close();
	}
	
	public LinkedList<Competence> getCompetenceLevel() throws IllegalAccessException, ClassNotFoundException, Exception{
		
		DataBaseConn dbc = new DataBaseConn();		
		Connection conn = dbc.getConn() ;
		state = conn.createStatement();
		String sqlstr = "Select * From competencelv";
		ResultSet rs = state.executeQuery(sqlstr);
		lct =new LinkedList<Competence>();
		Competence ct ;
		
		while(rs.next()){
			ct = new Competence();
			ct.setCompetenceLv(rs.getString(1));
			ct.setProductManage(rs.getInt(2));
			ct.setPurchaseManage(rs.getInt(3));
			ct.setInventoryManage(rs.getInt(4));
			ct.setInventoryInfoEdit(rs.getInt(5));
			ct.setClientManage(rs.getInt(6));
			ct.setEntireOrders(rs.getInt(7));
			ct.setOrdersInvoiceDownload(rs.getInt(8));
			ct.setPriceChange(rs.getInt(9));
			ct.setPendingOrdersEdit(rs.getInt(10));
			ct.setTotalAmountEdit(rs.getInt(11));
			ct.setOrdersManage(rs.getInt(12));
			ct.setChartView(rs.getInt(13));
			ct.setProductProfitView(rs.getInt(14)); 
			ct.setReportView(rs.getInt(15)); 	
			ct.setProductCostView(rs.getInt(16)); 
			ct.setAccountInfoEdit(rs.getInt(17)); 
			ct.setEbayPaypalAccountEdit(rs.getInt(18)); 	
			ct.setParamSettingEdit(rs.getInt(19)); 
			ct.setInventoryCostView(rs.getInt(20)); 			
			lct.add(ct);
		}
		
		rs.close();
		state.close();
		return lct;
		
	}
	
	public void competenceDelete(String cv) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		DataBaseConn dbc = new DataBaseConn();		
		Connection conn = dbc.getConn() ;
		state = conn.createStatement();
		
		String sqlstr = "DELETE FROM competencelv WHERE competenceLV=N'"+cv+"';";
		
		state.executeUpdate(sqlstr);
		state.close();
		dbc.connclose(conn);
	}
	
}
