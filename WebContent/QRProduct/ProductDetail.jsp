<%@page import="tw.iii.qr.DataBaseConn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.sql.Connection,java.sql.ResultSet,java.util.*,tw.iii.qr.stock.DTO.CProduct"%>
<jsp:useBean id="searchDetail" class="tw.iii.qr.stock.CProductFactory"
	scope="page" />
<!doctype html>

<html>

<head>


<script src="../js/jquery-1.12.4.min.js"></script>
<!-- jqueryAutoComplete 不可以在../js/jquery-1.12.4.min.js 之前 -->


<script type="text/javascript">
	function optionsonchange(ele) {
		if (ele.checked) {
			$("#myfields").prop("disabled", false);
			$("#btnCheck").prop("disabled", false);
		} else {
			$("#myfields").prop("disabled", true);
			$("#btnCheck").prop("disabled", true);
		}

	};

	function invalidate() {
		$("#collapse2").addClass("in");
		$("#collapse3").addClass("in");

	};
	$(function() {

		$("#listForm").validate({
			//debug:true,
			ignore : [],
			invalidHandler : function(form) {
				invalidate();

			}
		});
	});
</script>

<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />

<style type="text/css">
#listForm label.error {
	font-size: 0.8em;
	color: #F00;
	font-weight: bold;
	display: block;
}
</style>
<title>商品明細</title>
</head>
<body>
	<%@ include file="/href/navbar.jsp"%>
