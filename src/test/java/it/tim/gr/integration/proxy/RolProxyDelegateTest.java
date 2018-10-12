package it.tim.gr.integration.proxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

import java.util.HashMap;

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

import it.tim.gr.integration.client.FraudClient;
import it.tim.gr.integration.client.MobileClient;
import it.tim.gr.integration.client.RolClient;
import it.tim.gr.model.integration.FraudPreventionRequest;
import it.tim.gr.model.integration.FraudPreventionResponse;
import it.tim.gr.model.integration.MobileOffersResponse;
import it.tim.gr.model.integration.OnlineRefillRequest;

@RunWith(MockitoJUnitRunner.class)
public class RolProxyDelegateTest {

    @Mock
    FraudClient customersClient;
    
    @Mock
    RolClient rolClient;

    @Mock
    MobileClient mobileClient;
    
    RolProxyDelegate delegate;

    @Before
    public void init(){
        delegate = new RolProxyDelegate(null, customersClient, null);
    }

    @After
    public void cleanup(){
        Mockito.reset(customersClient);
    }

//    @Test
//    public void onlineRefillOkResponse() {
//        //GIVEN
//        ResponseEntity response = ResponseEntity.ok().build();
//        Mockito.when(rolClient.onlineRefill(anyObject(), anyString())).thenReturn(response);
//
//        //WHEN
//        ResponseEntity out = delegate.onlineRefill(new OnlineRefillRequest());
//
//        //THEN
//        assertEquals(HttpStatus.OK, out.getStatusCode());
//        assertNull(out.getBody());
//    }
//
//    @Test
//    public void onlineRefillKoResponse() {
//        //GIVEN
//        ResponseEntity response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        Mockito.when(rolClient.onlineRefill(anyObject(), anyString())).thenReturn(response);
//
//        //WHEN
//        ResponseEntity out = delegate.onlineRefill(new OnlineRefillRequest());
//
//        //THEN
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, out.getStatusCode());
//        assertNull(out.getBody());
//    }

    @Test
    public void reliableOnlineRefill() {
        ResponseEntity out = delegate.reliableOnlineRefill(new OnlineRefillRequest(), new HttpHeaders(), null);
        assertEquals(ProxyTemplate.getFallbackResponse(null).getStatusCode(), out.getStatusCode());
    }



//    @Test
//    public void mobileOffersOkResponse() {
//        //GIVEN
//        MobileOffersResponse response = new MobileOffersResponse();
//        ResponseEntity<MobileOffersResponse> entity = ResponseEntity.ok(response);
//        Mockito.when(mobileClient.mobileOffers(anyString(), anyString(), anyObject(), anyString())).thenReturn(entity);
//
//        //WHEN
//        ResponseEntity<MobileOffersResponse> out = delegate.mobileOffers("aCustomer", "aLine", new HashMap<>());
//
//        //THEN
//        assertEquals(HttpStatus.OK, out.getStatusCode());
//        assertNotNull(out.getBody());
//    }
//
//    @Test
//    public void mobileOffersKoResponse() {
//        //GIVEN
//        ResponseEntity<MobileOffersResponse> entity = new ResponseEntity<MobileOffersResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//        Mockito.when(mobileClient.mobileOffers(anyString(), anyString(), anyObject() , anyString())).thenReturn(entity);
//
//        //WHEN
//        ResponseEntity<MobileOffersResponse> out = delegate.mobileOffers("aCustomer", "aLine", new HashMap<>());
//
//        //THEN
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, out.getStatusCode());
//        assertNull(out.getBody());
//    }

    @Test
    public void reliableMobileOffers() {
        ResponseEntity<MobileOffersResponse> out
                = delegate.reliableMobileOffers("aClient", "aLine", new HashMap<>(), new HttpHeaders(), null);
        assertEquals(ProxyTemplate.getFallbackResponse(null).getStatusCode(), out.getStatusCode());
    }



    @Test
    public void fraudsPreventionOkResponse() {
        //GIVEN
        FraudPreventionResponse response = new FraudPreventionResponse();
        ResponseEntity<FraudPreventionResponse> entity = ResponseEntity.ok(response);
        Mockito.when(customersClient.fraudsPrevention(anyObject() , anyString(), anyObject())).thenReturn(entity);

        //WHEN
        ResponseEntity<FraudPreventionResponse> out = delegate.fraudsPrevention(new FraudPreventionRequest(), new HttpHeaders());

        //THEN
        assertEquals(HttpStatus.OK, out.getStatusCode());
        assertNotNull(out.getBody());
    }

    @Test
    public void fraudsPreventionKoResponse() {
        //GIVEN
        ResponseEntity<FraudPreventionResponse> entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        Mockito.when(customersClient.fraudsPrevention(anyObject() , anyString(), anyObject())).thenReturn(entity);

        //WHEN
        ResponseEntity<FraudPreventionResponse> out = delegate.fraudsPrevention(new FraudPreventionRequest(), new HttpHeaders());

        //THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, out.getStatusCode());
        assertNull(out.getBody());
    }

    @Test
    public void reliableFraudsPrevention() {
        ResponseEntity<FraudPreventionResponse> out = delegate.reliableFraudsPrevention(new FraudPreventionRequest(), new HttpHeaders(), null);
        assertEquals(ProxyTemplate.getFallbackResponse(null).getStatusCode(), out.getStatusCode());
    }
}