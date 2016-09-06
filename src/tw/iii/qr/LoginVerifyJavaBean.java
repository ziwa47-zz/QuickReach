package tw.iii.qr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginVerifyJavaBean {
	
	HttpSession session ;
	
	public void LoginVerify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		session = request.getSession(false) ;

		if(session.getAttribute("account") == null){
			
			response.sendRedirect("../Login.jsp");
		}
		
	}
	
	public boolean competenceCheck(String competenceLV,String competence) throws IllegalAccessException, ClassNotFoundException, Exception{
		
		
		Connection conn = new DataBaseConn().getConn() ;
		Statement state = conn.createStatement();
		String sqlstr = "SELECT "+competence+" FROM competencelv where competenceLV='"+competenceLV+"'";
		
		ResultSet rs = state.executeQuery(sqlstr);
		boolean b = false;
		while(rs.next()){
			
			if(rs.getInt(1)==1){
				b=true;
			}else{
				b=false;
			}
		}
		return b;
		
	}
	
	
}
