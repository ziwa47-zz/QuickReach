package tw.iii.qr.IndependentOrder.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="storage")
public class Storage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9732086924502591L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer item;
	@Id
	@Column(name="SKU")
	private String sku;
	private String warehouse;
	private String warehousePosition1;
	private String warehousePosition2;
	private Integer qty;
	private String comment;
	private Date purchaseDate;
	private String company;

	public void setItem(Integer item) {
		this.item = item;
	}
	public Integer getItem() {
		return this.item;
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

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return this.comment;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompany() {
		return this.company;
	}

}