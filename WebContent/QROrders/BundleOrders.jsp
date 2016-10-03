<%@page import="tw.iii.qr.order.COrders"%>
<%@page import="java.util.LinkedList"%>
<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="COrderFactory" class="tw.iii.qr.order.COrderFactory" scope="page" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/href/navbar.jsp" %>
<c:if test="${PageCompetence.getOrdersManage() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
Connection conn = new DataBaseConn().getConn();
LinkedList<COrders> similarOrders = COrderFactory.getSimilarOrders(request, conn);
request.setAttribute("list", similarOrders);

%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#F3CE9A;" >
      <ul class="nav nav-tabs">
        <li class="" style="background-color:#A45A21"><a href="SearchOrder.jsp?begin=0&end=10" style="color:#FFFFFF">訂單管理</a></li>
        <c:if test="${PageCompetence.getEntireOrders() == 1 }"> 
        	<li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
      	</c:if>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#A45A21;" >
      <ul class="nav nav-tabs">
        <li><a href="SearchOrder.jsp?begin=0&end=10" style="color:#fff">查詢訂單</a></li>
        <li><a href="OrderProcessingPage.jsp?begin=0&end=10">處理中</a></li>
        <li><a href="OrderPickupPage.jsp?begin=0&end=10">揀貨中</a></li>
        <li><a href="OrderUploadTrackingCode.jsp?begin=0&end=10">上傳追蹤碼</a></li>
        <li><a href="OrderFinished.jsp?begin=0&end=10">已完成訂單</a></li>
        <li><a href="OrderAbnormal.jsp?begin=0&end=10">異常訂單</a></li>
        <li><a href="ShipmentRecord.jsp?begin=0&end=10" >訂單出貨記錄</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
    <li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
  </ol>
</div>

<div class="nav">
  <div class="container table-responsive bg-warning" style="border-radius:20px;">
    <form name="searchform" method="post" action="../OrdersServlet" class="form-inline container"
    style="font-size: 100%; vertical-align: baseline; padding: 15px;">
      <fieldset class="font-weight" style="padding:0 30px 0 0;">
        <legend style="color:#000">待合併訂單</legend>
        <table class="table table-bordered table-hover table-condensed pull-left">
          <thead>
            <tr class="ListTitle">
              <th>選取</th>
              <th>訂單編號</th>
              <th>平台</th>
              <th>Ebay Account</th>
              <th>E/B帳號</th>
              <th>付款日期</th>
              <th>訂單狀態</th>
              <th>總價</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="i" items="${list}" begin="0" step="1" varStatus="check">
            <tr>
              <td><input type="checkbox" name="QR_id" value="${i.getCOrderMaster().getQR_id()}"></td>
              <td>${i.getCOrderMaster().getQR_id()}</td>
              <td>${i.getCOrderMaster().getPlatform()}</td>
              <td>${i.getCOrderMaster().getEbayAccount()}</td>
              <td>${i.getCOrderMaster().getGuestAccount()}</td>
              <td>${i.getCOrderMaster().getPayDate()}</td>
              <td>${i.getCOrderMaster().getOrderStatus()}</td>
              <td>${i.getCOrderMaster().getTotalPrice()}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <div class="row text-center" >
      <button type="submit" name="send" value="" class="btn-lg btn-primary"
       >送出</button>
    </div>
      </fieldset>
    </form>
  </div>
  
  <div class="container table-responsive bg-warning" style="border-radius:20px;">
    <form name="searchform" method="post" action="../OrdersServlet" class="form-inline container"
    style="font-size: 100%; vertical-align: baseline; padding: 15px;">
      <fieldset class="font-weight" style="padding:0 30px 0 0;">
        <legend style="color:#000">已合併訂單</legend>
        <table class="table table-bordered table-hover table-condensed pull-left">
          <thead>
            <tr class="ListTitle">
              <th>選取</th>
              <th>訂單編號</th>
              <th>平台</th>
              <th>Ebay Account</th>
              <th>E/B帳號</th>
              <th>付款日期</th>
              <th>訂單狀態</th>
              <th>總價</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="i" items="${list2}" begin="0" step="1" varStatus="check">
            <tr>
              <td><input type="checkbox" name="QR_id" value="${i.getCOrderMaster().getQR_id()}"></td>
              <td>${i.getCOrderMaster().getQR_id()}</td>
              <td>${i.getCOrderMaster().getPlatform()}</td>
              <td>${i.getCOrderMaster().getEbayAccount()}</td>
              <td>${i.getCOrderMaster().getGuestAccount()}</td>
              <td>${i.getCOrderMaster().getPayDate()}</td>
              <td>${i.getCOrderMaster().getOrderStatus()}</td>
              <td>${i.getCOrderMaster().getTotalPrice()}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </fieldset>
    </form>
  </div>
</div>
<script type="text/javascript">
    function onChangeSearchOrders(){
    	listForm.action = "BundleOrders.jsp"
	    listForm.submit()
	    	
    }
</script>
</body>
</html>