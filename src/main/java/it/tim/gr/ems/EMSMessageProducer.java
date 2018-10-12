package it.tim.gr.ems;
					
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EMSMessageProducer {	
	
  @Value("${gr.EMS.queue}") 
  private String DESTINATION;
 
  @Autowired
  private JmsTemplate jmsTemplate;

  public void send(String message) {
    log.debug("invio a EMS ["+DESTINATION+"] ["+message+"]" );
    jmsTemplate.convertAndSend(DESTINATION, message);
  }
}