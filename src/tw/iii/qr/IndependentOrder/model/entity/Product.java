package tw.iii.qr.IndependentOrder.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="product")
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8439910715314563906L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer item;
	@Id
	@Column(name="SKU")
	private String sku;
	private String owner;
	private String productType;
	private String brand;
	private String subBrand;
	@Column(name="EAN")
	private String ean;
	private String productCode;
	@Column(name="P_name")
	private String pName;
	private String spec;
	private String color;
	private Integer securedQty;
	private Double cost;
	private String comment;
	private Date checkupdate;
	private String added;
	private Double weight;
	private String packageMatrial;
	private String volume;
	private Double vilumetricWeight;
	private Date createDate;
	private String picturePath;

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

	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return this.owner;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductType() {
		return this.productType;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBrand() {
		return this.brand;
	}

	public void setSubBrand(String subBrand) {
		this.subBrand = subBrand;
	}
	public String getSubBrand() {
		return this.subBrand;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}
	public String getEan() {
		return this.ean;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCode() {
		return this.productCode;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpName() {
		return this.pName;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getSpec() {
		return this.spec;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public String getColor() {
		return this.color;
	}

	public void setSecuredQty(Integer securedQty) {
		this.securedQty = securedQty;
	}
	public Integer getSecuredQty() {
		return this.securedQty;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getCost() {
		return this.cost;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return this.comment;
	}

	public void setCheckupdate(Date checkupdate) {
		this.checkupdate = checkupdate;
	}
	public Date getCheckupdate() {
		return this.checkupdate;
	}

	public void setAdded(String added) {
		this.added = added;
	}
	public String getAdded() {
		return this.added;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getWeight() {
		return this.weight;
	}

	public void setPackageMatrial(String packageMatrial) {
		this.packageMatrial = packageMatrial;
	}
	public String getPackageMatrial() {
		return this.packageMatrial;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getVolume() {
		return this.volume;
	}

	public void setVilumetricWeight(Double vilumetricWeight) {
		this.vilumetricWeight = vilumetricWeight;
	}
	public Double getVilumetricWeight() {
		return this.vilumetricWeight;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getPicturePath() {
		return this.picturePath;
	}

}