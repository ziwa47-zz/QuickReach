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
    <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增複合商品</title>
    <!-- Bootstrap -->
	<link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/smoothness/jquery-ui.css">

      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="js/bootstrap.js"></script>

    
    <script src="js/jquery-1.12.4.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.ui.datepicker-zh-TW.js"></script>
    <script type="text/javascript">
    function getSelect(){
    	listForm.action = "ProductAddPage.jsp"
	    listForm.submit()
	    	
    }
       
    function getSelectt(){
	    document.getElementById('subBrand').value='select';
    	document.getElementById('productSKU').value='select';
    	document.getElementById('P_name').value='select';
    	listForm.action = "ProductAddPage.jsp"
	    listForm.submit()	    	
	}
    
    function getSelectPname(){
    	document.getElementById('productSKU').value='select';
    	listForm.action = "ProductAddPage.jsp"
    	listForm.submit()	    	    	    	
	}
    
    function getSelectPSKU(){
    	document.getElementById('P_name').value='select';
    	listForm.action = "ProductAddPage.jsp"
    	listForm.submit()	    	    	    	
	}
    
    </script>
    
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
              <li ><a href="SearchProductPage.jsp" style="color:#000">查詢商品</a></li>
              <li class="" style="background-color:#1CAF9A"><a href="ProductAddPage.jsp" style="color:#fff">新增複合商品</a></li>
              <li><a href="ProductEditPage.jsp" style="color:#000000">修改商品</a></li>
            </ul>
        </div>
    </div>
  
  </div>
  
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
          <li><a href="../QRMain/HomePage.jsp" >首頁</a></li>
          <li class="active" style="display:"><a href="SearchProductPage.jsp">庫存/商品管理</a></li>
          <li><a href="ProductAddPage.jsp">新增複合商品</a></li>
      </ol>
        </div>
  
  <div class="container table-responsive bg-warning" style="background: #9DDCD1; border-radius:20px">
  	<form id="listForm" name="listForm" method="post" action="bundlesAdd.do" style="font-size: 100%; vertical-align: baseline; 
    padding: 15px; " class="form-inline container">

<%

request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");

String bd = request.getParameter("brand");
String subbd = request.getParameter("subBrand");
String sku = request.getParameter("productSKU");
String pname = request.getParameter("P_name");

LinkedList<CProduct> list =  new LinkedList<CProduct>();
list = blf.getProductInfo(bd,subbd,sku,pname);
request.setAttribute("list",list);

LinkedList<CProduct> listBrand =  new LinkedList<CProduct>();
listBrand = blf.getBrand();
request.setAttribute("listBrand",listBrand);



LinkedList<CProduct> listSubBrand =  new LinkedList<CProduct>();
listSubBrand = blf.getSubBrand(bd);
request.setAttribute("listSubBrand",listSubBrand);

LinkedList<CProduct> listSKU =  new LinkedList<CProduct>();
listSKU = blf.getSKU();
request.setAttribute("listSKU",listSKU);

