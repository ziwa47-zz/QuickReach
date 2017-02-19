package tw.iii.guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import tw.iii.guest.ClassGuest;

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
	
	
}
