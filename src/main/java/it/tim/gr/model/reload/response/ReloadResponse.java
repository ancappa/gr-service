package it.tim.gr.model.reload.response;

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
"status",
"esito",
"descrizioneEsito",
"dateInvocation"
})
public class ReloadResponse {

@JsonProperty("status")
private String status;
@JsonProperty("esito")
private String esito;
@JsonProperty("descrizioneEsito")
private String descrizioneEsito;
@JsonProperty("dateInvocation")
private String dateInvocation;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonProperty("esito")
public String getEsito() {
return esito;
}

@JsonProperty("esito")
public void setEsito(String esito) {
this.esito = esito;
}

@JsonProperty("descrizioneEsito")
public String getDescrizioneEsito() {
return descrizioneEsito;
}

@JsonProperty("descrizioneEsito")
public void setDescrizioneEsito(String descrizioneEsito) {
this.descrizioneEsito = descrizioneEsito;
}

@JsonProperty("dateInvocation")
public String getDateInvocation() {
return dateInvocation;
}

@JsonProperty("dateInvocation")
public void setDateInvocation(String dateInvocation) {
this.dateInvocation = dateInvocation;
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
