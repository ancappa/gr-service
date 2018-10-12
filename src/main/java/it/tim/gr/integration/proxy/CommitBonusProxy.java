package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import it.tim.gr.model.bonus.response.BonusResponse;

/**
 * Created by Luca D'Agostino on 11/10/2018
 */
@Component
public class CommitBonusProxy extends ProxyTemplate {

    static final  String SUBSYSTEM_NAME = "Commit Bonus";

    private CommitBonusDelegate delegate;

    @Autowired
    public CommitBonusProxy(CommitBonusDelegate delegate) {
        this.delegate = delegate;
    }

    public BonusResponse commitBonus() {
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.set("Content-Type", "application/json");   	
    		
        return getBody(delegate.commitBonus());
    }

    @Override
    String getSubsystemName() {
        return SUBSYSTEM_NAME;
    }
}