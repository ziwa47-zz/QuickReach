<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>

<% 
if(session.getAttribute("account")!=null){

session.invalidate();

}
%>
</head>

<body>
<%@include file ="/href/navbar.jsp" %>
	<form action="LoginServlet.do" method="post" >
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