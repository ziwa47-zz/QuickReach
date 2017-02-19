package tw.iii.qr.IndependentOrder.model.repository;


import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;

@Repository
public class IordersMasterDAO extends AbstractDAO<IordersMaster> {
	@Override
	protected Class<IordersMaster> getEntityClass() {
		return IordersMaster.class;
	}
	
	public int selectTodayCount(Date today) throws Exception{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.ge("orderDate", today));
		return criteria.list().size();
	}
	
	
}
