package tw.iii.qr.IndependentOrder.model.entity;

import java.io.Serializable;
import java.util.List;

public class IDPorderAll  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3392007666605359681L;
	private IordersMaster iordersMaster;
	private IordersDetail iordersDetail;
	private Guest guestInfo;
	private List<IordersDetail> iordersDetails;
	
	
	public IordersMaster getIordersMaster() {
		return iordersMaster;
	}
	public void setIordersMaster(IordersMaster iordersMaster) {
		this.iordersMaster = iordersMaster;
	}
	public List<IordersDetail> getIordersDetails() {
		return iordersDetails;
	}
	public void setIordersDetails(List<IordersDetail> iordersDetails) {
		this.iordersDetails = iordersDetails;
	}
	public Guest getGuestInfo() {
		return guestInfo;
	}
	public void setGuestInfo(Guest guestInfo) {
		this.guestInfo = guestInfo;
	}
	public IordersDetail getIordersDetail() {
		return iordersDetail;
	}
	public void setIordersDetail(IordersDetail iordersDetail) {
		this.iordersDetail = iordersDetail;
	}
	
	
}
