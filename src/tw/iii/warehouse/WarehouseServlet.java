package tw.iii.warehouse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.iii.qr.DataBaseConn;

@WebServlet("/WarehouseServlet.do")
public class WarehouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String submitType = request.getParameter("submitButton");

			switch (submitType) {

			case "addWarehouse":
				processInsert(request, response); // new a SupplyCompany name
				break;
			case "editWarehouse":
				processEdit(request, response); // update a SupplyCompany name
				break;
			default:
				break;
			}
			
			response.sendRedirect("QRWarehouse/warehouseManage.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void processInsert(HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException, Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		DataBaseConn jdbc = new DataBaseConn();
		conn = jdbc.getConn();

		WarehouseFactory wf = new WarehouseFactory();
		wf.insertWarehouse(conn, request);

		conn.close();

	}

	private void processEdit(HttpServletRequest request, HttpServletResponse response)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		DataBaseConn jdbc = new DataBaseConn();
		conn = jdbc.getConn();

		WarehouseFactory wf = new WarehouseFactory();
		wf.editWarehouse(conn, request);

		conn.close();
		

	}

}
