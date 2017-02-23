<%@page import="tw.iii.qr.DataBaseConn"%>
<%@page import="tw.iii.qr.stock.DTO.CEbay"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*" %>
<jsp:useBean id="getebay" class="tw.iii.qr.stock.CEbayFactory"  scope="page"/>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>eBayAccount</title>
</head>


<body>
<%@include file="../href/navbar.jsp"%>
<c:if test="${PageCompetence.getEbayPaypalAccountEdit() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<%
Connection conn1 = new DataBaseConn().getConn();
LinkedList<CEbay> ebay1 = getebay.searchEbayAc(request,conn1);
session.setAttribute("ebay", ebay1);
conn1.close();
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#C7AAE4;" >
      <ul class="nav nav-tabs">
        <li><a href="./eBayAccount.jsp" style="color:#fff">eBay帳號管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li ><a href="./eBayAccount.jsp" style="color:#fff">帳號列表</a></li>
        <!--               <li style="background-color:#1CAF9A"><a href="purchasePage.jsp" style="color:#FFFFFF">進貨</a></li> -->
        <li><a href="./addAccount.jsp">新增eBay帳號</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="../HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="./eBayAccount.jsp">eBay帳號管理</a></li>
    <li><a href="./eBayAccount.jsp">帳號列表</a></li>
  </ol>
</div>
<div class="container table-responsive" style="background: #E9C2D0; border-radius:20px;">
  <form name="listForm" method="post" action="../EbayAccountDo" class="form-inline container required" 
  	style="font-size: 100%; vertical-align: baseline; padding: 15px; ">
    <fieldset id="myfields" class="font-weight" style="padding:0 30px 0 0;">
      <legend>eBay帳號管理</legend>
      <table class="table table-bordered table-hover table-condensed pull-left"  >
        <thead>
          <tr class="ListTitle2">
            <th>編輯</th>
            <th>eBayID</th>
        <!--    <th>eBay Token</th>  -->  
        <!--    <th>Token 到期</th>  --> 
            <th>paypal 帳號</th>
            <th>對應公司</th>
            
            <th>對應公司地址</th>
            <th>對應公司電話</th>
            <th>對應公司郵遞區號</th>
           
            <th>啟動時間</th>
            <th>最後修改時間</th>
            <th>狀態</th>
        <!--    <th>Comment</th>    --> 
          </tr>
        </thead>
        <tbody>
          <c:forEach var="i" varStatus="check" items="${ebay}" begin="0" step="1">
            <tr>
              <td><a href ="editeBayAccount.jsp?ebayId=${i.getebayId()}"> <img src="../img/compose-4.png"></a></td>
              <!-- <td width="50px" align="left"><input class="checker" type="checkbox" name="checking_companys[]" value="10"></td>	 -->
              <td>${i.getebayId()}</a></td>
              <!-- ebayId -->
        <!--      <td>${i.getebayToken()}</a></td>  -->
              <!-- ebayToken -->
        <!--      <td>${i.getendToken()}</td>  -->
              <!-- endToken -->
              <td>${i.getpaypalAccount() }</td>
              <!-- paypallAccount -->
              <td>${i.getcorrespondCompany()}</td>
              <!-- correspondCompany-->
              
              <td>${i.getcompanyAddress()}</td>
              <td>${i.getcompanyPhone()}</td>
              <td>${i.getcompanyPost()}</td>
              
              <td>${i.getstartTime()}</td>
              <!-- startTime-->
              <td>${i.getlastFixTime()}</td>
              <!-- lastFixTime -->
              <td>${i.getstatus()}</td>
              <!-- status -->
        <!--  <td>${i.getcomment()}</td>   --> 
              <!-- comment -->
              <!-- systemFeedback --> 
            </tr>
            
         
            
          </c:forEach>
        </tbody>
      </table>
      
      <!-- 			<div class="submit" align="center">
				<span id="submit_button_span"> <input type="button"	value="delete">
				</span>
			</div>
 --> <!-- 停用delete button,整合到編輯的頁面 -->
      
    </fieldset>
  </form>
</div>
<%@include file="../href/footer.jsp" %>
</body>
</html>