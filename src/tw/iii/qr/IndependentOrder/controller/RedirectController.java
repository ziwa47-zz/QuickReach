package tw.iii.qr.IndependentOrder.controller;

import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.iii.IDP.IOrderFactory;
import tw.iii.qr.IndependentOrder.model.entity.IDPorderAll;
import tw.iii.qr.IndependentOrder.service.CompanyService;
import tw.iii.qr.IndependentOrder.service.GuestService;
import tw.iii.qr.IndependentOrder.service.IordersMasterService;
import tw.iii.qr.IndependentOrder.service.StockTransferService;
import tw.iii.qr.IndependentOrder.service.StorageService;
import tw.iii.qr.IndependentOrder.service.WarehouseService;

@Controller
public class RedirectController {

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
	StockTransferService stockTransferService;
	
	@Resource
	IOrderFactory iOrderFactory;

	/**
	 * 導頁至處理中頁面<br/>
	 */
	@RequestMapping("QRIndependentOrder/Processing")
	public String redirectProcessing(HttpServletRequest request) {
		System.out.println("RedirectController.redirectProcessing():start");
		try {
			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder("處理中");

			HttpSession session = request.getSession();
	        session.setAttribute("list", orderList);
			System.out.println("orderList.size():"+orderList.size());
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("RedirectController.redirectProcessing():finish");
		return "redirect:/QRIndependentOrder/Processing.jsp?begin=0&end=10";

	}

	/**
	 * ajax該商品的倉別跟櫃位資料並回傳<br/>
	 * 
	 */
	@RequestMapping("QRIndependentOrder/Pickup")
	public String redirectPickup(HttpServletRequest request) {

		try {

			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder("揀貨中");

			HttpSession session = request.getSession();
	        session.setAttribute("list", orderList);
			System.out.println("orderList.size():"+orderList.size());
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/QRIndependentOrder/Pickup.jsp?begin=0&end=10";

	}

	
	@RequestMapping("QRIndependentOrder/UploadTrackingCode")
	public String redirectUploadTrackingCode(HttpServletRequest request) {

		try {

			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder("已出貨");

			HttpSession session = request.getSession();
	        session.setAttribute("list", orderList);
			System.out.println("orderList.size():"+orderList.size());
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/QRIndependentOrder/UploadTrackingCode.jsp?begin=0&end=10";

	}
	@RequestMapping("QRIndependentOrder/Finished")
	public String redirectFinished(HttpServletRequest request) {

		try {

			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder("已完成");

			HttpSession session = request.getSession();
	        session.setAttribute("list", orderList);
			System.out.println("orderList.size():"+orderList.size());
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/QRIndependentOrder/Finished.jsp?begin=0&end=10";

	}
	
	
}
