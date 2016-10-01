<%@page import="tw.iii.qr.DataBaseConn"%>
<%@page import="tw.iii.qr.QRAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*" %>
<jsp:useBean id="getaccount" class="tw.iii.qr.QRAccountFactory"  scope="page"/>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>員工帳號管理</title>
</head>
<body>
<%@include file="/href/navbar.jsp"%>
<c:if test="${PageCompetence.getAccountInfoEdit() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
Connection conn1 = new DataBaseConn().getConn();
LinkedList<QRAccount> account1 = getaccount.searchQRemployee(request,conn1);
session.setAttribute("account1", account1);
conn1.close();
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#C7AAE4;" >
      <ul class="nav nav-tabs">
        <li><a href="./accountManage.jsp" style="color:#fff">員工帳號管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li><a href="./accountManage.jsp" style="color: #fff">員工帳號管理</a></li>
        <li><a href="./Account.jsp">新增員工帳號</a></li>
      </ul>
    </div>
  </div>
</div>
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="../HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="./accountManage.jsp">員工帳號管理</a></li>
    <li><a href="./Account.jsp">新增員工帳號</a></li>
  </ol>
</div>

<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" method="post" action="../EbayAccountDo" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>員工帳號管理</legend>
      <table class="table table-bordered table-hover table-condensed pull-left">
        <thead>
          <tr class="ListTitle2">
            <th>編輯</th>
            <th>員工帳號</th>
        <!--<th>密碼</th>-->
            <th>姓氏</th>
            <th>名字</th>
            <th>E-Mail</th>
            <th>英文名字</th>
            <th>權限等級</th>
            <th>狀態</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="i" varStatus="check" items="${account1}" begin="0" step="1">
            <tr>
              <td><a href ="editAccount.jsp?p=${i.getAccount()}"> <img src="../img/compose-4.png"></a></td>
              <td>${i.getAccount()}</td>
              <!-- account -->
              
              <!--<c:if test="${i.getPassword()==i.getPassword()}"> -->
              <!--<td>*****</td>-->
              <!--</c:if> -->                         
              <!-- password -->
              
              <td>${i.getLastName()}</td>
              <td>${i.getFirstName()}</td>
              <!-- firstname -->
              <td>${i.getEmail()}</td>
              <!-- email-->
              <td>${i.getEnName()}</td>
              <!-- enname-->
              <td>${i.getCompetenceLV()}</td>
              <!-- competenceLV -->
              <c:if test="${i.getStatus()==1}">
              	<td>ON</td>
              </c:if>
              <c:if test="${i.getStatus()==0}">
              	<td>OFF</td>
              </c:if>
              <!-- status --> 
              
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