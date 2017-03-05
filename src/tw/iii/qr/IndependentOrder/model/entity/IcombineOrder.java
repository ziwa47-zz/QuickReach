package tw.iii.qr.IndependentOrder.model.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="icombineorder")
public class IcombineOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4052837649732290930L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Id
	@Column(name="m_iqrid")
	private String mIqrid;
	@Column(name="d_qrid")
	private String dQrid;
	private String guestId;
	private String combineDate;

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}

	public void setmIqrid(String mIqrid) {
		this.mIqrid = mIqrid;
	}
	public String getmIqrid() {
		return this.mIqrid;
	}

	public void setdQrid(String dQrid) {
		this.dQrid = dQrid;
	}
	public String getdQrid() {
		return this.dQrid;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public String getGuestId() {
		return this.guestId;
	}

	public void setCombineDate(String combineDate) {
		this.combineDate = combineDate;
	}
	public String getCombineDate() {
		return this.combineDate;
	}

}