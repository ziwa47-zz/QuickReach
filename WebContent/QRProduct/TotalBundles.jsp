<%@ page import="tw.iii.qr.stock.CProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="blf" scope="session" class="tw.iii.qr.stock.BundlesFactory" />
<jsp:setProperty name="blf" property="*"/>   

<html lang="en">
  <head>
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查詢複合商品</title>
    <!-- Bootstrap -->
	<link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/smoothness/jquery-ui.css">

      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="js/bootstrap.js"></script>

    
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.ui.datepicker-zh-TW.js"></script>
  	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.js"></script>
    <script type="text/javascript">
    function reload(){
    	
    	location.reload();
    	searchform.action="bundles.do";
    	searchform.submit();
    }
   
    
    
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
              <li class="" style="background-color:#1CAF9A"><a href="TotalBundles.jsp" style="color:#fff">查詢複合商品</a></li>
              <li ><a href="NewProduct.jsp" style="color:#000000">新增單項商品</a></li>
              <li ><a href="BundlesAdd.jsp" style="color:#000">新增複合商品</a></li>
            </ul>
        </div>
    </div>
  
  </div>
  
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
          <li><a href="../QRMain/HomePage.jsp" >首頁</a></li>
          <li class="active" style="display:"><a href="SearchProductPage.jsp">庫存/商品管理</a></li>
          <li><a href="TotalBundles.jsp">查詢複合商品</a></li>
      </ol>
        </div>
  
  <div class="container" style="background: #9DDCD1; border-radius:20px">
  	<form name="searchform" method="post" action="totalBundles.do" style="font-size: 100%; vertical-align: baseline; 
    padding: 15px; " class="form-inline container">
      <fieldset><legend>查詢複合商品</legend>
<% 
session.removeAttribute("getBundlesDetail");
LinkedList<CProduct> list =  new LinkedList<CProduct>();
list = blf.getTotalBundles();
request.setAttribute("list",list);    
%>    
    
    	<table class="table table-bordered table-hover table-condensed pull-left" style="margin: 0 0 0 -15px">
			<tr class="ListTitle2">
				<th>SKU</th>
				<th>品名</th>
				<th>備註</th>
				<th></th>							
			</tr>
			<c:forEach var="i" varStatus="check" items="${list}" begin="0" step="1">
	    		<tr>
	    			<td><input type="hidden" name="${i.getSKU()}${'sku'}" value="${i.getSKU()}">${i.getSKU()}</td>
	    			<td><input type="hidden" name="${i.getSKU()}${'name'}" value="${i.getP_name()}">${i.getP_name()}</td>
	    			<td><input type="hidden" name="${i.getSKU()}${'comment'}" value="${i.getComment()}">${i.getComment()}</td>
	    			<td><button value="${i.getSKU()}" type="submit" name="smt">查看</button></td>
	 			</tr>   	
    		</c:forEach> 
    	</table>
	  </fieldset>
    </form>
</div>
</body>
  <label></label>
<%@ include file="/href/footer.jsp" %>    
 
</html>