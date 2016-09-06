<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="checkno" class="tw.iii.purchase.purchaseFactory" scope="page"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="../css/smoothness/jquery-ui.css">
<script src="../js/jquery-1.12.4.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/jquery.ui.datepicker-zh-TW.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>

  
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>

<script type="text/javascript">
$(function () {

	
	$("#listForm").validate({
		//debug:true,
		ignore:[],
			
// 		submitHandler: function (form)
// 	    {
// 	        $('#addProductSubmit').attr('disabled', 'disabled');
// 	       alert("ok!!!~!!")
// 	        form.submit();
	       
// 	    },
	   
		
		
	});

	
	
});

</script>


<style type="text/css">
#listForm label.error {
font-size: 0.8em;
color: #F00;
font-weight: bold;
display: block;


}
</style>
<title>新增產品</title>
</head>
<body>

<%
String today = checkno.getDay();
session.setAttribute("today", today);

%>

  <%@ include file ="../href/navbar.jsp" %> 


  
<div class="nav">
  	<div class="container ">
    	<div class="navbar-left" style="background-color:#BCF1E5;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#1CAF9A"><a href="SearchStockPage.jsp" style="color:#FFFFFF">庫存</a></li>
              <li><a href="SearchProductPage.jsp" style="color:#000000">商品</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li><a href="SearchStockPage.jsp" >查詢庫存</a></li>
              <li style="background-color:#1CAF9A"><a href="NewProduct.jsp" style="color:#fff">新增產品</a></li>
              <li ><a href="purchasePage.jsp">進貨</a></li>
              <li><a href="PurchaseRecordPage.jsp">進貨紀錄</a></li>
              <li><a href="OutRecordPage.jsp">出貨紀錄</a></li>
            </ul>
        </div>
    </div>
  </div>
  
  
  
  <div class="container container-fluid breadcrumbBox">
      <ol class="breadcrumb" >
          <li><a href="#" >主要目錄</a></li>
          <li class="active" style="display:"><a href="#">庫存</a></li>
          <li><a href="#">庫存查詢</a></li>
      </ol>
  </div>

<div class="container" style="background: #9DDCD1; border-radius:20px;" >
  <form id="listForm" name="listForm" method="post" action="../ProductDo" 
  style="font-size: 100%; vertical-align: baseline; padding: 15px; " 
  class="form-inline container">

    <fieldset id="myfields" class="container-fluid" style="padding:0 30px 0 0;"><legend>新增產品</legend>
      <input type="hidden">
      <div class="panel-group" id="accordion">  
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">產品基本資料</a>
            </h4>
          </div>
          <div id="collapse1" class="panel-collapse collapse in">
            <div class="panel-body">
              <div class="container-fluid form-horizontal">
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>商品編號</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control required"  style="width:700px"  title="請輸入正確SKU" type="text" name="SKU" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>條碼編號</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control" style="width:700px"  type="text" name="barCode" value=""></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>擁有者</h4></div>
                    <div class="col-md-8  well-sm"><input class="form-control"  style="width:700px" type="text" name="owner" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>商品類別</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control required" style="width:700px" title="請輸入正確商品類別" type="text" name="productType" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>廠牌</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control required"  style="width:700px"  title="請輸入正確廠牌" type="text" name="brand" value="" ></div>
                  </div>
                   <div class="row">
                  	  <div class="col-md-3 text-right well-sm label-tag"  ><h4>副廠牌</h4></div>
                   	  <div class="col-md-8  well-sm"><input class="form-control" style="width:700px"  type="text" name="subBrand" value="" ></div>
                  </div>
                  <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>EAN</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control"  style="width:700px" type="text" name="EAN" value="" ></div>
              </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>ProductCode</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control" style="width:700px"  type="text" name="productCode" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>上傳圖片</h4></div>
                      <div class="col-md-8  well-sm">
                        <input class="" type="file" name="pic" accept="image/*">
                        <input type="submit">
                      </div>
                  </div>
              </div>
            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">商品進階資料</a>
            </h4>
          </div>
          <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
            
             
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>品名</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control required" style="width:700px"  title="請輸入正確品名" type="text" name="P_name" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>規格</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control required" style="width:700px"  title="請輸入正確規格" type="text" name ="spec" value="" ></div>
              </div>
            
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>顏色</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control required" style="width:700px"  title="請輸入正確顏色" type="text" name="color" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>安全庫存</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control digits required" style="width:700px"  title="請輸入正確安全庫存" type="text" name="securedQty" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>成本</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control digits required"  style="width:700px"  title="請輸入正確成本" type="text" name="cost" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>備註</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control"  style="width:700px" type="text" name="comment" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>更新紀錄</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control  required yymmdd" style="width:700px"  type="text" name="checkupdate" value="${today}" readonly></div>
              </div>
              	 <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>上架否</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control required" style="width:700px"   type="text" name="added" value="" ></div>
              </div>  
                <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>重量</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control number " style="width:700px" title="請輸入正確重量  ex:8.7" type="text" name="weight" value="" ></div>
              </div>

            </div>
          </div>
        </div>
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">商品備註</a>
            </h4>
          </div>
          <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
              <input type="hidden">
              <div class="container-fluid form-horizontal">
                    <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>包裝材質</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control" style="width:700px"  type="text" name="packageMatrial" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>材積重</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control number" style="width:700px"  type="text" title="請輸入正確重量  ex:8.7" name="vilumetricWeight" value="0" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>建檔日</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control  required yymmdd" style="width:700px" type="text" name="createDate" value="${today}" readonly></div>
              </div>
          
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="row text-center">
       
               <button  type="submit" name="submit" id="addProductSubmit" value="newProduct"
						class="btn-lg btn-success">新增產品</button>
          </div>
      </fieldset>
    </form>
  </div>
</body>
</html>