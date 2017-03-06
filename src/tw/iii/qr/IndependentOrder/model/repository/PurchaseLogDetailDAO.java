package tw.iii.qr.IndependentOrder.model.repository;

import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.PurchaselogDetail;

@Repository
public class PurchaselogDetailDAO extends AbstractDAO<PurchaselogDetail> {
	@Override
	protected Class<PurchaselogDetail> getEntityClass() {
		return PurchaselogDetail.class;
	}


}
