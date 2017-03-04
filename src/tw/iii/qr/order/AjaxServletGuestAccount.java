package tw.iii.qr.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.iii.qr.DataBaseConn;
import tw.iii.qr.order.DTO.GuestAccountAndOrder;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/AjaxServletGuestAccount")
public class AjaxServletGuestAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServletGuestAccount() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			processAjax(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		try {
			processAjax(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		COrderCombineFactory OCFactory = new COrderCombineFactory();
		
		LinkedList<GuestAccountAndOrder> guestAccount = new LinkedList<>();
		guestAccount = OCFactory.HasCombineOrderGuest(request);
		
		for(int i=0; i<guestAccount.size(); i++){
			System.out.println(guestAccount.get(i).getGuestAccount());
			out.println("<option>" + guestAccount.get(i).getOrder() + ":" + guestAccount.get(i).getGuestAccount() + "</option>");
		}
	}

}
