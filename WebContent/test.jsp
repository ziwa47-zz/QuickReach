<%@page import="org.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.order.CompleteSale,org.json.*"  %>

<jsp:useBean id="CompleteSale" class="tw.iii.qr.order.CompleteSale" scope="page"/>

<%
CompleteSale.CompleteSale1(request);
%>