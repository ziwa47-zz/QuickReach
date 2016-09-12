<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="competence1" class= "tw.iii.qr.QRAccountFactory" scope="page"/>
<%@ page  import=" tw.iii.qr.QRAccount,java.util.*,tw.iii.qr.DataBaseConn,tw.iii.qr.Competence"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新增帳號</title>
</head>

<body><%@ include file="/href/navbar.jsp" %>
<%
LinkedList<Competence> list= competence1.get權限();
session.setAttribute("getCompetenceLv", list);
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#AC7ED3;" >
      <ul class="nav nav-tabs">
        <li><a href="./accountManage.jsp" style="color:#fff">員工帳號管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li><a href="./accountManage.jsp">員工帳號管理</a></li>
        <li><a href="./Account.jsp" style="color: #fff">新增員工帳號</a></li>
      </ul>
    </div>
  </div>
</div>
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/QRMain/HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="./accountManage.jsp">員工帳號管理</a></li>
    <li><a href="./Account.jsp">新增員工帳號</a></li>
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
            <h4>登入帳號</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="account" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>密碼</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="password" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>姓氏</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="lastName" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>名字</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="firstName" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>E-mail</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="E-mail" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>英文名字</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control" name="enName" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>權限等級</h4>
          </div>
          <div class="col-md-5 well-sm">
            <select class="form-control" name="CompetenceLv" >
              <c:forEach var="i" varStatus="check" items="${getCompetenceLv}" begin="0" step="1">
                <option  value="${i.getCompetenceLv()}">${i.getCompetenceLv()}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        
        <!--                <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>簽名圖檔</h4></div>
                      <div class="col-md-5 well-sm"><input class="checkbox-inline" name="signatureImage" type="file" ></div>
                  </div>
     -->
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>帳號狀態</h4>
          </div>
          <div class="col-md-5 well-sm">
            <label class="checkbox-inline">
              <input type="radio" id="id_fd-is_active_0" value="1" name="status" />
              有效</label>
            <label class="checkbox-inline">
              <input type="radio" id="id_fd-is_active_1" value="0" name="status" checked/>
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
