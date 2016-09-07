package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
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
import tw.iii.qr.order.SessionRecord;
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
		LinkedList<COrders> orderProcessingPageSearch = OFactory.orderProcessingPageSearch(request, conn);
		SessionRecord sessionRecord = new SessionRecord();
		session.setAttribute(sessionRecord.getOrdersResult(), orderProcessingPageSearch);
		//conn.close();
		String submit = request.getParameter("submit");
		
				switch(submit){
				case "orderSearch":
					//session.setAttribute(sessionRecord.getSearchOrder(), orderProcessingPageSearch);
					response.sendRedirect("QROrders/SearchOrder.jsp?begin=0&end=10");
					break;
				case "processingSearch":
					//session.setAttribute(sessionRecord.getOrderProcessing(), orderProcessingPageSearch);
					response.sendRedirect("/QROrders/OrderProcessingPage.jsp?begin=0&end=10");
					break;
				case "pickupSearch":
					//session.setAttribute(sessionRecord.getOrderPickUp(), orderProcessingPageSearch);
					response.sendRedirect("/QROrders/OrderPickupPage.jsp?begin=0&end=10");
					break;
				case "finishedSearch":
					//session.setAttribute(sessionRecord.getOrderFinished(), orderProcessingPageSearch);
					response.sendRedirect("/QROrders/OrderFinished.jsp?begin=0&end=10");
					break;
				case "updateOrder":
					//OFactory.updateOrderDetail(request, conn);
					OFactory.updateOrderDetail(request, conn);
					response.sendRedirect("/QROrders/OrderDetail.jsp?QR_id=" + request.getParameter("QR_id"));
					break;
				default:
					response.sendRedirect("/QROrders/SearchOrder.jsp?begin=0&end=10");
				}
				
				
		
		
		
			
			
		
		
			
			
		
		
			
			
		
		
		
		
		
	}
	
	

}
