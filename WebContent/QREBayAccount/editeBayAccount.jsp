<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*,tw.iii.qr.stock.DTO.CEbay" %>
<jsp:useBean id="getebay" class="tw.iii.qr.stock.CEbayFactory"  scope="page"/>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>editAccount</title>

<script src="../js/jquery-1.12.4.min.js"></script>


<script type="text/javascript">

$(document).ready(function(){
	
	  $("#deleteSubmit").click(function(event){
	    var ebayId = $("#ebayId").val();
	    var r = confirm("是否刪除此Ebay帳號:"+ebayId)
	    if(r==true){
	    	
	    	$("#searchform").submit();
	    }else {
	    	  return false
	    }
	  
	    
	  });
	  
	  $("#img").click(function(event){
		  
		    var ebayId = $("#ebayId").val();
		    var r = confirm("是否刪除此帳號:"+ebayId)
		    if(r==true){
		    	$("#img").before('<input type="hidden" name="submitType" value="deleteEbayAccount">');
		    	$("#searchform").submit();
		    }else {
		    	  return false
		    }
		  
		    
		  });
	  
	
	});




	
</script>


</head>
<body>
<%@include file="../href/navbar.jsp"%>
<c:if test="${PageCompetence.getEbayPaypalAccountEdit() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color: #C7AAE4;">
      <ul class="nav nav-tabs">
        <li><a href="eBayAccount.jsp">eBay帳號管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color: #984AC0;">
      <ul class="nav nav-tabs">
        <li><a href="eBayAccount.jsp">帳號列表</a></li>
        <!--               <li class="" style="background-color:#1CAF9A"><a href="purchasePage.jsp" style="color:#FFFFFF">進貨</a></li> -->
        <li><a href="addAccount.jsp">新增eBay帳號</a></li>
      </ul>
    </div>
  </div>
</div>

<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb">
    <li><a href="../HomePage.jsp">首頁</a></li>
    <li class="active"><a href="eBayAccount.jsp">eBay帳號管理</a></li>
    <li><a href="addAccount.jsp">新增eBay帳號</a></li>
  </ol>
</div>

<%
request.setCharacterEncoding("UTF-8");
String account1 = request.getParameter("ebayId");

System.out.println(account1);   
System.out.println(request.getParameter("ebayId"));
if(!"".equals(account1) || account1 != null){
Connection conn = new DataBaseConn().getConn();

CEbay ebayaccount  = getebay.searchDetail(account1);
session.setAttribute("eBayAccount", ebayaccount);
System.out.println(ebayaccount.getebayId());
}
%>

