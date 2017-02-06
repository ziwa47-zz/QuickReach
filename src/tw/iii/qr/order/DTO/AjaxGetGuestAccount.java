package tw.iii.qr.order.DTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.iii.qr.DataBaseConn;

/**
 * Servlet implementation class AjaxGetGuestAccount
 */
@WebServlet("/AjaxGetGuestAccount")
public class AjaxGetGuestAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxGetGuestAccount() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAjaxGuestAccount(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processAjaxGuestAccount(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void processAjaxGuestAccount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PreparedStatement ps = null;
		String Combineorder = request.getParameter("select");
		System.out.println(Combineorder);
		HashMap<String, String> hm = new HashMap<String, String>();
		LinkedList<HashMap<String, String>> list= new LinkedList<>();
		Connection conn = new DataBaseConn().getConn();
		String strsql ="select m.ebayNO,m.payDate,p.picturePath from orders_master m left join orders_detail d on m.QR_id=d.QR_id left join product p on p.SKU = d.SKU where  m.CombineSku = ?";
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			hm = new HashMap<>();
			hm.put("ebayNO", rs.getString(1));
			hm.put("payDate", rs.getString(2));
			hm.put("picturePath", rs.getString(3));
			list.add(hm);
		}
		JSONObject responseJSONObject = new JSONObject(list);
		PrintWriter out = response.getWriter();
		out.println(responseJSONObject);
		out.close();
		rs.close();
		ps.close();
		conn.close();
	}

}
