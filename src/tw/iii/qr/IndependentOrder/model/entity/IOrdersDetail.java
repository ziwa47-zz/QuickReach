package tw.iii.qr.IndependentOrder.model.entity;
// default package
// Generated 2017/2/11 �U�� 10:14:00 by Hibernate Tools 4.0.0.Final

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IordersDetail generated by hbm2java
 */
@Entity
@Table(name = "iorders_detail")
public class IOrdersDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3770021725069437286L;
	private Integer id;
	private String tansactionId;
	private String sku;
	private String productName;
	private String invoiceName;
	private BigDecimal price;
	private BigDecimal invoicePrice;
	private Integer qty;
	private String warehouse;
	private String comment;
	private String owner;

	public IOrdersDetail() {
	}

	public IOrdersDetail(Integer id) {
		this.id = id;
	}

	public IOrdersDetail(Integer id, String tansactionId, String sku, String productName, String invoiceName,
			BigDecimal price, BigDecimal invoicePrice, Integer qty, String warehouse, String comment, String owner) {
		this.id = id;
		this.tansactionId = tansactionId;
		this.sku = sku;
		this.productName = productName;
		this.invoiceName = invoiceName;
		this.price = price;
		this.invoicePrice = invoicePrice;
		this.qty = qty;
		this.warehouse = warehouse;
		this.comment = comment;
		this.owner = owner;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "item", column = @Column(name = "Item", nullable = false)),
			@AttributeOverride(name = "qrId", column = @Column(name = "QR_id", nullable = false, length = 100)) })
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Column(name = "invoiceName", length = 1000)
	public String getInvoiceName() {
		return this.invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	@Column(name = "price", precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "invoicePrice", precision = 10)
	public BigDecimal getInvoicePrice() {
		return this.invoicePrice;
	}

	public void setInvoicePrice(BigDecimal invoicePrice) {
		this.invoicePrice = invoicePrice;
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

}