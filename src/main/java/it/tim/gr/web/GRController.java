package it.tim.gr.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.tim.gr.aspects.Loggable;
import it.tim.gr.model.exception.BadRequestException;
import it.tim.gr.model.input.ReloadRequest;
import it.tim.gr.model.web.ScratchCardResponse;
import it.tim.gr.service.ReloadService;
import it.tim.gr.util.JWTSIM;

/**
 * Created by Luca D'Agostino on 08/09/18.
 */
@RestController
@RequestMapping("/")
@Api("Controller exposing credit Top Up operations")
public class GRController {

    private ReloadService reloadService;

    private static final DateTimeFormatter AUTH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    
    @Autowired
    public GRController(ReloadService reloadService) {
    	this.reloadService = reloadService;
	}

    @RequestMapping(method = RequestMethod.POST, value = "/reload")
    @ApiOperation(value = "Refill operation with scratch card", response = ScratchCardResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Refill success"),
            @ApiResponse(code = 400, message = "Missing or wrong mandatory parameters"),
            @ApiResponse(code = 404, message = "Wrong card number or card not found"),
            @ApiResponse(code = 401, message = "Not authorized due to max attempts reached"),
            @ApiResponse(code = 500, message = "Generic error"),
    })
    @Loggable
    public String reload(@RequestBody ReloadRequest request)
    {    	    	    	  
//        GRControllerValidator.validateScratchCardRequest(request.getCardNumber(), request.getFromMsisdn()
//                , request.getToMsisdn(), request.getSubSys());
    	
    	System.out.println("[+++++++++++++++++++++++++++]");
    	System.out.println("Credito Residuo: ["+request.getMessage().getCreditoResiduo()+"]");
        System.out.println("Tipo Ricarica: ["+request.getMessage().getTipoRicarica()+"]");
        System.out.println("MSISDN: ["+request.getMessage().getNumLinea()+"]");
        System.out.println("Importo Ricarica: ["+request.getMessage().getImportoRicarica()+"]");
        System.out.println("[+++++++++++++++++++++++++++]");
        
        return reloadService.reload(request);
    }

    public String composeHeaderDateTime(){
    	
        LocalDateTime bankAuthDate = LocalDateTime.now();
        String authDate = bankAuthDate.format(AUTH_DATE_FORMATTER);
        return authDate;
    }

    
    public void checkMsisdnInSession(String msisdn,  List<JWTSIM> simListOffer ) throws BadRequestException{
    	  boolean isExistMsisdn = false;
          for (int i = 0; i < simListOffer.size(); i++) {
  			if (msisdn.equals(simListOffer.get(i).getMsisdn())) {
  				isExistMsisdn= true;
  				break;
  			}

  		}
  		
  		if( !isExistMsisdn) {
  			throw new BadRequestException("L'msisdn non esiste su opsc");
          }	
    }    
}
