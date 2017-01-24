<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.stock.DTO.CStockFactory,org.json.*"  %>

<jsp:useBean id="searchSecured" class="tw.iii.qr.stock.DTO.CStockFactory" scope="page"/>

<% 
if(request.getParameter("p")!=null){
	JSONArray jo1 = searchSecured.iosSearchSecuredno();
	out.print(jo1);
}
if(request.getParameter("q")!=null){
	JSONArray jo2 = searchSecured.iosSearchproductstock(request);
	out.print(jo2);
}
if(request.getParameter("stock")!=null){
	JSONArray jo3 = searchSecured.iosstockDetail(request);
	out.print(jo3);
}

%>
