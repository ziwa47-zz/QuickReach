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
<title>Upload Tracking Code</title>
</head>
<body>
<%@ include file = "/href/navbar.jsp"%>
<%
	COrderFactory.checkUrlToRemoveSession(request, session);
	Connection conn = new DataBaseConn().getConn();
	LinkedList<COrders> orderList = COrderFactory.orders(request,conn,"已出貨");
	session.setAttribute("list", orderList);
    request.setAttribute("begin", request.getParameter("begin"));
    request.setAttribute("end", request.getParameter("end"));
%>

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
           <li><a href="OrderUploadTrackingCode.jsp?begin=0&end=10" style="color:#fff">上傳追蹤碼</a></li>
           <li><a href="OrderFinished.jsp?begin=0&end=10">已完成訂單</a></li>
           <li><a href="OrderAbnormal.jsp?begin=0&end=10">異常訂單</a></li>
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
  

<br/>
<div class="container table-responsive bg-warning" style=" border-radius:20px">
        <form name="searchform" method="post" action="../StatusDo" class="form-inline container"
          style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
          <div class="row">
        	<div class="col-md-8 form-group ">
              <div class="row">
                <div class="col-md-2"><h5><label>追蹤碼：</label></h5></div>
                <div class="col-md-10"><input class="form-control" name="trackingCode" type="text"></div>
              </div>
            </div>
          </div>
          <button type="submit" name="send" value="sendTrackingCode" class="btn-lg btn-primary">送出追蹤碼</button>
      
          <ul class="pager pagination">
            <c:choose>
              <c:when test="${begin != 0}">
                <li><a href="OrderUploadTrackingCode.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="OrderUploadTrackingCode.jsp?begin=${begin-10}&end=${end-10}">上一頁</a></li>
              </c:otherwise>
            </c:choose>
            <c:forEach begin="0" end="${list.size()/10}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${(check.index*10) != begin}">
                  <li><a href="OrderUploadTrackingCode.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:when>
                <c:otherwise>
                  <li class="active"><a href="OrderUploadTrackingCode.jsp?begin=${check.index*10}&end=${(check.index+1)*10}">${check.index+1}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:choose>
              <c:when test="${end < list.size()}">
                <li><a href="OrderUploadTrackingCode.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
              </c:when>
              <c:otherwise>
                <li class="disabled"><a href="OrderUploadTrackingCode.jsp?begin=${begin+10}&end=${end+10}">下一頁</a></li>
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
            <tr class="ListTitle">
              <th>選取</th>
              <th>編輯</th>
              <th colspan="2">訂單編號</th>
              <th>平台</th>
              <th>Ebay Account</th>
              <th>客戶帳號</th>
              <th>購買日期</th>
              <th>出貨日期</th>
              <th>物流</th>
              <th>訂單狀態</th>
              <th>總金額</th>
              <th>使用者</th>
            </tr>
            <c:forEach var="i" items="${list}" begin="${begin}" end="${end}" step="1" varStatus="check">
              <c:choose>
                <c:when test="${check.index%2 != 0}">
                  <tr style="background-color:#D4F4D8">
                    <td rowspan="3" style="vertical-align:middle"><input type="checkbox" name="QR_id" value="${i.getCOrderMaster().getQR_id()}"></td>
                    <td><a href="OrderDetail.jsp?QR_id=${i.getCOrderMaster().getQR_id()}"><img src="../img/compose-4.png" ></a></td>
                    <td nowrap>${i.getCOrderMaster().getOrder_id()}
                    <td nowrap><a href="#"><img src="../img/compose.png" ></a></td>
                    <td>${i.getCOrderMaster().getPlatform()}</td>
                    <td>${i.getCOrderMaster().getEbayAccount()}</td>
                    <td><a href="#">${i.getCOrderMaster().getGuestAccount()}</a></td>
                    <td>${i.getCOrderMaster().getPayDate()}</td>
                    <td>${i.getCOrderMaster().getShippingDate()}</td>
                    <td>${i.getCOrderMaster().getLogistics()}</td>
                    <td>${i.getCOrderMaster().getOrderStatus()}
                      <input type="hidden" name="status" value="${i.getCOrderMaster().getOrderStatus()}"></td>
                    <td>${i.getCOrderMaster().getTotalPrice()}</td>
                    <td>${i.getCOrderMaster().getStaffName()}</td>
                  </tr>
                  <tr style="background-color:#D4F4D8">
                    <td colspan="9"><c:forEach var="i" items="${i.COrderDetail}" begin="0" step="1" varStatus="check"><b><a href="#">${i.getSKU()}</a></b>${i.getProductName()}
                        (SKU/productName)</c:forEach></td>
                    <td colspan="3"></td>
                  </tr>
                  <tr style="background-color:#D4F4D8">
                    <td colspan="12">${i.getCOrderMaster().getComment()}</td>
                  </tr>
                </c:when>
                <c:otherwise>
                  <tr>
                    <td rowspan="3" style="vertical-align:middle"><input type="checkbox" name="QR_id" value="${i.getCOrderMaster().getQR_id()}"></td>
                    <td><a href="OrderDetail.jsp?QR_id=${i.getCOrderMaster().getQR_id()}"><img src="../img/compose-4.png" ></a></td>
                    <td nowrap>${i.getCOrderMaster().getOrder_id()}
                    <td nowrap><a href="#"><img src="../img/compose.png" ></a></td>
                    <td>${i.getCOrderMaster().getPlatform()}</td>
                    <td>${i.getCOrderMaster().getEbayAccount()}</td>
                    <td><a href="#">${i.getCOrderMaster().getGuestAccount()}</a></td>
                    <td>${i.getCOrderMaster().getPayDate()}</td>
                    <td>${i.getCOrderMaster().getShippingDate()}</td>
                    <td>${i.getCOrderMaster().getLogistics()}</td>
                    <td>${i.getCOrderMaster().getOrderStatus()}
                      <input type="hidden" name="status" value="${i.getCOrderMaster().getOrderStatus()}"></td>
                    <td>${i.getCOrderMaster().getTotalPrice()}</td>
                    <td>${i.getCOrderMaster().getStaffName()}</td>
                  </tr>
                  <tr>
                    <td colspan="9"><c:forEach var="i" items="${i.COrderDetail}" begin="0" step="1" varStatus="check"><b><a href="#">${i.getSKU()}</a></b>${i.getProductName()}<br/>
                      </c:forEach></td>
                    <td colspan="3"></td>
                  </tr>
                  <tr>
                    <td colspan="12">${i.getCOrderMaster().getComment()}</td>
                  </tr>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </table>
          <div class="row text-center" >
          </div>
        </form>
      </div>
      </div>
<!-- <nav class="navbar navbar-default navbar-fixed-bottom" role="navigation" style="background-color:#000"> -->
<!--   	<div class="container">							   -->
<!--       <div class="row" >            -->
<!--           <p class="col-md-5 copyright" id="copyright" >Copyright © 2016 III South -->
<!--           </p>            -->
<!--           <p class="text-center footer-bot" id="link">           -->
<!--               <a href="#">關於我們</a>｜ -->
<!--               <a href="#">服務合約書</a>｜            -->
<!--               <a href="#">連絡我們</a>         -->
<!--           </p>          -->
<!--       </div>      -->
<!--     </div> -->
<!-- </nav> -->
</body>
</html>