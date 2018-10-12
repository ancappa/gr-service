package it.tim.gr.model.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Created by alongo on 26/04/18.
 */
@ConfigurationProperties(prefix = "builtin")
@Data
@Component
public class BuiltInConfiguration {

    private String keystorepath;
    private String httpsProtocols;
    private String keystoreAlias;
    private String keystorePass;
           
    public BuiltInConfiguration() {
	}

	public BuiltInConfiguration(String keystorepath) {
		super();
		this.keystorepath = keystorepath;
	}
}
