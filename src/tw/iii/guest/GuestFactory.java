package tw.iii.guest;

import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;


import tw.iii.qr.DataBaseConn;
import tw.iii.qr.IndependentOrder.model.entity.Guest;
import tw.iii.supplyCompany.CSupplyCompany;

public class GuestFactory extends Guest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public GuestFactory(){
		
	}


	public void insertGuest(Connection conn, HttpServletRequest request) throws ParseException	{

		Guest cg = new Guest();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

		cg.setBirthday(dateFormat.parse(request.getParameter("birthday")));

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

			ps.setDate(12,new java.sql.Date(cg.getBirthday().getTime()));

			ps.setString(13, cg.getGender());   //13th//
			ps.executeUpdate();
			
			System.out.println("Guest Insert processing...");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
		

	public void editGuest(Connection conn, HttpServletRequest request) throws ParseException{

		Guest cg = new Guest();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

		cg.setBirthday(dateFormat.parse(request.getParameter("birthday")));

		cg.setGender(request.getParameter("gender"));    //13th//
		cg.setGuestId(request.getParameter("item"));
		
		String sqlstr = "UPDATE guest set "
				+ "guestId = ?, name = ?, company = ?, platformAccount = ?,"
				+ "email = ?, country = ?, tel = ?, address = ?, comment = ?,"
				+ "phone = ?, postcode = ?, birthday = ?, gender = ? "
				+ "where id = ?";  


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
			ps.setDate(12,new java.sql.Date(cg.getBirthday().getTime()));
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
	public LinkedList<Guest> searchSGName(Connection conn,HttpServletRequest request) {
		LinkedList<Guest> sgnameall = new LinkedList<Guest>();		
		String strsql = "select id,guestid,name,company~ from  guest ";

		try {
			PreparedStatement ps = conn.prepareStatement(strsql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Guest cguest = new Guest();
				
				//cguest.setId(Integer.parseInt(rs.getString(1)));
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
				cguest.setBirthday(rs.getDate(13));
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
	public Guest searchDetail(Connection conn,HttpServletRequest request)  {
		String id = request.getParameter("p");
		Guest sgname = new Guest();
		String strsql = "SELECT  *  FROM   guest where id = ?";

		System.out.println("I get guest id:" + id);
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(strsql);		
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery(); 
			
			while (rs.next()) {
				sgname.setId(Integer.parseInt(rs.getString(1))); // item
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
				sgname.setBirthday(rs.getDate(13)); 
				sgname.setGender(rs.getString(14)); 			
							
				System.out.println("ID: "+rs.getString(1) +"GuestId:" + rs.getString(2)+"Name :" + rs.getString(3));
			}			
			rs.close();
			ps.close();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}				
		return sgname;	
	}
	
	public void deleteGuest (int id) throws SQLException{
		Connection conn = null;
		try {
			conn = new DataBaseConn().getConn();
			String strsql ="delete guest where Id = ?";
			PreparedStatement ps = null;
			ps = conn.prepareStatement(strsql);		
			ps.setInt(1, id);	
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("刪除guest 失敗");
			e.printStackTrace();
		}
		
	}
	
}
