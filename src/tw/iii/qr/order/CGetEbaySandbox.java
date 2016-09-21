package tw.iii.qr.order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.sdk.call.GeteBayOfficialTimeCall;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.OrderStatusCodeType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.TradingRoleCodeType;

import tw.iii.autoInsertData.autoInsertData;
import tw.iii.qr.DataBaseConn;

public class CGetEbaySandbox {

	public CGetEbaySandbox(){

	}
	
	public void getEbaySandbox() {
		// TODO Auto-generated method stub
		try {
          ApiContext apiContext = getApiContext();
          GeteBayOfficialTimeCall apiCall = new GeteBayOfficialTimeCall(apiContext);
          Calendar cal = apiCall.geteBayOfficialTime();
          GetOrdersCall apiord = new GetOrdersCall(apiContext);
          apiord.setDetailLevel(new DetailLevelCodeType[]{DetailLevelCodeType.RETURN_ALL,DetailLevelCodeType.ITEM_RETURN_DESCRIPTION,DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES});
          apiord.setNumberOfDays(30);
          OrderStatusCodeType status =   OrderStatusCodeType.ALL;
          apiord.setOrderStatus(status);
          
          TradingRoleCodeType role = TradingRoleCodeType.SELLER;
          apiord.setOrderRole(role);
      	  apiord.setIncludeFinalValueFee(true);
          OrderType[] orders = apiord.getOrders();
          
          other(orders);
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
		  String strSql = "INSERT INTO orders_master (QR_id, order_id, outsideCode, platform, ebayItemNO, orderStatus)"
		  		+ " VALUES (?, ?, ?, ?, ?, ?)";
		  
		  PreparedStatement ps = conn.prepareStatement(strSql);
		  ps.setString(1, QR_id);
		  ps.setString(2, order.getOrderID());
		  ps.setString(3, order.getExtendedOrderID());
		  ps.setString(4, "ebay");
		  ps.setString(5, order.getTransactionArray().getTransaction()[0].getItem().getItemID().toString());
		  ps.setString(6, "待處理");
		  int x =ps.executeUpdate();
		  System.out.println("orderId"+order.getOrderID());
		  
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
		 }
		 
	}
	
	private static ApiContext getApiContext() throws IOException {
		 String input;
	        ApiContext apiContext = new ApiContext();
	        //set Api Token to access eBay Api Server
	        ApiCredential cred = apiContext.getApiCredential();
	        //input = "AgAAAA**AQAAAA**aAAAAA**RRC7VQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wJmYSjCJCEoQmdj6x9nY+seQ**glsBAA**AAMAAA**ft/IQyOfTIgrx5InrX78iP/vOLfW1VoB6nM/wvyk+jvzxaYYVdPqZa23QAlta+WhCbohr+6dlOm8goKzUmIERvHJ+3Fr3wz2vpIGxqYXz8OyWS97Zml3zJrvCTQNh/VW5nhlZlwlWboqMDaOYy0fovq3E9LZYzjV6k66WJq88yWNnjbrsmjW3PyqGxjq2uBTyfJOhsGJ+EUrdsCwGJ+AF6tzAsLx0H4VSqX8QzEaVUbMPOO0hrpYElD94qz/V8rMMvgkDOIwR7MCiyQagFP6OqaBed70hWE9t39BmdiZopkLpXMTHpz5PqFwqtlN1d33hd5XuTHJeP0PdLZuK8m0dAqfFvzhAZz5qxKXctmwRdEk41xT0C0M+YLg8tl1xucvnhlrSTSbVNLg40uWWnUUnM5MI1ZHMeFpMP467VH6oWENyhjOs08Jidl6Aad2l/DQuRL7tiikaUiOTN8xJmBTtmnq2ouzTkbh07ryT1hX9Z8d/VgI1uGPmCd0vzgEzE6YPoxHRCQT+SLE1xCRxEfxFDqXgcyQNH59oZZT+kPgEc9/R+mbTMvaEjMFCmhkhD/j/YZEiWx1JR0VH23t0/tgv1yXHSxFU7W9MrEEe6e2J3dnZ+AKRo/d+x9vPLJvZ9YSfsV7EBY4osNq8aPA/M8qd6hdc7h5N/DWaJuG2CNU06IU0RZRQnjY+eGSpsQd3klHphWlfBD6NgZxKUR8EyJg5fSRFqEuGTLwizx+84WSgcr746du9AwtsWZxI/nQ0dL0";
	        input = "AgAAAA**AQAAAA**aAAAAA**T+rgVw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GjC5eBpgWdj6x9nY+seQ**++oDAA**AAMAAA**KrbpmpcD67VY0UO20Q/vbkLrLQChXpvs/xkUUPH7awJn4I72xqw6c/32ET17wrRFhsYsuScNi6NoD5GTFyM6lbJFWHDSZlrlz9+CGC7lPAUJA+72yEZ4ENAwEf1WlVIeKbmzrXYYogYTUpi6LKN6XmET9Lh0Yt6SxsoWMUaPK2wZ8RhUw8gu88PZGZ1wdLsaCmq6ykrYbXIxU7hzpaLH36YlSky4YJjvLrD0wFvMp2DZWbj/pYHWhLaMx9nrLGUl1fDQI/mAQMwi3q9kL2GedSY+cGXzP8ZXyMNrUvFjAh54xK6OX3P8Na2MCmV5gjz95u0Fv+OhgtvPl68/7yq6VZG7P6AghA5Klkn4VrYiMYc5YgpYYH+1+Ws00il4nmNm4nVLh04eFLQepyzi3cPdlakxGFl/fz8rhXd3NCjPPU42HjYWk54JrS8xasbg/4/yz2TOWxA3O9xQTF55bcrEnSMLy4bgPv/5xZC2SKqI70jmddJbne4VDEqRDBbuFugRWo+l6ztRXJU+u5fjtYDVrLFZTIIbVBPeXfaGsa6B8fvNpQk5wlwkpf7g0u0hTXeioDNy0diJzpJ06uNVPMNH70ujUxJX8ogAbUnkKCKdaim8cECfyJ0Ol9aMILrrKNlXczwIGyiBmLWTJZYN0IX0OFGsLurusiLIp6EtOQpgAkg9tTPbFY3BiizrUrggdDQ+cznG4SrRfylaP5qZVgLSkBzDy9tej9rPCwDHU5ubh0Pn5wg1YWatP1ZFUO1Dg9fo";
	        cred.seteBayToken(input); 	
	        //input = "https://api.ebay.com/wsapi";
	        input = "https://api.sandbox.ebay.com/wsapi";
	        apiContext.setApiServerUrl(input);
			return apiContext;
	}
	
}
