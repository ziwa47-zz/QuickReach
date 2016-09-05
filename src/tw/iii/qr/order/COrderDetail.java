package tw.iii.qr.order;

import java.sql.Date;

public class COrderDetail {
	private String order_id;
	private String SKU;
	private String productName;
	private String invoiceName;
	private double price;
	private double invoicePrice;
	private int qty;
	private String comment;
	private String owner;
	private String warehouse;
	private int item;
	private String QR_id;
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
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
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getInvoicePrice() {
		return invoicePrice;
	}
	public void setInvoicePrice(double invoicePrice) {
		this.invoicePrice = invoicePrice;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public String getQR_id() {
		return QR_id;
	}
	public void setQR_id(String qR_id) {
		QR_id = qR_id;
	}

}
