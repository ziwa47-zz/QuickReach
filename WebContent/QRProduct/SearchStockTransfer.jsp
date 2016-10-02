<%@page import="org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID"%>
<%@page import="tw.iii.purchase.purchaseFactory"%>

<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest"%>
<jsp:useBean id="searchDetail" class="tw.iii.purchase.purchaseFactory"
	scope="page" />


<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="../js/jquery-1.12.4.min.js"></script>
<!-- jqueryAutoComplete 不可以在../js/jquery-1.12.4.min.js 之前 -->


<script type="text/javascript">
	function jqueryAutoCompletePurchaseId() {

		$("#stockTransferId").autocomplete({
			source : "../JQueryStockTransferId",
			minLength : 8,
			delay : 500,

		});

	}

	$(function() {

		jqueryAutoCompleteSKU();
		jqueryAutoCompletePurchaseId();

		//var rowCount = $('#myTable tr').length;
		//var rowCount = $("#myTable").attr('rows').length;
		var rowCount = $('#myTable tr:last').index();
		$("#myTable").prepend(
				'<label class="text-success"><h2>共找到    ' + rowCount / 2
						+ ' 筆資料</h2></label>');

	});

	function jqueryAutoCompleteSKU() {

		$("#sku").autocomplete({
			source : "../JQueryAutoCompleteSKUData",
			minLength : 3,
			delay : 1000
		});

	}
</script>
<title>轉倉紀錄</title>
</head>
<body>
	<%@ include file="../href/navbar.jsp"%>
<c:if test="${PageCompetence.getInventoryManage() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
	<%
		Connection conn = new DataBaseConn().getConn();

		LinkedList<LinkedList<String>> warehouseList = searchDetail.warehouseSelectOption(conn);
		LinkedList<LinkedList<String>> accountList = searchDetail.accountSelectOption(conn);
		request.setAttribute("warehouseList", warehouseList);
		request.setAttribute("accountList", accountList);
		conn.close();
		
		

		
		
			%>



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
					<li><a href="SearchStockPage.jsp" style="color: #000">查詢庫存</a></li>
					<li><a href="PurchasePage.jsp" style="color: #000">進貨</a></li>
					<li><a href="PurchaseRecordPage.jsp" style="color: #000">進/出貨紀錄</a></li>
					<li><a href="StockTransferPage.jsp" style="color: #000">轉倉</a></li>
					<li class="" style="background-color: #1CAF9A"><a
						href="SearchStockTransfer.jsp" style="color: #fff">轉倉紀錄</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchStockPage.jsp">庫存/商品管理</a></li>
			<li><a href="PurchaseRecordPage.jsp">轉倉紀錄</a></li>
		</ol>
	</div>



	<div class="container"
		style="background: #9DDCD1; border-radius: 20px;">
		<form name="searchform" method="GET" action="../InsertPurchaseServlet.do"
			style="font-size: 100%; vertical-align: baseline; padding: 15px;"
			class="form-inline container">
			<fieldset>
				<legend>轉倉紀錄查詢</legend>
				<input type="hidden">

				


				<div class="row">
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">單號：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" type="text" name="stockTransferId"
									id="stockTransferId">
							</div>
						</div>
					</div>

					<div class="col-md-5 form-group ">
						<div class="row">
							<div class="col-md-3">
								<h5>
									<label for="focusedInput ">日期：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" type="text" name="dateMin"
									style="width: 100px" readonly> - <input
									class="form-control" type="text" style="width: 100px"
									name="dateMax" readonly>
							</div>
						</div>
					</div>
				</div>



				<div class="row">


					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">SKU：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="sku" id="sku" type="text"
									value="">
							</div>
						</div>
					</div>

					


				</div>

				<div class="row">
					
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">經手人：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="staffId">
									<option value=""></option>
									<c:forEach var="i" begin="0" step="1" items="${accountList}">

										<option value="${i.get(0)}">${i.get(0)}</option>

									</c:forEach>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">原倉別：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="oldWareHouse">
									<option value=""></option>
									<c:forEach var="i" begin="0" step="1" items="${warehouseList}">

										<option value="${i.get(0)}">${i.get(1)}</option>

									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">新倉別：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="newWareHouse">
									<option value=""></option>
									<c:forEach var="i" begin="0" step="1" items="${warehouseList}">

										<option value="${i.get(0)}">${i.get(1)}</option>

									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>


				<br />
				<div class="row text-center">
					<input type="submit" name="transfer" value="搜尋"
						class="btn-lg btn-success">
				</div>
			</fieldset>
		</form>
	</div>
	<hr />
	<c:if test="${logList != null}">
		<div class="container table-responsive bg-warning table-hover"
			style="border-radius: 20px" id="myTable">
			<form name="searchform" method="post" action="#"
				style="font-size: 100%; vertical-align: baseline; padding: 15px;"
				class="form-inline container">

				<table
					class="table table-bordered table-hover table-condensed pull-left"
					style="margin: 0 0 0 -15px">

					<tr class="ListTitle"
						style="background-color: #A65758; color: #fff">
						<th>項目</th>
						<th>單號</th>

						<th>SKU</th>

						<th>轉出數量</th>
						<th>原倉別</th>
						<th>原儲位</th>
						

						<th>新倉別</th>
						<th>新儲位</th>
						
						<th>經手人</th>



					</tr>


					<c:forEach var="i" begin="0" step="1" items="${logList}"
						varStatus="nu">

						
							<tr class="success" style="background-color: #9DDCD1">
								    <td>${nu.count}</td>
								
									<td>${i.CPurchase_master.getStockTransferId()}</td>
									<td>${i.CPurchase_detailsSingle.getSKU()}</td>
									
									<td>${i.CPurchase_detailsSingle.getQty()}</td>
									<td>${i.CPurchase_master.getWarehouse()}</td>
									
									<td>${i.CPurchase_detailsSingle.getWarehousePosition()}</td>
									<td>${i.CPurchase_master.getWarehouse2()}</td>
									<td>${i.CPurchase_detailsSingle.getNewWarehousePosition()}</td>
									<td>${i.CPurchase_master.getStaffId()}</td>
									
							
							</tr>
							<tr class="success" style="background-color: #9DDCD1">
								
								<td colspan="13">${i.CPurchase_detailsSingle.getComment()}</td>
							</tr>

						

					
					</c:forEach>

				</table>


			</form>
		</div>
	</c:if>
</body>

<%@ include file="../href/footer.jsp"%>

</html>