package tw.iii.qr.IndependentOrder.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.PurchaseLogMaster;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaseLogMasterDAO;

@Service
@Transactional
public class PurchaseLogMasterService extends AbstractService<PurchaseLogMaster> {
	@Resource
	private PurchaseLogMasterDAO purchaseLogMasterDAO;

	@Override
	protected AbstractDAO<PurchaseLogMaster> getDAO() {
		return purchaseLogMasterDAO;
	}

}
