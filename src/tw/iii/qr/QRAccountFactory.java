package tw.iii.qr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import tw.iii.qr.stock.CEbay;

public class QRAccountFactory extends QRAccount{

	private Statement state;
	
	public QRAccountFactory() {

	}
	
	public void insertQRAccount (QRAccount qra) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "insert into  accountinfo values(?,?,?,?,?,?,?,?,?)"; //9個
		PreparedStatement preparedState = conn.prepareStatement(sqlstr);
		
		preparedState.setString(1, qra.getAccount());  //1st
		preparedState.setString(2, qra.getPassword());
		preparedState.setString(3, qra.getLastName());
		preparedState.setString(4, qra.getFirstName());
		preparedState.setString(5, qra.getEmail());
		preparedState.setString(6, qra.getEnName());  //6th
		preparedState.setString(7, qra.getSignatureImage()); //簽名檔先不做
		preparedState.setString(8, qra.getCompetenceLV());
		preparedState.setInt(9, qra.getStatus());  //9th
		
		preparedState.execute();
		preparedState.close();
		
		conn.close();
	}
	
	public QRAccount searchDetail(String account) throws IllegalAccessException, ClassNotFoundException, Exception {

		DataBaseConn dbc = new DataBaseConn();	
		Connection conn = dbc.getConn();		
		String strsql = "SELECT  *  FROM   accountinfo where account = '" + account +"'";

				
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

		String sqlstr = "UPDATE accountinfo set " 
			    + " password = ?," + " lastName = ?," 
				+ " firstName = ?," + " Email = ?," + " enName = ?," 
				+ " competenceLV =?," + "status = ? "
				+ " where account = ?"; //8個

		PreparedStatement preparedState = conn.prepareStatement(sqlstr);
			 
		preparedState.setString(1, qra.getPassword());
		preparedState.setString(2, qra.getLastName());
		preparedState.setString(3, qra.getFirstName());
		preparedState.setString(4, qra.getEmail());
		preparedState.setString(5, qra.getEnName());  //6th
		//preparedState.setString(6, qra.getSignatureImage()); //簽名檔先不做
		System.out.println("Text:"+qra.getCompetenceLV());
		preparedState.setString(6, qra.getCompetenceLV());
		preparedState.setInt(7, qra.getStatus());
		preparedState.setString(8, qra.getAccount()); 
		
		int x = preparedState.executeUpdate();
		preparedState.close();
		
		conn.close();
	}
	
	
	public LinkedList<QRAccount> searchQRemployee(HttpServletRequest request,Connection conn) throws SQLException {
		LinkedList<QRAccount> employeeall = new LinkedList<QRAccount>();
		QRAccount qraccount ;
		
		String strsql = "select * from  accountinfo ";
		
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
	public  LinkedList<Competence> getCompetence(){
		LinkedList<Competence> list= new LinkedList<>();
		try {
			Connection conn = new DataBaseConn().getConn();
			String strsql="select competenceLV   from  competencelv";
			PreparedStatement ps = conn.prepareStatement(strsql);
		
	
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Competence c = new Competence();
				c.setCompetenceLv(rs.getString(1));
				list.add(c);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public boolean isNullorEmpty(String s){
		if (s.length() == 0 || s == null)
		return true;
			
		return false;
	}
	
}
