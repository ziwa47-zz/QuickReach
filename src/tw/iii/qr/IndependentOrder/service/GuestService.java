package tw.iii.qr.IndependentOrder.service;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.Guest;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.GuestDAO;

@Service
@Transactional
public class GuestService extends AbstractService<Guest> {

	@Autowired
	private GuestDAO guestDAO;

	@Override
	protected AbstractDAO<Guest> getDAO() {
		return guestDAO;
	}

	/**儲存或更新客戶資料<br/>
	 * 1.若guest.getId() != null ，更新客戶資料<br/>
	 * 2.否則新增客戶資料
	 * @param  
	 *  */
	public int iorderGuest(Guest guest) throws Exception {
		System.out.println("iorderGuest:start");
		
		if (guest.getId() != null) {
			System.out.println("tempGuest.getId() != null");
			update(guest);
		} else {
			System.out.println("tempGuest.getId() == null");
			persist(guest);
		}

		
		System.out.println("iorderGuest:finish");
		return 0;

	}
	
	
	public Map<String , Object> selectGuestByGuestId(Map<String , Object> map,String guestId) throws Exception {
		System.out.println("selectGuestByGuestId:start");
		
		Guest guest = guestDAO.selectGuestByGuestId(guestId);
		if ( guest != null) {
			System.out.println("guest != null");
					map.put("guestId"				, guest.getId());
					map.put("guestGuestId"			, guest.getGuestId());
					map.put("guestName"				, guest.getName());
					map.put("guestCompany"			, guest.getCompany());
					map.put("guestPlatformAccount"	, guest.getPlatformAccount());
					map.put("guestEmail"			, guest.getEmail());
					map.put("guestCountry"			, guest.getCountry());
					map.put("guestTel"				, guest.getTel());
					map.put("guestAddress"			, guest.getAddress());
					map.put("guestComment"			, guest.getComment());
					map.put("guestPhone"			, guest.getPhone());
					map.put("guestPostcode"			, guest.getPostcode());
					map.put("guestBirthday"			, guest.getBirthday());
					map.put("guestGender"			, guest.getGender());
					System.out.println(BeanUtils.describe(guest));
		} else {
			
			
		}
		
	 
		System.out.println("selectGuestByGuestId:finish");
		return map;

	}
	
	
	
	

}
