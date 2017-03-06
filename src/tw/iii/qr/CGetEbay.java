package tw.iii.qr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.OrderStatusCodeType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.PaginationResultType;
import com.ebay.soap.eBLBaseComponents.PaginationType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.SortOrderCodeType;
import com.ebay.soap.eBLBaseComponents.TradingRoleCodeType;

public class CGetEbay {
	public static void main(String[] args) {

	}

	public CGetEbay() {

	}

	public void CGetEbay1() {
		System.out.println("開撈拉拉拉拉");
		Connection conn;
		ArrayList<OrderType> od = new ArrayList<>();
		LinkedList<String> token = null;
		try {
			conn = new DataBaseConn().getConn();
			token = getToken(conn);
			for (int i = 0; i < token.size(); i++) {
				RequestOrder(token.get(i), od);
			}
			displayOrders(od, conn);
			System.out.println("撈完拉拉拉拉");
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void RequestOrder(String token, ArrayList<OrderType> od) throws Exception {
		ApiContext apiContext = getApiContext(token);
		GetOrdersCall apiord = new GetOrdersCall(apiContext);
		apiOrdSetting(apiord);
		apiord.getOrders();
		PaginationResultType rpr = apiord.getReturnedPaginationResult();

		System.out.println("有沒有大於100張訂單啊 : "+apiord.getReturnedHasMoreOrders());
		System.out.println("有幾筆 : "+rpr.getTotalNumberOfEntries());
		PaginationType page = new PaginationType();

		for (int pageNum = 1; pageNum <= rpr.getTotalNumberOfPages(); pageNum++) {
			if (pageNum != 1) {
				page.setPageNumber(pageNum);
				apiord.setPagination(page);
			}
			for (OrderType order : apiord.getOrders()) {
				od.add(order);
			}
//			System.out.println("odin " + od.size());
		}
	}

	private void apiOrdSetting(GetOrdersCall apiord) {
		apiord.setDetailLevel(new DetailLevelCodeType[] { DetailLevelCodeType.RETURN_ALL,
				DetailLevelCodeType.ITEM_RETURN_DESCRIPTION, DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES });
		apiord.setNumberOfDays(30);
		apiord.setPagination(new PaginationType());
		apiord.setSortingOrder(SortOrderCodeType.DESCENDING);
		OrderStatusCodeType status = OrderStatusCodeType.COMPLETED;
		apiord.setOrderStatus(status);
		TradingRoleCodeType role = TradingRoleCodeType.SELLER;
		apiord.setOrderRole(role);
		apiord.setIncludeFinalValueFee(true);
	}

	private void displayOrders(ArrayList<OrderType> od, Connection conn)
			throws IllegalAccessException, ClassNotFoundException, Exception {

		int size = od != null ? od.size() : 0;
//		System.out.println(size);
		for (int i = 0; i < size; i++) {
			OrderType order = od.get(i);

//			System.out.println("**********");
//			System.out.println("訂單狀態: " + order.getCheckoutStatus().getStatus().toString());
//			System.out.println("EbayNo: " + order.getShippingDetails().getSellingManagerSalesRecordNumber().toString());

			// 錢不相同(不知道為啥 有出現就要檢查)
			if (order.getExternalTransaction()[0].getPaymentOrRefundAmount().getValue() != order.getMonetaryDetails()
					.getPayments().getPayment(0).getPaymentAmount().getValue()) {
				System.out.println("不一樣!");
				System.out.println("$getExternalTransaction "
						+ order.getExternalTransaction()[0].getPaymentOrRefundAmount().getValue());
				System.out.println("$MonetaryDetails "
						+ order.getMonetaryDetails().getPayments().getPayment(0).getPaymentAmount().getValue());
			} else {
//				System.out.println("$$" + order.getExternalTransaction()[0].getPaymentOrRefundAmount().getValue());
			}

			// 已完成訂單才進去!
			if ("COMPLETE".equals(order.getCheckoutStatus().getStatus().toString())) {
				// 確認訂單是否存在資料庫
				if (!checkExist(order, conn)) {
					ShippingServiceOptionsType sso = order.getShippingServiceSelected();
					String QR_id = CreateOrderId.generateQR_Id();
					String strSql = "INSERT INTO orders_master (QR_id, order_id, outsideCode, platform, company,"
							+ " eBayAccount, guestAccount, orderdate, payDate, logisticsId, logistics, orderStatus, paypal_id,"
							+ " payment,  paypalFees,ebayFees, totalPrice, currency, ebayPrice, paypalNet, ebayItemNO,"
							+ " paypalmentId, ebayNO, shippingDate)"
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

					PreparedStatement ps = conn.prepareStatement(strSql);
					ps.setString(1, QR_id); // QRID
					ps.setString(2, order.getOrderID()); // ORDERID
					ps.setString(3, order.getExtendedOrderID()); // outsideCode
					ps.setString(4, "ebay"); // 平台
					ps.setString(5, ""); // EBAY帳號所屬公司
					ps.setString(6, order.getSellerUserID()); // EBAY帳號
					ps.setString(7, order.getBuyerUserID()); // 客戶帳號
					ps.setTimestamp(8, new java.sql.Timestamp(order.getCreatedTime().getTimeInMillis())); // OederDate
					ps.setTimestamp(9, (new java.sql.Timestamp(order.getMonetaryDetails().getPayments().getPayment()[0]
							.getPaymentTime().getTimeInMillis()))); // payDate
					ps.setString(10, "logisticsId"); // 其實沒用?
					ps.setString(11, sso.getShippingService().toString()); // 內建選擇的物流
					ps.setString(12, "待處理"); // 訂單初始狀態
					ps.setString(13, order.getMonetaryDetails().getPayments().getPayment()[0].getPayer().getValue()); // 顧客PAYPALID

					// 怪怪的金額! 有問題要重做
					double payment1 = order.getExternalTransaction()[0].getPaymentOrRefundAmount().getValue();
					double payment2 = order.getMonetaryDetails().getPayments().getPayment(0).getPaymentAmount()
							.getValue();
					double truepay = 0;
					if (payment1 == payment2) {
						truepay = payment1;
					} else {
						truepay = payment1 - payment2;
					}
					ps.setDouble(14, truepay); // payment
					ps.setDouble(15, order.getExternalTransaction()[0].getFeeOrCreditAmount().getValue()); // paypalFees
					ps.setDouble(16, order.getTransactionArray().getTransaction()[0].getFinalValueFee().getValue()); // ebayFees
					ps.setDouble(17, truepay); // totalPrice
					ps.setString(18, order.getMonetaryDetails().getPayments().getPayment()[0].getPaymentAmount()
							.getCurrencyID().value()); // 幣別
					ps.setDouble(19, order.getTransactionArray().getTransaction()[0].getTransactionPrice().getValue()); // ebayPrice
					///////////
					System.out.println("幣別: " + order.getMonetaryDetails().getPayments().getPayment()[0]
							.getPaymentAmount().getCurrencyID().value());

					ps.setDouble(20, truepay - order.getExternalTransaction()[0].getFeeOrCreditAmount().getValue()); // paypalNet
					///////////
					ps.setString(21, order.getTransactionArray().getTransaction()[0].getItem().getItemID().toString()); // ebayItemNO
					ps.setString(22, order.getTransactionArray().getTransaction()[0].getTransactionID()); // paypalmentId
					ps.setString(23, order.getShippingDetails().getSellingManagerSalesRecordNumber().toString()); // ebayNO
					ps.setTimestamp(24, new java.sql.Timestamp((long) 0.0)); // shippingDate
																				// 預設0
																				// 1970
					ps.executeUpdate();

					String strSql2 = "INSERT INTO order_recieverinfo (QR_id, order_id, recieverFirstName, recieverLastName,"
							+ " tel1, tel2, address, country, postCode)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

					PreparedStatement ps2 = conn.prepareStatement(strSql2);
					ps2.setString(1, QR_id);
					ps2.setString(2, order.getOrderID());
					ps2.setString(3, order.getShippingAddress().getName());
					ps2.setString(4, "");
					ps2.setString(5, order.getShippingAddress().getPhone());
					ps2.setString(6, order.getShippingAddress().getPhone2());

					String Street1 = order.getShippingAddress().getStreet1();
					String Street2 = order.getShippingAddress().getStreet2();

					if (Street2.equals("null") || Street2.equals(""))
						Street2 = "";

					String CityName = order.getShippingAddress().getCityName();
					String StateOrProvince = order.getShippingAddress().getStateOrProvince();
					String CountryName = order.getShippingAddress().getCountryName();
					String PostalCode = order.getShippingAddress().getPostalCode();

					String Address = Street1 + " " + Street2 + "," + PostalCode + "," + CityName + "," + StateOrProvince
							+ "," + CountryName;
					//System.out.println(Address);

					ps2.setString(7, Address);
					ps2.setString(8, order.getShippingAddress().getCountryName());
					ps2.setString(9, order.getShippingAddress().getPostalCode());
					ps2.executeUpdate();

					for (int l = 0; l < order.getTransactionArray().getTransaction().length; l++) {
						String strSql3 = "INSERT INTO orders_detail (QR_id, order_id, SKU, productName, invoiceName"
								+ ", price, invoicePrice, qty, comment )" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

						PreparedStatement ps3 = conn.prepareStatement(strSql3);
						ps3.setString(1, QR_id);
						ps3.setString(2, order.getOrderID());
						ps3.setString(3, order.getTransactionArray().getTransaction()[l].getItem().getSKU());
						ps3.setString(4, order.getTransactionArray().getTransaction()[l].getItem().getTitle());
						ps3.setString(5, order.getTransactionArray().getTransaction()[l].getItem().getTitle());
						ps3.setDouble(6, order.getTotal().getValue());
						ps3.setDouble(7, order.getTotal().getValue());
						ps3.setInt(8, order.getTransactionArray().getTransaction()[l].getQuantityPurchased());
						ps3.setString(9, order.getBuyerCheckoutMessage());
						ps3.executeUpdate();
					}

					String strSql4 = "INSERT INTO orders_guestinfo (QR_id, order_id, guestFirstName, guestLastName, guestAccount"
							+ ", email)" + " VALUES (?, ?, ?, ?, ?, ?)";

					PreparedStatement ps4 = conn.prepareStatement(strSql4);
					ps4.setString(1, QR_id);
					ps4.setString(2, order.getOrderID());
					ps4.setString(3, order.getTransactionArray().getTransaction()[0].getBuyer().getUserFirstName());
					ps4.setString(4, order.getTransactionArray().getTransaction()[0].getBuyer().getUserLastName());
					ps4.setString(5, order.getBuyerUserID());
					ps4.setString(6, order.getTransactionArray().getTransaction()[0].getBuyer().getEmail());

					ps4.executeUpdate();
					System.out.println("訂單編號:" + order.getOrderID());

				}
			}
//			System.out.println("-----");
		}

	}

	private static ApiContext getApiContext(String token) throws IOException {
		String input;
		ApiContext apiContext = new ApiContext();
		ApiCredential cred = apiContext.getApiCredential();
		cred.seteBayToken(token);
		input = "https://api.ebay.com/wsapi";
		apiContext.setApiServerUrl(input);
		return apiContext;
	}

	private static LinkedList<String> getToken(Connection conn) throws SQLException {

		LinkedList<String> tokens = new LinkedList<String>();

		String strSql = "select ebayToken" + " from ebayaccount" + " where status = 'ON'";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			tokens.add(rs.getString(1));
		}
		return tokens;
	}

	private static LinkedList<String> getOutsideCodeFromDatabase(Connection conn) throws SQLException {

		LinkedList<String> outsideCode = new LinkedList<String>();

		String strSql = "select isnull(outsideCode,'') from orders_master";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			outsideCode.add(rs.getString(1));
		}

		return outsideCode;
	}

	private static boolean checkExist(OrderType orders, Connection conn) throws Exception {

		LinkedList<String> outsideCode = getOutsideCodeFromDatabase(conn);
		for (int i = 0; i < outsideCode.size(); i++) {
			if (outsideCode.get(i).equals(orders.getExtendedOrderID())) {
//				System.out.println("資料庫已有 ");
				return true;
			}
		}
		System.out.println("資料庫沒有 新增!");
		return false;
	}

}
