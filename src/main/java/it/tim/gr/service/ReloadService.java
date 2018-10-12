package it.tim.gr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tim.gr.ems.EMSMessageProducer;
import it.tim.gr.integration.proxy.AdjustBonusProxy;
import it.tim.gr.integration.proxy.CommitBonusProxy;
import it.tim.gr.integration.proxy.PaymentProxy;
import it.tim.gr.integration.proxy.PinProxy;
import it.tim.gr.integration.proxy.ReloadProxy;
import it.tim.gr.model.bonus.response.BonusResponse;
import it.tim.gr.model.input.ReloadRequest;
import it.tim.gr.model.payment.response.PaymentResponse;
import it.tim.gr.model.pinsystem.response.PinResponse;
import it.tim.gr.model.reload.response.ReloadResponse;
import it.tim.gr.rabbit.O2CMessageProducer;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Luca D'Agostino on 9/09/18.
 */
@Service
@Slf4j
public class ReloadService {

	private PinProxy pinProxy;
	private ReloadProxy reloadProxy;
	private AdjustBonusProxy adjustBonusProxy;
	private CommitBonusProxy commitBonusProxy;    
    private PaymentProxy paymentProxy;
    
    private PinResponse pinResponse;
    private ReloadResponse reloadResponse;
    private BonusResponse bonusResponse;
    private PaymentResponse paymentResponse;
    
    @Autowired
    private EMSMessageProducer emsMessageProducer;
    
    @Autowired
    private O2CMessageProducer o2cMessageProducer;
   
    @Autowired
	public ReloadService(PaymentProxy paymentProxy, PinProxy pinProxy) {
        this.paymentProxy = paymentProxy;
        this.pinProxy = pinProxy;
    }
    
    @Autowired
    public ReloadService() {}