<c:if test="${PageCompetence.getProductManage() == 0 }">  
<% response.sendRedirect("/HomePage.jsp"); %>   
</c:if>
	<%
		String sku;
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("sku") != null || request.getParameter("sku") != "") {
			Connection conn = new DataBaseConn().getConn();
			sku = request.getParameter("sku");
			CProduct product = searchDetail.searchDetail(sku, conn);
			session.setAttribute("resultDetail", product);
			conn.close();
		}
	%>
	<div class="nav">
		<div class="container">
			<div class="navbar-left" style="background-color: #BCF1E5;">
				<ul class="nav nav-tabs">
					<c:if test="${PageCompetence.getInventoryManage() == 1 }">	
              	<li ><a href="SearchStockPage.jsp" style="color:#000">庫存</a></li>
              </c:if>
					<li class="" style="background-color: #1CAF9A"><a
						href="SearchProductPage.jsp" style="color: #fff">商品</a></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="nav" style="background-color: #1CAF9A;">
				<ul class="nav nav-tabs">
					<li class="" style="background-color: #1CAF9A"><a
						href="SearchProductPage.jsp" style="color: #fff">查詢商品</a></li>
					<li><a href="TotalBundles.jsp" style="color: #000000">查詢組合商品</a></li>
					<li><a href="NewProduct.jsp" style="color: #000">新增單項商品</a></li>
					<li><a href="BundlesAdd.jsp" style="color: #000">新增組合商品</a></li>

				</ul>
			</div>
		</div>

	</div>



	<div class="container container-fluid breadcrumbBox">
		<ol class="breadcrumb">
			<li><a href="/HomePage.jsp">首頁</a></li>
			<li class="active" style="display:"><a
				href="SearchProductPage.jsp">庫存/商品管理</a></li>
			<li><a href="ProductDetail.jsp?sku=${resultDetail.getSKU()}">商品明細</a></li>
		</ol>
	</div>


	<div class="container"
		style="background: #9DDCD1; border-radius: 20px;">
		<form id="listForm" name="listForm" method="post"
			action="../ProductDo"
			style="font-size: 100%; vertical-align: baseline; padding: 15px;"
			class="container"
			enctype="multipart/form-data">
			<div class="row">
				<label for="inputPassword" class="col-md-2 control-label text-left">編輯模式</label>
				<div class="col-md-4">
					<label class="radio-inline"> <input type="checkbox"
						name="optionsRadios" id="optionsRadios1"
						onchange="optionsonchange(this)">開啟
					</label>

					<button type="submit" name="submit" value="updateProduct"
						class="btn btn-lg btn-success" id="btnCheck" disabled>更新產品資料</button>

				</div>
			</div>
			<fieldset id="myfields" class="container-fluid"
				style="padding: 0 30px 0 0;" disabled>
				<legend>產品明細</legend>
				<input type="hidden">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse1">商品基本資料</a>
							</h4>
						</div>
						<div id="collapse1" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="container-fluid form-horizontal">
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>商品編號</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control required" title="請輸入正確SKU"
												type="text" name="sku" value="${resultDetail.getSKU()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>條碼編號</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control" type="text" name="barcode"
												value="${resultDetail.getSKU()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>擁有者</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control" type="text" name="owner"
												value="${resultDetail.getOwner()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>商品類別</h4>
										</div>
										<div class="col-md-8  well-sm">
											<select class="form-control" name="productType">

												<c:choose>
													<c:when test="${resultDetail.getProductType() eq '單一商品' } ">
														<option value="1" selected>單一商品</option>
														<option value="2">清倉類</option>
														<option value="3">調貨類</option>
														<option value="4">組合商品</option>

													</c:when>

													<c:when test="${resultDetail.getProductType() eq '清倉類' } ">
														<option value="1">單一商品</option>
														<option value="2" selected>清倉類</option>
														<option value="3">調貨類</option>
														<option value="4">組合商品</option>

													</c:when>

													<c:when test="${resultDetail.getProductType() eq '調貨類' } ">
														<option value="1">單一商品</option>
														<option value="2">清倉類</option>
														<option value="3" selected>調貨類</option>
														<option value="4">組合商品</option>

													</c:when>

													<c:when test="${resultDetail.getProductType() eq '組合商品' } ">
														<option value="單一商品">單一商品</option>
														<option value="清倉類">清倉類</option>
														<option value="調貨類">調貨類</option>
														<option value="組合商品" selected>組合商品</option>

													</c:when>
													
													<c:otherwise>
													<option value="" selected></option>
													<option value="單一商品">單一商品</option>
														<option value="清倉類">清倉類</option>
														<option value="調貨類">調貨類</option>
														<option value="組合商品" >組合商品</option>
													
													
													</c:otherwise>
												</c:choose>


											</select>


										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>廠牌</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control required" title="請輸入正確廠牌"
												type="text" name="brand" value="${resultDetail.getBrand()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>副廠牌</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control" type="text" name="subbrand"
												value="${resultDetail.getSubBrand()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>EAN</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control" type="text" name="ean"
												value="${resultDetail.getEAN()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>ProductCode</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control" type="text" name="productcode"
												value="${resultDetail.getProductCode()}">
										</div>
									</div>

									<c:if test="${resultDetail.getPicturePath() ne '' || null}">
										<div class="row">
											<div class="col-md-3 text-right well-sm label-tag">
												<h4>產品圖片</h4>
											</div>
											<div class="col-md-8 well-sm">
												<img alt="" height="100%" width="100%" src="/pics/${resultDetail.getPicturePath()}">
											</div>

										</div>

										<div class="row">
											<div class="col-md-3 text-right well-sm label-tag">
												<h4>上傳圖片</h4>
											</div>
											<div class="col-md-8 well-sm">
												<input class="form-control" type="file" name="picturePath"
													value="">
											</div>
										</div>


									</c:if>

									<c:if test="${resultDetail.getPicturePath() eq ''}">
										<div class="row">
											<div class="col-md-3 text-right well-sm label-tag">
												<h4>上傳圖片</h4>
											</div>
											<div class="col-md-8 well-sm">
												<input class="form-control" type="file" name="picturePath"
													value="">
											</div>
										</div>

									</c:if>




								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse2">商品進階資料</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-collapse collapse">
							<div class="panel-body">
								<div class="container-fluid form-horizontal">

									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>品名</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control required" title="請輸入正確品名"
												type="text" name="pname" value="${resultDetail.getP_name()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>規格</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control required" title="請輸入正確規格"
												type="text" name="spec" value="${resultDetail.getSpec()}">
										</div>
									</div>

									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>顏色</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control required" title="請輸入正確顏色"
												type="text" name="color" value="${resultDetail.getColor()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>安全庫存</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control digits required" title="請輸入正確安全庫存"
												type="text" name="securedqty"
												value="${resultDetail.getSecuredQty()}">
										</div>
									</div>
									
									
									<c:if test="${PageCompetence.getProductCostView() == 1 }">
										<c:if test="${PageCompetence.getPriceChange() == 1 }">	
											<div class="row">
												<div class="col-md-3 text-right well-sm label-tag">
													<h4>成本(TWD)</h4>
												</div>
												<div class="col-md-8 well-sm">
													<input class="form-control number required" title="請輸入正確成本"
														type="text" name="cost" value="${resultDetail.getCost()}">
												</div>
											</div>
										</c:if>		
										<c:if test="${PageCompetence.getPriceChange() == 0 }">	
											<div class="row">
												<div class="col-md-3 text-right well-sm label-tag">
													<h4>成本(TWD)</h4>
												</div>
												<div class="col-md-8 well-sm">
													<input class="form-control number required" title="請輸入正確成本"
														type="text" name="cost" readonly="readonly" value="${resultDetail.getCost()}">
												</div>
											</div>
										</c:if>		
									</c:if>


									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>更新紀錄</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control required yymmdd" type="text"
												name="checkupdate" value="${resultDetail.getCheckupdate()}"
												readonly>
										</div>
									</div>

									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>上架否</h4>
										</div>
										<div class="col-md-8  well-sm">

											<select class="form-control" name="added">
											
											<c:choose>
											<c:when test="${resultDetail.getAdded() eq 'true'  }">
											<option value="true" selected>是</option>
													<option value="false">否</option>
											</c:when >
											
											<c:when test="${resultDetail.getAdded() eq 'false'  }">
											<option value="true">是</option>
													<option value="false" selected>否</option>
											</c:when >
											
											<c:otherwise>
											<option value="true" selected>是</option>
													<option value="false" >否</option>
											
											
											</c:otherwise>
											
											
											</c:choose>
											


											</select>

										</div>
									</div>


								</div>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapse3">商品備註</a>
							</h4>
						</div>
						<div id="collapse3" class="panel-collapse collapse">
							<div class="panel-body">
								<input type="hidden">
								<div class="container-fluid form-horizontal">
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>材積</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control" type="text" name="volume"
												value="${resultDetail.getVolume()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>包裝材質</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control" type="text" name="package"
												value="${resultDetail.getPackageMatrial()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>材積重(克)</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control number" type="text" name="vilu"
												value="${resultDetail.getVilumetricWeight()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>建檔日</h4>
										</div>

										<div class="col-md-8 well-sm">
											<input class="form-control  yymmdd" type="text" name="cdate"
												value="${resultDetail.getCreateDate()}" readonly>
										</div>

									</div>

									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>重量(克)</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control number" type="text" name="weight"
												value="${resultDetail.getWeight()}">
										</div>
									</div>
									<div class="row">
										<div class="col-md-3 text-right well-sm label-tag">
											<h4>備註</h4>
										</div>
										<div class="col-md-8 well-sm">
											<input class="form-control" type="text" name="comment"
												value="${resultDetail.getComment()}">
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</form>
	</div>



</body>
</html>
