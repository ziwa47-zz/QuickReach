<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<script type="text/javascript">
//dynamicAdd
function dynamicAdd() {
$("#formSubmit").before(
'<div class="panel panel-default" id="dynamic'+dynamicId+'" style="background-color: #E7D29F">'+
  '<div class="panel-heading">'+
	'<div class="panel-title row ">'+
	  '<div class="col-md-11 form-group">'+
		'<a data-toggle="collapse" data-parent="#accordion"'+
		'href="#collapse'+dynamicId+'">進貨項目'+dynamicId+'</a>'+
		'<input type="hidden" name="times" value="'+dynamicId+'">'+//計算進貨筆+
	  '</div>'+
	'<div align="right" class="col-md-1 form-group">'+
	  '<button type="button" class="close" onclick="removeDynamicItem('+dynamicId+')"><span aria-hidden="true">&times;</span><span class="sr-only"></span></button>'+
	'</div>'+
  '</div>'+
'</div>'+
'<div id="collapse'+dynamicId+'" class="panel-collapse collapse in">'+
  '<div class="panel-body">'+
	'<input type="hidden">'+
	'<div class="row">'+
	  '<div class="col-md-4 form-group ">'+
		'<div class="row">'+
'<div class="col-md-4"><h5><label for="focusedInput " >SKU：</label></h5></div> '+
'<div class="col-md-8"><input class="form-control required" id="sku'+dynamicId+'" name="sku'+dynamicId+'" type="text" onfocus="jqueryAutoCompleteSKU('+dynamicId+')" value="" ></div>'+
'</div>'+
'</div>'+
'<div class="col-md-8 form-group ">'+
'<div class="row">'+
'<div class="col-md-2"><h5><label for="focusedInput " >品名：</label></h5></div> '+
'<div class="col-md-10 input-group" style="padding:0 39px 0 15px;"><input class="form-control " id="pName'+dynamicId+'" name="pName'+dynamicId+'" type="text" value="" readonly></div>'+
'</div>'+
'</div>'+
'</div>'+
'<div class="row">'+
'<div class="col-md-4 form-group ">'+
'<div class="row">'+
'<div class="col-md-4"><h5><label for="focusedInput " >規格：</label></h5></div>'+
'<div class="col-md-8"><input class="form-control" type="text" id="spec'+dynamicId+'" name="spec'+dynamicId+'" readonly></div>'+
'</div>'+
'</div>'+
'<div class="col-md-4 form-group ">'+
'<div class="row">'+
'<div class="col-md-4"><h5><label for="focusedInput " >顏色：</label></h5></div>'+
'<div class="col-md-8"><input class="form-control" type="text" id="color'+dynamicId+'" name="color'+dynamicId+'" readonly></div>'+
'</div>'+
'</div>'+
'<div class="col-md-4 form-group ">'+
'<div class="row">'+
'<div class="col-md-4"><h5><label for="focusedInput " >儲位：</label></h5></div>'+
'<div class="col-md-8"><input class="form-control" style="width:88px;"id="warehousePositionOne'+dynamicId+'" name="warehousePositionOne'+dynamicId+'" type="text" readonly>'+
-
'<input class="form-control" style="width:88px;" id="warehousePositionTwo'+dynamicId+'" name="warehousePositionTwo'+dynamicId+'" type="text" readonly></div>'+
'</div>'+
'</div>'+
'</div>'+
'<div class="row">'+
'<div class="col-md-4 form-group ">'+
'<div class="row">'+
'<div class="col-md-4"><h5><label for="focusedInput " >數量：</label></h5></div>'+
'<div class="col-md-8"><input class="form-control digits required" name="qty'+dynamicId+'" title="數量必須大於0" type="text"></div>'+
'</div>'+
'</div>'+
'<div class="col-md-4 form-group ">'+
'<div class="row">'+
'<div class="col-md-4"><h5><label for="focusedInput " >價格：</label></h5></div>'+
'<div class="col-md-8"><input class="form-control number required" name="price'+dynamicId+'" title="價格必須大於0" type="text"></div>'+
'</div>'+
'</div>'+
'<div class="col-md-4 form-group ">'+
'<div class="row">'+
'<div class="col-md-4"><h5><label for="focusedInput " >備註：</label></h5></div>'+
'<div class="col-md-8"><input class="form-control" id="comment'+dynamicId+'" name="comment'+dynamicId+'" type="text"></div>'+
'</div>'+
'</div>'+
'</div>'+
'<br/>'+
'</div>'+
'</div>'+
'</div>');
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

</body>
</html>