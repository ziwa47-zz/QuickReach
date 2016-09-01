<%@page import="tw.iii.qr.order.COrders"%>
<%@page import="java.util.LinkedList"%>
<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<jsp:useBean id="DBSF" class="tw.iii.qr.order.DayliBalanceSheetFactory"	scope="page" />
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>查詢訂單</title>
</head>
<body>
<%@ include file = "../href/navbar.jsp"%>

<%
Connection conn = new DataBaseConn().getConn();
LinkedList<COrders> dayliBalanceSheet = DBSF.dayliBalanceSheet(request,response, conn);
request.setAttribute("dbs", dayliBalanceSheet);
%>
  <div class="nav">
  	<div class="container">
    	<div class="navbar-left" style="background-color:#F3CE9A;" >
        	<ul class="nav nav-tabs">
              <li><a href="SearchOrder.jsp">訂單管理</a></li>
              <li style="background-color:#A45A21;"><a href="DayliBalanceSheet.jsp" style="color:#FFFFFF">日結表</a></li>
              <li><a href="SearchComment.jsp">查詢評價</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#A45A21;" >
        	<ul class="nav nav-tabs" id="test" >
              <li><a href="SearchOrder.jsp">查詢訂單</a></li>
              <li><a href="OrderProcessingPage.jsp">處理中</a></li>
              <li><a href="OrderPickupPage.jsp">揀貨中</a></li>
              <li><a href="OrderUploadTrackingCode.jsp">上傳追蹤碼</a></li>
              <li><a href="OrderFinished.jsp">已完成訂單</a></li>
              <li><a href="OrderAbnormal.jsp">異常訂單</a></li>
            </ul>
        </div>
    </div>
  </div>



  <div class="container container-fluid breadcrumbBox">
	<ol class="breadcrumb">
		<li><a href="../QRMain/HomePage.jsp">首頁</a></li>
		<li class="active" style="display:"><a href="SearchOrder.jsp">訂單</a></li>
		<li><a href="DayliBalanceSheet.jsp">日結表</a></li>
	</ol>
  </div>
  
<div class="container table-responsive bg-warning" style=" border-radius:20px" id="test2">
  <form name="searchform" method="post" action="../StatusDo" class="form-inline container"
   style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
  <button type="submit" name="" class="btn-sm btn-info">選擇全部</button>
      <button type="submit" name="" class="btn-sm btn-info">清除勾選</button>
      <button type="submit" name="" class="btn-sm btn-info">列印撿貨/出貨單</button>
      <button type="submit" name="" class="btn-sm btn-info">列印EMS</button>
      <button type="submit" name="" class="btn-sm btn-info">列印Invoice</button>
      <button type="submit" name="" class="btn-sm btn-info">回復</button>
      <label>共有:${SearchOrdersResult.size()}筆</label>
    <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
    <c:forEach var="i" items="${dbs}" begin="0" step="1">
    <tr class="ListTitle">
        <th>選取</th>
        <th>結標日</th>
        <th>訂單編號</th>
        <th colspan="2">SKU</th>
        <th colspan="7">品名</th>
        <th>TEL</th>
        <th>運費(USD)</th>
        <th>包材(NTD)</th>
        <th>REMARK</th>
        <th>商品持有人</th>
        <th>貨重</th>
      </tr>
         
      <tr>
        <td rowspan="3" style="vertical-align:middle"><input type="checkbox" name="orderId" value="${i.getCOrderMaster().getOrder_id()}"></td>
        <td>${i.getCOrderMaster().getOrderDate()}</td>
        <td>${i.getCOrderMaster().getOrder_id()}</td>
        <td colspan="2"></td><!--sku-->
        <td colspan="7"><!--productName--></td>
		<td>${i.getCOrderGuestInfo().getTel1()}</td>
        <td>${i.getCOrderMaster().getShippingFees()}</td>
        <td>${i.getCOrderMaster().getPackageFees()}</td>
        <td>${i.getCOrderMaster().getComment()}</td>
        <td><!--sku--></td>
        <td>${i.getCOrderMaster().getTotalWeight()}</td>
      </tr>
 	  <tr class="ListTitle">
        <td>E/B NO.</td>
        <td>E/B ITEM NO.</td>
        <td>數量</td>
        <td>E/B ID</td>
        <td>國家</td>
        <td>幣別</td>
        <td>E/B 成交價</td>
        <td>E/B 含運價</td>
        <td>P/P Date</td>
        <td>P/P PAYMENT ID</td>
        <td>P/P TOTAL</td>
        <td>P/P FEE</td>
        <td>P/P NET</td>
        <td>進貨成本 NTD</td>
        <td>寄件日</td>
        <td>遞交方式</td>
        <td>EBAYFEE (US)</td>
        
      </tr>
      <tr>
      <% //sku  品茗 without tracking code %>
        <td>${i.getCOrderMaster().getEbayNO()}</td>
        <td>${i.getCOrderMaster().getEbayItemNO()}</td>
        <td><!--qty--></td>
        <td>${i.getCOrderMaster().getEbayAccount()}</td>
        <td>${i.getCOrderGuestInfo().getCountry()}</td>
        <td>${i.getCOrderMaster().getCurrency()}</td>
        <td>${i.getCOrderMaster().getEbayPrice()}</td>
        <td>${i.getCOrderMaster().getEbayTotal()}</td>
        <td>${i.getCOrderMaster().getPayDate()}</td>
        <td>${i.getCOrderMaster().getPaypalmentId()}</td>
        <td>${i.getCOrderMaster().getPaypalTotal()}</td>
        <td>${i.getCOrderMaster().getPaypalFees()}</td>
        <td>${i.getCOrderMaster().getPaypalNet()}</td>
        <td><!--price--></td>
        <td>${i.getCOrderMaster().getShippingDate()}</td>
        <td>
        <select name="logistics" onchange="selectLogistics(this)">
          <option>DHL</option>
          <option>Fedex</option>
          <option>Post</option>
        </select>
        </td>
        <td>${i.getCOrderMaster().getEbayFees()}</td>
        
      </tr>
    </c:forEach>  
            
      
    </table>
    
        <div class="row text-center" >     
				<button type="submit" name="send" value="dayliBalance" class="btn-lg btn-primary ">送出</button>
				
			</div>
  </form>
</div>
<script type="text/javascript">
$( document ).ready(function() {
	//select all
	//$("input[name=orderId]").prop("checked", true);
});


</script>
</body>
</html>