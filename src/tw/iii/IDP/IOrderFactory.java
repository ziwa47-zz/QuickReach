package tw.iii.IDP;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import tw.iii.qr.IndependentOrder.model.entity.Guest;
import tw.iii.qr.IndependentOrder.model.entity.IDPorderAll;
import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;
import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;
import tw.iii.qr.IndependentOrder.model.repository.GuestDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersDetailDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersMasterDAO;

@Service
@Transactional
public class IOrderFactory  {
	
	@Autowired GuestDAO guestDAO;
	@Autowired IordersDetailDAO iordersDetailDAO;
	@Autowired IordersMasterDAO iordersMasterDAO;
//	GuestDAO gs = new GuestDAO();
//	IordersDetailDAO idd= new IordersDetailDAO();
//	IordersMasterDAO imd = new IordersMasterDAO();
	public IOrderFactory(){
		
	}
	
	
	/**取得該筆訂單主檔、明細、客戶資料<br/>
	 * 1.用Qrid找出來的master
	 * 2.用Qrid找出來的Detail(s)
	 * */
	public IDPorderAll getIDPorderAllInfo(String Qrid){ 
		IDPorderAll idp = new IDPorderAll();
		try{
		//1.用Qrid找出來的master
		IordersMaster iorderMaster = iordersMasterDAO.selectIordersMasterByQRId(Qrid);
		
		//2.用Qrid找出來的Detail(s)
		List<IordersDetail> iorderDetailList = iordersDetailDAO.selectIordersDetailByQRId(Qrid);
		
		
		Guest guest = null;
		if(StringUtils.hasText(iorderMaster.getGuestId())) {
		
		 guest = guestDAO.selectGuestByGuestId(iorderMaster.getGuestId());	
		 idp.setGuestInfo(guest);
		 System.out.println("guest:"+BeanUtils.describe(guest));
		 
		}
		
		if(iorderMaster == null || iorderDetailList.size() == 0) {
			return idp;
		}
	
		
		
		//取得
		System.out.println("iorderMaster:"+BeanUtils.describe(iorderMaster));
		System.out.println("iorderDetailList.size():"+iorderDetailList.size());
		
		for(IordersDetail iordersDetail : iorderDetailList) {
			System.out.println("iordersDetail:"+BeanUtils.describe(iordersDetail));
		}
		idp.setIordersMaster(iorderMaster);
		idp.setIordersDetails(iorderDetailList);
		System.out.println("");

		}catch(Exception e){
			e.printStackTrace();
		}
		return idp;
	}

	
}
