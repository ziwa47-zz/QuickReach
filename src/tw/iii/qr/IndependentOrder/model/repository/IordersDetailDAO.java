package tw.iii.qr.IndependentOrder.model.repository;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;
import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;

@Repository
public class IordersDetailDAO extends AbstractDAO<IordersDetail> {
	@Override
	protected Class<IordersDetail> getEntityClass() {
		return IordersDetail.class;
	}
	
	public List<IordersDetail> selectIordersDetailByQRId(String qrId) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("QR_id", qrId));
		List<IordersDetail> iordersDetails= null;
		
		System.out.println("QR_id = "+qrId);

		for ( int i =0;i< criteria.list().size();i++) {
			System.out.println("criteria.list().size() == 1");
			iordersDetails.add((IordersDetail) criteria.list().get(i));
		} 

		return iordersDetails;
	}
	
}
