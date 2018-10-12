package it.tim.gr.model.pinsystem.request;

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
"msisdn",
"pinCode",
"subSys"
})
public class PinRequest {

@JsonProperty("msisdn")
private String msisdn;
@JsonProperty("pinCode")
private String pinCode;
@JsonProperty("subSys")
private String subSys;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("msisdn")
public String getMsisdn() {
return msisdn;
}

@JsonProperty("msisdn")
public void setMsisdn(String msisdn) {
this.msisdn = msisdn;
}

@JsonProperty("pinCode")
public String getPinCode() {
return pinCode;
}

@JsonProperty("pinCode")
public void setPinCode(String pinCode) {
this.pinCode = pinCode;
}

@JsonProperty("subSys")
public String getSubSys() {
return subSys;
}

@JsonProperty("subSys")
public void setSubSys(String subSys) {
this.subSys = subSys;
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
