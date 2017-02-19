package tw.iii.qr.IndependentOrder.model.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.Company;
import tw.iii.qr.IndependentOrder.model.entity.Guest;

@Repository
public class CompanyDAO extends AbstractDAO<Company> {
	@Override
	protected Class<Company> getEntityClass() {
		return Company.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Guest> selectTodayCount(String email) throws Exception{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("email", email));
		return criteria.list();
	}
}
