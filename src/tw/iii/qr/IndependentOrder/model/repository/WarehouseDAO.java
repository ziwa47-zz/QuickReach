package tw.iii.qr.IndependentOrder.model.repository;

import org.springframework.stereotype.Repository;

import tw.iii.qr.IndependentOrder.model.entity.Warehouse;

@Repository
public class WarehouseDAO extends AbstractDAO<Warehouse> {
	@Override
	protected Class<Warehouse> getEntityClass() {
		return Warehouse.class;
	}
	
	
}
