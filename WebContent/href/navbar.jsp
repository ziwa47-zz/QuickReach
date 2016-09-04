<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nav</title>
<!-- local file -->
<!-- <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"> -->

<!-- <script src="../js/jquery-1.12.4.min.js"></script> -->
<!-- <script src="../js/bootstrap.min.js"></script> -->
<!-- <script src="../js/jquery-ui.min.js"></script> -->
<!-- <script src="../js/jquery.ui.datepicker-zh-TW.js"></script> -->

<link rel="stylesheet" href="/css/custom.css" rel="stylesheet">
<!-- downloaded -->
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
  

    <script type="text/javascript">  
    $(function () {
    	
    	  $("input[name=date]").datepicker({
  			dateFormat : 'yymmdd',
  			showOn : "both",
    	
        //日期選擇器
        $("input[name=date1]").datepicker();
        $("input[name=date2]").datepicker();
       });
    
    $(function () {
    	//feildset prop enable
        $("#optionsRadios1").click(function () {
           $("#myfields").prop("disabled", false);
      });
        $("#optionsRadios2").click(function () {
        $("#myfields").prop("disabled", true);
      });
    })
    
	</script>
</head>
<body>
<nav class="navbar" style="background-color: #000000" >
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/HomePage.jsp">QuickReach</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
       
        <li><a href="/QRProduct/SearchStockPage.jsp">商品/庫存 </a></li>
        <li><a href="/QROrders/SearchOrder.jsp">訂單資訊</a></li>
        <li><a href="/QREBayAccount/eBayAccount.jsp">Ebay帳號管理</a></li>
        <li><a href="/QREmployee/Account.jsp">員工管理</a></li>
        <li><a href="/QRAccess/Competence.jsp">權限管理</a></li>
      </ul>
      
      <ul class="nav navbar-right" >
        <li><a href="/Login.jsp">${EmpName}${EmpStatus} <span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
</body>
</html>