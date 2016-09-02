<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*,tw.iii.qr.stock.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="stockDetail" class="tw.iii.qr.stock.CStockFactory" scope="page" />
<jsp:useBean id="productDetail" class="tw.iii.qr.stock.CProductFactory" scope="page" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>庫存細節</title>
</head>
<body>
<%@ include file ="/href/navbar.jsp" %>
<div class="nav">
  	<div class="container">
    	<div class="navbar-left" style="background-color:#BCF1E5;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#1CAF9A"><a href="SearchStockPage.jsp" style="color:#FFFFFF">庫存</a></li>
              <li><a href="SearchProductPage.jsp" style="color:#000000">商品</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#1CAF9A"><a href="SearchStockPage.jsp" style="color:#fff">查詢庫存</a></li>
              <li><a href="NewProduct.jsp">新增產品</a></li>
              <li ><a href="purchasePage.jsp" style="color:#000">進貨</a></li>
              <li><a href="PurchaseRecordPage.jsp" style="color:#000000">進貨紀錄</a></li>
              <li><a href="OutRecordPage.jsp" style="color:#000000">出貨紀錄</a></li>
            </ul>
        </div>
    </div>
  
  </div>
  
  
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="#" >主要目錄</a></li>
    <li class="active" style="display:"><a href="#">庫存</a></li>
    <li><a href="#">庫存查詢</a></li>
  </ol>
</div>

<div class="container" style="background: #9DDCD1; border-radius:20px;">
  <form method="post" action="../ProductDo" 
  style="font-size: 100%; vertical-align: baseline; padding: 15px;">
    <fieldset id="myfields" class="container-fluid" style="padding:0 30px 0 0;" disabled ><legend>庫存明細</legend>
      <%
String sku ;
request.setCharacterEncoding("UTF-8");
if(request.getParameter("sku") != null || request.getParameter("sku") != ""){
Connection conn = new DataBaseConn().getConn();
sku = request.getParameter("sku");

CProduct product = productDetail.searchDetail(sku, conn);
session.removeAttribute("product");
session.setAttribute("product", product);

LinkedList<CStock> stock = stockDetail.searchDetailStock(sku, conn);
session.removeAttribute("resultDetail");
session.setAttribute("resultDetail", stock);
conn.close();
}

%>
      <input type="hidden">
      <div class="container-fluid form-horizontal">
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  ><h4>SKU</h4></div>
          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="sku" value="${product.getSKU()}" ></div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  ><h4>廠牌</h4></div>
          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="brand" value="${product.getBrand()}" ></div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  ><h4>副廠牌</h4></div>
          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="subbrand" value="${product.getSubBrand()}" ></div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  ><h4>規格</h4></div>
          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="spec" value="${product.getSpec()}" ></div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  ><h4>顏色</h4></div>
          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="color" value="${product.getColor()}" ></div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  ><h4>供應商代號</h4></div>
          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="cid" value="${product.getSKU()}" ></div>
        </div>
      </div>
    </fieldset>
  </form>
</div>
 <div class="container table-responsive bg-warning" style=" border-radius:20px">
	<form name="searchform" method="post" action="#" style="font-size: 100%; vertical-align: baseline; 
  padding: 15px; " class="form-inline container">

      <table class="table table-bordered table-hover table-condensed pull-left" 
      style="margin:0 0 0 -15px" >
      <thead>
 	    <tr class="ListTitle2">
          <th>倉別</th>
          <th>儲位</th>
          <th>庫存數量</th>
          <th>待處理量</th>
          <th>餘額</th>
          <th>進貨日期</th>
          <th>備註</th>
        </tr>
      </thead>
      <c:forEach var="i" items="${resultDetail}" begin="0" step="1" >
      <tbody>
        <tr>
          <td>${i.getWareHouse()}</td>
          <td>${i.getPosition1()}-${i.getPosition2()}</td>
          <td>${i.getQty()}</td>
          <td>${i.getQtysold()}</td>
          <td>${i.getQty()-i.getQtysold()}</td>
          <td>${i.getLastpurchasedate()}</td>
          <td>${i.getComment()}</td>
        </tr>
      </tbody>
     </c:forEach>
       </table>
      </form>
   </div>	

</body>
</html>