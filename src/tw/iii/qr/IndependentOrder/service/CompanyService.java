package tw.iii.qr.IndependentOrder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.iii.qr.IndependentOrder.model.entity.Company;
import tw.iii.qr.IndependentOrder.model.repository.AbstractDAO;
import tw.iii.qr.IndependentOrder.model.repository.CompanyDAO;

@Service
public class CompanyService extends AbstractService<Company> {
	@Autowired
	private CompanyDAO companyDAO;

 
	@Override
	protected AbstractDAO<Company> getDAO() {
		return companyDAO;
	}
	 
	
	/**查詢公司Id跟Name並存成Map<String , Object>*/
	@Transactional
	public Map<String , Object> makeCompanyMap(Map<String , Object> map) {

		try {
			
			List<Company> dataList = companyDAO.get();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			if (dataList.size() != 0 ) {
				for(Company company : dataList) {
					dataMap.put(company.getCId(), company.getCName());
					 
				}
			}
			map.put("data", dataMap);
			
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return map;
		
	}
	
	
	
	

}
