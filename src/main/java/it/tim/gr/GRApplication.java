package it.tim.gr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.DefaultFeignLoggerFactory;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignLoggerFactory;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import it.tim.gr.util.FeignExtendedLogger;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@ComponentScan(basePackages={"it.tim.gr"})
@EnableAspectJAutoProxy
public class GRApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(GRApplication.class, args);
	}
	
	@Bean
    public FeignLoggerFactory feignLoggerFactory() {
    	return new DefaultFeignLoggerFactory(new FeignExtendedLogger());
    }
}