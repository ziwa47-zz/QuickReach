<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.DataBaseConn,org.json.*,java.sql.Connection,java.sql.PreparedStatement,java.util.Calendar"  %>

<jsp:useBean id="COrderFactory" class="tw.iii.qr.order.DTO.COrderFactory" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pre new order</title>
</head>
<body>
<div class="loader text-center" id="spinner"></div>
<%
Connection conn = new DataBaseConn().getConn();
String QR_id = COrderFactory.generateQR_Id04();
String strSql = "INSERT INTO orders_master (QR_id, payDate, orderDate, shippingDate, orderStatus)"
		+ " VALUES (?, ?, ?, ?, ?)";
PreparedStatement ps = conn.prepareStatement(strSql);
ps.setString(1, QR_id);
ps.setTimestamp(2, new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
ps.setTimestamp(3, new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
ps.setTimestamp(4, new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
ps.setString(5, "未完成");

int x = ps.executeUpdate();

String strSql2 = "INSERT INTO orders_detail (QR_id)"
		+ " VALUES (?)";
PreparedStatement ps2 = conn.prepareStatement(strSql2);
ps2.setString(1, QR_id);
int y = ps2.executeUpdate();

String strSql3 = "INSERT INTO orders_guestinfo (QR_id)"
		+ " VALUES (?)";
PreparedStatement ps3 = conn.prepareStatement(strSql3);
ps3.setString(1, QR_id);
int z = ps3.executeUpdate();

String strSql4 = "INSERT INTO order_recieverinfo (QR_id)"
		+ " VALUES (?)";
PreparedStatement ps4 = conn.prepareStatement(strSql4);
ps4.setString(1, QR_id);
int q = ps4.executeUpdate();

out.write("<script type='text/javascript'>");
out.write("alert('新增訂單開始');");
out.write("window.location = '../QROrders/NewOrder.jsp?QR_id=" + QR_id + "'");
out.write("</script>");
%>

</body>
</html>