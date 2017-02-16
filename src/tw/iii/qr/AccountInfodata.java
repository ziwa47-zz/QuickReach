package tw.iii.qr;

public class AccountInfodata {
	private String account;
	private String password;
	private String lastname;
	private String firstname;
	private String staffname;
	private String competenceLV;
	private int status;

	public AccountInfodata() {
	}

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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
}