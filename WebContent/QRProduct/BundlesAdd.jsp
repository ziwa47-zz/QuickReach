<%@ page import="tw.iii.qr.stock.CProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="blf" scope="session" class="tw.iii.qr.stock.BundlesFactory" />
<jsp:setProperty name="blf" property="*"/>   

<html lang="en">
  <head>
    <meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增組合商品</title>
    <!-- Bootstrap -->
	<link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/smoothness/jquery-ui.css">

      <!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
	<script src="js/jquery-1.12.4.min.js"></script>
    <script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
	<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.ui.datepicker-zh-TW.js"></script>
    <script type="text/javascript">
    function getSelect(){
    	listForm.action = "BundlesAdd.jsp"
	    listForm.submit()
	    	
    };
       
    function getSelectt(){
	    document.getElementById('subBrand').value='select';
    	document.getElementById('productSKU').value='select';
    	document.getElementById('P_name').value='select';
    	listForm.action = "BundlesAdd.jsp"
	    listForm.submit()	    	
	};
    
    function getSelectPname(){
    	document.getElementById('productSKU').value='select';
    	listForm.action = "BundlesAdd.jsp"
    	listForm.submit()	    	    	    	
	};
    
    function getSelectPSKU(){
    	document.getElementById('P_name').value='select';
    	listForm.action = "BundlesAdd.jsp"
    	listForm.submit()	    	    	    	
	};
    
    function checkDetailSKUValue(){
    	
    	//var smtstatus = false;
    	
    	if($("#productSKU").val()=='select' ){
    		$("#listForm").submit( function () { 
    			  return false; 
    		});
    		alert("請選擇子商品"); 
    		//smtstatus = false;
    	}/*else if($("#productSKU").val()!='select' & $("#qty").val() <=0 ){
    		$("#listForm").submit( function () { 
  			  return false; 
	  		});
	  		alert("請輸入正確數量");  
	  		//smtstatus = false;		  		
  		}*/else{
  			$("#listForm").validate({
      			rules: {
      			    qty: "required",
	      			/*productSKU:{
	      				checksku:"select",
	      			},*/
      			},
      			messages: {
      			    qty: "请输入子商品數量",   			  
      			  	/*productSKU:{
      			  		checksku:"請選擇子商品",
        			},*/
      			}
      		});
  			/*$("#listForm").submit( function () { 
    			 smtstatus = true; 
  	  		});*/
  	  		
  			/*listForm.action = "bundlesAdd.do"
    		listForm.submit()*/
    	}
    	/* $("#listForm").submit( function () { 
        		 return smtstatus; 
      	 });*/
    };
    
 	$.validator.addMethod("checksku",function(value,ele,param){return value!=param;},"");
    
    $.validator.addMethod("skucheck",function(value,element,arg) 
    {return value != arg;},"");             
    
    $().ready(function() {	
    	$("#smtinsert").click(function() {
    	  $("#listForm").validate({
    			rules: {
    			  bdsku: "required",
    			  bdname: "required",    		
    			},
    			messages: {
    			  bdsku: "请输入商品sku碼",
    			  bdname: "请输入商品名稱",    			
   			  
    			}
    		});
    	});
    });
    
   /* $().ready(function() {	    	
    	$("#smtadd").click(function() {
      	  $("#listForm").validate({
      			rules: {
      			    qty: "required",
	      			/*productSKU:{
	      				checksku:"select",
	      			},
      			},
      			messages: {
      			    qty: "请输入子商品數量",   			  
      			  	/*productSKU:{
      			  		checksku:"請選擇子商品",
        			},
      			}
      		});
      	});
    });*/
       
    </script>
<style>
.error{
	color:red;
}
</style> 
  </head>
  <body>
 <%@ include file = "/href/navbar.jsp"%>
  <div class="nav">
  	<div class="container">
    	<div class="navbar-left" style="background-color:#BCF1E5;" >
        	<ul class="nav nav-tabs">
              <li ><a href="SearchStockPage.jsp" style="color:#000">庫存</a></li>
              <li class="" style="background-color:#1CAF9A"><a href="SearchProductPage.jsp" style="color:#fff">商品</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li><a href="SearchProductPage.jsp" style="color: #000">查詢商品</a></li>
			  <li><a href="TotalBundles.jsp" style="color: #000000">查詢組合商品</a></li>
			  <li><a href="NewProduct.jsp" style="color: #000000">新增單項商品</a></li>
			  <li class="" style="background-color: #1CAF9A"><a href="BundlesAdd.jsp" style="color: #fff">新增組合商品</a></li>
            </ul>
        </div>
    </div>
  
  </div>
  
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
          <li><a href="../QRMain/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchProductPage.jsp">庫存/商品管理</a></li>
			<li><a href="BundlesAdd.jsp">新增組合商品</a></li>
      </ol>
        </div>
  
  <div class="container table-responsive bg-warning" style="background: #9DDCD1; border-radius:20px">
  	<form id="listForm" name="listForm" method="post" action="bundlesAdd.do" style="font-size: 100%; vertical-align: baseline; 
    padding: 15px; " class="form-inline container">

