<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,tw.iii.qr.stock.DTO.CProduct" %>
<%@ page import="tw.iii.IDP.*" %>
<%@ page import="tw.iii.qr.IndependentOrder.model.entity.*" %>
<jsp:useBean id="IOrderFactory" class="tw.iii.IDP.IOrderFactory" scope="page" />
<jsp:useBean id="COrderFactory" class="tw.iii.qr.order.COrderFactory" scope="page" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂單內容</title>
</head>
<body>
  <%@ include file ="/href/navbar.jsp" %>
<c:if test="${PageCompetence.getOrdersManage() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
// String QR_id ;
// if(request.getParameter("QR_id") != null || request.getParameter("QR_id") != ""){
// QR_id = request.getParameter("QR_id");
// System.out.println(QR_id);
// IOrderFactory iof = new IOrderFactory();
// IDPorderAll IDPOrderDetail = iof.getIDPorderAllInfobyqrId(QR_id);
// session.setAttribute("IDPOrderDetail", IDPOrderDetail);
// }
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#3DFF81;" >
      <ul class="nav nav-tabs">
        <li class="" style="background-color:#189B30"><a href="SearchOrder.jsp?begin=0&end=10" style="color:#FFFFFF">獨立出貨</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#189B30;" >
      <ul class="nav nav-tabs">
        <li><a href="SearchOrder?begin=0&end=10">查詢訂單</a></li>
        <li><a href="IndependentOrder.jsp?begin=0&end=10">新增訂單</a></li>
        <li><a href=""  style="color:#fff">揀貨中</a></li>
        <li><a href="UploadTrackingCode?begin=0&end=10">上傳追蹤碼</a></li>
        <li><a href="Finished?begin=0&end=10">已完成訂單</a></li>
        <li><a href="ShipmentRecord?begin=0&end=10">訂單出貨記錄</a></li>
        <li><a href="refundPage.jsp?begin=0&end=10" >退貨</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder?begin=0&end=10">訂單管理</a></li>
    <li><a href="OrderDetail?QR_id=${IDPOrderDetail.getIordersMaster().getQrId()}">訂單明細</a></li>
  </ol>
