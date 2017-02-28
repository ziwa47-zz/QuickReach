package tw.iii.qr.IndependentOrder.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.iii.qr.IndependentOrder.service.CompanyService;
import tw.iii.qr.IndependentOrder.service.GuestService;
import tw.iii.qr.IndependentOrder.service.IordersMasterService;
import tw.iii.qr.IndependentOrder.service.StockTransferService;
import tw.iii.qr.IndependentOrder.service.StorageService;
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
	@Resource
	StorageService storageService;
	
	@Resource
	StockTransferService stockTransferService; //git test
	
	/**ajax查詢顧客資料並回傳<br/>*/
	@RequestMapping("/ajax/getGetGuest")
	public @ResponseBody Map<String, Object> ajaxGetGuest(HttpServletRequest request) {
		System.out.println("ajaxGetGuest:start");
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			String guestId = request.getParameter("guestId");
			System.out.println("guestId:"+guestId);
			map = guestService.selectGuestByGuestId(guestId );


		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ajaxGetGuest:finish");
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
	
	
	/**
	 * ajax該商品的倉別跟櫃位資料並回傳<br/>
	 * 
	 * */
	@RequestMapping("/ajax/getStorageWarehouse")
	public @ResponseBody Map<String, Object> ajaxGetWarehouseList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			String sku = request.getParameter("autoCompleteNumber");
			System.out.println("sku"+sku);
			storageService.makeWarehouseAndPosisation(map, sku);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	} 
	
	/**ajax查詢倉庫資料並回傳<br/>
	 * 
	 * */
	@RequestMapping("/ajax/getWarehouseList")
	public @ResponseBody Map<String, Object> ajaxGetWarehouse(HttpServletRequest request) {
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
	
	
	/**ajax查詢DB今天該倉別的轉倉單count數<br/>
	 * 胖神叫我做的<br/>
	 * 
	 * */
	@RequestMapping("/ajax/getStockTransferMasterCount")
	public @ResponseBody Map<String, Object> ajaxGetStockTransferMasterCount(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {

			//String warehouse = request.getParameter("warehouse");
			String warehouse = "US";
			if(StringUtils.hasText(warehouse)) {
				String stockTransferId = stockTransferService.makeStockTransferMasterId(warehouse);
				map.put("data", stockTransferId);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
	
	/**ajax查詢顧客下拉選單for JQuery autocomplete<br/>*/
	@RequestMapping("/ajax/getGuestList")
	public @ResponseBody Map<String, Object> ajaxGuestList(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			
			String guestId = request.getParameter("term");
			System.out.println("guestId="+guestId);
			map = guestService.selectGuestForJQuery(guestId );


		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
	
}


