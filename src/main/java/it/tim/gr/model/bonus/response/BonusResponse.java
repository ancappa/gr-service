package it.tim.gr.model.bonus.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"causale",
"dateInvocation",
"esito",
"status"
})
public class BonusResponse {

	@JsonProperty("causale")
	private String causale;
	@JsonProperty("dateInvocation")
	private String dateInvocation;
	@JsonProperty("esito")
	private String esito;
	@JsonProperty("status")
	private String status;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	@JsonProperty("causale")
	public String getCausale() {
	return causale;
	}
	
	@JsonProperty("causale")
	public void setCausale(String causale) {
	this.causale = causale;
	}
	
	@JsonProperty("dateInvocation")
	public String getDateInvocation() {
	return dateInvocation;
	}
	
	@JsonProperty("dateInvocation")
	public void setDateInvocation(String dateInvocation) {
	this.dateInvocation = dateInvocation;
	}
	
	@JsonProperty("esito")
	public String getEsito() {
	return esito;
	}
	
	@JsonProperty("esito")
	public void setEsito(String esito) {
	this.esito = esito;
	}
	
	@JsonProperty("status")
	public String getStatus() {
	return status;
	}
	
	@JsonProperty("status")
	public void setStatus(String status) {
	this.status = status;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
}
