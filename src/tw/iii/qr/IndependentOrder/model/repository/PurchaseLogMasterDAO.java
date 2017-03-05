package tw.iii.qr.IndependentOrder.model.repository;

import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.PurchaseLogMaster;

@Repository
public class PurchaseLogMasterDAO extends AbstractDAO<PurchaseLogMaster> {

	@Override
	protected Class<PurchaseLogMaster> getEntityClass() {
		return PurchaseLogMaster.class;
	}
	
	
}
