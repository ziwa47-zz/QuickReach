package tw.iii.guest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.stock.CEbayFactory;

@WebServlet("/GuestServelet")
public class GuestServerlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PrintWriter out;
	private LinkedList<String> list;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// processeBayAccount(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String submitType = request.getParameter("submitType");
			System.out.println("submitType:"+submitType);
			switch (submitType) {
			
			case "newGuestAccount":
				processaddGuestAccount(request, response); // new a Account
				break;
			case "updateGuestAccount":
				processupdateAccount(request, response); // update Account
				break;
			case "deleteGuestAccount":
				processDeleteAccount(request, response);//delete
			default:
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//新增Guest帳號//
	private void processaddGuestAccount(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		conn = new DataBaseConn().getConn();
		GuestFactory guestFac = new GuestFactory();
		System.out.println("add,"+request.getParameter("guestId"));
		guestFac.insertGuest(request, conn);

		conn.close();
		response.sendRedirect("GuestAccount/GuestAccount.jsp");
	}

	//edit Guest帳號//
		private void processupdateAccount(HttpServletRequest request, HttpServletResponse response)
				throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			conn = new DataBaseConn().getConn();
			GuestFactory guestFac = new GuestFactory();
			System.out.println("update:"+request.getParameter("GuestId"));
			guestFac.editGuest(request, conn);

			conn.close();
			response.sendRedirect("GuestAccount/GuestAccount.jsp");
		}
	
		// 刪除 Guest 帳號//
		private void processDeleteAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			conn = new DataBaseConn().getConn();
			GuestFactory guestFac = new GuestFactory();
			System.out.println("deleteGuestID:"+request.getParameter("Id"));		
			guestFac.deleteGuest(request.getParameter("Id"));

			conn.close();
			response.sendRedirect("GuestAccount/GuestAccount.jsp");
		}
		
		
}
