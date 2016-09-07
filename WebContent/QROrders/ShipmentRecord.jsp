<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.DataBaseConn"%>
<%@ page import="tw.iii.qr.order.COrders"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<jsp:useBean id="COrderFactory" class="tw.iii.qr.order.COrderFactory" scope="page" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出貨記錄</title>
</head>
<body>

<%@ include file = "/href/navbar.jsp"%>

<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#F3CE9A;" >
      <ul class="nav nav-tabs">
        <li class="" style="background-color:#A45A21"><a href="SearchOrder.jsp" style="color:#FFFFFF">訂單管理</a></li>
        <li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
        <li><a href="SearchComment.jsp">查詢評價</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#A45A21;" >
      <ul class="nav nav-tabs">
        <li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
        <li><a href="OrderProcessingPage.jsp?begin=0&end=10">處理中</a></li>
        <li><a href="OrderPickupPage.jsp?begin=0&end=10">揀貨中</a></li>
        <li><a href="OrderUploadTrackingCode.jsp?begin=0&end=10">上傳追蹤碼</a></li>
        <li><a href="OrderFinished.jsp?begin=0&end=10">已完成訂單</a></li>
        <li><a href="OrderAbnormal.jsp?begin=0&end=10">異常訂單</a></li>
        <li><a href="ShipmentRecord.jsp" style="color:#fff">出貨記錄</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="#" >主要目錄</a></li>
    <li class="active" style="display:"><a href="#">訂單</a></li>
    <li><a href="#">訂單查詢</a></li>
  </ol>
</div>

<div class="nav">
  <div class="container" style="background: #D9A56B; border-radius:20px;">
    <form name="searchform" method="post" action="../OrdersServlet" class="form-inline container"
    style="font-size: 100%; vertical-align: baseline; padding: 15px;">
      <fieldset class="font-weight" style="padding:0 30px 0 0;">
        <legend>出貨記錄查詢</legend>
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
                  <option value="comenwin0903">comenwin0903</option>
                  <option value="cyclistbike">cyclistbike</option>
                  <option value="huangbowei">huangbowei</option>
                  <option value="igrocery">igrocery</option>
                  <option value="magicbike">magicbike</option>
                </select>
              </div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>ebay訂單編號：</label>
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
          <button class="btn-lg btn-primary" type="submit" name="submit" value="finishedSearch">搜尋</button>
          <button class="btn-lg btn-primary" type="button" name="" >清空</button>
        </div>
      </fieldset>
    </form>
  </div>
  <hr/>
  <div class="container table-responsive bg-warning" style=" border-radius:20px">
    <form name="searchform" method="post" action="../SubmitToShipped" class="form-inline container"
          style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
      <button type="submit" name="" class="btn-sm btn-info">選擇全部</button>
      <button type="submit" name="" class="btn-sm btn-info">清除勾選</button>
      <button type="submit" name="" class="btn-sm btn-info">列印撿貨/出貨單</button>
      <button type="submit" name="" class="btn-sm btn-info">列印EMS</button>
      <button type="submit" name="" class="btn-sm btn-info">列印Invoice</button>
      <button type="submit" name="" class="btn-sm btn-info">回復</button>
      <ul class="pager pagination">
        <c:choose>
          <c:when test="${begin != 0}">
            <li><a href="OrderFinished.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
          </c:when>
          <c:otherwise>
            <li class="disabled"><a href="OrderFinished.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
          </c:otherwise>
        </c:choose>
        <c:forEach begin="0" end="${list.size()/10}" step="1" varStatus="check">
          <c:choose>
            <c:when test="${(check.index*10) != begin}">
              <li><a href="OrderFinished.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
            </c:when>
            <c:otherwise>
              <li class="active"><a href="OrderFinished.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
            </c:otherwise>
          </c:choose>
        </c:forEach>
        <c:choose>
          <c:when test="${end < list.size()}">
            <li><a href="OrderFinished.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
          </c:when>
          <c:otherwise>
            <li class="disabled"><a href="OrderFinished.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
          </c:otherwise>
        </c:choose>
        <label>共有:${list.size()}筆</label>
        <label>顯示
          <select class="form-control" name="showCounts" onChange="">
            <option value="10">10</option>
            <option value="25">25</option>
            <option value="50">50</option>
          </select>
          筆</label>
      </ul>
      <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
        <thead>
          <tr class="ListTitle">
            <th>出貨日期</th>
            <th>出貨編號</th>
            <th>類型</th>
            <th>Ebay Account</th>
            <th>QR_id</th>
            <th>SKU</th>
            <th>品名</th>
            <th>數量</th>
            <th>寄送國家</th>
            <th>Owner</th>
            <th>出倉位置</th>
            <th>處理人員</th>
            <th>備註</th>
          </tr>
        </thead>
        <tbody>
          <tr style="background-color:#D4F4D8">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        </tbody>
      </table>
      <div class="row text-center" > 
        <!--             <button type="submit" name="" class="btn-lg btn-primary">送出</button> --> 
      </div>
    </form>
  </div>
</div>

</body>
</html>