<%@page import="tw.iii.qr.stock.*"%>
<%@page import="tw.iii.qr.DataBaseConn"%>
<%@page import="tw.iii.qr.stock.CEbay"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*" %>
<jsp:useBean id="get" class="tw.iii.qr.stock.CDBtoExcel"  scope="page"/>
<<jsp:setProperty property="*" name="get"/>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>testDBtoExcel</title>
</head>


<body>

<%
get.物流匯出格式();
%>

</body>
</html>