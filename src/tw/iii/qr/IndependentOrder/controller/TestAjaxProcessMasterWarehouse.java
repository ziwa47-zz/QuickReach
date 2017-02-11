package tw.iii.qr.IndependentOrder.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.iii.qr.IndependentOrder.service.GuestService;

//


@Controller
public class TestAjaxProcessMasterWarehouse  {
	
	
	@Resource GuestService guestService;
	 
	/**
	 * App登入<br/><br/>
	 *1.檢查欄位是否有值<br/>
	 *2.核對email、password是否正確
	 * 
	 *  
	 * */
	
	@RequestMapping("/test3")
	public @ResponseBody Map<String , Object> ajaxProcessMasterWarehouse(HttpServletRequest request)  {
		Map<String , Object> map = new HashMap<String , Object>();
		System.out.println("asda");
	 
		
		try {
			
			guestService.test();
		} catch (Exception e) {
			
		}
		return map;

	}

}






//
