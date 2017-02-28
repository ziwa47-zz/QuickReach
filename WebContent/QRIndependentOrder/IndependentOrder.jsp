<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.*,javax.servlet.http.HttpServletRequest"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>獨立訂單</title>
<script	src="../js/jquery-1.12.4.min.js"></script><!-- jqueryAutoComplete 不可以在../js/jquery-1.12.4.min.js 之前 -->
<!--  <link rel="stylesheet" type="text/css" media=""  href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/smoothness/jquery-ui.css"    /> -->
 

<link rel="stylesheet" href="../css/smoothness/jquery-ui.min.css" rel="stylesheet">

<!-- <link rel="stylesheet" href="../css/smoothness/jquery-ui.css" rel="stylesheet"> -->

<script type="text/javascript">

var skuName;
var setValueId;
var independentId;

var dynamicId = 2;

//客戶下拉選單
//GuestId欄位輸入3碼後，搜尋相符合的客戶
function jqueryAutoCompleteGuest() {
	
	$("#guestId").autocomplete({
		source:"/ajax/getGuestList",
		minLength:2,
		
	});

}

//SKU欄位輸入3碼後，搜尋相符合的商品
function jqueryAutoCompleteSKU(id) {
	
	$("#sku"+id).autocomplete({
		source:"../JQueryAutoCompleteSKUData",
		minLength:3,
		//選擇SKU後，搜尋商品並自動填入該商品明細
		select:function(event,ui){
			autoComplete(id);
			
		}
	});

}

//選定SKU碼後搜尋符合的商品，並自動填入該商品明細
function autoComplete(id){
	$("#sku"+id).blur(function() {
		skuName = $(this).attr("name");
		var skuNum = $(this).val();
	     setValueId = skuName.substring(3);
		$("#autoCompleteNumber").val(skuNum);
		autoSetProductDetail();
		getWarehousePosition();
			});
	
};
//設定商品明細
function autoSetProductDetail() {
	
	$.ajax({
		
		type:"GET",                  
	    url: "../AjaxProcessAutoComplete",        
	    data: $("#listForm").serialize(), 
        dataType: "json", 

        success : function(response){       	
            	 $("#pName"+setValueId).val(response.pName);
                 $("#spec"+setValueId).val(response.spec);
                 $("#color"+setValueId).val(response.color);
                 $("#owner"+setValueId).val(response.owner);

        }        
	})

}



//取得公司列表
function getCompanyList() {
	
	$.ajax({
		
		type:"GET",                  
	    url: "/ajax/getCompanyList",        
	    data: $("#listForm").serialize(), 
        dataType: "json", 

        success : function(response){
        	
        		
        		 $.each(response.data, function(key, value){
    				 
    				 $("#company").append($("<option></option>").attr("value", key).text(value));
    				 
    			 })
        }        
	})
}

//取得該商品的庫存倉別及儲位
function getWarehousePosition() {
	
	$.ajax({
		
		type:"GET",                  
	    url: "/ajax/getStorageWarehouse",        
	    data: $("#listForm").serialize(), 
        dataType: "json", 

        success : function(response){       	
            	
        	//插入第一筆商品資料的倉庫select option
        	if (dynamicId == 2 ) {
        		 $.each(response.data, function(i, item){
    				 $("#warehouse1").append($("<option></option>").attr("value", item.warehouse+','+item.warehousePosition).text(item.warehouse+','+item.warehousePosition));
    				 
    			 })
        		
    		//其他  插入動態生成的商品資料 的倉庫select option
        	} else {
        		$.each(response.data, function(i, item){
    				 
   				 $("#warehouse"+(dynamicId-1)).append($("<option></option>").attr("value", item.warehouse+','+item.warehousePosition).text(item.warehouse+','+item.warehousePosition));
   				 
   			 })
        	}
            	
            			 
        }        
	})
}

//取得訂單號碼
function getIorderMasterId() {
	
	$.ajax({
		
		type:"GET",                  
	    url: "/ajax/getIordersMasterCount",        
	    data: $("#listForm").serialize(), 
        dataType: "json", 

        success : function(response){
        	
    				 $("#iorderMasterId").val(response.data);
        }        
	})
}


