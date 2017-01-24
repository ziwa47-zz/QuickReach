package tw.iii.qr.order.DTO;

import java.util.Date;

public class COrderCombine {
	
	private String guestAccount ;
	private String QR_Id1;
	private int EbayNO1 ;
	private Date PayTime1 ;
	private String QR_Id2;
	private int EbayNO2 ;
	private Date PayTime2 ;
	public String getGuestAccount() {
		return guestAccount;
	}
	public void setGuestAccount(String guestAccount) {
		this.guestAccount = guestAccount;
	}
	public int getEbayNO1() {
		return EbayNO1;
	}
	public void setEbayNO1(int ebayNO1) {
		EbayNO1 = ebayNO1;
	}
	public Date getPayTime1() {
		return PayTime1;
	}
	public void setPayTime1(Date payTime1) {
		PayTime1 = payTime1;
	}
	public int getEbayNO2() {
		return EbayNO2;
	}
	public void setEbayNO2(int ebayNO2) {
		EbayNO2 = ebayNO2;
	}
	public Date getPayTime2() {
		return PayTime2;
	}
	public void setPayTime2(Date payTime2) {
		PayTime2 = payTime2;
	}
	public String getQR_Id1() {
		return QR_Id1;
	}
	public void setQR_Id1(String qR_Id1) {
		QR_Id1 = qR_Id1;
	}
	public String getQR_Id2() {
		return QR_Id2;
	}
	public void setQR_Id2(String qR_Id2) {
		QR_Id2 = qR_Id2;
	}


}
