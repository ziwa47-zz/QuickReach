package tw.iii.qr.order;

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
import tw.iii.qr.stock.CDBtoExcel;

@WebServlet("/toExcelServlet")
public class toExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processToExcel(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processToExcel(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processToExcel(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Connection conn = new DataBaseConn().getConn();
		CDBtoExcel toExcel = new CDBtoExcel();
		
		String submit = request.getParameter("submit");

		switch(submit){
		case "toDailyBalanceSheetExcel":
			toExcel.dailyBalanceSheetExcel();
			conn.close();
			response.sendRedirect("QROrders/ShipmentRecord.jsp?begin=0&end=10");
		}
	}

}