//查詢客戶資料
function getGuestData() {
	$.ajax({
		
		type:"GET",                  
	    url: "/ajax/getGetGuest",        
	    data: $("#listForm").serialize(), 
        dataType: "json", 

        success : function(response){
        	
        	if (response.guestId != null){
        		 $("#id").val(response.guestId);
				 $("#guestId").val(response.guestGuestId);
				 $("#name").val(response.guestName);
				 $("#birthday").val(response.guestBirthday);
				 $("#gender").val(response.guestGender);
				 $("#company").val(response.guestCompany);
				 $("#platformAccount").val(response.guestPlatformAccount);
				 $("#email").val(response.guestEmail);
				 $("#tel").val(response.guestTel);
				 $("#phone").val(response.guestPhone);
				 $("#country").val(response.guestCountry);
				 $("#postcode").val(response.guestPostcode);
				 $("#comment").val(response.guestComment);
				 $("#address").val(response.guestAddress);
        	}
        	
    				 
        }        
	})
}

     
$(function() {	
	//取得公司列表
	getCompanyList();

	//取得訂單號碼
	getIorderMasterId();
	
	$("#orderDate").datepicker({
		dateFormat : 'yy-mm-dd'
		
	}).datepicker("setDate", new Date());

	
//自動驗證

	$("#listForm").validate({
	debug:false,
	onfocusout:false,
	delay:500,
	submitHandler: function (form)
	{
	
	 	if(isRepeat()){
	    		alert("請確認SKU是否重複");
	    	
	    	} else {
	    		$('#submitButton').attr('disabled', 'disabled');
	        	alert("此訂單單單號:"+$("#iorderMasterId").val())
	        	form.submit();
	    	}

	 	function isRepeat(){
	       	 	var arr = new Array;
	        	for(var i =1;i <= dynamicId; i++){
	        	
	        	 	arr[0] = $("#sku1").val();
	        	 if($("#sku"+(i+1)).val() != null){
	        				arr[i] = $("#sku"+(i+1)).val();
	        	 	}
	        	}
	    	
	    	 	var sortArr = arr.sort();
	    	    	for(var i=0;i<sortArr.length;i++){
	    	    	
	    	    		if (sortArr[i]==sortArr[i+1]){
	    	    		alert("sku重複："+sortArr[i]);
	    	    		return true
	    	    		}
	    	    		}
	    	}
   
	}

	})
		
	});

	//dynamic remove
	function cleanFirstItem() {
		$("#dynamic1 input").val(""), $("#firstItemClean").val("清空");

	}
	
	function removeDynamicItem(dynamicId) {
		$("#dynamic"+dynamicId).remove();
	};

	//dynamicAdd

	function dynamicAdd() {
		
		
		$("#formSubmit")  //'+dynamicId+'
				.before(
						

						'<div class="panel panel-default" id="dynamic'+dynamicId+'" style="background-color: #E7D29F">'
						+'		<div class="panel-heading">'
						+'			<div class="panel-title row ">'
						+'				<div class="col-md-11 form-group">'
						+'					<a data-toggle="collapse" data-parent="#accordion"	href="#collapse'+dynamicId+'">訂單項目'+dynamicId+'</a>'
						+'					<input type="hidden" name="times" value="'+dynamicId+'">'//計算訂單筆數
						+'				</div>'
						+'				<div align="right" class="col-md-1 form-group">'
						+'					<button type="button" class="close" onclick="removeDynamicItem('+dynamicId+')">'
						+'						<span aria-hidden="true">&times;</span>'
						+'						<span class="sr-only"></span>'
						+'					</button>'
						+'				</div>'
						+'			</div>'
						+'		</div>'
							
						+'		<div id="collapse'+dynamicId+'" class="panel-collapse collapse in">'
						+'			<div class="panel-body">'
						+'				<input type="hidden">'
	    	  
						+'    				<div class="row">'
						+' 						<div class="col-md-4 form-group ">'
						+'        					<div class="row">'
						+'           					<div class="col-md-4">'
						+'									<h5>'
						+'										<label for="focusedInput " >SKU：</label>'
						+'									</h5>'
						+'								</div>'
						+'            					<div class="col-md-8"><input class="form-control required" id="sku'+dynamicId+'" name="sku'+dynamicId+'" type="text" onfocus="jqueryAutoCompleteSKU('+dynamicId+')" value="" >'
						+'								</div>'
	                    +'          				</div>'
	                    +'        				</div>'
	            	
	                    +'    	 				<div class="col-md-8 form-group ">'
	                    +'        					<div class="row">'
	                    +'         						<div class="col-md-2">'
	                    +'									<h5>'
	                    +'										<label for="focusedInput " >品名：</label>'
	                    +'									</h5>'
	                    +'								</div>'
	                    +'        					<div class="col-md-10 input-group" style="padding:0 39px 0 15px;"><input class="form-control " id="pName'+dynamicId+'" name="pName'+dynamicId+'" type="text" value="" readonly>'
	                    +'							</div>'
	                    +'      				</div>'
	                    +'     				</div>'
	                
	                    +' 			</div>'
	            
	                    +'     			<div class="row">'
	                    
	                    +'    				<div class="col-md-4 form-group ">'
						+'         				<div class="row">'
						+'          				<div class="col-md-4">'
						+'								<h5>'
						+'									<label for="focusedInput " >規格：</label>'
						+'								</h5>'
						+'							</div>'
						+'         					<div class="col-md-8">'
						+'								<input class="form-control" type="text" id="spec'+dynamicId+'" name="spec'+dynamicId+'" readonly>'
						+'							</div>'
						+'      				</div>'
						+'     				</div>'
	                               
	                    +'           		<div class="col-md-4 form-group ">'
						+'         				<div class="row">'
						+'          				<div class="col-md-4">'
						+'								<h5>'
						+'									<label for="focusedInput " >顏色：</label>'
						+'								</h5>'
						+'							</div>'
						+'         					<div class="col-md-8">'
						+'								<input class="form-control" type="text" id="color'+dynamicId+'" name="color'+dynamicId+'" readonly>'
						+'							</div>'
						+'      				</div>'
						+'     				</div>'
						
						+'         			<div class="col-md-4 form-group ">'
	                    +'             			<div class="row">'
	                    +'              			<div class="col-md-4">'
	                    +'								<h5>'
	                    +'									<label for="focusedInput " >倉別：</label>'
	                    +'								</h5>'
	                    +'							</div>'
	                    +'              		<div class="col-md-8">'
	                    +'							<select class="form-control" name="warehouse'+dynamicId+'" id="warehouse'+dynamicId+'">'
						+'								<option value="">請選擇</option>'
						+'							</select>'
	                    +'						</div>'
	                    +'            		</div>'
	                    +'              </div>'
	                    +'           </div>'
	            
	                    +'           <div class="row">'
	                        
	                    +'				<div class="col-md-4 form-group ">'
	                    +'                	<div class="row">'
	                  	+'                  	<div class="col-md-4">'
	                  	+'							<h5>'
	                  	+'								<label for="focusedInput " >數量：</label>'
	                  	+'							</h5>'
	                  	+'						</div>'
	                  	+'                 		<div class="col-md-8"><input class="form-control digits required" name="qty'+dynamicId+'" title="數量必須大於0" type="text">'
	                  	+'						</div>'
	                  	+'                	</div>'
	                  	+'              </div>'
	                  
	                 	+' 				<div class="col-md-4 form-group ">'
	                  	+'                	<div class="row">'
	            		+'                  	<div class="col-md-4">'
	            		+'							<h5>'
	            		+'								<label for="focusedInput " >成本：</label>'
	            		+'							</h5>'
	            		+'						</div>'
	            		+'                 		<div class="col-md-8"><input class="form-control number required" name="price'+dynamicId+'" title="價格必須大於0" type="text">'
	            		+'						</div>'
	            		+'                	</div>'
	            		+'             </div>'
	            		
	            		+'				<div class="col-md-4 form-group ">'
						+'					<div class="row">'
	            		+'						<div class="col-md-4">'	
	            		+'							<h5>'		
	            		+'								<label for="focusedInput ">小計：</label>'			
	            		+'							</h5>'		
	            		+'						</div>'	
	            		+'						<div class="col-md-8">'	
	            		+'							<input class="form-control " title="" name="invoiceName'+dynamicId+'" type="text">'		
						+'						</div>'	
						+'					</div>'
						+'				</div>'
					
						+'				<div class="col-md-4 form-group ">'
						+'					<div class="row">'
						+'						<div class="col-md-4">'	
						+'							<h5>'	
						+'								<label for="focusedInput ">售價：</label>'	
						+'							</h5>'		
						+'						</div>'	
						+'						<div class="col-md-8">'	
						+'							<input class="form-control number required" title="" name="invoicePrice'+dynamicId+'" type="text">'	
						+'						</div>'	
						+'					</div>'
						+'				</div>'
					
						+'				<div class="col-md-4 form-group ">'
						+'					<div class="row">'
						+'						<div class="col-md-4">'	
						+'							<h5>'	
						+'								<label for="focusedInput ">owner：</label>'	
						+'							</h5>'
						+'						</div>'
						+'						<div class="col-md-8">'	
						+'							<input class="form-control " title="" id="owner'+dynamicId+'" name="owner'+dynamicId+'" type="text">'		
						+'						</div>'	
						+'					</div>'
						+'				</div>'
	                            
	                  	+'              <div class="col-md-4 form-group ">'
	                  	+'              	<div class="row">'
	                  	+'                  	<div class="col-md-4">'
	                  	+'							<h5>'
	                  	+'								<label for="focusedInput " >備註：</label>'
	                  	+'							</h5>'
	                  	+'						</div>'
	                  	+'                 		<div class="col-md-8"><input class="form-control" id="comment'+dynamicId+'" name="comment'+dynamicId+'" type="text">'
	                  	+'						</div>'
	                  	+'                	</div>'
	                  	+'            	</div>'
	                  	+'          </div>'
	           
	                  	+'         <br/>'
	                  	
	                  	+'			</div>'
	                  	+'		</div>'
	                  	+'</div>'

						);

		$("#count").val(dynamicId);
		
		dynamicId++;

	}

	

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
<c:if test="${PageCompetence.getInventoryManage() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color:#3DFF81;" >
      <ul class="nav nav-tabs">
        <li class="" style="background-color:#189B30"><a href="SearchOrder.jsp?begin=0&end=10" style="color:#FFFFFF">獨立出貨</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color:#189B30;" >
      <ul class="nav nav-tabs">
        <li><a href="SearchOrder.jsp?begin=0&end=10">查詢訂單</a></li>
        <li><a href="" style="color:#fff">新增訂單</a></li>
        <li><a href="Processing.jsp?begin=0&end=10">處理中</a></li>
        <li><a href="Pickup.jsp?begin=0&end=10">揀貨中</a></li>
        <li><a href="UploadTrackingCode.jsp?begin=0&end=10">上傳追蹤碼</a></li>
        <li><a href="Finished.jsp?begin=0&end=10">已完成訂單</a></li>
        <li><a href="ShipmentRecord.jsp?begin=0&end=10">訂單出貨記錄</a></li>
        <li><a href="refundPage.jsp?begin=0&end=10" >退貨</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb" >
    <li><a href="/HomePage.jsp" >首頁</a></li>
    <li class="active" style="display:"><a href="SearchOrder.jsp?begin=0&end=10">訂單管理</a></li>
    <li><a href="Pickup.jsp?begin=0&end=10">新增訂單</a></li>
  </ol>
