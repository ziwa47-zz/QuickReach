package tw.iii.qr.IndependentOrder.service;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.Guest;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.GuestDAO;

@Service
public class GuestService extends AbstractService<Guest> {
	@Autowired
	private GuestDAO guestDAO;

 
	@Override
	protected AbstractDAO<Guest> getDAO() {
		return guestDAO;
	}
 
	

	 
	@Transactional
	public void test() {

		System.out.println("hello");
		try {
			System.out.println(guestDAO.get(1).getAddress());
		} catch (Exception e) {
			System.out.println(e);
			 
			e.printStackTrace();
		}
		
	}

}
