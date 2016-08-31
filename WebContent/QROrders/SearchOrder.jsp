<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
  <div class="nav">
  
  

  	<div class="container">
    	<div class="navbar-left" style="background-color:#F3CE9A;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#A45A21"><a href="SearchOrder.jsp" style="color:#FFFFFF">訂單管理</a></li>
              <li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
              <li><a href="SearchComment.jsp">查詢評價</a></li>
              <li><a href="NewOrder.jsp">新增訂單</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#A45A21;" >
        <ul class="nav nav-tabs">
          <li><a href="SearchOrder.jsp" style="color:#fff">訂單查詢</a></li>
          <li><a href="OrderProcessingPage.jsp">處理中</a></li>
          <li><a href="OrderPickupPage.jsp">揀貨中</a></li>
          <li><a href="OrderUploadTrackingCode.jsp">上傳追蹤碼</a></li>
          <li><a href="OrderFinished.jsp">已完成訂單</a></li>
          <li><a href="OrderAbnormal.jsp">異常訂單</a></li>
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
  
  <div class="container" style="background: #D9A56B; border-radius:20px;">
  	<form name="searchform" method="post" action="../OrdersServlet" class="form-inline container" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
      <fieldset class="font-weight" style="padding:0 30px 0 0; "><legend>訂單查詢</legend>
    	<input type="hidden">
    	  <div class="row">
        	<div class="col-md-12 form-group ">
              <div class="row">
                <div class="col-md-10 form-group "><label for="usr">訂單狀態：</label>
                  <label class="checkbox-inline"><input type="checkbox" name="all" value="all">全部</label>
                  <label class="checkbox-inline"><input type="checkbox" name="waitProcess" value="waitProcess">待處理</label>
                  <label class="checkbox-inline"><input type="checkbox" name="processing"  value="processing">處理中</label>
                  <label class="checkbox-inline"><input type="checkbox" name="pickup"  value="pickup">撿貨中</label>
                  <label class="checkbox-inline"><input type="checkbox" name="shipped" value="shipped">已出貨</label>
                  <label class="checkbox-inline"><input type="checkbox" name="orderStatus">退款</label>
                  <label class="checkbox-inline"><input type="checkbox" name="orderStatus">其他</label>
                  <label class="checkbox-inline"><input type="checkbox" name="orderStatus">退貨中(扣庫存)</label>
                </div>
              </div>
            </div>
          </div>
          <br/>
          <div class="row">
            <div class="col-md-4 form-group ">
              <div class="row">
                <div class="col-md-4"><h5><label for="focusedInput " >商品名稱：</label></h5></div>
                <div class="col-md-8"><input class="form-control" name="productName" type="text"></div>
              </div>
            </div>
            <div class="col-md-4 form-group ">
              <div class="row">
                <div class="col-md-4"><h5><label for="focusedInput " >ebay account：</label></h5></div>
                <div class="col-md-8">
                  <select class="form-control" name="eBayAccount">
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
                  <div class="col-md-4"><h5><label for="focusedInput " >客戶帳號：</label></h5></div>
                  <div class="col-md-8"><input class="form-control" name=guestAccount type="text"></div>
                </div>
              </div>
            </div>
            
            <div class="row">
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4"><h5><label for="focusedInput " >SKU：</label></h5></div>
                  <div class="col-md-8"><input class="form-control" name="SKU" type="text"></div>
                </div>
              </div>
              <div class="col-md-8 form-group ">
                <div class="row">
                  <div class="col-md-2"><h5><label for="focusedInput " >付款日期：</label></h5></div>
                  <div class="col-md-10">
                      <input class="form-control" type="text" name="payDateMin">
                      <label for="focusedInput ">~</label>
                    <input class="form-control" type="text" name="payDateMax">
                  </div>
                </div>
              </div>               
            </div>
            <div class="row">
              <div class="col-md-8 form-group ">
                <div class="row">
                  <div class="col-md-2"><h5><label for="focusedInput " >出貨日期：</label></h5></div>
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
                <div class="row">
                  <div class="col-md-10 form-group"><label for="usr">物流選擇：</label>
                    <label class="checkbox-inline"><input type="checkbox" name="logistics">EMS</label>
                    <label class="checkbox-inline"><input type="checkbox" name="logistics">Fedex</label>
                    <label class="checkbox-inline"><input type="checkbox" name="logistics">DHL</label>
                    <label class="checkbox-inline"><input type="checkbox" name="logistics">Option 1</label>
                    <label class="checkbox-inline"><input type="checkbox" name="logistics">Option 2</label>
                    <label class="checkbox-inline"><input type="checkbox" name="logistics">Option 3</label>
                  </div>
                </div>
              </div>
            </div>
            <br/>
            <div class="row">
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4"><h5><label for="focusedInput " >出貨編號：</label></h5></div>
                  <div class="col-md-8"><input class="form-control" name="logisticsId" type="text"></div>
                </div>
              </div>
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4"><h5><label for="focusedInput " >表單確認編碼：</label></h5></div>
                  <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                </div>
              </div>
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4"><h5><label for="focusedInput " >Transaction ID：</label></h5></div>
                  <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                </div>
              </div>
            </div>
            
            <div class="row">
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4"><h5><label for="focusedInput " >收件國家：</label></h5></div>
                  <div class="col-md-8">
                    <select class="form-control" id="">
                      <option value="" selected="selected">--請選擇--</option>
                      <option value="AL">ALBANIA 阿爾巴尼亞</option>
                      <option value="UY">URUGUAY 烏拉圭</option>
                      <option value="UZ">UZBEKISTAN 烏茲別克</option>
                      <option value="VN">VIET NAM 越南</option>
                      <option value="VG">VIRGIN ISLANDS, BRITISH 英屬維京群島</option>
                    </select>
                  </div>
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
				<button type="submit" name="submit" value="orderSearch" class="btn-lg btn-primary ">搜尋</button>
				<button type="button" name="" class="btn-lg btn-primary">清空</button>		
			</div>
	  </fieldset>
    </form>
  </div>
  <hr/>  
<c:choose> 
  <c:when test="${ResultOrders != null}">
  
	<div class="container table-responsive bg-warning" style=" border-radius:20px">
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
	  <form name="searchform" method="post" action="../SubmitToProcessing" style="font-size: 100%; vertical-align: baseline; 
	  padding: 15px; " class="form-inline container">
	    <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px">
	 	  <tr class="ListTitle">
	        <th>選取	</th>
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
	        <td nowrap>${i.getCOrderMaster().getOrder_id()}<input type="hidden" name="orderId" value="${i.getCOrderMaster().getOrder_id()}"></td>
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
	        <td colspan="9">
	          <b><a href="#">${i.getCOrderDetail().getSKU()}</a></b>
	            ${i.getCOrderDetail().getProductName()}(SKU/productName)
	        </td>
	        <td colspan="3">
	        </td>
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
  </c:when>
</c:choose> 
  	
<%@ include file="../href/footer.jsp" %>
</body>
  
</html>