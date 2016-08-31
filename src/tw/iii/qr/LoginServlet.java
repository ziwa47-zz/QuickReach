package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SQLInputImpl;

@WebServlet("/LoginController.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Statement state;
	private ResultSet rs;
	private String empName;
	private String empPwd;
	private String empStatus;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		processLogin(request,response);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processLogin(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processLogin(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, Exception {
		request.setCharacterEncoding("UTF-8");
		empName = request.getParameter("EmpName");
		empPwd = request.getParameter("EmpPwd");
		empStatus = null;
		HttpSession session = request.getSession();
		
		if(checkLogin(empName,empPwd)){
			//Login true
			
			
			session.setAttribute("EmpName", empName);
			session.setAttribute("EmpStatus", empStatus);
			request.getRequestDispatcher("../QRMain/HomePage.jsp").forward(request, response);
		}else{
			//Login false
			response.setContentType("text/html;charset=UTF-8");
			response.sendRedirect("../QRMain/Login.jsp");
		}
		
		
		
		
		
	}
	public boolean checkLogin(String EmpName,String EmpPwd) throws IllegalAccessException, ClassNotFoundException, Exception{
		Connection conn = new DataBaseConn().getConn();
		String sqlstr3 = "SELECT * FROM QuickReach.Staff;";
		state = conn.createStatement();
		rs = state.executeQuery(sqlstr3);
		while (rs.next()) {
			if(EmpName.equals(rs.getString(2))){
				if(EmpPwd.equals(rs.getString(3))){
					//Login
					empStatus=rs.getString(6);
					rs.close();
					state.close();
					conn.close();
					return true;
				}
				rs.close();
				state.close();
				conn.close();
				return false;
			}

		}

		rs.close();
		state.close();
		conn.close();
		return false;
	}
}
