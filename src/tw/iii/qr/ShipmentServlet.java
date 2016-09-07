package tw.iii.qr;

import java.io.IOException;
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
import tw.iii.qr.order.COrders;
import tw.iii.qr.order.SessionRecord;
import tw.iii.qr.order.ShipmentRecord;

@WebServlet("/ShipmentServlet")
public class ShipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processShipmentSearch(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processShipmentSearch(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processShipmentSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Connection conn = new DataBaseConn().getConn();
		COrderFactory OFactory = new COrderFactory();
		LinkedList<ShipmentRecord> shipmentSearch = OFactory.searchShipmentRecord(request, conn);
		SessionRecord sessionRecord = new SessionRecord();
		session.setAttribute(sessionRecord.getOrdersResult(), shipmentSearch);
		conn.close();
		response.sendRedirect("/QROrders/ShipmentRecord.jsp?begin=0&end=10");
	}

}
