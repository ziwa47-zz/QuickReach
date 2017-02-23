package tw.iii.qr.IndependentOrder.model.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;

@Repository
public class IordersDetailDAO extends AbstractDAO<IordersDetail> {
	@Override
	protected Class<IordersDetail> getEntityClass() {
		return IordersDetail.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<IordersDetail> selectIordersDetailByQRId(String qrId) throws Exception {
		System.out.println("IordersDetailDAO.selectIordersDetailByQRId():start");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("qrId", qrId));
		
		System.out.println("IordersDetailDAO.selectIordersDetailByQRId():finish");
		return criteria.list();
	}
	
}
