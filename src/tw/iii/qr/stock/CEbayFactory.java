package tw.iii.qr.stock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import tw.iii.qr.DataBaseConn;



public class CEbayFactory extends CEbay {
	private Statement state;

	public CEbayFactory() {

	}

	
	public void InsertNewEbayAccount (HttpServletRequest request, Connection conn) throws SQLException{
		String strsql = "INSERT INTO quickreach.ebayaccount(ebayId,ebayToken,endToken,paypalAccount,correspondCompany,startTime,lastFixTime,status ,comment,systemFeedback)"
					  + " VALUES (?,?,?,?,?,?,?,?,?,?)"; //(10個)
	
		PreparedStatement ps = null;
		System.out.print(strsql); 
		ps = conn.prepareStatement(strsql);
		
		ps.setString(1, request.getParameter("ebayId"));	//1st
		ps.setString(2, request.getParameter("ebayToken"));
		ps.setString(3, request.getParameter("endToken"));
		ps.setString(4, request.getParameter("paypalAcconut"));
		ps.setString(5, request.getParameter("correspondCompany"));
		ps.setString(6, request.getParameter("startTime")); //6th
		ps.setString(7, request.getParameter("lastFixTime")); 
		ps.setString(8, request.getParameter("status"));
		ps.setString(9, request.getParameter("comment"));
		ps.setString(10, request.getParameter("systemFeedback"));
			
		int i =ps.executeUpdate();
	}

	public CEbay searchDetail(String ebayId) throws IllegalAccessException, ClassNotFoundException, Exception {

		DataBaseConn dbc = new DataBaseConn();
		
		Connection conn = dbc.getConn();
		
		String strsql = "SELECT  *  FROM  QuickReach.ebayaccount where ebayId = '" + ebayId +"'";

		/*PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ps.setString(1, ebayId);*/
		
		state = conn.createStatement();
		ResultSet rs =state.executeQuery(strsql);		
		
		CEbay ebayaccount = new CEbay();
	
		while (rs.next()) {
			ebayaccount.setebayId(rs.getString(1)); // ebayId
			ebayaccount.setebayToken(rs.getString(2)); // ebayToken
			ebayaccount.setendToken(rs.getString(3)); // endToken
			ebayaccount.setpaypalAccount(rs.getString(4)); // paypalAccount
			ebayaccount.setcorrespondCompany(rs.getString(5)); // correspondCompany
			ebayaccount.setstartTime(rs.getString(6)); // startTime
			ebayaccount.setlastFixTime(rs.getString(7)); // lastFixTime
			ebayaccount.setstatus(rs.getString(8)); // status
			ebayaccount.setcomment(rs.getString(9)); // comment
			ebayaccount.setsystemFeedback(rs.getString(10)); // systemFeedback
			
			
		}

		return ebayaccount;

	}
	
	public void updateEbayAccount (HttpServletRequest request, Connection conn) throws SQLException{
		String strsql = "UPDATE quickreach.ebayaccount SET"	
			 + "ebayToken = ?," + "endToken = ?," + "paypalAccount = ?,"
			 + "correspondCompany = ?," + "startTime = ?," + "lastFixTime = ?,"	
			 + "status = ?," + "comment = ?," + "systemFeedback = ?,"
			 + "where ebayId = ?"; //(10個)
	
		PreparedStatement ps = null;
		System.out.print(strsql); 
		ps = conn.prepareStatement(strsql);
		
		ps.setString(1, "ebayToken");	//1st
		ps.setString(2, "endToken");	
		ps.setString(3, "paypalAccount");	
		ps.setString(4, "correspondCompany");	
		ps.setString(5, "startTime");	
		ps.setString(6, "lastFixTime");//6th
		ps.setString(7, "status");
		ps.setString(8, "comment");
		ps.setString(9, "systemFeedback");	
		ps.setString(10, "ebayId");	//10th
		
//		ps.setString(1, request.getParameter("ebayToken")); //1st
//		ps.setString(2, request.getParameter("endToken"));
//		ps.setString(3, request.getParameter("paypalAcconut"));
//		ps.setString(4, request.getParameter("correspondCompany"));
//		ps.setString(5, request.getParameter("startTime"));
//		ps.setString(6, request.getParameter("lastFixTime"));  //6th
//		ps.setString(7, request.getParameter("status"));
//		ps.setString(8, request.getParameter("comment"));
//		ps.setString(9, request.getParameter("systemFeedback"));
//		ps.setString(10, request.getParameter("ebayId")); //10th
		
		int i =ps.executeUpdate();
		
	}
	
	public LinkedList<CEbay> searchEbayAc(HttpServletRequest request,Connection conn) throws SQLException {
		LinkedList<CEbay> ebayall = new LinkedList<CEbay>();
		CEbay ebay ;
		
		String strsql = "select * from ebayaccount ";
		
		PreparedStatement ps = conn.prepareStatement(strsql);
		//int index  = 1; 
		ResultSet rs = ps.executeQuery();
		// ebayId,ebayToken,endToken,paypalAccount,startTime,lastFixTime,status,comment
		while(rs.next()){
			ebay = new CEbay();
			//ebay.add(String.valueOf(index++));
			ebay.setebayId(rs.getString(1));// ebayId
			ebay.setebayToken(rs.getString(2));// ebayToken
			ebay.setendToken(rs.getString(3));// endToken
			ebay.setpaypalAccount(rs.getString(4));// paypalAccount
			ebay.setcorrespondCompany(rs.getString(5)); //correspondCompany
			ebay.setstartTime(rs.getString(6));// startTime
			ebay.setlastFixTime(rs.getString(7));// lastFixTime
			ebay.setstatus(rs.getString(8));// status
			ebay.setcomment(rs.getString(9));// comment
			ebay.setsystemFeedback(rs.getString(10)); //systemFeefback
			
			ebayall.add(ebay);			
		}
	
		return ebayall;
	}
	
	public boolean isNullorEmpty(String s){
		if (s.length() == 0 || s == null)
		return true;
			
		return false;
	}
}
