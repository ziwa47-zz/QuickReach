<%@page import="tw.iii.qr.order.DTO.ShipmentRecord"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.DataBaseConn"%>
<%@ page import="tw.iii.qr.order.DTO.COrders"%>
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
<c:if test="${PageCompetence.getOrdersManage() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
	COrderFactory.checkUrlToRemoveSession(request, session);
	LinkedList<String> ebayAccounts = COrderFactory.getEbayAccounts();
    request.setAttribute("begin", request.getParameter("begin"));
    request.setAttribute("end", request.getParameter("end"));
    session.setAttribute("ebayAccounts", ebayAccounts);
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#F3CE9A;" >
      <ul class="nav nav-tabs">
        <li class="" style="background-color:#A45A21"><a href="SearchOrder.jsp?begin=0&end=10" style="color:#FFFFFF">訂單管理</a></li>
        <c:if test="${PageCompetence.getEntireOrders() == 1 }"> 
        	<li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
      	</c:if>
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
        <li><a href="ShipmentRecord.jsp?begin=0&end=10" style="color:#fff">訂單出貨記錄</a></li>
        <li><a href="refundPage.jsp?begin=0&end=10" >退貨</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
    <li><a href="ShipmentRecord.jsp?begin=0&end=10">訂單出貨記錄</a></li>
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
                  <label>Ebay帳號：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <select class="form-control" name="eBayAccount">
                  <option value="">請選擇</option>
                  <c:forEach var="q" items="${ebayAccounts}" step="1" varStatus="check">
                  <option value="${q}">${q}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
<!--           <div class="col-md-4 form-group "> -->
<!--             <div class="row"> -->
<!--               <div class="col-md-4"> -->
<!--                 <h5> -->
<!--                   <label>類型：</label> -->
<!--                 </h5> -->
<!--               </div> -->
<!--               <div class="col-md-8"> -->
<!--                 <input class="form-control" name="type" type="text"> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--           <div class="col-md-4 form-group "> -->
<!--             <div class="row"> -->
<!--               <div class="col-md-4"> -->
<!--                 <h5> -->
<!--                   <label>Owner：</label> -->
<!--                 </h5> -->
<!--               </div> -->
<!--               <div class="col-md-8" style="padding-left: 15px; padding-right: 35px"> -->
<!--                 <input class="form-control" name="owner" type="text" style="border-radius: 4px"> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
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
                  <label>寄送國家：</label>
                </h5>
              </div>
              <div class="col-md-8" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="country" type="text" style="border-radius: 4px">
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
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>出貨倉別：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <input class="form-control" name="warehouse" type="text">
              </div>
            </div>
          </div>
        </div>
        <div class="row">
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
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4">
                <h5>
                  <label>處理人員：</label>
                </h5>
              </div>
              <div class="col-md-8">
                <input class="form-control" name="staffName" type="text">
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
          <button class="btn btn-lg btn-primary" type="submit" name="submit" value="shipmentRecordSearch">搜尋</button>
          <button class="btn btn-lg btn-primary" type="reset" name="" >清空</button>
        </div>
      </fieldset>
    </form>
  </div>
  <hr/>
      <div class="container table-responsive bg-warning" style=" border-radius:20px">
        <form name="searchform" method="post" action="../toExcelServlet" class="form-inline container"
          style="font-size: 100%; vertical-align: baseline; padding: 15px; " onsubmit="return noSelected()">
          <button class="btn btn-md btn-info" type="submit" name="submit" value="toDailyBalanceSheetExcel" >匯出日出貨報表</button>
          <ul class="pager pagination">
            <c:choose>
              <c:when test="${begin != 0}">
                <li><a href="ShipmentRecord.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="ShipmentRecord.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:otherwise>
            </c:choose>
            <c:forEach begin="0" end="${shipmentRecord.size()/10}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${(check.index*10) != begin}">
                  <li><a href="ShipmentRecord.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:when>
                <c:otherwise>
                  <li class="active"><a href="ShipmentRecord.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:choose>
              <c:when test="${end < shipmentRecords.size()}">
                <li><a href="ShipmentRecord.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="ShipmentRecord.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:otherwise>
            </c:choose>
            <label>共有:${shipmentRecords.size()}筆</label>
          </ul>
          <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
            <thead>
              <tr class="shipmentRecordTitle">
                <th>出貨日期</th>
                <th>出貨編號</th>
                <th>類型</th>
                <th>Ebay Account</th>
                <th>TrackingCode</th>
                <th>數量</th>
                <th>寄送國家</th>
                <th>Owner</th>
                <th>出倉位置</th>
                <th>處理人員</th>
                <th>備註</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="i" items="${shipmentRecords}" begin="${begin}" end="${end}" step="1" varStatus="check">
                <c:choose>
                  <c:when test="${check.index%2 != 0}">
                    <tr style="background-color:#D4F4D8">
                      <td>${i.getShippingDate()}</td>
                      <td>${i.getQR_id()}</td>
                      <td>${i.getType()}</td>
                      <td>${i.getEbayAccount()}</td>
                      <td>${i.getTrackingCode()}</td>
                      <td>${i.getQty()}</td>
                      <td>${i.getCountry()}</td>
                      <td>${i.getOwner()}</td>
                      <td>${i.getWarehouse()}</td>
                      <td>${i.getStaffName()}</td>
                      <td>${i.getComment()}</td>
                    </tr>
                    <tr style="background-color:#D4F4D8">
                      <td colspan="5"><b>${i.getSKU()}</b>${i.getProductName()}</td>
                      <td colspan="6"></td>
                    </tr>
                  </c:when>
                  <c:otherwise>
                    <tr>
                      <td>${i.getShippingDate()}</td>
                      <td>${i.getQR_id()}</td>
                      <td>${i.getType()}</td>
                      <td>${i.getEbayAccount()}</td>
                      <td>${i.getTrackingCode()}</td>
                      <td>${i.getQty()}</td>
                      <td>${i.getCountry()}</td>
                      <td>${i.getOwner()}</td>
                      <td>${i.getWarehouse()}</td>
                      <td>${i.getStaffName()}</td>
                      <td>${i.getComment()}</td>
                    </tr>
                    <tr>
                      <td colspan="5"><b>${i.getSKU()}</b>${i.getProductName()}</td>
                      <td colspan="6"></td>
                    </tr>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </tbody>
          </table>
        </form>
      </div>
</div>
</body>
</html>