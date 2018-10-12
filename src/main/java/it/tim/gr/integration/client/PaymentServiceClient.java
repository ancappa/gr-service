package it.tim.gr.integration.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import it.tim.gr.model.payment.request.PaymentRequest;
import it.tim.gr.model.payment.response.PaymentResponse;

@FeignClient(
        name="pinservice",
        url = "${gr.payservice}"
)
public interface PaymentServiceClient {
    @PostMapping(value = "/api/pay/capture")
    ResponseEntity<PaymentResponse> charge(
    		@RequestBody PaymentRequest paymentRequest);    
}
