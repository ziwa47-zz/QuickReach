<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<!-- Bootstrap -->
	<link href="./css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./css/smoothness/jquery-ui.css">

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.js"></script>

    
    <script src="./js/jquery-1.12.4.min.js"></script>
    <script src="./js/jquery-ui.min.js"></script>
    <script src="./js/jquery.ui.datepicker-zh-TW.js"></script>
</head>
<% session.removeAttribute("account"); %>
<body>
<%@include file ="/href/navbar.jsp" %>
	<form action="LoginController.do" method="post" >
	<div align="center">
		<p align="center">
		<input type="text" name="account" placeholder="請輸入帳號" /><br /> 
		<input type="password" name="password" placeholder="請輸入密碼" /><br/><br/>
		<input type="checkbox" name="autoLogin" value=auto />記住我
		<input type="submit" value="登入" /> <br />
		<input type="hidden" name="EmpStatus" />
		</p>
		
	</div>

	</form>
</body>
</html>
<%@include file ="/href/footer.jsp" %>