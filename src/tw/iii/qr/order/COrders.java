package tw.iii.qr.order;

import java.util.LinkedList;

public class COrders {
	COrderMaster COrderMaster = new COrderMaster();
	COrderDetail COrderDetailSingle = new COrderDetail();
	LinkedList<COrderDetail> COrderDetail = new LinkedList<COrderDetail>();
	COrderGuestInfo COrderGuestInfo = new COrderGuestInfo();
	COrderReciever COrderReciever = new COrderReciever();
	
	public COrderMaster getCOrderMaster() {
		return COrderMaster;
	}
	public void setCOrderMaster(COrderMaster cOrderMaster) {
		COrderMaster = cOrderMaster;
	}
	
	public LinkedList<COrderDetail> getCOrderDetail() {
		return COrderDetail;
	}
	public void setCOrderDetail(LinkedList<COrderDetail> cOrderDetail) {
		COrderDetail = cOrderDetail;
	}
	public COrderGuestInfo getCOrderGuestInfo() {
		return COrderGuestInfo;
	}
	public void setCOrderGuestInfo(COrderGuestInfo cOrderGuestInfo) {
		COrderGuestInfo = cOrderGuestInfo;
	}
	public COrderReciever getCOrderReciever() {
		return COrderReciever;
	}
	public void setCOrderReciever(COrderReciever cOrderReciever) {
		COrderReciever = cOrderReciever;
	}
	public COrderDetail getCOrderDetailSingle() {
		return COrderDetailSingle;
	}
	public void setCOrderDetailSingle(COrderDetail cOrderDetailSingle) {
		COrderDetailSingle = cOrderDetailSingle;
	}
	
}

