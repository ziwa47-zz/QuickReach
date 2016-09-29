<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.DataBaseConn"%>
<%@ page import="tw.iii.qr.order.COrders"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<jsp:useBean id="COrderFactory" class="tw.iii.qr.order.COrderFactory" scope="page" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>查詢訂單</title>
</head>
<body>
<%@ include file = "../href/navbar.jsp"%>
<%
  COrderFactory.checkUrlToRemoveSession(request, session);
  Connection conn = new DataBaseConn().getConn();
  LinkedList<COrders> orderList = new LinkedList<COrders>();
  LinkedList<String> ebayAccounts = COrderFactory.getEbayAccounts(conn);
  session.setAttribute("list", orderList);
  request.setAttribute("begin", request.getParameter("begin"));
  request.setAttribute("end", request.getParameter("end"));
  session.setAttribute("ebayAccounts", ebayAccounts);
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#F3CE9A;" >
      <ul class="nav nav-tabs">
        <li class="" style="background-color:#A45A21"><a href="SearchOrder.jsp?begin=0&end=10" style="color:#FFFFFF">訂單管理</a></li>
        <li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#A45A21;" >
      <ul class="nav nav-tabs">
        <li><a href="SearchOrder.jsp?begin=0&end=10" style="color:#fff">查詢訂單</a></li>
        <li><a href="OrderProcessingPage.jsp?begin=0&end=10">處理中</a></li>
        <li><a href="OrderPickupPage.jsp?begin=0&end=10">揀貨中</a></li>
        <li><a href="OrderUploadTrackingCode.jsp?begin=0&end=10">上傳追蹤碼</a></li>
        <li><a href="OrderFinished.jsp?begin=0&end=10">已完成訂單</a></li>
        <li><a href="ShipmentRecord.jsp?begin=0&end=10" >訂單出貨記錄</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
    <li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
  </ol>
