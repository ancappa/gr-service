package it.tim.gr.web.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.tim.gr.common.headers.TimHeaders;
import it.tim.gr.common.headers.TimSession;
import it.tim.gr.integration.client.OBGClient;
import it.tim.gr.integration.proxy.RolProxy;
import it.tim.gr.integration.proxy.PinProxy;
import it.tim.gr.model.configuration.BuiltInConfiguration;
import it.tim.gr.service.ScratchCardService;
import it.tim.gr.web.GRController;

/**
 * Created by alongo on 30/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
//Tested as in-service integration test
public class GRControllerTest {

    @Mock
    TimHeaders timHeaders;

    @Mock
    TimSession timHeadersSession;

    @Mock
    PinProxy creditProxy;

    @Mock
    RolProxy customersProxy;

    @Mock
    BuiltInConfiguration configuration;

    @Mock
    OBGClient obgClient;

    ScratchCardService scratchCardService;
    GRController controller;

    @Before
    public void init(){
        scratchCardService = new ScratchCardService(customersProxy, creditProxy);        
        controller = new GRController(scratchCardService);
    }

    @After
    public void cleanup(){
        Mockito.reset(timHeaders, creditProxy, customersProxy, configuration);
        Mockito.reset(obgClient);

    }



//    @Test(expected = BadRequestException.class)
//    public void scratchCardKoOnInvalidRequest() throws Exception {
//        controller.scratchCard(new ScratchCardRequest(), null, null, null, null, null, null, null, null);
//    }
}