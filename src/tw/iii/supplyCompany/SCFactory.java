package tw.iii.supplyCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.stock.CEbay;

public class SCFactory extends CSupplyCompany{

	private Statement state;
	
	public SCFactory() {

	}
	
	public void insertSCompanyId (CSupplyCompany cs) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "insert into company values(?,?,?,?,?,?)"; //6個
		PreparedStatement preparedState = conn.prepareStatement(sqlstr);
		
		preparedState.setInt(1, cs.getCompanyId());  //1st
		preparedState.setString(2, cs.getCompanyName());
		preparedState.setString(3, cs.getTel());
		preparedState.setString(4, cs.getFax());
		preparedState.setString(5, cs.getAddress());
		preparedState.setString(6, cs.getComment());  //6th
			
		preparedState.execute();
		preparedState.close();
		
		conn.close();
	}
	
	public CSupplyCompany searchDetail(String companyId) throws IllegalAccessException, ClassNotFoundException, Exception {

		DataBaseConn dbc = new DataBaseConn();	
		Connection conn = dbc.getConn();		
		String strsql = "SELECT  *  FROM   company where companyId = '" + companyId +"'";

				
		state = conn.createStatement();
		ResultSet rs =state.executeQuery(strsql);		
		
		CSupplyCompany scname = new CSupplyCompany();
	
		while (rs.next()) {
			scname.setCompanyId(rs.getInt(1)); // 1st
			scname.setCompanyName(rs.getString(2)); // 
			scname.setTel(rs.getString(3)); // 
			scname.setFax(rs.getString(4)); // 
			scname.setAddress(rs.getString(5)); // 
			scname.setComment(rs.getString(6)); // 6th
		
									
		}

		return scname;

	}
	
	public void editQRAccount (CSupplyCompany cs) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		Connection conn = new DataBaseConn().getConn();

		String sqlstr = "UPDATE company set " 
			    + " companyName = ?," 
				+ " tel = ?," + " fax = ?," + " address = ?," 
				+  " comment =?" 
				+ " where companyId = ?"; //6個

		PreparedStatement preparedState = conn.prepareStatement(sqlstr);
			 
		preparedState.setString(1, cs.getCompanyName());
		preparedState.setString(2, cs.getTel());
		preparedState.setString(3, cs.getFax());
		preparedState.setString(4, cs.getAddress());
		preparedState.setString(5, cs.getComment());  //6th		
		preparedState.setInt(6, cs.getCompanyId());
	
		
		preparedState.execute();
		preparedState.close();
		
		conn.close();
	}
	
	public LinkedList<CSupplyCompany> searchQRemployee(HttpServletRequest request,Connection conn) throws SQLException {
		LinkedList<CSupplyCompany> scnameall = new LinkedList<CSupplyCompany>();
		CSupplyCompany csupplycompany ;
		
		String strsql = "select * from  company ";
		
		PreparedStatement ps = conn.prepareStatement(strsql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			csupplycompany = new CSupplyCompany();
			
			csupplycompany.setCompanyId(rs.getInt(1));// 
			csupplycompany.setCompanyName(rs.getString(2));// 
			csupplycompany.setTel(rs.getString(3));// 
			csupplycompany.setFax(rs.getString(4));// 
			csupplycompany.setAddress(rs.getString(5)); //
			csupplycompany.setComment(rs.getString(6));// 
					
			scnameall.add(csupplycompany);			
		}
	
		return scnameall;
	}
	
}

