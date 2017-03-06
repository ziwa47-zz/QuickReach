package tw.iii.IDP;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.PERSIST_STORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import tw.iii.qr.IndependentOrder.model.entity.Bundles;
import tw.iii.qr.IndependentOrder.model.entity.Guest;
import tw.iii.qr.IndependentOrder.model.entity.IDPorderAll;
import tw.iii.qr.IndependentOrder.model.entity.IcombineOrder;
import tw.iii.qr.IndependentOrder.model.entity.IdpShippingLog;
import tw.iii.qr.IndependentOrder.model.entity.IordersDetail;
import tw.iii.qr.IndependentOrder.model.entity.IordersMaster;
import tw.iii.qr.IndependentOrder.model.entity.PurchaselogDetail;
import tw.iii.qr.IndependentOrder.model.entity.PurchaselogMaster;
import tw.iii.qr.IndependentOrder.model.entity.Storage;
import tw.iii.qr.IndependentOrder.model.repository.BundlesDAO;
import tw.iii.qr.IndependentOrder.model.repository.GuestDAO;
import tw.iii.qr.IndependentOrder.model.repository.IcomebineOrderDAO;
import tw.iii.qr.IndependentOrder.model.repository.IdpShippingLogDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersDetailDAO;
import tw.iii.qr.IndependentOrder.model.repository.IordersMasterDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaselogDetailDAO;
import tw.iii.qr.IndependentOrder.model.repository.PurchaselogMasterDAO;
import tw.iii.qr.IndependentOrder.model.repository.StorageDAO;
import tw.iii.qr.IndependentOrder.service.GuestService;
import tw.iii.qr.IndependentOrder.service.IordersDetailService;
import tw.iii.qr.IndependentOrder.service.IordersMasterService;
import tw.iii.qr.IndependentOrder.service.PurchaselogDetailService;
import tw.iii.qr.IndependentOrder.service.PurchaselogMasterService;
import tw.iii.qr.IndependentOrder.service.StorageService;

@Service
@Transactional
public class IOrderFactory {

	@Autowired
	GuestDAO guestDAO;
	@Autowired
	GuestService guestService;
	@Autowired
	IordersDetailDAO iordersDetailDAO;
	@Autowired
	IordersMasterDAO iordersMasterDAO;
	@Autowired
	IordersMasterService iordersMasterService;
	@Autowired
	IordersDetailService iordersDetailService;
	@Autowired
	IcomebineOrderDAO icomebineOrderDAO;
	@Autowired
	PurchaselogDetailDAO purchaseLogDetailDAO;
	@Autowired
	PurchaselogMasterDAO purchaseLogMasterDAO;
	@Autowired
	PurchaselogMasterService purchaselogMasterService;
	@Autowired
	PurchaselogDetailService purchaselogDetailService;
	@Autowired
	BundlesDAO bundlesDAO;
	@Autowired
	IdpShippingLogDAO idpShippingLogDAO;
	@Autowired
	StorageDAO storageDAO;
	@Autowired
	StorageService storageService;

	public IOrderFactory() {

	}

