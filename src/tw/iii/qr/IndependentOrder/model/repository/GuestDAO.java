package tw.iii.qr.IndependentOrder.model.repository;

import java.util.List;

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
	
	/**查詢以此email註冊之帳號
	 * 
	 * 
	 * 
	 **/
	@SuppressWarnings("unchecked")
	public List<Guest> selectAppUserByEmail(String email) throws Exception{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("email", email));
		return criteria.list();
	}
}
