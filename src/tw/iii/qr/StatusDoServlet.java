package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import tw.iii.qr.order.COrderFactory;


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
			OFactory.updateToProcessing(request, conn);
			response.sendRedirect("QROrders/OrderProcessingPage.jsp?begin=0&end=10");
			break;
		case "processing":
			OFactory.updateToPickUp(request, conn);
			response.sendRedirect("QROrders/OrderPickupPage.jsp?begin=0&end=10");
			break;
		case "pickUp":
			OFactory.updateToComplete(request, conn);
			response.sendRedirect("QROrders/OrderPickupPage.jsp?begin=0&end=10");
			break;
		case "sendTrackingCode":
			if (!OFactory.checkOrderIdOrderStatus(request, conn) == false){
				System.out.println("checked true");
				OFactory.updateToFinished(request, conn);
				//OFactory.deductStock(request, conn);
				OFactory.insertIntoShippingLog(request, conn);
				
				response.sendRedirect("QROrders/OrderFinished.jsp?begin=0&end=10");
			} else {
				System.out.println("checked false");
				out.write("<script type='text/javascript'>");
				out.write("alert('not matched');");
				out.write("window.location = 'QROrders/OrderUploadTrackingCode.jsp';");
				out.write("</script>");
			}
			break;
		}
		
		conn.close();
		
	}

}
