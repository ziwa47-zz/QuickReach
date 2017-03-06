package tw.iii.qr.IndependentOrder.model.repository;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.Bundles;
import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;
import tw.iii.qr.IndependentOrder.model.entity.Storage;

@Repository
public class BundlesDAO extends AbstractDAO<Bundles> {

	@Override
	protected Class<Bundles> getEntityClass() {
		return Bundles.class;
	}

	@SuppressWarnings("unchecked")
	public  List<Bundles> getAllskuByIod(IordersDetail iod) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("m_SKU", iod.getSku()));

		return criteria.list();
	}
	

}
