package tw.iii.supplyCompany;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.iii.qr.QRAccount;
import tw.iii.qr.QRAccountFactory;


@WebServlet("/SupplyCompany/SupplyCompanyServlet.do")
public class SupplyCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	SCFactory scf = new SCFactory();
	private PrintWriter out;
	
    public SupplyCompanyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String submitType = request.getParameter("submit");
			switch(submitType){
			
			case "addAccount":
				processInsert(request,response); // new a SupplyCompany name
				break;
			case "editAccount":
				processEdit(request,response); // update a SupplyCompany name
				break;
			default:
				break;			
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

private void processInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		CSupplyCompany csc = new CSupplyCompany();
		
		csc.setCompanyId(Integer.parseInt(request.getParameter("companyId")));  //1st
		csc.setCompanyName(request.getParameter("companyName"));
		csc.setTel(request.getParameter("tel"));
		csc.setFax(request.getParameter("fax"));
		csc.setAddress(request.getParameter("address"));
		csc.setComment(request.getParameter("comment"));  //6th	
		
		try {
			scf.insertSCompanyId(csc);
			response.sendRedirect("addSCName.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
private void processEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	out = response.getWriter();
	
	CSupplyCompany csc = new CSupplyCompany();
	
	csc.setCompanyId(Integer.parseInt(request.getParameter("companyId")));  //1st
	csc.setCompanyName(request.getParameter("companyName"));
	csc.setTel(request.getParameter("tel"));
	csc.setFax(request.getParameter("fax"));
	csc.setAddress(request.getParameter("address"));
	csc.setComment(request.getParameter("comment"));  //6th	
	
	try {
		scf.insertSCompanyId(csc);
		response.sendRedirect("addSCName.jsp");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

	
}








