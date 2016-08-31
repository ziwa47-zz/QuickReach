package tw.iii.qr.order;

import java.sql.Date;

public class COrderMaster {
	private String order_id;
	private String QR_id;
	private String outsideCode;
	private String productName;
	private String platform;
	private String company;
	private String EbayAccount;
	private String guestAccount;
	private Date orderDate;
	private Date payDate;
	private String logisticsID;
	private String logistics;
	private String orderStatus;
	private String paypalId;
	private double payment;
	private Date shippingDate;
	private double shippingFees;
	private double refundFees;
	private double otherFees;
	private double ebayFees;
	private double paypalFees;
	private boolean insurance;
	private double insurancePrice;
	private double insuranceTotal;
	private String currency;
	private double weight;
	private double totalWeight;
	private String FedexService;
	private String staffName;
	private String size;
	private double totalPrice;
	private String trackingCode;
	private String comment;
	private double packageFees;
	private String ebayNO;
	private String ebayItemNO;
	private double ebayPrice;
	private double ebayTotal;
	private String paypalmentId;
	private double paypalTotal;
	private double paypalNet;
	
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getQR_id() {
		return QR_id;
	}
	public void setQR_id(String qR_id) {
		QR_id = qR_id;
	}
	public String getOutsideCode() {
		return outsideCode;
	}
	public void setOutsideCode(String outsideCode) {
		this.outsideCode = outsideCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEbayAccount() {
		return EbayAccount;
	}
	public void setEbayAccount(String EbayAccount) {
		this.EbayAccount = EbayAccount;
	}
	public String getGuestAccount() {
		return guestAccount;
	}
	public void setGuestAccount(String guestAccount) {
		this.guestAccount = guestAccount;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getLogisticsID() {
		return logisticsID;
	}
	public void setLogisticsID(String logisticsID) {
		this.logisticsID = logisticsID;
	}
	public String getLogistics() {
		return logistics;
	}
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPaypalId() {
		return paypalId;
	}
	public void setPaypalId(String paypalId) {
		this.paypalId = paypalId;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public Date getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}
	public double getShippingFees() {
		return shippingFees;
	}
	public void setShippingFees(double shippingFees) {
		this.shippingFees = shippingFees;
	}
	public double getRefundFees() {
		return refundFees;
	}
	public void setRefundFees(double refundFees) {
		this.refundFees = refundFees;
	}
	public double getOtherFees() {
		return otherFees;
	}
	public void setOtherFees(double otherFees) {
		this.otherFees = otherFees;
	}
	public double getEbayFees() {
		return ebayFees;
	}
	public void setEbayFees(double ebayFees) {
		this.ebayFees = ebayFees;
	}
	public double getPaypalFees() {
		return paypalFees;
	}
	public void setPaypalFees(double paypalFees) {
		this.paypalFees = paypalFees;
	}
	public boolean isInsurance() {
		return insurance;
	}
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	public double getInsurancePrice() {
		return insurancePrice;
	}
	public void setInsurancePrice(double insurancePrice) {
		this.insurancePrice = insurancePrice;
	}
	public double getInsuranceTotal() {
		return insuranceTotal;
	}
	public void setInsuranceTotal(double insuranceTotal) {
		this.insuranceTotal = insuranceTotal;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getFedexService() {
		return FedexService;
	}
	public void setFedexService(String fedexService) {
		FedexService = fedexService;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getPackageFees() {
		return packageFees;
	}
	public void setPackageFees(double packageFees) {
		this.packageFees = packageFees;
	}
	
	public String getEbayNO() {
		return ebayNO;
	}
	public void setEbayNO(String ebayNO) {
		this.ebayNO = ebayNO;
	}
	public String getEbayItemNO() {
		return ebayItemNO;
	}
	public void setEbayItemNO(String ebayItemNO) {
		this.ebayItemNO = ebayItemNO;
	}
	public double getEbayPrice() {
		return ebayPrice;
	}
	public void setEbayPrice(double ebayPrice) {
		this.ebayPrice = ebayPrice;
	}
	public double getEbayTotal() {
		return ebayTotal;
	}
	public void setEbayTotal(double ebayTotal) {
		this.ebayTotal = ebayTotal;
	}
	public String getPaypalmentId() {
		return paypalmentId;
	}
	public void setPaypalmentId(String paypalmentId) {
		this.paypalmentId = paypalmentId;
	}
	public double getPaypalTotal() {
		return paypalTotal;
	}
	public void setPaypalTotal(double paypalTotal) {
		this.paypalTotal = paypalTotal;
	}
	public double getPaypalNet() {
		return paypalNet;
	}
	public void setPaypalNet(double paypalNet) {
		this.paypalNet = paypalNet;
	}
	
}


