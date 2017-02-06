package tw.iii.qr.order.DTO;

public class COrderReciever {
	private String QR_id;
	private String order_id;
	private String recieverFirstName;
	private String recieverLastName;
	private String tel1;
	private String tel2;
	private String address;
	private String country;
	private String postCode;
	
	public String getQR_id() {
		return QR_id;
	}
	public void setQR_id(String qR_id) {
		QR_id = qR_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getRecieverFirstName() {
		return recieverFirstName;
	}
	public void setRecieverFirstName(String recieverFirstName) {
		this.recieverFirstName = recieverFirstName;
	}
	public String getRecieverLastName() {
		return recieverLastName;
	}
	public void setRecieverLastName(String recieverLastName) {
		this.recieverLastName = recieverLastName;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
