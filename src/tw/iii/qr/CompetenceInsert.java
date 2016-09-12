package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/QRAccess/CompetenceInsert.do")
public class CompetenceInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	CompetenceSql cs = new CompetenceSql();
	HttpSession session;
	String a = "";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processInsert(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String submit = request.getParameter("smt");
		
		a = request.getParameter("CompetenceLv");
		if(submit.equals("insert")){
			processInsert(request, response);
			//response.sendRedirect("CompetenceInsert.jsp");
		}if(submit.equals("delete")){
			processDelete(request, response);
			//response.sendRedirect("Competence.jsp");
		}else{			
			processDelete(request, response);
			processInsert(request, response);
			//response.sendRedirect("Competence.jsp");
		}
	}

	private void processDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		CompetenceSql cs = new CompetenceSql();
				
		try {
			cs.competenceDelete(a);
			response.sendRedirect("Competence.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		session = request.getSession();
		out = response.getWriter();
		CompetenceSql cs = new CompetenceSql();
		Competence ct = new Competence();

		try {
//			String a = request.getParameter("CompetenceLv");
			ct.setCompetenceLv(a);
			if (a != null && !a.equals("")) {
				String b = request.getParameter("ProductManage");
				String c = request.getParameter("PurchaseManage");
				String d = request.getParameter("InventoryManage");
				String e = request.getParameter("InventoryInfoEdit");
				String f = request.getParameter("ClientManage");
				String g = request.getParameter("EntireOrders");
				String h = request.getParameter("OrdersInvoiceDownload");
				String i = request.getParameter("PriceChange");
				String j = request.getParameter("PendingOrdersEdit");
				String k = request.getParameter("TotalAmountEdit");
				String l = request.getParameter("OrdersManage");
				String m = request.getParameter("ChartView");
				String n = request.getParameter("ProductProfitView");
				String o = request.getParameter("ReportView");
				String p = request.getParameter("ProductCostView");
				String q = request.getParameter("AccountInfoEdit");
				String r = request.getParameter("EbayPaypalAccountEdit");
				String s = request.getParameter("ParamSettingEdit");
				String t = request.getParameter("InventoryCostView");

				if (b != null && b.equals("on")) {
					ct.setProductManage(1);
				}
				if (c != null && c.equals("on")) {
					ct.setPurchaseManage(1);
				}
				if (d != null && d.equals("on")) {
					ct.setInventoryManage(1);
				}
				if (e != null && e.equals("on")) {
					ct.setInventoryInfoEdit(1);
				}
				if (f != null && f.equals("on")) {
					ct.setClientManage(1);
				}
				if (g != null && g.equals("on")) {
					ct.setEntireOrders(1);
				}
				if (h != null && h.equals("on")) {
					ct.setOrdersInvoiceDownload(1);
				}
				if (i != null && i.equals("on")) {
					ct.setPriceChange(1);
				}
				if (j != null && j.equals("on")) {
					ct.setPendingOrdersEdit(1);
				}
				if (k != null && k.equals("on")) {
					ct.setTotalAmountEdit(1);
				}
				if (l != null && l.equals("on")) {
					ct.setOrdersManage(1);
				}
				if (m != null && m.equals("on")) {
					ct.setChartView(1);
				}
				if (n != null && n.equals("on")) {
					ct.setProductProfitView(1);
				}
				if (o != null && o.equals("on")) {
					ct.setReportView(1);
				}
				if (p != null && p.equals("on")) {
					ct.setProductCostView(1);
				}
				if (q != null && q.equals("on")) {
					ct.setAccountInfoEdit(1);
				}
				if (r != null && r.equals("on")) {
					ct.setEbayPaypalAccountEdit(1);
				}
				if (s != null && s.equals("on")) {
					ct.setParamSettingEdit(1);
				}
				if (t != null && t.equals("on")) {
					ct.setInventoryCostView(1);
				}

				cs.insetCompetence(ct);
				//out.write("新增成功");
				response.sendRedirect("Competence.jsp");
				//response.sendRedirect("CompetenceInsert.jsp");
				
			} else {
				out.write(a);
				System.out.println("a"+a);
				//out.write("請輸入LV名稱");
			}
			// LinkedList<Competence> llct = cs.getCompetenceLv();
			//
			// for(Competence x:llct){
			// //if(x.CompetenceLv!=null){
			// System.out.println(x.CompetenceLv);
			// //out.write(x.CompetenceLv+"<br/>");
			// //}
			// }

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// private void getValues(HttpServletRequest request, HttpServletResponse
	// response) throws UnsupportedEncodingException {
	//
	// request.setCharacterEncoding("UTF-8");
	//
	// String a = request.getParameter("1");
	// cs.setCompetenceLv(a);
	// }

}
