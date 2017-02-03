package tw.iii.supplyCompany;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.iii.Maintain.QRAccount;
import tw.iii.Maintain.QRAccountFactory;
import tw.iii.qr.DataBaseConn;


@WebServlet("/SupplyCompanyServlet.do")
public class SupplyCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	SCFactory scf = new SCFactory();

	private Connection conn;
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String submitType = request.getParameter("submitButton");
			
			switch(submitType){
			
			case "addCompany":
				processInsert(request,response); // new a SupplyCompany name
				break;
			case "editCompany":
				processEdit(request,response); // update a SupplyCompany name
				break;
			default:
				break;			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

private void processInsert(HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException, Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		DataBaseConn jdbc = new DataBaseConn();
		conn = jdbc.getConn();
		
		SCFactory scf = new SCFactory();
		scf.insertSCompany(conn, request);
		
		conn.close();
		response.sendRedirect("SupplyCompany/SCManage.jsp");
		
		
	}	
	
private void processEdit(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
	
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	
	DataBaseConn jdbc = new DataBaseConn();
	conn = jdbc.getConn();
	
	SCFactory scf = new SCFactory();
	scf.editCompany(conn, request);
	
	conn.close();
	response.sendRedirect("SupplyCompany/SCManage.jsp");
	
	

}

	
}








