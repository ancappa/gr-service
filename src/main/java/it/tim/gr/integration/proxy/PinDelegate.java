package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import it.tim.gr.integration.client.PinServiceClient;
import it.tim.gr.model.integration.RechargeAuthorizationResponse;
import it.tim.gr.model.pinsystem.request.PinRequest;
import it.tim.gr.model.pinsystem.response.PinResponse;
import it.tim.gr.util.Headers;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class PinDelegate {

    private PinServiceClient pinServiceClient;

    @Autowired
    PinDelegate(PinServiceClient pinServiceClient) {
        this.pinServiceClient = pinServiceClient;
    }

    @HystrixCommand(fallbackMethod = "reliablePinReservation")
    ResponseEntity<PinResponse> pinReservation(String msisdn, String pinCode, String subSys, HttpHeaders headers) {
        
    	Headers.updateHeaders(headers);
    	PinRequest req = new PinRequest();
    	req.setMsisdn(msisdn);
    	req.setPinCode(pinCode);
    	req.setSubSys(subSys);
    	
    	return pinServiceClient.pinReservation(req, headers);
    }

    @SuppressWarnings("unused")
    ResponseEntity<PinResponse> reliablePinReservation(String msisdn, String pinCode, String subSys, HttpHeaders headers, Throwable throwable) {
        return ProxyTemplate.getFallbackResponse(throwable);
    }

     

    
}
