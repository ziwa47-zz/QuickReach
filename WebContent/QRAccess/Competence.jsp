<%@ page import="tw.iii.qr.Competence"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="ctsql" scope="session" class="tw.iii.qr.CompetenceSql" />
<jsp:setProperty name="ctsql" property="*" />

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<title>檢視權限</title>

<script type="text/javascript">
	function goct() {
		searchform.action = "Competence.jsp"

		searchform.submit()

	}
</script>


</head>

<body><%@ include file="/href/navbar.jsp"%>
<div class="nav">
  <div class="container">
    <div class="navbar-left" style="background-color: #C7AAE4;">
      <ul class="nav nav-tabs">
        <li><a href="Competence.jsp" style="color: #FFF">權限管理</a></li>
      </ul>
    </div>
  </div>
  <div class="container">
    <div class="nav" style="background-color: #984AC0;">
      <ul class="nav nav-tabs">
        <li><a href="Competence.jsp" style="color: #FFF">檢視權限</a></li>
        <li><a href="CompetenceInsert.jsp">新增權限</a></li>
      </ul>
    </div>
  </div>
</div>
<div class="container container-fluid breadcrumbBox">
  <ol class="breadcrumb">
    <li><a href="../HomePage.jsp">首頁</a></li>
    <li class="active" style="display:"><a href="Competence.jsp">權限管理</a></li>
    <li><a href="Competence.jsp">檢視權限</a></li>
  </ol>
