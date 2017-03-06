package tw.iii.IDP;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.iii.qr.IndependentOrder.controller.RedirectController;
import tw.iii.qr.IndependentOrder.model.entity.Guest;
import tw.iii.qr.IndependentOrder.model.entity.IDPorderAll;
import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;
import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;
import tw.iii.qr.IndependentOrder.model.repository.IordersMasterDAO;
import tw.iii.qr.IndependentOrder.service.CompanyService;
import tw.iii.qr.IndependentOrder.service.GuestService;
import tw.iii.qr.IndependentOrder.service.IordersMasterService;
import tw.iii.qr.IndependentOrder.service.StockTransferService;
import tw.iii.qr.IndependentOrder.service.StorageService;
import tw.iii.qr.IndependentOrder.service.WarehouseService;
import tw.iii.qr.stock.CDBtoExcelIDP;

@Controller
public class IDPStatusDo extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	@Resource
	CompanyService companyService;

	@Resource
	WarehouseService warehouseService;

	@Resource
	IordersMasterService iordersMasterService;
	@Resource
	GuestService guestService;
	@Resource
	StorageService storageService;

	@Resource
	StockTransferService stockTransferService;
	
	@Resource
	RedirectController redirectController;
	@Resource
	IOrderFactory iOrderFactory;


	@RequestMapping(value ="QRIndependentOrder/IDPStatusDo")
	private void processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		IordersMaster Origincdm = new IordersMaster();
		Origincdm.setTrackingCode(request.getParameter("trackingCode"));
		Origincdm.setLogistics(request.getParameter("logistics"));
		Origincdm.setQrId(request.getParameter("QR_id"));
		Origincdm.setStaffName(request.getParameter("staffName"));
		String[] trueurl = null;
		String url ="";
		String send ="";
		String submit ="";
		if(!isNullorEmpty(request.getParameter("send")))
			 send = request.getParameter("send");
		if(!isNullorEmpty(request.getParameter("submit")))
			submit = request.getParameter("submit");
		
		
		switch (submit){
		case "updateOrder":
			iOrderFactory.updateOrder(request);
			url=redirectController.redirectOrderDetail(request);
			trueurl=url.split(":");
			response.sendRedirect(trueurl[1]);
			break;
		case "toGetProducts":
			response.sendRedirect("/QRIndependentOrder/selectProductNew.jsp?QR_id="+request.getParameter("QR_id"));
			break;
		case "insertSKUNew":
			iOrderFactory.insertOrderDetail(request);
			url=redirectController.redirectOrderDetail(request);
			trueurl=url.split(":");
			response.sendRedirect(trueurl[1]);
			break;	
		case "deleteDetail":
			iOrderFactory.deleteFromOrderDetail(request);
			url=redirectController.redirectOrderDetail(request);
			trueurl=url.split(":");
			System.out.print(trueurl[1]);
			response.sendRedirect(trueurl[1]);
			break;
		}
		
		switch (send) {

		case "printsent":
			String[] path = new CDBtoExcelIDP().logisticsselect(request, response);
			session.setAttribute("pathsent", path);
			response.sendRedirect("/href/toExcel.jsp?excel=4idp");

			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = '/QRIndependentOrder/Pickup';");
			out.write("</script>");

			break;
		case "printpick":
			String[] pathpick = new CDBtoExcelIDP().pickup(request, response);

			session.setAttribute("pathpick", pathpick);
			response.sendRedirect("/href/toExcel.jsp?excel=5idp");

			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = '/QRIndependentOrder/Pickup';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;

		case "printcoll":
			String[] pathcoll = new CDBtoExcelIDP().collect(request, response);
			session.setAttribute("pathcoll", pathcoll);
			response.sendRedirect("/href/toExcel.jsp?excel=6idp");

			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = '/QRIndependentOrder/Pickup';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;
		case "printlogistic":
			String[] pathlogistic = new CDBtoExcelIDP().物流匯出格式();
			session.setAttribute("pathlogistic", pathlogistic);
			response.sendRedirect("/href/toExcel.jsp?excel=1idp");

			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = '/QRIndependentOrder/Pickup';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;
		case "printdaily":
			response.sendRedirect("/href/toExcel.jsp?excel=2idp");
			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = '/QRIndependentOrder/Pickup';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;
		case "printdailyreport":
			response.sendRedirect("/href/toExcel.jsp?excel=3idp");
			out.write("<script type='text/javascript'>");
			out.write("alert('列印成功');");
			out.write("window.location = '/QRIndependentOrder/Pickup';");
			out.write("</script>");
			// session.removeAttribute("excelpath");
			break;
		case "processing":
			iordersMasterService.updateToPickUp(request);
			response.sendRedirect("/QRIndependentOrder/Pickup");
			break;

		case "pickUp":
			iordersMasterService.updateToComplete(request);
			response.sendRedirect("/QRIndependentOrder/UploadTrackingCode");
			break;
		case "sendTrackingCode":
			String result = DoSendTrackingCode(Origincdm, response, out);

			out.println(result);
			out.println("1秒後跳轉回上傳追蹤碼頁面");
			out.println("<br/>");
			response.setHeader("Refresh", "1; /QRIndependentOrder/UploadTrackingCode");

			break;
		case "finished":
			iordersMasterService.updateToRefund(request);
			iOrderFactory.isBundleAddBackToStock(Origincdm);
			response.sendRedirect("QRIndependentOrder/refundPage.jsp?begin=0&end=10");
			break;
		case "revertTo":
			iordersMasterService.revertTo(request);
			response.sendRedirect(request.getHeader("Referer"));
			break;
			
		


		}
		out.close();

	}

	
	private String DoSendTrackingCode(IordersMaster origincdm, HttpServletResponse response, PrintWriter out)
			throws Exception {

		// 找出真正的訂單號 單筆 size =1 合併size >1 更新追蹤碼 改狀態 加入shippingDate
		LinkedList<IordersMaster> TrueOrders = iOrderFactory.checkIDPOrderIdOrderStatus(origincdm);

		//扣庫存 寫Shippinglog 寫扣庫存紀錄
		for (IordersMaster iorder : TrueOrders) {
			System.out.println(iorder.getQrId());
			iOrderFactory.checkIsBundleAndDeductStock(iorder);
			iOrderFactory.insertIntoShippingLog(iorder);
			iOrderFactory.insertIntoPurchaseLogFromOrders(iorder);
			//OFactory.sendMAil(iorder);
			System.out.println("sendTrackingCode success");

		}
		// 如果訂單超過一筆表示是合併訂單 應該另外處理把合併訂單送到已完成 與 把狀態修改成已完成
		return "Success";

	}
	private static boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

}
