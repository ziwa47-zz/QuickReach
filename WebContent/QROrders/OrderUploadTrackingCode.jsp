<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Tracking Code</title>
</head>
<body>
<%@ include file = "/href/navbar.jsp"%>
  <div class="nav">
  	<div class="container">
    	<div class="navbar-left" style="background-color:#F3CE9A;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#A45A21"><a href="SearchOrder.jsp" style="color:#FFFFFF">訂單管理</a></li>
              <li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
              <li><a href="SearchComment.jsp">查詢評價</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#A45A21;" >
        	<ul class="nav nav-tabs">
              <li><a href="SearchOrder.jsp">查詢訂單</a></li>
              <li><a href="OrderProcessingPage.jsp">處理中</a></li>
              <li><a href="OrderPickupPage.jsp">揀貨中</a></li>
              <li><a href="OrderUploadTrackingCode.jsp" style="color:#fff">上傳追蹤碼</a></li>
              <li><a href="OrderFinished.jsp">已完成訂單</a></li>
              <li><a href="OrderAbnormal.jsp">異常訂單</a></li>
            </ul>
        </div>
    </div>
  </div>
  
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
      <li><a href="#" >主要目錄</a></li>
      <li class="active" style="display:"><a href="#">訂單</a></li>
      <li><a href="#">訂單查詢</a></li>
    </ol>
  </div>
<div class="nav">  
  <div class="container" style="background: #D9A56B; border-radius:20px;">
  	<form name="searchform" method="post" action="../StatusDo" style="font-size: 100%; vertical-align: baseline; 
    padding: 15px; " class="container">
      <fieldset class="font-weight" style="padding:0 30px 0 0;"><legend>出貨處理</legend>
    	<input type="hidden">
    	  <div class="row">
    	    <div class="col-md-8 form-group ">
    	      <div class="row">
    	        <div class="col-md-2"><h5><label> 訂單號碼：</label></h5></div>
                <div class="col-md-10"><input class="form-control" name="orderId" type="text"></div>
    	      </div>
    	    </div>
    	  </div>
    	  <div class="row">
        	<div class="col-md-8 form-group ">
              <div class="row">
                <div class="col-md-2"><h5><label>追蹤碼：</label></h5></div>
                <div class="col-md-10"><input class="form-control" name="trackingCode" type="text"></div>
              </div>
            </div>
          </div>
          <button type="submit" name="send" value="sendTrackingCode">送出</button>
      </fieldset>
    </form>
  </div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom" role="navigation" style="background-color:#000">
  	<div class="container">							  
      <div class="row" >           
          <p class="col-md-5 copyright" id="copyright" >Copyright © 2016 III South
          </p>           
          <p class="text-center footer-bot" id="link">          
              <a href="#">關於我們</a>｜
              <a href="#">服務合約書</a>｜           
              <a href="#">連絡我們</a>        
          </p>         
      </div>     
    </div>
</nav>
</body>
</html>