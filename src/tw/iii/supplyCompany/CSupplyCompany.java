package tw.iii.supplyCompany;

public class CSupplyCompany {
	
	private String companyId;
	private String companyName;
	private String tel;
	private String fax;
	private String address;
	private String comment;
	private String item;
	
	public String getCompanyId(){	
		return companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId = companyId;
	}
	public String getCompanyName(){
		return companyName;
	}
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
	
	public String getTel(){
		return tel;
	}
	public void setTel(String tel){
		this.tel = tel;
	}
	
	public String getFax(){
		return fax;
	}
	public void setFax(String fax){
		this.fax = fax;
	}
	
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	

	public String getItem(){
		return item;
	}
	public void setItem(String item) {
        this.item = item;	
	}
	
	
	public String getComment(){
		return comment;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	
	
}
