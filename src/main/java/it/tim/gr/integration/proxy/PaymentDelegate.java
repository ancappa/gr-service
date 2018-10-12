package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import it.tim.gr.integration.client.PaymentServiceClient;
import it.tim.gr.model.payment.request.PaymentRequest;
import it.tim.gr.model.payment.response.PaymentResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
class PaymentDelegate {

    private PaymentServiceClient paymentServiceClient;

    @Autowired
    PaymentDelegate(PaymentServiceClient paymentServiceClient) {
        this.paymentServiceClient = paymentServiceClient;
    }

    @HystrixCommand(fallbackMethod = "reliablePaymentSystem")
    ResponseEntity<PaymentResponse> paymentSystem(int amountValue, String merId, int origTranId, String paymentSystem, String txDt, String txId) {
        
    	PaymentRequest req = new PaymentRequest();
    	req.setAmountValue(amountValue);
    	req.setMerId(merId);
    	req.setOrigTranId(origTranId);
    	req.setPaymentSystem(paymentSystem);
    	req.setTxDt(txDt);
    	req.setTxId(txId);
    	    	
    	return paymentServiceClient.charge(req);
    }

    @SuppressWarnings("unused")
    ResponseEntity<PaymentResponse> reliablePaymentSystem(int amountValue, String merId, int origTranId, String paymentSystem, String txDt, String txId, Throwable throwable) {
        return ProxyTemplate.getFallbackResponse(throwable);
    }

     

    
}
