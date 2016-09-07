package tw.iii.qr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginVerifyJavaBean {

	HttpSession session;

	public void LoginVerify(HttpServletRequest request, HttpServletResponse response) throws IOException {

		session = request.getSession(false);

		if (session.getAttribute("account") == null) {

			response.sendRedirect("../Login.jsp");
		}

	}

//	public boolean competenceCheck(String competenceLV, String competence)
//			throws IllegalAccessException, ClassNotFoundException, Exception {
//
//		Connection conn = new DataBaseConn().getConn();
//		
//		String sqlstr = "SELECT " + competence + " FROM competencelv where competenceLV='" + competenceLV + "'";
//		PreparedStatement ps = conn.prepareStatement(sqlstr);
//		ResultSet rs = ps.executeQuery();
//		boolean b = false;
//		while (rs.next()) {
//
//			if (rs.getInt(1) == 1) {
//				b = true;
//			} else {
//				b = false;
//			}
//		}
//		return b;
//
//	}

//	public void competenceSession(HttpServletRequest request)
//			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
//
//		session = request.getSession(false);
//		Connection conn = new DataBaseConn().getConn();
//		
//
//		String competenceLV = (String) session.getAttribute("competenceLV");
//
//		String sqlstr = "SELECT * FROM competencelv where competenceLV= ? ";
//		Competence ct = new Competence();
//		PreparedStatement ps = conn.prepareStatement(sqlstr);
//		ps.setString(1, competenceLV);
//		ResultSet rs = ps.executeQuery();
//
//		while (rs.next()) {
//
//
//			ct.setProductManage(rs.getInt(2));
//			ct.setPurchaseManage(rs.getInt(3));
//			ct.setInventoryManage(rs.getInt(4));
//			ct.setInventoryInfoEdit(rs.getInt(5));
//			ct.setClientManage(rs.getInt(6));
//			ct.setEntireOrders(rs.getInt(7));
//			ct.setOrdersInvoiceDownload(rs.getInt(8));
//			ct.setPriceChange(rs.getInt(9));
//			ct.setPendingOrdersEdit(rs.getInt(10));
//			ct.setTotalAmountEdit(rs.getInt(11));
//			ct.setOrdersManage(rs.getInt(12));
//			ct.setChartView(rs.getInt(13));
//			ct.setProductProfitView(rs.getInt(14));
//			ct.setReportView(rs.getInt(15));
//			ct.setProductCostView(rs.getInt(16));
//			ct.setAccountInfoEdit(rs.getInt(17));
//			ct.setEbayPaypalAccountEdit(rs.getInt(18));
//			ct.setParamSettingEdit(rs.getInt(19));
//			ct.setInventoryCostView(rs.getInt(20));
//
//			
//			// session.setAttribute("productManage",rs.getInt(2));
//			// session.setAttribute("purchaseManage",rs.getInt(3));
//			// session.setAttribute("inventoryManage",rs.getInt(4));
//			// session.setAttribute("inventoryInfoEdit",rs.getInt(5));
//			// session.setAttribute("clientManage",rs.getInt(6));
//			// session.setAttribute("entireOrders",rs.getInt(7));
//			// session.setAttribute("ordersInvoiceDownload",rs.getInt(8));
//			// session.setAttribute("priceChange",rs.getInt(9));
//			// session.setAttribute("pendingOrdersEdit",rs.getInt(10));
//			// session.setAttribute("totalAmountEdit",rs.getInt(11));
//			// session.setAttribute("ordersManage",rs.getInt(12));
//			// session.setAttribute("chartView",rs.getInt(13));
//			// session.setAttribute("productProfitView",rs.getInt(14));
//			// session.setAttribute("reportView",rs.getInt(15));
//			// session.setAttribute("productCostView",rs.getInt(16));
//			// session.setAttribute("accountInfoEdit",rs.getInt(17));
//			// session.setAttribute("paramSettingEdit",rs.getInt(18));
//			// session.setAttribute("ebayPaypalAccountEdit",rs.getInt(19));
//			// session.setAttribute("inventoryCostView",rs.getInt(20));
//
//		}
//		session.setAttribute("PageCompetence", ct);
//		rs.close();
//		ps.close();
//		conn.close();
//	}

}
