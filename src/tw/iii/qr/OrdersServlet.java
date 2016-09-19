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
			processSearchOrders(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSearchOrders(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processSearchOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String QR_id = request.getParameter("QR_id");
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
					conn.close();
					response.sendRedirect("QROrders/SearchOrder.jsp?begin=0&end=10");
					break;
				case "processingSearch":
					//session.setAttribute(sessionRecord.getOrderProcessing(), orderProcessingPageSearch);
					conn.close();
					response.sendRedirect("QROrders/OrderProcessingPage.jsp?begin=0&end=10");
					break;
				case "pickupSearch":
					//session.setAttribute(sessionRecord.getOrderPickUp(), orderProcessingPageSearch);
					conn.close();
					response.sendRedirect("QROrders/OrderPickupPage.jsp?begin=0&end=10");
					break;
				case "finishedSearch":
					//session.setAttribute(sessionRecord.getOrderFinished(), orderProcessingPageSearch);
					conn.close();
					response.sendRedirect("QROrders/OrderFinished.jsp?begin=0&end=10");
					break;
				case "updateOrder":
					//OFactory.updateOrderDetail(request, conn);
					String staffName = session.getAttribute("account").toString();
					OFactory.updateOrderDetail(request, conn, staffName, QR_id);
					conn.close();
					response.sendRedirect("QROrders/OrderDetail.jsp?QR_id=" + request.getParameter("QR_id"));
					break;
				case "toGetProducts":
					conn.close();
					response.sendRedirect("QROrders/selectProduct.jsp?QR_id=" + request.getParameter("QR_id"));
					break;
				case "insertSKU":
					OFactory.insertOrderDetail(request, conn, QR_id);
					conn.close();
					response.sendRedirect("QROrders/OrderDetail.jsp?QR_id=" + QR_id);
					break;
				case "deleteDetail":
					OFactory.deleteFromOrderDetail(request, conn);
					conn.close();
					response.sendRedirect("QROrders/OrderDetail.jsp?QR_id=" + QR_id);
				default:
					conn.close();
					response.sendRedirect("QROrders/SearchOrder.jsp?begin=0&end=10");
					
				}
				
				
		
		
		
			
			
		
		
			
			
		
		
			
			
		
		
		
		
		
	}
	
	

}
