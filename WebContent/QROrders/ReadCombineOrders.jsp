<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.DataBaseConn"%>
<%@ page import="tw.iii.qr.order.DTO.*"%>
<%@ page import="tw.iii.qr.order.*"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>

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

	<script type="text/javascript">
	function onSelectGuest(){
		
		$.ajax({
			type:"GET",
			url : "../AjaxServletGuestAccount",
			data: {
				DATECOUNT: $( "input[type=radio]:checked" ).val()
			},
			success : function(result) {
				$("#guestAccount").html(result);
				}
			});
	}
	function onCombineOrder(){
		$.ajax({
			type:"GET",
			url : "../AjaxServletCombineOrder",
			data: {
				CQRID: $("#guestAccount :selected").val().split(":")[0]
			},
			success : function(result) {
				$("#orderDetail").html(result);
				}
			});
	}
	
	function selectDays(){
		var text = $( "input[type=radio]:checked" ).val();
	    alert(text);
	}
		/*function selectDays(){
		    $("#select").click(function() {
		    	$("input[type=radio]:checked").each(function(){
		        	$("#test").empty();
					var number = $(this).attr("id");
		            var text = $("label[for="+number+"]").text();
		            $("#test").append("<li><a href='#'>" + text + "</a></li>");
		        });
			});
		}	*/
	</script>
				
		

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
		<div class="container table-responsive bg-warning" style="border-radius: 20px">
			<div class="row">
				<div class="col-md-8 form-group ">
					<div class="row">
						<div class="col-md-2">
							<h5><label>有效期限：</label></h5>
						</div>
						<div class="col-md-4">
							<div class="form-control" id="validDays" >
								<label class="radio-inline" for="7"><input type="radio" name="optradio"  id="7" value="7" onchange="onSelectGuest()">7</label>
								<label class="radio-inline" for="14"><input type="radio" name="optradio"  id="14" value="14" onchange="onSelectGuest()">14</label>
								<label class="radio-inline" for="all"><input type="radio" name="optradio"  id="all" value="-1" onchange="onSelectGuest()">all</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">
							<h5><label>客戶帳號：</label></h5>
						</div>
						<div class="col-md-4">
							<select multiple class="form-control" id="guestAccount" onchange="onCombineOrder()">
							  <option>1</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</select>
						</div>
					</div>
					<div class="row">
					  <h2>來個表格標題吧</h2>
					  <p>可以輸入附標</p>
					  <table class="table">
					    <thead>
					      <tr>
					        <th>m_cqrid</th>
					        <th>d_qrid</th>
					        <th>d_ebayno</th>
					        <th>guestAccount</th>
					        <th>combineDate</th>
					      </tr>
					    </thead>
					    <tbody id="orderDetail">
					      <tr>
					        <td>Default</td>
					        <td>Defaultson</td>
					        <td>def@somemail.com</td>
					      </tr>
					    </tbody>
					  </table>
					</div>		
				</div>
			</div>
		</div>
	</div>
			
	
	
<div id="test"></div>


	<%@ include file="../href/footer.jsp"%>


</body>
</html>
