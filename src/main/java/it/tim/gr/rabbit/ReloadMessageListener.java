package it.tim.gr.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.tools.json.JSONWriter;

import it.tim.gr.model.input.ReloadMessageHeader;
import it.tim.gr.model.input.ReloadRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@EnableRabbit
public class ReloadMessageListener {		
	
	@Autowired
	private O2CMessageProducer o2cMessaggeProducer;
	
	@RabbitListener(queues = "${gr.reload.rabbit.queue.name}")
    public void listen(byte[] message) throws Exception {     			 	
    	try{			
			log.debug("Inizio");
			log.debug("Messaggio="+new String(message,"UTF-8"));			

			ObjectMapper objectMapper = new ObjectMapper();
			ReloadRequest reloadRequest = objectMapper.readValue(message, ReloadRequest.class);
			JSONWriter writer = new JSONWriter(true);
			String jsonMessage = writer.write(reloadRequest);
			log.debug("Messaggio JSON: "+jsonMessage);
			
			ReloadMessageHeader header = reloadRequest.getHeaderHttp();
			if(header != null){
				String transactionId = header.getTransactionID();
				log.info("ID Transazione: "+transactionId);
			}else{
				log.error("L'oggetto [Header] e' null!");
			}
			
			log.debug("Chimata Servizio Reload");							
			ReloadService reloadService = new ReloadService();
			reloadService.reload(reloadRequest);
			
			o2cMessaggeProducer.produceMsg();
			
			log.debug("Chimata Servizio Reload terminata.");			
					
    	}catch(Exception e){
    		try {
    			log.error("Errore durante l'elaborazione della richiesta. Esecuzione fallita!");
				e.printStackTrace();
			} catch (Exception e1) {				
				log.error(e.getMessage()); 
			}
		 	
    	}
    }
}