package tw.iii.purchase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.DataBaseConn;

@WebServlet("/InsertPurchaseServlet.do")
public class InsertPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;

	LinkedList<String> values = new LinkedList<String>();

	private String whichWay;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processStockTransfer(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processStockTransfer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		
		
		
		DataBaseConn jdbc = new DataBaseConn();
		conn = jdbc.getConn();
		
		stockTransferFactory stf = new stockTransferFactory();
		LinkedList<Cpurchase> list = stf.searchStockTransfer(conn, request); 
		session.setAttribute("logList", list);
		conn.close();
		response.sendRedirect("QRProduct/SearchStockTransfer.jsp");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processInsert(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	private void processInsert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		whichWay = request.getParameter("newWarehouse");
	

		DataBaseConn jdbc = new DataBaseConn();
		try {
			conn = jdbc.getConn();

			if (isNullorEmpty(whichWay)) {

				System.out.println("whichway:PurchasePage");

				purchaseFactory pcf = new purchaseFactory();
				pcf.insertIntoPurchaseTest(request, conn);
				conn.close();
				response.sendRedirect("QRProduct/PurchasePage.jsp");
			} else {

				System.out.println("whichway:StockTransferPage");

				stockTransferFactory stf = new stockTransferFactory();
				stf.insertIntoStockTransfer(request, conn);

				conn.close();
				response.sendRedirect("QRProduct/StockTransferPage.jsp");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
