package tw.iii.Bundle;

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

@WebServlet("/QRProduct/totalBundles.do")
public class totalBundlesServlet2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	BundlesFactory bdf = new BundlesFactory();
	LinkedList<String[]> getBundlesDetail ;
	HttpSession session;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			processShowBundlesDetail(request,response);
		
	}

	private void processShowBundlesDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		String sku = request.getParameter("smt");
		String bdsku = request.getParameter(sku+"sku");
		String bdName = request.getParameter(sku+"name");
		String bdComment = request.getParameter(sku+"comment");

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
	


}
