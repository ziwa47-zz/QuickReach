package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.stock.CProduct;
import tw.iii.qr.stock.CProductFactory;
import tw.iii.qr.stock.CStock;
import tw.iii.qr.stock.CStockFactory;

@WebServlet("/ProductDo")
public class ProductDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PrintWriter out;
	private LinkedList<String> list;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processSearchProduct(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String submitType = request.getParameter("submit");
			switch(submitType){
			
			case "submitproduct":
				processSearchProduct(request, response);
				break;
			case "submitstorage":
				processSearchStorage(request, response);
				break;
			case "updateProduct":
				processUpdateProduct(request, response);
				break;
			
			default:
				break;
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	private void processUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		conn = new DataBaseConn().getConn();
		CProductFactory cpf = new CProductFactory();
		cpf.updateProduct(request,conn);
		String sku = request.getParameter("sku");
		String prod = request.getParameter("producttype");
		System.out.println(prod);
		conn.close();
		response.sendRedirect("QRProduct/ProductDetail.jsp?sku="+sku);
	}

	private void processSearchStorage(HttpServletRequest request, HttpServletResponse response)throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(); 
		conn = new DataBaseConn().getConn();
		CStockFactory csf = new CStockFactory();
		LinkedList<CStock> storage = csf.searchStorage(request,conn);
		session.setAttribute("storageall", storage);
		conn.close();
		
		
		response.sendRedirect("QRProduct/SearchStockPage.jsp");
	}

	private void processSearchProduct(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(); 
		conn = new DataBaseConn().getConn();
		CProductFactory cpf = new CProductFactory();
		LinkedList<CProduct> cp =cpf.searchProduct(request,conn);
		session.setAttribute("productall", cp);
		conn.close();
		
		
		response.sendRedirect("QRProduct/SearchProductPage.jsp");
		
	}
	

}
