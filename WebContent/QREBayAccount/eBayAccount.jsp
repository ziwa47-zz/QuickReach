<%@page import="tw.iii.qr.DataBaseConn"%>
<%@page import="tw.iii.qr.stock.CEbay"%>
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

<!-- Bootstrap -->
	<link href="./css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./css/smoothness/jquery-ui.css">

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.js"></script>

    
    <script src="./js/jquery-1.12.4.min.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/jquery.ui.datepicker-zh-TW.js"></script>
    

<script type="text/javascript">

</script>

</head>


<body>
<%@include file="../href/navbar.jsp"%>

<%
Connection conn1 = new DataBaseConn().getConn();
LinkedList<CEbay> ebay1 = getebay.searchEbayAc(request,conn1);
session.setAttribute("ebay", ebay1);
conn1.close();
%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#E48EB9;" >
      <ul class="nav nav-tabs">
        <li><a href="./eBayAccount.jsp">eBay帳號管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#984AC0;" >
      <ul class="nav nav-tabs">
        <li ><a href="./eBayAccount.jsp">帳號列表</a></li>
        <!--               <li style="background-color:#1CAF9A"><a href="purchasePage.jsp" style="color:#FFFFFF">進貨</a></li> -->
        <li><a href="./addAccount.jsp">新增eBay帳號</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="../QRMain/HomePage.jsp" >首頁</a></li>
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
            <th>eBay Token</th>
            <th>Token 到期</th>
            <th>paypal 帳號</th>
            <th>對應公司</th>
            <th>啟動時間</th>
            <th>最後修改時間</th>
            <th>狀態</th>
            <th>Comment</th>
            <th>SystemFeedback</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="i" varStatus="check" items="${ebay}" begin="0" step="1">
            <tr>
              <td><a href ="editeBayAccount.jsp?ebayId=${i.getebayId()}"> <img src="../img/compose-4.png"></a></td>
              <!-- <td width="50px" align="left"><input class="checker" type="checkbox" name="checking_companys[]" value="10"></td>	 -->
              <td>${i.getebayId()}</a></td>
              <!-- ebayId -->
              <td>${i.getebayToken()}</a></td>
              <!-- ebayToken -->
              <td>${i.getendToken()}</td>
              <!-- endToken -->
              <td>${i.getpaypalAccount() }</td>
              <!-- paypallAccount -->
              <td>${i.getcorrespondCompany()}</td>
              <!-- correspondCompany-->
              <td>${i.getstartTime()}</td>
              <!-- startTime-->
              <td>${i.getlastFixTime()}</td>
              <!-- lastFixTime -->
              <td>${i.getstatus()}</td>
              <!-- status -->
              <td>${i.getcomment()}</td>
              <!-- comment -->
              <td>${i.getsystemFeedback()}</td>
              <!-- systemFeedback --> 
            </tr>
            
            <!-- 				<tr>				
                    <td width="50px" align="left"><input class="checker" type="checkbox" name="checking_companys[]" value="11"></td>				
					<td align="left"><a href="/settings/edit_ebay_account/?id=11">cyclistbike</a></td>
										
					<td><a href="/settings/edit_ebay_account/?id=11">QdgFsoVhGN...</a></td>
                    <td>2017-03-28 09:52:21</td> 
					<td width="150px">mandarinbike@gmail.com</td>					
					<td>2015-10-05 15:30:56</td>
					<td>2016-08-04 08:46:33</td>
					<td> Success </td>					
				</tr>
                
				<tr>				
                    <td width="50px" align="left"><input class="checker" type="checkbox" name="checking_companys[]" value="12"></td>				
					<td align="left"><a href="/settings/edit_ebay_account/?id=12">magicbike</a></td>
									
					<td><a href="/settings/edit_ebay_account/?id=12">NlhVlMEhRg...</a></td>
                    <td>2017-05-24 08:52:11</td> 
					<td width="150px">mandarinbike@gmail.com</td>					
					<td>2015-12-01 16:44:32</td>
					<td>2016-08-04 08:46:34</td>
					<td> Success </td>					
				</tr>
                
				<tr>				
                    <td width="50px" align="left"><input class="checker" type="checkbox" name="checking_companys[]" value="13"></td>				
					<td align="left"><a href="/settings/edit_ebay_account/?id=13">igrocery</a></td>
									
					<td><a href="/settings/edit_ebay_account/?id=13">xMRu/Qtc+B...</a></td>
                    <td>2017-05-31 08:38:44</td> 
					<td width="150px">mandarinbike@gmail.com</td>					
					<td>2015-12-08 16:39:00</td>
					<td>2016-08-04 08:46:36</td>
					<td> Success </td>					
				</tr>
                
				<tr>				
                    <td width="50px" align="left"><input class="checker" type="checkbox" name="checking_companys[]" value="14"></td>				
					<td align="left"><a href="/settings/edit_ebay_account/?id=14">comenwin0903</a></td>
					
					<td><a href="/settings/edit_ebay_account/?id=14">0WIdC2uYjk...</a></td>
                    <td>2017-05-31 08:38:44</td>
					<td width="150px">william19780901@gmail.com</td>					
					<td>2016-04-28 16:37:35</td>
					<td>2016-08-04 08:46:37</td>
					<td> Failed </td>					
				</tr> --> 
            
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