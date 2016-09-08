package tw.iii.qr.order;

import java.sql.Date;

public class ShipmentRecord {

	private Date shippingDate;
	private int shippingId;
	private String type;
	private String EbayAccount;
	private String QR_id;
	private String SKU;
	private String productName;
	private int qty;
	private String country;
	private String owner;
	private String warehouse;
	private String staffName;
	private String comment;
	private String trackingCode;
	
	public Date getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}
	public int getshippingId() {
		return shippingId;
	}
	public void setshippingId(int shippingId) {
		this.shippingId = shippingId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEbayAccount() {
		return EbayAccount;
	}
	public void setEbayAccount(String ebayAccount) {
		EbayAccount = ebayAccount;
	}
	public String getQR_id() {
		return QR_id;
	}
	public void setQR_id(String qR_id) {
		QR_id = qR_id;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTrackingCode() {
		return trackingCode;
	}
	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
	
	
}
