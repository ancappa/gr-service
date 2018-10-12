package it.tim.gr.model.payment.response;

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
"merId",
"resultCode",
"errDescription"
})
public class TxHead {

@JsonProperty("merId")
private String merId;
@JsonProperty("resultCode")
private String resultCode;
@JsonProperty("errDescription")
private String errDescription;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("merId")
public String getMerId() {
return merId;
}

@JsonProperty("merId")
public void setMerId(String merId) {
this.merId = merId;
}

@JsonProperty("resultCode")
public String getResultCode() {
return resultCode;
}

@JsonProperty("resultCode")
public void setResultCode(String resultCode) {
this.resultCode = resultCode;
}

@JsonProperty("errDescription")
public String getErrDescription() {
return errDescription;
}

@JsonProperty("errDescription")
public void setErrDescription(String errDescription) {
this.errDescription = errDescription;
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
-----------------------------------it.tim.gr.model.payment.TxRes.java-----------------------------------

