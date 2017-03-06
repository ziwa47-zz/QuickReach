package tw.iii.qr.IndependentOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.PurchaselogMaster;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaselogMasterDAO;

@Service
@Transactional
public class PurchaselogMasterService extends AbstractService<PurchaselogMaster> {
	@Autowired
	private PurchaselogMasterDAO purchaselogMasterDAO;

	
	@Override
	protected AbstractDAO<PurchaselogMaster> getDAO() {
		return purchaselogMasterDAO;
	}



}