<%

request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");
if(request.getParameter("bdsku") != null){
session.setAttribute("bdSKU", new String(request.getParameter("bdsku").getBytes("8859_1"), "UTF-8"));
}
if(request.getParameter("bdname") != null){
session.setAttribute("bdname", new String(request.getParameter("bdname").getBytes("8859_1"), "UTF-8"));
}
if(request.getParameter("comment") != null){
session.setAttribute("bdcomment", new String(request.getParameter("comment").getBytes("8859_1"), "UTF-8"));
}
String bd = null;
String subbd = null;
String sku = null;
String pname = null;
if (request.getParameter("brand") != null) {
	bd = new String(request.getParameter("brand").getBytes("8859_1"), "UTF-8");
	request.setAttribute("parambrand", bd);
}
if (request.getParameter("subBrand") != null) {
	subbd = new String(request.getParameter("subBrand").getBytes("8859_1"), "UTF-8");
	request.setAttribute("paramsubBrand", subbd);
}
if (request.getParameter("productSKU") != null) {
	sku = new String(request.getParameter("productSKU").getBytes("8859_1"), "UTF-8");
	request.setAttribute("paramproductSKU", sku);
}
if (request.getParameter("P_name") != null) {
	pname = new String(request.getParameter("P_name").getBytes("8859_1"), "UTF-8");
	request.setAttribute("paramP_name", pname);
}


LinkedList<CProduct> list =  new LinkedList<CProduct>();
list = blf.getProductInfo(bd,subbd,sku,pname);
request.setAttribute("list",list);

LinkedList<CProduct> listBrand =  new LinkedList<CProduct>();
listBrand = blf.getBrand();
request.setAttribute("listBrand",listBrand);

LinkedList<CProduct> listSubBrand =  new LinkedList<CProduct>();
listSubBrand = blf.getSubBrand(bd);
request.setAttribute("listSubBrand",listSubBrand);