%>

      <fieldset class="container-fluid" style="padding:0 30 0 0;"><legend>新增複合商品</legend>
    	<input type="hidden">
  
        <div class="row">
		  <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >SKU：</label></h5></div>
              <div class="col-md-8"><input class="form-control" type="text" name="bdsku" value="${bdsku}"></div>
            </div>
          </div>
		  <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >商品名稱：</label></h5></div>
              <div class="col-md-10"><input class="form-control" name="bdname" type="text" value="${param.bdname }"></div>
            </div>
          </div>
		  <div class="col-md-8 form-group ">
            <div class="row">
              <div class="col-md-2"><h5><label for="focusedInput " >備註：</label></h5></div>
              <div class="col-md-8"><textarea rows="2"  class="form-control" name="comment" >${param.comment }</textarea></div>
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
              <th width="50px">數量	</th>
              
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
             
              	<select name="brand" onChange="getSelectt()" >
              	
              			<option value="select">==請選擇==</option>      
   						<option value="select"></option>
              
              		<c:forEach var="i" varStatus="check" items="${listBrand}" begin="0" step="1" >
						<c:if test="${param.brand == i.getBrand() }">
							<option selected="selected" value="${i.getBrand()}">${i.getBrand()}</option>
						</c:if>
						<c:if test="${param.brand != i.getBrand() }">
							<option value="${i.getBrand()}">${i.getBrand()}</option>
						</c:if>
              		</c:forEach>
              	</select>
              </td>
              <td>              
              	<select name="subBrand" id="subBrand" onChange="getSelect()">
              		
              			<option value="select">==請選擇==</option>

              		<c:forEach var="i" varStatus="check" items="${listSubBrand}" begin="0" step="1" >
						<c:if test="${param.subBrand == i.getSubBrand()}">
							<option selected="selected" value="${i.getSubBrand()}">${i.getSubBrand()}</option>
              			</c:if>
              			<c:if test="${param.subBrand != i.getSubBrand()}">
							<option value="${i.getSubBrand()}">${i.getSubBrand()}</option>
              			</c:if>
              		</c:forEach>
              	</select>
              </td>              
              <td nowrap>
	              <select name="productSKU" id="productSKU" onChange="getSelectPSKU()">
	              	<option value="select">==請選擇==</option>
						<c:forEach var="i" varStatus="check" items="${list}" begin="0" step="1" >
	            			<c:if test="${param.productSKU ne 'select'}">
		            			<c:if test="${param.productSKU == i.getSKU()}">
		            				<option selected="selected" value="${i.getSKU()}">${i.getSKU()}</option>
		            			</c:if>
		            			<c:if test="${param.productSKU != i.getSKU()}">
		            				<option value="${i.getSKU()}">${i.getSKU()}</option>
		            			</c:if>
	            			</c:if>
	            			<c:if test="${param.productSKU eq 'select'}">
		            			<c:if test="${param.P_name == i.getP_name()}">
			            			<option selected="selected" value="${i.getSKU()}">${i.getSKU()}</option>
			            		</c:if>
			            		<c:if test="${param.P_name != i.getP_name()}">
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
		            		<c:if test="${param.P_name ne 'select'}">
		            			<c:if test="${param.P_name == i.getP_name()}">
		            				<option selected="selected" value="${i.getP_name()}">${i.getP_name()}</option>
		            			</c:if>
		            			<c:if test="${param.productSKU != i.getSKU()}">
		            				<option value="${i.getP_name()}">${i.getP_name()}</option>
		            			</c:if>
	            			</c:if>
	            			<c:if test="${param.P_name eq 'select'}">
		            			<c:if test="${param.productSKU == i.getSKU()}">
			            			<option selected="selected" value="${i.getP_name()}">${i.getP_name()}</option>
			            		</c:if>
			            		<c:if test="${param.productSKU != i.getSKU()}">
			            			<option value="${i.getP_name()}">${i.getP_name()}</option>
			            		</c:if>	
			            	</c:if>      		
		            	</c:forEach>
	            </select>
              </td>
              <td><input type="number" name="qty"></td>
              
            </tr> 
            <tr>
            	<td ><button type="submit" name="smt" value="add" onclick="getSelect()">加入</button></td>
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
				<th></th>							
			</tr>

		<c:forEach var="i" varStatus="check" items="${getBundlesDetail}" begin="0" step="1">
			<tr>
				<td>${i[0]}</td>
				<td>${i[1]}</td>
				<td>${i[2]}</td>
				<td><button type="submit" name="delete" value="${i[0]}" onclick="getSelect()">刪除</button></td>
			</tr>
		</c:forEach>    
	</table>	
</div>
	<center><button type="submit" name="last" value="insert" onclick="getSelect()">送出</button></center>
</form>

    
</body>
  
<%@ include file="/href/footer.jsp" %>    
 
</html>