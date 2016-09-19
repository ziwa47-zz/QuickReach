<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.stock.CStockFactory,org.json.*"  %>

<jsp:useBean id="searchSecured" class="tw.iii.qr.stock.CStockFactory" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<% 

JSONArray jo1 = searchSecured.iosSearchSecuredno();
out.print(jo1);
%>

</body>
</html>