<%@page import="org.json.JSONArray"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="getJSON" class="tw.iii.qr.order.getJSON"	scope="page" />
<%
JSONArray pickupResults = getJSON.pickupDetail(request);
out.print(pickupResults);
%>