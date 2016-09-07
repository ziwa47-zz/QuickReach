<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>NewOrder</title>
<!-- Bootstrap -->
	<link href="/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/smoothness/jquery-ui.css">

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.js"></script>

    
    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/jquery.ui.datepicker-zh-TW.js"></script>
    
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
    	<div class="navbar-left" style="background-color:#BCF1E5;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#1CAF9A"><a href="#" style="color:#FFFFFF">管理訂單</a></li>
              <li><a href="#" style="color:#000000">Menu 1</a></li>
              <li><a href="#" style="color:#000000">Menu 2</a></li>
              <li><a href="#" style="color:#000000">Menu 3</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#1CAF9A"><a href="#" style="color:#FFFFFF">新增訂單</a></li>
              <li><a href="#" style="color:#000000">Menu 1</a></li>
              <li><a href="#" style="color:#000000">Menu 2</a></li>
              <li><a href="#" style="color:#000000">Menu 3</a></li>
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
      <h3>新增訂單</h3>
      <hr/>
  </div>
  
  <div class="container" >
  	<form name="searchform" method="post" action="#" style="font-size: 100%; vertical-align: baseline;
      " class=" form-group container">
      
          <input type="hidden">
              <h3 class="" style="background: #BCF1E5; border-left: 6px solid #1CAF9A;" >請選擇客戶</h3>
              <div class="container-fluid form-horizontal">
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>客戶名子</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>客戶姓氏</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>帳號</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>電子郵件</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>電話(日)</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>電話(夜)</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>行動電話</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>生日</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>公司/學校</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>地址</h4></div>
                      <div class="col-md-8 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>國家</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>郵遞區號</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>性別</h4></div>
                      <div class="col-md-5 well-sm">
                          <label class="radio-inline"><input type="radio" value="">__</label>
                          <label class="radio-inline"><input type="radio" value="">男</label>
                          <label class="radio-inline"><input type="radio" value="">女</label>
                      </div>
                  </div>
              </div>
              
              
              
              
              <h3 class="" style="background: #BCF1E5; border-left: 6px solid #1CAF9A;" >收件人資料</h3>
              <div class="container-fluid form-horizontal">
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp</h4></div>
                    <div class="col-md-5 well-sm">
                        <label class="checkbox-inline"><input type="checkbox" value="">同訂購人資料</label>                  </div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>收件人名子</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>收件人姓氏</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>電話(日)</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>地址</h4></div>
                    <div class="col-md-8 well-sm"><input class="form-control" type="text" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>郵遞區號</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>收件人國家</h4></div>
                    <div class="col-md-5 well-sm">
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
              
              
              
              <h3 class="" style="background: #BCF1E5; border-left: 6px solid #1CAF9A;" >訂單資料</h3>
              <div class="container-fluid form-horizontal">
              	<div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>公司</h4></div>
                    <div class="col-md-5 well-sm">
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
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>物流配送方式</h4></div>
                    <div class="col-md-9 well-sm">
                    	<label class="checkbox-inline"><input type="checkbox" value="">EMS</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">Fedex</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">DHL</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">TNT</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">掛號小包</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">國際包裹</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">其他</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>郵遞區號</h4></div>
                    <div class="col-md-5 well-sm">
                    	<label class="checkbox-inline"><input type="checkbox" value="">order</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">官網</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">ebay</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">yahoo拍賣</label>
                        <label class="checkbox-inline"><input type="checkbox" value="">露天拍賣</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>ebay帳號</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>paypal交易帳號</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>購買日期</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" name="date" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>出貨日期</h4></div>
                    <div class="col-md-5 well-sm"><input class="form-control" type="text" name="date" ></div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>付款方式</h4></div>
                    <div class="col-md-5 well-sm"><input type="checkbox" value="" checked>Paypal</div>
                </div>
                <div class="row">
                    <div class="col-md-3 text-right well-sm label-tag"  ><h4>paypal交易帳號</h4></div>
                    <div class="col-md-8 well well-sm">
                    	<select class="form-inline" id="">
                          <option value="" selected="selected">--請選擇--</option>
                          <option value="AL">ALBANIA 阿爾巴尼亞</option>
                          <option value="UY">URUGUAY 烏拉圭</option>
                          <option value="UZ">UZBEKISTAN 烏茲別克</option>
                          <option value="VN">VIET NAM 越南</option>
                          <option value="VG">VIRGIN ISLANDS, BRITISH 英屬維京群島</option>
                        </select>
                    	<input class="form-inline" type="text" >
                    </div>
                </div>
                
                
                
                <h3 class="" style="background: #BCF1E5; border-left: 6px solid #1CAF9A;" >Invoice</h3>
                <div class="container-fluid form-horizontal">
                	<div class="row text-center" >     
                        <input type="submit" value="新增" name="" id="" >
                    </div>
                
                </div>
                
              
              
              </div>
  	</form>
  </div>
  
  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>
