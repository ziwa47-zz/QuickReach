package tw.iii.guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import tw.iii.guest.ClassGuest;
import tw.iii.supplyCompany.CSupplyCompany;

public class GuestFactory extends ClassGuest{
	
	public GuestFactory(){
		
	}

	public void insertGuest(Connection conn, HttpServletRequest request)	{

		ClassGuest cg = new ClassGuest();

		cg.setGuestId(request.getParameter("guestId"));   //1st//
		cg.setName(request.getParameter("name"));
		cg.setCompany(request.getParameter("company"));
		cg.setPlatformAccount(request.getParameter("platformAccount"));
		cg.setEmail(request.getParameter("email"));
		cg.setCountry(request.getParameter("country"));
		cg.setTel(request.getParameter("tel"));       //7th//
		cg.setAddress(request.getParameter("address"));
		cg.setComment(request.getParameter("comment"));
		cg.setPhone(request.getParameter("phone"));
		cg.setPostcode(request.getParameter("postcode"));
		cg.setBirthday(request.getParameter("birthday"));
		cg.setGender(request.getParameter("gender"));    //13th//
		
		String sqlstr = "insert into guest values(?,?,?,?,?,?,?,?,?,?,?,?,?)"; // 13個
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sqlstr);
			ps.setString(1, cg.getGuestId());  //1st//
			ps.setString(2, cg.getName());
			ps.setString(3, cg.getCompany());
			ps.setString(4, cg.getPlatformAccount());
			ps.setString(5, cg.getEmail());
			ps.setString(6, cg.getCountry()); 
			ps.setString(7, cg.getTel());    //7th//
			ps.setString(8, cg.getAddress());
			ps.setString(9, cg.getComment());
			ps.setString(10, cg.getPhone());
			ps.setString(11, cg.getPostcode());
			ps.setString(12, cg.getBirthday());
			ps.setString(13, cg.getGender());   //13th//
			ps.executeUpdate();
			
			System.out.println("Guest Insert processing...");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
		
	public void editGuest(Connection conn, HttpServletRequest request){

		ClassGuest cg = new ClassGuest();
		
		cg.setGuestId(request.getParameter("guestId"));   //1st//
		cg.setName(request.getParameter("name"));
		cg.setCompany(request.getParameter("company"));
		cg.setPlatformAccount(request.getParameter("platformAccount"));
		cg.setEmail(request.getParameter("email"));
		cg.setCountry(request.getParameter("country"));  //6th//
		cg.setTel(request.getParameter("tel"));       
		cg.setAddress(request.getParameter("address"));
		cg.setComment(request.getParameter("comment"));
		cg.setPhone(request.getParameter("phone"));
		cg.setPostcode(request.getParameter("postcode"));
		cg.setBirthday(request.getParameter("birthday"));
		cg.setGender(request.getParameter("gender"));    //13th//
		cg.setGuestId(request.getParameter("item"));
		
		String sqlstr = "UPDATE guest set "
				+ "guestId = ?, name = ?, company = ?, platformAccount = ?,"
				+ "email = ?, country = ?, tel = ?, address = ?, comment = ?,"
				+ "phone = ?, postcode = ?, birthday = ?, gender = ? "
				+ "where item = ?";  

		try {
			PreparedStatement ps = conn.prepareStatement(sqlstr);
			
			ps.setString(1, cg.getGuestId()); //1st//
			ps.setString(2, cg.getName());
			ps.setString(3, cg.getCompany());
			ps.setString(4, cg.getPlatformAccount());
			ps.setString(5, cg.getEmail());
			ps.setString(6, cg.getCountry()); //6th//
			ps.setString(7, cg.getTel());
			ps.setString(8, cg.getAddress());
			ps.setString(9, cg.getComment());
			ps.setString(10, cg.getPhone());
			ps.setString(11, cg.getPostcode());  
			ps.setString(12, cg.getBirthday());
			ps.setString(13, cg.getGender());  //13th//
						
			ps.executeUpdate();						
			ps.close();

		} catch (SQLException e) {			
			e.printStackTrace();
		}
				
		System.out.println("editGuest:" + sqlstr);
	//	System.out.println("item:"+request.getParameter("item"));//
		System.out.println("editGuest info processEdit");
	}
	
	// --搜尋  guest all 名稱--//
	public LinkedList<ClassGuest> searchSGName(Connection conn,HttpServletRequest request) {
		LinkedList<ClassGuest> sgnameall = new LinkedList<ClassGuest>();		
		String strsql = "select * from  guest ";

		try {
			PreparedStatement ps = conn.prepareStatement(strsql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ClassGuest cguest = new ClassGuest();
				
				cguest.setItem(rs.getString(1));
				cguest.setGuestId(rs.getString(2));
				cguest.setName(rs.getString(3));
				cguest.setCompany(rs.getString(4));
				cguest.setPlatformAccount(rs.getString(5));
				cguest.setEmail(rs.getString(6)); 
				cguest.setCountry(rs.getString(7));
				cguest.setTel(rs.getString(8));
				cguest.setAddress(rs.getString(9));
				cguest.setComment(rs.getString(10));
				cguest.setPhone(rs.getString(11));
				cguest.setPostcode(rs.getString(12));
				cguest.setBirthday(rs.getString(13));
				cguest.setGender(rs.getString(14));
				
				sgnameall.add(cguest);
			}			
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return sgnameall;
	}
	
	// --搜尋guest detail--//	
	public  ClassGuest searchDetail(Connection conn,HttpServletRequest request)  {
		String item = request.getParameter("p");
		ClassGuest sgname = new ClassGuest();
		String strsql = "SELECT  *  FROM   guest where item = '"+ item +"'";

		System.out.println("I get guest item:" + item);
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(strsql);			
			ResultSet rs = ps.executeQuery(); 
			
			while (rs.next()) {
				sgname.setItem(rs.getString(1)); // item
				sgname.setGuestId(rs.getString(2));  
				sgname.setName(rs.getString(3)); 
				sgname.setCompany(rs.getString(4)); 
				sgname.setPlatformAccount(rs.getString(5)); 
				sgname.setEmail(rs.getString(6)); 
				sgname.setCountry(rs.getString(7));
				sgname.setTel(rs.getString(8)); 
				sgname.setAddress(rs.getString(9)); 
				sgname.setComment(rs.getString(10)); 
				sgname.setPhone(rs.getString(11)); 
				sgname.setPostcode(rs.getString(12)); 
				sgname.setBirthday(rs.getString(13)); 
				sgname.setGender(rs.getString(14)); 			
							
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
			}			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}				
		return sgname;	
	}
	
	public void deleteGuest (HttpServletRequest request, Connection conn) throws SQLException{
		String strsql ="delete guest where guestId = ?";
				
		PreparedStatement ps = null;
		System.out.print(strsql); 
		ps = conn.prepareStatement(strsql);		
		ps.setString(1, request.getParameter("guestId"));	
					
		int i =ps.executeUpdate();
	}
	
}
