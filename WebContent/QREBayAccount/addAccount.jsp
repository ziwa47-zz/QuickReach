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
<!-- Bootstrap -->
<link href="./css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="./css/smoothness/jquery-ui.css">

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="./js/bootstrap.js"></script>


<script src="./js/jquery-1.12.4.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.ui.datepicker-zh-TW.js"></script>

</head>
<body>
	<%@include file="../href/navbar.jsp"%>
	<div class="nav">
		<div class="container">
			<div class="navbar-left" style="background-color: #BCF1E5;">
				<ul class="nav nav-tabs">
					<li><a href="eBayAccount.jsp" style="color: #000000">eBay帳號管理</a></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="background-color: #1CAF9A;">
				<ul class="nav nav-tabs">
					<li><a href="eBayAccount.jsp" style="color: #000000">帳號列表</a></li>
					<!--               <li class="" style="background-color:#1CAF9A"><a href="purchasePage.jsp" style="color:#FFFFFF">進貨</a></li> -->
					<li><a href="addAccount.jsp" style="color: #000000">新增eBay帳號</a></li>
				</ul>
			</div>
		</div>

	</div>

	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="../QRMain/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a href="eBayAccount.jsp">eBay帳號管理</a></li>
			<li><a href="eBayAccount.jsp">新增eBay帳號</a></li>
		</ol>


	<h2>新增eBay帳號</h2>
	<form action="../EbayAccountDo" method="post">
		

		<h2 class="">ebay 帳號</h2>
		<div class="">
			<table border="1" align="center">

				<tbody>
					<tr>
						<th class=""><span> * </span>ebay ID</th>
						<td class=""><input id="id_fd-name" type="text" name="ebayId" value=""
							class=""  maxlength="50" ></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class=""><span > * </span>ebay Token</th>
						<td class=""><textarea id="id_fd-token" rows="5"
								cols="85" name="ebayToken" value=""></textarea></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class=""><span > * </span>end Token</th>
						<td class=""><textarea id="id_fd-token" rows="5"
								cols="85" name="endToken" value=""></textarea></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class=""><span class="red"> * </span>paypal
							帳號(E-mail)</th>
						<td class=""><input id="id_fd-email" type="text" name="paypalAccount" value=""
							class="" ></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class="">對應公司</th>
						<td class=""><select name="correspondCompany"
							id="id_fd-company_id"> 
								<option value="HUANG PO-WEI">HUANG PO-WEI</option>
								<option value="YU CHIN WU">YU CHIN WU</option>
								<option value="WHIRLWIND SPEED LIMITED">WHIRLWIND SPEED LIMITED</option>
						</select></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class=""><span> * </span>startTime</th>
						<td class=""><input id="id_fd-name" type="text" name="startTime" value=""
							class=""  maxlength="50"></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class=""><span> * </span>lastFixTime</th>
						<td class=""><input id="id_fd-name" type="text" name="lastFixTime" value=""
							class=""  maxlength="50"></td>
						<td class=""></td>
					</tr>
					
					<tr>
						<th class=""><span> * </span>status</th>
						<td class=""><input id="id_fd-name" type="text" name="status" value=""
							class=""  maxlength="50" name="ebayId" value=""></td>
						<td class=""></td>
					</tr>
					
					<tr>
						<th class="">Comment</th>
						<td class=""><textarea id="id_fd-feedback_text" rows="5" name="comment" value=""
								cols="85" ></textarea></td>
						<td class=""></td>
					</tr>


					<tr>
						<th class="">系統通知信(Feedback給客戶)</th>
						<td class=""><textarea id="id_fd-feedback_message"
								rows="25" cols="95" name="systemFeedback"></textarea></td>
						<td class=""></td>
					</tr>

				</tbody>
			</table>
		</div>


		<div class="" align="center">
			<input type="submit" name="submit" value="newAccount"> <input type="button" value="取消">
		</div>
		<input type="hidden" name="id" value="">
	</form>
		</div>

</body>
</html>