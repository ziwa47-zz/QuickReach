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

		}
		out.close();

	}


}
