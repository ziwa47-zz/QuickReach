package tw.iii.qr.order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiCall;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.CompleteSaleCall;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.sdk.call.GeteBayOfficialTimeCall;
import com.ebay.soap.eBLBaseComponents.AbstractResponseType;
import com.ebay.soap.eBLBaseComponents.AckCodeType;
import com.ebay.soap.eBLBaseComponents.CompleteSaleRequestType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.ErrorType;
import com.ebay.soap.eBLBaseComponents.ShipmentTrackingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShipmentType;

import tw.iii.qr.DataBaseConn;

public class CompleteSale {

	public CompleteSale() {

	}


	public void CompleteSale1(HttpServletRequest request) throws ApiException, SdkException, Exception {
		 // set credentials in ApiCredential
	   
	    //credential.setApiAccount(aa);
	    String ebayaccount = request.getParameter("ebayaccount");
		ApiContext apiContext = getApiContext(ebayaccount);
		
	    
		  ApiCall call = new ApiCall( apiContext );
	    CompleteSaleRequestType rq = new CompleteSaleRequestType();
	    rq.setDetailLevel(new DetailLevelCodeType[] { DetailLevelCodeType.RETURN_ALL,
				DetailLevelCodeType.ITEM_RETURN_DESCRIPTION, DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES });
	    rq.setTransactionID(request.getParameter("paypalmentId"));
	    rq.setItemID(request.getParameter("ebayItemNO"));
	    //rq.setPaid(true);
	    //rq.setShipped(true);
	    ShipmentType ShipmentType1 = new ShipmentType();
		ShipmentType1.setShipmentTrackingNumber(request.getParameter("trackingCode"));
		ShipmentType1.setShippingCarrierUsed("ChunghwaPost");
		rq.setShipment(ShipmentType1);
	    AbstractResponseType response;
		call.execute(rq);
		
		System.out.println("done");
//		String ebayaccount = request.getParameter("ebayaccount");
//		ApiContext apiContext = getApiContext(ebayaccount);
//		//CompleteSaleCall  apicsc = new CompleteSaleCall(apiContext);
//		ApiCall call = new ApiCall(apiContext);
//		CompleteSaleRequestType rq = new CompleteSaleRequestType();
//		
//		rq.setDetailLevel(new DetailLevelCodeType[] { DetailLevelCodeType.RETURN_ALL,
//				DetailLevelCodeType.ITEM_RETURN_DESCRIPTION, DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES });
//		// as TransactionID value
//		System.out.println(request.getParameter("paypalmentId"));
//		rq.setTransactionID(request.getParameter("paypalmentId"));
//		//apiCsC.setTransactionID("0");
//		// itemid
//		System.out.println(request.getParameter("ebayItemNO"));
//		rq.setItemID(request.getParameter("ebayItemNO"));
//		//apiCsC.setItemID(request.getParameter("361848844112"));
////361848844112-0
//		
//		
//		// System.out.println(request.getParameter("ebayItemNO"));
//		// apiCsC.setOrderID(request.getParameter("ebayItemNO"));
//
//		// 買家是否付款,賣家是否收到錢
//		rq.setPaid(true);
//		// 是否已送出
//		rq.setShipped(true);
//
//		ShipmentType ShipmentType1 = new ShipmentType();
//		ShipmentType1.setShipmentTrackingNumber(request.getParameter("trackingCode"));
//		ShipmentType1.setShippingCarrierUsed(request.getParameter("logistics"));
//		// 送出時間
//		// ShipmentType1.setShippedTime(cal);
//		rq.setShipment(ShipmentType1);
//		ErrorType error = new ErrorType();
//		
//		System.out.println(error.getLongMessage());
//		AbstractResponseType rsp ;
//		call.execute(rq);
		
	}

	private static ApiContext getApiContext(String ebayaccount) {
		ApiContext apiContext = new ApiContext();
		
		Connection conn;
		try {
			conn = new DataBaseConn().getConn();
			ApiCredential credential = new ApiCredential();
			String strSql = "select ebayToken  from ebayaccount where  ebayId = ? ";
			PreparedStatement ps = conn.prepareStatement(strSql);
			ps.setString(1, ebayaccount);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
			credential.seteBayToken(rs.getString(1));
			System.out.println(rs.getString(1));
			}
		    // add ApiCredential to ApiContext
		    ApiContext context = new ApiContext();
		    context.setApiCredential(credential);
		  
		    // set eBay server URL to call
		    context.setApiServerUrl( "https://api.ebay.com/wsapi" );  // sandbox
		    
		    // set timeout in milliseconds - 3 minutes
		    context.setTimeout(180000);
		    
		    // set wsdl version number
		    context.setWSDLVersion("423");
		  
		  

		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apiContext;

	}
//	public void CompleteSale2(HttpServletRequest request) throws ApiException, SdkException, Exception {
//	String ebayaccount = request.getParameter("ebayaccount");
//	ApiContext apiContext = getApiContext(ebayaccount);
//	
//	// GeteBayOfficialTimeCall apiCall = new
//	// GeteBayOfficialTimeCall(apiContext);
//	// Calendar cal = apiCall.geteBayOfficialTime();
//	CompleteSaleRequestType csrt = new CompleteSaleRequestType();
//	
//	CompleteSaleCall apiCsC = new CompleteSaleCall();
//	apiCsC.setApiContext(apiContext);
//	apiCsC.setDetailLevel(new DetailLevelCodeType[] { DetailLevelCodeType.RETURN_ALL,
//			DetailLevelCodeType.ITEM_RETURN_DESCRIPTION, DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES });
//	// as TransactionID value
//	System.out.println(request.getParameter("paypalmentId"));
//	apiCsC.setTransactionID(request.getParameter("paypalmentId"));
//	//apiCsC.setTransactionID("0");
//	// itemid
//	System.out.println(request.getParameter("ebayItemNO"));
//	apiCsC.setItemID(request.getParameter("ebayItemNO"));
//	//apiCsC.setItemID(request.getParameter("361848844112"));
////361848844112-0
//	
//	
//	// System.out.println(request.getParameter("ebayItemNO"));
//	// apiCsC.setOrderID(request.getParameter("ebayItemNO"));
//
//	// 買家是否付款,賣家是否收到錢
//	apiCsC.setPaid(true);
//	// 是否已送出
//	apiCsC.setShipped(true);
//
//	ShipmentType ShipmentType1 = new ShipmentType();
//	ShipmentType1.setShipmentTrackingNumber(request.getParameter("trackingCode"));
//	ShipmentType1.setShippingCarrierUsed(request.getParameter("logistics"));
//	// 送出時間
//	// ShipmentType1.setShippedTime(cal);
//	apiCsC.setShipment(ShipmentType1);
//	
//	ErrorType error = new ErrorType();
//	
//	System.out.println(error.getLongMessage());
//	apiCsC.completeSale();
//
//}
}
