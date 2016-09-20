<%@page import="org.json.JSONArray"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="getJSON" class="tw.iii.qr.order.getJSON"	scope="page" />
<%
if(request.getParameter("QR_id")!=null){
JSONArray pickupResults = getJSON.pickupDetail(request);
out.print(pickupResults);
} 
if(request.getParameter("status")!=null){
getJSON.updateToFinished(request);
}
%>