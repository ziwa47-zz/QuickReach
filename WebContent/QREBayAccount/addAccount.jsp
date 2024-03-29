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

<script src="../js/jquery-1.12.4.min.js"></script>


<script type="text/javascript">
	
	$(function() {

		$("#searchform").validate({
			//debug:true,
			ignore : [],
			invalidHandler : function(form) {
				

			}
		});
	});
</script>

<style type="text/css">
#searchform label.error {
font-size: 0.8em;
color: #F00;
font-weight: bold;
display: block;


}
</style>

</head>
<body>
<%@include file="../href/navbar.jsp"%>
<c:if test="${PageCompetence.getEbayPaypalAccountEdit() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color: #C7AAE4;">
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
    <li><a href="../HomePage.jsp">首頁</a></li>
    <li class="active"><a href="eBayAccount.jsp">eBay帳號管理</a></li>
    <li><a href="eBayAccount.jsp">新增eBay帳號</a></li>
  </ol>
</div>

<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" id="searchform" method="post" action="../EbayAccountDo" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>新增eBay帳號</legend>
     
      <div class="row">
        <div class="col-md-3 well-sm ">     
        <h4>ebay ID</h4>           
   <!-- <h4 class="col-md-5 well-sm control-label">ebay ID</h4>   --> 
        </div>
        <div class="col-md-5 well-sm ">
          <input class="form-control required" type="text" title="請輸入ebay ID" name="ebayId" value="" >
        </div>
      </div>
      
      <div class="row">
        <div class="col-md-3 well-sm">
        	<h4>ebay Token</h4>
       <!-- <h4 class="col-md-5 well-sm control-label">ebayToken</h4> -->
        </div>
        <div class="col-md-5 well-sm ">
          <textarea class="form-control required" name="ebayToken" title="請輸入ebayToken" rows="5"	cols="85"  ></textarea>
        </div>
      </div>
      
 <!--    <div class="row">
        <div class="col-md-3 well-sm">
          <h4>ebay endToken</h4>
        </div>
        <div class="col-md-5 well-sm control-label">
          <textarea class="form-control" name="endToken"  rows="5"	cols="85"  required></textarea>
        </div>
      </div>
  -->  
  
  
       
      <div class="row">
        <div class="col-md-3 well-sm">
        	<h4>paypal帳號(E-mail)</h4>	
          <!--  <h4>paypal帳號(E-mail)</h4> -->
        </div>
        <div class="col-md-5 well-sm ">
          <input class="form-control required" type="text" title="請輸入paypal帳號(E-mail)" name="paypalAccount" value="" >
        </div>
      </div>
      
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>國家</h4>
        </div>
        <div class="col-md-5 well-sm ">
          <input class="form-control" type="text" name="country" value="" >
        </div>
      </div>
      
       <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司</h4>
        </div>
        <div class="col-md-5 well-sm ">
        <input class="form-control required" type="text" title="請輸入對應公司" name="correspondCompany" id="correspondCompany" value="">
         
        </div>
      </div>
      
        <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司電話</h4>
        </div>
        <div class="col-md-5 well-sm ">
        <input class="form-control required" type="text" title="請輸入對應公司電話"  name="companyPhone" id="companyPhone" value="">
         
        </div>
      </div>
        <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司郵遞區號</h4>
        </div>
        <div class="col-md-5 well-sm ">
        <input class="form-control required" type="text" title="請輸入對應公司郵遞區號"  name="companyPost" id="companyPost" value="">
         
        </div>
      </div>
        <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司地址</h4>
        </div>
        <div class="col-md-5 well-sm ">
        <input class="form-control required" type="text" title="請輸入對應公司地址"  name="companyAddress" id="companyAddress" value="">
         
        </div>
      </div>
      
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>啟用狀態</h4>
        </div>
        <div class="col-md-5 well-sm">
          <input type="radio" name="status" class="" value="ON" checked> 啟用
          <input type="radio" name="status" class="" value="OFF"> 停用 </div>
      </div>
      
      
      
 <!--     <div class="row">
        <div class="col-md-3 well-sm">
          <h4>回應評價文字</h4>
        </div>
        <div class="col-md-5 well-sm">
          <textarea class="form-control" name="comment"  rows="5" cols="85"></textarea>
        </div>
      </div>
  -->  
  
      
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>SystemFeedback</h4>
        </div>
        <div class="col-md-5 well-sm ">
          <textarea class="form-control" name="systemFeedback" rows="25" cols="95" ></textarea>
        </div>
      </div>
      
      <div class="" align="center">
        <button type="submit" name="submitType" value="newAccount" class="btn-lg btn-success">新增</button>
        <a href="eBayAccount.jsp"><button type="button" value="取消" class="btn-lg btn-success">取消 </button></a>
      </div>
      
    </fieldset>
  </form>
</div>
</body>
</html>