<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.order.CompleteSale,org.json.*"  %>

<jsp:useBean id="CGetEbay" class="tw.iii.qr.order.CGetEbay" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>真的ebay訂單</title>
</head>
<body>
<div class="loader text-center" id="spinner"></div>
<%
CGetEbay.CGetEbay1();
out.write("<script type='text/javascript'>");
out.write("alert('Complete');");
out.write("window.location = '../HomePage.jsp';");
out.write("</script>");
%>

</body>
</html>