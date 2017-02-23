package tw.iii.qr.IndependentOrder.model.repository;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.Guest;

@Repository
public class GuestDAO extends AbstractDAO<Guest> {

	@Override
	protected Class<Guest> getEntityClass() {
		return Guest.class;
	}

	public Guest selectGuestByGuestId(String guestId) throws Exception {
		System.out.println("GuestDAO.selectGuestByGuestId():start");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("guestId", guestId));
		Guest guest = null;
		System.out.println("guestId = "+guestId);

		if (criteria.list().size() == 1) {
			System.out.println("criteria.list().size() == 1");
			guest = (Guest) criteria.list().get(0);
			System.out.println(BeanUtils.describe(guest));
		} 
		System.out.println("GuestDAO.selectGuestByGuestId():finish");
		return guest;
	}

}
