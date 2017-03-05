package tw.iii.qr.IndependentOrder.controller;

import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 * 導頁至查詢訂單頁面<br/>
	 */
	@RequestMapping(value ="QRIndependentOrder/SearchOrder",method=RequestMethod.POST)
	public String redirectSearchOrder(HttpServletRequest request) {
		//System.out.println("RedirectController.redirectProcessing():start");
		try {
			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder(request,"");
			
			HttpSession session = request.getSession();
	        session.setAttribute("IDPSearchOrder", orderList);
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("RedirectController.redirectProcessing():finish");
		return "redirect:/QRIndependentOrder/SearchOrder.jsp?begin=0&end=10";

	}
	
	/**
	 * 導頁至處理中頁面<br/>
	 */
	@RequestMapping(value ="QRIndependentOrder/Processing",method=RequestMethod.POST)
	public String redirectProcessing(HttpServletRequest request) {
		//System.out.println("RedirectController.redirectProcessing():start");
		try {
			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder(request,"處理中");
			
			HttpSession session = request.getSession();
	        session.setAttribute("IDPprocess", orderList);
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("RedirectController.redirectProcessing():finish");
		return "redirect:/QRIndependentOrder/Processing.jsp?begin=0&end=10";

	}

	/**
	 * ajax該商品的倉別跟櫃位資料並回傳<br/>
	 * 
	 */
	@RequestMapping(value ="QRIndependentOrder/Pickup",method=RequestMethod.POST)
	public String redirectPickup(HttpServletRequest request) {

		try {

			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder(request,"揀貨中");
			HttpSession session = request.getSession();
	        session.setAttribute("IDPpicup", orderList);
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/QRIndependentOrder/Pickup.jsp?begin=0&end=10";

	}

	
	@RequestMapping(value ="QRIndependentOrder/UploadTrackingCode",method=RequestMethod.POST)
	public String redirectUploadTrackingCode(HttpServletRequest request) {

		try {

			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder(request,"已出貨");

			HttpSession session = request.getSession();
	        session.setAttribute("IDPUploadTrackingCode", orderList);
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/QRIndependentOrder/UploadTrackingCode.jsp?begin=0&end=10";

	}
	@RequestMapping(value ="QRIndependentOrder/Finished",method=RequestMethod.POST)
	public String redirectFinished(HttpServletRequest request) {

		try {

			LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder(request,"已完成");

			HttpSession session = request.getSession();
	        session.setAttribute("IDPfinished", orderList);
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/QRIndependentOrder/Finished.jsp?begin=0&end=10";

	}
	
	@RequestMapping(value ="QRIndependentOrder/ShipmentRecord",method=RequestMethod.POST)
	public String redirectShipmentRecord(HttpServletRequest request) {

		try {

			//LinkedList<IDPorderAll> orderList = iOrderFactory.getAllIDPorder(request,"已完成");

			HttpSession session = request.getSession();
	        //session.setAttribute("IDPShipmentRecord", orderList);
			request.setAttribute("begin", request.getParameter("begin"));
			request.setAttribute("end", request.getParameter("end"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/QRIndependentOrder/ShipmentRecord.jsp?begin=0&end=10";

	}
	
	
}
