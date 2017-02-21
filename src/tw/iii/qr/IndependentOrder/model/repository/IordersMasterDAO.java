package tw.iii.qr.IndependentOrder.model.repository;


import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.Guest;
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
	
	public IordersMaster selectIordersMasterByQRId(String qrId) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("QR_id", qrId));
		IordersMaster iordersMaster = null;
		System.out.println("QR_id = "+qrId);

		if (criteria.list().size() == 1) {
			System.out.println("criteria.list().size() == 1");
			iordersMaster = (IordersMaster) criteria.list().get(0);
			System.out.println(BeanUtils.describe(iordersMaster));
		} 

		return iordersMaster;
	}
	
}
