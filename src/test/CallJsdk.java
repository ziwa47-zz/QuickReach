
package test;

// use java sdk to authenticate
import com.ebay.soap.eBLBaseComponents.*;
import com.ebay.sdk.*;

import java.util.Calendar;

import java.rmi.RemoteException;
import java.util.Properties;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.FileInputStream;

/**
 * 
 * @author Suzanne Ahmed suzanne.ahmed@ebay.com
 * 
 */

public class CallJsdk {

	public static void main(String[] args) throws RemoteException, ApiException, SdkSoapException, SdkException {

		ApiCredential credential = new ApiCredential();
		ApiAccount apiAccount = new ApiAccount();
		apiAccount.setApplication("ziwaTu-QuickRea-PRD-f45f8a2bd-e830f2df");
		apiAccount.setDeveloper("0c8765fd-9dd8-400c-8342-aa6c6e06d5e3");
		apiAccount.setCertificate("PRD-45f8a2bd03d9-4505-43a0-bf59-4860");
		credential.setApiAccount(apiAccount);
		credential.seteBayToken(
				"AgAAAA**AQAAAA**aAAAAA**W+SSWA**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wJmYSjCJCEoQmdj6x9nY+seQ**sIgDAA**AAMAAA**yYFc8eH5NcyU80c7mJBVrjnV5SHyz4b1aMK6Sur+VGvshBNLM9PHQkRjhg3WgWCVTQXL5mR+4sHWz9jGiDiiJrSgUuQIA4UonJIBuQHAAVoM1QeQcPHKIYikO/QN0YnINNkkZIkb3BY+lABHoFlWeL+CmQdsMqFM9yncr5OAf0N0jNwmQtgTjhZPzz42JIoD7uu+5eWUTsG6KW+F7Uat8kOWrieSvrmdnFK/GR4SLGPPdLVni/IMMj+0r3IkdzbVZPh+KAerVJQxQ8ohzchMUMa4lKfmWBLTkcmxgOdnYHrp6Vl1MKI9vX4PoedRh80Hn7OdqXqCVRxVLcYS/tbIKgxSYTiRicgd8O6K+KPhquBdroah2TZITy+6rCoZv3ghxSkPMYlOrkBALk8LqZE2Cr3+B/Ze9U24PNIqmhd2X9J9zyhrEcxUPnKVWSuZwrOmzqCwGrknRik8MmGm9WLl8jWmP7i8DrmJiIgNHxjHtYqIq4o3xHqiwMkbkfpPHJMqDkqhVMwDTEAVNGx92tDtX5eqaIanoTuJbBmbuDL6fiYBCCE0z04G5pIwcw7qEKOTSbDK0v0EPZZ2ft5Gi9EtxSNfqW2OJCHAst5u1/Asds9LE7or1z1Wsqr7QLjR+f3Mcyn6UyxjmQww9FmIzSFaqxk/PkR1vWN+L4+UL+6XzFDqBaA4W2tHJBu7HHm2TfEVnTO3aHe+q9gDI3yKYywT/Or+Hxoo/Q3MCz5GcbBPED+el/763NdR4c+pneo1ePoo");
		ApiContext context = new ApiContext();
		context.setApiCredential(credential);
		context.setApiServerUrl("https://api.ebay.com/wsapi");
		context.setWSDLVersion("967");
		ApiCall call = new ApiCall(context);
		CompleteSaleRequestType rq = new CompleteSaleRequestType();
		rq.setErrorHandling(ErrorHandlingCodeType.ALL_OR_NOTHING);
		rq.setTransactionID("0");
		rq.setItemID("361889043214");
		ShipmentType ShipmentType1 = new ShipmentType();
		ShipmentType1.setShipmentTrackingNumber("3618890437");
		ShipmentType1.setShippingCarrierUsed("DHL");
		rq.setShipment(ShipmentType1);
		call.execute(rq);
		System.out.println("done");
	}
}
