package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.order.COrderFactory;

@WebServlet("/SendTrackingCodeServlet")
public class SendTrackingCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSendTrackingCode(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processSendTrackingCode(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processSendTrackingCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Connection conn = new DataBaseConn().getConn();
		COrderFactory OFactory = new COrderFactory();
		if (!OFactory.checkOrderIdOrderStatus(request, conn) == false){
			System.out.println("checked true");
		//	OFactory.updateToFinished(request, conn);
		//	OFactory.deductStock(request, conn);
		//	OFactory.insertIntoShippingLog(request, conn);
			OFactory.insertIntoPurchaseLogFromOrders(request, conn);
			response.sendRedirect("QROrders/OrderFinished.jsp");
		} else {
			System.out.println("checked false");
			out.write("<script type='text/javascript'>");
			out.write("alert('not matched');");
			out.write("window.location = 'QROrders/OrderUploadTrackingCode.jsp';");
			out.write("</script>");
		}
		
	}
	
	
	
	
	private void SendTrackingCode() {
		
	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

}
