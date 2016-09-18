<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*,tw.iii.qr.stock.CEbay" %>
<jsp:useBean id="getebay" class="tw.iii.qr.stock.CEbayFactory"  scope="page"/>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addAccount</title>
</head>
<body>
	<%@include file="../href/navbar.jsp"%>
	
	<div class="nav">
		<div class="container">
			<div class="navbar-left" style="background-color: #AC7ED3;">
				<ul class="nav nav-tabs">
					<li><a href="eBayAccount.jsp" style="color: #000000">eBay帳號管理</a></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="background-color: #1CAF9A;">
				<ul class="nav nav-tabs">
					<li><a href="eBayAccount.jsp" style="color: #000000">帳號列表</a></li>
					<!--               <li class="" style="background-color:#1CAF9A"><a href="purchasePage.jsp" style="color:#FFFFFF">進貨</a></li> -->
					<li><a href="addAccount.jsp" style="color: #000000">新增eBay帳號</a></li>
				</ul>
			</div>
		</div>

	</div>

	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="../QRMain/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a href="eBayAccount.jsp">eBay帳號管理</a></li>
			<li><a href="eBayAccount.jsp">新增eBay帳號</a></li>
		</ol>


	<h2>編輯/停用eBay帳號</h2>
	<form action="../EbayAccountDo" method="post">
		

		<h2 class="">ebay 帳號</h2>
		  <div class="row">
        <label for="inputPassword" class="col-md-2 control-label text-left">編輯模式</label>
          <div class="col-md-4">
            <label class="radio-inline">
              <input type="radio" name="optionsRadios" id="optionsRadios1">開啟</label>
      	    <label class="radio-inline">
      	      <input type="radio" name="optionsRadios" id="optionsRadios2">關閉</label>
      	           	       
          </div>
<%
request.setCharacterEncoding("UTF-8");
String ebayId1 = request.getParameter("p");

//out.print(ebayId);  
System.out.println(ebayId1);   
System.out.println(request.getParameter("p"));
if(!"".equals(ebayId1) || ebayId1 != null){
Connection conn = new DataBaseConn().getConn();

CEbay eBayAccount = getebay.searchDetail(ebayId1);
session.setAttribute("eBayAccount", eBayAccount);
System.out.println(eBayAccount.getebayId());
}
%>
          
      </div>
      </div>
		<div class="">
			<table border="1" align="center">

				<tbody>
					<tr>
						<th class=""><span> * </span>ebay ID</th>
						<td class=""><input id="id_fd-name" type="text" name="ebayId" value="${eBayAccount.getebayId() }"
							class=""  maxlength="50" readonly></td>
						<td class=""></td>
					</tr>

	<!-- 				<tr>
						<th class=""><span > * </span>ebay Token</th>
						<td class=""><textarea id="id_fd-token" rows="5"
								cols="85" name="ebayToken" value="" readonly>${eBayAccount.getebayToken() }</textarea></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class=""><span > * </span>end Token</th>
						<td class=""><textarea id="id_fd-token" rows="5"
								cols="85" name="endToken" value="" readonly>${eBayAccount.getendToken() }</textarea></td>
						<td class=""></td>
					</tr>
    -->
    
    
					<tr>
						<th class=""><span class="red"> * </span>paypal
							帳號 </br>(E-mail)</th>
						<td class=""><input id="id_fd-email" type="text" name="paypalAccount" value="${eBayAccount.getpaypalAccount() }"
							class="" ></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class="">對應公司</th>
						<td class=""><select name="correspondCompany" value =${eBayAccount.getcorrespondCompany() }
							id="id_fd-company_id"> 
								<option value="HUANG PO-WEI">HUANG PO-WEI</option>
								<option value="YU CHIN WU">YU CHIN WU</option>
								<option value="WHIRLWIND SPEED LIMITED">WHIRLWIND SPEED LIMITED</option>
						</select></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class=""><span> * </span>startTime</th>
						<td class=""><input id="id_fd-name" type="text" name="startTime" value="${eBayAccount.getstartTime() }"
							class=""  maxlength="50"></td>
						<td class=""></td>
					</tr>

					<tr>
						<th class=""><span> * </span>lastFixTime</th>
						<td class=""><input id="id_fd-name" type="text" name="lastFixTime" value="${eBayAccount.getlastFixTime() }"
							class=""  maxlength="50"></td>
						<td class=""></td>
					</tr>
					
					<tr>
						<th class=""><span> * </span>status</th>
						<td class=""><input id="id_fd-name" type="text" name="status" value="${eBayAccount.getstatus() }"
							class=""  maxlength="50" name="ebayId" value=""></td>
						<td class=""></td>
					</tr>
					
					<tr>
						<th class="">Comment</th>
						<td class=""><textarea id="id_fd-feedback_text" rows="5"  name="comment" value=""
								cols="85" >${eBayAccount.getcomment() }</textarea></td>
						<td class=""></td>
					</tr>
					

				</tbody>
			</table>
		</div>


		<div class="" align="center">
			<button type="submit" name="submit" value="updateEbayAccount">修改送出</button> <td><a href="eBayAccount.jsp"><input type="button" value="取消"></a></td>
		</div>
		<input type="hidden" name="id" value="">
	</form>
		</div>

</body>
</html>