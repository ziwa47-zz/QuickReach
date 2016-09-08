<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新增帳號</title>
<!-- Bootstrap -->
	<link href="/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/smoothness/jquery-ui.css">

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.js"></script>

    
    <script src="/js/jquery-1.12.4.min.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/jquery.ui.datepicker-zh-TW.js"></script>
    
    <script type="text/javascript">        
        $(function () {
            //日期選擇器
            $("input[name=date]").datepicker({ dateFormat: 'yy/mm/dd', showOn: "both" });
            
           });
	</script>   
    
    <style type="text/css">
            .label-tag {
                background-color:#BFE9B2;
            }
            .pressed {
                border-width: 5px;
                border-color: black;
                border-style: solid;
            }
        </style>
    
</head>

<body><%@ include file="/href/navbar.jsp" %>
  <div class="nav">
  	<div class="container">
    	<div class="navbar-left" style="background-color:#BCF1E5;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#1CAF9A"><a href="Account.jsp" style="color:#FFFFFF">帳號管理</a></li>
            </ul>
        </div>
    </div>
    <div class="container">
   	  <div class="nav" style="background-color:#1CAF9A;" >
        	<ul class="nav nav-tabs">
              <li class="" style="background-color:#1CAF9A"><a href="Account.jsp" style="color:#FFFFFF">新增帳號</a></li>
              <li><a href="accountManage.jsp" style="color:#000000">修改帳號資料</a></li>
            </ul>
        </div>
    </div>
  
  </div>
  
  <div class="container container-fluid breadcrumbBox">
    <ol class="breadcrumb" >
          <li><a href="../QRMain/HomePage.jsp" >首頁</a></li>
          <li class="active" style="display:"><a href="accountManage.jsp">員工管理</a></li>
          <li><a href="Account.jsp">新增帳號</a></li>
      </ol>
      <h3>新增帳號</h3>
      <hr/>
  </div>
  
  <div class="container" >
  	<form name="searchform" method="post" action="QRAccountServlet.do" style="font-size: 100%; vertical-align: baseline;" class=" form-group container">
      
          <input type="hidden">
              <h3 class="" style="background: #BCF1E5; border-left: 6px solid #1CAF9A;" >輸入帳號資料</h3>
              <div class="container-fluid form-horizontal">
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>登入帳號</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" name="account" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>密碼</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" name="password" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>姓氏</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" name="lastName" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>名字</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" name="firstName" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>E-mail</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" name="E-mail" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>英文名字</h4></div>
                      <div class="col-md-5 well-sm"><input class="form-control" name="enName" type="text" ></div>
                  </div>
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>權限等級</h4></div>
                      <div class="col-md-5 well-sm">
						<select class="form-control" name="competenceLV">
						    <option value="A">A</option>
						    <option value="B">B</option>
						    <option value="C">C</option>
						</select>
					  </div>
                  </div>
                  
    <!--                <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>簽名圖檔</h4></div>
                      <div class="col-md-5 well-sm"><input class="checkbox-inline" name="signatureImage" type="file" ></div>
                  </div>
     -->              
                  <div class="row">
                      <div class="col-md-3 text-right well-sm label-tag"  ><h4>帳號狀態</h4></div>
                      <div class="col-md-5 well-sm">
                      	
                      	<label class="checkbox-inline"><input type="radio" id="id_fd-is_active_0" value="1" name="status" />有效</label>
					 	<label class="checkbox-inline"><input type="radio" id="id_fd-is_active_1" value="0" name="status" checked/> 停用</label>
					   
					  </div>
                  </div>
                  <div class="row">
                     <center><button class="checkbox-inline" name="submit" type="submit" value="addAccount" >新增</button></center>
                  </div>
              </div>
              
</form>
</div>

  
  
  <%@ include file="/href/footer.jsp" %>
</body>
</html>
