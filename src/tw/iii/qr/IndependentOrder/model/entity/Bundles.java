package tw.iii.qr.IndependentOrder.model.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="bundles")
public class Bundles implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6357484820927372308L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer item;
	@Column(name="m_SKU")
	private String mSku;
	@Column(name="p_SKU")
	private String pSku;
	private Integer qty;

	public void setItem(Integer item) {
		this.item = item;
	}
	public Integer getItem() {
		return this.item;
	}

	public void setmSku(String mSku) {
		this.mSku = mSku;
	}
	public String getmSku() {
		return this.mSku;
	}

	public void setpSku(String pSku) {
		this.pSku = pSku;
	}
	public String getpSku() {
		return this.pSku;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getQty() {
		return this.qty;
	}

}