package it.tim.gr.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import it.tim.gr.model.reload.response.ReloadResponse;

/**
 * Created by Luca D'Agostino on 11/10/2018
 */
@Component
public class ReloadProxy extends ProxyTemplate {

    static final  String SUBSYSTEM_NAME = "Reload";

    private ReloadDelegate delegate;

    @Autowired
    public ReloadProxy(ReloadDelegate delegate) {
        this.delegate = delegate;
    }

    public ReloadResponse reload(String msisdn, String value) {
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.set("Content-Type", "application/json");   	
    		
        return getBody(delegate.reload(msisdn,value));
    }

    @Override
    String getSubsystemName() {
        return SUBSYSTEM_NAME;
    }
}