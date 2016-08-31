package tw.iii.qr.order;


public class COrders {
	COrderMaster COrderMaster = new COrderMaster();
	COrderDetail COrderDetail = new COrderDetail();
	COrderGuestInfo COrderGuestInfo = new COrderGuestInfo();
	COrderReciever COrderReciever = new COrderReciever();
	public COrderMaster getCOrderMaster() {
		return COrderMaster;
	}
	public void setCOrderMaster(COrderMaster cOrderMaster) {
		COrderMaster = cOrderMaster;
	}
	public COrderDetail getCOrderDetail() {
		return COrderDetail;
	}
	public void setCOrderDetail(COrderDetail cOrderDetail) {
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
	
}

