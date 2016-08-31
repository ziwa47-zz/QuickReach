<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>訂單內容</title>
</head>
<body>
  <%@ include file ="/href/navbar.jsp" %>
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
          <li><a href="OrderProcessingPage.jsp" style="color:#000000">處理中</a></li>
          <li><a href="OrderPickupPage.jsp" style="color:#000000">揀貨中</a></li>
          <li><a href="OrderUploadTrackingCode.jsp" style="color:#000000">上傳追蹤碼</a></li>
          <li><a href="OrderFinished.jsp" style="color:#000000">已完成訂單</a></li>
          <li><a href="OrderAbnormal.jsp" style="color:#000000">異常訂單</a></li>
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

  <div class="container table-responsive" style="background: #D9A56B; border-radius:20px;">
  	<form name="searchform" method="post" action="../OrdersServlet" class="form-inline container" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
  	<div class="row">
      <label for="inputPassword" class="col-md-2 control-label text-left">編輯模式</label>
      <div class="col-md-4">
        <label class="radio-inline"><input type="radio" name="optionsRadios" id="optionsRadios1">開啟</label>
        <label class="radio-inline"><input type="radio" name="optionsRadios" id="optionsRadios2">關閉</label>
    	<label class="radio-inline">
    	<button type="submit" name="submit" value="updateProduct"	class="btn-lg btn-success">更新產品資料</button>
      	</label>
      </div>
    </div>
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;" disabled><legend>訂單明細</legend>
      <div class="panel-group" id="accordion" >
        <div class="panel panel-default" style="background-color:#E7D29F">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">訂購人資料</a>
            </h4>
          </div>
          <div id="collapse1" class="panel-collapse collapse in">
            <div class="panel-body">
              <div class="container-fluid form-horizontal">
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>客戶名子</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>客戶姓氏</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>訂購帳號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電子郵件</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話(日)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話(夜)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>行動電話</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>生日</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>公司/學校</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>地址</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>國家</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>郵遞區號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>性別</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
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
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>收件人姓氏</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話1</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>電話2</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>地址</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>收件人國家</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>郵遞區號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
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
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>訂單狀態</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>訂單編號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>表單確認編碼</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>公司</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>平台</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>ebay 帳號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>購買日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>付款日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>付款方式</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>paypal 交易序號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>出貨日期</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>物流配送方式</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>提早出貨</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>出貨編號</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>運費</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>退運費</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>其它費用</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>其它費用備註</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>其它收入</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>其它收入備註</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>ebay成交費</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>計算保價</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>保價</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>保價金額</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>paypal費用</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>淨重(公克)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>毛重(公克)</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>Fedex服務</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>長/寛/高</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>備註</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		        <div class="row">
		          <div class="col-md-3 text-right well-sm label-tag"><h4>總計</h4></div>
		          <div class="col-md-5 well-sm"><input class="form-control" type="text" value="" ></div>
		        </div>
		      </div>
            </div>
          </div>
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">訂購商品清單</a>
            </h4>
          </div>
          <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
              <table class="table table-bordered table-hover table-condensed pull-left" style="margin:0 0 0 -15px" >
			    <thead>
			 	  <tr class="ListTitle2">
			        <th>商品編號</th>
			        <th>商品名稱 / invoice名稱</th>
			        <th>成交價</th>
			        <th>invoice價格</th>
			        <th>數量</th>
			        <th>備註</th>
		          </tr>
	            </thead>
		        <tbody>
		          <tr>
		            <td>B00SXT0000046ZZ</td>
		            <td>Shimano 2016 Deore XT M8000 Groupset 2x11-spd (38/28T 175mm) 7Pcs New US</td>
		            <td>452.68</td>
		            <td>452.68</td>
		            <td>1</td>
		            <td>Shimano 2016 Deore XT M8000 Groupset 2x11-spd (38/28T 175mm) 7Pcs New US</td>
		          </tr>
		        </tbody>
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