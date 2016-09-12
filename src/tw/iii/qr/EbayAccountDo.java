package tw.iii.qr;

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
import javax.servlet.http.HttpSession;

import tw.iii.qr.stock.CStock;
import tw.iii.qr.stock.CStockFactory;
import tw.iii.qr.stock.CEbayFactory;

@WebServlet("/EbayAccountDo")
public class EbayAccountDo extends HttpServlet {		
		private static final long serialVersionUID = 1L;
		private Connection conn;
		private PrintWriter out;
		private LinkedList<String> list;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
//				processeBayAccount(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				String submitType = request.getParameter("submit");
				switch(submitType){
				
//				case "submitAccount":
//					processeBayAccount(request, response); //search Account
//					break;
				case "newAccount":
					processaddAccount(request,response); // new a Account
					break;
				case "updateEbayAccount":
					processupdateAccount(request,response); // update Account
					break;
				default:
					break;
					
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
	
		//以下為新增eBay 帳號
		private void processaddAccount(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			conn = new DataBaseConn().getConn();
			CEbayFactory cef = new CEbayFactory();
			System.out.println(request.getParameter("ebayId"));
			cef.InsertNewEbayAccount(request, conn);
			
			conn.close();
			response.sendRedirect("QREBayAccount/addAccount.jsp");
		}
		
		//以下為修改eBay 帳號
				private void processupdateAccount(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
					request.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset=UTF-8");
					conn = new DataBaseConn().getConn();
					CEbayFactory cef = new CEbayFactory();
					System.out.println(request.getParameter("ebayId")+"ttttt");
					cef.updateEbayAccount(request, conn);
					
					conn.close();
					response.sendRedirect("QREBayAccount/eBayAccount.jsp");
				}
		
		
		//以下為serach Ebay 帳號
//		private void processeBayAccount(HttpServletRequest request, HttpServletResponse response)throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
//			request.setCharacterEncoding("UTF-8");
//			response.setContentType("text/html;charset=UTF-8");
//			HttpSession session = request.getSession(); 
//			conn = new DataBaseConn().getConn();
//			CEbayFactory cef = new CEbayFactory();
//			LinkedList<LinkedList<String>> ebaymaster = cef.searchEbayAc(request,conn);
//			session.setAttribute("ebayall", ebaymaster);
//			conn.close();
//			
//			
//			response.sendRedirect("QREbayAccount/eBayAccount.jsp");
//		}
		
}
