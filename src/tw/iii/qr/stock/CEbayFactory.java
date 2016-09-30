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
		String strsql = "INSERT INTO  ebayaccount(ebayId,ebayToken,endToken,paypalAccount,correspondCompany,startTime,lastFixTime,status ,comment,systemFeedback,companyAddress,companyPhone,companyPost)"
					  + " VALUES (?,?,?,?,?,SELECT DATEADD(HOUR,8,GetUTCDate()) ,SELECT DATEADD(HOUR,8,GetUTCDate()) ,?,?,?,?,?,?)"; //(13個)
	
		PreparedStatement ps = null;
		System.out.print(strsql); 
		ps = conn.prepareStatement(strsql);
		
		ps.setString(1, request.getParameter("ebayId"));	//1st
		ps.setString(2, request.getParameter("ebayToken"));
		ps.setString(3, request.getParameter("endToken"));
		ps.setString(4, request.getParameter("paypalAccount"));
		ps.setString(5, request.getParameter("correspondCompany"));
		//ps.setString(6, request.getParameter("startTime")); //6th
		//ps.setString(7, request.getParameter("lastFixTime")); 
		ps.setString(6, request.getParameter("status"));
		ps.setString(7, request.getParameter("comment"));
		ps.setString(8, request.getParameter("systemFeedback"));
		
		ps.setString(9, request.getParameter("companyAddress"));
		ps.setString(10, request.getParameter("companyPhone"));
		ps.setString(13, request.getParameter("companyPost")); 
			
		int i =ps.executeUpdate();
	}

	public CEbay searchDetail(String ebayId) throws IllegalAccessException, ClassNotFoundException, Exception {

		DataBaseConn dbc = new DataBaseConn();
		
		Connection conn = dbc.getConn();
		
		String strsql = "SELECT  *  FROM   ebayaccount where ebayId = ?";
		//String strsql = "SELECT  *  FROM   ebayaccount where ebayId = '" + ebayId +"'";
		
		/*PreparedStatement ps = null;
		ps = conn.prepareStatement(strsql);
		ps.setString(1, ebayId);*/
		
		PreparedStatement ps = conn.prepareStatement(strsql);
		ps.setString(1, ebayId);
		//ResultSet rs =ps.executeQuery(strsql);
		ResultSet rs =ps.executeQuery();	
		
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
			
			ebayaccount.setcompanyAddress(rs.getString(11));
			ebayaccount.setcompanyPhone(rs.getString(12));
			ebayaccount.setcompanyPost(rs.getString(13));
			
		}

		return ebayaccount;

	}
	
	public void updateEbayAccount (HttpServletRequest request, Connection conn) throws SQLException{

//		String strsql = "UPDATE  ebayaccount SET "	
//			 + "ebayToken = ?," + "endToken = ?," + "paypalAccount = ?,"
//			 + "correspondCompany = ?," + "startTime = ?," + "lastFixTime = ?,"	
//			 + "status = ?," + "comment = ?," + "systemFeedback = ?,"
//			 + "where ebayId = ?"; //(10個)
		
		String strsql = "UPDATE  ebayaccount SET "	
				 + "ebayToken = ?," + "paypalAccount = ?,"
				 + "correspondCompany = ?,lastFixTime=(SELECT DATEADD(HOUR,8,GetUTCDate())) , " 	
				 + "status = ?," + "comment = ?," + "systemFeedback = ? ,"
				 + "companyAddress = ?,"+ "companyPhone = ?,"+ "companyPost = ? "
				 + " where ebayId = ?"; //(10個)
		
	
		PreparedStatement ps = null;
		System.out.print(strsql); 
		ps = conn.prepareStatement(strsql);
		
//		ps.setString(1, request.getParameter("ebayToken")); //1st
//		ps.setString(2, request.getParameter("endToken"));
//		ps.setString(3, request.getParameter("paypalAccount"));
//		ps.setString(4, request.getParameter("correspondCompany"));
//		ps.setString(5, request.getParameter("startTime"));
//		ps.setString(6, request.getParameter("lastFixTime"));  //6th
//		ps.setString(7, request.getParameter("status"));
//		ps.setString(8, request.getParameter("comment"));
//		ps.setString(9, request.getParameter("systemFeedback"));
//		ps.setString(10, request.getParameter("ebayId")); //10th
		
		
		
		ps.setString(1, request.getParameter("ebayToken")); 
		ps.setString(2, request.getParameter("paypalAccount"));
		ps.setString(3, request.getParameter("correspondCompany"));
		
		  
		ps.setString(4, request.getParameter("status"));
		ps.setString(5, request.getParameter("comment"));
		ps.setString(6, request.getParameter("systemFeedback"));
		
		ps.setString(7, request.getParameter("companyAddress"));
		ps.setString(8, request.getParameter("companyPhone"));
		ps.setString(9, request.getParameter("companyPost")); 
		ps.setString(10, request.getParameter("ebayId")); 
		
		
		
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
