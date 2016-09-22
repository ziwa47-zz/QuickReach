package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SQLInputImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Statement state;
	private ResultSet rs;
	private String account;
	private String password;
	String competencelv;
	boolean ok = false;
	String staffName;
	HttpSession session;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processLogin(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("NONO");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processLogin(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("NONO");
			response.sendRedirect("/Login.jsp?p=0");
		}
		
		
			
	}

	private void processLogin(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, Exception {
		request.setCharacterEncoding("UTF-8");
		account = request.getParameter("account");
		password = request.getParameter("password");		
		session = request.getSession();
		
		checkLogin(account,password);
		
		if(ok){
			//Login true
			System.out.println(competencelv);
			session.setAttribute("account", account);
			session.setAttribute("staffName",staffName);
			competenceSession(request,competencelv);
			response.sendRedirect("/HomePage.jsp");
		}else{
			//Login false
			System.out.println("NO");
			response.setContentType("text/html;charset=UTF-8");
			response.sendRedirect("/Login.jsp?p=0");
		}
	
	}
	public void checkLogin(String account,String password) throws IllegalAccessException, ClassNotFoundException, Exception{
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "SELECT * FROM accountinfo ;";
		state = conn.createStatement();
		rs = state.executeQuery(sqlstr);
		while (rs.next()) {
			if(account.equals(rs.getString(1))){
				System.out.println("acc");
				if(password.equals(rs.getString(2))){
					System.out.println("pass");
					if(rs.getInt(9)==1){
						System.out.println("cv");
						staffName = rs.getString(3)+rs.getString(4);
						System.out.println(staffName);
						competencelv = rs.getString(8);
						ok = true;
						rs.close();
						state.close();
						conn.close();
						return ;
					}
					rs.close();
					state.close();
					conn.close();
					
				}
				rs.close();
				state.close();
				conn.close();
				
			}

		}

		rs.close();
		state.close();
		conn.close();
		
	}
	
	public void competenceSession(HttpServletRequest request,String competencelv)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		session = request.getSession(false);
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "SELECT * FROM competencelv where competenceLV= ? ";
		Competence ct = new Competence();
		PreparedStatement ps = conn.prepareStatement(sqlstr);
		ps.setString(1, competencelv);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			ct.setProductManage(rs.getInt(2));
			ct.setPurchaseManage(rs.getInt(3));
			ct.setInventoryManage(rs.getInt(4));
			ct.setInventoryInfoEdit(rs.getInt(5));
			ct.setClientManage(rs.getInt(6));
			ct.setEntireOrders(rs.getInt(7));
			ct.setOrdersInvoiceDownload(rs.getInt(8));
			ct.setPriceChange(rs.getInt(9));
			ct.setPendingOrdersEdit(rs.getInt(10));
			ct.setTotalAmountEdit(rs.getInt(11));
			ct.setOrdersManage(rs.getInt(12));
			ct.setChartView(rs.getInt(13));
			ct.setProductProfitView(rs.getInt(14));
			ct.setReportView(rs.getInt(15));
			ct.setProductCostView(rs.getInt(16));
			ct.setAccountInfoEdit(rs.getInt(17));
			ct.setEbayPaypalAccountEdit(rs.getInt(18));
			ct.setParamSettingEdit(rs.getInt(19));
			ct.setInventoryCostView(rs.getInt(20));

			
			// session.setAttribute("productManage",rs.getInt(2));
			// session.setAttribute("purchaseManage",rs.getInt(3));
			// session.setAttribute("inventoryManage",rs.getInt(4));
			// session.setAttribute("inventoryInfoEdit",rs.getInt(5));
			// session.setAttribute("clientManage",rs.getInt(6));
			// session.setAttribute("entireOrders",rs.getInt(7));
			// session.setAttribute("ordersInvoiceDownload",rs.getInt(8));
			// session.setAttribute("priceChange",rs.getInt(9));
			// session.setAttribute("pendingOrdersEdit",rs.getInt(10));
			// session.setAttribute("totalAmountEdit",rs.getInt(11));
			// session.setAttribute("ordersManage",rs.getInt(12));
			// session.setAttribute("chartView",rs.getInt(13));
			// session.setAttribute("productProfitView",rs.getInt(14));
			// session.setAttribute("reportView",rs.getInt(15));
			// session.setAttribute("productCostView",rs.getInt(16));
			// session.setAttribute("accountInfoEdit",rs.getInt(17));
			// session.setAttribute("paramSettingEdit",rs.getInt(18));
			// session.setAttribute("ebayPaypalAccountEdit",rs.getInt(19));
			// session.setAttribute("inventoryCostView",rs.getInt(20));

		}
		session.setAttribute("PageCompetence", ct);
		rs.close();
		ps.close();
		conn.close();
	}
	
	
}
