package tw.iii.qr.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import tw.iii.qr.*;

public class BundlesFactory {
	
	
	LinkedList<CProduct> lcp;
	private Statement state;
	public String[] bundles ;
	LinkedList<String[]> bundlesList = new LinkedList<String[]>();
	
	public void setBundles(String brand,String subBrand,String SKU,String P_name,String qty) {
		bundles=new String[]{brand,subBrand,SKU,P_name,qty};	
	}
	
	public LinkedList<CProduct> getProductInfo(String brand,String subBrand,String SKU,String P_name) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		
		DataBaseConn dbc = new DataBaseConn();		
		Connection conn = dbc.getConn() ;
		state = conn.createStatement();
		String sqlstr = "SELECT sku,P_name,brand,subBrand FROM quickreach.product where 1=1";
		
		if (brand != null && !brand.equals("select")){
			sqlstr += " and brand='" + brand + "'";

		}
		if (subBrand != null && !subBrand.equals("select")){
			sqlstr += " and subBrand='" + subBrand + "'";
		}
		if (SKU != null && !SKU.equals("select")){
			sqlstr += " and SKU='" + SKU + "'";
		}
		if (P_name != null && !P_name.equals("select")){
			sqlstr += " and P_name='" + P_name + "'";
		}
		
		
		ResultSet rs = state.executeQuery(sqlstr);
		lcp = new LinkedList<CProduct>();
		CProduct cp ;
		
		while (rs.next()) {
			cp=new CProduct();
			cp.setSKU(rs.getString(1));
			cp.setP_name(rs.getString(2));
			cp.setBrand(rs.getString(3));
			cp.setSubBrand(rs.getString(4));
			lcp.add(cp);			
		}
		
		rs.close();
		state.close();
		dbc.connclose(conn);
		return lcp;
	}
	
	public LinkedList<CProduct> getBrand() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		DataBaseConn dbc = new DataBaseConn();		
		Connection conn = dbc.getConn() ;
		state = conn.createStatement();
		String sqlstr = "SELECT distinct brand FROM quickreach.product where brand is not null";
		ResultSet rs = state.executeQuery(sqlstr);
		lcp = new LinkedList<CProduct>();
		CProduct cp ;
		
		while (rs.next()) {
			cp=new CProduct();
			
			cp.setBrand(rs.getString(1));
			
			lcp.add(cp);			
		}
		
		rs.close();
		state.close();	
		dbc.connclose(conn);
		return lcp;
	}
	
	public LinkedList<CProduct> getSubBrand(String b) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		
		DataBaseConn dbc = new DataBaseConn();		
		Connection conn = dbc.getConn() ;
		state = conn.createStatement();
		String sqlstr = "";
		if (b==null || b.equals("select")){
			sqlstr = "SELECT distinct subbrand FROM quickreach.product where subbrand is not null";
		}else if (!b.equals("select")){
			sqlstr = "SELECT distinct subbrand FROM quickreach.product where subbrand is not null and brand = '"+b+"'";
		}
		ResultSet rs = state.executeQuery(sqlstr);
		lcp = new LinkedList<CProduct>();
		CProduct cp ;
		
		while (rs.next()) {
			cp=new CProduct();
			
			cp.setSubBrand(rs.getString(1));
			
			lcp.add(cp);			
		}
		
		rs.close();
		state.close();
		dbc.connclose(conn);
		return lcp;
	}
	
	public LinkedList<CProduct> getSKU() throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		DataBaseConn dbc = new DataBaseConn();		
		Connection conn = dbc.getConn() ;
		state = conn.createStatement();
		String sqlstr = "SELECT SKU FROM quickreach.product";
		ResultSet rs = state.executeQuery(sqlstr);
		lcp = new LinkedList<CProduct>();
		CProduct cp ;
		
		while (rs.next()) {
			cp=new CProduct();
			
			cp.setSKU(rs.getString(1));
			
			lcp.add(cp);			
		}
		
		rs.close();
		state.close();	
		dbc.connclose(conn);
		return lcp;
	}
	
	public LinkedList<String[]> getBundlesInfo(){
		return bundlesList;
	}
	
	public void addItem(String[] a){
		bundlesList.add(a); 
	}
	
	public void removeItem(String a){
		
		for(String[] x:bundlesList){
			if(x[2].equals(a)){
				bundlesList.remove(x);
			}
		}
	}
	
	public void processBundles(String s){
		if (s.equals("add")) {
			addItem(bundles);
		}else {
			removeItem(s);
		}
	}
	
	public void bundlesToProduct(String sku, String pname ,String ps) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		DataBaseConn dbc = new DataBaseConn();
		Connection conn = dbc.getConn() ;
		String sqlstr = "insert into product(SKu,productType,P_name,comment) values(?,'組合包',?,?)";
		PreparedStatement preparedState = conn.prepareStatement(sqlstr);
		
		preparedState.setString(1, sku);		
		preparedState.setString(2, pname);
		preparedState.setString(3, ps);
		
		preparedState.execute();
		preparedState.close();
		dbc.connclose(conn);
	
	}
	
	public void bundlesToDetail(String sku) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{		
		
		for(String[] x: bundlesList)
		{
			DataBaseConn dbc = new DataBaseConn();
			Connection conn = dbc.getConn() ;
			
			String sqlstr = "INSERT INTO `quickreach`.`bundles` (`m_SKU`, `p_SKU`, `qty`) VALUES (?, ?, ?);";
			PreparedStatement preparedState = conn.prepareStatement(sqlstr);		
	
			preparedState.setString(1, sku);
			preparedState.setString(2, x[2]);
			preparedState.setInt(3, Integer.parseInt(x[4]));
			
			
			preparedState.execute();
			preparedState.close();
			
			dbc.connclose(conn);
		}
		
								
	}
	
}
