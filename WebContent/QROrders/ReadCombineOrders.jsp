<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.DataBaseConn"%>
<%@ page import="tw.iii.qr.order.DTO.*"%>
<%@ page import="tw.iii.qr.order.*"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<jsp:useBean id="CombineOrder"
	class="tw.iii.qr.order.DTO.COrderCombineFactory" scope="page" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合併訂單</title>
</head>
<body>
	<%@ include file="/href/navbar.jsp"%>
	<c:if test="${PageCompetence.getOrdersManage() == 0 }">
		<%
			response.sendRedirect("/HomePage.jsp");
		%>
	</c:if>
	<%
		Connection conn = new DataBaseConn().getConn();
		LinkedList<GuestAccountAndOrder> list = CombineOrder.HasCombineOrderGuest();

		session.setAttribute("list", list);
	%>

	<div class="nav">
		<div class="container">
			<div class="navbar-left" style="background-color: #F3CE9A;">
				<ul class="nav nav-tabs">
					<li class="" style="background-color: #A45A21"><a
						href="SearchOrder.jsp?begin=0&end=10" style="color: #FFFFFF">訂單管理</a></li>
					<c:if test="${PageCompetence.getEntireOrders() == 1 }">
						<li><a href="DayliBalanceSheet.jsp">日結表</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="background-color: #A45A21;">
				<ul class="nav nav-tabs">
					<li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
					<li><a href="OrderCombine.jsp">合併訂單</a></li>
					<li><a href="OrderProcessingPage.jsp?begin=0&end=10">處理中</a></li>
					<li><a href="OrderPickupPage.jsp?begin=0&end=10">揀貨中</a></li>
					<li><a href="OrderUploadTrackingCode.jsp?begin=0&end=10"
						style="color: #fff">上傳追蹤碼</a></li>
					<li><a href="OrderFinished.jsp?begin=0&end=10">已完成訂單</a></li>
					<li><a href="ShipmentRecord.jsp?begin=0&end=10">訂單出貨記錄</a></li>
					<li><a href="refundPage.jsp?begin=0&end=10">退貨</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
			<li><a href="OrderCombine.jsp">合併訂單</a></li>
		</ol>
	</div>
	<div class="nav">
		<br />
		<div class="container table-responsive bg-warning"
			style="border-radius: 20px">
			<form name="searchform" method="post" action="../OrdersServlet"
				class="form-inline container"
				style="font-size: 100%; vertical-align: baseline; padding: 15px;">
				<button class="btn btn-sm btn-info" type="submit" name="submit"
					value="GoOrderCombine">回到合併訂單</button>
				<br /> 
				<select id="select" class="styled-select"
					onchange="onSelectGuest()">
					<c:forEach var="i" items="${list}" step="1" varStatus="check">
						<option value="${i.getOrder()}">${i.getOrder()}--${i.getGuestAccount()}</option>
					</c:forEach>
				</select>

				
				
			</form>
		</div>
	</div>


<script type="text/javascript">
				function onSelectGuest(){
					$("#select").on("change", function() {
						$.ajax({
							url : "/AjaxGetGuestAccount",
							
							success : function(result) {
								$("#theList").html(result);
							}
						});
					});
				}
				</script>
	<%@ include file="../href/footer.jsp"%>


</body>
</html>
