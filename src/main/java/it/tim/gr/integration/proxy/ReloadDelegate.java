package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import it.tim.gr.integration.client.ReloadServiceClient;
import it.tim.gr.model.integration.RechargeAuthorizationResponse;
import it.tim.gr.model.reload.request.ReloadRequest;
import it.tim.gr.model.reload.response.ReloadResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class ReloadDelegate {

    private ReloadServiceClient reloadServiceClient;

    @Autowired
    ReloadDelegate(ReloadServiceClient reloadServiceClient) {
        this.reloadServiceClient = reloadServiceClient;
    }

    @HystrixCommand(fallbackMethod = "reliableReload")
    ResponseEntity<ReloadResponse> reload(String msisdn, String value) {
        
    	ReloadRequest req = new ReloadRequest();
    	req.setMsisdn(msisdn);
    	req.setBasketValue(value);
    	req.setSubSys("OPSC");
    	
    	return reloadServiceClient.reload(req);
    }

    @SuppressWarnings("unused")
    ResponseEntity<RechargeAuthorizationResponse> reliableReload(String msisdn, String value, HttpHeaders headers, Throwable throwable) {
        return ProxyTemplate.getFallbackResponse(throwable);
    }       
}