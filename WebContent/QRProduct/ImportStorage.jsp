<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="importS" class="tw.iii.qr.stock.CExceltoDB" scope="page"/>
<%
importS.addProduct();
importS.addStorage();
response.sendRedirect("/HomePage.jsp");
%>