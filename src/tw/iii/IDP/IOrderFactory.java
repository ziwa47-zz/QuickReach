package tw.iii.IDP;

import java.util.LinkedList;
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
import tw.iii.qr.IndependentOrder.model.repository.BundlesDAO;
import tw.iii.qr.IndependentOrder.model.repository.GuestDAO;
import tw.iii.qr.IndependentOrder.model.repository.IcomebineOrderDAO;
import tw.iii.qr.IndependentOrder.model.repository.IdpShippingLogDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersDetailDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersMasterDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaseLogDetailDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaseLogMasterDAO;



@Service
@Transactional
public class IOrderFactory {

	@Autowired	GuestDAO guestDAO;
	@Autowired	IordersDetailDAO iordersDetailDAO;
	@Autowired	IordersMasterDAO iordersMasterDAO;
	@Autowired	IcomebineOrderDAO icomebineOrderDAO;
	@Autowired	PurchaseLogDetailDAO purchaseLogDetailDAO;
	@Autowired	PurchaseLogMasterDAO purchaseLogMasterDAO;
	@Autowired	BundlesDAO bundlesDAO;
	@Autowired	IdpShippingLogDAO idpShippingLogDAO;

	


	public IOrderFactory() {

	}

	/**
	 * 取得該筆訂單主檔、明細、客戶資料<br/>
	 * 1.用Qrid找出來的master 2.用Qrid找出來的Detail(s)
	 */
	public IDPorderAll getIDPorderAllInfo(String Qrid) {
		IDPorderAll idp = new IDPorderAll();
		try {

			// 1.用Qrid找出來的master
			IordersMaster iorderMaster = iordersMasterDAO.selectIordersMasterByQRId(Qrid);
			idp.setIordersMaster(iorderMaster);
			// 2.用Qrid找出來的Detail(s)
			List<IordersDetail> iorderDetailList = iordersDetailDAO.selectIordersDetailByQRId(Qrid);
			idp.setIordersDetails(iorderDetailList);

			if (StringUtils.hasText(iorderMaster.getGuestId())) {
				Guest guest = guestDAO.selectGuestByGuestId(iorderMaster.getGuestId());
				System.out.println("guest:" + BeanUtils.describe(guest));
				idp.setGuestInfo(guest);
			}

			// 取得
			// System.out.println("iorderMaster:"+BeanUtils.describe(iorderMaster));
			// System.out.println("iorderDetailList.size():"+iorderDetailList.size());
			// for(IordersDetail iordersDetail : iorderDetailList) {
			// System.out.println("iordersDetail:"+BeanUtils.describe(iordersDetail));
			// }
			// System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idp;
	}

	public LinkedList<IDPorderAll> getAllIDPorder(String Orderstatus) {
		// Orderstatus 為空值時找全部 否則找該狀態
		LinkedList<IDPorderAll> idps = new LinkedList<IDPorderAll>();
		System.out.println(Orderstatus);
		try {
			List<IordersMaster> iordersMasters = iordersMasterDAO.selectIordersMasterByStatus(Orderstatus);
			
			if(iordersMasters.size()==0) return null;
			
			for (IordersMaster iom : iordersMasters) {
				idps.add(getIDPorderAllInfo(iom.getQrId()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return idps;
	}
	public boolean updateTrackingCode(String qrId,String tcode){
		try {
			IordersMaster iorderMaster = iordersMasterDAO.selectIordersMasterByQRId(qrId);
			iorderMaster.setTrackingCode(tcode);
			iorderMaster.setOrderStatus("已完成");
			checkIsBundleAndDeductStock(qrId);
			iordersMasterDAO.update(iorderMaster);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return true;
	}
	private void checkIsBundleAndDeductStock(String qrId){
		try {
			IordersMaster iorderMaster = iordersMasterDAO.selectIordersMasterByQRId(qrId);
			List<IordersDetail> iorderDetailList = iordersDetailDAO.selectIordersDetailByQRId(qrId);
			
		
			
			for(IordersDetail iod: iorderDetailList){
				if(checkCondiction(iod.getSku())){
					
				}
				
				if(iod.getSku().startsWith("B00")){
					plusBundledeductStock(); //組合包就個別扣
				}else{
					deductStock();
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private boolean checkCondiction(String sku) {
		//TODO checkCondiction
		return false;
	}

	private void deductStock() {
	 //TODO deductStock
		
	}

	private void plusBundledeductStock(){
		//TODO plusBundledeductStock
	} 
	
	
	

}
