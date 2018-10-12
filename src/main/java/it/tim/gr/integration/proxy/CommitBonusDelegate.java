package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import it.tim.gr.integration.client.CommitBonusServiceClient;
import it.tim.gr.model.bonus.request.AdjustBonusRequest;
import it.tim.gr.model.bonus.response.BonusResponse;
import it.tim.gr.model.integration.RechargeAuthorizationResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class CommitBonusDelegate {

    private CommitBonusServiceClient commitBonusServiceClient;

    @Autowired
    CommitBonusDelegate(CommitBonusServiceClient commitBonusServiceClient) {
        this.commitBonusServiceClient = commitBonusServiceClient;
    }

    @HystrixCommand(fallbackMethod = "reliableCommitBonus")
    ResponseEntity<BonusResponse> commitBonus() {            
    	return commitBonusServiceClient.commitBonus();
    }

    @SuppressWarnings("unused")
    ResponseEntity<BonusResponse> reliableCommitBonus(Throwable throwable) {
        return ProxyTemplate.getFallbackResponse(throwable);
    }       
}