<%@page import="tw.iii.qr.order.COrderDetail"%>
<%@page import="tw.iii.qr.order.COrders"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*,tw.iii.qr.stock.CProduct" %>
<%@page import="tw.iii.qr.DataBaseConn"%>
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
<%
String QR_id ;
request.setCharacterEncoding("UTF-8");
if(request.getParameter("QR_id") != null || request.getParameter("QR_id") != ""){
Connection conn = new DataBaseConn().getConn();
QR_id = request.getParameter("QR_id");
COrders searchResult = COrderFactory.getOrderAllInfo(QR_id, conn);
session.setAttribute("result", searchResult);
LinkedList<COrderDetail> resultDetail = COrderFactory.getOrderDetails(QR_id, conn);
session.setAttribute("resultDetail", resultDetail);

conn.close();
}else {
	response.sendRedirect("QROrders/SearchOrder.jsp");	
}
%>
 <div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#F3CE9A;" >
      <ul class="nav nav-tabs">
        <li class="" style="background-color:#A45A21"><a href="SearchOrder.jsp" style="color:#FFFFFF">訂單管理</a></li>
        <li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#A45A21;" >
      <ul class="nav nav-tabs">
        <li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
        <li><a href="OrderProcessingPage.jsp?begin=0&end=10" style="color:#fff">處理中</a></li>
        <li><a href="OrderPickupPage.jsp?begin=0&end=10">揀貨中</a></li>
        <li><a href="OrderUploadTrackingCode.jsp?begin=0&end=10">上傳追蹤碼</a></li>
        <li><a href="OrderFinished.jsp?begin=0&end=10">已完成訂單</a></li>
        <li><a href="OrderAbnormal.jsp?begin=0&end=10">異常訂單</a></li>
        <li><a href="ShipmentRecord.jsp?begin=0&end=10" >訂單出貨記錄</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
    <li><a href="OrderDetail.jsp?QR_id=${result.getCOrderMaster().getQR_id()}">訂單明細</a></li>
  </ol>
</div>

  <div class="container table-responsive" style="background: #D9A56B; border-radius:20px;">
  	<form name="searchform" method="post" action="/OrdersServlet" class="form-inline container" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
  	
  	<div class="row">
      <label for="inputPassword" class="col-md-2 control-label text-left">編輯模式</label>
      <div class="col-md-4">
        <label class="radio-inline"><input type="radio" name="optionsRadios" id="optionsRadios1">開啟</label>
        <label class="radio-inline"><input type="radio" name="optionsRadios" id="optionsRadios2">關閉</label>
    	<label class="radio-inline">
    	 <button type="submit" name="submit" value="updateOrder" class="btn-lg btn-success">更新訂單資料</button>
      	</label>
      </div>
    </div>
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
		          <div class="col-md-3 text-right well-sm label-tag"><h4>客戶名子</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getGuestFirstName()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>客戶姓氏</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getGuestLastName()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>訂購帳號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getGuestAccount()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電子郵件</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getEmail()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話(日)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getTel1()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話(夜)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getTel2()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>行動電話</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getMobile()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>生日</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getBirthday()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>公司/學校</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getCompany()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>地址</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getAddress()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>國家</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getCountry()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>郵遞區號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getPostcode()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>性別</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderGuestInfo().getGender()}"></div>
		        </div>
		      </div>
            </div>
          </div>
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">收件人資料</a>
            </h4>
          </div>
          <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
              <div class="container-fluid form-horizontal">
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>收件人名字</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderReciever().getRecieverFirstName()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>收件人姓氏</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderReciever().getRecieverLastName()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話1</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderReciever().getTel1()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話2</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderReciever().getTel2()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>地址</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderReciever().getAddress()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>收件人國家</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderReciever().getCountry()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>郵遞區號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderReciever().getPostCode()}"></div>
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
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>外部訂單編號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getOutsideCode()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>訂單狀態</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getOrderStatus()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>訂單編號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="QR_id" value="${result.getCOrderMaster().getQR_id()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>公司</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getCompany()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>平台</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" name="platform" value="${result.getCOrderMaster().getPlatform()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>ebay 帳號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getEbayAccount()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>購買日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getOrderDate()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>付款日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getPayDate()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>付款方式</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>paypal 交易序號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getPaypalId()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>出貨日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getShippingDate()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>物流配送方式</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getLogistics()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>提早出貨</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>出貨編號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>運費</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getShippingFees()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>退運費</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>其它費用</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getOtherFees()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>其它費用備註</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>其它收入</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>其它收入備註</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>ebay成交費</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getEbayFees()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>計算保價</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>paypal費用</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getPaypalFees()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>淨重(公克)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>毛重(公克)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>Fedex服務</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>長/寛/高</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value=""></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>備註</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getComment()}"></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>總計</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="${result.getCOrderMaster().getTotalPrice()}"></div>
		        </div>
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
			        <th>商品名稱 / invoice名稱</th>
			        <th>成交價</th>
			        <th>invoice價格</th>
			        <th>數量</th>
			        <th>備註</th>
		          </tr>
	            </thead>
	            <c:forEach var="i" items="${resultDetail}" begin="0" step="1" varStatus="check">
		        <tbody>
		          <tr>
		            <td><input class="" type="text" name="SKU" value="${i.getSKU()}"></td>
		            <td>Product Name:<br/>${i.getProductName()}<br/>
						Invoice Name:<br/><input class="" type="text" name="invoiceName" value="${i.getInvoiceName()}">
		            </td>
		            <td><input class="" type="text" name="price" value="${i.getPrice()}"></td>
		            <td><input class="" type="text" name="invoicePrice" value="${i.getInvoicePrice()}"></td>
		            <td><input class="" type="text" name="qty" value="${i.getQty()}"></td>
		            <td>備註:<input class="" type="text" name="comment" value="${i.getComment()}">
		            <input type="hidden" name="item1" value="${i.getItem()}">
		            <p>${i.getItem()}</p>
		            </td>
		          </tr>
		        </tbody>
		        </c:forEach>
		        
		      </table>
            </div>
          </div>
        </div>
       
      </div>
    </fieldset>
    </form>
  </div>
</body>
</html>