package tw.iii.qr.order;

public class SessionRecord {

	private String SearchOrder;
	private String OrderProcessing;
	private String OrderPickUp;
	private String OrderFinished;
	private String OrderAbnormal;
	private String DayliBalance;
	private String OrdersResult;


	public String getOrderProcessing() {
		OrderProcessing = "SearchProcessingResult";
		return OrderProcessing;
	}
	public void setOrderProcessing(String orderProcessing) {
		OrderProcessing = orderProcessing;
	}
	public String getOrderPickUp() {
		OrderPickUp = "SearchPickUpResult";
		return OrderPickUp;
	}
	public void setOrderPickUp(String orderPickUp) {
		OrderPickUp = orderPickUp;
	}
	public String getOrderFinished() {
		OrderFinished = "SearchFinishedResult";
		return OrderFinished;
	}
	public void setOrderFinished(String orderFinished) {
		OrderFinished = orderFinished;
	}
	public String getOrderAbnormal() {
		OrderAbnormal = "SearchOrderAbnormal";
		return OrderAbnormal;
	}
	public void setOrderAbnormal(String orderAbnormal) {
		OrderAbnormal = orderAbnormal;
	}
	public String getDayliBalance() {
		DayliBalance = "SearchDailiBalanceResult";
		return DayliBalance;
	}
	public void setDayliBalance(String dayliBalance) {
		DayliBalance = dayliBalance;
	}
	public String getOrdersResult() {
		OrdersResult = "SearchOrdersResult";
		return OrdersResult;
	}
	public void setOrdersResult(String ordersResult) {
		OrdersResult = ordersResult;
	}
}
