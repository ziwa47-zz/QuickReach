<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="org.json.JSONObject"%>
<%@page import="tw.iii.qr.DataBaseConn"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.LinkedList,java.util.*,javax.servlet.http.HttpServletRequest"%>

<jsp:useBean id="checkno" class="tw.iii.purchase.purchaseFactory" scope="page"/>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>進貨</title>
<script src="../js/jquery-1.12.4.min.js"></script><!-- jqueryAutoComplete 不可以在../js/jquery-1.12.4.min.js 之前 -->


<script type="text/javascript">

var skuName;
var setValueId;
var realPurchaseId;

var dynamicId = 2;


function warehouseChange() {
	
	$.ajax({
		
		type:"GET",
		url:"../AjaxProcessMasterWarehouse",
		data:$("#listForm").serialize(),
		dataType:"json",
		
		success: function(response){
			
			var date = $("#selectDate").val();
		        realPurchaseId = date+response.warehousePurchaseId
				$("#purchaseId").val(realPurchaseId);	
		        
		},
	})
}




function autoComplete(id){
	$("#sku"+id).blur(function() {
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
	    url: "../AjaxProcessAutoComplete",        
	    data: $("#listForm").serialize(), 
        dataType: "json", 

        success : function(response){       	
                   
            	 $("#pName"+setValueId).val(response.pName);
                 $("#spec"+setValueId).val(response.spec);
                 $("#color"+setValueId).val(response.color);
                 
                 $("#warehousePositionOne"+setValueId).val(response.warehousePosition);
                 $("#warehousePositionTwo"+setValueId).val(response.warehousePosition2);   

        },        
	})

}
     

	$(function() {		
				//進場先檢查purchaseId
		warehouseChange()
		//聽說是自動驗證
		$("#listForm").validate({
			onfocusout:false,
			delay:500,
			submitHandler: function (form)
		    {
		        $('#submitButton').attr('disabled', 'disabled');
		        warehouseChange();
		        alert("此進貨單單號:"+realPurchaseId)
		        form.submit();
		       
		    },
		   
			
			})
 				
		//日期選擇器  
		$("input[name=purchaseDate]").datepicker({
			dateFormat : 'yymmdd',
			
			
		});

	});
	

	
	function jqueryAutoCompleteSKU(id) {
		
		
			$("#sku"+id).autocomplete({
				source:"../JQueryAutoCompleteSKUData",
				minLength:3,
				select:function(event,ui){
					autoComplete(id)
				}
					
				
			});
		

		
	}


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
						
						//	+'			<button type="button" class="close" onclick="onclick="removeDynamicItem('+dynamicId+')"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button> '
						

						'<div class="panel panel-default" id="dynamic'+dynamicId+'" style="background-color: #E7D29F">'
							+'<div class="panel-heading">'
							+'	<div class="panel-title row ">'
							+'			<div class="col-md-11 form-group">'
							+'			<a data-toggle="collapse" data-parent="#accordion"'
							+'				href="#collapse'+dynamicId+'">進貨項目'+dynamicId+'</a>'
							+'				<input type="hidden" name="times" value="'+dynamicId+'">'//計算進貨筆數
											
							+'		</div>'
							+'		<div align="right" class="col-md-1 form-group">'
							+'			<button type="button" class="close" onclick="removeDynamicItem('+dynamicId+')"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button> '
							+'		</div>'
							+'	</div>'


							+'	</div>'
							+'			<div id="collapse'+dynamicId+'" class="panel-collapse collapse in">'
							+'				<div class="panel-body">'
							+'					<input type="hidden">'
	    	  
							+'    	<div class="row">'
							
	            	
							+' 	<div class="col-md-4 form-group ">'
							+'        <div class="row">'
							+'           <div class="col-md-4"><h5><label for="focusedInput " >SKU：</label></h5></div>'
							+'            <div class="col-md-8"><input class="form-control required" id="sku'+dynamicId+'" name="sku'+dynamicId+'" type="text" onfocus="jqueryAutoCompleteSKU('+dynamicId+')" value="" ></div>'
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
                  +'                 <div class="col-md-8"><input class="form-control digits required" id="qtyOne'+dynamicId+'" name="qty'+dynamicId+'" title="數量必須大於0" type="text"></div>'
                  +'                </div>'
                  +'               </div>'
                  
                 +' <div class="col-md-4 form-group ">'
                  +'                <div class="row">'
            +'                  <div class="col-md-4"><h5><label for="focusedInput " >數量：</label></h5></div>'
            +'                 <div class="col-md-8"><input class="form-control number required" id="qtyTwo'+dynamicId+'" name="qtyTwo'+dynamicId+'" title="價格必須大於0" type="text"></div>'
            +'                </div>'
            +'               </div>'
	                
	              
	                        +'             </div>'
	                        

	                        
	            
	                        +'           <div class="row">'
	                        +'         	<div class="col-md-8 form-group ">'
	                        +'             <div class="row">'
	                        +'              <div class="col-md-2"><h5><label for="focusedInput " >櫃位：</label></h5></div>'
	                        +'              <div class="col-md-8"><input class="form-control" style="width:89px;"id="warehousePositionOne'+dynamicId+'" name="warehousePositionOne'+dynamicId+'" type="text"> - <input class="form-control" style="width:89px;" id="warehousePositionTwo'+dynamicId+'" name="warehousePositionTwo'+dynamicId+'" type="text"></div>'
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
<style type="text/css">
#listForm label.error {
font-size: 0.8em;
color: #F00;
font-weight: bold;
display: block;


}
</style>

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
					<li><a href="SearchStockPage.jsp" style="color: #000">查詢庫存</a></li>
					<li class="" style="background-color: #1CAF9A"><a href="PurchasePage.jsp" style="color: #000">進貨</a></li>
					<li ><a
						href="PurchaseRecordPage.jsp" style="color: #000">進/出貨紀錄</a></li>
						<li ><a
						href="PurchaseRecordPage.jsp" style="color: #fff">轉倉</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchStockPage.jsp">庫存/商品管理</a></li>
			<li><a href="StockTransferPage.jsp">轉倉</a></li>
		</ol>
	</div>

	<div class="container" style="background: #9DDCD1; border-radius: 20px">
		
		<form id="listForm" name="listForm" method="post"
			action="../InsertPurchaseServlet.do"
			style="font-size: 100%; vertical-align: baseline; padding: 15px;"
			class="form-inline container">
			<%
			
			Connection conn = new DataBaseConn().getConn();
			
			LinkedList<LinkedList<String>> warehouseList = checkno.warehouseSelectOption(conn);
			LinkedList<LinkedList<String>> companyList = checkno.companySelectOption(conn);
			request.setAttribute("warehouseList", warehouseList);
			request.setAttribute("companyList", companyList);
			String srno;
			String srnoDate = checkno.purchaseGetDay();
			request.setAttribute("srnoDate", srnoDate);
			conn.close();
			
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
								<input class="form-control" type="text" id="selectDate" name="purchaseDate" value="${srnoDate}" onchange="warehouseChange()" readonly>
							</div>
						</div>
					</div>
