<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="tw.iii.qr.DataBaseConn"%>
<%@ page import="tw.iii.qr.order.DTO.COrderCombine"%>
<%@ page import="tw.iii.qr.order.*"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse"%>
<jsp:useBean id="CombineOrder"
	class="tw.iii.qr.order.DTO.COrderCombineFactory" scope="page" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合併訂單</title>
</head>
<body>
<%@ include file="/href/navbar.jsp"%>
<c:if test="${PageCompetence.getOrdersManage() == 0 }">
<%
	response.sendRedirect("/HomePage.jsp");
%>
</c:if>
<%
	Connection conn = new DataBaseConn().getConn();
	LinkedList<COrderCombine> list = CombineOrder.canCombine(request, conn);
	conn.close();
	session.setAttribute("list", list);
	
	//request.setAttribute("begin", request.getParameter("begin"));
	//request.setAttribute("end", request.getParameter("end"));
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color: #F3CE9A;">
      <ul class="nav nav-tabs">
        <li class="" style="background-color: #A45A21"><a
						href="SearchOrder.jsp?begin=0&end=10" style="color: #FFFFFF">訂單管理</a></li>
        <c:if test="${PageCompetence.getEntireOrders() == 1 }">
          <li><a href="DayliBalanceSheet.jsp">日結表</a></li>
        </c:if>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color: #A45A21;">
      <ul class="nav nav-tabs">
        <li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
        <li><a href="OrderCombine.jsp">合併訂單</a></li>
        <li><a href="OrderProcessingPage.jsp?begin=0&end=10">處理中</a></li>
        <li><a href="OrderPickupPage.jsp?begin=0&end=10">揀貨中</a></li>
        <li><a href="OrderUploadTrackingCode.jsp?begin=0&end=10"
						style="color: #fff">上傳追蹤碼</a></li>
        <li><a href="OrderFinished.jsp?begin=0&end=10">已完成訂單</a></li>
        <li><a href="ShipmentRecord.jsp?begin=0&end=10">訂單出貨記錄</a></li>
        <li><a href="refundPage.jsp?begin=0&end=10">退貨</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb">
    <li><a href="/HomePage.jsp">首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
    <li><a href="OrderCombine.jsp">合併訂單</a></li>
  </ol>
</div>
<div class="nav"> <br />
  <div class="container table-responsive bg-warning" style="border-radius: 20px">
    <form name="searchform" method="post" action="../OrdersServlet" class="form-inline container" style="padding: 15px;">
      <a href="./ReadCombineOrders.jsp" class="btn btn-info" role="button">查看已合併訂單</a>
      <table class="table table-bordered table-hover table-condensed pull-left" style="margin: 0 0 0 -15px">
        <tr class="ListTitle">
          <th>選取</th>
          <th>客戶帳號</th>
          <th>EBayNo1</th>
          <th>商品照</th>
          <th>購買日期</th>
          <th>EBayNo2</th>
          <th>商品照</th>
          <th>購買日期</th>
        </tr>
        <c:forEach var="i" items="${list}" begin="0" step="1" varStatus="check">
          <c:choose>
            <c:when test="${check.index%2 != 0}">
              <tr style="background-color: #D4F4D8">
                <td rowspan="1" style="vertical-align: middle"><input type="checkbox" name="EbayNO" value="${i.getEbayNO1()},${i.getEbayNO2()},${i.getGuestAccount()},${i.getQR_Id1()},${i.getQR_Id2()}"></td>
                <td>${i.getGuestAccount()}</td>
                <td><a href="OrderDetail.jsp?QR_id=${i.getQR_Id1()}">${i.getEbayNO1()}</a></td>
              
                <td>
                <c:forEach var="pic" items="${i.getPicturePath1()}" begin="0" step="1">
                  <a href='#' class='pop' ><img src='/pics/${pic}' style='width: 100px; height: 100px;'></a><br/>
                </c:forEach>
                </td>
                <td>${i.getPayTime1()}</td>
                <td><a href="OrderDetail.jsp?QR_id=${i.getQR_Id2()}">${i.getEbayNO2()}</a></td>
				<td>
                  <c:forEach var="pic2" items="${i.getPicturePath2()}" begin="0" step="1">
               	  <a href='#' class='pop' ><img src='/pics/${pic2}' style='width: 100px; height: 100px;'></a><br/>
               	  </c:forEach>
                </td>
                <td>${i.getPayTime2()}</td>
              </tr>
            </c:when>
            <c:otherwise>
              <tr>
                <td rowspan="1" style="vertical-align: middle"><input type="checkbox" name="EbayNO"	value="${i.getEbayNO1()},${i.getEbayNO2()},${i.getGuestAccount()},${i.getQR_Id1()},${i.getQR_Id2()}"></td>
                <td>${i.getGuestAccount()}</td>
                <td><a href="OrderDetail.jsp?QR_id=${i.getQR_Id1()}">${i.getEbayNO1()}</a></td>
                 <td>
                 	<c:forEach var="pic" items="${i.getPicturePath1()}" begin="0" step="1">
                	<a href='#' class='pop' ><img src='/pics/${pic}' style='width: 100px; height: 100px;'></a><br/>
                	</c:forEach>
                </td>
                <td>${i.getPayTime1()}</td>
                <td><a href="OrderDetail.jsp?QR_id=${i.getQR_Id2()}">${i.getEbayNO2()}</a></td>
                 <td>
                 	<c:forEach var="pic2" items="${i.getPicturePath2()}" begin="0" step="1">
                	<a href='#' class='pop' ><img src='/pics/${pic2}' style='width: 100px; height: 100px;'></a><br/>
                	</c:forEach>
                </td>
                <td>${i.getPayTime2()}</td>
              </tr>
            </c:otherwise>
          </c:choose>
        </c:forEach>
      </table>
      <button type="submit" name="submit" value="CreateCombineOrders" id="Combine" class="btn btn-primary">合併勾選訂單</button>
    </form>
  </div>
</div>
<%@ include file="../href/footer.jsp"%>
</body>
</html>