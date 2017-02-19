package tw.iii.qr.IndependentOrder.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.iii.qr.IndependentOrder.service.CompanyService;
import tw.iii.qr.IndependentOrder.service.GuestService;
import tw.iii.qr.IndependentOrder.service.IordersMasterService;
import tw.iii.qr.IndependentOrder.service.WarehouseService;

@Controller
public class AjaxGetData {

	@Resource
	CompanyService companyService;
	
	@Resource
	WarehouseService warehouseService;
		
	@Resource
	IordersMasterService iordersMasterService;
	@Resource
	GuestService guestService;
	
	/**ajax查詢顧客資料並回傳<br/>*/
	@RequestMapping("/ajax/getGetGuest")
	public @ResponseBody Map<String, Object> ajaxGetGuest(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			String guestId = request.getParameter("guestId");
			map = guestService.selectGuestByGuestId(map, guestId );


		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
	
	
	/**ajax查詢公司資料並回傳<br/>*/
	@RequestMapping("/ajax/getCompanyList")
	public @ResponseBody Map<String, Object> ajaxGetCompanyList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			companyService.makeCompanyMap(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
	
	
	/**ajax查詢倉庫資料並回傳<br/>*/
	@RequestMapping("/ajax/getWarehouseList")
	public @ResponseBody Map<String, Object> ajaxGetWarehouseList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			warehouseService.makeWarehouseMap(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}

	
	
	/**ajax查詢DB獨立訂單count數<br/>*/
	@RequestMapping("/ajax/getIordersMasterCount")
	public @ResponseBody Map<String, Object> ajaxGetIOrdersMasterCount(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			String iordersMasterId = iordersMasterService.makeIordersMasterId();
			map.put("data", iordersMasterId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
}


