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
<div class="container container-fluid breadcrumbBox">
  <form name="Homeform" method="post" action="HomePageChoose" style="font-size: 100%; vertical-align: baseline; " class=" form-group container">
    <%-- <h2>Welcome ${EmpName}${EmpStatus}</h2> --%>
    <div class="container bg-info text-center" style= "padding:10px 10px 10px 10px">
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
      <hr/>
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
      <hr/>
      <div class="row" style="font-size:36px; padding:10px 10px 10px 10px">
        <div class="col-md-4"> <a href="QREBayAccount/eBayAccount.jsp" ><img src="img/store-2.png">Ebay帳號管理</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QREBayAccount/addAccount.jsp">新增帳號</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QREBayAccount/eBayAccount.jsp">查詢帳號</a> </div>
      </div>
      <hr/>
      <div class="row" style="font-size:36px; padding:10px 10px 10px 10px">
        <div class="col-md-4"> <a href="/QREmployee/accountManage.jsp" ><img src="img/users.png">員工管理</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QREmployee/Account.jsp">新增員工</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="/QREmployee/accountManage.jsp">查詢員工</a> </div>
      </div>
      <hr/>
      <div class="row" style="font-size:36px; padding:10px 10px 10px 10px">
        <div class="col-md-4"> <a href="QRAccess/Competence.jsp" ><img src="img/compose-3.png">權限管理</a> </div>
        <div class="col-md-2" style="font-size:24px;"> <a href="QRAccess/Competence.jsp">層級設定</a> </div>
      </div>
    </div>
  </form>
</div>
   
	
</body>
<%@ include file="/href/footer.jsp"%>

</html>