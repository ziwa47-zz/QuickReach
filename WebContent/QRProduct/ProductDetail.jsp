<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*,tw.iii.qr.stock.CProduct" %>
<jsp:useBean id="searchDetail" class="tw.iii.qr.stock.CProductFactory" scope="page"/> 
<!doctype html>
<html>
  
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<title>Product Detail</title>
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
              <li class="" style="background-color:#1CAF9A"><a href="SearchStockPage.jsp" style="color:#fff">查詢庫存</a></li>
              <li><a href="NewProduct.jsp">新增產品</a></li>
              <li ><a href="purchasePage.jsp" style="color:#000">進貨</a></li>
              <li><a href="PurchaseRecordPage.jsp" style="color:#000000">進貨紀錄</a></li>
              <li><a href="OutRecordPage.jsp" style="color:#000000">出貨紀錄</a></li>
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
<%
String sku ;
request.setCharacterEncoding("UTF-8");
if(request.getParameter("sku") != null || request.getParameter("sku") != ""){
Connection conn = new DataBaseConn().getConn();
sku = request.getParameter("sku");
CProduct product = searchDetail.searchDetail(sku, conn);
session.setAttribute("resultDetail", product);
conn.close();
}

%>

<div class="container" style="background: #9DDCD1; border-radius:20px;" >
  <form id="listForm" name="listForm" method="post" action="../ProductDo" 
  style="font-size: 100%; vertical-align: baseline; padding: 15px; " 
  class="form-inline container">
    <div class="row">
        <label for="inputPassword" class="col-md-2 control-label text-left">編輯模式</label>
          <div class="col-md-4">
            <label class="radio-inline">
              <input type="radio" name="optionsRadios" id="optionsRadios1">開啟</label>
      	    <label class="radio-inline">
      	      <input type="radio" name="optionsRadios" id="optionsRadios2">關閉</label>
      	       <label class="radio-inline"><button type="submit" name="submit" value="updateProduct"
						class="btn-lg btn-success">更新產品資料</button></label>
      	       
          </div>
      </div>
    <fieldset id="myfields" class="container-fluid" style="padding:0 30px 0 0;" disabled><legend>產品明細</legend>
      <input type="hidden">
      <div class="panel-group" id="accordion">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">商品基本資料</a>
            </h4>
          </div>
          <div id="collapse1" class="panel-collapse collapse in">
            <div class="panel-body">
              <div class="container-fluid form-horizontal">
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>商品編號</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" name="sku" value="${resultDetail.getSKU()}" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>條碼編號</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" name="barcode" value="${resultDetail.getSKU()}"></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>擁有者</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" name="owner" value="${resultDetail.getOwner()}" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>商品類別</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" name="producttype" value="${resultDetail.getProductType()}" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>廠牌</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" name="brand" value="${resultDetail.getBrand()}" ></div>
                  </div>
                   <div class="row">
                  	  <div class="col-md-3 text-right well-sm label-tag"  ><h4>副廠牌</h4></div>
                   	  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="subbrand" value="${resultDetail.getSubBrand()}" ></div>
                  </div>
                  <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>EAN</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="ean" value="${resultDetail.getEAN()}" ></div>
              </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>ProductCode</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" name="productcode" value="${resultDetail.getProductCode()}" ></div>
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
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="pname" value="${resultDetail.getP_name()}" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>規格</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="spec" value="${resultDetail.getSpec()}" ></div>
              </div>
            
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>顏色</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="color" value="${resultDetail.getColor()}"></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>安全庫存</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text"name="securedqty" value="${resultDetail.getSecuredQty()}"></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>成本</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="cost" value="${resultDetail.getCost()}" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>備註</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="comment" value="${resultDetail.getComment()}"></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>更新紀錄</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="checkupdate" value="${resultDetail.getCheckupdate()}"></div>
              </div>
                <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>重量</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="weight" value="${resultDetail.getWeight()}" ></div>
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
                  <div class="col-md-5 well-sm"><input class="form-control" type="text" name="package" value="${resultDetail.getPackageMatrial()}" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>材積重</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text"name="vilu" value="${resultDetail.getVilumetricWeight()}" ></div>
              </div>
              <div class="row">
                  <div class="col-md-3 text-right well-sm label-tag"  ><h4>建檔日</h4></div>
                  <div class="col-md-5 well-sm"><input class="form-control" type="text"name="cdate" value="${resultDetail.getCreateDate()}" ></div>
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
