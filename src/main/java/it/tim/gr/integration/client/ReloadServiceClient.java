package it.tim.gr.integration.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.tim.gr.model.reload.request.ReloadRequest;
import it.tim.gr.model.reload.response.ReloadResponse;

@FeignClient(
        name="reloadservice",
        url = "${gr.reloadservice}"
)
public interface ReloadServiceClient {
    @PostMapping(value = "/api/reload")
    ResponseEntity<ReloadResponse> reload(
    		@RequestBody ReloadRequest reloadRequest);    
}
