<%@page import="tw.iii.qr.DataBaseConn"%>
<%@page import="tw.iii.supplyCompany.CSupplyCompany"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*" %>
<jsp:useBean id="getaccount" class="tw.iii.supplyCompany.SCFactory"  scope="page"/>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供應商帳號管理</title>
</head>
<body>
<%@include file="/href/navbar.jsp"%>
<c:if test="${PageCompetence.getParamSettingEdit() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
Connection conn = new DataBaseConn().getConn();
LinkedList<CSupplyCompany> account1 = getaccount.searchSCName(conn,request);
session.setAttribute("account1", account1);
conn.close();
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#C7AAE4;" >
      <ul class="nav nav-tabs">
        <li><a href="SCManage.jsp" style="color:#fff">供應商帳號管理</a></li>
        <li><a href="../QRWarehouse/warehouseManage.jsp" >倉庫管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li><a href="SCManage.jsp" style="color: #fff">供應商帳號管理</a></li>
        <li><a href="addSCName.jsp" >新增供應商帳號</a></li>
      </ul>
    </div>
  </div>
</div>
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="../HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="SCManage.jsp">供應商帳號管理</a></li>
    <li><a href="SCManage.jsp">供應商帳號管理</a></li>
  </ol>
</div>

<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" method="post" action="../EbayAccountDo" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>供應商帳號管理</legend>
      <table class="table table-bordered table-hover table-condensed pull-left">
        <thead>
          <tr class="ListTitle2">
            <th>編輯</th>
            <th>公司序號</th>
            <th>公司名稱</th>
            <th>電話</th>
            <th>傳真</th>
            <th>地址</th>
            <th>備註</th>
           
          </tr>
        </thead>
        <tbody>
          <c:forEach var="i" varStatus="check" items="${account1}" begin="0" step="1">
            <tr>
              <td><a href ="editSCName.jsp?p=${i.getItem()}"> <img src="../img/compose-4.png"></a></td>
              <td>${i.getItem()}</td>
              <!-- Item -->
              <td>${i.getCompanyName()}</td>
              <!-- CompanyName -->
              <td>${i.getTel()}</td>
              <!-- Tel -->
              <td>${i.getFax()}</td>
              <!-- Fax -->
              <td>${i.getAddress()}</td>
              <!-- Address-->
              <td>${i.getComment()}</td>
              <!-- Comment-->
             
              
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