</div>

  <div class="container table-responsive" style="background: #99C61D; border-radius:20px;">
  	<form name="searchform" method="post" action="../OrdersServlet" class="form-inline container" 	style="font-size: 100%; vertical-align: baseline; padding: 15px;" onsubmit="return isSubmited()">
	<c:if test="${PageCompetence.getPendingOrdersEdit() == 1}">  	
	  	<div class="row">
	      <label for="inputPassword" class="col-md-2 control-label text-left">編輯模式</label>
	      <div class="col-md-4">
	        <label class="radio-inline"><input type="checkbox" name="optionsRadios" id="optionsCheck" onchange="enableFields(this)">開關</label>
	    	<label class="radio-inline">
	    	<button type="submit" name="submit" value="updateOrder" class="btn btn-lg btn-success" id="btnCheck" disabled>更新商品資料</button>
	    	<a href="../OrderProcessingPage.jsp?begin=0&end=10" class="btn btn-info" role="button">回到處理中</a>
	      	</label>
	      </div>
	    </div>
    </c:if>
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;" disabled><legend>訂單明細</legend>
      <div class="panel-group" id="accordion">
        <div class="panel panel-default" style="background-color:#E7D29F">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">訂購人資料</a>
            </h4>
          </div>
          <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
              <div class="container-fluid form-horizontal">
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>熟客ID</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="guestId" value="${IDPOrderDetail.getGuestInfo().getGuestId()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>客戶姓名</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="name" value="${IDPOrderDetail.getGuestInfo().getName()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電子郵件</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="email" value="${IDPOrderDetail.getGuestInfo().getEmail()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="tel" value="${IDPOrderDetail.getGuestInfo().getTel()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>手機</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="phone" value="${IDPOrderDetail.getGuestInfo().getPhone()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>公司/學校</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="company" value="${IDPOrderDetail.getGuestInfo().getCompany()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>地址</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="address" value="${IDPOrderDetail.getGuestInfo().getAddress()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>國家</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="country" value="${IDPOrderDetail.getGuestInfo().getCountry()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>郵遞區號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="postcode" value="${IDPOrderDetail.getGuestInfo().getPostcode()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>性別</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="gender" value="${IDPOrderDetail.getGuestInfo().getGender()}"></div>
		        </div>
		      </div>
            </div>
          </div>
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">訂單資料</a>
            </h4>
          </div>
          <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
              <div class="container-fluid form-horizontal">
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>訂單狀態</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="OrderStatus" value="${IDPOrderDetail.getIordersMaster().getOrderStatus()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>出貨編號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="QR_id" value="${IDPOrderDetail.getIordersMaster().getQrId()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>Tracking Code</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="trackingCode" value="${IDPOrderDetail.getIordersMaster().getTrackingCode() }"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>平台</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="Platform" value="${IDPOrderDetail.getIordersMaster().getPlatform()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>平台訂單交易序號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="transactionId" value="${IDPOrderDetail.getIordersMaster().getTransactionId()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>購買日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="OrderDate" value="${IDPOrderDetail.getIordersMaster().getOrderDate()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>付款日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="PayDate" value="${IDPOrderDetail.getIordersMaster().getPayDate()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>出貨日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="ShippingDate" value="${IDPOrderDetail.getIordersMaster().getShippingDate()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>物流配送方式</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="Logistics" value="${IDPOrderDetail.getIordersMaster().getLogistics()}"></div>
		        </div>
			    <c:if test="${PageCompetence.getTotalAmountEdit() == 1}">
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>運費</h4></div>
			          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="ShippingFees" value="${IDPOrderDetail.getIordersMaster().getShippingFees()}"></div>
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>退運費</h4></div>
			          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="RefundShippingFees" value="${IDPOrderDetail.getIordersMaster().getRefundShippingFees()}"></div>
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>其它費用</h4></div>
			          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="OtherFees" value="${IDPOrderDetail.getIordersMaster().getOtherFees()}"></div>
			        </div>
<!-- 			        <div class="row"> -->
<!-- 			          <div class="col-md-3 text-right well-sm label-tag"><h4>ebay成交費</h4></div> -->
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>計算保價</h4></div>
			          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="InsuranceTotal" value="${IDPOrderDetail.getIordersMaster().getInsuranceTotal()}"></div>
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>paypal費用</h4></div>
			          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="PaypalFees" value="${IDPOrderDetail.getIordersMaster().getPaypalFees()}"></div>
			        </div>
		        </c:if>
		        <c:if test="${PageCompetence.getTotalAmountEdit() == 0}">
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>運費</h4></div>
			          <div class="col-md-5 well-sm"><input readonly class="form-control" type="text" name="ShippingFees" value="${IDPOrderDetail.getIordersMaster().getShippingFees()}"></div>
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>退運費</h4></div>
			          <div class="col-md-5 well-sm"><input readonly class="form-control" type="text" name="RefundShippingFees" value="${IDPOrderDetail.getIordersMaster().getRefundShippingFees()}"></div>
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>其它費用</h4></div>
			          <div class="col-md-5 well-sm"><input readonly class="form-control" type="text" name="OtherFees" value="${IDPOrderDetail.getIordersMaster().getOtherFees()}"></div>
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>ebay成交費</h4></div>
			          <div class="col-md-5 well-sm"><input readonly class="form-control" type="text" name="EbayFees" value="${IDPOrderDetail.getIordersMaster().getEbayFees()}"></div>
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>計算保價</h4></div>
			          <div class="col-md-5 well-sm"><input readonly class="form-control" type="text" name="InsuranceTotal" value="${IDPOrderDetail.getIordersMaster().getInsuranceTotal()}"></div>
			        </div>
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>paypal費用</h4></div>
			          <div class="col-md-5 well-sm"><input readonly class="form-control" type="text" name="PaypalFees" value="${IDPOrderDetail.getIordersMaster().getPaypalFees()}"></div>
			        </div>
		        </c:if>
		        
		        
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>重量(公克)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="weight_g" value="${IDPOrderDetail.getIordersDetail().getWeight_g()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>重量(盎司)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="weight_oz" value="${IDPOrderDetail.getIordersDetail().getWeight_oz()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>備註</h4></div>
		          <div class="col-md-5 well-sm"></div>
		        </div>
		        
		        <c:if test="${PageCompetence.getTotalAmountEdit() == 1}">
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>總計</h4></div>
			          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="TotalPrice" id="TotalPrice" value="${IDPOrderDetail.getIordersMaster().getTotalPrice()}"></div>
			        </div>
		      	</c:if>
		      	<c:if test="${PageCompetence.getTotalAmountEdit() == 0}">
			        <div class="row">
			          <div class="col-md-3 text-right well-sm label-tag"><h4>總計</h4></div>
			          <div class="col-md-5 well-sm"><input readonly class="form-control" type="text" name="TotalPrice" value="${IDPOrderDetail.getIordersMaster().getTotalPrice()}"></div>
			        </div>
		      	</c:if>
		      </div>
            </div>
          </div>
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">訂購商品清單</a>
            </h4>
          </div>
          <div id="collapse4" class="panel-collapse collapse in">
            <div class="panel-body">
              <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
			    <thead>
			 	  <tr class="ListTitle2">
			        <th>商品SKU</th>
			        <th>商品名稱</th>
			        <th>售價</th>
			        <th>數量</th>
			        <th>備註</th>
		          </tr>
	            </thead>
	            <c:forEach var="i" items="${IDPOrderDetail.getIordersDetails()}" begin="0" step="1" varStatus="check">
		        <tbody>
		          <tr>
		            <td>
		              <input class="" type="hidden" name="SKU" value="${i.getSku()}">
		              SKU:<br/><a href="../QRProduct/StockDetail.jsp?sku=${i.getSku()}"><b>${i.getSku()}</b></a>
	          		  <button type="submit" name="submit" value="deleteDetail" class="btn btn-sm btn-danger">移除此商品</button>
		            </td>
		            <td>Product Name:<br/>${i.getProductName()}<br/>
		            </td>
		           	<c:if test="${PageCompetence.getTotalAmountEdit() == 1}">
			            <td><input class="" type="text" name="price" value="${i.getPrice()}" ></td>
			        </c:if>
		            <c:if test="${PageCompetence.getTotalAmountEdit() == 0}">
			            <td><input class="" type="text" name="price" value="${i.getPrice()}" readonly></td>
		           	</c:if>
		            <td>
		              <input class="" type="text" name="qty" value="${i.getQty()}">
		             	 倉別:
		              <select name="warehouse">
                        <c:set var="SKU" scope="session" value="${i.getSku()}"/>
                        <%
                        if(session.getAttribute("SKU") != null){
                        LinkedList<String> warehouses = COrderFactory.getWarehousesForI(request,session.getAttribute("SKU").toString());
                        session.setAttribute("warehouses", warehouses);
                        } else {
                        	session.setAttribute("warehouses", "");
                        }
                        %>
                        <c:choose>
                          <c:when test="${i.getWarehouse() != null || i.getWarehouse() != ''}">
                            <option value="${i.getWarehouse()}">${i.getWarehouse()}</option>
		                  </c:when>
		                  <c:otherwise>
		                    <option></option>
		                  </c:otherwise>
		                </c:choose>
                          <c:forEach var="w" items="${warehouses}">
                            <option value="${w}">${w}</option>
                          </c:forEach>
                      </select>
		            </td>
		            <td><input class="form-control" type="text" name="comment" value="${i.getComment()}">
		            <input type="hidden" name="item" value="${i.getItem()}">
		            </td>
		          </tr>
		        </tbody>
		        </c:forEach>
		      </table>
	          <button type="submit" name="submit" value="toGetProducts" class="btn btn-sm btn-success">新增商品</button>
            </div>
          </div>
        </div>
      </div>
    </fieldset>
    </form>
  </div>

<script type="text/javascript">
 
$(function () {
	$(".btn-danger").click(function() {
		bool = confirm("確認是否刪除訂單");
		if(!bool){
			return false;
		}
	});
});
  
 function enableFields(ele){
  if (ele.checked) {
	  $("#myfields").prop("disabled", false);
	  $("#btnCheck").prop("disabled", false);
  } else {
	  $("#myfields").prop("disabled", true);
	  $("#btnCheck").prop("disabled", true);
	  }
  };

function isSubmited() {
	//isLessTotalPrice()
	var sum = 0;
    $('input[name="price"]').each(function(){
        sum += +$(this).val();
    });
    var total = $('#TotalPrice').val();
    if (sum>total || sum <=0)
    {
    	alert("請注意,修改後金額小於原始金額,不可更改");
        return false;
    } else if (sum>total || sum <=0)
    {
    	alert("請注意,修改後金額小於等於0");
        return false;
    }
    //isWarehousePicked()
    var isWarehousePicked = true;
    $('select[name=warehouse]').each(function(){
    	if($(this).val() == undefined || $(this).val().trim() == ""){
    		alert("請選擇倉別");
    		isWarehousePicked = false;
    		return false;
		}
    });
    return isWarehousePicked;
}
</script>  
</body>
</html>	