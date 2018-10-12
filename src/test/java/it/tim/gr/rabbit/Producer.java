package it.tim.gr.rabbit;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ExceptionHandler;
import com.rabbitmq.client.TopologyRecoveryException;
import com.rabbitmq.tools.json.JSONWriter;

import it.tim.gr.model.input.ReloadMessageBody;
import it.tim.gr.model.input.ReloadMessageHeader;
import it.tim.gr.model.input.ReloadRequest;

public class Producer {
	
	//Localhost
	private final static String HOST = "localhost";
	private final static String VHOST = "/"; //Virtual Host Standard
	private final static int PORT = 5672;
	private final static String USER = "guest";
	private final static String PWD = "guest";
	
	private final static String EXCHANGE = "amq.direct";
	private final static String ROUTING_KEY = "reload";

	final static Logger logger = Logger.getLogger(Producer.class);
	
	//TIM
//	private final static String HOST = "10.53.111.6";
//	private final static int PORT = 5672;
//	private final static String VHOST = "/"; //Virtual Host Standard
//	private final static String USER = "sdpEvent";
//	private final static String PWD = "sdpEvent";	
//	private final static String EXCHANGE = "ex.sdp.dev.credenziali";
//	private final static String ROUTING_KEY = "*";
	
	
	public static void main(String[] argv) throws Exception {

		logger.debug("Producer_Credenziali begin!");;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USER);
		factory.setPassword(PWD);	
		factory.setVirtualHost(VHOST);
		
		//factory.useSslProtocol("tlsv1.2");
		//factory.useSslProtocol();
		//factory.useSslProtocol("tlsv1.2");
		
		ExceptionHandler exceptionHandler= new ExceptionHandler() {
			
			@Override
			public void handleUnexpectedConnectionDriverException(Connection arg0,
					Throwable arg1) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
			
			@Override
			public void handleTopologyRecoveryException(Connection arg0, Channel arg1,
					TopologyRecoveryException arg2) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
			
			@Override
			public void handleReturnListenerException(Channel arg0, Throwable arg1) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
			
			@Override
			public void handleFlowListenerException(Channel arg0, Throwable arg1) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
			
			@Override
			public void handleConsumerException(Channel arg0, Throwable arg1,
					Consumer arg2, String arg3, String arg4) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
			
			@Override
			public void handleConnectionRecoveryException(Connection arg0,
					Throwable arg1) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
			
			@Override
			public void handleConfirmListenerException(Channel arg0, Throwable arg1) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
			
			@Override
			public void handleChannelRecoveryException(Channel arg0, Throwable arg1) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
			
			@Override
			public void handleBlockedListenerException(Connection arg0, Throwable arg1) {
				logger.debug("Producer_Credenziali ex!");;
				
			}
		};
		factory.setExceptionHandler(exceptionHandler);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

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

		channel.basicPublish(EXCHANGE, ROUTING_KEY, null, jsonMessage.getBytes());

		System.out.println(" Messaggio inviato: '" + jsonMessage + "'");

		channel.close();
		connection.close();
	}
}