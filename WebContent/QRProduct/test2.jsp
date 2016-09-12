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
              <li ><a href="BundlesAdd.jsp" style="color:#000">新增複合商品</a></li>
              <li ><a href="ProductEditPage.jsp" style="color:#000000">修改商品</a></li>
              <li class="" style="background-color:#1CAF9A"><a href="TotalBundles.jsp" style="color:#fff">查詢複合商品</a></li>
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
request.setAttribute("bundlesMaster",list);    
%>    
  <c:forEach var="i" varStatus="check" items="${bundlesMaster}" begin="0" step="1">
<div class="panel-group" id="accordion">  

	  <div class="panel panel-default">
	          <div class="panel-heading">
	            <h4 class="panel-title">
	              	<a data-toggle="collapse" data-parent="#accordion" href="#${i.getSKU()}">
	              		<input type="hidden" name="${i.getSKU()}${'sku'}" value="${i.getSKU()}">${i.getSKU()}	
	              	</a>
				  <input type="hidden" name="${i.getSKU()}${'name'}" value="${i.getP_name()}">${i.getP_name()}
				  <input type="hidden" name="${i.getSKU()}${'comment'}" value="${i.getComment()}">${i.getComment()}
				  <button class="pull-right" value="${i.getSKU()}" type="submit" name="smt">查看</button>
	            </h4>
	          </div>
	     <div id="${i.getSKU()}" class="panel-collapse collapse">
	         <div class="panel-body">
		    	<div class="container-fluid form-horizontal">
<c:set var="msku" scope="session" value="${i.getSKU()}"/>		    	
<%

String msku = (String)session.getAttribute("msku");

blf.showBundlesDetail(msku);

request.setAttribute("bundlesDetail",(LinkedList<String[]>)blf.bundlesList);    

%>	    	
					<c:forEach var="i" varStatus="check" items="${bundlesDetail}" begin="0" step="1">
			    		<div class="row">
				    		<div class="col-md-3">${i[0]}</div>
				    		<div class="col-md-8">${i[1]}</div>
				    		<div class="col-md-1">${i[2]}</div>
			 			</div>
		    		</c:forEach> 
		  		</div>
	  		</div>
	  	</div>
	  </div>

</div>
    </c:forEach>
  
	  </fieldset>
    </form>
</div>
</body>
  <label></label>
<%@ include file="/href/footer.jsp" %>    
 
</html>