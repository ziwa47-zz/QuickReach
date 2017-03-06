<%@page import="tw.iii.qr.DataBaseConn"%>
<%@page import="tw.iii.qr.IndependentOrder.model.entity.Guest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*" %>
<jsp:useBean id="getGuest" class="tw.iii.guest.GuestFactory"  scope="page"/>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客戶管理</title>
</head>
<body>
<%@include file="/href/navbar.jsp"%>
<c:if test="${PageCompetence.getAccountInfoEdit() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
Connection conn1 = new DataBaseConn().getConn();
LinkedList<Guest> guest = getGuest.searchSGName(request,conn1);
session.setAttribute("guest", guest);
conn1.close();
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#C7AAE4;" >
      <ul class="nav nav-tabs">
        <li><a href="./GuestManage.jsp" style="color:#fff">客戶管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li><a href="./GuestManage.jsp" style="color: #fff">客戶管理</a></li>
        <li><a href="./Guest.jsp">新增客戶</a></li>
      </ul>
    </div>
  </div>
</div>
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="../HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="./accountManage.jsp">客戶管理</a></li>
    <li><a href="./account.jsp">新增客戶</a></li>
  </ol>
</div>

<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" method="post" action="../GuestServelet" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>客戶管理</legend>
      <table class="table table-bordered table-hover table-condensed pull-left">
        <thead>
          <tr class="ListTitle2">
            <th>編輯</th>
            <th>熟客ID</th>
            <th>姓名</th>
            <th>公司名稱</th>
            <th>平台帳號</th>
            <th>Email</th>
            <th>國家</th>
            <th>手機</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="i" varStatus="check" items="${guest}" begin="0" step="1">
            <tr>
              <td><a href ="editGuest.jsp?p=${i.getId()}"> <img src="../img/compose-4.png"></a></td>
              <td>${i.getGuestId()}</td>
              <td>${i.getName()}</td>
              <td>${i.getCompany()}</td>
              <td>${i.getPlatformAccount()}</td>
              <td>${i.getEmail()}</td>
              <td>${i.getCountry()}</td>
              <td>${i.getTel()}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </fieldset>
  </form>
</div>
<%@include file="/href/footer.jsp" %>
</body>
</html>