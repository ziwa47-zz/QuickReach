package tw.iii.qr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginVerifyJavaBean {
	
	HttpSession session ;
	
	public void LoginVerify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		session = request.getSession(false) ;

		if(session.getAttribute("account") == null){
			
			response.sendRedirect("../Login.jsp");
		}
		
	}
	
	public boolean competenceCheck(String competenceLV,String competence) throws IllegalAccessException, ClassNotFoundException, Exception{
		
		
		Connection conn = new DataBaseConn().getConn() ;
		Statement state = conn.createStatement();
		String sqlstr = "SELECT "+competence+" FROM competencelv where competenceLV='"+competenceLV+"'";
		
		ResultSet rs = state.executeQuery(sqlstr);
		boolean b = false;
		while(rs.next()){
			
			if(rs.getInt(1)==1){
				b=true;
			}else{
				b=false;
			}
		}
		return b;
		
	}
	
	public void competenceSession(HttpServletRequest request) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception{
		
		session = request.getSession(false) ;
		Connection conn = new DataBaseConn().getConn() ;
		Statement state = conn.createStatement();
		
		String competenceLV =(String)session.getAttribute("competenceLV");
		
		String sqlstr = "SELECT * FROM competencelv where competenceLV='"+competenceLV+"'";
	
		ResultSet rs = state.executeQuery(sqlstr);
		
		while(rs.next()){
			
			session.setAttribute("productManage",rs.getInt(2));
			session.setAttribute("purchaseManage",rs.getInt(3));
			session.setAttribute("inventoryManage",rs.getInt(4));
			session.setAttribute("inventoryInfoEdit",rs.getInt(5));
			session.setAttribute("clientManage",rs.getInt(6));
			session.setAttribute("entireOrders",rs.getInt(7));
			session.setAttribute("ordersInvoiceDownload",rs.getInt(8));
			session.setAttribute("priceChange",rs.getInt(9));
			session.setAttribute("pendingOrdersEdit",rs.getInt(10));
			session.setAttribute("totalAmountEdit",rs.getInt(11));
			session.setAttribute("ordersManage",rs.getInt(12));
			session.setAttribute("chartView",rs.getInt(13));
			session.setAttribute("productProfitView",rs.getInt(14));
			session.setAttribute("reportView",rs.getInt(15));
			session.setAttribute("productCostView",rs.getInt(16));
			session.setAttribute("accountInfoEdit",rs.getInt(17));
			session.setAttribute("paramSettingEdit",rs.getInt(18));
			session.setAttribute("ebayPaypalAccountEdit",rs.getInt(19));
			session.setAttribute("inventoryCostView",rs.getInt(20));
			
		}
		
		conn.close();
		state.close();
		rs.close();
		
	}
	
	
}
