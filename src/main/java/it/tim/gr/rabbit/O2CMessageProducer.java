package it.tim.gr.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.tools.json.JSONWriter;

import it.tim.gr.model.input.ReloadMessageBody;
import it.tim.gr.model.input.ReloadMessageHeader;
import it.tim.gr.model.input.ReloadRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class O2CMessageProducer {
	
	@Value("${gr.O2C.rabbit.queue.exchange}") 
	private String EXCHANGE;
	
	@Value("${gr.O2C.rabbit.queue.routing_key}") 
	private String ROUTING_KEY;
					
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void produceMsg(){
		ReloadRequest reloadMessage = new ReloadRequest();
		ReloadMessageHeader reloadMessageHeader = new ReloadMessageHeader();
		ReloadMessageBody reloadMessageBody = new ReloadMessageBody();
		
		
		//INIZIO HEADER		
		reloadMessageHeader.setTransactionID("0987654321");
		reloadMessageHeader.setChannel("TESTLUCA");
		reloadMessageHeader.setInteractionDateDate("10/09/2018");
		//FINE HEADER
		
		
		//BODY	
		reloadMessageBody.setCodicePIN("35935");
		reloadMessageBody.setImportoRicarica("100");
		reloadMessageBody.setNumLinea("3357811137");
		//FINE BODY	
		
		reloadMessage.setHeaderHttp(reloadMessageHeader);
		reloadMessage.setMessage(reloadMessageBody);
		
		
		JSONWriter writer = new JSONWriter(true);
		String jsonMessage = writer.write(reloadMessage);

		amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, jsonMessage);
		log.debug("Messaggio inviato: [" + jsonMessage + "]");
	}	
}
