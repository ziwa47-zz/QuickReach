package tw.iii.qr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import tw.iii.qr.stock.CEbay;

public class QRAccountFactory {

	private Statement state;
	
	public QRAccountFactory() {

	}
	
	public void insertQRAccount (QRAccount qra) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "insert into quickreach.accountinfo values(?,?,?,?,?,?,?,?,?)"; //9個
		PreparedStatement preparedState = conn.prepareStatement(sqlstr);
		
		preparedState.setString(1, qra.account);
		preparedState.setString(2, qra.password);
		preparedState.setString(3, qra.lastName);
		preparedState.setString(4, qra.firstName);
		preparedState.setString(5, qra.email);
		preparedState.setString(6, qra.enName);
		preparedState.setString(7, qra.signatureImage); //簽名檔先不做
		preparedState.setString(8, qra.competenceLV);
		preparedState.setInt(9, qra.status);
		
		preparedState.execute();
		preparedState.close();
		
		conn.close();
	}
	
	public QRAccount searchDetail(String account) throws IllegalAccessException, ClassNotFoundException, Exception {

		DataBaseConn dbc = new DataBaseConn();	
		Connection conn = dbc.getConn();		
		String strsql = "SELECT  *  FROM  QuickReach.accountinfo where account = '" + account +"'";

				
		state = conn.createStatement();
		ResultSet rs =state.executeQuery(strsql);		
		
		//CEbay ebayaccount = new CEbay();
		QRAccount employee = new QRAccount();
	
		while (rs.next()) {
			employee.setAccount(rs.getString(1)); // account
			employee.setPassword(rs.getString(2)); // password
			employee.setLastName(rs.getString(3)); // lastName
			employee.setFirstName(rs.getString(4)); // firstName
			employee.setEmail(rs.getString(5)); // E-mail
			employee.setEnName(rs.getString(6)); // enName
			employee.setSignatureImage(rs.getString(7)); // signatureImage
			employee.setCompetenceLV(rs.getString(8)); // competenceLV
			employee.setStatus(rs.getInt(9)); // status
			
			
			
		}

		return employee;

	}
	
	public void editQRAccount (QRAccount qra) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "UPDATE quickreach.accountinfo set"
				+ "account = ?," + "password = ?," + "lastname = ?,"
				+ "firstname = ?," + "email = ?," + "ename = ?,"
				+ "signatureImage = ?," + "competenceLV =?," + "status = ?"; //9個
		PreparedStatement preparedState = conn.prepareStatement(sqlstr);
		
		preparedState.setString(1, qra.account);
		preparedState.setString(2, qra.password);
		preparedState.setString(3, qra.lastName);
		preparedState.setString(4, qra.firstName);
		preparedState.setString(5, qra.email);
		preparedState.setString(6, qra.enName);
		preparedState.setString(7, qra.signatureImage); //簽名檔先不做
		preparedState.setString(8, qra.competenceLV);
		preparedState.setInt(9, qra.status);
		
		preparedState.execute();
		preparedState.close();
		
		conn.close();
	}
	
	
	public LinkedList<QRAccount> searchQRemployee(HttpServletRequest request,Connection conn) throws SQLException {
		LinkedList<QRAccount> employeeall = new LinkedList<QRAccount>();
		QRAccount qraccount ;
		
		String strsql = "select * from QuickReach.accountinfo ";
		
		PreparedStatement ps = conn.prepareStatement(strsql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			qraccount = new QRAccount();
			
			qraccount.setAccount(rs.getString(1));// Account
			qraccount.setPassword(rs.getString(2));// ebayToken
			qraccount.setLastName(rs.getString(3));// endToken
			qraccount.setFirstName(rs.getString(4));// paypalAccount
			qraccount.setEmail(rs.getString(5)); //correspondCompany
			qraccount.setEnName(rs.getString(6));// startTime
			qraccount.setSignatureImage(rs.getString(7));// lastFixTime
			qraccount.setCompetenceLV(rs.getString(8));// status
			qraccount.setStatus(rs.getInt(9));// comment
			
			
			employeeall.add(qraccount);			
		}
	
		return employeeall;
	}
	
	public boolean isNullorEmpty(String s){
		if (s.length() == 0 || s == null)
		return true;
			
		return false;
	}
	
}
