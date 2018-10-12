package it.tim.gr.integration.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.tim.gr.integration.client.BlackListClient;
import it.tim.gr.integration.client.PinServiceClient;
import it.tim.gr.model.integration.RechargeAuthorizationResponse;
import it.tim.gr.model.integration.ScratchCardRequest;
import it.tim.gr.model.integration.ScratchCardResponse;

/**
 * Created by alongo on 27/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class PinDelegateTest {
	
    @Mock
    BlackListClient blClient;
    
    @Mock
    PinServiceClient scClient;

    PinDelegate delegate;

    @Before
    public void init(){
        delegate = new PinDelegate(blClient, scClient);
    }

    @After
    public void cleanup(){
        Mockito.reset(blClient, scClient);
    }

    
    /*
    @Test
    public void authorizeLineForRechargeOkResponse(){

        //GIVEN
        String lineNumber = "lineNumber";
        RechargeAuthorizationResponse authResponse = new RechargeAuthorizationResponse("A",lineNumber,"OK");
        ResponseEntity<RechargeAuthorizationResponse> response = new ResponseEntity<RechargeAuthorizationResponse>(authResponse, HttpStatus.OK);
        Mockito.when(blClient.authorizeLineForRecharge(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(response);

        //WHEN
        String legacyTid = generateTransactionId(24);
        ResponseEntity<RechargeAuthorizationResponse> out = delegate.authorizeLineForRecharge("ASDFGH72P12F839H", lineNumber, legacyTid);

        //THEN
        assertEquals(HttpStatus.OK, out.getStatusCode());
        assertNotNull(out.getBody());
        //assertEquals(lineNumber, out.getBody().getNumLinea());

    }

    @Test
    public void authorizeLineForRechargeKOResponse(){

        //GIVEN
        String lineNumber = "lineNumber";
        ResponseEntity<RechargeAuthorizationResponse> response = new ResponseEntity<RechargeAuthorizationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(blClient.authorizeLineForRecharge(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(response);

        //WHEN
        String legacyTid = generateTransactionId(24);
        ResponseEntity<RechargeAuthorizationResponse> out = delegate.authorizeLineForRecharge("ASDFGH72P12F839H", lineNumber, legacyTid);

        //THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, out.getStatusCode());
        assertNull(out.getBody());

    }
	*/
    
	
    @Test
    public void reliableAuthorizeLineForRecharge(){
        ResponseEntity<RechargeAuthorizationResponse> out = delegate.reliableAuthorizeLineForRecharge("refCli","lineNumber","tid", new HttpHeaders(), null);
        assertEquals(ProxyTemplate.getFallbackResponse(null).getStatusCode(), out.getStatusCode());
    }


    @Test
    public void rechargeWithScratchCardOkResponse(){

        //GIVEN
        String card = "card";
        String amount = "10";
        String lineNumber = "lineNumber";
        ScratchCardRequest request = new ScratchCardRequest();
        
        ScratchCardResponse response = new ScratchCardResponse(lineNumber, amount, "0", card, "tid");
        ResponseEntity<ScratchCardResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

        Mockito.when(scClient.rechargeWithScratchCard(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenReturn(responseEntity);

        //WHEN
        ResponseEntity<ScratchCardResponse> out = delegate.rechargeWithScratchCard("referName", lineNumber, request, new HttpHeaders());

        //THEN
        assertEquals(HttpStatus.OK, out.getStatusCode());
        assertNotNull(out.getBody());
        assertEquals(lineNumber, out.getBody().getNumLinea());
        assertEquals(amount, out.getBody().getImporto());

    }

    @Test
    public void rechargeWithScratchCardKOResponse(){

        //GIVEN
        ScratchCardRequest request = new ScratchCardRequest();
        ResponseEntity<ScratchCardResponse> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        Mockito.when(scClient.rechargeWithScratchCard(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject() , Mockito.anyObject() )).thenReturn(responseEntity);

        //WHEN
        ResponseEntity<ScratchCardResponse> out = delegate.rechargeWithScratchCard("referName", "lineNumber", request, new HttpHeaders());

        //THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, out.getStatusCode());
        assertNull(out.getBody());

    }

    @Test
    public void reliableRechargeWithScratchCard(){
        ResponseEntity<ScratchCardResponse> out = delegate.reliableRechargeWithScratchCard("referName", "3331212121", new ScratchCardRequest(), new HttpHeaders(), null);
        assertEquals(ProxyTemplate.getFallbackResponse(null).getStatusCode(), out.getStatusCode());
    }

}