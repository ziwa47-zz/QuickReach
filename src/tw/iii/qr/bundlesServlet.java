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
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//processBundlesAdd(request,response);
		processShowBundlesDetail(request,response);
	}

	private void processShowBundlesDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String bdsku = request.getParameter("detailSKU");
		LinkedList<String[]> getBundlesDetail = new LinkedList<String[]>();
		
		try {
			getBundlesDetail = bdf.showBundlesDetail(bdsku);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("bdsku", bdsku );
		session.setAttribute("getBundlesDetail", getBundlesDetail );
		response.sendRedirect("BundlesDetail.jsp");
	}
	
	private void processDetailAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//新增子商品
		request.setCharacterEncoding("UTF-8");
	
		String dSKU = request.getParameter("productSKU");
		String dPName = request.getParameter("P_name");
		String dQty = request.getParameter("qty");
		
		bdf.setBundles(dSKU,dPName,dQty);
		
	}	

	private void processBundlesAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//新增複合商品
		request.setCharacterEncoding("UTF-8");
		
		String bdsku = request.getParameter("bdsku");
		String bdname = request.getParameter("bdname");
		String ps = request.getParameter("comment");
	
		
		
		try {
			
			bdf.bundlesToProduct(bdsku,bdname,ps);
			bdf.bundlesToDetail(bdsku);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	private void processBundlesUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//更新複合商品
		
		
	
	}

}
