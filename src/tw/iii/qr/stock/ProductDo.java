 package tw.iii.qr.stock;

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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.stock.DTO.CProduct;
import tw.iii.qr.stock.DTO.CStock;

@WebServlet("/ProductDo")
@MultipartConfig
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
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
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
			case "newProduct":
				processnewProduct(request,response);
				break;
			case "getProcuct": //jenan's function
				processSearchProductToAddOrderDetail(request, response);
				break;
			case "getProcuctNew":
				processSearchProductToAddOrderDetailNew(request, response);
				break;
			case "submitcounting":
				processcountingsearch(request,response);
				break;
			case "counting":
				  String[] path = new CDBtoExcel().counting(request,response);
				  session.setAttribute("pathcounting", path);
				  System.out.println(7);
				  response.sendRedirect("/href/toExcel.jsp?excel=counting");
				  System.out.println(8);
				  
				 

				out.write("<script type='text/javascript'>");
				out.write("alert('列印成功');");
				out.write("window.location = 'QRProduct/SearchStockPage.jsp';");
				out.write("</script>");
				// session.removeAttribute("excelpath");
				break;
			case "modifyStorage":
				processModifyStorage(request,response);
				break;			
			default:
				break;
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	private void processModifyStorage(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		out = response.getWriter();
		HttpSession session = request.getSession(); 
		
		String sku = request.getParameter("txtsku");
		String stockID = request.getParameter("txtstockID");
		String modifyType = request.getParameter("ddlModifyType");
		String count = request.getParameter("txtCount");
		String wareHouse = request.getParameter("wareHouse");
		String comment = request.getParameter("txtComment");
		String staffName= session.getAttribute("staffName").toString();
		
		int modifyCount = 0;
		
		if("increase".equals(modifyType))
		{	
			modifyCount=Integer.valueOf(count);
			comment="增加;"+comment;
		}
		else if("decrease".equals(modifyType))
		{
			modifyCount=(-1)*Integer.valueOf(count);
			comment="減少;"+comment;
		}
		
		conn = new DataBaseConn().getConn();
		
		CStockFactory csf = new CStockFactory();
		
		csf.modifyStorage(modifyCount, comment, sku, wareHouse);
		csf.modifyStorageLogMaster(stockID, comment, staffName, wareHouse);
		csf.modifyStorageLogDetail(stockID, comment, sku, wareHouse, modifyCount);
		
		conn.close();
		
		out.write("<script type='text/javascript'>");
		out.write("alert('修改成功');");
		out.write("window.location = 'QRProduct/StockDetail.jsp?sku="+sku+"';");
		out.write("</script>");
		
	}


	private void processcountingsearch(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		conn = new DataBaseConn().getConn();
		HttpSession session = request.getSession(); 
		CStockFactory csf = new CStockFactory();
		LinkedList<CStock> counting = csf.searchforcounting(request,conn);
		session.setAttribute("countingall", counting);
		
		conn.close();
		response.sendRedirect("/QRProduct/StockCounting.jsp");
		
	}


	private void processnewProduct(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		conn = new DataBaseConn().getConn();
		CProductFactory cpf = new CProductFactory();
		System.out.println(request.getParameter("SKU"));
		cpf.InsertNewProduct(request, conn);
		
		conn.close();
		response.sendRedirect("/QRProduct/NewProduct.jsp");
	}

	private void processUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		conn = new DataBaseConn().getConn();
		CProductFactory cpf = new CProductFactory();
		cpf.updateProduct(request,conn);
		String sku = request.getParameter("sku");
//		String prod = request.getParameter("producttype");
//		System.out.println(prod);
		conn.close();
		response.sendRedirect("/QRProduct/ProductDetail.jsp?sku="+sku);
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
		
		
		response.sendRedirect("/QRProduct/SearchStockPage.jsp");
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
		
		response.sendRedirect("/QRProduct/SearchProductPage.jsp");
		
	}
	
	private void processSearchProductToAddOrderDetail(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(); 
		conn = new DataBaseConn().getConn();
		CProductFactory cpf = new CProductFactory();
		LinkedList<CProduct> cp =cpf.searchProduct(request,conn);
		session.setAttribute("productall", cp);
		conn.close();
		
		response.sendRedirect("/QROrders/selectProduct.jsp?QR_id=" + request.getParameter("QR_id"));
	}

	private void processSearchProductToAddOrderDetailNew(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(); 
		conn = new DataBaseConn().getConn();
		CProductFactory cpf = new CProductFactory();
		LinkedList<CProduct> cp =cpf.searchProduct(request,conn);
		session.setAttribute("productall", cp);
		conn.close();
		
		response.sendRedirect("/QROrders/selectProductNew.jsp?QR_id=" + request.getParameter("QR_id"));
	}
	
}
