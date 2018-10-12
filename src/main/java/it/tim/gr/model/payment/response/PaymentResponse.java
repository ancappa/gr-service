-----------------------------------it.tim.gr.model.payment.PaymentResponse.java-----------------------------------

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
"txHead",
"txRes",
"pendingAmount"
})
public class PaymentResponse {

@JsonProperty("txHead")
private TxHead txHead;
@JsonProperty("txRes")
private TxRes txRes;
@JsonProperty("pendingAmount")
private PendingAmount pendingAmount;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("txHead")
public TxHead getTxHead() {
return txHead;
}

@JsonProperty("txHead")
public void setTxHead(TxHead txHead) {
this.txHead = txHead;
}

@JsonProperty("txRes")
public TxRes getTxRes() {
return txRes;
}

@JsonProperty("txRes")
public void setTxRes(TxRes txRes) {
this.txRes = txRes;
}

@JsonProperty("pendingAmount")
public PendingAmount getPendingAmount() {
return pendingAmount;
}

@JsonProperty("pendingAmount")
public void setPendingAmount(PendingAmount pendingAmount) {
this.pendingAmount = pendingAmount;
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
-----------------------------------it.tim.gr.model.payment.PendingAmount.java-----------------------------------

