package it.tim.gr.integration.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.tim.gr.model.bonus.request.AdjustBonusRequest;
import it.tim.gr.model.bonus.response.BonusResponse;

@FeignClient(
        name="commitbonusservice",
        url = "${gr.commitbonusservice}"
)
public interface CommitBonusServiceClient {
    @PostMapping(value = "/api/bonuscommit")
    ResponseEntity<BonusResponse> commitBonus();    
}
