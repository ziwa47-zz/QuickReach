package tw.iii.purchase;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.mysql.jdbc.ReplicationMySQLConnection;

import tw.iii.qr.DataBaseConn;


@WebServlet("/TestJdbcMvcServlet")
public class TestJdbcMvcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	
	LinkedList<String> values = new LinkedList<String>();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//processJdbcAction(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//processJdbcAction(request,response);
		try {
			processInsert(request,response);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	private void processInsert(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		DataBaseConn jdbc = new DataBaseConn();
		try {
			conn = jdbc.getConn();			
			purchaseFactory pcf = new purchaseFactory();
			PreparedStatement preparedState = null;
			
			
			String warehouse = request.getParameter("warehouse");
			
			String sqlCount = "select count(*) from quickreach.purchaselog_master where date >= curdate() and warehouse = ?";
			
			
			preparedState = conn.prepareStatement(sqlCount);
			preparedState.setString(1, warehouse);
			// stmt = conn.createStatement();

			int count = 0;
			ResultSet rs = preparedState.executeQuery();
			if(rs.next()&& Integer.valueOf(rs.getInt(1)) == 0){
				count = 1;
				System.out.println("true:"+count);
			}else{				
				count = Integer.valueOf(rs.getInt(1)+1); 
				System.out.println("false:"+count);
			}

			DecimalFormat df = new DecimalFormat("000");
			
			
			System.out.println("針對倉庫之count(*):"+df.format(count));
			
			
			String oldPurchaseIdFront11 = request.getParameter("purchaseId").substring(0,10);
			System.out.println("原16碼之前11碼(流水號):"+oldPurchaseIdFront11);
			
			System.out.println("finally:\n"+oldPurchaseIdFront11+warehouse+count);
		//purchaseLog_Master
			LinkedList<String> pMaster = pcf.purchaseMaster(request);
			
		
			String sqlstr1 = "Insert Into purchaselog_master(purchaseId,date,companyId,companyName,staffId,warehouse,comment,stockStatus) Values(?,now(),?,(select C_name from quickreach.company where C_id=?),?,?,?,1)";
			preparedState = conn.prepareStatement(sqlstr1);
			
			preparedState.setString(1, oldPurchaseIdFront11+warehouse+df.format(count));
			preparedState.setInt(2,Integer.parseInt(pMaster.get(0))); //companyId
			preparedState.setInt(3,Integer.parseInt(pMaster.get(0))); //select C_name from quickreach.company where C_id=?
			preparedState.setString(4,pMaster.get(1));                //staffId
			preparedState.setString(5,pMaster.get(2));     //warehouse
			preparedState.setString(6,pMaster.get(3));   //purchaseMasterComment
			
			preparedState.executeUpdate();
			
			
			
			LinkedList<LinkedList<String>> Alllist = pcf.checkvalue(request);
		//purchaseLog_Detail
			for(int i = 0 ; i <Alllist.size() ; i++){
				String sqlstr2 = "Insert Into purchaselog_detail(purchaseId,SKU,P_name,specification,color,qty,price,warehousePosition,comment,stockStatus,warehouse)"
						+"Values(?,?,?,?,?,?,?,?,?,1,?)";
				
								
				preparedState = conn.prepareStatement(sqlstr2);
				
				System.out.println(i+":\n");
				
				
				
				preparedState.setString(1, oldPurchaseIdFront11+warehouse+df.format(count));  //時代的眼淚WTF
	
				for(int j = 0 ; j < Alllist.get(i).size() ; j++){
					preparedState.setString(j+2, Alllist.get(i).get(j));					
					System.out.print(Alllist.get(i).get(j)+",");
				}
				//preparedState.setString(Alllist.get(i).size(),pMaster.get(2) );
				  preparedState.executeUpdate();
				  
				  
				
				String sqlstr3 = "Update quickreach.storage set qty=qty+? where SKU=?";
				preparedState = conn.prepareStatement(sqlstr3);
				preparedState.setInt(1, Integer.parseInt(Alllist.get(i).get(4).trim()));
				preparedState.setString(2, Alllist.get(i).get(0));			
			
				 preparedState.executeUpdate();
				
			}
			
			
			
			
			
			preparedState.close();
			
			conn.close();
			
			response.sendRedirect("QRProduct/PurchasePage.jsp");
		}  catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
	

}
