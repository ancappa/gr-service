package it.tim.gr.model.input;

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
"headerHttp",
"message"
})
public class ReloadRequest {

@JsonProperty("headerHttp")
private ReloadMessageHeader headerHttp;
@JsonProperty("message")
private ReloadMessageBody message;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("headerHttp")
public ReloadMessageHeader getHeaderHttp() {
return headerHttp;
}

@JsonProperty("headerHttp")
public void setHeaderHttp(ReloadMessageHeader headerHttp) {
this.headerHttp = headerHttp;
}

@JsonProperty("message")
public ReloadMessageBody getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(ReloadMessageBody message) {
this.message = message;
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
