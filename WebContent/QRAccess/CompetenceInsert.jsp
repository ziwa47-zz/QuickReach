<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新增權限</title>
</head>

<body><%@ include file="/href/navbar.jsp" %>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color: #AC7ED3;">
      <ul class="nav nav-tabs">
        <li><a href="Competence.jsp" style="color: #FFF">權限管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color: #AC7ED3;">
      <ul class="nav nav-tabs">
        <li><a href="Competence.jsp">檢視權限</a></li>
        <li><a href="CompetenceInsert.jsp" style="color: #FFF">新增權限</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb">
    <li><a href="../HomePage.jsp">首頁</a></li>
    <li class="active" style="display:"><a href="Competence.jsp">權限管理</a></li>
    <li><a href="Competence.jsp">檢視權限</a></li>
  </ol>
</div>

  <div class="container" >
  	<form name="searchform" action="CompetenceInsert.do" method="post" style="font-size: 100%; vertical-align: baseline;" class=" form-group container">
      
          <input type="hidden">
              <h3 class="" style="background: #BCF1E5; border-left: 6px solid #1CAF9A;" >新增權限</h3>
              <div class="container-fluid form-horizontal">
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>權限等級</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" required="required" name="CompetenceLv"></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>商品權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ProductManage" />管理商品</label>
					   
					  </div>
                  </div>
                  <div  style="display:none" class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>採購權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="PurchaseManage" />管理採購</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>庫存權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="InventoryManage" />管理庫存&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="InventoryInfoEdit" />編輯庫存資料</label>
					   
					  </div>
                  </div>
                  <div  style="display:none" class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>客戶權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ClientManage" /> 管理客戶</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>訂單權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0" name="EntireOrders" />所有狀態訂單</label>
					 	<label  style="display:none" class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="OrdersInvoiceDownload" />下載所有訂單Invoice</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp;</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="PriceChange" />更改成本費用</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="PendingOrdersEdit" /> 編輯待處理訂單</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp;</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="TotalAmountEdit" />編輯訂單金額</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="OrdersManage" />管理訂單</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>報表權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label  style="display:none" class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ChartView" />查看圖表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="ProductProfitView" />查看商品利潤</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp;</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label  style="display:none" class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ReportView" />查看報表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="ProductCostView" />查看商品成本</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>帳號權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="AccountInfoEdit" />修改帳號資料</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="EbayPaypalAccountEdit" />編輯ebay/paypal帳號</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp;</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ParamSettingEdit" />修改參數設定</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="InventoryCostView" />查看庫存成本</label>
					   
					  </div>
                  </div>
                  <div class="container-fluid form-horizontal">
                	<div class="row text-center" >     
                        <button type="submit" value="insert" name="smt" id="new">新增</button>
                    </div>
                
                </div>
                  
              </div>
              
</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $("#new").click(function(){
        $('#spinner').hide();
    });
});
</script>
  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>
