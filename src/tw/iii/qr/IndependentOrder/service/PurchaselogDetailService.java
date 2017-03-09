package tw.iii.qr.IndependentOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.PurchaselogDetail;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaselogDetailDAO;

@Service
@Transactional
public class PurchaselogDetailService extends AbstractService<PurchaselogDetail> {
	@Autowired
	private PurchaselogDetailDAO purchaselogDetailDAO;


	@Override
	protected AbstractDAO<PurchaselogDetail> getDAO() {
		return purchaselogDetailDAO;
	}

	
}