</div>
<div class="container" style="background:#99C61D; border-radius:20px;">
  <form id="listForm" name="listForm" method="post"
			action=<c:url value='../independentOrder/'/>
  
  style="font-size: 100%; vertical-align: baseline; padding: 15px;"
  class="form-inline container">
  <fieldset class="container-fluid" style="padding: 0 30 0 0;">
    <legend>獨立訂單</legend>
    <input type="hidden">
    <div class="row">
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">日期：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" type="text" id="orderDate"	name="orderDate" readonly>
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group " style="display: none">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">單號：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="iorderMasterId" id="iorderMasterId" type="text" value="" readonly>
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
            <input class="form-control required " id=staffName name="staffName" type="text" value="${staffName}" readonly>
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">物流：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <select name="logistics" class="form-control">
		      <option value="DHL">DHL</option>
		      <option value="Fedex">Fedex</option>
		      <option value="EMS">EMS</option>
		      <option value="AP">AP(國際包裹)</option>
		      <option value="RA">RA(國際掛號)</option>
		      <option value="USPS1">USPS寄倉</option>
		      <option value="USPS2">USPS集運</option>
		      <option value="other">其它</option>
		    </select>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">平台：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="platform" id="platform"	type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">交易序號：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="transactionId" id="transactionId"	type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">幣別：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <select name="currency" id="currency" class="form-control">
		      <option value="USD">USD</option>
		      <option value="GBP">GBP</option>
		      <option value="EUR">EUR</option>
		      <option value="AUD">AUD</option>
		      <option value="CAD">CAD</option>
		      <option value="NTD">NTD</option>
		    </select>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">paypalPrice：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="paypalPrice" id="paypalPrice"	type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">paypalFees：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="paypalFees" id="paypalFees"	type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">paypalNet：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="paypalNet" id="paypalNet"	type="text" value="">
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">invoiceName：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="platform" id="platform"	type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">invoicePrice：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="transactionId" id="transactionId"	type="text" value="">
          </div>
        </div>
      </div>
    </div>
  </fieldset>
  <fieldset class="container-fluid" style="padding: 0 30 0 0;">
    <legend>客戶資料</legend>
    <input type="hidden">
    <div class="row">
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">客戶代號：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" type="text" id="guestId" name="guestId" value=""  onfocus = "jqueryAutoCompleteGuest()" onblur = "getGuestData()">
            <input type="hidden" name ='id' id='id'>
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">客戶姓名：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="name" id="name" type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">出生日期：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="birthday" id="birthday"	type="text" value="" readonly>
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">性別：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <select class="form-control" name="gender" id="gender">
              <option value="">請選擇</option>
              <option value="M">男</option>
              <option value="F">女</option>
            </select>
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">公司：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="company"	id="company" type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">平台帳號：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="platformAccount"	id="platformAccount" type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">信箱：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="email" id="email" type="text"	value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">電話：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="tel" id="tel" type="text"	value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">手機：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="phone" id="phone" type="text"	value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">國家：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="country" id="country"	type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">郵遞區號：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="postcode" id="postcode"	type="text" value="">
          </div>
        </div>
      </div>
      <div class="col-md-4 form-group ">
        <div class="row">
          <div class="col-md-4">
            <h5>
              <label for="focusedInput ">地址：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <input class="form-control" name="address" id="address"	type="text" value="">
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-8 form-group ">
        <div class="row">
          <div class="col-md-2">
            <h5>
              <label>備註：</label>
            </h5>
          </div>
          <div class="col-md-8">
            <textarea rows="3" cols="50" class="form-control"></textarea>
          </div>
        </div>
      </div>
    </div>
  </fieldset>
  <br />
  <div class="row text-center">
    <button id="buttonAddItem" type="button" name="" onclick="dynamicAdd()" class="btn-lg btn-success">新增欄位(F2)</button>
  </div>
  <fieldset class="container-fluid" style="padding: 0 30 0 0;">
    <legend></legend>
    <input type="hidden">
    <div class="panel-group" id="accordion">
      <div class="panel panel-default" id="dynamic1" style="background-color: #E7D29F">
        <div class="panel-heading">
          <div class="panel-title row ">
            <div class="col-md-11 form-group"> <a data-toggle="collapse" data-parent="#accordion"	href="#collapse1">訂單項目1</a>
              <input type="hidden" name="times" value="1">
              <input type="hidden" id="count"	name="count" value="1">
              <input type="hidden" id="autoCompleteNumber" name="autoCompleteNumber" value="1">
            </div>
            <div align="right" class="col-md-1 form-group">
              <button type="button" class="close" onclick="cleanFirstItem()"> <span aria-hidden="true">&times;</span><span class="sr-only">Close</span> </button>
            </div>
          </div>
        </div>
        <div id="collapse1" class="panel-collapse collapse in">
          <div class="panel-body">
            <input type="hidden">
            <div class="row">
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">SKU：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <input class="form-control  required" title="請輸入SKU"	id="sku1" name="sku1" type="text" onfocus="jqueryAutoCompleteSKU(1)" value="">
                  </div>
                </div>
                <!--  onchange="autoComplete(1)" --> 
              </div>
              <div class="col-md-8 form-group ">
                <div class="row">
                  <div class="col-md-2">
                    <h5>
                      <label for="focusedInput ">品名：</label>
                    </h5>
                  </div>
                  <div class="col-md-10 input-group"	style="padding: 0 39px 0 15px;">
                    <input class="form-control " id="pName1" name="pName1"	type="text" value="" readonly>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">規格：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <input class="form-control" id="spec1" type="text"	name="spec1" readonly>
                  </div>
                </div>
              </div>
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">顏色：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <input class="form-control" id="color1" type="text"	name="color1" readonly>
                  </div>
                </div>
              </div>
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">倉別：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <select class="form-control" name="warehouse1" id="warehouse1">
                      <option value="">請選擇</option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">數量：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <input class="form-control digits required" title="數量必須大於0" name="qty1" type="text">
                  </div>
                </div>
              </div>
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">成本：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <input class="form-control number required" title="價格必須大於0" name="price1" type="text">
                  </div>
                </div>
              </div>
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">小計：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <input class="form-control required"  name="invoiceName1" type="text">
                  </div>
                </div>
              </div>
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">售價：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <input class="form-control number required" title="價格必須大於0" name="invoicePrice1" type="text">
                  </div>
                </div>
              </div>
              <div class="col-md-4 form-group ">
                <div class="row">
                  <div class="col-md-4">
                    <h5>
                      <label for="focusedInput ">owner：</label>
                    </h5>
                  </div>
                  <div class="col-md-8">
                    <input class="form-control " id=owner1  name="owner1" type="text">
                  </div>
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
                    <input class="form-control" name="comment1" type="text">
                  </div>
                </div>
              </div>
            </div>
            <br />
          </div>
        </div>
      </div>
      <br />
      <div class="row text-center" id="formSubmit">
        <button type="submit" id="submitButton" name="submitButton"	class="btn-lg btn-success">送出</button>
      </div>
    </div>
  </fieldset>
  </form>
</div>
	<%@ include file="../href/footer.jsp"%>
</body>
</html>