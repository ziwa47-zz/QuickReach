package tw.iii.qr.stock;

import java.util.Date;

public class CStock extends CProduct {
	public CStock() {
	}
	private String wareHouse;
	private String position;
	private int qty;
	private int qtysold;
	private int qtyremain;
	private String comment;
	private Date lastpurchasedate;

	public String getWareHouse() {
		return wareHouse;
	}
	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public Date getLastpurchasedate() {
		return lastpurchasedate;
	}
	public void setLastpurchasedate(Date lastpurchasedate) {
		this.lastpurchasedate = lastpurchasedate;
	}
}