</div>
<div class="nav">
  <div class="container" style="background: #D9A56B; border-radius:20px;">
    <form name="searchform" method="post" action="../OrdersServlet" class="form-inline container"
    style="font-size: 100%; vertical-align: baseline; padding: 15px;">
      <fieldset class="font-weight" style="padding:0 30px 0 0;">
        <legend>查詢訂單</legend>
        <div class="row">
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>ebay account：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <select class="form-control" name="eBayAccount">
                  <option value="">請選擇</option>
                  <c:forEach var="q" items="${ebayAccounts}" step="1" varStatus="check">
                  <option value="">${q}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>訂單編號：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <input class="form-control" name="ebayNO" type="text">
              </div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>P/P帳號：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <input class="form-control" name="paypal_id" type="text">
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2">
                <h5>
                  <label>出貨單號：</label>
                </h5>
              </div>
              <div class="col-md-6 input-group" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="QR_id" type="text" style="border-radius: 4px">
              </div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>E/B帳號：</label>
                </h5>
              </div>
              <div class="col-md-8" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="guestAccount" type="text" style="border-radius: 4px">
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2">
                <h5>
                  <label>Tracking Code：</label>
                </h5>
              </div>
              <div class="col-md-6 input-group" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="trackingCode" type="text" style="border-radius: 4px">
              </div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>客戶姓名：</label>
                </h5>
              </div>
              <div class="col-md-8" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="guestLastName" type="text" style="border-radius: 4px">
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
          <div class="col-md-12 form-group">
            <label>訂單狀態：</label>
            <label class="checkbox-inline"><input type="checkbox" name="allOrders" value="allOrders" onchange="checkAllOrders(this)">全部</label>
            <label class="checkbox-inline"><input type="checkbox" name="waitProcess" value="waitProcess">待處理</label>
            <label class="checkbox-inline"><input type="checkbox" name="processing"  value="processing">處理中</label>
            <label class="checkbox-inline"><input type="checkbox" name="pickup"  value="pickup">揀貨中</label>
            <label class="checkbox-inline"><input type="checkbox" name="shipped" value="shipped">已出貨</label>
            <label class="checkbox-inline"><input type="checkbox" name="finished" value="finished">已完成</label>
            <label class="checkbox-inline"><input type="checkbox" name="refund" value="refund">退款</label>
            <label class="checkbox-inline"><input type="checkbox" name="oothers" value="oothers">其他</label>
            <label class="checkbox-inline"><input type="checkbox" name="deducted" value="deducted">退貨中(扣庫存)</label>
          </div>
        </div>
        <br/>
        <div class="row">
          <div class="col-md-12 form-group ">
            <label>物流選擇：</label>
            <label class="checkbox-inline"><input type="checkbox" name="DHL" value="DHL">DHL</label>
            <label class="checkbox-inline"><input type="checkbox" name="Fedex" value="Fedex">Fedex</label>
            <label class="checkbox-inline"><input type="checkbox" name="EMS" value="EMS">EMS</label>
            <label class="checkbox-inline"><input type="checkbox" name="AP" value="AP">AP(國際包裹)</label>
            <label class="checkbox-inline"><input type="checkbox" name="RA" value="RA">RA(國際掛號)</label>
            <label class="checkbox-inline"><input type="checkbox" name="USPS1" value="USPS1">USPS寄倉</label>
            <label class="checkbox-inline"><input type="checkbox" name="USPS2" value="USPS2">USPS集運</label>
            <label class="checkbox-inline"><input type="checkbox" name="seven" value="seven">7-11取貨付款</label>
            <label class="checkbox-inline"><input type="checkbox" name="familyMart" value="familyMart">全家取貨付款</label>
            <label class="checkbox-inline"><input type="checkbox" name="post" value="post">郵局快捷貨到付款</label>
            <label class="checkbox-inline"><input type="checkbox" name="lothers" value="lothers">其他</label>
          </div>
        </div>
        <br/>
        <div class="row">
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2">
                <h5>
                  <label>備註：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <textarea rows="4" cols="50" class="form-control"></textarea>
              </div>
            </div>
          </div>
        </div>
        <br/>
        <div class="row text-center" >
          <button class="btn btn-lg btn-primary" type="submit" name="submit" value="orderSearch">搜尋</button>
          <button class="btn btn-lg btn-primary" type="button" name="" >清空</button>
        </div>
      </fieldset>
    </form>
  </div>
  <hr/>
  
  <c:choose>
    <c:when test="${SearchOrdersResult != null}">
      <div class="container table-responsive bg-warning" style=" border-radius:20px">
        <form name="searchform" method="post" action="" class="form-inline container"
          style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
          <ul class="pager pagination">
            <c:choose>
              <c:when test="${begin != 0}">
                <li><a href="SearchOrder.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="SearchOrder.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:otherwise>
            </c:choose>
            <c:forEach begin="0" end="${SearchOrdersResult.size()/10}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${(check.index*10) != begin}">
                  <li><a href="SearchOrder.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:when>
                <c:otherwise>
                  <li class="active"><a href="SearchOrder.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:choose>
              <c:when test="${end < SearchOrdersResult.size()}">
                <li><a href="SearchOrder.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="SearchOrder.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:otherwise>
            </c:choose>
          </ul>
		  <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
            <tr class="ListTitle">
              <th nowrap>選取</th>
              <th nowrap>編輯</th>
              <th>訂單編號</th>
              <th>平台</th>
              <th>Ebay Account</th>
              <th>客戶帳號</th>
              <th>購買日期</th>
              <th>出貨日期</th>
              <th>物流</th>
              <th>國家</th>
              <th>訂單狀態</th>
              <th>總金額</th>
              <th nowrap>使用者</th>
            </tr>
            <c:forEach var="i" items="${SearchOrdersResult}" begin="${begin}" end="${end}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${check.index%2 != 0}">
                  <tr style="background-color:#D4F4D8">
                    <td rowspan="3" style="vertical-align:middle"><input type="checkbox" name="QR_id" value="${i.getCOrderMaster().getQR_id()}"></td>
                    <td><a href="OrderDetail.jsp?QR_id=${i.getCOrderMaster().getQR_id()}"><img src="../img/compose-4.png" ></a></td>
                    <td>${i.getCOrderMaster().getEbayNO()}
                    <td>${i.getCOrderMaster().getPlatform()}</td>
                    <td>${i.getCOrderMaster().getEbayAccount()}</td>
                    <td>${i.getCOrderMaster().getGuestAccount()}</td>
                    <td>${i.getCOrderMaster().getPayDate()}</td>
                    <td></td>
                    <td>${i.getCOrderMaster().getLogistics()}</td>
                    <td>${i.getCOrderReciever().getCountry()}</td>
                    <td>${i.getCOrderMaster().getOrderStatus()}
                    <input type="hidden" name="status" value="${i.getCOrderMaster().getOrderStatus()}"></td>
                    <td>${i.getCOrderMaster().getTotalPrice()}</td>
                    <td>${i.getCOrderMaster().getStaffName()}</td>
                  </tr>
                  <tr style="background-color:#D4F4D8">
                    <td colspan="9">
                    <c:forEach var="j" items="${i.COrderDetail}" begin="0" step="1" varStatus="check">
                      <b><a href="#">${j.getSKU()}</a></b>${j.getProductName()}<br/>
                    </c:forEach>
                    </td>
                    <td colspan="3">
                    <c:forEach var="k" items="${i.COrderDetail}" begin="0" step="1" varStatus="check">
                      <b>${k.getWarehouse()}</b>(倉別)<br/>
                    </c:forEach>
                    </td>
                  </tr>
                  <tr style="background-color:#D4F4D8">
                    <td colspan="12">${i.getCOrderMaster().getComment()}</td>
                  </tr>
                </c:when>
                <c:otherwise>
                  <tr>
                    <td rowspan="3" style="vertical-align:middle"><input type="checkbox" name="QR_id" value="${i.getCOrderMaster().getQR_id()}"></td>
                    <td><a href="OrderDetail.jsp?QR_id=${i.getCOrderMaster().getQR_id()}"><img src="../img/compose-4.png" ></a></td>
                    <td>${i.getCOrderMaster().getEbayNO()}
                      <input type="hidden" name="orderId" value="${i.getCOrderMaster().getOrder_id()}"></td>
                    <td>${i.getCOrderMaster().getPlatform()}</td>
                    <td>${i.getCOrderMaster().getEbayAccount()}</td>
                    <td>${i.getCOrderMaster().getGuestAccount()}</td>
                    <td>${i.getCOrderMaster().getPayDate()}</td>
                    <td></td>
                    <td>${i.getCOrderMaster().getLogistics()}</td>
 					<td>${i.getCOrderReciever().getCountry()}</td>                   
                    <td>${i.getCOrderMaster().getOrderStatus()}
                      <input type="hidden" name="status" value="${i.getCOrderMaster().getOrderStatus()}"></td>
                    <td>${i.getCOrderMaster().getTotalPrice()}</td>
                    <td>${i.getCOrderMaster().getStaffName()}</td>
                  </tr>
                  <tr>
					<td colspan="9">
                    <c:forEach var="j" items="${i.COrderDetail}" begin="0" step="1" varStatus="check">
                      <b><a href="#">${j.getSKU()}</a></b>${j.getProductName()}<br/>
                    </c:forEach>
                    </td>
                    <td colspan="3">
                    <c:forEach var="k" items="${i.COrderDetail}" begin="0" step="1" varStatus="check">
                      <b>${k.getWarehouse()}</b>(倉別)<br/>
                    </c:forEach>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="12">${i.getCOrderMaster().getComment()}</td>
                  </tr>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </table>
          <div class="row text-center" >
