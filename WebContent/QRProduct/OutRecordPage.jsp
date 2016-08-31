<%@page import="tw.iii.purchase.purchaseFactory"%>
<%@page import="com.mysql.fabric.xmlrpc.base.Param"%>
<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest"%>
<jsp:useBean id="searchDetail" class="tw.iii.purchase.purchaseFactory"
	scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shipping Record</title>
</head>
<body>
	<%@ include file="../href/navbar.jsp"%>
	<%
		LinkedList<LinkedList<String>> warehouseList = searchDetail.warehouseSelectOption();
		LinkedList<LinkedList<String>> companyList = searchDetail.companySelectOption();
		request.setAttribute("warehouseList", warehouseList);
		request.setAttribute("companyList", companyList);
		String date = request.getParameter("date");
		String outRecord = request.getParameter("outRecordId");

		String sku = request.getParameter("sku");
		String pname = request.getParameter("pName");

		String companyName = request.getParameter("companyName");
		String owner = request.getParameter("owner");
		String wareHouse = request.getParameter("wareHouse");

		String warehousePosition = request.getParameter("warehousePosition");
		String qty = request.getParameter("qty");
		String price = request.getParameter("price");

		Connection conn = new DataBaseConn().getConn();

		LinkedList<LinkedList<String>> allList = searchDetail.SearchOutRecord(conn, date, outRecord, pname, sku,
				companyName, owner, wareHouse, warehousePosition, qty, price);

		request.setAttribute("logList", allList);
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
					<li><a href="SearchStockPage.jsp">查詢庫存</a></li>
					<li><a href="PurchasePage.jsp">進貨</a></li>
					<li><a href="searchPurchase.jsp">進貨紀錄</a></li>
					<li><a href="searchOutRecordPage.jsp" style="color: #fff">出貨紀錄</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="../QRMain/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchStockPage.jsp">庫存/商品管理</a></li>
			<li><a href="searchOutRecordPage.jsp">出貨查詢</a></li>
		</ol>
	</div>


	<div class="container"
		style="background: #9DDCD1; border-radius: 20px;">
		<form name="searchform" method="post" action="OutRecordPage.jsp"
			style="font-size: 100%; vertical-align: baseline; padding: 15px;"
			class="form-inline container">
			<fieldset>
				<legend>出貨紀錄查詢</legend>
				<input type="hidden">

				<div class="row">
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">日期：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" type="text" name="date">
							</div>
						</div>
					</div>

					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">單號：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" type="text" name="outRecordId">
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
								<input class="form-control" name="sku" type="text" value="">
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
								<input class="form-control" name="pName" type="text" value="">
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

				<div class="row">
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">櫃位：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="warehousePosition" type="text">
							</div>
						</div>
					</div>
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">數量：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="qty" type="text">
							</div>
						</div>
					</div>
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">價格：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="price" type="text">
							</div>
						</div>
					</div>
				</div>

				<br />
				<div class="row text-center">
					<input type="submit" name="" value="搜尋" class="btn-lg btn-success">
				</div>
			</fieldset>
		</form>
	</div>
	<hr />

	<div class="container table-responsive bg-warning"
		style="border-radius: 20px;">
		<form name="searchform" method="post" action="#"
			style="font-size: 100%; vertical-align: baseline; padding: 15px;"
			class="form-inline container">
			<table
				class="table table-bordered table-hover table-condensed pull-left"
				style="margin: 0 0 0 -15px">
				<tr class="ListTitle" style="background-color: #A65758; color: #fff">
					<th>選取</th>
					<th>項目</th>
					<th>SKU</th>
					<th>品名</th>
					<th>出貨單號</th>

					<th>規格</th>
					<th>顏色</th>
					<th>數量</th>
					<th>成本</th>
					<th>倉別</th>

					<th>櫃位</th>
					<th>日期</th>
					<th>廠商</th>
					<!-- 14 -->
					<th>經手人</th>
				</tr>


				<c:forEach var="i" begin="0" step="1" items="${logList}"
					varStatus="nu">
					<c:if test="${nu.count%2==0}">

						<tr style="background-color: #9DDCD1">
							<td rowspan="2" style="vertical-align: middle"><input
								type="checkbox"></td>
							<td rowspan="2" style="vertical-align: middle"><c:out
									value="${nu.count}"></c:out></td>

							<c:forEach var="j" begin="0" end="12" step="1">
								<td><c:out value="${i.get(j)}"></c:out></td>

							</c:forEach>
						</tr>


						<tr style="background-color: #9DDCD1">
							<td colspan="12"><c:out value="${i.get(12)}"></c:out></td>
						</tr>
					</c:if>
					<c:if test="${nu.count%2 !=0}">
						<tr style="background-color: #D4F4D8">
							<td rowspan="2" style="vertical-align: middle"><input
								type="checkbox"></td>


							<td rowspan="2" style="vertical-align: middle"><c:out
									value="${nu.count}"></c:out></td>

							<c:forEach var="j" begin="0" end="11" step="1">
								<td><c:out value="${i.get(j)}"></c:out></td>

							</c:forEach>
						</tr>


						<tr style="background-color: #D4F4D8">
							<td colspan="12"><c:out value="${i.get(12)}"></c:out></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</form>
	</div>

</body>

<%@ include file="../href/footer.jsp"%>




</body>
</html>