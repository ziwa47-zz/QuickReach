﻿package tw.iii.qr.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.xmlrpc.base.Data;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.DTO.COrderFactory;
import tw.iii.qr.stock.CDBtoExcel;


@WebServlet("/StatusDo")
public class StatusDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processSubmit(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			processSubmit(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Connection conn = new DataBaseConn().getConn();
		COrderFactory OFactory = new COrderFactory();
		
		String send = request.getParameter("send");
		
		switch(send){
		case "dayliBalance":
			if(request.getParameter("QR_id") != null && request.getParameter("logistics") != ""){
				if(OFactory.unSelectedList(request) != null){
					OFactory.updateToProcessing(request, conn);
				} else{
					LinkedList<String> printList = OFactory.unSelectedList(request);
					out.write("<script type='text/javascript'>");
					for(int i=0; i<printList.size(); i++){
						out.write("alert('" + printList.get(i) + "');");
					}
					out.write("window.location = 'QROrders/DayliBalanceSheet.jsp';");
					out.write("</script>");
				}
				response.sendRedirect("QROrders/OrderProcessingPage.jsp?begin=0&end=10");
				conn.close();
			} else {
				out.write("<script type='text/javascript'>");
				out.write("alert('未勾選任何一筆訂單，請再次操作');");
				out.write("window.location = 'QROrders/DayliBalanceSheet.jsp';");
				out.write("</script>");
				conn.close();
			}
			break;
		case "printsent":
			  String[] path = new CDBtoExcel().logisticsselect(request,response);
			  session.setAttribute("pathsent", path);
			  System.out.println(7);
			  response.sendRedirect("/href/toExcel.jsp?excel=4");
			  System.out.println(8);
			  
			 

			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = 'QROrders/OrderPickupPage.jsp?begin=0&end=10';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;
		case "printpick":
		  String[] pathpick = new CDBtoExcel().pickup(request, response);
		  
		  session.setAttribute("pathpick", pathpick);
		  response.sendRedirect("/href/toExcel.jsp?excel=5");

		out.write("<script type='text/javascript'>");
		out.write("alert('列印成功');");
		out.write("window.location = 'QROrders/OrderPickupPage.jsp?begin=0&end=10';");
		out.write("</script>");
		// session.removeAttribute("excelpath");
		break;
		
		case "printcoll":
		  String[] pathcoll = new CDBtoExcel().collect(request, response);
		  session.setAttribute("pathcoll", pathcoll);
		  response.sendRedirect("/href/toExcel.jsp?excel=6");

		out.write("<script type='text/javascript'>");
		out.write("alert('列印成功');");
		out.write("window.location = 'QROrders/OrderPickupPage.jsp?begin=0&end=10';");
		out.write("</script>");
		// session.removeAttribute("excelpath");
		break;
		case "printlogistic":
			  String[] pathlogistic = new CDBtoExcel().物流匯出格式();
			  session.setAttribute("pathlogistic", pathlogistic);
			  response.sendRedirect("/href/toExcel.jsp?excel=1");

			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = 'QROrders/OrderPickupPage.jsp?begin=0&end=10';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;
		case "printdaily":
			  response.sendRedirect("/href/toExcel.jsp?excel=2");
			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = 'QROrders/OrderPickupPage.jsp?begin=0&end=10';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;
		case "printdailyreport":
			  response.sendRedirect("/href/toExcel.jsp?excel=3");
			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = 'QROrders/OrderPickupPage.jsp?begin=0&end=10';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;
		case "processing":
			LinkedList<String> warehouses = OFactory.getWarehouse(request);
			
//			for(int i=0; i<warehouses.size(); i++){
//				System.out.println("warehouse:" + warehouses.get(i));
//				if(OFactory.isNullorEmpty(warehouses.get(i))){
//					out.write("<script type='text/javascript'>");
//					out.write("alert('訂單尚未選擇倉別，請再次操作');");
//					out.write("window.location = 'QROrders/OrderProcessingPage.jsp?begin=0&end=10';");
//					out.write("</script>");
//					conn.close();
//				}
//			}
			OFactory.updateToPickUp(request, conn);
			response.sendRedirect("QROrders/OrderPickupPage.jsp?begin=0&end=10");
			conn.close();
			break;
			
		case "pickUp":
			OFactory.updateToComplete(request, conn);
			response.sendRedirect("QROrders/OrderUploadTrackingCode.jsp?begin=0&end=10");
			conn.close();
			break;
		case "sendTrackingCode":
			if (!OFactory.checkOrderIdOrderStatus(request, conn) == false){
				System.out.println("checked true");
				OFactory.updateToFinished(request, conn);
				OFactory.isBundledeductStock(request, conn);
				OFactory.insertIntoShippingLog(request, conn);
				OFactory.insertIntoPurchaseLogFromOrders(request, conn);
				CompleteSale myCompleteSale = new CompleteSale();
				myCompleteSale.CompleteSale1(request);
				response.sendRedirect("QROrders/OrderFinished.jsp?begin=0&end=10");
				conn.close();
			} else {
				System.out.println("checked false");
				out.write("<script type='text/javascript'>");
				out.write("alert('not matched');");
				out.write("window.location = 'QROrders/OrderUploadTrackingCode.jsp';");
				out.write("</script>");
				conn.close();
			}
			break;
		case "finished":
			OFactory.updateToRefund(request, conn);
			OFactory.isBundleAddBackToStock(request, conn);
			response.sendRedirect("QROrders/refundPage.jsp?begin=0&end=10");
			conn.close();
			break;
		case "revertTo":
			OFactory.revertTo(request, conn);
			response.sendRedirect(request.getHeader("Referer"));
			conn.close();
			break;
		case "deleteUndo":
			OFactory.deleteUndoOrder(request, conn);
			response.sendRedirect("QROrders/NewOrderSearch.jsp?begin=0&end=10");
			conn.close();
			break;
//		case "sendTrackingCodeSandbox":
//			OFactory.updateToFinished(request, conn);
//			CompleteSale myCompleteSale = new CompleteSale();
//			myCompleteSale.CompleteSale1(request);
//			response.sendRedirect("QROrders/OrderFinished.jsp?begin=0&end=10");
//			conn.close();
//			break;
		}
		out.close();
		conn.close();
		
	}

}