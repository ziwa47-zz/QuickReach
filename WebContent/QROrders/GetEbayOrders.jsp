<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isThreadSafe="false"%>
<jsp:useBean id="daliy" class="tw.iii.qr.daliy" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>真的ebay訂單</title>
</head>
<body>
	<div class="loader text-center" id="spinner"></div>
	<%
	tw.iii.qr.daliy.ClickGetEbayOrderAndDailyBalance(request,response);
		
		
	%>

</body>
</html>