    public String reload(ReloadRequest request){    	        
    	log.debug("Inizio");
		log.debug("Numero Linea" + request.getMessage().getNumLinea() + " - importo = " + request.getMessage().getImportoRicarica());
		/*
		 * TODO: Scrivi su Traking e Logging
		 */
	        
		String response = null;

		if (request.getMessage().getCodicePIN()!=null && !request.getMessage().getCodicePIN().equals("")) {							
				/*
				 * Traccia su Traking&Logging sostituito da LOG per KIBANA
				 */  							
			     log.debug("Pin valorizzato" + request.getMessage().getCodicePIN());
				
				
			     pinResponse = pinProxy.pinReservation(request.getMessage().getNumLinea(),
			    													request.getMessage().getCodicePIN(),
			    													request.getMessage().getSubSys(),
			    													request.getHeaderHttp());
				if (pinResponse==null || pinResponse.getEsito().equalsIgnoreCase("KO")) {
					/*
					 * Traccia su Traking&Logging sostituito con LOG
					 */
					log.error("Richiesta PIN Fallita. Esito ["+pinResponse.getEsito()+"] Causale: ["+pinResponse.getCausale()+"] PIN: ["+request.getMessage().getCodicePIN()+"]");
					
					/*
					 * TODO: fabbrica messaggio XML per O2C
					 */
					
					/*
					 * TODO: accoda messaggio per O2C su coda EMS
					 */					
				}
		} 
		
		reloadResponse = reloadProxy.reload(request.getMessage().getNumLinea(), request.getMessage().getImportoRicarica());
		if (reloadResponse!=null && reloadResponse.getEsito().equals("OK")) {
			/*
			 * Traccia su Traking&Logging sostituito con LOG
			 */
			log.debug("ricarica con esito OK  Numero Linea ["+request.getMessage().getNumLinea()+"] Importo ["+request.getMessage().getImportoRicarica()+"]");
			
			if (pinResponse!=null && pinResponse.getEsito().equalsIgnoreCase("OK")) {
				/*
				 * Traccia su Traking&Logging sostituito con LOG
				 */
				log.debug("Gestione Bonus");	

				
				bonusResponse = adjustBonusProxy.adjustBonus(request.getMessage().getNumLinea(), request.getMessage().getImportoBonus());
				if (bonusResponse!=null && bonusResponse.getEsito().equalsIgnoreCase("OK")) {
					/*
					 * Traccia su Traking&Logging sostituito con LOG
					 */
					log.debug("Ricarica Bonus OK. Esito ["+bonusResponse.getEsito()+"] ["+bonusResponse.getCausale()+"] Numero Linea ["+request.getMessage().getNumLinea()+"] Bonus ["+request.getMessage().getImportoBonus()+"]");
					
					bonusResponse = commitBonusProxy.commitBonus(); 
					if (bonusResponse!=null && bonusResponse.getEsito().equalsIgnoreCase("OK")) {
						/*
						 * Traccia su Traking&Logging sostituito con LOG
						 */
						log.debug("Commit Bonus esito OK");								
					} else {
						/*
						 * Traccia su Traking&Logging sostituito con LOG
						 */
						log.error("Commit Bonus Fallita con esito ["+bonusResponse.getEsito()+"] ["+bonusResponse.getCausale()+"] Numero Linea ["+request.getMessage().getNumLinea()+"] Bonus ["+request.getMessage().getImportoBonus()+"]");
											
						/*
						 * TODO: fabbrica messaggio fallimento BONUS(2) per O2C
						 */
						
						/*
						 * TODO: Accoda messaggio su coda EMS O2C
						 */
					}
				} else {
					/*
					 * Traccia su Traking&Logging sostituito con LOG
					 */
					log.error("ricarica Bonus Fallita con esito ["+bonusResponse.getEsito()+"] ["+bonusResponse.getCausale()+"] Numero Linea ["+request.getMessage().getNumLinea()+"] Bonus ["+request.getMessage().getImportoBonus()+"]");
										
					/*
					 * TODO: fabbrica messaggio fallimento BONUS(2) per O2C
					 */
					
					/*
					 * TODO: Accoda messaggio su coda EMS O2C
					 */
				}
				
			}
			
			

			paymentResponse = paymentProxy.payment(Integer.parseInt(request.getMessage().getImportoRicarica()),
													"TIMMOTORIC",
													Integer.parseInt(request.getMessage().getIdAutorizzazionePagamento()),
													"NTS", 
													request.getMessage().getDataAutorizzazionePagamento(),
													request.getHeaderHttp().getTransactionID());													
			
			if (paymentResponse!=null && paymentResponse.getTxHead().getResultCode().equalsIgnoreCase("IGFS_000")) {
				/*
				 * Traccia su Traking&Logging sostituito con LOG
				 */
				log.info("Addebito Riuscito ["+paymentResponse.getTxHead().getResultCode()+"] Numero Linea ["+request.getMessage().getNumLinea()+"] Importo ["+request.getMessage().getImportoRicarica()+"]");
				
				/*
				 * TODO: fabbrica messaggio OK Ricarica JSON notifica per ESB-E
				 */			
				
				/*
				 * TODO: fabbrica messaggio OK Ricarica per O2C
				 */	
				
			} else {
				log.error("Addebito FALLITO ["+paymentResponse.getTxHead().getResultCode()+"] ["+paymentResponse.getTxHead().getErrDescription()+"] Numero Linea ["+request.getMessage().getNumLinea()+"] Importo ["+request.getMessage().getImportoRicarica()+"]");
				
				/*
				 * TODO: fabbrica messaggio KO Ricarica JSON notifica per ESB-E
				 */

							
				/*
				 * TODO: fabbrica messaggio KO Ricarica per O2C
				 */
				
				response = "KO";
			}
			
			response = "OK";
		} else {
			/*
			 * Traccia su Traking&Logging sostituito con LOG
			 */
			log.error("Ricarica Fallita con esito ["+reloadResponse.getEsito()+"] ["+reloadResponse.getDescrizioneEsito()+"] Numero Linea ["+request.getMessage().getNumLinea()+"] Importo ["+request.getMessage().getImportoRicarica()+"]");

			
			/*
			 * TODO: fabbrica messaggio KO Ricarica JSON notifica per ESB-E
			 */

						
			/*
			 * TODO: fabbrica messaggio KO Ricarica per O2C
			 */
			
			response = "KO";
		}
		
		
		/*
		 * TODO: accoda messaggio EMS su O2C
		 */
		
		/*
		 * TODO: accoda messaggio EMS su O2C
		 */
		
		/*
		 * TODO: fabbrica messaggio dispositivo
		 */
		
		/*
		 * TODO: Accoda messaggio dispositivo su GR_outputDisp
		 */		
			
        log.debug("Reload completata ["+response+"]");    
        
        return response;
    }        
}