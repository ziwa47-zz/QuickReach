package tw.iii.qr.IndependentOrder.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="purchaselog_master")
public class PurchaselogMaster implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 828576945726355502L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String purchaseId;
	private Date date;
	private String companyId;
	private String companyName;
	private String staffName;
	private String comment;
	private String stockStatus;
	private String warehouse;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseId() {
		return this.purchaseId;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return this.date;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyName() {
		return this.companyName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffName() {
		return this.staffName;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return this.comment;
	}

	public void setStockStatus(String stockStatus) {
		this.stockStatus = stockStatus;
	}
	public String getStockStatus() {
		return this.stockStatus;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getWarehouse() {
		return this.warehouse;
	}

}
