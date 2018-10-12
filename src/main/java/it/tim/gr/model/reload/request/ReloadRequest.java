package it.tim.gr.model.reload.request;

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
"subSys",
"msisdn",
"basketValue"
})
public class ReloadRequest {

@JsonProperty("subSys")
private String subSys;
@JsonProperty("msisdn")
private String msisdn;
@JsonProperty("basketValue")
private String basketValue;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("subSys")
public String getSubSys() {
return subSys;
}

@JsonProperty("subSys")
public void setSubSys(String subSys) {
this.subSys = subSys;
}

@JsonProperty("msisdn")
public String getMsisdn() {
return msisdn;
}

@JsonProperty("msisdn")
public void setMsisdn(String msisdn) {
this.msisdn = msisdn;
}

@JsonProperty("basketValue")
public String getBasketValue() {
return basketValue;
}

@JsonProperty("basketValue")
public void setBasketValue(String basketValue) {
this.basketValue = basketValue;
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
