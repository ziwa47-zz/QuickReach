<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.stock.CStockFactory,org.json.*"  %>

<jsp:useBean id="searchSecured" class="tw.iii.qr.stock.CStockFactory" scope="page"/>

<% 
JSONArray jo1 = searchSecured.iosSearchSecuredno();
out.print(jo1);
%>
