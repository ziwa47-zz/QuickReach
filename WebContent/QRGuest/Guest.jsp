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
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#C7AAE4;" >
      <ul class="nav nav-tabs">
        <li><a href="./GuestManage.jsp" style="color:#fff">客戶管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li><a href="./GuestManage.jsp">客戶管理</a></li>
        <li><a href="./Guest.jsp" style="color: #fff">新增客戶</a></li>
      </ul>
    </div>
  </div>
</div>
  
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="../HomePage.jsp" >首頁</a></li>
    <li class="active"><a href="./GuestManage.jsp">客戶管理</a></li>
    <li><a href="./Guest.jsp">新增客戶</a></li>
  </ol>
</div>
<div class="container" style="background: #E9C2D0; border-radius:20px;">
  <form name="searchform" id="searchform" method="post" action="../GuestServelet" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>輸入客戶資料</legend>
      <div class="container-fluid form-horizontal">
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>客戶ID</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" title="請輸入客戶ID" name="guestId" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>姓名</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" title="請輸入姓名" name="name" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>公司名稱</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" title="請輸入公司名稱" name="company" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>平台帳號</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="platformAccount" title="請輸入平台帳號" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>Email</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="email" title="請輸入email" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>國家</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="country" title="請輸入國家" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>電話</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="tel" title="請輸入電話" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>手機</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="phone" title="請輸入手機" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>地址</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="address" title="請輸入地址" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>郵遞區號</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="postcode" title="請輸入郵遞區號" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>生日</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="birthday" title="請輸入生日" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>性別</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="gender" title="請輸入性別" type="text" >
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 text-right well-sm label-tag"  >
            <h4>備註</h4>
          </div>
          <div class="col-md-5 well-sm">
            <input class="form-control required" name="comment" title="請輸入備註" type="text" >
          </div>
        </div>
        <div class="row" align="center">              
         	 <button type="submit" name="submitType" value="newGuestAccount" class="btn-lg btn-success">新增</button>
       		 <a href="GuestManage.jsp"><button type="button" value="取消" class="btn-lg btn-success">取消 </button></a>
        </div>
        
      </div>
    </fieldset>
  </form>
</div>
  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>
