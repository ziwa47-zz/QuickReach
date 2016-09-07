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

@WebServlet("/QRAccess/CompetenceInsert.do")
public class CompetenceInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	CompetenceSql cs = new CompetenceSql();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processInsert(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processInsert(request, response);
	}

	private void processInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		CompetenceSql cs = new CompetenceSql();
		Competence ct = new Competence();

		try {
			String a = request.getParameter("1");
			ct.setCompetenceLv(a);
			if (a != null && !a.equals("")) {
				String b = request.getParameter("2");
				String c = request.getParameter("3");
				String d = request.getParameter("4");
				String e = request.getParameter("5");
				String f = request.getParameter("6");
				String g = request.getParameter("7");
				String h = request.getParameter("8");
				String i = request.getParameter("9");
				String j = request.getParameter("10");
				String k = request.getParameter("11");
				String l = request.getParameter("12");
				String m = request.getParameter("13");
				String n = request.getParameter("14");
				String o = request.getParameter("15");
				String p = request.getParameter("16");
				String q = request.getParameter("17");
				String r = request.getParameter("18");
				String s = request.getParameter("19");
				String t = request.getParameter("20");

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
				out.write("新增成功");
			} else {
				out.write("請輸入LV名稱");
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
