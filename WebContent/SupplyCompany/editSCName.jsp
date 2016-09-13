<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*,tw.iii.supplyCompany.CSupplyCompany" %>
<jsp:useBean id="getebay" class="tw.iii.supplyCompany.SCFactory"  scope="page"/>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯供應商帳號</title>
</head>

<body><%@ include file="/href/navbar.jsp" %>

<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#AC7ED3;" >
      <ul class="nav nav-tabs">
        <li><a href="./accountManage.jsp" style="color:#fff">供應商帳號管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li><a href="">供應商帳號管理</a></li>
        <li><a href="./addSCName.jsp" style="color: #fff">新增供應商帳號</a></li>
      </ul>
    </div>
  </div>
</div>
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/QRMain/HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="">供應商帳號管理</a></li>
    <li><a href="./addSCName.jsp">新增供應商帳號</a></li>
  </ol>
</div>
<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" method="post" action="../EbayAccountDo" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>輸入帳號資料</legend>
      <div class="container-fluid form-horizontal">
      
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>公司序號</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="companyId" type="text" value="${supplycompany.getCompanyId()}" readonly>
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>公司名稱</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="companyName" type="text" value="${supplycompany.getCompanyName()}" >
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>電話</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="tel" type="text" value="${supplycompany.getTel()}">
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>傳真</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="fax" type="text" value="${supplycompany.getFax()}">
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>地址</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="address" type="text" value="${supplycompany.getAddress()}">
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
                 
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>帳號狀態</h4>
          </div>
          <div class="col-md-5 well-sm">
            <label class="checkbox-inline">
              <input type="radio" id="id_fd-is_active_0" value="1" name="status" checked/>
              有效</label>
            <label class="checkbox-inline">
              <input type="radio" id="id_fd-is_active_1" value="0" name="status" />
              停用</label>
          </div>
        </div>
        
        <div class="row">
          <center>
            <button class="btn-lg btn-success" name="submit" type="submit" value="addAccount" >新增</button>
          </center>
        </div>
      </div>
    </fieldset>
  </form>
</div>
  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>