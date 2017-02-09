package tw.iii.qr.order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.ebay.soap.eBLBaseComponents.ErrorHandlingCodeType;
import com.ebay.soap.eBLBaseComponents.ErrorType;
import com.ebay.soap.eBLBaseComponents.ShipmentTrackingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShipmentType;

import tw.iii.qr.DataBaseConn;

public class CompleteSale {

	public CompleteSale() {

	}

	public Boolean CompleteSale1(HttpServletRequest request) throws ApiException, SdkException, Exception {

		try {
			String ebayaccount = request.getParameter("ebayaccount");
			String token =	GetToken(ebayaccount);
			String orderid = request.getParameter("Order_id");
			String[] transaionAndItem=orderid.split("-");
			ApiCredential credential = new ApiCredential();
			credential.seteBayToken(token);
			ApiContext context = new ApiContext();
			context.setApiCredential(credential);
			context.setApiServerUrl("https://api.ebay.com/wsapi");
			context.setWSDLVersion("967");
			ApiCall call = new ApiCall(context);
			CompleteSaleRequestType rq = new CompleteSaleRequestType();
//			rq.setDetailLevel(new DetailLevelCodeType[] { DetailLevelCodeType.RETURN_ALL,
//					DetailLevelCodeType.ITEM_RETURN_DESCRIPTION, DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES });
			
			
			rq.setItemID(transaionAndItem[0]);
			rq.setTransactionID(transaionAndItem[1]);
			System.out.println("ItemID: "+transaionAndItem[0]);
			System.out.println("TransactionID: "+transaionAndItem[1]);
			rq.setErrorHandling(ErrorHandlingCodeType.ALL_OR_NOTHING);
			// rq.setPaid(true);
			// rq.setShipped(true);
			ShipmentType ShipmentType1 = new ShipmentType();
			ShipmentType1.setShipmentTrackingNumber(request.getParameter("trackingCode"));
			ShipmentType1.setShippingCarrierUsed("DHL");
			rq.setShipment(ShipmentType1);
			//AbstractResponseType response;
			call.execute(rq);
			System.out.println("done");
			return true;
		} catch (Exception e) {
			System.out.println("fail " + e);
			return false;
		}
	}
	private  String GetToken(String ebayaccount) throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
		Connection conn;
		conn = new DataBaseConn().getConn();
		String strSql = "select ebayToken  from ebayaccount where  ebayId = ? ";
		PreparedStatement ps = conn.prepareStatement(strSql);
		ps.setString(1, ebayaccount);
		ResultSet rs = ps.executeQuery();
		String token = null; 
		if (rs.next()) {
			token = rs.getString(1);
		}
		return token;
	}
}
