package tw.iii.qr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.order.COrderFactory;
import tw.iii.qr.order.COrderMaster;
import tw.iii.qr.order.COrders;
import tw.iii.qr.DataBaseConn;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processOrdersFunction(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processOrdersFunction(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processOrdersFunction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		processSearchOrders(request,response);
	}

	private void processSearchOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		
		
		Connection conn = new DataBaseConn().getConn();
		COrderFactory OFactory = new COrderFactory();
		//LinkedList<COrders> SearchResult = OFactory.searchOrders(request, conn);
		LinkedList<COrders> orderProcessingPageSearch = OFactory.orderProcessingPageSearch(request, conn);
		session.setAttribute("ResultOrders", orderProcessingPageSearch);
		
		conn.close();
		
		
		
		String submit = request.getParameter("submit");
		
				switch(submit){
				case "toPickupPage":
					response.sendRedirect("QROrders/OrderPickupPage.jsp");
					break;
					
				case "processingSearch":
					response.sendRedirect("QROrders/OrderProcessingPage.jsp");
					break;
				case "pickupSearch":
					response.sendRedirect("QROrders/OrderPickupPage.jsp");
					break;
					
				case "orderSearch":
					response.sendRedirect("QROrders/SearchOrder.jsp");
					break;
					
			
					
					
					
					default:
						response.sendRedirect("QROrders/SearchOrder.jsp");
					
				}
				
				
		
		
		
			
			
		
		
			
			
		
		
			
			
		
		
		
		
		
	}
	
	

}
