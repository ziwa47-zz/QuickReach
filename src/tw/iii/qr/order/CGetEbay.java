package tw.iii.qr.order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.undo.AbstractUndoableEdit;

import java.sql.Date;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.sdk.call.GetSellerTransactionsCall;
import com.ebay.sdk.call.GeteBayOfficialTimeCall;
import com.ebay.sdk.helper.ConsoleUtil;
import com.ebay.sdk.helper.Utils;
import com.ebay.sdk.helper.ui.ControlEntryTypes;
import com.ebay.sdk.util.eBayUtil;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.OrderArrayType;
import com.ebay.soap.eBLBaseComponents.OrderIDArrayType;
import com.ebay.soap.eBLBaseComponents.OrderStatusCodeType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.ShippingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.TradingRoleCodeType;

import tw.iii.autoInsertData.autoInsertData;
import tw.iii.qr.DataBaseConn;

public class CGetEbay {
	public CGetEbay() {
		
	}
	public void CGetEbay2(){
		
		new MyThread().start();
	}
	public void CGetEbay1() {
		// TODO Auto-generated constructor stub
		 try {
//110183287995
	          // Instantiate  ApiContext and initialize with token and Trading API URL
	          ApiContext apiContext = getApiContext();
	          //Create call object and execute the call
	          GeteBayOfficialTimeCall apiCall = new GeteBayOfficialTimeCall(apiContext);
	          Calendar cal = apiCall.geteBayOfficialTime();
	          GetOrdersCall apiord = new GetOrdersCall(apiContext);
	          apiord.setDetailLevel(new DetailLevelCodeType[]{DetailLevelCodeType.RETURN_ALL,DetailLevelCodeType.ITEM_RETURN_DESCRIPTION,DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES});
	          apiord.setNumberOfDays(30);
//	          OrderIDArrayType oiat = new OrderIDArrayType();
//	          String[] orderIds = new String[1];
//	          orderIds[0]= "110183287995";
//	          oiat.setOrderID(orderIds);
	          OrderStatusCodeType status =   OrderStatusCodeType.ALL;
	          apiord.setOrderStatus(status);
	          
	          TradingRoleCodeType role = TradingRoleCodeType.SELLER;
	          apiord.setOrderRole(role);
	      	  apiord.setIncludeFinalValueFee(true);
	          OrderType[] orders = apiord.getOrders();
	          
	          displayOrders(orders);
	          //other(orders);
	          //Handle the result returned
	          System.out.println("Official eBay Time : " + cal.getTime().toString());
	      }  //try
	      catch(Exception e) {
	         
	          e.printStackTrace();
	      }
	}
	
	private static void other(OrderType[] orders) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {

		int size = orders != null ? orders.length : 0;
		 System.out.println(size);
		 for (int i = 0; i < size; i++) {
		  OrderType order = orders[i];
		  Connection conn = new DataBaseConn().getConn();
		  String QR_id = autoInsertData.generateQR_Id();
		  String strSql = "INSERT INTO orders_master (QR_id, order_id, outsideCode, platform, totalPrice, ebayItemNO)"
		  		+ " VALUES (?, ?, ?, ?, ?, ?)";
		  
		  PreparedStatement ps = conn.prepareStatement(strSql);
		  ps.setString(1, QR_id);
		  ps.setString(2, order.getOrderID());
		  ps.setString(3, order.getExtendedOrderID());
		  ps.setString(4, "ebay");
		  ps.setDouble(5, order.getMonetaryDetails().getPayments().getPayment()[0].getPaymentAmount().getValue());
		  ps.setString(6, order.getTransactionArray().getTransaction()[0].getItem().getItemID().toString());
		  int x =ps.executeUpdate();
		  
		  System.out.println("orderId"+order.getOrderID());
		  
		 }
		 
	}

