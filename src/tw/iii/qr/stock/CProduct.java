package tw.iii.qr.stock;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class CProduct {
	private String item;
	private String SKU;
	private String P_name;
	private String productType;
	private String productCode;
	private String brand;
	private String subBrand;
	private String spec;
	private String color;
	private int securedQty;
	private double cost;
	private String comment;
	private Date checkupdate;
	private String owner;
	private String EAN;
	private String added;
	private double weight;
	private String packageMatrial;
	private double vilumetricWeight;
	private Date createDate;
	private String picturePath;
	private String volume;
	private int AllStock;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
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
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSubBrand() {
		return subBrand;
	}
	public void setSubBrand(String subBrand) {
		this.subBrand = subBrand;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSecuredQty() {
		return securedQty;
	}
	public void setSecuredQty(int securedQty) {
		this.securedQty = securedQty;
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
	public String getEAN() {
		return EAN;
	}
	public void setEAN(String eAN) {
		EAN = eAN;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getPackageMatrial() {
		return packageMatrial;
	}
	public void setPackageMatrial(String packageMatrial) {
		this.packageMatrial = packageMatrial;
	}
	public double getVilumetricWeight() {
		return vilumetricWeight;
	}
	public void setVilumetricWeight(double vilumetricWeight) {
		this.vilumetricWeight = vilumetricWeight;
	}
	public Date getCreateDate() {
		
		
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getCheckupdate() {
		return checkupdate;
	}
	public void setCheckupdate(Date checkupdate) {
		this.checkupdate = checkupdate;
	}
	public String getAdded() {
		return added;
	}
	public void setAdded(String added) {
		this.added = added;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public int getAllStock() {
		return AllStock;
	}
	public void setAllStock(int allStock) {
		AllStock = allStock;
	}
}
	