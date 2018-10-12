package it.tim.gr.integration.proxy;

import static it.tim.gr.service.IdsGenerator.generateTransactionId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import it.tim.gr.model.integration.RechargeAuthorizationResponse;
import it.tim.gr.model.integration.ScratchCardRequest;
import it.tim.gr.model.integration.ScratchCardResponse;

/**
 * Created by alongo on 27/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SCProxyTest {

    @Mock
    PinDelegate delegate;

    PinProxy proxy;

    @Before
    public void init(){
        proxy = new PinProxy(delegate);
    }

    @After
    public void cleanup(){
        Mockito.reset(delegate);
    }

    @Test
    public void authorizeLineForRecharge() throws Exception {

        String lineNum = "lineNum";
        Mockito.when(delegate.authorizeLineForRecharge(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyObject() ))
                .thenReturn(new ResponseEntity<>(
                        new RechargeAuthorizationResponse("A", lineNum, "OK"),
                        HttpStatus.OK));

        String legacyTid = generateTransactionId(24);
        RechargeAuthorizationResponse rechargeAuthorizationResponse = proxy.authorizeLineForRecharge("FRRCFT79P12F836G",lineNum, legacyTid, new HttpHeaders());
        assertNotNull(rechargeAuthorizationResponse);
        //assertEquals(lineNum, rechargeAuthorizationResponse.getNumLinea());
        //assertEquals("A", rechargeAuthorizationResponse.getTransactionIdLegacy());
        //assertEquals("0", rechargeAuthorizationResponse.getStatoUtenza());

    }


    @Test
    public void rechargeWithScratchCard() throws Exception {
        String lineNum = "lineNum";
        String amount = "amount";
        Mockito.when(delegate.rechargeWithScratchCard(Mockito.anyObject(),Mockito.anyObject(),Mockito.anyObject(),Mockito.anyObject() ))
                .thenReturn(new ResponseEntity<>(
                		 new ScratchCardResponse(lineNum, amount, "0", "card", "tid"),
                        HttpStatus.OK));

        ScratchCardResponse scratchCardResponse = proxy.rechargeWithScratchCard("refName", lineNum, new ScratchCardRequest(), new HttpHeaders());
        assertNotNull(scratchCardResponse);
        assertEquals(lineNum, scratchCardResponse.getNumLinea());
        assertEquals(amount, scratchCardResponse.getImporto());
        assertEquals("0", scratchCardResponse.getEsito());
    }

    @Test
    public void getSubsystemName() throws Exception {
        assertEquals(PinProxy.SUBSYSTEM_NAME, proxy.getSubsystemName());
    }

}