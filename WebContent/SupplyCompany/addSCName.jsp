<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<jsp:useBean id="competence1" class= "tw.iii.Maintain.QRAccountFactory" scope="page"/>
<%@ page  import=" tw.iii.supplyCompany.CSupplyCompany,java.util.*,tw.iii.qr.DataBaseConn"%>
<!DOCTYPE html>
<html>
<head>
<meta  charset=UTF-8>
<title>新增供應商名稱</title>
</head>

<body><%@ include file="/href/navbar.jsp" %>
<c:if test="${PageCompetence.getParamSettingEdit() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#AC7ED3;" >
      <ul class="nav nav-tabs">
        <li><a href="SCManage.jsp" style="color:#fff">供應商帳號管理</a></li>
        <li><a href="../QRWarehouse/warehouseManage.jsp" >倉庫管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li><a href="SCManage.jsp">供應商帳號管理</a></li>
        <li><a href="./addSCName.jsp" style="color: #fff">新增供應商帳號</a></li>
      </ul>
    </div>
  </div>
</div>
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="../HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="SCManage.jsp">供應商帳號管理</a></li>
    <li><a href="addSCName.jsp">新增供應商帳號</a></li>
  </ol>
</div>
<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" method="post" action="../SupplyCompanyServlet.do" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>輸入帳號資料</legend>
      <div class="container-fluid form-horizontal">
      
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>公司簡稱</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="companyId" type="text" >
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>公司全名</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="companyName" type="text" >
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>電話</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="tel" type="text" >
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>傳真</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="fax" type="text" >
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>地址</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="address" type="text" >
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>備註</h4>
          </div>
          <div class="col-md-5 well-sm ">     
             <textarea class="form-control" name="comment"  rows="2"	cols="55"  required></textarea>
          </div>
        </div>
                 
       
        
       <div class="row text-center" id="formSubmit">
						<button type="submit" id="submitButton" name="submitButton" class="btn-lg btn-success" value="addCompany" >送出</button>
					</div>
      </div>
    </fieldset>
  </form>
</div>
  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>