package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import it.tim.gr.integration.client.AdjustBonusServiceClient;
import it.tim.gr.model.bonus.request.AdjustBonusRequest;
import it.tim.gr.model.bonus.response.BonusResponse;
import it.tim.gr.model.integration.RechargeAuthorizationResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class AdjustBonusDelegate {

    private AdjustBonusServiceClient adjustBonusServiceClient;

    @Autowired
    AdjustBonusDelegate(AdjustBonusServiceClient adjutBonusServiceClient) {
        this.adjustBonusServiceClient = adjustBonusServiceClient;
    }

    @HystrixCommand(fallbackMethod = "reliableAdjutBonus")
    ResponseEntity<BonusResponse> adjustBonus(String msisdn, String value) {
        
    	AdjustBonusRequest req = new AdjustBonusRequest();
    	req.setMsisdn(msisdn);
    	req.setPrize(value);
    	req.setSubSys("OPSC");
    	
    	return adjustBonusServiceClient.adjustBonus(req);
    }

    @SuppressWarnings("unused")
    ResponseEntity<BonusResponse> reliableAdjustBonus(String msisdn, String pinCode, Throwable throwable) {
        return ProxyTemplate.getFallbackResponse(throwable);
    }       
}