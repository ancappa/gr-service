package it.tim.gr.rabbit;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;



@Configuration
public class ReloadMessageConf {
	
	@Value("${gr.reload.rabbit.queue.connection.host}") 
	private String HOST;
	
	@Value("${gr.reload.rabbit.queue.connection.vhost}") 
	private String VHOST;
	
	@Value("${gr.reload.rabbit.queue.connection.port}") 
	private int PORT;
	
	@Value("${gr.reload.rabbit.queue.connection.user}") 
	private String USER;
	
	@Value("${gr.reload.rabbit.queue.connection.pwd}") 
	private String PWD;

	@Value("${gr.reload.rabbit.queue.flags}")
	private String FLAGS;
	
	@Value("${gr.reload.rabbit.queue.net_debug}") 
	private String NET_DEBUG;
		
	
    @Bean
    public ConnectionFactory connectionFactory() {            	
    	System.out.println("Begin connection factory NEW");
    	org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean rb= new org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean();
    	Resource sslPropertiesLocation=null;
		rb.setSslPropertiesLocation(sslPropertiesLocation);
		
    	if (FLAGS.equals("0")){
    		rb.setUseSSL(false);
    	}else{
    		rb.setUseSSL(true);
    		//rb.setSslAlgorithm("TLSv1");
    	}
    	
		rb.setHost(HOST);
		rb.setPort(PORT);
		rb.setVirtualHost(VHOST);
		rb.setUsername(USER);
		rb.setPassword(PWD);
		
		System.out.println("HOST ["+HOST+"]");
		System.out.println("PORT ["+PORT+"]");
		System.out.println("VHOST ["+VHOST+"]");
		System.out.println("USER ["+USER+"]");
		System.out.println("PWD ["+PWD+"]");		
		
		
    	try {
			rb.afterPropertiesSet();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
    	
		if (NET_DEBUG.equals("true")) {
			System.setProperty("javax.net.debug", "all");
			System.out.println("Logging debug all");
			System.setProperty("javax.net.debug", "ssl:handshake:verbose");
			}
		
        CachingConnectionFactory connectionFactory = null;
		try {
			connectionFactory = new CachingConnectionFactory(rb.getObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

        System.out.println("Returning Single connection factory.");
        return connectionFactory;				
    }
           
    @Bean(name="rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory         rabbitListenerContainerlistenerFactory(){
     SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
     factory.setConnectionFactory(connectionFactory());
     return factory;
    }         
}