<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" id="searchform" method="post" action="../EbayAccountDo" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
    
    <div class="panel-title row ">
								<div class="col-md-11 form-group">
									<h2>修改eBay帳號</h2>
									<br/>
								</div>
								<div align="right" class="col-md-1 form-group">
									<button type="button" id="img" class="close" ><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
								</div>
							</div>
    
      
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>ebay ID</h4>
        </div>
        <div class="col-md-5 well-sm ">
          <input class="form-control" id="ebayId" type="text" name="ebayId" value="${eBayAccount.getebayId()}" readonly>
        </div>
      </div>
      
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>ebay Token</h4>
        </div>
        <div class="col-md-5 well-sm ">
          <textarea class="form-control" name="ebayToken"  rows="5"	cols="85" >${eBayAccount.getebayToken()}</textarea>
        </div>
      </div>
      
 <!--    <div class="row">
        <div class="col-md-3 well-sm">
          <h4>ebay endToken</h4>
        </div>
        <div class="col-md-5 well-sm control-label">
          <textarea class="form-control" name="endToken"  rows="5"	cols="85"  readonly>${eBayAccount.getendToken()}</textarea>
        </div>
      </div>
 -->  
       
      
      
       
       <div class="row">
        <div class="col-md-3 well-sm">
          <h4>paypal 帳號(E-mail)</h4>
        </div>
        <div class="col-md-5 well-sm ">
          <input class="form-control" type="text" name="paypalAccount" value="${eBayAccount.getpaypalAccount() }" >
        </div>
      </div>
      
        <div class="row">
        <div class="col-md-3 well-sm">
          <h4>國家</h4>
        </div>
        <div class="col-md-5 well-sm ">
          <input class="form-control" type="text" name="country" value="${eBayAccount.getcountry() }" >
        </div>
      </div>
      
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司</h4>
        </div>
        <div class="col-md-5 well-sm ">
        <input class="form-control" type="text" name="correspondCompany" id="correspondCompany" value="${eBayAccount.getcorrespondCompany()}">
         
        </div>
      </div>
      
        <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司電話</h4>
        </div>
        <div class="col-md-5 well-sm ">
        <input class="form-control" type="text" name="companyPhone" id="companyPhone" value="${eBayAccount.getcompanyPhone()}">
         
        </div>
      </div>
        <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司郵遞區號</h4>
        </div>
        <div class="col-md-5 well-sm ">
        <input class="form-control" type="text" name="companyPost" id="companyPost" value="${eBayAccount.getcompanyPost()}">
         
        </div>
      </div>
        <div class="row">
        <div class="col-md-3 well-sm">
          <h4>對應公司地址</h4>
        </div>
        <div class="col-md-5 well-sm ">
        <input class="form-control" type="text" name="companyAddress" id="companyAddress" value="${eBayAccount.getcompanyAddress()}">
         
        </div>
      </div>
      
      
      
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>啟用狀態</h4>
        </div>
       	  <div class="col-md-5 well-sm">
        	<c:if test="${eBayAccount.getstatus() eq 'ON' }">       	
         	 	<input type="radio" name="status" class="" value="ON" checked > 啟用
          		<input type="radio" name="status" class="" value="OFF"> 停用 
          	</c:if>
          	<c:if test="${eBayAccount.getstatus() eq 'OFF' }">	
          		<input type="radio" name="status" class="" value="ON"> 啟用
          		<input type="radio" name="status" class="" value="OFF" checked > 停用 
          	</c:if>
          </div>
      </div>
      
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>啟用時間</h4>          
        </div>                   
        <div class="col-md-5 well-sm ">
         <input class="form-control" type="text" name="startDate" value="${eBayAccount.getstartTime()}"  readonly>     
        </div>
      </div>
           
       <div class="row" >
        <div class="col-md-3 well-sm ">      
          <h4>最後修改時間 </h4>
        </div>
        <div class="col-md-5 well-sm ">
          <input class="form-control" type="text" name="lastFixDate" value="${eBayAccount.getlastFixTime() }" readonly>
        </div>
      </div>
      
 <!--   <div class="row">
        <div class="col-md-3 well-sm">
          <h4>回應評價文字</h4>
        </div>
        <div class="col-md-5 well-sm">
          <textarea class="form-control" name="comment"  rows="5" cols="85">${eBayAccount.getcomment()}</textarea>
        </div>
       </div>
 --> 
        
      <div class="row">
        <div class="col-md-3 well-sm">
          <h4>SystemFeedback</h4>
        </div>
        <div class="col-md-5 well-sm ">
          <textarea class="form-control" name="systemFeedback" rows="25" cols="95" >${eBayAccount.getsystemFeedback()}</textarea>
        </div>
      </div>
      
      <div class="" align="center">
   <!-- <input type="submit" name="submit" value="newAccount" class="btn-lg btn-success"> --> 
        <button type="submit" id="editSubmit" name="submitType" value="updateEbayAccount" class="btn-lg btn-success">修改送出</button> 
    	<a href="eBayAccount.jsp"><button type="button" value="取消" class="btn-lg btn-success">取消 </button></a>
    	 <button type="submit" id="deleteSubmit" name="submitType" value="deleteEbayAccount" class="btn-lg btn-success">刪除</button> 
     
      </div>
      
    </fieldset>
  </form>
</div>
</body>
</html>