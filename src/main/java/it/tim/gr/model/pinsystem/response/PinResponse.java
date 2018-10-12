package it.tim.gr.model.pinsystem.response;

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
"esito",
"causale",
"status",
"interactionDate"
})
public class PinResponse {

@JsonProperty("esito")
private String esito;
@JsonProperty("causale")
private String causale;
@JsonProperty("status")
private String status;
@JsonProperty("interactionDate")
private String interactionDate;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("esito")
public String getEsito() {
return esito;
}

@JsonProperty("esito")
public void setEsito(String esito) {
this.esito = esito;
}

@JsonProperty("causale")
public String getCausale() {
return causale;
}

@JsonProperty("causale")
public void setCausale(String causale) {
this.causale = causale;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("interactionDate")
public String getInteractionDate() {
return interactionDate;
}

@JsonProperty("interactionDate")
public void setInteractionDate(String interactionDate) {
this.interactionDate = interactionDate;
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
