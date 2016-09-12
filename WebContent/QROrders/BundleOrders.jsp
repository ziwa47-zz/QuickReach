<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/href/navbar.jsp" %>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#F3CE9A;" >
      <ul class="nav nav-tabs">
        <li class="" style="background-color:#A45A21"><a href="SearchOrder.jsp?begin=0&end=10" style="color:#FFFFFF">訂單管理</a></li>
        <li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
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
  <div class="container" style="background: #D9A56B; border-radius:20px;">
    <form name="searchform" method="post" action="../OrdersServlet" class="form-inline container"
    style="font-size: 100%; vertical-align: baseline; padding: 15px;">
      <fieldset class="font-weight" style="padding:0 30px 0 0;">
        <legend>合併訂單</legend>
        
        
      </fieldset>
    </form>
  </div>
</div>
</body>
</html>