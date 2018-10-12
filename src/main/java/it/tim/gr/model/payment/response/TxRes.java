package it.tim.gr.model.payment.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"paymentId",
"tranId",
"merTxInfo"
})
public class TxRes {

@JsonProperty("paymentId")
private String paymentId;
@JsonProperty("tranId")
private Integer tranId;
@JsonProperty("merTxInfo")
private List<String> merTxInfo = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("paymentId")
public String getPaymentId() {
return paymentId;
}

@JsonProperty("paymentId")
public void setPaymentId(String paymentId) {
this.paymentId = paymentId;
}

@JsonProperty("tranId")
public Integer getTranId() {
return tranId;
}

@JsonProperty("tranId")
public void setTranId(Integer tranId) {
this.tranId = tranId;
}

@JsonProperty("merTxInfo")
public List<String> getMerTxInfo() {
return merTxInfo;
}

@JsonProperty("merTxInfo")
public void setMerTxInfo(List<String> merTxInfo) {
this.merTxInfo = merTxInfo;
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
