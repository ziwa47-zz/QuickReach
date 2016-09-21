package tw.iii.supplyCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;


public class SCFactory extends CSupplyCompany {


	public SCFactory() {

	}

	public void insertSCompany(Connection conn, HttpServletRequest request)	{

		CSupplyCompany csc = new CSupplyCompany();

		csc.setCompanyId(request.getParameter("companyId")); 
		csc.setCompanyName(request.getParameter("companyName"));
		csc.setTel(request.getParameter("tel"));
		csc.setFax(request.getParameter("fax"));
		csc.setAddress(request.getParameter("address"));
		csc.setComment(request.getParameter("comment")); 
		
		String sqlstr = "insert into company values(?,?,?,?,?,?)"; // 6個
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sqlstr);
			ps.setString(1, csc.getCompanyId()); // 1st
			ps.setString(2, csc.getCompanyName());
			ps.setString(3, csc.getTel());
			ps.setString(4, csc.getFax());
			ps.setString(5, csc.getAddress());
			ps.setString(6, csc.getComment()); // 6th
			ps.executeUpdate();
			
			System.out.println("processInsert");
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
		

	}

	public  CSupplyCompany searchDetail(Connection conn,HttpServletRequest request)  {
		String item = request.getParameter("p");
		CSupplyCompany scname = new CSupplyCompany();
		String strsql = "SELECT  *  FROM   company where item = '"+ item +"'";

		System.out.println("I get item:" + item);
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(strsql);
			
			ResultSet rs = ps.executeQuery(); 

			

			while (rs.next()) {
				scname.setItem(rs.getString(1)); // item
				scname.setCompanyId(rs.getString(2)); // 1st
				scname.setCompanyName(rs.getString(3)); //
				scname.setTel(rs.getString(4)); //
				scname.setFax(rs.getString(5)); //
				scname.setAddress(rs.getString(6)); //
				scname.setComment(rs.getString(7)); // 6th
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));//

			}
			
			rs.close();
			ps.close();
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return scname;
	

	}

	public void editCompany(Connection conn, HttpServletRequest request){

		CSupplyCompany csc = new CSupplyCompany();
		
		csc.setItem(request.getParameter("item"));

		csc.setCompanyId(request.getParameter("companyId")); 
		csc.setCompanyName(request.getParameter("companyName"));
		csc.setTel(request.getParameter("tel"));
		csc.setFax(request.getParameter("fax"));
		csc.setAddress(request.getParameter("address"));
		csc.setComment(request.getParameter("comment")); 

		String sqlstr = "UPDATE company set  C_id = ?, C_name = ?, tel = ?, fax = ?, address = ?, comment = ? where item = ?"; // 

		try {
			PreparedStatement ps = conn.prepareStatement(sqlstr);
			
			ps.setString(1, csc.getCompanyId());
			ps.setString(2, csc.getCompanyName());
			ps.setString(3, csc.getTel());
			ps.setString(4, csc.getFax());
			ps.setString(5, csc.getAddress());
			ps.setString(6, csc.getComment()); // 6th
			ps.setString(7, csc.getItem());

			ps.executeUpdate();
			
			
			ps.close();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		System.out.println(sqlstr);
		System.out.println("item:"+request.getParameter("item"));
		System.out.println("Hi~ processEdit");
	}

	public LinkedList<CSupplyCompany> searchSCName(Connection conn,HttpServletRequest request) {
		LinkedList<CSupplyCompany> scnameall = new LinkedList<CSupplyCompany>();
		;

		String strsql = "select * from  company ";

		try {
			PreparedStatement ps = conn.prepareStatement(strsql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CSupplyCompany csupplycompany = new CSupplyCompany();

				csupplycompany.setItem(rs.getString(1));
				csupplycompany.setCompanyId(rs.getString(2));//
				csupplycompany.setCompanyName(rs.getString(3));//
				csupplycompany.setTel(rs.getString(4));//
				csupplycompany.setFax(rs.getString(5));//
				csupplycompany.setAddress(rs.getString(6)); //
				csupplycompany.setComment(rs.getString(7));//

				scnameall.add(csupplycompany);
			}
			
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	
		return scnameall;
	}

}
