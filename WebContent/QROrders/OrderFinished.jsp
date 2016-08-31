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
<title>Insert title here</title>
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
              <li><a href="SearchOrder.jsp">查詢訂單</a></li>
              <li><a href="OrderProcessingPage.jsp">處理中</a></li>
              <li><a href="OrderPickupPage.jsp">揀貨中</a></li>
              <li><a href="OrderUploadTrackingCode.jsp">上傳追蹤碼</a></li>
              <li><a href="OrderFinished.jsp" style="color:#fff">已完成訂單</a></li>
              <li><a href="OrderAbnormal.jsp">異常訂單</a></li>
            </ul>
      </div>
    </div>
  </div>
  <%
  Connection conn = new DataBaseConn().getConn();
  LinkedList<COrders> orderList = COrderFactory.orders(request,conn,"已完成");
  request.setAttribute("list", orderList);
  %>
  
 
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
      <li><a href="#" >主要目錄</a></li>
      <li class="active" style="display:"><a href="#">訂單</a></li>
      <li><a href="#">訂單查詢</a></li>
    </ol>
  </div>
<div class="nav">  
  <div class="container" style="background: #D9A56B; border-radius:20px;">
  	<form name="searchform" method="post" action="#" style="font-size: 100%; vertical-align: baseline; 
    padding: 15px; " class="form-inline container">
      <fieldset class="font-weight" style="padding:0 30px 0 0;"><legend>已完成</legend>
    	<input type="hidden">
        <div class="row">
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4"><h5><label for="focusedInput " >ebay account：</label></h5></div>
              <div class="col-md-8">
                <select class="form-control" name="warehouse">
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
              <div class="col-md-4"><h5><label for="focusedInput " >ebay訂單編號：</label></h5></div>
              <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4"><h5><label for="focusedInput " >P/P帳號：</label></h5></div>
              <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
            </div>
          </div>
        </div>
          
               
        <div class="row">
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label>出貨單號：</label></h5></div>
              <div class="col-md-6 input-group" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="sku" type="text" style="border-radius: 4px">
              </div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4"><h5><label>E/B帳號：</label></h5></div>
              <div class="col-md-8" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="sku" type="text" style="border-radius: 4px">
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label>Tracking Code：</label></h5></div>
              <div class="col-md-6 input-group" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="sku" type="text" style="border-radius: 4px">
              </div>
            </div>
          </div>
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4"><h5><label>客戶姓名：</label></h5></div>
              <div class="col-md-8" style="padding-left: 15px; padding-right: 35px">
                <input class="form-control" name="sku" type="text" style="border-radius: 4px">
              </div>
            </div>
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4"><h5><label for="focusedInput " >商品SKU：</label></h5></div>
              <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
            </div>
          </div>
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >付款日期：</label></h5></div>
              <div class="col-md-10">
                  <input class="form-control" type="text" name="date">
                  <label for="focusedInput ">~</label>
                <input class="form-control" type="text" name="date">
              </div>
            </div>
          </div>               
        </div>
        <div class="row">
          <div class="col-md-4 form-group ">
            <div class="row">
              <div class="col-md-4"><h5><label for="focusedInput " >商品名稱：</label></h5></div>
              <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
            </div>
          </div>
          <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >出貨日期：</label></h5></div>
              <div class="col-md-10">
                  <input class="form-control" type="text" name="date">
                  <label for="focusedInput ">~</label>
                <input class="form-control" type="text" name="date">
              </div>
            </div>
          </div>               
        </div>
        <br/>
        <div class="row">
          <div class="col-md-12 form-group "><label for="usr">訂單狀態：</label>
            <label class="checkbox-inline"><input type="checkbox" value="">全部</label>
            <label class="checkbox-inline"><input type="checkbox" value="">待處理</label>
            <label class="checkbox-inline"><input type="checkbox" value="">處理中</label>
            <label class="checkbox-inline"><input type="checkbox" value="">撿貨中</label>
            <label class="checkbox-inline"><input type="checkbox" value="">已出貨</label>
            <label class="checkbox-inline"><input type="checkbox" value="">退款</label>
            <label class="checkbox-inline"><input type="checkbox" value="">其他</label>
            <label class="checkbox-inline"><input type="checkbox" value="">退貨中(扣庫存)</label>
          </div>
        </div>
        <br/>
        <div class="row">
          <div class="col-md-12 form-group "><label for="usr">物流選擇：</label>
            <label class="checkbox-inline"><input type="checkbox" value="">DHL</label>
            <label class="checkbox-inline"><input type="checkbox" value="">Fedex</label>
            <label class="checkbox-inline"><input type="checkbox" value="">EMS</label>
            <label class="checkbox-inline"><input type="checkbox" value="">AP(國際包裹)</label>
            <label class="checkbox-inline"><input type="checkbox" value="">RA(國際掛號)</label>
            <label class="checkbox-inline"><input type="checkbox" value="">USPS寄倉</label>
            <label class="checkbox-inline"><input type="checkbox" value="">USPS集運</label>
            <label class="checkbox-inline"><input type="checkbox" value="">7-11取貨付款</label>
            <label class="checkbox-inline"><input type="checkbox" value="">全家取貨付款</label>
            <label class="checkbox-inline"><input type="checkbox" value="">郵局快捷貨到付款</label>
            <label class="checkbox-inline"><input type="checkbox" value="">其他</label>
          </div>
        </div>
        <br/>
        <div class="row">
		  <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >備註：</label></h5></div>
              <div class="col-md-8"><textarea rows="4" cols="50" class="form-control"></textarea></div>
            </div>
          </div>          
        </div>
        <div class="row">
          <button type="button" class="btn btn-info" data-parent="#accordion" data-toggle="collapse" data-target="#furtherInfo">進階查詢</button>
          <div id="furtherInfo" class="collapse">
            <div class="container">
              <div class="row">
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >List：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >Type：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >Product Code：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >規格：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >顏色：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >備註：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <br/>
        <div class="row text-center" >     
          <button type="submit" name="" class="btn-lg btn-primary ">搜尋</button>
          <button type="button" name="" class="btn-lg btn-primary">清空</button>		
        </div>
	  </fieldset>
    </form>
  </div>
  
  
  <hr/>
  <c:choose>
  <c:when test="${ResultOrders != null}">
    <div class="container table-responsive bg-warning" style=" border-radius:20px">
      <form name="searchform" method="post" action="../SubmitToShipped" style="font-size: 100%; vertical-align: baseline; padding: 15px; " class="form-inline container">
        <div class="container">
          <div class="row" style="padding:10px 0 10px 15px">
            <button type="submit" name="" class="btn-sm btn-info">選擇全部</button>
            <button type="submit" name="" class="btn-sm btn-info">清除勾選</button>
            <button type="submit" name="" class="btn-sm btn-info">列印撿貨/出貨單</button>
            <button type="submit" name="" class="btn-sm btn-info">列印EMS</button>
            <button type="submit" name="" class="btn-sm btn-info">列印Invoice</button>
            <button type="submit" name="" class="btn-sm btn-info">回復</button>
          </div>
        </div>
        <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
          <tr class="ListTitle">
            <th>選取 </th>
            <th>編輯</th>
            <th colspan="2">訂單編號</th>
            <th>平台</th>
            <th>Ebay Account</th>
            <th>客戶帳號</th>
            <th>購買日期</th>
            <th>出貨日期</th>
            <th>資料狀態</th>
            <th>訂單狀態</th>
            <th>總金額</th>
            <th>使用者</th>
          </tr>
          <c:forEach var="i" items="${ResultOrders}" begin="0" step="1" varStatus="check">
            <tr>
              <td rowspan="3" style="vertical-align:middle"><input type="checkbox"></td>
              <td><a href="#"><img src="../img/compose-4.png" ></a></td>
              <td nowrap>${i.getCOrderMaster().getOrder_id()}</td>
              <td nowrap><a href="#"><img src="../img/compose.png" ></a></td>
              <td>${i.getCOrderMaster().getPlatform()}</td>
              <td>${i.getCOrderMaster().getEbayAccount()}</td>
              <td><a href="#">${i.getCOrderMaster().getGuestAccount()}</a></td>
              <td>${i.getCOrderMaster().getPayDate()}</td>
              <td>${i.getCOrderMaster().getShippingDate()}</td>
              <td>icon</td>
              <td>${i.getCOrderMaster().getOrderStatus()}</td>
              <td>${i.getCOrderMaster().getTotalPrice()}</td>
              <td>${i.getCOrderMaster().getStaffName()}</td>
            </tr>
            <tr>
              <td colspan="9"><b><a href="#">${i.getCOrderDetail().getSKU()}</a></b> ${i.getCOrderDetail().getProductName()}(SKU/productName) </td>
              <td colspan="3"></td>
            </tr>
            <tr>
              <td colspan="12">Dear William, Please remember, split the order in 2 packages. Thanks and best regards!</td>
            </tr>
          </c:forEach>
        </table>
        <div class="row text-center" >
          <button type="submit" name="" class="btn-lg btn-primary ">送出</button>
        </div>
      </form>
      <div class="container">
        <div class="row" style="padding:10px 0 10px 15px">
          <button type="submit" name="" class="btn-sm btn-info">選擇全部</button>
          <button type="submit" name="" class="btn-sm btn-info">清除勾選</button>
          <button type="submit" name="" class="btn-sm btn-info">列印撿貨/出貨單</button>
          <button type="submit" name="" class="btn-sm btn-info">列印EMS</button>
          <button type="submit" name="" class="btn-sm btn-info">列印Invoice</button>
          <button type="submit" name="" class="btn-sm btn-info">回復</button>
        </div>
      </div>
    </div>
    <% session.invalidate(); %>
  </c:when>
  <c:otherwise>
    <div class="container table-responsive bg-warning" style=" border-radius:20px">
      <form name="searchform" method="post" action="../SubmitToShipped" style="font-size: 100%; vertical-align: baseline; padding: 15px; " class="form-inline container">
        <div class="container">
          <div class="row" style="padding:10px 0 10px 15px">
            <button type="submit" name="" class="btn-sm btn-info">選擇全部</button>
            <button type="submit" name="" class="btn-sm btn-info">清除勾選</button>
            <button type="submit" name="" class="btn-sm btn-info">列印撿貨/出貨單</button>
            <button type="submit" name="" class="btn-sm btn-info">列印EMS</button>
            <button type="submit" name="" class="btn-sm btn-info">列印Invoice</button>
            <button type="submit" name="" class="btn-sm btn-info">回復</button>
          </div>
        </div>
        <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
          <tr class="ListTitle">
            <th>選取 </th>
            <th>編輯</th>
            <th colspan="2">訂單編號</th>
            <th>平台</th>
            <th>Ebay Account</th>
            <th>客戶帳號</th>
            <th>購買日期</th>
            <th>出貨日期</th>
            <th>資料狀態</th>
            <th>訂單狀態</th>
            <th>總金額</th>
            <th>使用者</th>
          </tr>
          <c:forEach var="i" items="${list}" begin="0" step="1" varStatus="check">
            <tr>
              <td rowspan="3" style="vertical-align:middle"><input type="checkbox"></td>
              <td><a href="#"><img src="../img/compose-4.png" ></a></td>
              <td nowrap>${i.getCOrderMaster().getOrder_id()}
                <input type="hidden" name="orderId" value="${i.getCOrderMaster().getOrder_id()}"></td>
              <td nowrap><a href="#"><img src="../img/compose.png" ></a></td>
              <td>${i.getCOrderMaster().getPlatform()}</td>
              <td>${i.getCOrderMaster().getEbayAccount()}</td>
              <td><a href="#">${i.getCOrderMaster().getGuestAccount()}</a></td>
              <td>${i.getCOrderMaster().getPayDate()}</td>
              <td>${i.getCOrderMaster().getShippingDate()}</td>
              <td>icon</td>
              <td>${i.getCOrderMaster().getOrderStatus()}
                <input type="hidden" name="status" value="${i.getCOrderMaster().getOrderStatus()}"></td>
              <td>${i.getCOrderMaster().getTotalPrice()}</td>
              <td>${i.getCOrderMaster().getStaffName()}</td>
            </tr>
            <tr>
              <td colspan="9"><b><a href="#">${i.getCOrderDetail().getSKU()}</a></b> ${i.getCOrderDetail().getProductName()}(SKU/productName) </td>
              <td colspan="3"></td>
            </tr>
            <tr>
              <td colspan="12">Dear William, Please remember, split the order in 2 packages. Thanks and best regards!</td>
            </tr>
          </c:forEach>
        </table>
        <div class="row text-center" >
          <button type="submit" name="" class="btn-lg btn-primary ">送出</button>
        </div>
      </form>
      <div class="container">
        <div class="row" style="padding:10px 0 10px 15px">
          <button type="submit" name="" class="btn-sm btn-info">選擇全部</button>
          <button type="submit" name="" class="btn-sm btn-info">清除勾選</button>
          <button type="submit" name="" class="btn-sm btn-info">列印撿貨/出貨單</button>
          <button type="submit" name="" class="btn-sm btn-info">列印EMS</button>
          <button type="submit" name="" class="btn-sm btn-info">列印Invoice</button>
          <button type="submit" name="" class="btn-sm btn-info">回復</button>
        </div>
      </div>
    </div>
  </c:otherwise>
</c:choose>
</div>
  	
<%@ include file="/href/footer.jsp" %>
</body>
</html>