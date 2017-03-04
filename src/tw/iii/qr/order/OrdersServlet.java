package tw.iii.qr.order;

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

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.DTO.COrders;
import tw.iii.qr.order.DTO.SessionRecord;
import tw.iii.qr.order.DTO.ShipmentRecord;

@WebServlet("/OrdersServlet")
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String Order_Processing = "處理中";
	private static String Order_Pickup = "揀貨中";
	private static String Order_Send = "已出貨";
	private static String Order_Finished = "已完成";
	private static String Order_Refund = "退貨";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processSearchOrders(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processSearchOrders(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processSearchOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		COrderFactory OFactory = new COrderFactory();
		LinkedList<COrders> orderList = new LinkedList<>();
		String QR_id = request.getParameter("QR_id");
		String submit = request.getParameter("submit");
		System.out.println(submit);
		switch (submit) {
		case "orderSearch":
			orderList = COrderFactory.orders(request,"");
			session.setAttribute("orderSearch", orderList);
			response.sendRedirect("QROrders/SearchOrder.jsp?begin=0&end=10");
			break;
		case "processingSearch":
			orderList = COrderFactory.orders(request,Order_Processing);
			session.setAttribute("processing", orderList);
			response.sendRedirect("QROrders/OrderProcessingPage.jsp?begin=0&end=10");
			break;
		case "pickupSearch":
			orderList = COrderFactory.orders(request,Order_Pickup);
			session.setAttribute("pickup", orderList);
			response.sendRedirect("QROrders/OrderPickupPage.jsp?begin=0&end=10");
			break;
		case "finishedSearch":
			orderList = COrderFactory.orders(request,Order_Finished);
			session.setAttribute("finished", orderList);
			response.sendRedirect("QROrders/OrderFinished.jsp?begin=0&end=10");
			break;
		case "refundSearch":
			orderList = COrderFactory.orders(request,Order_Refund);
			session.setAttribute("refund", orderList);
			response.sendRedirect("QROrders/refundPage.jsp?begin=0&end=10");
			break;
		case "shipmentRecordSearch":
			LinkedList<ShipmentRecord>	shipmentRecord = COrderFactory.searchShipmentRecord(request);
			session.setAttribute("shipmentRecords", shipmentRecord);
			response.sendRedirect("QROrders/ShipmentRecord.jsp?begin=0&end=10");
			break;
		case "updateOrder":
			// OFactory.updateOrderDetail(request, conn);
			String staffName = session.getAttribute("staffName").toString();
			OFactory.updateOrderDetail(request, staffName, QR_id);
			OFactory.updateMainOrderDetail(request, QR_id);
			response.sendRedirect("QROrders/OrderDetail.jsp?QR_id=" + request.getParameter("QR_id"));
			break;
		case "toGetProducts":
			response.sendRedirect("QROrders/selectProduct.jsp?QR_id=" + request.getParameter("QR_id"));
			break;
		case "insertSKU":
			OFactory.insertOrderDetail(request, QR_id);
			response.sendRedirect("QROrders/OrderDetail.jsp?QR_id=" + QR_id);
			break;
		case "deleteDetail":
			OFactory.deleteFromOrderDetail(request);
			response.sendRedirect("QROrders/OrderDetail.jsp?QR_id=" + QR_id);
		case "updateOrderNew":
			String staffName2 = session.getAttribute("staffName").toString();
			OFactory.updateOrderDetail(request, staffName2, QR_id);
			OFactory.updateMainOrderDetail(request, QR_id);
			response.sendRedirect("QROrders/NewOrder.jsp?QR_id=" + QR_id);
			break;
		case "toGetProductsNew":
			response.sendRedirect("QROrders/selectProductNew.jsp?QR_id=" + QR_id);
			break;
		case "insertSKUNew":
			OFactory.insertOrderDetail(request, QR_id);
			response.sendRedirect("QROrders/NewOrder.jsp?QR_id=" + QR_id);
			break;
		case "deleteDetailNew":
			OFactory.deleteFromOrderDetail(request);
			response.sendRedirect("QROrders/NewOrder.jsp?QR_id=" + QR_id);
			break;
		case "sendNewOrder":
			OFactory.updateToPickUp(request);
			response.sendRedirect("QROrders/OrderPickupPage.jsp?begin=0&end=10");
			break;
		case "CreateCombineOrders":
			COrderCombineFactory Ccoc = new COrderCombineFactory();
			String result = Ccoc.CombineOrders(request);
			out.println(result);
			out.println("1秒後跳轉回合併訂單頁面");
			out.println("<br/>");
			response.setHeader("Refresh", "1; /QROrders/OrderCombine.jsp");
			break;
		case "GoOrderCombine":
			response.sendRedirect("QROrders/OrderCombine.jsp");
			break;
		case "ReadCombineOrders":
			COrderCombineFactory Rcoc = new COrderCombineFactory();
			Rcoc.canCombine(request);
			response.sendRedirect("QROrders/ReadCombineOrders.jsp");
			break;
		default:
			response.sendRedirect("QROrders/SearchOrder.jsp?begin=0&end=10");
			break;

		}
	}
}
