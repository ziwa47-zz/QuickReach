<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.*,javax.servlet.*"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>查詢庫存</title>

<script type="text/javascript">
	$(function() {
		//日期選擇器
		$("input[name=date]").datepicker({
			dateFormat : 'yy/mm/dd',
			showOn : "both"
		});

	});
</script>

</head>
<body>
<%@ include file="/href/navbar.jsp"%>
<c:if test="${PageCompetence.getInventoryManage() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
	<div class="nav">
		<div class="container">
			<div class="navbar-left" style="background-color: #BCF1E5;">
				<ul class="nav nav-tabs">
					<li class="" style="background-color: #1CAF9A"><a
						href="SearchStockPage.jsp" style="color: #FFFFFF">庫存</a></li>
					<c:if test="${PageCompetence.getProductManage() == 1 }">  	
						<li><a href="SearchProductPage.jsp" style="color: #000000">商品</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="background-color: #1CAF9A;">
				<ul class="nav nav-tabs">
					<li class="" style="background-color: #1CAF9A"><a href="SearchStockPage.jsp" style="color: #fff">查詢庫存</a></li>
					<li ><a href="PurchasePage.jsp" style="color: #000">進貨</a></li>
					<li ><a
						href="PurchaseRecordPage.jsp" style="color: #000">進/出貨紀錄</a></li>
						<li ><a	href="StockTransferPage.jsp" style="color: #000">轉倉</a></li>
						<li ><a	href="SearchStockTransfer.jsp" style="color: #000">轉倉紀錄</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchStockPage.jsp">庫存/商品管理</a></li>
			<li><a href="SearchStockPage.jsp">庫存</a></li>
		</ol>
	</div>
<div class="container" style="background: #9DDCD1; border-radius: 20px;">
  <form name="searchform" method="post" action="../Product.do" style="font-size: 100%; vertical-align: baseline; padding: 15px;" class="form-inline container">
    <fieldset>
      <legend>查詢庫存</legend>
      <input type="hidden">
      <div class="row">
        <div class="col-md-8 form-group ">
          <div class="row">
            <div class="col-md-2">
              <h5>
                <label>名稱關鍵字：</label>
              </h5>
            </div>
            <div class="col-md-8 input-group" style="padding-left: 15px; padding-right: 35px">
              <input class="form-control" name="pname" type="text" style="border-radius: 4px">
            </div>
          </div>
        </div>
        <div class="col-md-4 form-group ">
          <div class="row">
            <div class="col-md-4">
              <h5>
                <label>廠牌：</label>
              </h5>
            </div>
            <div class="col-md-8">
              <input class="form-control" name="brand" type="text">
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-8 form-group ">
          <div class="row">
            <div class="col-md-2">
              <h5>
                <label>SKU：</label>
              </h5>
            </div>
            <div class="col-md-8 input-group" style="padding-left: 15px; padding-right: 35px">
              <input class="form-control" name="sku" type="text" style="border-radius: 4px">
            </div>
          </div>
        </div>
        <div class="col-md-4 form-group ">
          <div class="row">
            <div class="col-md-4">
              <h5>
                <label>副廠牌：</label>
              </h5>
            </div>
            <div class="col-md-8">
              <input class="form-control" name="subbrand" type="text">
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-8 form-group ">
          <div class="row">
            <div class="col-md-2">
              <h5>
              <!--   <label>儲位區間：</label> -->
              </h5>
            </div>
            <div class="col-md-10">
             <!--  <input class="form-control" type="text" name="location1">
              <label>~</label>
              <input class="form-control" type="text" name="location2"> -->
            </div>
          </div>
        </div>
        <div class="col-md-4 form-group ">
          <div class="row">
            <div class="col-md-4">
              <h5>
                <label>規格：</label>
              </h5>
            </div>
            <div class="col-md-8">
              <input class="form-control" name="spec" type="text">
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-8 form-group ">
          <div class="row">
            <div class="col-md-2">
              <h5>
                <label>建檔日期：</label>
              </h5>
            </div>
            <div class="col-md-10">
              <input class="form-control" type="text" name="date1">
              <label>~</label>
              <input class="form-control" type="text" name="date2">
            </div>
          </div>
        </div>
        <div class="col-md-4 form-group ">
          <div class="row">
            <div class="col-md-4">
              <h5>
                <label>顏色：</label>
              </h5>
            </div>
            <div class="col-md-8">
              <input class="form-control" name="color" type="text">
            </div>
          </div>
        </div>
      </div>
      <br />
      <div class="row text-center">
        <button type="submit" name="submit" value="submitstorage" class="btn-lg btn-success">搜尋</button>
        <button type="reset" name="" class="btn-lg btn-success">清空</button>
        <button type="submit" name="submit" value="counting" class="btn-lg btn-success">匯出盤點</button>
      </div>
    </fieldset>
  </form>
</div>
<br />
<%
	HttpSession sess = request.getSession();
	if (sess.getAttribute("storageall") != null && sess.getAttribute("storageall") != "") {
	}
%>
<c:choose>
  <c:when test="${storageall != null}">
    <div class="container table-responsive bg-warning" style="border-radius: 20px">
      <form name="searchform" method="post" action="#" style="font-size: 100%; vertical-align: baseline; padding: 15px;" class="form-inline container">
        <table class="table table-bordered table-hover table-condensed pull-left" style="margin: 0 0 0 -15px">
          <thead>
          <tr class="ListTitle2">
            <th nowrap>查看</th>
            <th>SKU</th>
            <th>廠牌</th>
            <th>副廠牌</th>
            <th>品名</th>
            <th>規格</th>
            <th>顏色</th>
            <th nowrap>總庫存</th>
          </tr>
          </thead>
          <c:forEach var="i" items="${storageall}" begin="0" step="1" varStatus="check">
          <tbody>
            <tr>
            <c:set var="string1" value="${ i.getSKU()}"/>
           <c:set var="string2" value="${fn:substring(string1, 0, 3)}" />	
           
           <c:if test="${string2  != 'B00'}" > 
             <td><a href="StockDetail.jsp?sku=${i.getSKU()}"><img src="../img/compose-4.png"></a></td>
           </c:if>
           
 		<c:if test="${string2 == 'B00'}">
             <td><a href="BundlesDetail.jsp?QQ=${i.getSKU()}"><img src="../img/compose-4.png"></a></td>
           </c:if>
            
              <td>${i.getSKU()}</td>
              <td>${i.getBrand()}</td>
              <td>${i.getSubBrand()}</td>
              <td>${i.getP_name()}</td>
              <td>${i.getSpec()}</td>
              <td>${i.getColor()}</td>
              
              
              <c:choose>
              <c:when test="${i.getAllStock() < i.getSecuredQty()}">
               <td style="color: red;">${i.getAllStock()}</td>
              </c:when>
              <c:otherwise>
              <td >${i.getAllStock()}</td>
              </c:otherwise>
              </c:choose>
             
            </tr>
            </tbody>
          </c:forEach>
        </table>
      </form>
    </div>
  
  </c:when>
</c:choose>




</body>

<%@ include file="/href/footer.jsp"%>

</html>