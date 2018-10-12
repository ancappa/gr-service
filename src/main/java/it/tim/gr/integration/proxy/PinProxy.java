package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import it.tim.gr.model.input.ReloadMessageHeader;
import it.tim.gr.model.pinsystem.response.PinResponse;

/**
 * Created by Luca D'Agostino on 11/10/2018
 */
@Component
public class PinProxy extends ProxyTemplate {

    static final  String SUBSYSTEM_NAME = "Pin Reservation";

    private PinDelegate delegate;

    @Autowired
    public PinProxy(PinDelegate delegate) {
        this.delegate = delegate;
    }

    public PinResponse pinReservation(String msisdn, String pinCode, String subSys, ReloadMessageHeader messageHeaders) {
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.set("businessID", messageHeaders.getBusinessID());
    	httpHeaders.set("messageID", messageHeaders.getMessageID());
    	httpHeaders.set("transactionID", messageHeaders.getTransactionID());
    	httpHeaders.set("channel", messageHeaders.getChannel());
    	httpHeaders.set("sourceSystem", messageHeaders.getSourceSystem());   	
    		
        return getBody(delegate.pinReservation(msisdn,pinCode,subSys,httpHeaders));
    }

    @Override
    String getSubsystemName() {
        return SUBSYSTEM_NAME;
    }
}