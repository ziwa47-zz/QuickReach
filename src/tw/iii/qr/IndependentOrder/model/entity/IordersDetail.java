package tw.iii.qr.IndependentOrder.model.entity;
// default package
// Generated 2017/2/18 �U�� 10:32:46 by Hibernate Tools 4.0.0.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IordersDetail generated by hbm2java
 */
@Entity
@Table(name = "iorders_detail")
public class IordersDetail implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -6110412986393571142L;
	private Integer item;
	private String qrId;
	private String tansactionId;
	private String sku;
	private String productName;
	private BigDecimal price;
	private Integer qty;
	private String warehouse;
	private String comment;
	private String owner;
	private BigDecimal weight_g;
	private BigDecimal weight_oz;
	private String picPath;
	

	public IordersDetail() {
	}

	public IordersDetail(String qrId) {
		this.qrId = qrId;
	}

	public IordersDetail(String qrId, String tansactionId, String sku, String productName, BigDecimal price,
			Integer qty, String warehouse, String comment, String owner,BigDecimal weight_g,BigDecimal weight_oz
			,String picPath) {
		this.qrId = qrId;
		this.tansactionId = tansactionId;
		this.sku = sku;
		this.productName = productName;
		this.price = price;
		this.qty = qty;
		this.warehouse = warehouse;
		this.comment = comment;
		this.owner = owner;
		this.weight_g = weight_g;
		this.weight_oz = weight_oz;
		this.picPath = picPath;
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Item", unique = true, nullable = false)
	public Integer getItem() {
		return this.item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	@Column(name = "QR_id", nullable = false, length = 100)
	public String getQrId() {
		return this.qrId;
	}

	public void setQrId(String qrId) {
		this.qrId = qrId;
	}

	@Column(name = "tansactionId", length = 100)
	public String getTansactionId() {
		return this.tansactionId;
	}

	public void setTansactionId(String tansactionId) {
		this.tansactionId = tansactionId;
	}

	@Column(name = "SKU", length = 100)
	public String getSku() {
		return this.sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Column(name = "productName", length = 1000)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}



	@Column(name = "price", precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	@Column(name = "qty")
	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	@Column(name = "warehouse", length = 100)
	public String getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	@Column(name = "comment", length = 3000)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "owner", length = 45)
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	@Column(name = "weight_g")
	public BigDecimal getWeight_g() {
		return weight_g;
	}

	public void setWeight_g(BigDecimal weight_g) {
		this.weight_g = weight_g;
	}
	@Column(name = "weight_oz")
	public BigDecimal getWeight_oz() {
		return weight_oz;
	}

	public void setWeight_oz(BigDecimal weight_oz) {
		this.weight_oz = weight_oz;
	}
	
	@Column(name = "picPath", length = 100)
	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	

}
