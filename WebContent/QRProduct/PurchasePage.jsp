<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
 <%@page import="org.json.JSONObject"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="checkno" class="tw.iii.purchase.purchaseFactory" scope="page"/>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>進貨</title>

<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="../css/smoothness/jquery-ui.css">
<script src="../js/jquery-1.12.4.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/jquery.ui.datepicker-zh-TW.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  
  
<script type="text/javascript">

var skuName;
var setValueId;
var realPurchaseId;

function alertPurchaseId(){
	warehouseChange();
	//alert(realPurchaseId);
	
}

function warehouseChange() {
	
	$.ajax({
		
		type:"GET",
		url:"../ProcessMasterWarehouse",
		data:$("#listForm").serialize(),
		dataType:"json",
		
		success: function(response){
			
			var date = $("#selectDate").val();
		        realPurchaseId = date+response.warehousePurchaseId
				$("#purchaseId").val(realPurchaseId);	
		        alert(realPurchaseId)
		},
	})
}




function autoComplete(id){
	$("#sku"+id).blur(function (){
		skuName = $(this).attr("name");
		var skuNum = $(this).val();
	     setValueId = skuName.substring(3);
		$("#autoCompleteNumber").val(skuNum);
		test();
	});
};



function test() {
	
	$.ajax({
		
		type:"GET",                  
	    url: "../AutoCompleteServlet",        
	    data: $("#listForm").serialize(), 
        dataType: "json", 

        success : function(response){       	
                   
            	 $("#pName"+setValueId).val(response.pName);
                 $("#spec"+setValueId).val(response.spec);
                 $("#color"+setValueId).val(response.color);
                 $("#warehousePosition"+setValueId).val(response.warehousePosition);        

        },        
	})

}
     

	$(function() {		
		
		//日期選擇器  
		$("input[name=date]").datepicker({
			dateFormat : 'yymmdd',
			showOn : "both",
			//onSelect:warehouseChange
		});

	});

	var dynamicId = 2;
	//dynamic remove
	function cleanFirstItem() {
		$("#dynamic1 input").val(""), $("#firstItemClean").val("清空");

	}
	
	//FOR purchaseId 
	//retire囉
	function makePurchaseId() {  
		
		var wareHouse =  $("#purchaseId").val();
		var status = wareHouse.substring(8,10);
		var behindThree = wareHouse.slice(-3); 
		var selectWareHouse = $("#warehouse").val();
		var date = $("#selectDate").val();
		var realPurchaseId =date+status+selectWareHouse+behindThree;
			$("#purchaseId").val(realPurchaseId);		
	}
	//END
	function removeDynamicItem(dynamicId) {
		$("#dynamic"+dynamicId).remove();
	};

	//dynamicAdd

	function dynamicAdd() {

		$("#formSubmit")  //'+dynamicId+'
				.before(

						'<div class="panel panel-default" id="dynamic'+dynamicId+'" style="background-color: #E7D29F">'
							+'<div class="panel-heading">'
							+'	<div class="panel-title row ">'
							+'			<div class="col-md-11 form-group">'
							+'			<a data-toggle="collapse" data-parent="#accordion"'
							+'				href="#collapse1">進貨項目'+dynamicId+'</a>'
							+'				<input type="hidden" name="times" value="'+dynamicId+'">'//計算進貨筆數
											
							+'		</div>'
							+'		<div align="right" class="col-md-1 form-group">'
							+'			<input type="button" value="刪除" onclick="removeDynamicItem('+dynamicId+')" '
							+'				id="firstItemClean">'
							+'		</div>'
							+'	</div>'


							+'	</div>'
							+'			<div id="collapse1" class="panel-collapse collapse in">'
							+'				<div class="panel-body">'
							+'					<input type="hidden">'
	    	  
							+'    	<div class="row">'
							
	            	
							+' 	<div class="col-md-4 form-group ">'
							+'        <div class="row">'
							+'           <div class="col-md-4"><h5><label for="focusedInput " >SKU：</label></h5></div>'
							+'            <div class="col-md-8"><input class="form-control con" id="sku'+dynamicId+'" name="sku'+dynamicId+'" type="text" onchange="autoComplete('+dynamicId+')" value="" ></div>'
	                    +'          </div>'
	                    +'        </div>'
	            	
	                    +'    	 <div class="col-md-4 form-group ">'
	                    +'        <div class="row">'
	                    +'         <div class="col-md-4"><h5><label for="focusedInput " >品名：</label></h5></div>'
	                    +'        <div class="col-md-8"><input class="form-control" id="pName'+dynamicId+'" name="pName'+dynamicId+'" type="text" value=""></div>'
	                    +'      </div>'
	                    +'     </div>'
	                    +'    	<div class="col-md-4 form-group ">'
						+'         <div class="row">'
						+'          <div class="col-md-4"><h5><label for="focusedInput " >規格：</label></h5></div>'
						+'         <div class="col-md-8"><input class="form-control" type="text" id="spec'+dynamicId+'" name="spec'+dynamicId+'"></div>'
						+'      </div>'
						+'     </div>'
	            	
	                
	                    +' </div>'
	            
	                    +'     <div class="row">'
	            	
	                
	               
	                               
	                    +'           	<div class="col-md-4 form-group ">'
						+'         <div class="row">'
						+'          <div class="col-md-4"><h5><label for="focusedInput " >顏色：</label></h5></div>'
						+'         <div class="col-md-8"><input class="form-control" type="text" id="color'+dynamicId+'" name="color'+dynamicId+'"></div>'
						+'      </div>'
						+'     </div>'
						 +'<div class="col-md-4 form-group ">'
                        +'                <div class="row">'
                  +'                  <div class="col-md-4"><h5><label for="focusedInput " >數量：</label></h5></div>'
                  +'                 <div class="col-md-8"><input class="form-control" name="qty'+dynamicId+'" type="text"></div>'
                  +'                </div>'
                  +'               </div>'
                  
                 +' <div class="col-md-4 form-group ">'
                  +'                <div class="row">'
            +'                  <div class="col-md-4"><h5><label for="focusedInput " >價格：</label></h5></div>'
            +'                 <div class="col-md-8"><input class="form-control" name="price'+dynamicId+'" type="text"></div>'
            +'                </div>'
            +'               </div>'
	                
	              
	                        +'             </div>'
	            
	                        +'           <div class="row">'
	                        +'         	<div class="col-md-4 form-group ">'
	                        +'             <div class="row">'
	                        +'              <div class="col-md-4"><h5><label for="focusedInput " >櫃位：</label></h5></div>'
	                        +'              <div class="col-md-8"><input class="form-control" id="warehousePosition'+dynamicId+'" name="warehousePosition'+dynamicId+'" type="text"></div>'
	                        +'            </div>'
	                        +'                </div>'
	                                    
	                  +'               <div class="col-md-4 form-group ">'
	                  +'               <div class="row">'
	                  +'                  <div class="col-md-4"><h5><label for="focusedInput " >備註：</label></h5></div>'
	                  +'                 <div class="col-md-8"><input class="form-control" id="comment'+dynamicId+'" name="comment'+dynamicId+'" type="text"></div>'
	                  +'                </div>'
	                  +'            </div>'
	                  +'           </div>'
	           
	                  +'         <br/>'
	                  +'						</div>'
								
	                  +'					</div>'
	                  +'				</div>'

				);
		$("#count").val(dynamicId);
		
		dynamicId++;

	}

	$("#buttonAddItem").click(function() {

		dynamicAdd();
	});

	//f2 add
	function keyFunction() {
		if (event.keyCode == 113) {

			dynamicAdd();

		}
	};

	document.onkeydown = keyFunction;
	//f2 end
