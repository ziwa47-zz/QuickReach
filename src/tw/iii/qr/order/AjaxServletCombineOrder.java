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
import tw.iii.qr.order.DTO.COrderCombineDetail;
import tw.iii.qr.order.DTO.COrderCombineFactory;
import tw.iii.qr.order.DTO.COrderFactory;
import tw.iii.qr.order.DTO.GuestAccountAndOrder;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/AjaxServletCombineOrder")
public class AjaxServletCombineOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServletCombineOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			processAjax(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			processAjax(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(request.getParameter("CQRID"));
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Connection conn = new DataBaseConn().getConn();
		COrderCombineFactory OCFactory = new COrderCombineFactory();
		
		LinkedList<COrderCombineDetail> combineDetail = new LinkedList<>();
		combineDetail = OCFactory.ReadCombineOrder(request, conn);
		LinkedList<String> pic = new LinkedList<>();

		for(int i=0; i<combineDetail.size(); i++){
			//System.out.println(combineDetail.get(i).getGuestAccount());
			out.println("<tr class='success'>");
			out.println("<td>" + combineDetail.get(i).getM_cqrid() + "</td>");
			out.println("<td>" + combineDetail.get(i).getD_qrid() + "</td>");
			out.println("<td>" + combineDetail.get(i).getD_ebayno() + "</td>");
			out.println("<td>" + combineDetail.get(i).getCombineDate() + "</td>");
			String qrid = combineDetail.get(i).getD_qrid();
			pic = OCFactory.GetPic(qrid, conn);
			for(int j=0; j<pic.size(); j++){
				out.println("<td><a href='#' class='pop'><img src='/pics/"+ pic.get(j) +"'  style='width: 100px; height: 100px;'></a></td>");
			}
			//class='img-responsive'z
			out.println("</tr>");
		}
	}

}
