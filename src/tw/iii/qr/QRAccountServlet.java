package tw.iii.qr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QREmployee/QRAccountServlet.do")
public class QRAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	QRAccountFactory qraf = new QRAccountFactory();

	private PrintWriter out;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processInsert(request,response);
	}

	private void processInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		QRAccount qra = new QRAccount();
		
		qra.setAccount(request.getParameter("account"));
		qra.setPassword(request.getParameter("password"));
		qra.setLastName(request.getParameter("lastName"));
		qra.setFirstName(request.getParameter("firstName"));
		qra.setEmail(request.getParameter("E-mail"));
		qra.setEnName(request.getParameter("enName"));
		//qra.setSignatureImage(request.getParameter("signatureImage"));
		qra.setCompetenceLV(request.getParameter("competenceLV"));
		qra.setStatus(Integer.parseInt(request.getParameter("status")));
		
		try {
			qraf.insertQRAccount(qra);
			response.sendRedirect("Account.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
private void processEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			
			QRAccount qra = new QRAccount();
			
			qra.setAccount(request.getParameter("account"));
			qra.setPassword(request.getParameter("password"));
			qra.setLastName(request.getParameter("lastName"));
			qra.setFirstName(request.getParameter("firstName"));
			qra.setEmail(request.getParameter("E-mail"));
			qra.setEnName(request.getParameter("enName"));
			//qra.setSignatureImage(request.getParameter("signatureImage"));
			qra.setCompetenceLV(request.getParameter("competenceLV"));
			qra.setStatus(Integer.parseInt(request.getParameter("status")));
			
			try {
				qraf.editQRAccount(qra);
				response.sendRedirect("Account.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	


}