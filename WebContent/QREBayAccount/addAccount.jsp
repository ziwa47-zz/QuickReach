<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*" %>
<jsp:useBean id="getebay" class="tw.iii.qr.stock.CEbayFactory"  scope="page"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addAccount</title>

</head>
<body>
<%@include file="../href/navbar.jsp"%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color: #AC7ED3;">
      <ul class="nav nav-tabs">
        <li><a href="eBayAccount.jsp">eBay帳號管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color: #984AC0;">
      <ul class="nav nav-tabs">
        <li><a href="eBayAccount.jsp">帳號列表</a></li>
        <!--               <li class="" style="background-color:#1CAF9A"><a href="purchasePage.jsp" style="color:#FFFFFF">進貨</a></li> -->
        <li><a href="addAccount.jsp">新增eBay帳號</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb">
    <li><a href="../QRMain/HomePage.jsp">首頁</a></li>
    <li class="active"><a href="eBayAccount.jsp">eBay帳號管理</a></li>
    <li><a href="eBayAccount.jsp">新增eBay帳號</a></li>
  </ol>
</div>
<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" method="post" action="../EbayAccountDo" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>新增eBay帳號</legend>
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>ebay ID</h4>
        </div>
        <div class="col-md-5 well-sm control-label">
          <input class="form-control" type="text" name="ebayId" value="" required>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>ebay Token</h4>
        </div>
        <div class="col-md-5 well-sm control-label">
          <textarea class="form-control" name="ebayToken"  rows="5"	cols="85"  required></textarea>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>paypal 帳號(E-mail)</h4>
        </div>
        <div class="col-md-5 well-sm control-label">
          <input class="form-control" type="text" name="paypalAccount" value="" required>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司</h4>
        </div>
        <div class="col-md-5 well-sm control-label">
          <select class="form-control" name="correspondCompany">
            <option value="HUANG PO-WEI">HUANG PO-WEI</option>
            <option value="YU CHIN WU">YU CHIN WU</option>
            <option value="WHIRLWIND SPEED LIMITED">WHIRLWIND SPEED LIMITED</option>
          </select>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>啟用狀態</h4>
        </div>
        <div class="col-md-5 well-sm">
          <input type="radio" name="status" class="" checked>
          啟用
          <input type="radio" name="status" class="">
          停用 </div>
      </div>
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>回應評價文字</h4>
        </div>
        <div class="col-md-5 well-sm">
          <textarea class="form-control" name="systemFeedback"  rows="5" cols="85"></textarea>
        </div>
      </div>
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>ebay Token</h4>
        </div>
        <div class="col-md-5 well-sm control-label">
          <textarea class="form-control" name="ebayToken" rows="25" cols="95" required></textarea>
        </div>
      </div>
      <div class="" align="center">
        <input type="submit" name="submit" value="newAccount" class="btn-lg btn-success">
        <input type="button" value="取消" class="btn-lg btn-success">
      </div>
    </fieldset>
  </form>
</div>
</body>
</html>