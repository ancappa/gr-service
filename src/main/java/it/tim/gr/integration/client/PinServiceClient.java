package it.tim.gr.integration.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import it.tim.gr.model.pinsystem.request.PinRequest;
import it.tim.gr.model.pinsystem.response.PinResponse;

@FeignClient(
        name="pinservice",
        url = "${gr.pinservice}"
)
public interface PinServiceClient {
    @PostMapping(value = "/api/pinreservation")
    ResponseEntity<PinResponse> pinReservation(
    		@RequestBody PinRequest pinRequest,
    		@RequestHeader HttpHeaders headers);
}
