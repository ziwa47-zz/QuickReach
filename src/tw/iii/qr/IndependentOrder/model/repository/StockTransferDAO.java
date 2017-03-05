package tw.iii.qr.IndependentOrder.model.repository;


import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.StockTransferlogMaster;

@Repository
public class StockTransferDAO extends AbstractDAO<StockTransferlogMaster> { 
	@Override
	protected Class<StockTransferlogMaster> getEntityClass() {
		return StockTransferlogMaster.class;
	}
	
	public int selectTodayCount(Date today,String oldWarehouse) throws Exception{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.ge("date", today));
		criteria.add(Restrictions.eq("oldWarehouse", oldWarehouse));
		return criteria.list().size();
	}
	
	
}
