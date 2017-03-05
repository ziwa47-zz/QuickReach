package tw.iii.qr.IndependentOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.IdpShippingLog;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.IdpShippingLogDAO;

@Service
@Transactional
public class ShippinglogService extends AbstractService<IdpShippingLog> {
	@Autowired
	private IdpShippingLogDAO idpShippingLogDAO;

	@Override
	protected AbstractDAO<IdpShippingLog> getDAO() {
		return idpShippingLogDAO;
	}


}
