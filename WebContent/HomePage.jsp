<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QuickReach</title>

</head>
<body>



<%@ include file="/href/navbar.jsp"%>
<div class="container" style="background-color:#F3CE9A;">
  <h2>歡迎使用QuickReach系統</h2>
  <p>請開始您的作業</p>
  <ul class="nav nav-tabs" style="font-size:24px;">
    <li class="active"><a href="#home">商品管理</a></li>
    <li><a href="#menu1">訂單資訊</a></li>
    <li><a href="#menu2"><img src="img/store-2.png" width="24" height="24">帳號管理</a></li>
    <li><a href="#menu3"><img src="img/users.png" width="24" height="24">員工管理</a></li>
    <li><a href="#menu4"><img src="img/compose-3.png" width="24" height="24">權限管理</a></li>
    <li><a href="#menu5">獲取訂單</a></li>
  </ul>	

  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <h3>商品管理</h3>
      <div class="row" style=" padding:10px 10px 10px 10px">
        <div class="col-md-4" style="font-size:36px;"> <a href="/QRProduct/SearchStockPage.jsp" ><img src="/img/database.png">商品管理</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QRProduct/SearchStockPage.jsp">查詢庫存</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QRProduct/PurchasePage.jsp">進貨</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QRProduct/PurchaseRecordPage.jsp">查詢庫存紀錄</a> </div>
        <hr/>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QRProduct/SearchProductPage.jsp">查詢商品</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QRProduct/NewProduct.jsp">新增單項商品</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QRProduct/ProductAddPage.jsp">新增組合商品</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QRProduct/SearchProductPage.jsp">組合商品</a> </div>
      </div>
    </div>
    <div id="menu1" class="tab-pane fade">
      <h3>訂單資訊</h3>
      <div class="row" style="font-size:36px; padding:10px 10px 10px 10px">
        <div class="col-md-4"> <a href="/QROrders/SearchOrder.jsp" ><img src="img/clipboard-2.png">訂單資訊</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QROrders/SearchOrder.jsp">查詢訂單</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QROrders/DayliBalanceSheet.jsp">日結表</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QROrders/ShipmentRecord.jsp">訂單出貨紀錄</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QROrders/OrderAbnormal.jsp">異常</a> </div>
        <hr/>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QROrders/OrderProcessingPage.jsp">處理中</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QROrders/OrderPickupPage.jsp">揀貨中</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QROrders/OrderUploadTrackingCode.jsp">上傳追蹤碼</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QROrders/OrderFinished.jsp">已完成</a> </div>
      </div>
      <div class="row" style="font-size:24px; padding:10px">
        <a href="/QREBayAccount/eBayAccount.jsp"><label class="btn btn-lg btn-info">帳號管理</label></a>
      	<a href="/QREBayAccount/addAccount.jsp"><label class="btn btn-lg btn-info">新增帳號</label></a>
      	<a href="/QREBayAccount/eBayAccount.jsp"><label class="btn btn-lg btn-info">查詢帳號</label></a>
      </div>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>帳號管理</h3>
      <div class="row" style="font-size:24px; padding:10px">
        <a href="/QREBayAccount/eBayAccount.jsp"><label class="btn btn-lg btn-info">帳號管理</label></a>
      	<a href="/QREBayAccount/addAccount.jsp"><label class="btn btn-lg btn-info">新增帳號</label></a>
      	<a href="/QREBayAccount/eBayAccount.jsp"><label class="btn btn-lg btn-info">查詢帳號</label></a>
      </div>
    </div>
    <div id="menu3" class="tab-pane fade">
      <h3>員工管理</h3>
      <div class="row" style="font-size:24px; padding:10px">
        <a href="/QREmployee/accountManage.jsp"><label class="btn btn-lg btn-info">員工管理</label></a>
      	<a href="/QREmployee/Account.jsp"><label class="btn btn-lg btn-info">新增員工</label></a>
      	<a href="/QREmployee/accountManage.jsp"><label class="btn btn-lg btn-info">查詢員工</label></a>
      </div>
    </div>
    <div id="menu4" class="tab-pane fade">
      <h3>權限管理</h3>
      <div class="row" style="font-size:24px; padding:10px">
      	<a href="/QRAccess/Competence.jsp"><label class="btn btn-lg btn-info">權限管理</label></a>
      	<a href="/QRAccess/Competence.jsp"><label class="btn btn-lg btn-info">層級設定</label></a>
      </div>
    </div>
    <div id="menu5" class="tab-pane fade">
      <h3>獲取訂單</h3>
      <div class="row text-center" >
      <a href="/QROrders/GetEbayOrders.jsp"><label class="btn btn-lg btn-primary" >實際訂單資料</label></a>
      <a href="/QROrders/GetEbayOrdersFromSandbox.jsp"><label class="btn btn-lg btn-warning" id="button-upload">Sandbox訂單資料</label></a>
    </div>
    </div>
  </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $(this).tab('show');
        $('#spinner').hide();
    });
    $('.nav-tabs a').on('shown.bs.tab', function(event){
        var x = $(event.target).text();         // active tab
        var y = $(event.relatedTarget).text();  // previous tab
        $(".act span").text(x);
        $(".prev span").text(y);
    });
});
</script>
<%@ include file="/href/footer.jsp"%>
</body>


</html>