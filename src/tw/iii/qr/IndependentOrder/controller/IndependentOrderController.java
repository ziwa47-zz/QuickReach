package tw.iii.qr.IndependentOrder.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tw.iii.IDP.IOrderFactory;
import tw.iii.qr.IndependentOrder.model.entity.Guest;
import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;
import tw.iii.qr.IndependentOrder.model.repository.StorageDAO;
import tw.iii.qr.IndependentOrder.service.CompanyService;
import tw.iii.qr.IndependentOrder.service.GuestService;
import tw.iii.qr.IndependentOrder.service.IordersDetailService;
import tw.iii.qr.IndependentOrder.service.IordersMasterService;
import tw.iii.qr.IndependentOrder.service.QueueEmailService;
import tw.iii.qr.IndependentOrder.service.WarehouseService;

@Controller
public class IndependentOrderController {

	@Autowired
	IOrderFactory iOrderFactory;

	@Resource
	StorageDAO storageDAO;
	@Resource
	CompanyService companyService;
	@Resource
	WarehouseService warehouseService;
	@Resource
	IordersMasterService iordersMasterService;
	@Resource
	GuestService guestService;
	@Resource
	IordersDetailService iordersDetailService;
	@Resource
	QueueEmailService queueEmailService;

	private static final String ORDER_STATUS_PROCESSING = "處理中";

	/**
	 * 處理獨立訂單<br/>
	 * 1.儲存或更新guest<br/>
	 * 2.儲存iorders_master<br/>
	 * 3.儲存iorders_detail<br/>
	 * 
	 */
	@RequestMapping(value = "/independentOrder", method = RequestMethod.POST)
	public String processIndependentOrder(HttpServletRequest request) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// for guest
		String id 						= request.getParameter("id");
		String guestId 				= request.getParameter("guestId");
		String name 				= request.getParameter("name");
		String company 			= request.getParameter("company");
		String platformAccount 	= request.getParameter("platformAccount");
		String email 					= request.getParameter("email");
		String country 				= request.getParameter("country");
		String tel 					= request.getParameter("tel");
		String address 				= request.getParameter("address");
		String guestComment		= request.getParameter("guestComment");
		String phone 				= request.getParameter("phone");
		String postcode 			= request.getParameter("postcode");
		String birthday 				= request.getParameter("birthday");
		String gender 				= request.getParameter("gender");

		// for iorders_master
		String qrId 					= request.getParameter("iorderMasterId");
		String transactionId 		= request.getParameter("transactionId");
		// String date = request.getParameter("orderDate");
		// System.out.println(date);
		String staffName 			= request.getParameter("staffName");
		String orderGuest 			= request.getParameter("guestId");
		String platform 			= request.getParameter("platform");
		String paypalFees 			= request.getParameter("paypalFees");
		String paypalPrice 			= request.getParameter("paypalPrice");
		String paypalNet 			= request.getParameter("paypalNet");
		String logistics 				= request.getParameter("logistics");
		String currency 			= request.getParameter("currency");
		String masterComment 	= request.getParameter("masterComment");
		String invoiceName 		= request.getParameter("invoiceName");
		String invoicePrice 			= request.getParameter("invoicePrice");
		String totalPrice 			= request.getParameter("totalPrice");

		// for iorders_detail

		try {

			// 1.儲存或更新guest

			Guest guest = new Guest();

			if (StringUtils.hasText(id)) {
				System.out.println("id:" + id);
				guest.setId(Integer.valueOf(id));
			}

			guest.setGuestId(guestId);
			guest.setName(name);
			guest.setCompany(company);
			guest.setPlatformAccount(platformAccount);
			guest.setEmail(email);
			guest.setCountry(country);
			guest.setTel(tel);
			guest.setAddress(address);
			guest.setComment(guestComment);
			guest.setPhone(phone);
			guest.setPostcode(postcode);
			guest.setBirthday(dateFormat.parse(birthday));
			guest.setGender(gender);

			System.out.println(BeanUtils.describe(guest));

			guestService.iorderGuest(guest);

			// 2.儲存iorders_master

			IordersMaster iorderMaster = new IordersMaster();

			iorderMaster.setQrId(qrId);
			iorderMaster.setTransactionId(transactionId);
			iorderMaster.setOrderDate(new Date());
			iorderMaster.setStaffName(staffName);
			iorderMaster.setGuestId(orderGuest);
			iorderMaster.setPlatform(platform);
			if (paypalFees != null && paypalFees != "")
				iorderMaster.setPaypalFees(new BigDecimal(paypalFees));
			iorderMaster.setLogistics(logistics);
			iorderMaster.setCurrency(currency);
			iorderMaster.setComment(masterComment);
			if (paypalPrice != null && paypalPrice != "")
				iorderMaster.setPaypalPrice(new BigDecimal(paypalPrice));
			iorderMaster.setOrderStatus(ORDER_STATUS_PROCESSING);
			if (paypalNet != null && paypalNet != "")
				iorderMaster.setPaypalNet(new BigDecimal(paypalNet));
			iorderMaster.setInvoiceName(invoiceName);
			if (invoicePrice != null && invoicePrice != "")
				iorderMaster.setInvoicePrice(new BigDecimal(invoicePrice));
			if (totalPrice != null && totalPrice != "")
				iorderMaster.setTotalPrice(new BigDecimal(totalPrice));

			iordersMasterService.persist(iorderMaster);

			System.out.println(BeanUtils.describe(iorderMaster));

			// 3.儲存iorders_detail

			iordersDetailService.processPurchaseDetail(request);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/QRIndependentOrder/Processing";

	}

}
