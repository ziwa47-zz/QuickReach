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
import tw.iii.qr.order.DTO.COrderMaster;

public class CompleteSale {

	public CompleteSale() {

	}

	public Boolean CompleteSale1(COrderMaster corder) throws ApiException, SdkException, Exception {
		// 可用參數
		// Origincdm.setEbayAccount(request.getParameter("ebayaccount"));
		// Origincdm.setOrder_id(request.getParameter("Order_id"));
		// Origincdm.setTrackingCode(request.getParameter("trackingCode"));
		// Origincdm.setLogistics(request.getParameter("logistics"));
		// Origincdm.setQR_id(request.getParameter("QR_id"));
		// Origincdm.setStaffName(request.getParameter("staffName"));

		String ebayaccount = corder.getEbayAccount(); // ebay帳號
		String token = GetToken(ebayaccount); // token
		String orderid = corder.getOrder_id(); // orderid ItemID-TransactionID
		String trackingCode = corder.getTrackingCode(); // 追蹤馬
		String[] transaionAndItem = orderid.split("-"); // ItemID,TransactionID
		String logistic = checklogistic(corder.getLogistics()); // 手選物流 轉 EBAY物流
		try {
			ApiCredential credential = new ApiCredential();
			credential.seteBayToken(token);
			ApiContext context = new ApiContext();
			context.setApiCredential(credential);
			context.setApiServerUrl("https://api.ebay.com/wsapi");
			context.setWSDLVersion("967");
			ApiCall call = new ApiCall(context);
			CompleteSaleRequestType rq = new CompleteSaleRequestType();
			rq.setErrorHandling(ErrorHandlingCodeType.ALL_OR_NOTHING);
			rq.setTransactionID(transaionAndItem[1]);
			rq.setItemID(transaionAndItem[0]);
			ShipmentType ShipmentType1 = new ShipmentType();
			ShipmentType1.setShipmentTrackingNumber(trackingCode);
			ShipmentType1.setShippingCarrierUsed(logistic);
			rq.setShipment(ShipmentType1);

			AbstractResponseType response = call.execute(rq);

			AckCodeType isOk = response.getAck();
			if (isOk == AckCodeType.SUCCESS) {
				System.out.println("上傳追蹤碼成功 Upload Success");
				return true;
			}
			ErrorType[] error = response.getErrors();
			System.out.println("錯誤原因: " + error[0] + " : " + error[1]);
			return false;

		} catch (Exception e) {
			System.out.println("fail " + e);
			return false;
		}
	}

	private String checklogistic(String logistic) {
		// <option value="DHL">DHL</option>
		// <option value="Fedex">Fedex</option>
		// <option value="EMS">EMS</option>
		// <option value="AP">AP</option>
		// <option value="RA">RA</option>
		// <option value="USPS(寄倉)">USPS(寄倉)</option>
		// <option value="USPS(集運)">USPS(集運)</option>
		// <option value="Post">Post</option>
		String truelogistic = "";
		switch (logistic) {
		case "DHL":
			truelogistic = "DHL";
			break;
		case "Fedex":
			truelogistic = "FedEx";
			break;
		case "EMS":
		case "AP":
		case "RA":
		case "Post":
			truelogistic = "ChunghwaPost";
			break;
		case "USPS":
			truelogistic = "USPS";
			break;
		case "USPS(寄倉)":
		case "USPS(集運)":
			truelogistic = "USPS";
			break;
		default:
			truelogistic = "ChunghwaPost";
			break;
		}
		return truelogistic;
	}

	private String GetToken(String ebayaccount)
			throws IllegalAccessException, ClassNotFoundException, SQLException, Exception {
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
