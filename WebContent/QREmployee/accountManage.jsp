<%@page import="tw.iii.qr.DataBaseConn"%>
<%@page import="tw.iii.qr.QRAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.sql.Connection,java.sql.ResultSet,java.util.*" %>
<jsp:useBean id="getaccount" class="tw.iii.qr.QRAccountFactory"  scope="page"/>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account</title>


    
<script type="text/javascript">

</script>
</head>


<body>
<%@include file="/href/navbar.jsp"%>
<div class="nav">
  	<div class="container">
    	<div class="navbar-left" style="background-color:#BCF1E5;" >
        	<ul class="nav nav-tabs">
              <li><a href="./eBayAccount.jsp" style="color:#000000">員工帳號管理</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li ><a href="./accountManage.jsp" style="color:#000000">帳號列表</a></li>
              <li><a href="./Account.jsp" style="color:#000000">新增員工帳號</a></li>
            </ul>
        </div>
    </div>
  
  </div>
  
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
          <li><a href="../QRMain/HomePage.jsp" >首頁</a></li>
          <li class="active" style="display:"><a href="./accountManage.jsp">員工帳號管理</a></li>
          <li><a href="./accountManage.jsp">帳號列表</a></li>
      </ol>
	<form id="listForm" name="listForm" method="post" action="" >

<h2>員工帳號管理</h2><br/>
<hr>

<%
Connection conn1 = new DataBaseConn().getConn();

LinkedList<QRAccount> account1 = getaccount.searchQRemployee(request,conn1);

session.setAttribute("account1", account1);

conn1.close();

%>
		
		<div class="form_data">
			<table class="Table"  >
				<thead>
					<tr>										
						<th class="">編輯</th>
						<th class="" width="190">員工帳號</th>
						<th class="" width="150">密碼</th>
						<th class="">姓氏</th>
						<th class="">名字</th>
						<th class="">E-Mail</th>
						<th class="">英文名字</th>
						<th class="">權限等級</th>
						<th class="">狀態</th>											
					</tr>
				</thead>
				         			
				<tbody>
				
					<c:forEach var="i" varStatus="check" items="${account1}" begin="0" step="1">
  									
				<tr>	
					                    <td> <a href ="editAccount.jsp?p=${i.getAccount()}"> <img src="../img/compose-4.png"></a></td>
                 		
					<td width="80px" align="left">${i.getAccount()}</td> <!-- account -->					
					<td width="100px" align="left" nowrap="">${i.getPassword()}</td><!-- password -->
                    <td width="100px">${i.getLastName()}</td> 
					<td width="100px" align="left" nowrap="">${i.getFirstName()}</td><!-- firstname -->			
					<td width="100px">${i.getEmail()}</td><!-- email-->
					<td width="100px">${i.getEnName()}</td><!-- enname-->
					<td width="100px">${i.getCompetenceLV()}</td><!-- competenceLV -->
					<td width="100px">${i.getStatus()}</td><!-- status -->			
								    
		       	                     
	
				</tr>
                            	               	               	
                	</c:forEach>			
				</tbody>
					  
  			
			</table>

		</div>

	</form>
	</div>
	<hr/>
<%@include file="/href/footer.jsp" %>
</body>
</html>