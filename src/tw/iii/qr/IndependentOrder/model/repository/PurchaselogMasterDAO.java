package tw.iii.qr.IndependentOrder.model.repository;

import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.PurchaselogMaster;

@Repository
public class PurchaselogMasterDAO extends AbstractDAO<PurchaselogMaster> {
	@Override
	protected Class<PurchaselogMaster> getEntityClass() {
		return PurchaselogMaster.class;
	}

}
