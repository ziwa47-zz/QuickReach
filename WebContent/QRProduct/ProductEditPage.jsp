<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改商品</title>
    <!-- Bootstrap -->
	<link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/smoothness/jquery-ui.css">

      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="js/bootstrap.js"></script>

    
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.ui.datepicker-zh-TW.js"></script>
    
   
    <script type="text/javascript">        
        $(function () {
            //日期選擇器
            $("input[name=date]").datepicker({ dateFormat: 'yy/mm/dd', showOn: "both" });
            
           });
	</script>    
    
  </head>
  <body>
 <%@ include file = "/href/navbar.jsp"%>
  <div class="nav">
  	<div class="container">
    	<div class="navbar-left" style="background-color:#BCF1E5;" >
        	<ul class="nav nav-tabs">
              <li ><a href="SearchStockPage.jsp" style="color:#000">庫存</a></li>
              <li class="" style="background-color:#1CAF9A"><a href="SearchProductPage.jsp" style="color:#fff">商品</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li ><a href="SearchProductPage.jsp" style="color:#000">查詢商品</a></li>
              <li><a href="ProductAddPage.jsp" style="color:#000">新增商品</a></li>
              <li  class="" style="background-color:#1CAF9A"><a href="ProductEditPage.jsp" style="color:#fff">修改商品</a></li>
            </ul>
        </div>
    </div>
  
  </div>
  
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
          <li><a href="../QRMain/HomePage.jsp" >首頁</a></li>
          <li class="active" style="display:"><a href="SearchProductPage.jsp">庫存/商品管理</a></li>
          <li><a href="ProductEditPage.jsp">修改商品</a></li>
      </ol>
        </div>
  
  
  
  	
 
  <div class="container" style="background: #E5C1F9;">
    <h3>修改商品</h3>
  	
  </div>
  <hr/>
  
  
  	
    
    
  </body>
  
<%@ include file="/href/footer.jsp" %>
 
</html>