</script>


</head>
<body>
	<%@ include file="../href/navbar.jsp"%>

	<div class="nav">
		<div class="container">
			<div class="navbar-left" style="background-color: #BCF1E5;">
				<ul class="nav nav-tabs">
					<li class="" style="background-color: #1CAF9A"><a
						href="SearchStockPage.jsp" style="color: #FFFFFF">庫存</a></li>
					<li><a href="SearchProductPage.jsp" style="color: #000000">商品</a></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="background-color: #1CAF9A;">
				<ul class="nav nav-tabs">
					<li><a href="SearchStockPage.jsp" style="color: #000000">查詢庫存</a></li>
					<li class="" style="background-color: #1CAF9A"><a
						href="PurchasePage.jsp" style="color: #FFFFFF">進貨</a></li>
					<li><a href="searchPurchase.jsp" style="color: #000000">進貨紀錄</a></li>
					<li><a href="searchOutRecordPage.jsp" style="color: #000000">出貨紀錄</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="../QRMain/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchStockPage.jsp">庫存/商品管理</a></li>
			<li><a href="PurchasePage.jsp">進貨</a></li>
		</ol>
	</div>

	<div class="container" style="background: #9DDCD1; border-radius: 20px">
		<form id="listForm" name="listForm" method="post"
			action="../TestJdbcMvcServlet"
			style="font-size: 100%; vertical-align: baseline; padding: 15px;"
			class="form-inline container">
			<%
			
			LinkedList<LinkedList<String>> warehouseList = checkno.warehouseSelectOption();
			LinkedList<LinkedList<String>> companyList = checkno.companySelectOption();
			request.setAttribute("warehouseList", warehouseList);
			request.setAttribute("companyList", companyList);
			String srno;
			String srnoDate = checkno.getDay();
			srno = checkno.processStorageRecord("01");//進貨單代碼 01
			request.setAttribute("purchaseId", srno);
			request.setAttribute("srnoDate", srnoDate);
			
			%>
			<fieldset class="container-fluid" style="padding: 0 30 0 0;">
				<legend>進貨頁面</legend>
				<input type="hidden">

				<div class="row">
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">進貨日期：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" type="text" id="selectDate" name="date" value="${srnoDate}" readonly>
							</div>
						</div>
					</div>
					<div style="display:none" class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">單號：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="purchaseId" id="purchaseId" type="text" value="${purchaseId}" readonly>
							</div>
						</div>
					</div>
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">廠商：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="companyId" id="companyId" >
                    
                    <c:forEach var = "i" begin="0" step="1" items="${companyList}">
                    
                      <option value="${i.get(0)}">${i.get(1)}</option>
                     
                    </c:forEach>     
                                 
                      </select>
							</div>
						</div>
					</div>
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">員工編號：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="staffId" type="text">
							</div>
						</div>
					</div>
					
					  <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >倉別：</label></h5></div>
                    <div class="col-md-8">
                      <select class="form-control" name="warehouse" id="warehouse" >
                    
                    <c:forEach var = "i" begin="0" step="1" items="${warehouseList}">
                    
                      <option value="${i.get(0)}">${i.get(1)}</option>
                     
                    </c:forEach>     
                                 
                      </select></div>
                    </div>
                  </div>
					
					<div class="col-md-4 form-group ">
						<div class="row">
							<div class="col-md-4">
								<h5>
									<label for="focusedInput ">備註：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control" name="purchaseMasterComment" type="text">
							</div>
						</div>
					</div>
				</div>


			</fieldset>

			<br />
			<div class="row text-center">
				<button id="buttonAddItem" type="button" name=""
					onclick="dynamicAdd()" class="btn-lg btn-success">新增欄位</button>
			</div>
			<fieldset class="container-fluid" style="padding: 0 30 0 0;">

				<legend>按F2多重新增</legend>
				<input type="hidden">

				<div class="panel-group" id="accordion">
					<div class="panel panel-default" id="dynamic1"
						style="background-color: #E7D29F">
						<div class="panel-heading">
							<div class="panel-title row ">
								<div class="col-md-11 form-group">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapse1">進貨項目1</a>
										<input type="hidden" name="times" value="1">
										<input type="hidden" id="count" name="count" value="1">
										<input type="hidden" id = "autoCompleteNumber" name="autoCompleteNumber" value= "1">
								</div>
								<div align="right" class="col-md-1 form-group">
									<input type="button" value="清空" onclick="cleanFirstItem()"
										id="firstItemClean">
								</div>
							</div>


						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body">
								<input type="hidden">
    	  
        	<div class="row">
            	
            	
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >SKU：</label></h5></div>
                    <div class="col-md-8"><input class="form-control con" id="sku1" name="sku1" type="text" onchange="autoComplete(1)" value=""></div>
                  </div>
                </div>
            	
            	 <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >品名：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" id="pName1" name="pName1" type="text" value=""></div>
                  </div>
                </div>
                
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >規格：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" id="spec1" type="text" name="spec1"></div>
                  </div>
                </div>
                
            </div>
            
            <div class="row">
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >顏色：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" id="color1" type="text" name="color1"></div>
                  </div>
                </div>
                
                  <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >數量：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="qty1" type="text"></div>
                  </div>
                </div>
                
                
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >價格：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="price1" type="text"></div>
                  </div>
                </div>
              
                </div>
            
            <div class="row">
            	<div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >櫃位：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" id="warehousePosition1" name="warehousePosition1" type="text"></div>
                  </div>
                </div>
                
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >備註：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" name="comment1" type="text"></div>
                  </div>
                </div>
              
                
            </div>
           
            <br/>
							</div>

						</div>
					</div>
				

					<br />

					<div class="row text-center" id="formSubmit">
						<button type="submit" name="" class="btn-lg btn-success" onclick="alertPurchaseId()">送出</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>

	<%@ include file="../href/footer.jsp"%>

</body>
</html>