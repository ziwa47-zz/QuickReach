package tw.iii.qr.stock;

public class CEbay {

	private String ebayId;
	private String ebayToken;
	private String endToken;
	private String paypalAccount;
	private String correspondCompany;
	private String startTime;
	private String lastFixTime;
	private String status;
	private String comment;
	private String systemFeedback;
	private String companyAddress;
	private String companyPost;
	private String companyPhone;
	private String country;
	
	public String getcountry() {
		return country;
	}
	public void setcountry(String country) {
		this.country = country;
	}	
	
	public String getebayId() {
		return ebayId;
	}
	public void setebayId(String ebayId) {
		this.ebayId = ebayId;
	}	
	public String getebayToken() {
		return ebayToken;
	}
	public void setebayToken(String ebayToken) {
		this.ebayToken = ebayToken;
	}
	public String getendToken() {
		return endToken;
	}
	public void setendToken(String endToken) {
		this.endToken= endToken;
	}
	public String getpaypalAccount() {
		return paypalAccount;
	}
	public void setpaypalAccount(String paypalAccount) {
		this.paypalAccount = paypalAccount;
	}
	public String getcompanyPhone() {
		return companyPhone;
	}
	public void setcompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	
	public String getcompanyAddress() {
		return companyAddress;
	}
	public void setcompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}public String getcompanyPost() {
		return companyPost;
	}
	public void setcompanyPost(String companyPost) {
		this.companyPost = companyPost;
	}public String getcorrespondCompany() {
		return correspondCompany;
	}
	public void setcorrespondCompany(String correspondCompany) {
		this.correspondCompany = correspondCompany;
	}
	
	public String getstartTime() {
		return startTime;
	}
	public void setstartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getlastFixTime() {
		return lastFixTime;
	}
	public void setlastFixTime(String lastFixTime) {
		this.lastFixTime = lastFixTime;
	}
	public String getstatus() {
		return status;
	}
	public void setstatus(String status) {
		this.status = status;
	}
	public String getcomment() {
		return comment;
	}
	public void setcomment(String comment) {
		this.comment = comment;
	}
	public String getsystemFeedback() {
		return systemFeedback;
	}
	public void setsystemFeedback(String systemFeedback) {
		this.systemFeedback = systemFeedback;
	}
	
}
