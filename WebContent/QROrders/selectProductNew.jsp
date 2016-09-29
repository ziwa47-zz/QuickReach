<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*,javax.servlet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品選取New</title>
</head>
<body>
<%@ include file="/href/navbar.jsp"%>
<%request.setAttribute("QR_id", request.getParameter("QR_id")); %>
<div class="container" style="background: #9DDCD1; border-radius: 20px;">
  <form name="searchform" method="post" action="../ProductDo" 
  style="font-size: 100%; vertical-align: baseline; padding: 15px;" 
  class="form-inline container">
    <fieldset>
      <legend>商品編號:${QR_id}<input type="hidden" name="QR_id" value="${QR_id}"></legend>
      <div class="row">
        <div class="col-md-8 form-group ">
          <div class="row">
            <div class="col-md-2">
              <h5>
                <label >名稱關鍵字：</label>
              </h5>
            </div>
            <div class="col-md-8 input-group"
            style="padding-left: 15px; padding-right: 35px">
              <input class="form-control" name="pname" type="text" style="border-radius: 4px">
            </div>
          </div>
        </div>
        <div class="col-md-4 form-group ">
          <div class="row">
            <div class="col-md-4">
              <h5>
                <label >廠牌：</label>
              </h5>
            </div>
            <div class="col-md-8">
              <input class="form-control" name="brand" type="text">
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-8 form-group ">
          <div class="row">
            <div class="col-md-2">
              <h5>
                <label >SKU：</label>
              </h5>
            </div>
            <div class="col-md-8 input-group"
                style="padding-left: 15px; padding-right: 35px">
              <input class="form-control" name="sku" type="text" style="border-radius: 4px">
            </div>
          </div>
        </div>
        <div class="col-md-4 form-group ">
          <div class="row">
            <div class="col-md-4">
              <h5>
                <label >副廠牌：</label>
              </h5>
            </div>
            <div class="col-md-8">
              <input class="form-control" name="subbrand" type="text">
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-8 form-group ">
          <div class="row">
            <div class="col-md-2">
              <h5>
                <!-- <label >儲位區間：</label> -->
              </h5>
            </div>
            <div class="col-md-10">
             <!--  <input class="form-control" type="text" name="location1">
              <label >~</label>
              <input class="form-control" type="text" name="location2"> -->
            </div>
          </div>
        </div>
        <div class="col-md-4 form-group ">
          <div class="row">
            <div class="col-md-4">
              <h5>
                <label >規格：</label>
              </h5>
            </div>
            <div class="col-md-8">
              <input class="form-control" name="spec" type="text">
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-8 form-group ">
          <div class="row">
            <div class="col-md-2">
              <h5>
                <label >建檔日期：</label>
              </h5>
            </div>
            <div class="col-md-10">
              <input class="form-control" type="text" name="date1">
              <label >~</label>
              <input class="form-control" type="text" name="date2">
            </div>
          </div>
        </div>
        <div class="col-md-4 form-group ">
          <div class="row">
            <div class="col-md-4">
              <h5>
                <label >顏色：</label>
              </h5>
            </div>
            <div class="col-md-8">
              <input class="form-control" name="color" type="text">
            </div>
          </div>
        </div>
      </div>
      <br />
      <div class="row text-center">
        <button type="submit" name="submit" value="getProcuct" class="btn-lg btn-success">搜尋</button>
        <button type="button" name="" class="btn-lg btn-success">清空</button>
      </div>
    </fieldset>
  </form>
</div>
<br />
<%
    HttpSession sess = request.getSession();
    if (sess.getAttribute("productall") != null && sess.getAttribute("productall") != "") {
    }
%>
<c:choose>
  <c:when test="${productall != null}">
    <div class="container table-responsive bg-warning" style="border-radius: 20px">
      <form name="searchform" method="post" action="../OrdersServlet" style="font-size: 100%; vertical-align: baseline; padding: 15px;" class="form-inline container">
        <table class="table table-bordered table-hover table-condensed pull-left" style="margin: 0 0 0 -15px">
          <tr class="ListTitle2">
            <th>選取</th>
            <th>編輯</th>
            <th>SKU</th>
            <th>廠牌</th>
            <th>副廠牌</th>
            <th>品名</th>
            <th>規格</th>
            <th>顏色</th>
          </tr>
          <c:forEach var="i" items="${productall}" begin="0" step="1" varStatus="check">
            <tr>
              <td><input type="checkbox" name="SKU" value="${i.getSKU()}" onchange="enableProductName(this)"></td>
              <td><a href="../QRProduct/StockDetail.jsp?sku=${i.getSKU()}"><img src="../img/compose-4.png"></a></td>
              <td>${i.getSKU()}</td>
              <td>${i.getBrand()}</td>
              <td>${i.getSubBrand()}</td>
              <td><input id="${i.getSKU()}" type="hidden" name="init" value="${i.getP_name()}">${i.getP_name()}</td>
              <td>${i.getSpec()}</td>
              <td>${i.getColor()}</td>
            </tr>
          </c:forEach>
        </table>
        <div class="row text-center">
          <input type="hidden" name="QR_id" value="${QR_id}">
          <button type="submit" name="submit" value="insertSKUNew" class="btn btn-bg btn-success">新增商品</button>
        </div>
      </form>
    </div>
  </c:when>
</c:choose>
<script type="text/javascript">
 
 function enableProductName(ele){
	  var id = ele.value;
	  if (ele.checked) {
		  $("#" + id).attr("name","productName");
	  } else {
		  $("#" + id).attr("name","init");
	  }
  };
  
</script>
</body>
</html>