package tw.iii.qr.order.DTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

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
		HashMap<String, String> hm = new HashMap<String, String>();		
		Connection conn = new DataBaseConn().getConn();
		String strsql ="select distinct guestAccount from  comebineorder where DATEDIFF(Day,combineDate,GETDATE())<14";
		ps = conn.prepareStatement(strsql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			hm.put("guestAccount", rs.getString(1));
		}
		JSONObject responseJSONObject = new JSONObject(hm);
		PrintWriter out = response.getWriter();
		out.println(responseJSONObject);
		out.close();
		rs.close();
		ps.close();
		conn.close();
	}

}