	private static void displayOrders(OrderType[] orders) throws IllegalAccessException, ClassNotFoundException, Exception {
		
		
		int size = orders != null ? orders.length : 0;
		 System.out.println(size);
		 for (int i = 0; i < size; i++) {
		  OrderType order = orders[i];
		  Connection conn = new DataBaseConn().getConn();
		  System.out.println(order.getCheckoutStatus().getStatus().toString());
		  if("COMPLETE".equals(order.getCheckoutStatus().getStatus().toString())){
		  
		  ShippingServiceOptionsType sso = order.getShippingServiceSelected();

		  String QR_id = autoInsertData.generateQR_Id();
		  
		  
		  String strSql = "INSERT INTO orders_master (QR_id, order_id, outsideCode, platform, company,"
		  		+ " eBayAccount, guestAccount, orderDate, payDate, logisticsId, logistics, orderStatus, paypal_id,"
		  		+ " payment, ebayFees, paypalFees, totalPrice, currency, ebayPrice, paypalNet, ebayItemNO,"
		  		+ " paypalmentId, ebayNO)"
		  		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		  
		  PreparedStatement ps = conn.prepareStatement(strSql);
		  ps.setString(1, QR_id);
		  ps.setString(2, order.getOrderID());
		  ps.setString(3, order.getExtendedOrderID());
		  ps.setString(4, "ebay");
		  ps.setString(5, order.getShippingAddress().getCompanyName());
		  ps.setString(6, order.getSellerUserID());
		  ps.setString(7, order.getMonetaryDetails().getPayments().getPayment()[0].getPayer().getValue());
		  ps.setTimestamp(8, new java.sql.Timestamp(order.getCreatedTime().getTimeInMillis()));
		  ps.setTimestamp(9, (new java.sql.Timestamp(order.getMonetaryDetails().getPayments().getPayment()[0].getPaymentTime().getTimeInMillis()))); //payDate
		  ps.setString(10, "logisticsId");
		  ps.setString(11, sso.getShippingService().toString());
		  ps.setString(12, "待處理"); //order.getOrderStatus().toString()
		  ps.setString(13, order.getMonetaryDetails().getPayments().getPayment()[0].getPayer().getValue());
		  ps.setDouble(14, order.getMonetaryDetails().getPayments().getPayment()[0].getPaymentAmount().getValue());
		  ps.setDouble(15, order.getMonetaryDetails().getPayments().getPayment()[0].getFeeOrCreditAmount().getValue());
		  ps.setDouble(16, order.getTransactionArray().getTransaction()[0].getFinalValueFee().getValue());
		  ps.setDouble(17, order.getMonetaryDetails().getPayments().getPayment()[0].getPaymentAmount().getValue());
		  ps.setString(18, order.getMonetaryDetails().getPayments().getPayment()[0].getPaymentAmount().getCurrencyID().value());
		  ps.setDouble(19, order.getTransactionArray().getTransaction()[0].getTransactionPrice().getValue());
		  ps.setDouble(20, order.getMonetaryDetails().getPayments().getPayment()[0].getPaymentAmount().getValue()
				  - order.getMonetaryDetails().getPayments().getPayment()[0].getFeeOrCreditAmount().getValue());
		  ps.setString(21, order.getTransactionArray().getTransaction()[0].getItem().getItemID().toString());
		  ps.setString(22, order.getTransactionArray().getTransaction()[0].getTransactionID());
		  ps.setString(23, order.getShippingDetails().getSellingManagerSalesRecordNumber().toString());
		  int x =ps.executeUpdate();
		  

		  String strSql2 = "INSERT INTO order_recieverinfo (QR_id, order_id, recieverFirstName, recieverLastName,"
		  		+ " tel1, tel2, address, country, postCode)"
		  		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		  PreparedStatement ps2 = conn.prepareStatement(strSql2);
		  ps2.setString(1, QR_id);
		  ps2.setString(2, order.getOrderID());
		  ps2.setString(3, order.getShippingAddress().getFirstName());
		  ps2.setString(4, order.getShippingAddress().getLastName());
		  ps2.setString(5, order.getShippingAddress().getPhone());
		  ps2.setString(6, order.getShippingAddress().getPhone2());
		  ps2.setString(7, order.getShippingAddress().getAddressID()
				  + order.getShippingAddress().getStreet()
				  + order.getShippingAddress().getStreet1()
				  + order.getShippingAddress().getStreet2()
				  + order.getShippingAddress().getCityName()
				  + order.getShippingAddress().getStateOrProvince()
				  + order.getShippingAddress().getCountryName()
				  + order.getShippingAddress().getPostalCode());
		  ps2.setString(8, order.getShippingAddress().getCountryName());
		  ps2.setString(9, order.getShippingAddress().getPostalCode());
		  
		  int y =ps2.executeUpdate();
		  
			  
		 for(int l =0; l<order.getTransactionArray().getTransaction().length;l++){
		  String strSql3 = "INSERT INTO orders_detail (QR_id, order_id, SKU, productName, invoiceName"
		  		+ ", price, invoicePrice, qty, comment )"
		  		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
		  int z =ps3.executeUpdate();
		 }
		 
			  String strSql4 = "INSERT INTO orders_guestinfo (QR_id, order_id, guestFirstName, guestLastName, guestAccount"
			  		+ ", email, tel1, tel2, mobile, birthday, company, address, country, postcode )"
			  		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			  
			  PreparedStatement ps4 = conn.prepareStatement(strSql4);
			  ps4.setString(1, QR_id);
			  ps4.setString(2, order.getOrderID());
			  ps4.setString(3, order.getTransactionArray().getTransaction()[0].getBuyer().getUserFirstName());
			  ps4.setString(4, order.getTransactionArray().getTransaction()[0].getBuyer().getUserLastName());
			  ps4.setString(5, order.getBuyerUserID());
			  ps4.setString(6, order.getTransactionArray().getTransaction()[0].getBuyer().getEmail());
			  ps4.setString(7, null);
			  ps4.setString(8, null);
			  ps4.setString(9, null);
			  ps4.setString(10, null);
			  ps4.setString(11, null);
			  ps4.setString(12, null);
			  ps4.setString(13, null);
			  ps4.setString(14, null);
			  int q =ps4.executeUpdate();
		 
		  System.out.println("訂單編號:"+order.getOrderID());
		  
//		  System.out.println("款項調整:"+order.getAdjustmentAmount().getValue());
//		  System.out.println("款項調整:"+order.getAdjustmentAmount().getCurrencyID());
		  System.out.println("已付款項:"+order.getAmountPaid().getValue());
//		  System.out.println("已付款項:"+order.getTransactionArray().getTransaction()[0].getAmountPaid().getValue());
		  System.out.println("已付款項:"+order.getAmountPaid().getCurrencyID());
//		  System.out.println("款項調整2:"+order.getAmountSaved().getValue());
//		  System.out.println("款項調整2:"+order.getAmountSaved().getCurrencyID());
		  System.out.println("買方留言:"+order.getBuyerCheckoutMessage());
		  
		  System.out.println("付費狀態:"+order.getCheckoutStatus().getEBayPaymentStatus());
		  System.out.println("付費方法:"+order.getCheckoutStatus().getPaymentMethod());
		  System.out.println("狀態:"+order.getCheckoutStatus().getStatus());
		  
		  
		  for(int k = 0 ; k < order.getMonetaryDetails().getPayments().getPayment().length ; k++){
		  System.out.println("MonetaryDetails 賣家帳號:"+order.getMonetaryDetails().getPayments().getPayment()[k].getPayee().getValue());
		  System.out.println("MonetaryDetails FeeOrCreditAmount:"+order.getMonetaryDetails().getPayments().getPayment()[k].getFeeOrCreditAmount().getValue());
		  System.out.println("MonetaryDetails 買家帳號:"+order.getMonetaryDetails().getPayments().getPayment()[k].getPayer().getValue());
		  System.out.println("MonetaryDetails 付費總額:"+order.getMonetaryDetails().getPayments().getPayment()[k].getPaymentAmount().getValue());
//		  System.out.println("MonetaryDetails:"+order.getMonetaryDetails().getPayments().getPayment()[k].getPaymentReferenceID()[0].getType());
		  System.out.println("MonetaryDetails ebay與paypal交易狀態:"+order.getMonetaryDetails().getPayments().getPayment()[k].getPaymentStatus());
		  System.out.println("MonetaryDetails 付費時間:"+order.getMonetaryDetails().getPayments().getPayment()[k].getPaymentTime().getTime());
		  }
		  for(int j = 0 ; j < order.getExternalTransaction().length ; j++){
		  System.out.println("ExternalTransactionStatus:"+order.getExternalTransaction()[j].getExternalTransactionStatus());
		  System.out.println("ExternalTransactionStatus:"+order.getExternalTransaction()[j].getFeeOrCreditAmount().getValue());
		  }
		  System.out.println("訂單狀態:"+order.getOrderStatus());
		  System.out.println("客戶名稱:"+order.getBuyerUserID());
		  System.out.println("外部訂單編號:"+order.getExtendedOrderID());
		  System.out.println("訂單長度?:"+ new Integer(order.getTransactionArray().getTransaction().length).toString());
		  System.out.println("總價:"+ new Double(order.getTotal().getValue()).toString());
		  System.out.println ("訂單建立時間:"+eBayUtil.toAPITimeString(order.getCreatedTime().getTime()));
//		  System.out.println ("訂單付款時間:"+order.getPaidTime().getTime());
		  
		  
		  System.out.println("收件人國家:"+order.getShippingAddress().getCounty());
		  System.out.println("收件人國家名:"+order.getShippingAddress().getCountryName());
		  System.out.println("收件人城市名:"+order.getShippingAddress().getCityName());
		  System.out.println("收件人公司:"+order.getShippingAddress().getCompanyName());
//		  System.out.println("收件人:"+order.getShippingAddress().getInternationalName());
//		  System.out.println("收件人名:"+order.getShippingAddress().getFirstName());
//		  System.out.println("收件人姓:"+order.getShippingAddress().getLastName());
//		  System.out.println("收件人:"+order.getShippingAddress().getInternationalStateAndCity());
//		  System.out.println("收件人:"+order.getShippingAddress().getInternationalStreet());
//		  System.out.println("收件人getName:"+order.getShippingAddress().getName());
//		  System.out.println("收件人getPhone:"+order.getShippingAddress().getPhone());
//		  System.out.println("收件人:"+order.getShippingAddress().getPhone2());
//		  System.out.println("收件人:"+order.getShippingAddress().getPhone2AreaOrCityCode());
//		  System.out.println("收件人:"+order.getShippingAddress().getPhone2LocalNumber());
//		  System.out.println("收件人:"+order.getShippingAddress().getPhoneLocalNumber());
//		  System.out.println("收件人getPostalCode:"+order.getShippingAddress().getPostalCode());
//		  System.out.println("收件人getReferenceID:"+order.getShippingAddress().getReferenceID());
//		  System.out.println("收件人:"+order.getShippingAddress().getStateOrProvince());
		  
		  for(int l =0; l<order.getTransactionArray().getTransaction().length;l++){
		  
		  System.out.println("商品名:"+order.getTransactionArray().getTransaction()[l].getItem().getTitle());
		  System.out.println("SKU:"+order.getTransactionArray().getTransaction()[l].getItem().getSKU());
		  System.out.println("Price:"+order.getTransactionArray().getTransaction()[l].getItem().getCeilingPrice());
		  System.out.println("itemid:"+order.getTransactionArray().getTransaction()[l].getItem().getItemID());
		  
		  }
		  
		  
		  //ShippingServiceOptionsType sso = order.getShippingServiceSelected();
	      if (sso != null) {
	    	  System.out.println ("客戶選擇的物流:"+ sso.getShippingService().toString());
	      }
	      ShippingDetailsType shippingDetails = order.getShippingDetails();
	      Boolean insuranceWanted = shippingDetails.isInsuranceWanted();
	      if (insuranceWanted != null) {
	    	  System.out.println("保險?"+ insuranceWanted.toString());
	      }
	      
	      //MultiLegShipping (Added for SDK 777 Release)
	      System.out.println( Utils.booleanToYesNo(order.isIsMultiLegShipping()));
		  }
		 
		 }
	}
	private static ApiContext getApiContext() throws IOException {
		 String input;
	        ApiContext apiContext = new ApiContext();

	        //set Api Token to access eBay Api Server
	        ApiCredential cred = apiContext.getApiCredential();
	        //input = ConsoleUtil.readString("Enter your eBay Authentication Token: ");
	        input = "AgAAAA**AQAAAA**aAAAAA**RRC7VQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wJmYSjCJCEoQmdj6x9nY+seQ**glsBAA**AAMAAA**ft/IQyOfTIgrx5InrX78iP/vOLfW1VoB6nM/wvyk+jvzxaYYVdPqZa23QAlta+WhCbohr+6dlOm8goKzUmIERvHJ+3Fr3wz2vpIGxqYXz8OyWS97Zml3zJrvCTQNh/VW5nhlZlwlWboqMDaOYy0fovq3E9LZYzjV6k66WJq88yWNnjbrsmjW3PyqGxjq2uBTyfJOhsGJ+EUrdsCwGJ+AF6tzAsLx0H4VSqX8QzEaVUbMPOO0hrpYElD94qz/V8rMMvgkDOIwR7MCiyQagFP6OqaBed70hWE9t39BmdiZopkLpXMTHpz5PqFwqtlN1d33hd5XuTHJeP0PdLZuK8m0dAqfFvzhAZz5qxKXctmwRdEk41xT0C0M+YLg8tl1xucvnhlrSTSbVNLg40uWWnUUnM5MI1ZHMeFpMP467VH6oWENyhjOs08Jidl6Aad2l/DQuRL7tiikaUiOTN8xJmBTtmnq2ouzTkbh07ryT1hX9Z8d/VgI1uGPmCd0vzgEzE6YPoxHRCQT+SLE1xCRxEfxFDqXgcyQNH59oZZT+kPgEc9/R+mbTMvaEjMFCmhkhD/j/YZEiWx1JR0VH23t0/tgv1yXHSxFU7W9MrEEe6e2J3dnZ+AKRo/d+x9vPLJvZ9YSfsV7EBY4osNq8aPA/M8qd6hdc7h5N/DWaJuG2CNU06IU0RZRQnjY+eGSpsQd3klHphWlfBD6NgZxKUR8EyJg5fSRFqEuGTLwizx+84WSgcr746du9AwtsWZxI/nQ0dL0";
	        //input = "AgAAAA**AQAAAA**aAAAAA**T+rgVw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GjC5eBpgWdj6x9nY+seQ**++oDAA**AAMAAA**KrbpmpcD67VY0UO20Q/vbkLrLQChXpvs/xkUUPH7awJn4I72xqw6c/32ET17wrRFhsYsuScNi6NoD5GTFyM6lbJFWHDSZlrlz9+CGC7lPAUJA+72yEZ4ENAwEf1WlVIeKbmzrXYYogYTUpi6LKN6XmET9Lh0Yt6SxsoWMUaPK2wZ8RhUw8gu88PZGZ1wdLsaCmq6ykrYbXIxU7hzpaLH36YlSky4YJjvLrD0wFvMp2DZWbj/pYHWhLaMx9nrLGUl1fDQI/mAQMwi3q9kL2GedSY+cGXzP8ZXyMNrUvFjAh54xK6OX3P8Na2MCmV5gjz95u0Fv+OhgtvPl68/7yq6VZG7P6AghA5Klkn4VrYiMYc5YgpYYH+1+Ws00il4nmNm4nVLh04eFLQepyzi3cPdlakxGFl/fz8rhXd3NCjPPU42HjYWk54JrS8xasbg/4/yz2TOWxA3O9xQTF55bcrEnSMLy4bgPv/5xZC2SKqI70jmddJbne4VDEqRDBbuFugRWo+l6ztRXJU+u5fjtYDVrLFZTIIbVBPeXfaGsa6B8fvNpQk5wlwkpf7g0u0hTXeioDNy0diJzpJ06uNVPMNH70ujUxJX8ogAbUnkKCKdaim8cECfyJ0Ol9aMILrrKNlXczwIGyiBmLWTJZYN0IX0OFGsLurusiLIp6EtOQpgAkg9tTPbFY3BiizrUrggdDQ+cznG4SrRfylaP5qZVgLSkBzDy9tej9rPCwDHU5ubh0Pn5wg1YWatP1ZFUO1Dg9fo";
	        cred.seteBayToken(input); 	

	        //set Api Server Url
	        //input = ConsoleUtil.readString("Enter eBay SOAP server URL (e.g., https://api.ebay.com/wsapi): ");

	        input = "https://api.ebay.com/wsapi";
	        //input = "https://api.sandbox.ebay.com/wsapi";
	        apiContext.setApiServerUrl(input);
			return apiContext;

	        
	}
	 



	private static String checkExistedOrNot(Connection conn) throws SQLException{
		
		String outsideCode = null;
		String strSql = "select top 1 outsideCode from orders_master order by item desc ";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			outsideCode = rs.getString(1);
		}
		return outsideCode;
	}
}
class MyThread extends Thread {
	CGetEbay c = new CGetEbay();
	@Override
	public void run() {
		
		c.CGetEbay1();
		
	}

}
