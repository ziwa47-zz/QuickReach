package tw.iii.qr.IndependentOrder.model.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.Storage;

@Repository
public class StorageDAO extends AbstractDAO<Storage> {

	@Override
	protected Class<Storage> getEntityClass() {
		return Storage.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Storage> selectStorageBySku(String sku) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("sku", sku));
		System.out.println("sku = "+sku);


		return criteria.list();
	}

}
