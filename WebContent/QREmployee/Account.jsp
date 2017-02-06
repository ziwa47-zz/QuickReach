<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="competence1" class= "tw.iii.Maintain.QRAccountFactory" scope="page"/>
<%@ page  import=" tw.iii.Maintain.QRAccount,java.util.*,tw.iii.qr.DataBaseConn,tw.iii.Competenece.Competence"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新增帳號</title>

<script src="../js/jquery-1.12.4.min.js"></script>


<script type="text/javascript">
	
	$(function() {

		$("#searchform").validate({
			//debug:true,
			rules:{
					
				checkPassword:{
						equalTo:"#password"
					}
				},
			ignore : [],
			invalidHandler : function(form) {
				

			}
		});
	});
</script>

<style type="text/css">
#searchform label.error {
font-size: 0.8em;
color: #F00;
font-weight: bold;
display: block;


}
</style>
</head>

<body><%@ include file="/href/navbar.jsp" %>
<c:if test="${PageCompetence.getAccountInfoEdit() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
LinkedList<Competence> list= competence1.getCompetence();
session.setAttribute("getCompetenceLv", list);
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#C7AAE4;" >
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
    <li><a href="../HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="./accountManage.jsp">員工帳號管理</a></li>
    <li><a href="./Account.jsp">新增員工帳號</a></li>
  </ol>
</div>
<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" id="searchform" method="post" action="QRAccountServlet.do" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>輸入帳號資料</legend>
      <div class="container-fluid form-horizontal">
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>登入帳號</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="account" title="請輸入帳號" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag "  >
            <h4>密碼</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" id="password" title="請輸入密碼" name="password" type="password" >
          </div>
        </div>
        
         <div class="row">
          <div class="col-md-3 text-right well-sm label-tag "  >
            <h4>確認密碼</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" title="確認密碼錯誤" name="checkPassword" type="password" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>姓氏</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" title="請輸入姓氏" name="lastName" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>名字</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="firstName" title="請輸入名字" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>E-mail</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="E-mail" title="請輸入E-mail" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>英文名字</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="enName" title="請輸入英文名字" type="text" >
          </div>
        </div>
        
        
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>權限等級</h4>
          </div>
          <div class="col-md-5 well-sm">
            <select class="form-control" name="competenceLv" >
              <c:forEach var="i" varStatus="check" items="${getCompetenceLv}" begin="0" step="1">
                <option  value="${i.getCompetenceLv()}">${i.getCompetenceLv()}</option>
              </c:forEach>
            </select>
          </div>
        </div>
   
       <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>帳號狀態</h4>
          </div>
          <div class="col-md-5 well-sm">           
              <input type="radio" id="id_fd-is_active_0" value="1" name="status" checked/>有效           
              <input type="radio" id="id_fd-is_active_1" value="0" name="status" />停用 </div>
        </div>
        
        <div class="row" align="center">              
         	 <button type="submit" name="submitType" value="addAccount" class="btn-lg btn-success">新增</button>
       		 <a href="accountManage.jsp"><button type="button" value="取消" class="btn-lg btn-success">取消 </button></a>
        </div>
        
      </div>
    </fieldset>
  </form>
</div>
  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>
