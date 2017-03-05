package tw.iii.IDP;

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
import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;
import tw.iii.qr.IndependentOrder.service.IordersDetailService;
import tw.iii.qr.IndependentOrder.service.IordersMasterService;
import tw.iii.qr.order.COrderFactory;
import tw.iii.qr.order.CompleteSale;
import tw.iii.qr.order.DTO.COrderMaster;
import tw.iii.qr.stock.CDBtoExcel;

@WebServlet("/IDPStatusDo")
public class IDPStatusDo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IDPStatusDo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processSubmit(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processSubmit(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		IOrderFactory OFactory = new IOrderFactory();
		IordersMaster Origincdm = new IordersMaster();
		IordersMasterService ims = new IordersMasterService();
		Origincdm.setTrackingCode(request.getParameter("trackingCode"));
		Origincdm.setLogistics(request.getParameter("logistics"));
		Origincdm.setQrId(request.getParameter("QR_id"));
		Origincdm.setStaffName(request.getParameter("staffName"));
		String send = request.getParameter("send");

		switch (send) {

		case "printsent":
			String[] path = new CDBtoExcel().logisticsselect(request, response);
			session.setAttribute("pathsent", path);
			response.sendRedirect("/href/toExcel.jsp?excel=4");

			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = 'QROrders/OrderPickupPage.jsp?begin=0&end=10';");
			out.write("</script>");

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
			ims.updateToPickUp(request);
			response.sendRedirect("QRIndependentOrder/Pickup.jsp?begin=0&end=10");
			break;

		case "pickUp":
			ims.updateToComplete(request);
			response.sendRedirect("QRIndependentOrder/UploadTrackingCode.jsp?begin=0&end=10");
			break;
//		case "sendTrackingCode":
//			String result = DoSendTrackingCode(Origincdm, response, out);
//
//			out.println(result);
//			out.println("1秒後跳轉回上傳追蹤碼頁面");
//			out.println("<br/>");
//			response.setHeader("Refresh", "1; /QROrders/OrderUploadTrackingCode.jsp?begin=0&end=10");
//
//			break;
//		case "finished":
//			ims.updateToRefund(request);
//			ims.isBundleAddBackToStock(Origincdm);
//			response.sendRedirect("QROrders/refundPage.jsp?begin=0&end=10");
//			break;
//		case "revertTo":
//			ims.revertTo(request);
//			response.sendRedirect(request.getHeader("Referer"));
//			break;
//		case "deleteUndo":
//			ims.deleteUndoOrder(request);
//			response.sendRedirect("QROrders/NewOrderSearch.jsp?begin=0&end=10");
//			break;

		}
		out.close();

	}

//	private String DoSendTrackingCode(IordersMaster origincdm, HttpServletResponse response, PrintWriter out)
//			throws Exception {
//
//		IOrderFactory OFactory = new IOrderFactory();
//		// 找出真正的訂單號 單筆 size =1 合併size >1 更新追蹤碼 改狀態 加入shippingDate
//		LinkedList<IordersMaster> TrueOrders = OFactory.checkIDPOrderIdOrderStatus(origincdm);
//
//		//扣庫存 寫Shippinglog 寫扣庫存紀錄
//		for (IordersMaster corder : TrueOrders) {
//			OFactory.checkIsBundleAndDeductStock(corder);
//			
//			//TODO IDP SHIPPING
//			OFactory.insertIntoShippingLog(corder);
////			OFactory.insertIntoPurchaseLogFromOrders(corder);
//			System.out.println("sendTrackingCode success");
//
//		}
//		// 如果訂單超過一筆表示是合併訂單 應該另外處理把合併訂單送到已完成 與 把狀態修改成已完成
//		return "Success";
//
//	}

}
