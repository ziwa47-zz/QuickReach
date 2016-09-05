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

<link rel="stylesheet" type="text/css"
	href="../css/smoothness/jquery-ui.css">
<script src="../js/jquery-1.12.4.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/jquery.ui.datepicker-zh-TW.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

 
<script type="text/javascript">


$(function() {		
	
	
	jqueryAutoCompleteSKU();
	
	
});

function jqueryAutoCompleteSKU() {
	
	$("#sku").autocomplete({source:"../JQueryAutoCompleteSKUData",minLength:1});
	
	
}

	
</script>
<title>進貨紀錄</title>
</head>
<body>
	<%@ include file="../href/navbar.jsp"%>
	<%
		LinkedList<LinkedList<String>> warehouseList = searchDetail.warehouseSelectOption();
		LinkedList<LinkedList<String>> companyList = searchDetail.companySelectOption();
		request.setAttribute("warehouseList", warehouseList);
		request.setAttribute("companyList", companyList);
				
		
	%>



	<div class="nav">
		<div class="container">
			<div class="navbar-left" style="background-color: #BCF1E5;">
				<ul class="nav nav-tabs">
					<li class="" style="background-color: #1CAF9A"><a
						href="SearchStockPage.jsp" style="color: #FFFFFF">庫存</a></li>
					<li><a href="SearchProductPage.jsp" style="color: #000000">商品</a></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="background-color: #1CAF9A;">
				<ul class="nav nav-tabs">
					<li><a href="SearchStockPage.jsp" style="color: #000">查詢庫存</a></li>
					<li><a href="PurchasePage.jsp" style="color: #000">進貨</a></li>
					<li class="" style="background-color: #1CAF9A"><a
						href="searchPurchase.jsp" style="color: #fff">進/出貨紀錄</a></li>
					<li><a href="searchOutRecordPage.jsp" style="color: #000000">出貨紀錄</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="../QRMain/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchStockPage.jsp">庫存/商品管理</a></li>
			<li><a href="searchPurchase.jsp">進/出貨紀錄</a></li>
		</ol>
	</div>



	<div class="container"
		style="background: #9DDCD1; border-radius: 20px;">
		<form name="searchform" method="POST" action="PurchaseRecordPage.jsp"
			style="font-size: 100%; vertical-align: baseline; padding: 15px;"
			class="form-inline container">
			<fieldset>
				<legend>進/出貨紀錄查詢</legend>
				<input type="hidden">
				
				<div class="row">
					<div class="col-md-4 form-group ">
						<div class="row">
							
							<div class="col-md-4">
							<h5>
								<label for="focusedInput">種類：</label> 
							</h5>
							</div>
							<div class="col-md-8">
								<label class="checkbox-inline" style="margin-top: 7 "><input
									type="checkbox" name="purchaseRecord" value="purchase" >進貨紀錄</label>
								<label class="checkbox-inline"  style="margin-top: 7"><input type="checkbox"
									name="outRecord" value="outRecord" >出貨記錄</label>
							</div>
						</div>
					</div>
				</div>



				<div class="row">
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">日期：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" type="text" name="dateMin" style="width:89px" readonly> - <input class="form-control" type="text" name="dateMax" style="width:89px" readonly>
							</div>
						</div>
					</div>

					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">SKU：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="sku" id="sku" type="text" value="">
							</div>
						</div>
					</div>

					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">品名：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="pname" type="text" value="">
							</div>
						</div>
					</div>


				</div>

				<div class="row">
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">廠商：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="companyName" id="companyName">
									<option value=""></option>


									<c:forEach var="i" begin="0" step="1" items="${companyList}">

										<option value="${i.get(1)}">${i.get(1)}</option>

									</c:forEach>

								</select>
							</div>
						</div>
					</div>


					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">經手人：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="owner">
									<option value=""></option>
									<option value="William">William</option>
									<option value="Eric">Eric</option>
									<option value="OBU">OBU</option>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">倉別：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="wareHouse">
									<option value=""></option>
									<c:forEach var="i" begin="0" step="1" items="${warehouseList}">

										<option value="${i.get(0)}">${i.get(1)}</option>

									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>

<!-- 				<div class="row"> -->
<!-- 					<div class="col-md-8 form-group "> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-2"> -->
<!-- 								<h5> -->
<!-- 									<label for="focusedInput ">櫃位：</label> -->
<!-- 								</h5> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<!-- 								<input class="form-control" id="warehousePositionOne" name="warehousePositionOne" type="text">-<input class="form-control" id="warehousePositionTwo" name="warehousePositionTwo" type="text"> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
<!-- 					<div class="col-md-4 form-group "> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<h5> -->
<!-- 									<label for="focusedInput ">數量：</label> -->
<!-- 								</h5> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<!-- 								<input class="form-control" name="qty" type="text"> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
<!-- 				</div> -->
				
<!-- 				<div class="row"> -->
<!-- 				<div class="col-md-4 form-group "> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<h5> -->
<!-- 									<label for="focusedInput ">價格：</label> -->
<!-- 								</h5> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<!-- 								<input class="form-control" name="price" type="text"> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->


				<br />
				<div class="row text-center">
					<input type="submit" name="" value="搜尋" class="btn-lg btn-success">
				</div>
			</fieldset>
		</form>
	</div>
	<hr />
	
	<div class="container table-responsive bg-warning"
		style="border-radius: 20px">
		<form name="searchform" method="post" action="#"
			style="font-size: 100%; vertical-align: baseline; padding: 15px;"
			class="form-inline container">
			<table
				class="table table-bordered table-hover table-condensed pull-left"
				style="margin: 0 0 0 -15px">
				<thead><c:out value="共找到   筆結果"></c:out></thead>
				<tr class="ListTitle" style="background-color: #A65758; color: #fff">
					<th>項目</th>
					<th>種類</th>
					<th>單號</th>
					<th>SKU</th>
					<th>品名</th>
					
					<th>規格</th>
					<th>顏色</th>
					<th>數量</th>
					<th>成本</th>
					<th>倉別</th>
					
					<th>櫃位</th>
					<th>日期</th>
					<th>廠商</th>
					<th>經手人</th>


				</tr>


				<c:forEach var="i" begin="0" step="1" items="${logList}"
					varStatus="nu">

					<c:if test="${nu.count%2==0}">

						<tr style="background-color: #9DDCD1">
							<td rowspan="2" style="vertical-align: middle"><c:out
									value="${nu.count}"></c:out></td>
									
							<c:forEach var="j" begin="0" end="12" step="1">
								<td><c:out value="${i.get(j)}"></c:out></td>

							</c:forEach>
						</tr>


						<tr style="background-color: #9DDCD1">
							<td colspan="13"><c:out value="${i.get(13)}"></c:out></td>
						</tr>
					</c:if>
					<c:if test="${nu.count%2 !=0}">

						<tr style="background-color: #D4F4D8">
							<td rowspan="2" style="vertical-align: middle"><c:out
									value="${nu.count}"></c:out></td>

							<c:forEach var="j" begin="0" end="12" step="1">
								<td><c:out value="${i.get(j)}"></c:out></td>

							</c:forEach>
						</tr>


						<tr style="background-color: #D4F4D8">
							<td colspan="13"><c:out value="${i.get(13)}"></c:out></td>
						</tr>
					</c:if>
				</c:forEach>

			</table>


		</form>
	</div>
</body>

<%@ include file="../href/footer.jsp"%>

</html>