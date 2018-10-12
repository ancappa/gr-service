package it.tim.gr.integration.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;

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

import it.tim.gr.model.integration.FraudPreventionRequest;
import it.tim.gr.model.integration.FraudPreventionResponse;
import it.tim.gr.model.integration.OnlineRefillRequest;

@RunWith(MockitoJUnitRunner.class)
public class RolProxyTest {

    @Mock
    RolProxyDelegate delegate;

    RolProxy proxy;

    @Before
    public void init(){
        proxy = new RolProxy(delegate);
    }

    @After
    public void cleanup(){
        Mockito.reset(delegate);
    }

    @Test
    public void getSubsystemName() throws Exception {
        assertEquals(RolProxy.SUBSYSTEM_NAME, proxy.getSubsystemName());
    }

    @Test
    public void onlineRefill() {
        Mockito.when(delegate.onlineRefill(anyObject(),anyObject()))
                .thenReturn(ResponseEntity.ok().build());
        proxy.onlineRefill(new OnlineRefillRequest(), new HttpHeaders());
    }

//    @Test
//    public void mobileOffers() {
//        MobileOffersResponse clientResponse = new MobileOffersResponse();
//        Mockito.when(delegate.mobileOffers(anyString(), anyString(), anyObject()))
//                .thenReturn(new ResponseEntity<>(clientResponse, HttpStatus.OK));
//
//        MobileOffersResponse response
//                = proxy.mobileOffers("aCustomer","aLine", new MobileOffersRequest());
//
//        assertNotNull(response);
//    }

    @Test
    public void fraudsPrevention() {
        FraudPreventionResponse clientResponse = new FraudPreventionResponse();
        Mockito.when(delegate.fraudsPrevention(anyObject(), anyObject()))
                .thenReturn(new ResponseEntity<>(clientResponse, HttpStatus.OK));

        FraudPreventionResponse response
                = proxy.fraudsPrevention(new FraudPreventionRequest(), new HttpHeaders());

        assertNotNull(response);
    }
}