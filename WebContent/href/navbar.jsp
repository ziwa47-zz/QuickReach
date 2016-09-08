<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nav</title>
<!-- local file -->
<!-- <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"> -->

<!-- <script src="../js/jquery-1.12.4.min.js"></script> -->
<!-- <script src="../js/bootstrap.min.js"></script> -->
<!-- <script src="../js/jquery-ui.min.js"></script> -->
<!-- <script src="../js/jquery.ui.datepicker-zh-TW.js"></script> -->

<link rel="stylesheet" href="../css/custom.css" rel="stylesheet">
<!-- downloaded -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

  
  <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.js"></script>
  <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
  <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js"></script>
  <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.min.js"></script>
  
 


<script type="text/javascript">
	$(function() {
		//日期選擇器
		$(".yymmdd").datepicker({
			dateFormat : 'yymmdd',
			
		});
		
		$("input[name=dateMin]").datepicker({
			dateFormat : 'yymmdd',
		});
		$("input[name=dateMax]").datepicker({
			dateFormat : 'yymmdd',

		});
		 $("input[name=date1]").datepicker({dateFormat : 'yy/mm/dd'});
	        $("input[name=date2]").datepicker({dateFormat : 'yy/mm/dd'});
	        $("input[name=payDateMin]").datepicker({dateFormat : 'yy/mm/dd'});
	        $("input[name=payDateMax]").datepicker({dateFormat : 'yy/mm/dd'});
	        $("input[name=shippingDateMin]").datepicker({dateFormat : 'yy/mm/dd'});
	        $("input[name=shippingDateMax]").datepicker({dateFormat : 'yy/mm/dd'});
	   });
	

	$(function() {
		//feildset prop enable
		$("#optionsRadios1").click(function() {
			$("#myfields").prop("disabled", false);
		});
		$("#optionsRadios2").click(function() {
			$("#myfields").prop("disabled", true);
		});
	});
	
	    function checkAllOrders(ele) {
    	//select all
		if (ele.checked) {
			$("input[name=all]").prop("checked", true);
	        $("input[name=waitProcess]").prop("checked", true);
	        $("input[name=processing]").prop("checked", true);
	        $("input[name=pickup]").prop("checked", true);
	        $("input[name=finished]").prop("checked", true);
	        $("input[name=refund]").prop("checked", true);
	        $("input[name=oothers]").prop("checked", true);
	        $("input[name=deducted]").prop("checked", true);
	    } else {
	    	$("input[name=all]").prop("checked", false);
	        $("input[name=waitProcess]").prop("checked", false);
	        $("input[name=processing]").prop("checked", false);
	        $("input[name=pickup]").prop("checked", false);
	        $("input[name=finished]").prop("checked", false);
	        $("input[name=refund]").prop("checked", false);
	        $("input[name=oothers]").prop("checked", false);
	        $("input[name=deducted]").prop("checked", false);
	    }
};
	$(function () {
	$("button[name=send]").click(function() {
		confirm("確認是否送出訂單");
	});
});
</script>
</head>
<body>
<%
// String ac =(String)session.getAttribute("account");
// if(ac==null || "".equals(ac)){
// 	if("0".equals(request.getParameter("p"))){
		
// 	}else{
// 		response.sendRedirect("/Login.jsp?p=0");
// 	}
	
// }

%>

	<nav class="navbar" style="background-color: #000000">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/HomePage.jsp">QuickReach</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">

				<li><a href="/QRProduct/SearchStockPage.jsp">商品/庫存 </a></li>
				<li><a href="/QROrders/SearchOrder.jsp">訂單資訊</a></li>
				<li><a href="/QREBayAccount/eBayAccount.jsp">Ebay帳號管理</a></li>
				<li><a href="/QREmployee/Account.jsp">員工管理</a></li>
				<li><a href="/QRAccess/Competence.jsp">權限管理</a></li>
			</ul>

			<ul class="nav navbar-right">
				<c:if test="${account != null}">
					<li><a href="/Login.jsp">${staffName}${'('}${account}${')'}<span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</c:if>	
				<c:if test="${account == null}">
					<li><a href="/Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</c:if>	
			</ul>
		</div>
	</div>
	</nav>
</body>
</html>