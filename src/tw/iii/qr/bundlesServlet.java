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

@WebServlet("/QRProduct/bundles.do")
public class bundlesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	BundlesFactory bdf = new BundlesFactory();
	LinkedList<String[]> getBundlesDetail ;
	HttpSession session;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		String submit = request.getParameter("smt");
		
		if(submit.equals("update")){
			processBundlesDelete(request,response);
			processBundlesInsert(request,response);
		}else{
			processDetailAdd(request,response);
		}
		
		
		
	}

	private void processShowBundlesDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		String sku = request.getParameter("smt");
		String bdsku = request.getParameter(sku+"sku");
		String bdName = request.getParameter(sku+"name");
		String bdComment = request.getParameter(sku+"comment");
//		System.out.print(bdsku);
//		System.out.print(bdName);
//		System.out.print(bdComment);
//		if(session.getAttribute("getBundlesDetail") == null){
//			System.out.println("NO");
//		}else{
//			System.out.println("yes");
//			
//			
//		}
		try {
			
			bdf.showBundlesDetail(bdsku);
			//getBundlesDetail = bdf.bundlesList;
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("bdsku", bdsku );
		session.setAttribute("bdName", bdName );
		session.setAttribute("bdComment", bdComment );
		getBundlesDetail = bdf.bundlesList ;
		session.setAttribute("getBundlesDetail", getBundlesDetail );
		response.sendRedirect("BundlesDetail.jsp");
		
	}
	
	private void processDetailAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//新增子商品
		request.setCharacterEncoding("UTF-8");
		String submit = request.getParameter("smt");
		String dSKU = request.getParameter("productSKU");
		String dPName = request.getParameter("P_name");
		String dQty = request.getParameter("qty");
		
		bdf.bundlesList = (LinkedList<String[]>)session.getAttribute("getBundlesDetail");
		
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
		session.setAttribute("getBundlesDetail", bdf.bundlesList );
		response.sendRedirect("BundlesDetail.jsp");
		
		
		
	}	

	private void processBundlesInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//新增複合商品
		request.setCharacterEncoding("UTF-8");
		
		String bdsku = request.getParameter("bdsku");
		String bdname = request.getParameter("bdname");
		String ps = request.getParameter("comment");
	
		bdf.bundlesList = (LinkedList<String[]>)session.getAttribute("getBundlesDetail");
		
		try {
			
			bdf.bundlesToProduct(bdsku,bdname,ps);
			bdf.bundlesToDetail(bdsku);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	private void processBundlesDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String bdsku = request.getParameter("bdsku");
		try {
			bdf.bundlesDeleteFormProduct(bdsku);
			bdf.bundlesDeleteFormBundles(bdsku);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}

}
