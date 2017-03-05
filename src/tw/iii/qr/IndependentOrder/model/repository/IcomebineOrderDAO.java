package tw.iii.qr.IndependentOrder.model.repository;

import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.IcombineOrder;

@Repository
public class IcomebineOrderDAO extends AbstractDAO<IcombineOrder> {

	@Override
	protected Class<IcombineOrder> getEntityClass() {
		return IcombineOrder.class;
	}
	
	
}
