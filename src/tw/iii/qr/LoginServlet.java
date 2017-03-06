package tw.iii.qr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.Competenece.Competence;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			processLogin(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	

	}

	private void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, ClassNotFoundException, Exception {

		AccountInfodata ac = new AccountInfodata();
		request.setCharacterEncoding("UTF-8");
		ac.setAccount(request.getParameter("account"));
		ac.setPassword(request.getParameter("password"));
		HttpSession session = request.getSession();

		if (checkLogin(ac)) {
			// Login true
				session.setAttribute("account", ac.getAccount());
				session.setAttribute("staffName", ac.getStaffname());
				competenceSession(request, ac.getCompetenceLV());
				response.sendRedirect("/HomePage.jsp");
		} else {
			// Login false
			System.out.println("登入失敗");
			session.setAttribute("accountError", 1);
			response.setContentType("text/html;charset=UTF-8");
			response.sendRedirect("/Login.jsp?p=0");
		}
	}

	public boolean checkLogin(AccountInfodata ac)
			throws IllegalAccessException, ClassNotFoundException, Exception {
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "SELECT account,password,lastName,firstName,competenceLV,status FROM accountinfo where account = ?;";
		PreparedStatement ps = conn.prepareStatement(sqlstr);
		ps.setString(1, ac.getAccount());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			if (ac.getAccount().equals(rs.getString(1)) && ac.getPassword().equals(rs.getString(2)) && rs.getInt(6) == 1) {
				ac.setStatus(rs.getInt(6));
				ac.setLastname(rs.getString(3));
				ac.setFirstname(rs.getString(4));
				ac.setStaffname(ac.getLastname()+ac.getFirstname());
				ac.setCompetenceLV(rs.getString(5));
				System.out.println(ac.getStaffname()+" 帳號狀態:" + ac.getStatus() + "帳號等級: "+ac.getCompetenceLV());
				return true;
			}
		}
		
		rs.close();
		ps.close();
		conn.close();
		return false;
	}

	public void competenceSession(HttpServletRequest request, String competencelv)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		HttpSession session = request.getSession(false);
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "SELECT * FROM competencelv where competenceLV= ? ";
		Competence ct = new Competence();
		PreparedStatement ps = conn.prepareStatement(sqlstr);
		ps.setString(1, competencelv);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			ct.setProductManage(rs.getInt(3));
			ct.setPurchaseManage(rs.getInt(4));
			ct.setInventoryManage(rs.getInt(5));
			ct.setInventoryInfoEdit(rs.getInt(6));
			ct.setClientManage(rs.getInt(7));
			ct.setEntireOrders(rs.getInt(8));
			ct.setOrdersInvoiceDownload(rs.getInt(9));
			ct.setPriceChange(rs.getInt(10));
			ct.setPendingOrdersEdit(rs.getInt(11));
			ct.setTotalAmountEdit(rs.getInt(12));
			ct.setOrdersManage(rs.getInt(13));
			ct.setChartView(rs.getInt(14));
			ct.setProductProfitView(rs.getInt(15));
			ct.setReportView(rs.getInt(16));
			ct.setProductCostView(rs.getInt(17));
			ct.setAccountInfoEdit(rs.getInt(18));
			ct.setEbayPaypalAccountEdit(rs.getInt(19));
			ct.setParamSettingEdit(rs.getInt(20));
			ct.setInventoryCostView(rs.getInt(21));

		}
		session.setAttribute("PageCompetence", ct);
		rs.close();
		ps.close();
		conn.close();
	}

}

