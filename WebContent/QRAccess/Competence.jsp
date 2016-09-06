<%@ page import="tw.iii.qr.Competence"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="ctsql" scope="session" class="tw.iii.qr.CompetenceSql" />
<jsp:setProperty name="ctsql" property="*"/>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>修改權限</title>
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
    <script type="text/javascript"> 
    function goct(){
        document.form.action = "Competence.jsp"
        document.form.submit()
     }
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
              <li class="" style="background-color:#1CAF9A"><a href="Competence.jsp" style="color:#FFFFFF">權限管理</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#1CAF9A"><a href="Competence.jsp" style="color:#FFFFFF">修改權限</a></li>
              <li class="" ><a href="CompetenceInsert.jsp" style="color:#000000">新增權限</a></li>
            </ul>
        </div>
    </div>
  
  </div>
  
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
          <li><a href="../QRMain/HomePag.jsp" >首頁</a></li>
          <li class="active" style="display:"><a href="Competence.jsp">權限管理</a></li>
          <li><a href="Competence.jsp">修改權限</a></li>
      </ol>
      <h3>修改權限</h3>
      <hr/>
  </div>
  
  <div class="container" >
  	<form name="searchform" method="post" action="Competence.jsp" style="font-size: 100%; vertical-align: baseline;" class=" form-group container">
<%request.setCharacterEncoding("UTF-8");%>
<%
response.setContentType("text/html;charset=UTF-8");

LinkedList<Competence> list =  new LinkedList<Competence>();
list = ctsql.getCompetenceLevel();
request.setAttribute("list",list);

%>



          <input type="hidden">
              <h3 class="" style="background: #BCF1E5; border-left: 6px solid #1CAF9A;" >權限設定</h3>
              <div class="container-fluid form-horizontal">
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag" ><h4>權限等級</h4></div>
                      <div class="col-md-5 well-sm">
						<select class="form-control" name="Competencelv" onChange="goct()"> 
						    <option value="ab" >==請選擇==${param.Competencelv}</option>
						    <c:forEach var="i" varStatus="check" items="${list}" begin="0" step="1">
						    
						    <option value="${i.getCompetenceLv()}" >${i.getCompetenceLv()}</option>						    
						 
						    </c:forEach>
						</select>
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>商品權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ProductManage" />管理商品</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>採購權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="PurchaseManage" />管理採購</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>庫存權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="InventoryManage" />管理庫存&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="InventoryInfoEdit" />編輯庫存資料</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>客戶權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ClientManage" /> 管理客戶</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>訂單權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0" name="EntireOrders" />所有狀態訂單</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="OrdersInvoiceDownload" />下載所有訂單Invoice</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp;</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="PriceChange" />更改成本費用</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="PendingOrdersEdit" /> 編輯待處理訂單</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp;</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="TotalAmountEdit" />編輯訂單金額</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="OrdersManage" />管理訂單</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>報表權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ChartView" />查看圖表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="ProductProfitView" />查看商品利潤</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp;</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ReportView" />查看報表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="ProductCostView" />查看商品成本</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>帳號權限</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="AccountInfoEdit" />修改帳號資料</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="EbayPaypalAccountEdit" />編輯ebay/paypal帳號</label>
					   
					  </div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>&nbsp;</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_0"  name="ParamSettingEdit" />修改參數設定</label>
					 	<label class="checkboxbox-inline"><input type="checkbox" id="id_fd-is_active_1"  name="InventoryCostView" />查看庫存成本</label>
					   
					  </div>
                  </div>
                    <div class="container-fluid form-horizontal">
                	<div class="row text-center" >     
                        <input type="submit" value="新增" name="" id="" />
                    </div>
                
                </div>
              </div>
              
</form>
</div>

  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>
