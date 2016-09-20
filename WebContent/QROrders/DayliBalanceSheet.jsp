<%@page import="tw.iii.qr.order.COrders"%>
<%@page import="java.util.LinkedList"%>
<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<jsp:useBean id="newd" class="tw.iii.qr.order.DayliBalanceSheetFactory"	scope="page" />
	<jsp:useBean id="CProductFactory" class="tw.iii.qr.stock.CProductFactory" scope="page" />
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>日結表</title>
</head>
<body>
<%@ include file = "/href/navbar.jsp"%>

<%
Connection conn = new DataBaseConn().getConn();
LinkedList<COrders> dayliBalanceSheetnew = newd.dayliBalanceSheet(request,response, conn);
request.setAttribute("ndbs", dayliBalanceSheetnew);
%>
 <div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#F3CE9A;" >
      <ul class="nav nav-tabs">
        <li ><a href="SearchOrder.jsp" >訂單管理</a></li>
        <li class="" style="background-color:#A45A21"><a href="DayliBalanceSheet.jsp" style="color:#fff">日結表</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#A45A21;" >
      <ul class="nav nav-tabs">
        <li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
        <li><a href="OrderProcessingPage.jsp?begin=0&end=10" style="color:#fff">處理中</a></li>
        <li><a href="OrderPickupPage.jsp?begin=0&end=10">揀貨中</a></li>
        <li><a href="OrderUploadTrackingCode.jsp?begin=0&end=10">上傳追蹤碼</a></li>
        <li><a href="OrderFinished.jsp?begin=0&end=10">已完成訂單</a></li>
        <li><a href="OrderAbnormal.jsp?begin=0&end=10">異常訂單</a></li>
        <li><a href="ShipmentRecord.jsp?begin=0&end=10" >訂單出貨記錄</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
    <li><a href="DayliBalanceSheet.jsp">日結表</a></li>
  </ol>
</div>
  
<div class="container table-responsive" style="background: #D9A56B; border-radius:20px;">
  <form name="searchform" method="post" action="../StatusDo" class="container"
   style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <label>共有:${ndbs.size()}筆</label>
    <c:forEach var="i" items="${ndbs}" begin="0" step="1" varStatus="check">
    <div class="panel-group" id="accordion">
      <div class="panel panel-default" style="background-color:#E7D29F">
        <div class="panel-heading">
          <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#${check.index}">
            <table class="table table-condensed" >
              <thead>
                <tr>
                  <th>結標日</th>
                  <th>EbayNO.</th>
                  <th>國家</th>
                  <th>E/B 成交價</th>
                  <th>E/B FEE</th>
                  <th>P/P Total</th>
                  <th>P/P FEE</th>
                  <th>P/P NET</th>  <!-- P/P Total - P/P Fee -->
                  <th>進貨成本</th>
                  <th>平台</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>${i.getCOrderMaster().getOrderDate()}</td>
                  <td>${i.getCOrderMaster().getEbayNO()}</td>
                  <td>${i.getCOrderReciever().getCountry()}</td>
                  <td>${i.getCOrderMaster().getEbayPrice()}</td>
                  <td>${i.getCOrderMaster().getEbayFees()}</td>
                  <td>${i.getCOrderMaster().getTotalPrice()}</td>
                  <td>${i.getCOrderMaster().getPaypalFees()}</td>
                  <td>${i.getCOrderMaster().getPaypalNet()}</td>
                  <td>${i.getCOrderMaster().getPurchaseCost()}</td>
                  <td>${i.getCOrderMaster().getEbayAccount()}</td>
                </tr>
                <tr>
                  <td colspan="9"><c:forEach var="j" items="${i.COrderDetail}" begin="0" step="1">
                  <b>${j.getSKU()}</b>&nbsp${j.getProductName()}(SKU/品名)&nbsp數量:${j.getQty()}<br/>
						</c:forEach>
                  </td>
                </tr>
              </tbody>
            </table>
            </a> </h4>
        </div>
        <div id="${check.index}" class="panel-collapse collapse">
          <div class="panel-body">
            <table class="table table-bordered table-hover table-condensed pull-left">
                <tr class="ListTitle">
                  <th>選取</th>
                  <th>結標日</th>
                  <th>訂單編號</th>
                  <th>SKU</th>
                  <th>品名</th>
                  <td>幣別</td>
                  <th>TEL</th>
                  <td>遞交方式</td>
                </tr>
                <tr>
                  <td rowspan="3" style="vertical-align:middle"><input type="checkbox" name="QR_id"
                    value="${i.getCOrderMaster().getQR_id()}" id="${i.getCOrderMaster().getQR_id()}"
                    onchange="enableOrderStatus(this)"></td>
                  <td>${i.getCOrderMaster().getOrderDate()}</td>
                  <td>${i.getCOrderMaster().getQR_id()}</td>
                  <td>${i.getCOrderDetailSingle().getSKU()}</td><!--sku-->
                  <td>${i.getCOrderDetailSingle().getProductName()}</td><!--productName-->
                  <td>${i.getCOrderMaster().getCurrency()}</td>
                  <td>${i.getCOrderGuestInfo().getTel1()}</td>
                  <td>
                    <select name="init" id="${i.getCOrderMaster().getQR_id()}" onchange="autoChecked(this)">
                      <option value="">請選擇</option>
                      <option value="DHL">DHL</option>
                      <option value="Fedex">Fedex</option>
                      <option value="EMS">EMS</option>
                      <option value="AP">AP</option>
                      <option value="RA">RA</option>
                      <option value="USPS(寄倉)">USPS(寄倉)</option>
                      <option value="USPS(集運)">USPS(集運)</option>
                      <option value="Post">Post</option>
                    </select>
                  </td>
                </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
    </c:forEach>
    <div class="row text-center" >
      <button type="submit" name="send" value="dayliBalance" class="btn btn-lg btn-primary"
       >送出</button>
    </div>
  </form>
</div>
<script type="text/javascript">
 	function enableOrderStatus(ele){
 	  var id = ele.value;
	  if (ele.checked) {
		  $(ele).attr("name","QR_id");
	  } else {
		  $(ele).attr("name","init");
		  alert("取消勾選了一筆訂單");
 	  }
   };
   function autoChecked(ele){
 		var id = ele.id ;
 		$("#" + id).prop("checked", true);
 		$(ele).attr("name","logistics");
 		if(ele.value == '請選擇'){
 			$(ele).attr("name","init");
 		}
 };
</script>
</body>
</html>