<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="checkno" class="tw.iii.purchase.purchaseFactory" scope="page"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/jquery-1.12.4.min.js"></script>
<!-- jqueryAutoComplete 不可以在../js/jquery-1.12.4.min.js 之前 -->


<script type="text/javascript">
function invalidate() {
	$("#collapse2").addClass("in");
	$("#collapse3").addClass("in");

};
$(function() {

	$("#listForm").validate({
		//debug:true,
		ignore : [],
		invalidHandler : function(form) {
			invalidate();

		}
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
System.out.print(today);
session.setAttribute("today", today);

%>

  <%@ include file ="/href/navbar.jsp" %> 


  
<div class="nav">
  	<div class="container ">
    	<div class="navbar-left" style="background-color:#BCF1E5;" >
        	<ul class="nav nav-tabs">
        	<li><a href="SearchStockPage.jsp" style="color:#000000">庫存</a></li>
              <li class="" style="background-color:#1CAF9A"><a href="SearchProductPage.jsp" style="color:#FFFFFF">商品</a></li>
              
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li><a href="SearchProductPage.jsp" style="color: #000">查詢商品</a></li>
					<li><a href="TotalBundles.jsp" style="color: #000000">查詢組合商品</a></li>
					
					<li class="" style="background-color: #1CAF9A"><a
						href="NewProduct.jsp" style="color: #fff">新增單項商品</a></li>
						<li ><a
						href="ProductAddPage.jsp" style="color: #000">新增組合商品</a></li>
            </ul>
        </div>
    </div>
  </div>
  
  
  
 <div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchProductPage.jsp">庫存/商品管理</a></li>
			<li><a href="NewProduct.jsp">新增單項商品</a></li>
		</ol>
	</div>


<div class="container" style="background: #9DDCD1; border-radius:20px;" >
  <form id="listForm" name="listForm" method="post" action="../ProductDo" 
  style="font-size: 100%; vertical-align: baseline; padding: 15px; " 
  class=" container">

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
                      <div class="col-md-8  well-sm"><input class="form-control required"    title="請輸入正確SKU" type="text" name="SKU" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>條碼編號</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control"    type="text" name="barCode" value=""></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>擁有者</h4></div>
                    <div class="col-md-8  well-sm"><input class="form-control"    type="text" name="owner" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>商品類別</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control required"   title="請輸入正確商品類別" type="text" name="productType" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>廠牌</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control required"     title="請輸入正確廠牌" type="text" name="brand" value="" ></div>
                  </div>
                   <div class="row">
                  	  <div class="col-md-3 text-right well-sm label-tag"  ><h4>副廠牌</h4></div>
                   	  <div class="col-md-8  well-sm"><input class="form-control"    type="text" name="subBrand" value="" ></div>
                  </div>
                  <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>EAN</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control"    type="text" name="EAN" value="" ></div>
              </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>ProductCode</h4></div>
                      <div class="col-md-8  well-sm"><input class="form-control"    type="text" name="productCode" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>上傳圖片</h4></div>
                      <div class="col-md-8  well-sm">
                        <input class="" type="file" name="pic" accept="image/*">
                       
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
             <div class="container-fluid form-horizontal">
            
             
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>品名</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control required"    title="請輸入正確品名" type="text" name="P_name" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>規格</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control required"    title="請輸入正確規格" type="text" name ="spec" value="" ></div>
              </div>
            
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>顏色</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control required"    title="請輸入正確顏色" type="text" name="color" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>安全庫存</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control digits required"    title="請輸入正確安全庫存" type="text" name="securedQty" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>成本</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control digits required"     title="請輸入正確成本" type="text" name="cost" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>備註</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control"    type="text" name="comment" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>更新紀錄</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control  required yymmdd"    type="text" name="checkupdate" value="${today}" readonly></div>
              </div>
              	 <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>上架否</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control required"     type="text" name="added" value="" ></div>
              </div>  
                <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>重量</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control number "   title="請輸入正確重量  ex:8.7" type="text" name="weight" value="" ></div>
              </div>
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
                  <div class="col-md-8  well-sm"><input class="form-control"    type="text" name="packageMatrial" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>材積重</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control number"    type="text" title="請輸入正確重量  ex:8.7" name="vilumetricWeight" value="0" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>建檔日</h4></div>
                  <div class="col-md-8  well-sm"><input class="form-control  required yymmdd"   type="text" name="createDate" value="${today}" readonly></div>
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