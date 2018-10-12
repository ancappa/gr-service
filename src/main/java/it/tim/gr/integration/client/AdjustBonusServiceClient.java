package it.tim.gr.integration.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.tim.gr.model.bonus.request.AdjustBonusRequest;
import it.tim.gr.model.bonus.response.BonusResponse;

@FeignClient(
        name="adjustbonusservice",
        url = "${gr.adjustbonusservice}"
)
public interface AdjustBonusServiceClient {
    @PostMapping(value = "/api/bonusadjust")
    ResponseEntity<BonusResponse> adjustBonus(
    		@RequestBody AdjustBonusRequest adjustBonusRequest);    
}
