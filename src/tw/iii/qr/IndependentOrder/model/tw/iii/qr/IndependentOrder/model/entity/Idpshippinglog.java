package tw.iii.qr.IndependentOrder.model.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="IDPshippinglog")
public class Idpshippinglog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	private String qrId;
	private Date shippingdate;
	private String trackingCode;
	private String staffName;
	private String type;
	private String warehouse;
	private String warehouselocation;

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}

	public void setQrId(String qrId) {
		this.qrId = qrId;
	}
	public String getQrId() {
		return this.qrId;
	}

	public void setShippingdate(Date shippingdate) {
		this.shippingdate = shippingdate;
	}
	public Date getShippingdate() {
		return this.shippingdate;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}
	public String getTrackingCode() {
		return this.trackingCode;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffName() {
		return this.staffName;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouselocation(String warehouselocation) {
		this.warehouselocation = warehouselocation;
	}
	public String getWarehouselocation() {
		return this.warehouselocation;
	}

}