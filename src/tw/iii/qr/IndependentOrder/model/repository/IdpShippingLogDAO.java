package tw.iii.qr.IndependentOrder.model.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.IdpShippingLog;
import tw.iii.qr.IndependentOrder.model.entity.Storage;

@Repository
public class IdpShippingLogDAO extends AbstractDAO<IdpShippingLog> {

	@Override
	protected Class<IdpShippingLog> getEntityClass() {
		return IdpShippingLog.class;
	}
	

}
