<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="tw.iii.qr.stock.CDBtoExcel,java.io.*, java.net.URLEncoder ,java.util.*"%>

<jsp:useBean id="toexcel" class="tw.iii.qr.stock.CDBtoExcel"
	scope="page" />

<%
	if ("1".equals(request.getParameter("excel"))) {

		String[] path = toexcel.物流匯出格式();
		String pa = path[0];
		String filename = path[1];

		filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");

		File file = new File(pa + filename);
		if (file.exists()) {//檢驗檔案是否存在
			try {
				out.println("exist");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + URLEncoder.encode(filename, "UTF-8") + "\"");
				OutputStream output = response.getOutputStream();
				InputStream in = new FileInputStream(file);
				byte[] b = new byte[2048];
				int len;

				while ((len = in.read(b)) > 0) {
					output.write(b, 0, len);
				}
				in.close();
				output.flush();
				output.close(); //關閉串流
				out.clear();
				out = pageContext.pushBody();
			} catch (Exception ex) {
				out.println("Exception : " + ex.toString());
				out.println("<br/>");
			}
		} else {
			out.println(pa + filename + " : 此檔案不存在");
			out.println("<br/>");
		}
	}
	if ("2".equals(request.getParameter("excel"))) {
		String path[] = toexcel.日結表();

		String pa = path[0];
		String filename = path[1];

		filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");

		File file = new File(pa + filename);
		if (file.exists()) {//檢驗檔案是否存在
			try {
				out.println("exist");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + URLEncoder.encode(filename, "UTF-8") + "\"");
				OutputStream output = response.getOutputStream();
				InputStream in = new FileInputStream(file);
				byte[] b = new byte[2048];
				int len;

				while ((len = in.read(b)) > 0) {
					output.write(b, 0, len);
				}
				in.close();
				output.flush();
				output.close(); //關閉串流
				out.clear();
				out = pageContext.pushBody();
			} catch (Exception ex) {
				out.println("Exception : " + ex.toString());
				out.println("<br/>");
			}
		} else {
			out.println(pa + filename + " : 此檔案不存在");
			out.println("<br/>");
		}
	}
	if ("3".equals(request.getParameter("excel"))) {
		//日出貨報表
		String[] path = toexcel.dailyBalanceSheetExcel();

		String pa = path[0];
		String filename = path[1];

		filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");

		File file = new File(pa + filename);
		if (file.exists()) {//檢驗檔案是否存在
			try {
				out.println("exist");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + URLEncoder.encode(filename, "UTF-8") + "\"");
				OutputStream output = response.getOutputStream();
				InputStream in = new FileInputStream(file);
				byte[] b = new byte[2048];
				int len;

				while ((len = in.read(b)) > 0) {
					output.write(b, 0, len);
				}
				in.close();
				output.flush();
				output.close(); //關閉串流
				out.clear();
				out = pageContext.pushBody();
			} catch (Exception ex) {
				out.println("Exception : " + ex.toString());
				out.println("<br/>");
			}
		} else {
			out.println(pa + filename + " : 此檔案不存在");
			out.println("<br/>");
		}
	}
	if ("4".equals(request.getParameter("excel"))) {
		
		LinkedList<String[]> path = (LinkedList<String[]>) session.getAttribute("excelpath");
		for (int i = 0; i < path.size(); i++) {
			String[] mypath = path.get(i);
			System.out.println(i);
			String pa = mypath[0];
			String filename = mypath[1];

			filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");

			File file = new File(pa + filename);
			System.out.println("file");
			if (file.exists()) {//檢驗檔案是否存在
				try {
					out.println("exist");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + URLEncoder.encode(filename, "UTF-8") + "\"");
					OutputStream output = response.getOutputStream();
					InputStream in = new FileInputStream(file);
					byte[] b = new byte[2048];
					int len;

					while ((len = in.read(b)) > 0) {
						output.write(b, 0, len);
					}
					in.close();
					output.flush();
					output.close(); //關閉串流
					out.clear();
					out = pageContext.pushBody();
					System.out.println("done");
				} catch (Exception ex) {
					out.println("Exception : " + ex.toString());
					out.println("<br/>");
				}
			} else {
				out.println(pa + filename + " : 此檔案不存在");
				out.println("<br/>");
			}
		}
	}
%>