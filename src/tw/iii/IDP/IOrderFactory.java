package tw.iii.IDP;
import java.util.List;

import tw.iii.qr.IndependentOrder.model.entity.IDPorderAll;
import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;
import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;
import tw.iii.qr.IndependentOrder.model.repository.GuestDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersDetailDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersMasterDAO;

public class IOrderFactory  {
	GuestDAO gs = new GuestDAO();
	IordersDetailDAO idd= new IordersDetailDAO();
	IordersMasterDAO imd = new IordersMasterDAO();
	public IOrderFactory(){
		
	}
	
	public IDPorderAll getIDPorderAllInfo(String Qrid){
		IDPorderAll idp = new IDPorderAll();
		try{
			//用Qrid找出來的master
		IordersMaster im = imd.selectIordersMasterByQRId(Qrid);
		//用Qrid找出來的Detail(s)
		List<IordersDetail> ids = idd.selectIordersDetailByQRId(Qrid);
		//取得
		idp.setIordersMaster(im);
		idp.setIordersDetails(ids);
		idp.setGuestInfo(gs.selectGuestByGuestId(im.getGuestId()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return idp;
	}
}
