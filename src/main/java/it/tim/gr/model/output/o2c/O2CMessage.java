package it.tim.gr.model.output.o2c;

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
public class O2CMessage {

@JsonProperty("headerHttp")
private HeaderHttp headerHttp;
@JsonProperty("message")
private Message message;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("headerHttp")
public HeaderHttp getHeaderHttp() {
return headerHttp;
}

@JsonProperty("headerHttp")
public void setHeaderHttp(HeaderHttp headerHttp) {
this.headerHttp = headerHttp;
}

@JsonProperty("message")
public Message getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(Message message) {
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
