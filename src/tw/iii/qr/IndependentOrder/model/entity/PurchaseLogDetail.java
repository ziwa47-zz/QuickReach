package tw.iii.qr.IndependentOrder.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name="purchaselog_detail")
public class PurchaselogDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2674334248706188391L;
	@Id
	private String purchaseId;
	@Id
	@Column(name="SKU")
	private String sku;
	private String warehouse;
	private String warehousePosition1;
	private String warehousePosition2;
	private Integer qty;
	private BigDecimal price;
	private String comment;
	private String stockStatus;

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseId() {
		return this.purchaseId;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getSku() {
		return this.sku;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getWarehouse() {
		return this.warehouse;
	}

	public void setWarehousePosition1(String warehousePosition1) {
		this.warehousePosition1 = warehousePosition1;
	}
	public String getWarehousePosition1() {
		return this.warehousePosition1;
	}

	public void setWarehousePosition2(String warehousePosition2) {
		this.warehousePosition2 = warehousePosition2;
	}
	public String getWarehousePosition2() {
		return this.warehousePosition2;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getQty() {
		return this.qty;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return this.comment;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getStockStatus() {
		return this.stockStatus;
	}

}