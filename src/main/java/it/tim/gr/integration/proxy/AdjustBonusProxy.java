package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import it.tim.gr.model.bonus.response.BonusResponse;

/**
 * Created by Luca D'Agostino on 11/10/2018
 */
@Component
public class AdjustBonusProxy extends ProxyTemplate {

    static final  String SUBSYSTEM_NAME = "Adjust Bonus";

    private AdjustBonusDelegate delegate;

    @Autowired
    public AdjustBonusProxy(AdjustBonusDelegate delegate) {
        this.delegate = delegate;
    }

    public BonusResponse adjustBonus(String msisdn, String value) {
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.set("Content-Type", "application/json");   	
    		
        return getBody(delegate.adjustBonus(msisdn,value));
    }

    @Override
    String getSubsystemName() {
        return SUBSYSTEM_NAME;
    }
}