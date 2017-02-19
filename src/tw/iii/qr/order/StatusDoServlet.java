package tw.iii.qr.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.DTO.COrderMaster;
import tw.iii.qr.stock.CDBtoExcel;

@WebServlet("/StatusDo")
public class StatusDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			processSubmit(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			processSubmit(request, response);
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
		COrderMaster Origincdm = new COrderMaster();
		Origincdm.setEbayAccount(request.getParameter("ebayaccount"));
		Origincdm.setOrder_id(request.getParameter("Order_id"));
		Origincdm.setTrackingCode(request.getParameter("trackingCode"));
		Origincdm.setLogistics(request.getParameter("logistics"));
		Origincdm.setQR_id(request.getParameter("QR_id"));
		Origincdm.setStaffName(request.getParameter("staffName"));
		String send = request.getParameter("send");

		switch (send) {
		case "dayliBalance":
			if (request.getParameter("QR_id") != null && request.getParameter("logistics") != "") {
				if (OFactory.unSelectedList(request) != null) {
					OFactory.updateToProcessing(request, conn);
				} else {
					LinkedList<String> printList = OFactory.unSelectedList(request);
					out.write("<script type='text/javascript'>");
					for (int i = 0; i < printList.size(); i++) {
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
			String[] path = new CDBtoExcel().logisticsselect(request, response);
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
			String result = DoSendTrackingCode(Origincdm, response, out, conn) ;
			out.println(result);
			out.println("1秒後跳轉回上傳追蹤碼頁面");
			out.println("<br/>");
			response.setHeader("Refresh", "1; /QROrders/OrderUploadTrackingCode.jsp?begin=0&end=10");
			
			break;
		case "finished":
			OFactory.updateToRefund(request, conn);
			OFactory.isBundleAddBackToStock(Origincdm, conn);
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
			
		}
		out.close();
		conn.close();

	}

	private String DoSendTrackingCode(COrderMaster origincdm, HttpServletResponse response, PrintWriter out,
			Connection conn) throws Exception, ApiException, SdkException, SQLException, IOException {

		
		COrderFactory OFactory = new COrderFactory();
		//找出真正的訂單號 單筆 size =1 合併size >1
		LinkedList<COrderMaster> TrueOrders = OFactory.checkOrderIdOrderStatus(origincdm, conn);
		
		conn.setAutoCommit(false);
		Savepoint sp1 = conn.setSavepoint();
		for (COrderMaster corder : TrueOrders) {
			//如果上傳OK就繼續寫資料庫 中間失敗就回家並rollback 
			boolean isOK = new CompleteSale().CompleteSale1(corder);
			if (isOK) {
				try {
					OFactory.updateToFinished(corder, conn);
					OFactory.isBundledeductStock(corder, conn);
					OFactory.insertIntoShippingLog(corder, conn);
					OFactory.insertIntoPurchaseLogFromOrders(corder, conn);
					System.out.println("sendTrackingCode success");
				} catch (Exception e) {
					System.out.println("sendTrackingCode fail");
					conn.rollback(sp1);
					return "Fail";
				}
			} else {
				System.out.println("checked false");
				out.write("<script type='text/javascript'>");
				out.write("alert('not matched');");
				out.write("window.location = 'QROrders/OrderUploadTrackingCode.jsp';");
				out.write("</script>");
				conn.rollback(sp1);
				return "Fail";
			}
		}
		//如果訂單超過一筆表示是合併訂單 應該另外處理把合併訂單送到已完成 與 把狀態修改成已完成
		if(TrueOrders.size()>1)
		OFactory.updateToFinished(origincdm, conn);
		conn.commit();
		conn.close();
		return "Success";
		
	}

}