%>

      <fieldset class="container-fluid" style="padding:0 30 0 0;"><legend>新增組合商品</legend>
    	<input type="hidden">
  
        <div class="row">
		  <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >SKU：</label></h5></div>
              <div class="col-md-8">          	
              		<input class="form-control" type="text" id="bdsku" name="bdsku" value="${bdSKU}">
              </div>
            </div>
          </div>
		  <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >商品名稱：</label></h5></div>
              <div class="col-md-10">
              		<input class="form-control" id="bdname" name="bdname" type="text" value="${bdname}">
              </div>
            </div>
          </div>
		  <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >備註：</label></h5></div>
              <div class="col-md-8">
              		<textarea style="width:178px;height:55px;"  class="form-control" name="comment" >${bdcomment}</textarea>           	
              </div>
            </div>
          </div>
		</div>
      </fieldset>
  	  <fieldset class="container-fluid" style="padding:0 30 0 0;"><legend>選擇子商品</legend>
        <table class="table table-hover table-condensed pull-left" >
          <thead>
            <tr>
              <th width="100px">廠牌</th>
              <th width="100px">副廠牌</th>
              <th width="25%">商品編號</th>
              <th>商品名稱</th>
     
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
             
              	<select name="brand" onChange="getSelectt()" >
              	
              			<option value="select">==請選擇==</option>      
   						<option value="select"></option>
              
              		<c:forEach var="i" varStatus="check" items="${listBrand}" begin="0" step="1" >
						<c:if test="${parambrand == i.getBrand() }">
							<option selected="selected" value="${i.getBrand()}">${i.getBrand()}</option>
						</c:if>
						<c:if test="${parambrand != i.getBrand() }">
							<option value="${i.getBrand()}">${i.getBrand()}</option>
						</c:if>
              		</c:forEach>
              	</select>
              </td>
              <td>              
              	<select name="subBrand" id="subBrand" onChange="getSelect()">
              		
              			<option value="select">==請選擇==</option>

              		<c:forEach var="i" varStatus="check" items="${listSubBrand}" begin="0" step="1" >
						<c:if test="${paramsubBrand == i.getSubBrand()}">
							<option selected="selected" value="${i.getSubBrand()}">${i.getSubBrand()}</option>
              			</c:if>
              			<c:if test="${paramsubBrand != i.getSubBrand()}">
							<option value="${i.getSubBrand()}">${i.getSubBrand()}</option>
              			</c:if>
              		</c:forEach>
              	</select>
              </td>              
              <td nowrap>
	              <select name="productSKU" id="productSKU" onChange="getSelectPSKU()">
	              	<option value="select">==請選擇==</option>
						<c:forEach var="i" varStatus="check" items="${list}" begin="0" step="1" >
	            			<c:if test="${paramproductSKU ne 'select'}">
		            			<c:if test="${paramproductSKU == i.getSKU()}">
		            				<option selected="selected" value="${i.getSKU()}">${i.getSKU()}</option>
		            			</c:if>
		            			<c:if test="${paramproductSKU != i.getSKU()}">
		            				<option value="${i.getSKU()}">${i.getSKU()}</option>
		            			</c:if>
	            			</c:if>
	            			<c:if test="${paramproductSKU eq 'select'}">
		            			<c:if test="${paramP_name == i.getP_name()}">
			            			<option selected="selected" value="${i.getSKU()}">${i.getSKU()}</option>
			            		</c:if>
			            		<c:if test="${paramP_name != i.getP_name()}">
			            			<option value="${i.getSKU()}">${i.getSKU()}</option>
			            		</c:if>	
	            			</c:if>
	            		</c:forEach>						
	              </select>
              </td>
              <td>
              	<select name="P_name" id="P_name" onChange="getSelectPname()">
	            	<option value="select">==請選擇==</option>
		            	<c:forEach var="i" varStatus="check" items="${list}" begin="0" step="1" >
		            		<c:if test="${paramP_name ne 'select'}">
		            			<c:if test="${paramP_name == i.getP_name()}">
		            				<option selected="selected" value="${i.getP_name()}">${i.getP_name()}</option>
		            			</c:if>
		            			<c:if test="${paramproductSKU != i.getSKU()}">
		            				<option value="${i.getP_name()}">${i.getP_name()}</option>
		            			</c:if>
	            			</c:if>
	            			<c:if test="${paramP_name eq 'select'}">
		            			<c:if test="${paramproductSKU == i.getSKU()}">
			            			<option selected="selected" value="${i.getP_name()}">${i.getP_name()}</option>
			            		</c:if>
			            		<c:if test="${paramproductSKU != i.getSKU()}">
			            			<option value="${i.getP_name()}">${i.getP_name()}</option>
			            		</c:if>	
			            	</c:if>      		
		            	</c:forEach>
	            </select>
              </td>
            </tr> 
            <tr> <th>數量:<input type="number" id="qty" name="qty"></th></tr>
            <tr>
            	<td ><button type="submit" id="smtadd" name="smt" value="add" onclick="checkDetailSKUValue()">加入</button></td>
            </tr>           
          </tbody>
        </table>
      </fieldset>
   
  </div>

<div class="container table-responsive bg-warning"	style="border-radius: 20px" >
	<table class="table table-bordered table-hover table-condensed pull-left" style="margin: 0 0 0 -15px" >
			<tr class="ListTitle2">
				<th>SKU</th>
				<th>品名</th>
				<th>數量</th>
				<th>庫存量</th>
				<th></th>							
			</tr>

		<c:forEach var="i" varStatus="check" items="${getBundlesAddDetail}" begin="0" step="1">
			<tr>
				<td><a href="StockDetail.jsp?sku=${i[0]}">${i[0]}</a></td>
				<td>${i[1]}</td>
				<td>${i[2]}</td>
				<c:set var="getStockSKU" scope="session" value="${i[0]}"></c:set>
				<%
				String getStockSKU = (String)session.getAttribute("getStockSKU");
				request.setAttribute("stock", blf.getStock(getStockSKU));
				%>
				<td>${stock}</td>
				<td><button type="submit" name="smt" value="${i[0]}" >刪除</button></td>
			</tr>
		</c:forEach>    
	</table>	
</div>
	<center><button type="submit" id="smtinsert" name="smt" value="insert" >新增</button></center>
</form>

    
</body>
  
<%@ include file="/href/footer.jsp" %>    
 
</html>