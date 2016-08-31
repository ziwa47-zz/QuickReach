<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>NewOrder</title>
<!-- Bootstrap -->
	<link href="./css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./css/smoothness/jquery-ui.css">

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.js"></script>

    
    <script src="./js/jquery-1.12.4.min.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/jquery.ui.datepicker-zh-TW.js"></script>
    
    <script type="text/javascript">        
        $(function () {
            //日期選擇器
            $("input[name=date]").datepicker({ dateFormat: 'yy/mm/dd', showOn: "both" });
           });
	</script>   
    
    <style type="text/css">
            .label-tag {
                background-color:#BFE9B2;
            }
            .pressed {
                border-width: 5px;
                border-color: black;
                border-style: solid;
            }
        </style>
    
</head>

<body><%@ include file="/href/navbar.jsp" %>
  <div class="nav">
  	<div class="container">
    	<div class="navbar-left" style="background-color:#F3CE9A;" >
        	<ul class="nav nav-tabs">
              <li><a href="SearchOrder.jsp" >訂單管理</a></li>
              <li><a href="DayliBalanceSheet.jsp" >日結表</a></li>
              <li><a href="SearchComment.jsp">查詢評價</a></li>
              <li><a href="NewOrder.jsp" style="background-color:#A45A21; color:#fff">新增訂單</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#A45A21;" >
        <ul class="nav nav-tabs">
          <li><a href="SearchOrder.jsp">訂單查詢</a></li>
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
  
  <div class="container" style="background: #9DDCD1; border-radius:20px;">
  <form id="listForm" name="listForm" method="post" action="PurchaseRecordPage.jsp" style="font-size: 100%; vertical-align: baseline; padding: 15px; " class="form-inline container">
    <fieldset class="container-fluid" style="padding:0 30px 0 0;"><legend>新增訂單</legend>
      <input type="hidden">
      <div class="panel-group" id="accordion" >
        <div class="panel panel-default" style="background-color:#E7D29F">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">訂購人資料</a>
            </h4>
          </div>
          <div id="collapse1" class="panel-collapse collapse in">
            <div class="panel-body">
              <input type="hidden">
              <div class="row">
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >訂購人名子：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >訂購人姓氏：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >帳號：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
              </div>
            
              <div class="row">
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >電子郵件'：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >電話(日)：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >電話(夜)：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
              </div>
              
              <div class="row">
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >行動電話：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >生日：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >公司/學校：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-8 ">
                  <div class="row ">
                    <div class="col-md-2"><h5><label for="focusedInput " >地址：</label></h5></div>
                    <div class="col-md-10 input-group" style="padding-left:15px; padding-right:35px"><input class="form-control " name="sku" type="text" style=" border-radius: 4px; "></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >國家：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >郵遞區號：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >性別</label></h5></div>
                    <div class="col-md-8">
                      <select class="form-control" name="warehouse">
                        <option value="男"></option>
                        <option value="kaohsiung">男</option>
                        <option value="US">女</option>
                      </select>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="panel panel-default" style="background-color:#E7D29F">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">收件人資料</a>
            </h4>
          </div>
          <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
              <input type="hidden">
              <div class="row">
                <div class="col-md-4"><input class="form-control radio" name="sku" type="radio">同訂購者資料</div>
              </div>
              <div class="row">
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >收件人姓名：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="r"></div>
                  </div>
                </div>
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >收件姓氏：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >電話(日)：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
              </div>
            
              <div class="row">
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >地址：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >郵遞區號：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >國家)：</label></h5></div>
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
            </div>
          </div>
        </div>
        <div class="panel panel-default" style="background-color:#E7D29F">
          <div class="panel-heading">
            <h4 class="panel-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">訂單詳細資料</a>
            </h4>
          </div>
          <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
              <input type="hidden">
              <div class="row">
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >公司：</label></h5></div>
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
                <div class="col-md-10 form-group">
                  <div class="row">
                    <div class="col-md-2"><h5><label>物流配送方式:</label></h5></div>
                    <div class="col-md-10"><h5>
                      <label class="radio-inline"><input type="radio" value="">EMS</label>
                      <label class="radio-inline"><input type="radio" value="">Fedex</label>
                      <label class="radio-inline"><input type="radio" value="">DHL</label>
                      <label class="radio-inline"><input type="radio" value="">TNT</label>	
                      <label class="radio-inline"><input type="radio" value="">掛號小包</label>
                      <label class="radio-inline"><input type="radio" value="">國際包裹</label>
                      <label class="radio-inline"><input type="radio" value="">其他</label></h5>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-10 form-group">
                  <div class="row">
                    <div class="col-md-2"><h5><label>平台:</label></h5></div>
                    <div class="col-md-10"><h5>
                      <label class="radio-inline"><input type="radio" value="">Order</label>
                      <label class="radio-inline"><input type="radio" value="">官網</label>
                      <label class="radio-inline"><input type="radio" value="">Ebay</label>
                      <label class="radio-inline"><input type="radio" value="">AMAZON</label></h5>
                    </div>
                  </div>
                </div>
              </div>
              
              <div class="row">
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput ">ebay 帳號</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="r"></div>
                  </div>
                </div>
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >paypal 帳號：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="text"></div>
                  </div>
                </div>
              </div>
            
              <div class="row">
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >購買日期</label></h5></div>
                    <div class="col-md-8"><input class="form-control" type="text" name="date" ></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >出貨日期</label></h5></div>
                    <div class="col-md-8"><input class="form-control" type="text" name="date" ></div>
                  </div>
                </div>
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >付款方式</label></h5></div>
                    <div class="col-md-8"><input type="checkbox" value="" checked>Paypal</div>
                  </div>
                </div>
              </div>
              <div class="row">
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput ">ebay 帳號</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="sku" type="r"></div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >國家)：</label></h5></div>
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
            </div>
          </div>
        </div>
		
		<br/>
		
        <div class="row text-center">
          <button type="submit" name="" class="btn-lg btn-success">送出</button>
        </div>

      </div>
      
    </fieldset>
  </form>
</div>

  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>
