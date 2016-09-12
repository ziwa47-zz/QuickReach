package tw.iii.purchase;

import java.util.Date;

public class Cpurchase_detail  extends Cpurchase_master{
	private String purchaseId;
	private String SKU;
	private String P_name;
	private String warehousePosition;
	private String warehousePosition2;
	
	private String newWarehousePosition;
	private String newWarehousePosition2;
	private int qty;
	private double price ;
	private String comment;
	private String stockStatus;
	
	
	public String getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public String getP_name() {
		return P_name;
	}
	public void setP_name(String p_name) {
		P_name = p_name;
	}
	public String getWarehousePosition() {
		return warehousePosition;
	}
	public void setWarehousePosition(String warehousePosition) {
		this.warehousePosition = warehousePosition;
	}
	public String getWarehousePosition2() {
		return warehousePosition2;
	}
	public void setWarehousePosition2(String warehousePosition2) {
		this.warehousePosition2 = warehousePosition2;
	}
	
	
	
	public String getNewWarehousePosition() {
		return newWarehousePosition;
	}
	public void setNewWarehousePosition(String newWarehousePosition) {
		this.newWarehousePosition = newWarehousePosition;
	}
	public String getNewWarehousePosition2() {
		return newWarehousePosition2;
	}
	public void setNewWarehousePosition2(String newWarehousePosition2) {
		this.newWarehousePosition2 = newWarehousePosition2;
	}
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStockStatus() {
		return stockStatus;
	}
	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	
	
}
