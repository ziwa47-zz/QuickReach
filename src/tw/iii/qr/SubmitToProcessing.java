package tw.iii.qr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import tw.iii.qr.DataBaseConn;

/**
 * Servlet implementation class SubmitToProcessing
 */
@WebServlet("/SubmitToProcessing")
public class SubmitToProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement ps;

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		LinkedList<Integer> orderIdList = new LinkedList<>();
		for(String s : request.getParameterValues("orderId")){
			orderIdList.add(Integer.parseInt(s));
		}
		
		System.out.println("�����:"+orderIdList);
		//雿輻LinkedHashSet��扳�������
		Set<Integer> set = new LinkedHashSet<Integer>(orderIdList);
		System.out.println("��敺�:"+set);
		
		LinkedList<Integer> list = new LinkedList<>();
		
	Iterator it = set.iterator();
	while(it.hasNext()){
		list.add((Integer)it.next());
	}
	
	System.out.println("�����:"+list);
	
	
		
		DataBaseConn jdbc = new DataBaseConn();
		try {
			conn = jdbc.getConn();
			for(int i = 0;i<list.size();i++){
			String strsql = "UPDATE  orders_master SET orderstatus='揀貨中' WHERE order_id=?";
			ps = conn.prepareStatement(strsql); 
			
			
				ps.setInt(1, list.get(i));
				ps.executeUpdate();
			
			}
		
			response.sendRedirect("QROrders/OrderPickupPage.jsp");
			ps.close();
			conn.close();
		
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}

}