<!-- 					style="display:none" -->
					<div  class="col-md-4 form-group " style="display:none">
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
									<label for="focusedInput ">供應商：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<select class="form-control" name="companyId" id="companyId"  >
                    
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
									<label for="focusedInput ">員工姓名：</label>
								</h5>
							</div>
							<div class="col-md-8">
								<input class="form-control required "  name="staffId" type="text" value="${staffName}">
							</div>
						</div>
					</div>
					
					  <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >原倉別：</label></h5></div>
                    <div class="col-md-8">
                      <select class="form-control" name="warehouse" id="warehouse"  onchange="warehouseChange()">
                    
                    <c:forEach var = "i" begin="0" step="1" items="${warehouseList}">
                    
                      <option value="${i.get(0)}">${i.get(1)}</option>
                     
                    </c:forEach>     
                                 
                      </select></div>
                    </div>
                  </div>
					
					  <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >新倉別：</label></h5></div>
                    <div class="col-md-8">
                      <select class="form-control" name="warehouse" id="warehouse"  onchange="warehouseChange()">
                    
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
					onclick="dynamicAdd()" class="btn-lg btn-success">新增欄位(F2)</button>
			</div>
			<fieldset class="container-fluid" style="padding: 0 30 0 0;">

				<legend></legend>
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
									<button type="button" class="close" onclick="cleanFirstItem()"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
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
                    <div class="col-md-8"><input class="form-control  required" id="sku1" name="sku1" type="text" onfocus="jqueryAutoCompleteSKU(1)"   value=""></div>
                  </div>                                                                                    <!--  onchange="autoComplete(1)" -->                                     
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
                    <div class="col-md-8"><input class="form-control digits required" title="數量必須大於0" id="qtyOne1" name="qtyOne1" type="text" placeholder="高雄"></div>
                  </div>
                </div>
                
                
                <div class="col-md-4 form-group ">
                  <div class="row">
                    <div class="col-md-4"><h5><label for="focusedInput " >數量：</label></h5></div>
                    <div class="col-md-8"><input class="form-control number required" title="數量必須大於0" id="qtyTwo1" name="qtyTwo1" type="text" placeholder="美國"></div>
                  </div>
                </div>
              
                </div>
            
            <div class="row">
            	<div class="col-md-8 form-group ">
                  <div class="row">
                    <div class="col-md-2"><h5><label for="focusedInput " >櫃位：</label></h5></div>
                    <div class="col-md-8"><input class="form-control" style="width:89px;"id="warehousePositionOne1" name="warehousePositionOne1" type="text"> - <input class="form-control" style="width:89px;" id="warehousePositionTwo1" name="warehousePositionTwo1" type="text"></div>
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
						<button type="submit" id="submitButton" name="submitButton" class="btn-lg btn-success" >送出</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>

	<%@ include file="../href/footer.jsp"%>

</body>
</html>