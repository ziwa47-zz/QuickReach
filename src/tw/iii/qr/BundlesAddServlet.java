package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.stock.BundlesFactory;

@WebServlet("/QRProduct/bundlesAdd.do")
public class BundlesAddServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	BundlesFactory bdf = new BundlesFactory();
	LinkedList<String[]> getBundlesDetail ;
	HttpSession session;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		String submit = request.getParameter("smt");
		
		if("skuCheck".equals(submit)){
			processSKUCheck(request,response);
		}else if("insert".equals(submit)){
			processBundlesInsert(request,response);
		}else{
			processDetailAdd(request,response);
		}
		
		
		
	}

	
	private void processSKUCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		String sku = request.getParameter("bdsku");
		
		try {
			if(bdf.SKUCheck(sku)){
				session.setAttribute("SKURepeat", 1);
				response.sendRedirect("BundlesAdd.jsp");
			}else{
				session.setAttribute("SKURepeat", 0);
				response.sendRedirect("BundlesAdd.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void processDetailAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//新增子商品
		request.setCharacterEncoding("UTF-8");
		String submit = request.getParameter("smt");
		String bdsku = request.getParameter("bdsku");
		String bdName = request.getParameter("bdname");
		String bdComment = request.getParameter("comment");
		String dSKU = request.getParameter("productSKU");
		String dPName = request.getParameter("P_name");
		String dQty = request.getParameter("qty");
		
//		if(session.getAttribute("getBundlesDetail") != null){
//			bdf.bundlesList = (LinkedList<String[]>)session.getAttribute("getBundlesAddDetail");
//			
//		}
//		if(session.getAttribute("getBundlesDetail") == null){
//			System.out.println("NO");
//		}else{
//			getBundlesDetail = (LinkedList<String[]>)session.getAttribute("getBundlesDetail");
//			
//			for(String[] x:getBundlesDetail){
//				System.out.println(x[1]);
//			}
//			
//		}
		
		bdf.setBundles(dSKU,dPName,dQty);
		bdf.processBundles(submit);
		
		
		session.setAttribute("getBundlesAddDetail", bdf.bundlesList);
		session.setAttribute("bdSKU", bdsku );
		session.setAttribute("bdname", bdName );
		session.setAttribute("bdcomment", bdComment );		
		response.sendRedirect("BundlesAdd.jsp");
		
	}	

	private void processBundlesInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//新增複合商品
		request.setCharacterEncoding("UTF-8");
		
		String bdsku = request.getParameter("bdsku");
		String bdname = request.getParameter("bdname");
		String ps = request.getParameter("comment");
	
		bdf.bundlesList = (LinkedList<String[]>)session.getAttribute("getBundlesAddDetail");
		
		try {
			
			bdf.bundlesToProduct(bdsku,bdname,ps);
			bdf.bundlesToDetail(bdsku);
			session.removeAttribute("getBundlesAddDetail");
			session.removeAttribute("bdSKU");
			session.removeAttribute("bdname");
			session.removeAttribute("bdcomment");
			session.setAttribute("insertSuccess", 1);
			response.sendRedirect("BundlesAdd.jsp");
			
		} catch (Exception e) {
			session.setAttribute("SKURepeat", 1);
			response.sendRedirect("BundlesAdd.jsp");
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
