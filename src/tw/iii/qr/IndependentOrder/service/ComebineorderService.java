package tw.iii.qr.IndependentOrder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.IcombineOrder;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.IcomebineOrderDAO;

@Service
@Transactional
public class ComebineorderService extends AbstractService<IcombineOrder> {
	@Autowired
	private IcomebineOrderDAO icomebineOrderDAO;

	@Override
	protected AbstractDAO<IcombineOrder> getDAO() {
		return icomebineOrderDAO;
	}

	

}
