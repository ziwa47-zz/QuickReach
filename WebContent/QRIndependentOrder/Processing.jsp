<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.IndependentOrder.model.entity.IDPorderAll"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>處理中頁面</title>
</head>
<body>
<%@ include file = "/href/navbar.jsp"%>
<c:if test="${PageCompetence.getOrdersManage() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
	//COrderFactory.checkUrlToRemoveSession(request, session);
	
// 	LinkedList<IDPorderAll> orderList  = IOF.getAllIDPorder("處理中");
	
// 	session.setAttribute("list", orderList);
     request.setAttribute("begin", request.getParameter("begin"));
     request.setAttribute("end", request.getParameter("end"));
   
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
        <li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
        <li><a href="IndependentOrder.jsp?begin=0&end=10">新增訂單</a></li>
        <li><a href=""  style="color:#fff">處理中</a></li>
        <li><a href="Pickup.jsp?begin=0&end=10">揀貨中</a></li>
        <li><a href="UploadTrackingCode.jsp?begin=0&end=10">上傳追蹤碼</a></li>
        <li><a href="Finished.jsp?begin=0&end=10">已完成訂單</a></li>
        <li><a href="ShipmentRecord.jsp?begin=0&end=10">訂單出貨記錄</a></li>
        <li><a href="refundPage.jsp?begin=0&end=10" >退貨</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
    <li><a href="Processing.jsp?begin=0&end=10">處理中</a></li>
  </ol>
</div>

<div class="nav">
  <div class="container" style="background:#99C61D; border-radius:20px;">
    <form name="searchform" method="post" action="/QRIndependentOrder/Processing" class="form-inline container"
    style="font-size: 100%; vertical-align: baseline; padding: 15px;">
      <fieldset class="font-weight" style="padding:0 30px 0 0;">
        <legend>處理中</legend>
        <div class="row">
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>訂單編號：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <input class="form-control" name="QR_id" type="text">
              </div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>熟客代號：</label>
                </h5>
              </div>
              <div class="col-md-8" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="guestId" type="text" style="border-radius: 4px">
              </div>
            </div>
          </div>
        </div>
    
        <div class="row">
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>商品SKU：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <input class="form-control" name="SKU" type="text">
              </div>
            </div>
          </div>
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2">
                <h5>
                  <label>付款日期：</label>
                </h5>
              </div>
              <div class="col-md-10">
                <input class="form-control" type="text" name="payDateMin">
                <label for="focusedInput ">~</label>
                <input class="form-control" type="text" name="payDateMax">
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>商品名稱：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <input class="form-control" name="productName" type="text">
              </div>
            </div>
          </div>
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2">
                <h5>
                  <label>出貨日期：</label>
                </h5>
              </div>
              <div class="col-md-10">
                <input class="form-control" type="text" name="shippingDateMin">
                <label for="focusedInput ">~</label>
                <input class="form-control" type="text" name="shippingDateMax">
              </div>
            </div>
          </div>
        </div>
        <br/>
        <div class="row">
          <div class="col-md-12 form-group ">
            <label>物流選擇：</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="ALL" checked="checked">ALL</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="DHL">DHL</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="Fedex">Fedex</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="EMS">EMS</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="AP">AP(國際包裹)</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="RA">RA(國際掛號)</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="USPS1">USPS寄倉</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="USPS2">USPS集運</label>
            <label class="checkbox-inline"><input type="radio" name="logistics" value="lothers">其他</label>
          </div>
        </div>
        <br/>
       
        <br/>
        <div class="row text-center" >
          <input type="hidden" name="IDPprocessing"  value="IDPprocessing"> <!-- 控制搜尋結果在處理中 -->
          <button class="btn btn-lg btn-primary" type="submit" name="submit" value="IDPprocessingSearch">搜尋</button>
          <button class="btn btn-lg btn-primary" type="reset" name="" >清空</button>
        </div>
      </fieldset>
    </form>
  </div>
  <hr/>
      <div class="container table-responsive bg-warning" style=" border-radius:20px">
        <form name="searchform" method="post" action="/QRIndependentOrder/IDPStatusDo" class="form-inline container"
          style="font-size: 100%; vertical-align: baseline; padding: 15px; "  onsubmit="return isSubmited()">
          <label class="btn btn-sm btn-info">
		    <input type="checkbox" autocomplete="off" onchange="selectAllOrders(this)"> 選擇全部
		  </label>
		  <button type="submit" name="send" value="revertTo" class="btn btn-md btn-info">回復至</button>
		  <select name="status" class="form-control">
		    <option></option>
		    <option>待處理</option>
		  </select>
          <ul class="pager pagination">
            <c:choose>
              <c:when test="${begin != 0}">
                <li><a href="OrderProcessingPage.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="OrderProcessingPage.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:otherwise>
            </c:choose>
            <c:forEach begin="0" end="${IDPprocess.size()/10}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${(check.index*10) != begin}">
                  <li><a href="OrderProcessingPage.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:when>
                <c:otherwise>
                  <li class="active"><a href="OrderProcessingPage.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:choose>
              <c:when test="${end < IDPprocess.size()}">
                <li><a href="OrderProcessingPage.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="OrderProcessingPage.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:otherwise>
            </c:choose>
            <label>共有:${IDPprocess.size()}筆</label>
          </ul>
          <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
            <tr class="ListTitle">
              <th>選取</th>
              <th>編輯</th>
              <th>訂單編號</th>
              <th>平台</th>
              <th>熟客代號</th>
              <th>客戶姓名</th>
              <th>購買日期</th>
              <th>出貨日期</th>
              <th>物流</th>
              <th>國家</th>
              <th>訂單狀態</th>
              <th>總金額</th>
              <th>使用者</th>
            </tr>
            <c:forEach var="i" items="${IDPprocess}" begin="${begin}" end="${end}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${check.index%2 != 0}">
                  <tr style="background-color:#D4F4D8">
                    <td rowspan="2" style="vertical-align:middle"><input type="checkbox" name="QR_id" value="${i.getIordersMaster().getQrId()}"></td>
                    <td><a href="OrderDetail.jsp?QR_id=${i.getIordersMaster().getQrId()}"><img src="../img/compose-4.png" ></a></td>
                    <td>${i.getIordersMaster().getQrId()}</td>
                    <td>${i.getIordersMaster().getPlatform()}</td>
                    <td>${i.getIordersMaster().getGuestId()}</td>
                    <td>${i.getGuestInfo().getName()}</td>
                    <td>${i.getIordersMaster().getPayDate()}</td>
                    <td></td>
                    <td>${i.getIordersMaster().getLogistics()}</td>
                    <td>${i.getGuestInfo().getCountry()}</td>
                    <td>${i.getIordersMaster().getOrderStatus()}
                      <input type="hidden" name="status" value="${i.getIordersMaster().getOrderStatus()}"></td>
                    <td>${i.getIordersMaster().getTotalPrice()}${i.getIordersMaster().getCurrency()}</td>
                    <td>${i.getIordersMaster().getStaffName()}</td>
                  </tr>
                  <tr style="background-color:#D4F4D8">
					<td colspan="9">
                    <c:forEach var="j" items="${i.getIordersDetails()}" begin="0" step="1" varStatus="check">
