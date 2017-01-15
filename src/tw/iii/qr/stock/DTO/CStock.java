package tw.iii.qr.stock.DTO;

import java.util.Date;

public class CStock extends CProduct {
	
	private String wareHouse;
	private String position1;
	private String position2;
	private int qty;
	private int qtysold;
	private int qtyremain;
	private String comment;
	private Date lastpurchasedate;
	private String Company;
	
	
	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getPosition1() {
		return position1;
	}
	public void setPosition1(String position1) {
		this.position1 = position1;
	}
	public String getPosition2() {
		return position2;
	}
	public void setPosition2(String position2) {
		this.position2 = position2;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getQtysold() {
		return qtysold;
	}
	public void setQtysold(int qtysold) {
		this.qtysold = qtysold;
	}
	public int getQtyremain() {
		return qtyremain;
	}
	public void setQtyremain(int qtyremain) {
		this.qtyremain = qtyremain;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getLastpurchasedate() {
		return lastpurchasedate;
	}
	public void setLastpurchasedate(Date lastpurchasedate) {
		this.lastpurchasedate = lastpurchasedate;
	}
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}

}