</div>

	<div class="container">
		<form name="searchform" method="post" action="CompetenceInsert.do"
			style="font-size: 100%; vertical-align: baseline;"
			class=" form-group container">
			<%
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");

				LinkedList<Competence> list = new LinkedList<Competence>();
				list = ctsql.getCompetenceLevel();
				session.setAttribute("list", list);

				if (request.getParameter("CompetenceLv") != null) {
					//System.out.println(request.getParameter("Competencelv"));

					String csv = new String(request.getParameter("CompetenceLv").getBytes("8859_1"), "UTF-8");
					session.setAttribute("csv", csv);
					//System.out.println(csv);
					//out.write(csv);
				} else {
					System.out.println("null");

				}
				
				//System.out.print(1+cv);
				//session.setAttribute("cv", cv);
			%>
			
			<c:if test="${PageCompetence.getAccountInfoEdit() == 0 }">  
			<% response.sendRedirect("/HomePage.jsp"); %>
			</c:if>


			<input type="hidden">
			<h3 class=""
				style="background: #BCF1E5; border-left: 6px solid #1CAF9A;">權限設定</h3>
			<div class="container-fluid form-horizontal">
				<div class="row">
					<div class="col-md-3 text-right well-sm label-tag">
						<h4>權限等級</h4>
					</div>
					<div class="col-md-5 well-sm">
						<select class="form-control" name="CompetenceLv" onChange="goct()">
							<option value="ab">==請選擇==</option>
							<c:forEach var="i" varStatus="check" items="${list}" begin="0"
								step="1">
								<c:if test="${csv eq i.getCompetenceLv() }">
									<option selected value="${i.getCompetenceLv()}">${i.getCompetenceLv()}</option>
								</c:if>
								<c:if test="${csv ne i.getCompetenceLv() }">
									<option value="${i.getCompetenceLv()}">${i.getCompetenceLv()}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>

				<c:forEach var="lv" varStatus="check" items="${list}" begin="0"
					step="1">
					<c:if test="${csv eq lv.getCompetenceLv() }">
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>商品權限</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getProductManage() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ProductManage"
										checked />管理商品</label>
								</c:if>
								<c:if test="${lv.getProductManage() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ProductManage" />管理商品</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>採購權限</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getPurchaseManage() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="PurchaseManage"
										checked />管理採購</label>
								</c:if>
								<c:if test="${lv.getPurchaseManage() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="PurchaseManage" />管理採購</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>庫存權限</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getInventoryManage() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="InventoryManage"
										checked />管理庫存&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								</c:if>
								<c:if test="${lv.getInventoryManage() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="InventoryManage" />管理庫存&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								</c:if>

								<c:if test="${lv.getInventoryInfoEdit() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="InventoryInfoEdit" checked />編輯庫存資料</label>
								</c:if>
								<c:if test="${lv.getInventoryInfoEdit() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="InventoryInfoEdit" />編輯庫存資料</label>
								</c:if>

							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>客戶權限</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getClientManage() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ClientManage"
										checked /> 管理客戶</label>
								</c:if>
								<c:if test="${lv.getClientManage() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ClientManage" />
										管理客戶</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>訂單權限</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getEntireOrders() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="EntireOrders"
										checked />所有狀態訂單</label>
								</c:if>
								<c:if test="${lv.getEntireOrders() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="EntireOrders" />所有狀態訂單</label>
								</c:if>

								<c:if test="${lv.getOrdersInvoiceDownload() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="OrdersInvoiceDownload" checked />下載所有訂單Invoice</label>
								</c:if>
								<c:if test="${lv.getOrdersInvoiceDownload() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="OrdersInvoiceDownload" />下載所有訂單Invoice</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>&nbsp;</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getPriceChange() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="PriceChange"
										checked />更改成本費用</label>
								</c:if>
								<c:if test="${lv.getPriceChange() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="PriceChange" />更改成本費用</label>
								</c:if>

								<c:if test="${lv.getPendingOrdersEdit() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="PendingOrdersEdit" checked /> 編輯待處理訂單</label>
								</c:if>
								<c:if test="${lv.getPendingOrdersEdit() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="PendingOrdersEdit" /> 編輯待處理訂單</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>&nbsp;</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getTotalAmountEdit() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="TotalAmountEdit"
										checked />編輯訂單金額</label>
								</c:if>
								<c:if test="${lv.getTotalAmountEdit() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="TotalAmountEdit" />編輯訂單金額</label>
								</c:if>

								<c:if test="${lv.getOrdersManage() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1" name="OrdersManage"
										checked />管理訂單</label>
								</c:if>
								<c:if test="${lv.getOrdersManage() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1" name="OrdersManage" />管理訂單</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>報表權限</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getChartView() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ChartView"
										checked />查看圖表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								</c:if>
								<c:if test="${lv.getChartView() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ChartView" />查看圖表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								</c:if>

								<c:if test="${lv.getProductProfitView() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="ProductProfitView" checked />查看商品利潤</label>
								</c:if>
								<c:if test="${lv.getProductProfitView() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="ProductProfitView" />查看商品利潤</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>&nbsp;</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getReportView() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ReportView"
										checked />查看報表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								</c:if>
								<c:if test="${lv.getReportView() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ReportView" />查看報表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								</c:if>

								<c:if test="${lv.getProductCostView() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1" name="ProductCostView"
										checked />查看商品成本</label>
								</c:if>
								<c:if test="${lv.getProductCostView() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1" name="ProductCostView" />查看商品成本</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>帳號權限</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getAccountInfoEdit() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="AccountInfoEdit"
										checked />修改帳號資料</label>
								</c:if>
								<c:if test="${lv.getAccountInfoEdit() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="AccountInfoEdit" />修改帳號資料</label>
								</c:if>

								<c:if test="${lv.getEbayPaypalAccountEdit() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="EbayPaypalAccountEdit" checked />編輯ebay/paypal帳號</label>
								</c:if>
								<c:if test="${lv.getEbayPaypalAccountEdit() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="EbayPaypalAccountEdit" />編輯ebay/paypal帳號</label>
								</c:if>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3 text-right well-sm label-tag">
								<h4>&nbsp;</h4>
							</div>
							<div class="col-md-5 well-sm">
								<c:if test="${lv.getParamSettingEdit() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ParamSettingEdit"
										checked />修改參數設定</label>
								</c:if>
								<c:if test="${lv.getParamSettingEdit() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_0" name="ParamSettingEdit" />修改參數設定</label>
								</c:if>

								<c:if test="${lv.getInventoryCostView() == 1 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="InventoryCostView" checked />查看庫存成本</label>
								</c:if>
								<c:if test="${lv.getInventoryCostView() == 0 }">
									<label class="checkboxbox-inline"><input
										type="checkbox" id="id_fd-is_active_1"
										name="InventoryCostView" />查看庫存成本</label>
								</c:if>
							</div>
						</div>

						<div class="container-fluid form-horizontal">
							<div class="row text-center">
								<button type="submit" value="update" name="smt" id="">修改</button>
								<button type="submit" value="delete" name="smt" id="">刪除</button>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

		</form>
	</div>

	<%
		session.removeAttribute("csv");
	%>

	<%@ include file="/href/footer.jsp"%>
</body>
</html>
