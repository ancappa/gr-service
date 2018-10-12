package it.tim.gr.util;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class JWTToken {

	@JsonProperty("cf_piva")
    private String rifCliente;
	
	private String userAccount;
	private String tiid;
	
	private List<JWTSIM> sim;
	
	
}
