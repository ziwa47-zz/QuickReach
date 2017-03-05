package tw.iii.qr.IndependentOrder.model.repository;

import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.PurchaseLogDetail;

@Repository
public class PurchaseLogDetailDAO extends AbstractDAO<PurchaseLogDetail> {

	@Override
	protected Class<PurchaseLogDetail> getEntityClass() {
		return PurchaseLogDetail.class;
	}
	
	
}