<%--                       <a href='#' class='pop' ><img src='/pics/${j.getPicPath()}' style='width: 20px; height: 20px;'></a> --%>
                      <b><a href="../QRProduct/StockDetail.jsp?sku=${j.getSku()}">${j.getSku()}</a></b>${j.getProductName()}<br/>
                    </c:forEach>
                    </td>
                    <td colspan="3" class="warehouseLocation" id="${i.getIordersMaster().getQrId()}">
                    <c:forEach var="k" items="${i.getIordersDetails()}" begin="0" step="1" varStatus="check">
                      <b>${k.getWarehouse()}</b><br/>
                    </c:forEach>
                    </td>
                  </tr>
                 
                </c:when>
                <c:otherwise>
                  <tr>
                   <td rowspan="2" style="vertical-align:middle"><input type="checkbox" name="QR_id" value="${i.getIordersMaster().getQrId()}"></td>
                    <td><a href="OrderDetail.jsp?QR_id=${i.getIordersMaster().getQrId()}"><img src="../img/compose-4.png" ></a></td>
                    <td>${i.getIordersMaster().getQrId()}</td>
                    <td>${i.getIordersMaster().getPlatform()}</td>
                    <td>${i.getIordersMaster().getGuestId()}</td>
                    <td>${i.getGuestInfo().getName()}</td>
                    <td>${i.getIordersMaster().getPayDate()}</td>
                    <td></td>
                    <td>${i.getIordersMaster().getLogistics()}</td>
                    <td>${i.getGuestInfo().getCountry()}</td>
                    <td>${i.getIordersMaster().getOrderStatus()}
                      <input type="hidden" name="status" value="${i.getIordersMaster().getOrderStatus()}"></td>
                    <td>${i.getIordersMaster().getTotalPrice()}${i.getIordersMaster().getCurrency()}</td>
                    <td>${i.getIordersMaster().getStaffName()}</td>
                  </tr>
                  <tr style="background-color:#D4F4D8">
					<td colspan="9">
                    <c:forEach var="j" items="${i.getIordersDetails()}" begin="0" step="1" varStatus="check">
<%--                       <a href='#' class='pop' ><img src='/pics/${j.getPicPath()}' style='width: 20px; height: 20px;'></a> --%>
                      <b><a href="../QRProduct/StockDetail.jsp?sku=${j.getSku()}">${j.getSku()}</a></b>${j.getProductName()}<br/>
                    </c:forEach>
                    </td>
                    <td colspan="3" class="warehouseLocation" id="${i.getIordersMaster().getQrId()}">
                    <c:forEach var="k" items="${i.getIordersDetails()}" begin="0" step="1" varStatus="check">
                      <b>${k.getWarehouse()}</b><br/>
                    </c:forEach>
                    </td>
                  </tr>
                 
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </table>
          <div class="row text-center" >
            <button type="submit" name="send" value="processing" class="btn btn-lg btn-primary">送出</button>
          </div>
        </form>
      </div>
</div>

<%@ include file="../href/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('.warehouseLocation').each(function(){
		if($(this).text() == undefined || $(this).text().trim() == ""){
			$(this).addClass("danger");
		} else {
		    $(this).addClass("success");
		}
	});
});
function selectAllOrders(ele) {
	//select all
	if (ele.checked) {
		$("input[name=QR_id]").prop("checked", true);
    } else {
    	$("input[name=QR_id]").prop("checked", false);
    }
};
function isSubmited() {
	var id;
	var tdWarehouseClass;
	var bool = true; 
	var count = 0;
	$('input[name=QR_id]:checked').each(function(){
		count = count + 1;
		id = $(this).val();
		tdWarehouseClass = $('#' + id).attr('class');
		if(tdWarehouseClass.endsWith('danger')) {
			bool = false;
			return false;
		}
	});
	if (count == 0) {
		alert('請勾選訂單');
		return false;
	}
	
	return bool;
};
</script>
</body>
  
</html>