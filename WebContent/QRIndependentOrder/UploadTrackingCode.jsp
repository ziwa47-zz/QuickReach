<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.IndependentOrder.model.entity.IDPorderAll"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Tracking Code</title>
</head>
<body>
	<%@ include file="/href/navbar.jsp"%>
	<c:if test="${PageCompetence.getOrdersManage() == 0 }">
		<%
			response.sendRedirect("/HomePage.jsp");
		%>
	</c:if>
	<%
	
		request.setAttribute("begin", request.getParameter("begin"));
		request.setAttribute("end", request.getParameter("end"));
	%>

	<div class="nav">
		<div class="container">
			<div class="navbar-left" style="background-color: #3DFF81;">
				<ul class="nav nav-tabs">
					<li class="" style="background-color: #189B30"><a
						href="SearchOrder?begin=0&end=10" style="color: #FFFFFF">獨立出貨</a></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="background-color: #189B30;">
				<ul class="nav nav-tabs">
			        <li><a href="SearchOrder?begin=0&end=10">查詢訂單</a></li>
			        <li><a href="IndependentOrder.jsp?begin=0&end=10">新增訂單</a></li>
			        <li><a href="Processing?begin=0&end=10">處理中</a></li>
			        <li><a href="Pickup?begin=0&end=10">揀貨中</a></li>
			        <li><a href="" style="color:#fff">上傳追蹤碼</a></li>
			        <li><a href="Finished?begin=0&end=10">已完成訂單</a></li>
			        <li><a href="ShipmentRecord?begin=0&end=10">訂單出貨記錄</a></li>
			        <li><a href="refundPage.jsp?begin=0&end=10" >退貨</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchOrder?begin=0&end=10">訂單管理</a></li>
			<li><a href="UploadTrackingCode?begin=0&end=10">上傳追蹤碼</a></li>
		</ol>
	</div>
	<div class="nav">


		<br />
		<div class="container table-responsive bg-warning"
			style="border-radius: 20px">
			<form name="searchform" method="post" action="/QRIndependentOrder/IDPStatusDo"
				class="form-inline container"
				style="font-size: 100%; vertical-align: baseline; padding: 15px;">
				<div class="row">
					<div class="col-md-8 form-group ">
						<div class="row">
							<div class="col-md-2">
								<h5>
									<label>追蹤碼：</label>
								</h5>
							</div>
							<div class="col-md-10">
								<input class="form-control" id="trackingCode"  name="trackingCode" type="text" onchange="trackingCodeValie()" readonly>
							</div>
						</div>
					</div>
				</div>
				<a href="../QRIndependentOrder/UploadTrackingCode"  class="btn btn-lg btn-primary">查詢已出貨訂單</a>
				<button type="submit" name="send" id="sendTrackingCode"  value="sendTrackingCode" class="btn btn-lg btn-primary" disabled>送出追蹤碼</button>
				<ul class="pager pagination">
					<c:choose>
						<c:when test="${begin != 0}">
							<li><a 	href="OrderUploadTrackingCode.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a 	href="OrderUploadTrackingCode.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="0" end="${IDPUploadTrackingCode.size()/10}" step="1"
						varStatus="check">
						<c:choose>
							<c:when test="${(check.index*10) != begin}">
								<li><a 	href="OrderUploadTrackingCode.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
							</c:when>
							<c:otherwise>
								<li class="active"><a href="OrderUploadTrackingCode.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${end < IDPUploadTrackingCode.size()}">
							<li><a
								href="OrderUploadTrackingCode.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a
								href="OrderUploadTrackingCode.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
						</c:otherwise>
					</c:choose>
					<label>共有:${IDPUploadTrackingCode.size()}筆</label>
				</ul>
				<table
					class="table table-bordered table-hover table-condensed pull-left"
					style="margin: 0 0 0 -15px">
					<tr class="ListTitle">
						<th>選取</th>
						<th>訂單編號</th>
						<th>平台</th>
						<th>熟客ID</th>
						<th>付款日期</th>
						<th>物流</th>
						<th>訂單狀態</th>
						<th>總金額</th>
						<th>使用者</th>
					</tr>
					<c:forEach var="i" items="${IDPUploadTrackingCode}" begin="${begin}" end="${end}"
						step="1" varStatus="check">
						<c:choose>
							<c:when test="${check.index%2 != 0}">
								<tr style="background-color: #D4F4D8">
									<td rowspan="3" style="vertical-align: middle"><input
										type="checkbox" name="QR_id"
										value="${i.getIordersMaster().getQrId()}"
										onblur="preventDoubleOrder(this)"></td>
									<td><a

										href="OrderDetail?QR_id=${i.getIordersMaster().getQrId()}"><img
											src="../img/compose-4.png"></a></td>

									

									<td>${i.getIordersMaster().getPlatform()}</td>
									<td>${i.getIordersMaster().getGuestId()}</td>
									<td>${i.getIordersMaster().getPayDate()}</td>
									<td>${i.getIordersMaster().getLogistics()}
									<input 	type="hidden" name="${i.getIordersMaster().getQrId()}"	value="${i.getIordersMaster().getLogistics()}">
									<input		type="hidden" name="logistics"		value="${i.getIordersMaster().getLogistics()}">
									</td>
									<td>${i.getIordersMaster().getOrderStatus()}
									<input	 type="hidden" name="status"	value="${i.getIordersMaster().getOrderStatus()}"></td>
									<td>${i.getIordersMaster().getTotalPrice()}${i.getIordersMaster().getCurrency()}</td>
									<td>${i.getIordersMaster().getStaffName()}</td>
								</tr>
								<tr style="background-color: #D4F4D8">
									<td colspan="9"><c:forEach var="j"	items="${i.getIordersDetails()}" begin="0" step="1" varStatus="check">
											<b><a	href="../QRProduct/StockDetail.jsp?sku=${j.getSku()}">${j.getSku()}</a></b>${j.getProductName()}<br />
										</c:forEach></td>
									<td colspan="3"><c:forEach var="k"	items="${i.getIordersDetails()}" begin="0" step="1"	varStatus="check">
											<b>${k.getWarehouse()}</b>(倉別)<br />
										</c:forEach></td>
								</tr>
							
							</c:when>
							<c:otherwise>
								<tr>
									<td rowspan="3" style="vertical-align: middle">
									<input 	type="checkbox" name="QR_id"	value="${i.getIordersMaster().getQrId()}"	onchange="preventDoubleOrder(this)"></td>

									<td><a	href="OrderDetail?QR_id=${i.getIordersMaster().getQrId()}">
									<img	src="../img/compose-4.png"></a></td>

									

									<td>${i.getIordersMaster().getPlatform()}</td>
									<td>${i.getIordersMaster().getGuestId()}</td>
									<td>${i.getIordersMaster().getPayDate()}</td>
									<td>${i.getIordersMaster().getLogistics()}
									<input 	type="hidden" name="${i.getIordersMaster().getQrId()}"	value="${i.getIordersMaster().getLogistics()}">
									</td>
									<td>${i.getIordersMaster().getOrderStatus()}
									<input	 type="hidden" name="status" value="${i.getIordersMaster().getOrderStatus()}"></td>
									<td>${i.getIordersMaster().getTotalPrice()}${i.getIordersMaster().getCurrency()}</td>
									<td>${i.getIordersMaster().getStaffName()}</td>
								</tr>
								<tr>
									<td colspan="6"><c:forEach var="j" items="${i.getIordersDetails()}" begin="0" step="1" varStatus="check">
											<b><a 	href="../QRProduct/StockDetail.jsp?sku=${j.getSku()}">${j.getSku()}</a></b>${j.getProductName()}<br />
										</c:forEach></td>
									<td colspan="3"><c:forEach var="k" 	items="${i.getIordersDetails()}" begin="0" step="1" varStatus="check">
											<b>${k.getWarehouse()}</b>(倉別)<br />
										</c:forEach></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
				<div class="row text-center"></div>
			</form>
		</div>
	</div>
	<%@ include file="../href/footer.jsp"%>
	<script type="text/javascript">

	
		var whichLogistics = "";
		function preventDoubleOrder(ele) {
			var id = ele.value;
			if (ele.checked) {
				$("input[name=QR_id]").prop("disabled", true);
				$("#trackingCode").prop("readonly",false)
				$(ele).prop("disabled", false);
				whichLogistics = $("input[name=" + id + "]").val();
				alert('接著輸入追蹤碼--' + whichLogistics);
			} else {
				$("input[name=QR_id]").prop("disabled", false);
				$("#trackingCode").prop("readonly",true)
				$("#sendTrackingCodeSandbox").prop('disabled', true);
				$("#sendTrackingCode").prop('disabled', true);
				
			}
		};

		function trackingCodeValie() {

			var checkTrackingCode = $("#trackingCode").val();
		//	alert(checkTrackingCode)
		
		var front = checkTrackingCode.substring(0, 2);
				var tw = checkTrackingCode.slice(-2);
				var nu = checkTrackingCode.substring(2,11);

		
			switch (whichLogistics) {

			
			case "EMS":
								
				if ((front == "EE") && (tw == "TW")&& (!isNaN(nu)) && checkTrackingCode.length == 13) {
				//	alert("驗證成功" + ee + tw + "\n" + checkTrackingCode);
					$("#sendTrackingCodeSandbox").prop('disabled', false);
					$("#sendTrackingCode").prop('disabled', false);
				} else {

					$("#sendTrackingCodeSandbox").prop('disabled', true);
					$("#sendTrackingCode").prop('disabled', true);
					alert("須為EE開頭TW結尾\n ex.EE745879345TW\n當前輸入:"
							+ checkTrackingCode);
					//$("input[name=trackingCode]").focus();
				}

				break;
			case "AP":

				

				if ((front == "CC") && (tw == "TW")&& !isNaN(nu) && checkTrackingCode.length == 13) {
				//	alert("驗證成功" + cc + "," + tw + "\n" + checkTrackingCode);
					$("#sendTrackingCodeSandbox").prop('disabled', false);
					$("#sendTrackingCode").prop('disabled', false);
				} else {
					$("#sendTrackingCodeSandbox").prop('disabled', true);
					$("#sendTrackingCode").prop('disabled', true);
					alert("須為CC開頭TW結尾\n ex.CC290035010TW\n當前輸入:" + checkTrackingCode);
					//$("input[name=trackingCode]").focus();
				}

				//alert("ex.CC290035010TW");
				break;
			case "RA":
				//郵局

				if ((front == "RA") && (tw == "TW")&& !isNaN(nu) && checkTrackingCode.length == 13) {
					alert("驗證成功" + ra + "," + tw + "\n" + checkTrackingCode);
					$("#sendTrackingCodeSandbox").prop('disabled', false);
					$("#sendTrackingCode").prop('disabled', false);
				} else {
					$("#sendTrackingCodeSandbox").prop('disabled', true);
					$("#sendTrackingCode").prop('disabled', true);
					alert("須為RA開頭TW結尾 \n ex.RA042574668TW \n當前輸入:" + checkTrackingCode);
					//$("input[name=trackingCode]").focus();
				}

				break;
			case "DHL":
				if (!(isNaN(checkTrackingCode))
						&& checkTrackingCode.length == 10) {
					$("#sendTrackingCodeSandbox").prop('disabled', false);
					$("#sendTrackingCode").prop('disabled', false);
				/*	alert("驗證成功" + "checkTrackingCode.length="
							+ checkTrackingCode.length + "\n"
							+ checkTrackingCode);*/
				} else {
					$("#sendTrackingCodeSandbox").prop('disabled', true);
					$("#sendTrackingCode").prop('disabled', true);
					alert("須輸入10碼數字 \n ex.7388710921 \n當前輸入:" + checkTrackingCode);
				}

				break;
// 			case "USPS(寄倉)":
// 				if ((!isNaN(checkTrackingCode))
// 						&& checkTrackingCode.length == 22) {
// 					$("#sendTrackingCodeSandbox").prop('disabled', false);
// 					$("#sendTrackingCode").prop('disabled', false);
// // 					alert("ok,checkTrackingCode.length="
// // 							+ checkTrackingCode.length + "\n"
// // 							+ checkTrackingCode);
// 				} else {
// 					$("#sendTrackingCodeSandbox").prop('disabled', true);
// 					$("#sendTrackingCode").prop('disabled', true);
// 					alert("須輸入22碼數字 \n ex.9405510298370033498220 \n當前輸入:" + checkTrackingCode);
// 				}

// 				break;
// 			case "USPS(集運)":
// 				if ((!isNaN(checkTrackingCode))
// 						&& checkTrackingCode.length == 22) {
// 					$("#sendTrackingCodeSandbox").prop('disabled', false);
// 					$("#sendTrackingCode").prop('disabled', false);
// 					alert("ok,checkTrackingCode.length="
// 							+ checkTrackingCode.length + "\n"
// 							+ checkTrackingCode);
// 				} else {
// 					$("#sendTrackingCodeSandbox").prop('disabled', true);
// 					$("#sendTrackingCode").prop('disabled', true);
// 					alert("須輸入22碼數字 \n ex.9405510298370033498220 \n當前輸入:" + checkTrackingCode);
					
// 				}

// 				break;
				
			case "Fedex":

				//alert("ex.尚未給格式" + checkTrackingCode);
				$("#sendTrackingCodeSandbox").prop('disabled', false);
				$("#sendTrackingCode").prop('disabled', false);
				break;
		
			default:
				$("#sendTrackingCodeSandbox").prop('disabled', false);
				$("#sendTrackingCode").prop('disabled', false);
				alert("日結表的物流選單無此物流選項" + "\n" + checkTrackingCode);

			}

		}
	</script>
</body>
</html>