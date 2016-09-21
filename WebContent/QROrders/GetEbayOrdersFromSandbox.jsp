<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.order.CompleteSale,org.json.*"  %>
<jsp:useBean id="CGetEbaySandbox" class="tw.iii.qr.order.CGetEbaySandbox" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>沙盒訂單</title>
</head>
<body>
<%
CGetEbaySandbox.getEbaySandbox();
out.write("<script type='text/javascript'>");
out.write("alert('Complete');");
out.write("window.location = '../HomePage.jsp';");
out.write("</script>");
%>
</body>
</html>