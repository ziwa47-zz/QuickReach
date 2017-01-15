
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
 * @author Suzanne Ahmed   suzanne.ahmed@ebay.com
 * 
 */

public class CallJsdk {

  public static void main(String[] args) throws  RemoteException, ApiException, SdkSoapException, SdkException {
  	
//  Read properties file to load developer credentials
    
    
    // set credentials in ApiAccount
//	  ApiAccount aa = new ApiAccount();
//		aa.setApplication("ziwaTu-QuickRea-PRD-f45f8a2bd-e830f2df");
//		aa.setDeveloper("0c8765fd-9dd8-400c-8342-aa6c6e06d5e3");
//		aa.setCertificate("PRD-45f8a2bd03d9-4505-43a0-bf59-4860");
    
    // set credentials in ApiCredential
    ApiCredential credential = new ApiCredential();
    //credential.setApiAccount(aa);
    credential.seteBayToken("AgAAAA**AQAAAA**aAAAAA**RRC7VQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wJmYSjCJCEoQmdj6x9nY+seQ**glsBAA**AAMAAA**ft/IQyOfTIgrx5InrX78iP/vOLfW1VoB6nM/wvyk+jvzxaYYVdPqZa23QAlta+WhCbohr+6dlOm8goKzUmIERvHJ+3Fr3wz2vpIGxqYXz8OyWS97Zml3zJrvCTQNh/VW5nhlZlwlWboqMDaOYy0fovq3E9LZYzjV6k66WJq88yWNnjbrsmjW3PyqGxjq2uBTyfJOhsGJ+EUrdsCwGJ+AF6tzAsLx0H4VSqX8QzEaVUbMPOO0hrpYElD94qz/V8rMMvgkDOIwR7MCiyQagFP6OqaBed70hWE9t39BmdiZopkLpXMTHpz5PqFwqtlN1d33hd5XuTHJeP0PdLZuK8m0dAqfFvzhAZz5qxKXctmwRdEk41xT0C0M+YLg8tl1xucvnhlrSTSbVNLg40uWWnUUnM5MI1ZHMeFpMP467VH6oWENyhjOs08Jidl6Aad2l/DQuRL7tiikaUiOTN8xJmBTtmnq2ouzTkbh07ryT1hX9Z8d/VgI1uGPmCd0vzgEzE6YPoxHRCQT+SLE1xCRxEfxFDqXgcyQNH59oZZT+kPgEc9/R+mbTMvaEjMFCmhkhD/j/YZEiWx1JR0VH23t0/tgv1yXHSxFU7W9MrEEe6e2J3dnZ+AKRo/d+x9vPLJvZ9YSfsV7EBY4osNq8aPA/M8qd6hdc7h5N/DWaJuG2CNU06IU0RZRQnjY+eGSpsQd3klHphWlfBD6NgZxKUR8EyJg5fSRFqEuGTLwizx+84WSgcr746du9AwtsWZxI/nQ0dL0");
    
    // add ApiCredential to ApiContext
    ApiContext context = new ApiContext();
    context.setApiCredential(credential);
  
    // set eBay server URL to call
    context.setApiServerUrl( "https://api.ebay.com/wsapi" );  // sandbox
    
    // set timeout in milliseconds - 3 minutes
    context.setTimeout(180000);
    
    // set wsdl version number
    context.setWSDLVersion("423");
    
   
    
    
    // create ApiCall object - we'll use it to make the call
    ApiCall call = new ApiCall( context );
    
    // create soap api request and response objects
    //GeteBayOfficialTimeRequestType request = new GeteBayOfficialTimeRequestType();
    
    
    CompleteSaleRequestType rq = new CompleteSaleRequestType();
    rq.setDetailLevel(new DetailLevelCodeType[] { DetailLevelCodeType.RETURN_ALL,
			DetailLevelCodeType.ITEM_RETURN_DESCRIPTION, DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES });
    rq.setTransactionID("0");
    rq.setItemID("401238936837");
    //rq.setPaid(true);
    //rq.setShipped(true);
    ShipmentType ShipmentType1 = new ShipmentType();
	ShipmentType1.setShipmentTrackingNumber("9400110200793229501856");
	ShipmentType1.setShippingCarrierUsed("ChunghwaPost");
	rq.setShipment(ShipmentType1);
    AbstractResponseType response;
	call.execute(rq);
	
	System.out.println("done");
	
    // make the call and handle the response
//    try {
//    	response = call.executeByApiName("GeteBayOfficialTime", request);
//    	
//        // Get the ebay time
//        // Result inherits from AbstractResponseType
//        Calendar cal = response.getTimestamp();            // ebay time is returned in gmt 
//
//        // Display official ebay time in gmt
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        month = month + 1;   // java months go from 0-11; make human readable
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//        int hour24 = cal.get(Calendar.HOUR_OF_DAY);
//        int min = cal.get(Calendar.MINUTE);
//        int sec = cal.get(Calendar.SECOND);
//        System.out.println("official ebay time in gmt is: " + year + "-" + month + "-" + day + " " + hour24 + ":" + min + ":" + sec);
//        
//        // Convert ebay time to local time and display
//        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
//        System.out.println("in your time zone " + formatter.format( cal.getTime() ));
//        
//    } catch (ApiException ae) {
//    	System.out.println(ae);
//    } catch (SdkSoapException sse) {
//    	System.out.println(sse);
//    } catch (SdkException se) {
//    	System.out.println(se);
//    }
    			
  }
 }