	public LinkedList<IDPorderAll> getAllIDPorder(HttpServletRequest request, String Orderstatus) {
		System.out.println("IOrderFactory.getAllIDPorder():start");
		// 整理查詢參數
		String guestId = request.getParameter("guestId");
		String QR_id = request.getParameter("QR_id");
		String SKU = request.getParameter("SKU");
		String productName = request.getParameter("productName");
		String payDateMin = request.getParameter("payDateMin");
		String payDateMax = request.getParameter("payDateMax");
		String shippingDateMin = request.getParameter("shippingDateMin");
		String shippingDateMax = request.getParameter("shippingDateMax");
		String logistics = request.getParameter("logistics");
		if (logistics == "USPS1")
			logistics = "USPS寄倉";
		if (logistics == "USPS2")
			logistics = "USPS集運";
		Map<String, String> masterselector = new HashMap<>();
		// Master

		masterselector.put("guestId", guestId);
		masterselector.put("QR_id", QR_id);
		masterselector.put("payDateMin", payDateMin);
		masterselector.put("payDateMax", payDateMax);
		masterselector.put("shippingDateMin", shippingDateMin);
		masterselector.put("shippingDateMax", shippingDateMax);
		masterselector.put("logistics", logistics);

		// Detail
		Map<String, String> detailselector = new HashMap<>();
		detailselector.put("SKU", SKU);
		detailselector.put("productName", productName);

		// Orderstatus 為空值時找全部 否則找該狀態
		LinkedList<IDPorderAll> idps = new LinkedList<IDPorderAll>();
		System.out.println(Orderstatus);
		try {
			List<IordersMaster> iordersMasters = iordersMasterDAO.selectIordersMasterByStatus(masterselector,
					Orderstatus);
			if (iordersMasters.size() == 0)
				return null;

			System.out.println("iordersMasters.size():" + iordersMasters.size());
			for (IordersMaster iom : iordersMasters) {
				IDPorderAll idp = getIDPorderAllInfo(detailselector, iom);
				if (idp != null)
					idps.add(idp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("IOrderFactory.getAllIDPorder():finish");

		return idps;
	}

	/**
	 * 取得該筆訂單主檔、明細、客戶資料<br/>
	 * 1.用Qrid找出來的master 2.用Qrid找出來的Detail(s)
	 * 
	 * @param selector
	 * @param detailselector
	 * @param iom
	 */
	public IDPorderAll getIDPorderAllInfo(Map<String, String> detailselector, IordersMaster iom) {
		System.out.println("IOrderFactory.getIDPorderAllInfo():start");
		IDPorderAll idp = new IDPorderAll();
		// 加入master
		idp.setIordersMaster(iom);
		// 加入detail
		List<IordersDetail> iorderDetailList = null;
		try {
			// 如果有查SKU或productName
			if (StringUtils.hasText(detailselector.get("SKU"))
					&& StringUtils.hasText(detailselector.get("productName"))) {
				iorderDetailList = iordersDetailDAO.getSeletedDetail(iom.getQrId(), detailselector);
				if (iorderDetailList == null || iorderDetailList.size() == 0)
					return null;

				idp.setIordersDetails(iorderDetailList);
			} else {
				idp.setIordersDetails(iordersDetailDAO.selectIordersDetailByQRId(iom.getQrId()));
			}

			// 加入guest
			Guest guest = new Guest();
			if (StringUtils.hasText(iom.getGuestId())) {
				guest = guestDAO.selectGuestByGuestId(iom.getGuestId());
				// System.out.println("guest:" + BeanUtils.describe(guest));
				idp.setGuestInfo(guest);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("IOrderFactory.getIDPorderAllInfo():finish");
		return idp;
	}

	public IDPorderAll getIDPorderAllInfobyqrId(String qrId) {
		// System.out.println("IOrderFactory.getIDPorderAllInfobyqrId():start");
		IDPorderAll idp = new IDPorderAll();

		// 加入master
		IordersMaster iom;
		try {
			iom = iordersMasterDAO.selectIordersMasterByQRId(qrId);
			idp.setIordersMaster(iom);
			// 加入detail
			idp.setIordersDetails(iordersDetailDAO.selectIordersDetailByQRId(iom.getQrId()));
			// 加入guest
			Guest guest = new Guest();
			if (StringUtils.hasText(iom.getGuestId())) {
				guest = guestDAO.selectGuestByGuestId(iom.getGuestId());
				// System.out.println("guest:" + BeanUtils.describe(guest));
				idp.setGuestInfo(guest);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// System.out.println("IOrderFactory.getIDPorderAllInfobyqrId():finish");
		return idp;
	}

	public void updateOrder(HttpServletRequest request) throws Exception {
		IDPorderAll order = getIDPorderAllInfobyqrId(request.getParameter("QR_id"));
		IordersMaster master = order.getIordersMaster();
		Guest guest = order.getGuestInfo();
		List<IordersDetail> details = order.getIordersDetails();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		master.setGuestId(request.getParameter("guestId"));
		master.setOrderStatus(request.getParameter("OrderStatus"));
		master.setTrackingCode(request.getParameter("trackingCode"));
		master.setPlatform(request.getParameter("Platform"));
		master.setTransactionId(request.getParameter("transactionId"));
		try {
			if (!isNullorEmpty(request.getParameter("OrderDate"))) {
				master.setOrderDate(format.parse(request.getParameter("OrderDate")));
			}
		} catch (Exception e) {
			master.setOrderDate(format2.parse(request.getParameter("OrderDate")));
		}
		try {
			if (!isNullorEmpty(request.getParameter("PayDate"))) {
				master.setPayDate(format.parse(request.getParameter("PayDate")));
			}

		} catch (Exception e) {
			master.setPayDate(format2.parse(request.getParameter("PayDate")));
		}
		try {
		if (!isNullorEmpty(request.getParameter("ShippingDate"))) {
			master.setShippingDate(format.parse(request.getParameter("ShippingDate")));
		}
		}catch (Exception e) {
			master.setShippingDate(format.parse(request.getParameter("ShippingDate")));
		}
		master.setLogistics(request.getParameter("Logistics"));
		if (!isNullorEmpty(request.getParameter("ShippingFees")))
			master.setShippingFees(new BigDecimal(request.getParameter("ShippingFees")));
		if (!isNullorEmpty(request.getParameter("RefundShippingFees")))
			master.setRefundShippingFees(new BigDecimal(request.getParameter("RefundShippingFees")));
		if (!isNullorEmpty(request.getParameter("OtherFees")))
			master.setOtherFees(new BigDecimal(request.getParameter("OtherFees")));
		if (!isNullorEmpty(request.getParameter("InsuranceTotal")))
			master.setOtherFees(new BigDecimal(request.getParameter("InsuranceTotal")));
		if (!isNullorEmpty(request.getParameter("PaypalFees")))
			master.setPaypalFees(new BigDecimal(request.getParameter("PaypalFees")));
		if (!isNullorEmpty(request.getParameter("TotalPrice")))
			master.setTotalPrice(new BigDecimal(request.getParameter("TotalPrice")));
		iordersMasterService.update(master);

		guest.setName(request.getParameter("name"));
		guest.setEmail(request.getParameter("email"));
		guest.setTel(request.getParameter("tel"));
		guest.setPhone(request.getParameter("phone"));
		guest.setCompany(request.getParameter("company"));
		guest.setAddress(request.getParameter("address"));
		guest.setCountry(request.getParameter("country"));
		guest.setPostcode(request.getParameter("postcode"));
		guest.setGender(request.getParameter("gender"));
		guestService.update(guest);

		LinkedList<String> itemList = new LinkedList<String>(Arrays.asList(request.getParameterValues("item")));
		LinkedList<String> SKUList = new LinkedList<String>(Arrays.asList(request.getParameterValues("SKU")));
		LinkedList<String> priceList = new LinkedList<String>(Arrays.asList(request.getParameterValues("price")));
		LinkedList<String> qtyList = new LinkedList<String>(Arrays.asList(request.getParameterValues("qty")));
		LinkedList<String> weightgList = new LinkedList<String>(Arrays.asList(request.getParameterValues("weight_g")));
		LinkedList<String> weightozList = new LinkedList<String>(
				Arrays.asList(request.getParameterValues("weight_oz")));
		LinkedList<String> commentList = new LinkedList<String>(Arrays.asList(request.getParameterValues("comment")));
		LinkedList<String> warehouseList = new LinkedList<String>(
				Arrays.asList(request.getParameterValues("warehouse")));
		for (int i = 0; i < details.size(); i++) {
			details.get(i).setItem(Integer.valueOf(itemList.get(i)));
			details.get(i).setSku(SKUList.get(i));
			if (!isNullorEmpty(priceList.get(i)))
				details.get(i).setPrice(new BigDecimal(priceList.get(i)));
			details.get(i).setQty(Integer.valueOf(qtyList.get(i)));
			if (!isNullorEmpty(weightgList.get(i)))
				details.get(i).setWeight_g(new BigDecimal(weightgList.get(i)));
			if (!isNullorEmpty(weightozList.get(i)))
				details.get(i).setWeight_oz(new BigDecimal(weightozList.get(i)));
			details.get(i).setComment(commentList.get(i));
			details.get(i).setWarehouse(warehouseList.get(i));
			iordersDetailService.update(details.get(i));
		}

	}

	public void insertOrderDetail(HttpServletRequest request) throws Exception {
		String[] strSKUArray = request.getParameterValues("SKU");
		LinkedList<String> SKUs = new LinkedList<String>(Arrays.asList(strSKUArray));
		String[] strProductName = request.getParameterValues("productName");
		LinkedList<String> productNames = new LinkedList<String>(Arrays.asList(strProductName));
		for (int i = 0; i < SKUs.size(); i++) {
			IordersDetail detail = new IordersDetail();
			// QR_id, SKU, productName, invoiceName, price, invoicePrice, qty
			detail.setQrId(request.getParameter("QR_id"));
			detail.setSku(SKUs.get(i));
			detail.setProductName(productNames.get(i));
			detail.setPrice(new BigDecimal("0.0"));
			detail.setQty(Integer.valueOf("0"));
			detail.setWeight_g(new BigDecimal("0.0"));
			detail.setWeight_oz(new BigDecimal("0"));
			detail.setComment("");
			iordersDetailService.persist(detail);
		}
	}

	public void deleteFromOrderDetail(HttpServletRequest request) {
		try {
			iordersDetailService.deletebyitem((request.getParameter("item")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void checkIsBundleAndDeductStock(IordersMaster corder) {
		try {
			IordersMaster iorderMaster = iordersMasterDAO.selectIordersMasterByQRId(corder.getQrId());
			List<IordersDetail> iorderDetailList = iordersDetailDAO.selectIordersDetailByQRId(iorderMaster.getQrId());

			for (IordersDetail iod : iorderDetailList) {

				if (iod.getSku().startsWith("B00")) {
					plusBundledeductStock(iod); // 組合包就個別扣
				} else {
					deductStock(iod);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deductStock(IordersDetail iod) {
		storageService.deductStock(iod);
	}

	private void plusBundledeductStock(IordersDetail iod) {
		List<Bundles> bundlesku = bundlesDAO.getAllskuByIod(iod);
		for (Bundles b : bundlesku) {
			storageService.deductStock(iod, b);
		}

	}

	private void plusBundleaddStock(IordersDetail iod) {

		List<Bundles> bundlesku = bundlesDAO.getAllskuByIod(iod);
		for (Bundles b : bundlesku) {
			storageService.addStock(iod, b);
		}

	}

	private void addStock(IordersDetail iod) {
		storageService.addStock(iod);
	}

	private static boolean isNullorEmpty(String s) {

		if (s == null || s.length() == 0)
			return true;

		return false;
	}

	public LinkedList<IordersMaster> checkIDPOrderIdOrderStatus(IordersMaster origincdm) throws Exception {
		// 檢查後回傳用
		LinkedList<IordersMaster> CombineOrders = new LinkedList<IordersMaster>();
		IordersMaster iom = new IordersMaster();

		iom = iordersMasterDAO.selectIordersMasterByQRId(origincdm.getQrId());
		if (iom.getQrId().toLowerCase().startsWith("i")) {
			System.out.println("這是合併訂單 " + iom.getQrId());

			List<IcombineOrder> icbo = icomebineOrderDAO.getbymqrId(iom.getQrId());
			IordersMaster icom = new IordersMaster();
			for (IcombineOrder DqrId : icbo) {
				icom = iordersMasterDAO.selectIordersMasterByQRId(DqrId.getdQrid());
				iordersMasterService.updateTrackingCode(icom, origincdm.getTrackingCode());
				CombineOrders.add(iordersMasterDAO.get(icom.getQrId()));
			}
		} else {
			iordersMasterService.updateTrackingCode(iom, origincdm.getTrackingCode());
			CombineOrders.add(iom);

		}

		return CombineOrders;

	}

	public void insertIntoShippingLog(IordersMaster iorder) throws Exception {
		IdpShippingLog idpshippinglog = new IdpShippingLog();
		idpshippinglog.setQrId(iorder.getQrId());
		idpshippinglog.setShippingdate(iorder.getShippingDate());
		idpshippinglog.setTrackingCode(iorder.getTrackingCode());
		idpshippinglog.setStaffName(iorder.getStaffName());
		idpshippinglog.setType("大量出貨");
		List<IordersDetail> iod = iordersDetailDAO.selectIordersDetailByQRId(iorder.getQrId());
		for (IordersDetail od : iod) {
			Storage storage = storageDAO.selectStorageByIorderDetail(od);
			idpshippinglog.setWarehouse(storage.getWarehouse());
			idpshippinglog
					.setWarehouselocation(storage.getWarehousePosition1() + "-" + storage.getWarehousePosition2());
			// idpshippinglogService.persist(idpshippinglog);
		}
	}

	public void insertIntoPurchaseLogFromOrders(IordersMaster iorder) throws Exception {

		PurchaselogMaster purchaselog_master = new PurchaselogMaster();
		purchaselog_master.setPurchaseId(iorder.getQrId());
		purchaselog_master.setDate(iorder.getShippingDate());
		purchaselog_master.setComment(iorder.getGuestId());
		purchaselog_master.setStaffName(iorder.getStaffName());
		purchaselog_master.setStockStatus("2");
		purchaselogMasterService.persist(purchaselog_master);
		// TODO String strSql2 = "insert into purchaselog_detail (purchaseId,
		// SKU, warehouse, qty, price, stockStatus)"
		// + " values( ?, ?, ?, ?, ?, ?)";
		PurchaselogDetail purchaselog_detail = new PurchaselogDetail();
		List<IordersDetail> iod = iordersDetailDAO.selectIordersDetailByQRId(iorder.getQrId());

		for (IordersDetail od : iod) {

			if (od.getSku().startsWith("B00")) {
				List<Bundles> bundlesku = bundlesDAO.getAllskuByIod(od);
				for (Bundles bundles : bundlesku) {
					Storage storage = storageDAO.selectStorageBySku(od, bundles.getpSku());
					purchaselog_detail.setPurchaseId(iorder.getQrId());
					purchaselog_detail.setSku(bundles.getpSku());
					purchaselog_detail.setWarehouse(storage.getWarehouse());
					purchaselog_detail.setWarehousePosition1(storage.getWarehousePosition1());
					purchaselog_detail.setWarehousePosition2(storage.getWarehousePosition2());
					purchaselog_detail.setQty(bundles.getQty() * od.getQty());
					purchaselog_detail.setStockStatus("2");
					purchaselogDetailService.persist(purchaselog_detail);
				}
			} else {
				Storage storage = storageDAO.selectStorageBySku(od, od.getSku());
				purchaselog_detail.setPurchaseId(iorder.getQrId());
				purchaselog_detail.setSku(od.getSku());
				purchaselog_detail.setWarehouse(storage.getWarehouse());
				purchaselog_detail.setWarehousePosition1(storage.getWarehousePosition1());
				purchaselog_detail.setWarehousePosition2(storage.getWarehousePosition2());
				purchaselog_detail.setQty(od.getQty());
				purchaselog_detail.setStockStatus("2");
				purchaselogDetailService.persist(purchaselog_detail);
			}
		}

	}

	public void isBundleAddBackToStock(IordersMaster iorder) throws Exception {

		try {
			IordersMaster iorderMaster = iordersMasterDAO.selectIordersMasterByQRId(iorder.getQrId());
			List<IordersDetail> iorderDetailList = iordersDetailDAO.selectIordersDetailByQRId(iorderMaster.getQrId());

			for (IordersDetail iod : iorderDetailList) {

				if (iod.getSku().startsWith("B00")) {
					plusBundleaddStock(iod); // 組合包就個別扣
				} else {
					addStock(iod);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public LinkedList<String> getWarehouses(HttpServletRequest request, String SKU) throws Exception {
		// System.out.println("SKU:"+SKU);
		LinkedList<String> warehouses = new LinkedList<>();
		List<Storage> storage = storageDAO.selectStorageBySku(SKU);
		for (Storage s : storage) {
			warehouses.add(s.getWarehouse() + "," + s.getWarehousePosition1() + "-" + s.getWarehousePosition2());
		}

		return warehouses;
	}

}
