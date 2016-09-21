package tw.iii.qr.order;

import java.io.IOException;
import java.sql.Connection;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.call.CompleteSaleCall;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.sdk.call.GeteBayOfficialTimeCall;
import com.ebay.soap.eBLBaseComponents.AckCodeType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.ErrorType;
import com.ebay.soap.eBLBaseComponents.ShipmentTrackingDetailsType;
import com.ebay.soap.eBLBaseComponents.ShipmentType;

public class CompleteSale {
	
	public CompleteSale(){
		
	}

	public void CompleteSale1(HttpServletRequest request) throws ApiException, SdkException, Exception{
		
		ApiContext apiContext = getApiContext();
		
		GeteBayOfficialTimeCall apiCall = new GeteBayOfficialTimeCall(apiContext);
        Calendar cal = apiCall.geteBayOfficialTime();
        
        CompleteSaleCall apiCsC = new CompleteSaleCall(apiContext);
        apiCsC.setDetailLevel(new DetailLevelCodeType[]{DetailLevelCodeType.RETURN_ALL,DetailLevelCodeType.ITEM_RETURN_DESCRIPTION,DetailLevelCodeType.ITEM_RETURN_ATTRIBUTES});
        //as TransactionID value
        apiCsC.setOrderID(request.getParameter("ebayItemNO"));
        //買家是否付款,賣家是否收到錢
        apiCsC.setPaid(true);
        ShipmentType ShipmentType1 = new ShipmentType();
        ShipmentType1.setShipmentTrackingNumber(request.getParameter("trackingCode"));
        ShipmentType1.setShippingCarrierUsed(request.getParameter("logistics"));
        apiCsC.setShipment(ShipmentType1);

        ErrorType error= new ErrorType(); 
        System.out.println(error.getLongMessage());
        apiCsC.completeSale();
        
	}
	
	
	
	private static ApiContext getApiContext() throws IOException {
		 String input;
	        ApiContext apiContext = new ApiContext();

	        //set Api Token to access eBay Api Server
	        ApiCredential cred = apiContext.getApiCredential();
	        //input = ConsoleUtil.readString("Enter your eBay Authentication Token: ");
	        //input = "AgAAAA**AQAAAA**aAAAAA**RRC7VQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wJmYSjCJCEoQmdj6x9nY+seQ**glsBAA**AAMAAA**ft/IQyOfTIgrx5InrX78iP/vOLfW1VoB6nM/wvyk+jvzxaYYVdPqZa23QAlta+WhCbohr+6dlOm8goKzUmIERvHJ+3Fr3wz2vpIGxqYXz8OyWS97Zml3zJrvCTQNh/VW5nhlZlwlWboqMDaOYy0fovq3E9LZYzjV6k66WJq88yWNnjbrsmjW3PyqGxjq2uBTyfJOhsGJ+EUrdsCwGJ+AF6tzAsLx0H4VSqX8QzEaVUbMPOO0hrpYElD94qz/V8rMMvgkDOIwR7MCiyQagFP6OqaBed70hWE9t39BmdiZopkLpXMTHpz5PqFwqtlN1d33hd5XuTHJeP0PdLZuK8m0dAqfFvzhAZz5qxKXctmwRdEk41xT0C0M+YLg8tl1xucvnhlrSTSbVNLg40uWWnUUnM5MI1ZHMeFpMP467VH6oWENyhjOs08Jidl6Aad2l/DQuRL7tiikaUiOTN8xJmBTtmnq2ouzTkbh07ryT1hX9Z8d/VgI1uGPmCd0vzgEzE6YPoxHRCQT+SLE1xCRxEfxFDqXgcyQNH59oZZT+kPgEc9/R+mbTMvaEjMFCmhkhD/j/YZEiWx1JR0VH23t0/tgv1yXHSxFU7W9MrEEe6e2J3dnZ+AKRo/d+x9vPLJvZ9YSfsV7EBY4osNq8aPA/M8qd6hdc7h5N/DWaJuG2CNU06IU0RZRQnjY+eGSpsQd3klHphWlfBD6NgZxKUR8EyJg5fSRFqEuGTLwizx+84WSgcr746du9AwtsWZxI/nQ0dL0";
	        input = "AgAAAA**AQAAAA**aAAAAA**T+rgVw**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GjC5eBpgWdj6x9nY+seQ**++oDAA**AAMAAA**KrbpmpcD67VY0UO20Q/vbkLrLQChXpvs/xkUUPH7awJn4I72xqw6c/32ET17wrRFhsYsuScNi6NoD5GTFyM6lbJFWHDSZlrlz9+CGC7lPAUJA+72yEZ4ENAwEf1WlVIeKbmzrXYYogYTUpi6LKN6XmET9Lh0Yt6SxsoWMUaPK2wZ8RhUw8gu88PZGZ1wdLsaCmq6ykrYbXIxU7hzpaLH36YlSky4YJjvLrD0wFvMp2DZWbj/pYHWhLaMx9nrLGUl1fDQI/mAQMwi3q9kL2GedSY+cGXzP8ZXyMNrUvFjAh54xK6OX3P8Na2MCmV5gjz95u0Fv+OhgtvPl68/7yq6VZG7P6AghA5Klkn4VrYiMYc5YgpYYH+1+Ws00il4nmNm4nVLh04eFLQepyzi3cPdlakxGFl/fz8rhXd3NCjPPU42HjYWk54JrS8xasbg/4/yz2TOWxA3O9xQTF55bcrEnSMLy4bgPv/5xZC2SKqI70jmddJbne4VDEqRDBbuFugRWo+l6ztRXJU+u5fjtYDVrLFZTIIbVBPeXfaGsa6B8fvNpQk5wlwkpf7g0u0hTXeioDNy0diJzpJ06uNVPMNH70ujUxJX8ogAbUnkKCKdaim8cECfyJ0Ol9aMILrrKNlXczwIGyiBmLWTJZYN0IX0OFGsLurusiLIp6EtOQpgAkg9tTPbFY3BiizrUrggdDQ+cznG4SrRfylaP5qZVgLSkBzDy9tej9rPCwDHU5ubh0Pn5wg1YWatP1ZFUO1Dg9fo";
	        cred.seteBayToken(input); 	

	        //set Api Server Url
	        //input = ConsoleUtil.readString("Enter eBay SOAP server URL (e.g., https://api.ebay.com/wsapi): ");

	        //input = "https://api.ebay.com/wsapi";
	        input = "https://api.sandbox.ebay.com/wsapi";
	        apiContext.setApiServerUrl(input);
			return apiContext;

	        
	}
}
