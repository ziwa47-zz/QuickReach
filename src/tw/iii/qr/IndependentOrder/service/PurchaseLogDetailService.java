package tw.iii.qr.IndependentOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.PurchaseLogDetail;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaseLogDetailDAO;

@Service
@Transactional
public class PurchaseLogDetailService extends AbstractService<PurchaseLogDetail> {
	@Autowired
	private PurchaseLogDetailDAO purchaseLogDetailDAO;


	@Override
	protected AbstractDAO<PurchaseLogDetail> getDAO() {
		return purchaseLogDetailDAO;
	}


}
