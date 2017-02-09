package tw.iii.qr.order.DTO;

import java.util.Date;

public class COrderCombineDetail {
	String m_cqrid;
	String d_qrid;
	String d_ebayno;
	String guestAccount;
	Date combineDate;
	
	public String getM_cqrid() {
		return m_cqrid;
	}
	public void setM_cqrid(String m_cqrid) {
		this.m_cqrid = m_cqrid;
	}
	public String getD_qrid() {
		return d_qrid;
	}
	public void setD_qrid(String d_qrid) {
		this.d_qrid = d_qrid;
	}
	public String getD_ebayno() {
		return d_ebayno;
	}
	public void setD_ebayno(String d_ebayno) {
		this.d_ebayno = d_ebayno;
	}
	public String getGuestAccount() {
		return guestAccount;
	}
	public void setGuestAccount(String guestAccount) {
		this.guestAccount = guestAccount;
	}
	public Date getCombineDate() {
		return combineDate;
	}
	public void setCombineDate(Date combineDate) {
		this.combineDate = combineDate;
	}
}
