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
<div style="background-color:#CCFFCC;">
<div class="container">
  <h2>歡迎使用QuickReach系統</h2>
  <p>請開始您的作業</p>
  <ul class="nav nav-tabs" style="font-size:24px;">
    <c:if test="${PageCompetence.getProductManage() ==1}">
    	<li class="active"><a href="#home"><img src="/img/database.png" width="24" height="24">商品管理</a></li>
    </c:if>
    <c:if test="${PageCompetence.getOrdersManage() ==1}">
    	<li><a href="#menu1"><img src="img/clipboard-2.png" width="24" height="24">訂單資訊</a></li>
    </c:if>
    <c:if test="${PageCompetence.getEbayPaypalAccountEdit()==1}">
    	<li><a href="#menu2"><img src="img/store-2.png" width="24" height="24">帳號管理</a></li>
	</c:if>
	<c:if test="${PageCompetence.getAccountInfoEdit() ==1 }">    
	    <li><a href="#menu3"><img src="img/users.png" width="24" height="24">員工管理</a></li>
	    <li><a href="#menu4"><img src="img/compose-3.png" width="24" height="24">權限管理</a></li>
    </c:if>
    <c:if test="${PageCompetence.getParamSettingEdit() ==1}">
    	<li><a href="#menu6"><img src="img/warehouse.png" width="36" height="36">供應商/倉庫</a></li>
    </c:if>
    <c:if test="${PageCompetence.getOrdersManage() ==1}">
    	<li><a href="#menu5">獲取訂單</a></li>
    </c:if>
  </ul>	

  <div class="tab-content">

    <c:if test="${PageCompetence.getProductManage()==1}">
	    <div id="home" class="tab-pane fade in active">
	      <h3>商品管理</h3>
	      <div class="row" style="font-size:24px; padding:10px">
	        <a href="/QRProduct/SearchStockPage.jsp"><label class="btn btn-lg btn-primary">查詢庫存</label></a>
	      	<a href="/QRProduct/PurchasePage.jsp"><label class="btn btn-lg btn-primary">進貨</label></a>
	      	<a href="/QRProduct/PurchaseRecordPage.jsp"><label class="btn btn-lg btn-primary">查詢庫存紀錄</label></a>
	      	<a href="/QRProduct/SearchProductPage.jsp"><label class="btn btn-lg btn-primary">查詢商品</label></a>
	      	<a href="/QRProduct/NewProduct.jsp"><label class="btn btn-lg btn-primary">新增單項商品</label></a>
	      	<a href="/QRProduct/BundlesAdd.jsp"><label class="btn btn-lg btn-primary">新增組合商品</label></a>
	      	<a href="/QRProduct/TotalBundles.jsp"><label class="btn btn-lg btn-primary">組合商品</label></a>
	      </div>
	    </div>
 	</c:if>  

    <div id="menu1" class="tab-pane fade">
      <h3>訂單資訊</h3>
      <div class="row" style="font-size:24px; padding:10px">
        <a href="/QROrders/SearchOrder.jsp?begin=0&end=10"><label class="btn btn-lg btn-primary">查詢訂單</label></a>
      	<a href="/QROrders/DayliBalanceSheet.jsp?begin=0&end=10"><label class="btn btn-lg btn-primary">日結表</label></a>
      	<a href="/QROrders/OrderProcessingPage.jsp?begin=0&end=10"><label class="btn btn-lg btn-primary">處理中</label></a>
      	<a href="/QROrders/OrderPickupPage.jsp?begin=0&end=10"><label class="btn btn-lg btn-primary">揀貨中</label></a>
      	<a href="/QROrders/OrderUploadTrackingCode.jsp?begin=0&end=10"><label class="btn btn-lg btn-primary">上傳追蹤碼</label></a>
      	<a href="/QROrders/OrderFinished.jsp?begin=0&end=10"><label class="btn btn-lg btn-primary">已完成</label></a>
      	<a href="/QROrders/ShipmentRecord.jsp?begin=0&end=10"><label class="btn btn-lg btn-primary">訂單出貨紀錄</label></a>
      </div>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>帳號管理</h3>
      <div class="row" style="font-size:24px; padding:10px">
        <a href="/QREBayAccount/eBayAccount.jsp"><label class="btn btn-lg btn-primary">帳號管理</label></a>
      	<a href="/QREBayAccount/addAccount.jsp"><label class="btn btn-lg btn-primary">新增帳號</label></a>
      	<a href="/QREBayAccount/eBayAccount.jsp"><label class="btn btn-lg btn-primary">查詢帳號</label></a>
      </div>
    </div>
    <div id="menu3" class="tab-pane fade">
      <h3>員工管理</h3>
      <div class="row" style="font-size:24px; padding:10px">
        <a href="/QREmployee/accountManage.jsp"><label class="btn btn-lg btn-primary">員工管理</label></a>
      	<a href="/QREmployee/Account.jsp"><label class="btn btn-lg btn-primary">新增員工</label></a>
      	<a href="/QREmployee/accountManage.jsp"><label class="btn btn-lg btn-primary">查詢員工</label></a>
      </div>
    </div>
    <div id="menu4" class="tab-pane fade">
      <h3>權限管理</h3>
      <div class="row" style="font-size:24px; padding:10px">
      	<a href="/QRAccess/Competence.jsp"><label class="btn btn-lg btn-primary">權限管理</label></a>
      	<a href="/QRAccess/CompetenceInsert.jsp"><label class="btn btn-lg btn-primary">新增權限</label></a>
      </div>
    </div>
    <div id="menu6" class="tab-pane fade">
      <h3>供應商/倉庫</h3>
      <div class="row" style="font-size:24px; padding:10px">
      <a href="/SupplyCompany/SCManage.jsp"><label class="btn btn-lg btn-primary" id="button-upload">供應商帳號管理</label></a>
      <a href="/SupplyCompany/addSCName.jsp"><label class="btn btn-lg btn-primary" >新增供應商帳號</label></a>
      <a href="/QRWarehouse/warehouseManage.jsp"><label class="btn btn-lg btn-primary" >倉庫管理</label></a>
      <a href="/QRWarehouse/addWarehouse.jsp"><label class="btn btn-lg btn-primary" >新增倉庫</label></a>
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