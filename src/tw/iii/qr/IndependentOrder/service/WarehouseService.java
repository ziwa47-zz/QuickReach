package tw.iii.qr.IndependentOrder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.Company;
import tw.iii.qr.IndependentOrder.model.entity.Warehouse;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.WarehouseDAO;

@Service
public class WarehouseService extends AbstractService<Warehouse> {
	@Autowired
	private WarehouseDAO warehouseDAO;

 
	@Override
	protected AbstractDAO<Warehouse> getDAO() {
		return warehouseDAO;
	}
 
	

	 
	/**查詢倉庫代表跟Name並存成Map<String , Object>*/
	@Transactional
	public Map<String , Object> makeWarehouseMap(Map<String , Object> map) {

		try {
			
			List<Warehouse> dataList 	= warehouseDAO.get();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			if (dataList.size() != 0 ) {
				for(Warehouse warehouse : dataList) {
					dataMap.put(warehouse.getWarehouse(), warehouse.getWarehousename());
				}
			}
			map.put("data", dataMap);
			
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return map;
		
	}
	
	
	
	

	
	

}
