<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增產品</title>
</head>
<body>
<%@ include file ="/href/navbar.jsp" %>
<div class="nav">
  	<div class="container">
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
  <script type="text/javascript">
  
  </script>
  
  
  <div class="container container-fluid breadcrumbBox">
      <ol class="breadcrumb" >
          <li><a href="#" >主要目錄</a></li>
          <li class="active" style="display:"><a href="#">庫存</a></li>
          <li><a href="#">庫存查詢</a></li>
      </ol>
  </div>

<div class="container" style="background: #9DDCD1; border-radius:20px;" >
  <form id="listForm" name="listForm" method="post" action="PurchaseRecordPage.jsp" 
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
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>條碼編號</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>擁有者</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>商品類別</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>廠牌</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
                  </div>
                   <div class="row">
                  	  <div class="col-md-3 text-right well-sm label-tag"  ><h4>副廠牌</h4></div>
                   	  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
                  </div>
                  <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>EAN</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
              </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>ProductCode</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>上傳圖片</h4></div>
                      <div class="col-md-5 well-sm">
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
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="$" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>規格</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
              </div>
            
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>顏色</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>安全庫存</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>成本</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>備註</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>更新紀錄</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
              </div>
                <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>重量</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
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
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>材積重</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>建檔日</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
              </div>
          
              </div>
            </div>
          </div>
        </div>
      </div>
      </fieldset>
    </form>
  </div>
</body>
</html>