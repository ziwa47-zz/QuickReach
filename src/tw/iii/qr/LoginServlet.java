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
	private String account;
	private String password;
	String competencelv;
	boolean ok = false;
	String staffName;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processLogin(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("NONO");
		}
		
		
			
	}

	private void processLogin(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, ClassNotFoundException, Exception {
		request.setCharacterEncoding("UTF-8");
		account = request.getParameter("account");
		password = request.getParameter("password");		
		HttpSession session = request.getSession();
		System.out.println("yo");
		
		checkLogin(account,password);
		
		if(ok){
			//Login true
			System.out.println(competencelv);
			session.setAttribute("account", account);
			session.setAttribute("password", password);
			session.setAttribute("competencelv",competencelv);
			session.setAttribute("staffName",staffName);
			request.getRequestDispatcher("HomePage.jsp").forward(request, response);
		}else{
			//Login false
			System.out.println("NO");
			response.setContentType("text/html;charset=UTF-8");
			response.sendRedirect("Login.jsp");
		}
	
	}
	public void checkLogin(String account,String password) throws IllegalAccessException, ClassNotFoundException, Exception{
		Connection conn = new DataBaseConn().getConn();
		String sqlstr = "SELECT * FROM accountinfo ;";
		state = conn.createStatement();
		rs = state.executeQuery(sqlstr);
		while (rs.next()) {
			if(account.equals(rs.getString(1))){
				System.out.println("acc");
				if(password.equals(rs.getString(2))){
					System.out.println("pass");
					if(rs.getInt(9)==1){
						System.out.println("cv");
						staffName = rs.getString(3)+rs.getString(4);
						System.out.println(staffName);
						competencelv = rs.getString(8);
						ok = true;
						rs.close();
						state.close();
						conn.close();
						return ;
					}
					rs.close();
					state.close();
					conn.close();
					
				}
				rs.close();
				state.close();
				conn.close();
				
			}

		}

		rs.close();
		state.close();
		conn.close();
		
	}
}
