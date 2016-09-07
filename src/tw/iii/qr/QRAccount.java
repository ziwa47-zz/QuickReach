package tw.iii.qr;

public class QRAccount {
	
	private String account ;  //1st
	private String password ;
	private String lastName ;
	private String firstName ;
	private String email ;
	private String enName ;  //6th
	private String signatureImage ;
	private String competenceLV ;
	private int status ; //9th
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getSignatureImage() {
		return signatureImage;
	}
	public void setSignatureImage(String signatureImage) {
		this.signatureImage = signatureImage;
	}
	public String getCompetenceLV() {
		return competenceLV;
	}
	public void setCompetenceLV(String competenceLV) {
		this.competenceLV = competenceLV;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
