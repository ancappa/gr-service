package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import it.tim.gr.model.payment.response.PaymentResponse;

/**
 * Created by Luca D'Agostino on 11/10/2018.
 */
@Component
public class PaymentProxy extends ProxyTemplate {

    static final  String SUBSYSTEM_NAME = "Payment System";

    private PaymentDelegate delegate;

    @Autowired
    public PaymentProxy(PaymentDelegate delegate) {
        this.delegate = delegate;
    }

    public PaymentResponse payment(int amountValue, String merId, int origTranId, String paymentSystem, String txDt, String txId) {
        return getBody(delegate.paymentSystem(amountValue, merId, origTranId, paymentSystem, txDt, txId));
    }

    @Override
    String getSubsystemName() {
        return SUBSYSTEM_NAME;
    }
}