<!--             <button type="submit" name="" class="btn-lg btn-primary">送出</button> -->
          </div>
        </form>
      </div>
    </c:when>
    <c:otherwise>
      <div class="container table-responsive bg-warning" style=" border-radius:20px">
        <form name="searchform" method="post" action="../StatusDo" class="form-inline container"
          style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
          <ul class="pager pagination">
            <c:choose>
              <c:when test="${begin != 0}">
                <li><a href="SearchOrder.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="SearchOrder.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:otherwise>
            </c:choose>
            <c:forEach begin="0" end="${list.size()/10}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${(check.index*10) != begin}">
                  <li><a href="SearchOrder.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:when>
                <c:otherwise>
                  <li class="active"><a href="SearchOrder.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:choose>
              <c:when test="${end < list.size()}">
                <li><a href="SearchOrder.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="SearchOrder.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:otherwise>
            </c:choose>
            <label>共有:${list.size()}筆</label>
          </ul>
          <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
            <tr class="ListTitle">
              <th nowrap>選取</th>
              <th nowrap>編輯</th>
              <th>訂單編號</th>
              <th>平台</th>
              <th>Ebay Account</th>
              <th>客戶帳號</th>
              <th>購買日期</th>
              <th>出貨日期</th>
              <th>物流</th>
              <th>國家</th>
              <th>訂單狀態</th>
              <th>總金額</th>
              <th nowrap>使用者</th>
            </tr>
            <c:forEach var="i" items="${list}" begin="${begin}" end="${end}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${check.index%2 != 0}">
                  <tr style="background-color:#D4F4D8">
                    <td rowspan="3" style="vertical-align:middle"><input type="checkbox" name="QR_id" value="${i.getCOrderMaster().getQR_id()}"></td>
                    <td><a href="OrderDetail.jsp?QR_id=${i.getCOrderMaster().getQR_id()}"><img src="../img/compose-4.png" ></a></td>
                    <td>${i.getCOrderMaster().getEbayNO()}
                    <td>${i.getCOrderMaster().getPlatform()}</td>
                    <td>${i.getCOrderMaster().getEbayAccount()}</td>
                    <td>${i.getCOrderMaster().getGuestAccount()}</td>
                    <td>${i.getCOrderMaster().getPayDate()}</td>
                    <td></td>
                    <td>${i.getCOrderMaster().getLogistics()}</td>
                    <td>${i.getCOrderReciever().getCountry()}</td>
                    <td>${i.getCOrderMaster().getOrderStatus()}
                      <input type="hidden" name="status" value="${i.getCOrderMaster().getOrderStatus()}"></td>
                    <td>${i.getCOrderMaster().getTotalPrice()}${i.getCOrderMaster().getCurrency()}</td>
                    <td>${i.getCOrderMaster().getStaffName()}</td>
                  </tr>
                  <tr style="background-color:#D4F4D8">
					<td colspan="9">
                    <c:forEach var="j" items="${i.COrderDetail}" begin="0" step="1" varStatus="check">
                      <b><a href="../QRProduct/StockDetail.jsp?sku=${j.getSKU()}">${j.getSKU()}</a></b>${j.getProductName()}<br/>
                    </c:forEach>
                    </td>
                    <td colspan="3">
                    <c:forEach var="k" items="${i.COrderDetail}" begin="0" step="1" varStatus="check">
                      <b>${k.getWarehouse()}</b>(倉別)<br/>
                    </c:forEach>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="12">${i.getCOrderMaster().getComment()}</td>
                  </tr>
                </c:when>
                <c:otherwise>
                  <tr>
                    <td rowspan="3" style="vertical-align:middle"><input type="checkbox" name="QR_id" value="${i.getCOrderMaster().getQR_id()}"></td>
                    <td><a href="OrderDetail.jsp?QR_id=${i.getCOrderMaster().getQR_id()}"><img src="../img/compose-4.png" ></a></td>
                    <td>${i.getCOrderMaster().getEbayNO()}
                    <td>${i.getCOrderMaster().getPlatform()}</td>
                    <td>${i.getCOrderMaster().getEbayAccount()}</td>
                    <td>${i.getCOrderMaster().getGuestAccount()}</td>
                    <td>${i.getCOrderMaster().getPayDate()}</td>
                    <td></td>
                    <td>${i.getCOrderMaster().getLogistics()}</td>
                    <td>${i.getCOrderReciever().getCountry()}</td>
                    <td>${i.getCOrderMaster().getOrderStatus()}
                      <input type="hidden" name="status" value="${i.getCOrderMaster().getOrderStatus()}"></td>
                    <td>${i.getCOrderMaster().getTotalPrice()}${i.getCOrderMaster().getCurrency()}</td>
                    <td>${i.getCOrderMaster().getStaffName()}</td>
                  </tr>
                  <tr>
					<td colspan="9">
                    <c:forEach var="j" items="${i.COrderDetail}" begin="0" step="1" varStatus="check">
                      <b><a href="../QRProduct/StockDetail.jsp?sku=${j.getSKU()}">${j.getSKU()}</a></b>${j.getProductName()}<br/>
                    </c:forEach>
                    </td>
                    <td colspan="3">
                    <c:forEach var="k" items="${i.COrderDetail}" begin="0" step="1" varStatus="check">
                      <b>${k.getWarehouse()}</b>(倉別)<br/>
                    </c:forEach>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="12">${i.getCOrderMaster().getComment()}</td>
                  </tr>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </table>
          <div class="row text-center" >
<!--             <button type="submit" name="" class="btn-lg btn-primary">送出</button> -->
          </div>
        </form>
      </div>
    </c:otherwise>
  </c:choose>
</div>
<%@ include file="../href/footer.jsp" %>
</body>
  
</html>