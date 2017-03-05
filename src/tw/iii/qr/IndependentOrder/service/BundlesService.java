package tw.iii.qr.IndependentOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.Bundles;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.BundlesDAO;

@Service
@Transactional
public class BundlesService extends AbstractService<Bundles> {
	@Autowired
	private BundlesDAO bundlesDAO;

	@Override
	protected AbstractDAO<Bundles> getDAO() {
		return bundlesDAO;
	}

	
	
}
