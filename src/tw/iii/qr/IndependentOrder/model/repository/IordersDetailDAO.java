package tw.iii.qr.IndependentOrder.model.repository;

import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;

@Repository
public class IordersDetailDAO extends AbstractDAO<IordersDetail> {
	@Override
	protected Class<IordersDetail> getEntityClass() {
		return IordersDetail.class;
	}
	
	
}
