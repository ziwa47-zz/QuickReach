<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="tw.iii.qr.stock.CDBtoExcel,java.io.*, java.net.URLEncoder ,java.util.*"%>

<jsp:useBean id="toexcel" class="tw.iii.qr.stock.CDBtoExcel"
	scope="page" />

<%
	if ("1".equals(request.getParameter("excel"))) {
		try {
			String[] path = toexcel.物流匯出格式();
			String pa = path[0];
			String filename = path[1];

			filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");

			File file = new File(pa + filename);
			if (file.exists()) {//檢驗檔案是否存在

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

			} else {
				out.println(pa + filename + " : 此檔案不存在");
				out.println("<br/>");
				out.println("請先選擇想要列印的訂單，再按下列印物流匯出格式按鈕");
				out.println("<br/>");
				out.println("3秒後跳轉回揀貨中頁面");
				out.println("<br/>");
				response.setHeader("Refresh","3; /QROrders/OrderPickupPage.jsp?begin=0&end=10");
			}
		} catch (Exception ex) {
			out.println("請先選擇想要列印的訂單，再按下列印物流匯出格式按鈕");
			out.println("<br/>");
			out.println("3秒後跳轉回揀貨中頁面");
			out.println("<br/>");
			response.setHeader("Refresh","3; /QROrders/OrderPickupPage.jsp?begin=0&end=10");
		}
	}
	if ("2".equals(request.getParameter("excel"))) {
		try {
			String path[] = toexcel.日結表();

			String pa = path[0];
			String filename = path[1];

			filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");

			File file = new File(pa + filename);
			if (file.exists()) {//檢驗檔案是否存在

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

			} else {
				out.println(pa + filename + " : 此檔案不存在");
				out.println("<br/>");
				out.println("3秒後跳轉回已完成頁面");
				out.println("<br/>");
				response.setHeader("Refresh","3; /QROrders/OrderFinished.jsp?begin=0&end=10");
			}
		} catch (Exception ex) {
			out.println("3秒後跳轉回已完成頁面");
			out.println("<br/>");
			response.setHeader("Refresh","3; /QROrders/OrderFinished.jsp?begin=0&end=10");
		}
	}
	if ("3".equals(request.getParameter("excel"))) {
		try {
			//日出貨報表
			String[] path = toexcel.dailyBalanceSheetExcel();

			String pa = path[0];
			String filename = path[1];
			filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");
			File file = new File(pa + filename);
			if (file.exists()) {//檢驗檔案是否存在
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

			} else {
				out.println(pa + filename + " : 此檔案不存在");
				out.println("<br/>");
				out.println("3秒後跳轉回已完成頁面");
				out.println("<br/>");
				response.setHeader("Refresh","3; /QROrders/OrderFinished.jsp?begin=0&end=10");
			}
		} catch (Exception ex) {
			out.println("3秒後跳轉回已完成頁面");
			out.println("<br/>");
			response.setHeader("Refresh","3; /QROrders/OrderFinished.jsp?begin=0&end=10");
		}
	}
	if ("4".equals(request.getParameter("excel"))) {
		try {
			String[] path = (String[]) session.getAttribute("pathsent");

			String pa = path[0];
			String filename = path[1];
			filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");
			File file = new File(pa + filename);
			System.out.println("file");
			if (file.exists()) {//檢驗檔案是否存在

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
			}

			else {
				out.println(pa + filename + " : 此檔案不存在");
				out.println("<br/>");
				out.println("請先選擇想要列印的訂單，再按下列印出貨單按鈕");
				out.println("<br/>");
				out.println("3秒後跳轉回揀貨中頁面");
				out.println("<br/>");
				response.setHeader("Refresh","3; /QROrders/OrderPickupPage.jsp?begin=0&end=10");
			}
		} catch (Exception ex) {
			out.println("請先選擇想要列印的訂單，再按下列印揀貨單按鈕");
			out.println("<br/>");
			out.println("3秒後跳轉回揀貨中頁面");
			out.println("<br/>");
			response.setHeader("Refresh","3; /QROrders/OrderPickupPage.jsp?begin=0&end=10");
		}
	}

	if ("5".equals(request.getParameter("excel"))) {
		try {
			String[] path = null;
			if (session.getAttribute("pathpick") == null || "".equals(session.getAttribute("pathpick"))) {
			} else {
				path = (String[]) session.getAttribute("pathpick");
			}
			String pa = path[0];
			String filename = path[1];
			filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");
			File file = new File(pa + filename);
			System.out.println("file");
			if (file.exists()) {//檢驗檔案是否存在
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

			} else {
				out.println(pa + filename + " : 此檔案不存在");
				out.println("<br/>");
				out.println("請先選擇想要列印的訂單，再按下列印揀貨單按鈕");
				out.println("<br/>");
				out.println("3秒後跳轉回揀貨中頁面");
				out.println("<br/>");
				response.setHeader("Refresh","3; /QROrders/OrderPickupPage.jsp?begin=0&end=10");
			}
		} catch (Exception ex) {
			out.println("請先選擇想要列印的訂單，再按下列印揀貨單按鈕");
			out.println("<br/>");
			out.println("3秒後跳轉回揀貨中頁面");
			out.println("<br/>");
			response.setHeader("Refresh","3; /QROrders/OrderPickupPage.jsp?begin=0&end=10");
		}
	}
	if ("6".equals(request.getParameter("excel"))) {
		try {
			String[] path = (String[]) session.getAttribute("pathcoll");
			String pa = path[0];
			String filename = path[1];
			filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");
			File file = new File(pa + filename);
			System.out.println("file");
			if (file.exists()) {//檢驗檔案是否存在

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

			} else {
				out.println(pa + filename + " : 此檔案不存在");
				out.println("<br/>");
				out.println("3秒後跳轉回揀貨中頁面");
				out.println("<br/>");
				response.setHeader("Refresh","3; /QROrders/OrderPickupPage.jsp?begin=0&end=10");
			}
		} catch (Exception ex) {
			out.println("請先選擇想要列印的訂單，再按下列印集貨單按鈕");
			out.println("<br/>");
			out.println("3秒後跳轉回揀貨中頁面");
			out.println("<br/>");
			response.setHeader("Refresh","3; /QROrders/OrderPickupPage.jsp?begin=0&end=10");
		}
	}
	if ("counting".equals(request.getParameter("excel"))) {
		try {
			String[] path = (String[]) session.getAttribute("pathcounting");
			String pa = path[0];
			String filename = path[1];
			filename = new String(filename.getBytes("ISO-8859-1"), "ISO-8859-1");
			File file = new File(pa + filename);
			System.out.println("file");
			if (file.exists()) {//檢驗檔案是否存在

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

			} else {
				out.println(pa + filename + " : 此檔案不存在");
				out.println("<br/>");
				out.println("3秒後跳轉回查詢庫存頁面");
				out.println("<br/>");
				response.setHeader("Refresh","3; /QRProduct/SearchStockPage.jsp");
			}
		} catch (Exception ex) {
			out.println("<br/>");
			out.println("3秒後跳轉回查詢庫存頁面");
			out.println("<br/>");
			response.setHeader("Refresh","3; /QRProduct/SearchStockPage.jsp");
		}
	}
%>