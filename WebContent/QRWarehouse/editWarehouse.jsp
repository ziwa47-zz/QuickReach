<%@page import="tw.iii.warehouse.CWarehouse"%>
<%@page import="tw.iii.qr.DataBaseConn"%>
<%@page import="tw.iii.supplyCompany.CSupplyCompany"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*" %>
<jsp:useBean id="getWarehouse" class="tw.iii.warehouse.WarehouseFactory"  scope="page"/>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編輯倉庫</title>
</head>




<body><%@ include file="/href/navbar.jsp" %>

<%

Connection conn = new DataBaseConn().getConn();
CWarehouse wh  = getWarehouse.searchDetail(conn,request);

request.setAttribute("wh", wh);


conn.close();

%>

<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#AC7ED3;" >
      <ul class="nav nav-tabs">
       <li><a href="../SupplyCompany/SCManage.jsp">供應商帳號管理</a></li>
        <li><a href="warehouseManage.jsp" style="color:#fff">倉庫管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li><a href="warehouseManage.jsp" style="color: #fff">倉庫管理</a></li>
        <li><a href="addWarehouse.jsp" >新增倉庫</a></li>
      </ul>
    </div>
  </div>
</div>
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="../HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="warehouseManage.jsp">倉庫管理</a></li>
    <li><a href="editWarehouse.jsp">編輯倉庫</a></li>
  </ol>
</div>
<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" method="post" action="../WarehouseServlet.do" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>編輯倉庫</legend>
      <div class="container-fluid form-horizontal">
      
      <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>編號</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="id" type="text" value="${wh.getId()}" readonly>
          </div>
        </div>
      
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>倉庫代碼</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="warehouse" type="text" value="${wh.getWarehouse()}" >
          </div>
        </div>
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>倉庫名稱</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="warehouseName" type="text" value="${wh.getWarehouseName()}"  >
          </div>
        </div>
        
      
                 
       
        
         <div class="row text-center" id="formSubmit">
						<button type="submit" id="submitButton" name="submitButton" class="btn-lg btn-success" value="editWarehouse" >送出</button>
					</div>
      </div>
    </fieldset>
  </form>
</div>
  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>