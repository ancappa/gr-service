package it.tim.gr.model.payment.request;

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
"paymentSystem",
"merId",
"txId",
"txDt",
"origTranId",
"amountValue"
})
public class PaymentRequest {

@JsonProperty("paymentSystem")
private String paymentSystem;
@JsonProperty("merId")
private String merId;
@JsonProperty("txId")
private String txId;
@JsonProperty("txDt")
private String txDt;
@JsonProperty("origTranId")
private Integer origTranId;
@JsonProperty("amountValue")
private Integer amountValue;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("paymentSystem")
public String getPaymentSystem() {
return paymentSystem;
}

@JsonProperty("paymentSystem")
public void setPaymentSystem(String paymentSystem) {
this.paymentSystem = paymentSystem;
}

@JsonProperty("merId")
public String getMerId() {
return merId;
}

@JsonProperty("merId")
public void setMerId(String merId) {
this.merId = merId;
}

@JsonProperty("txId")
public String getTxId() {
return txId;
}

@JsonProperty("txId")
public void setTxId(String txId) {
this.txId = txId;
}

@JsonProperty("txDt")
public String getTxDt() {
return txDt;
}

@JsonProperty("txDt")
public void setTxDt(String txDt) {
this.txDt = txDt;
}

@JsonProperty("origTranId")
public Integer getOrigTranId() {
return origTranId;
}

@JsonProperty("origTranId")
public void setOrigTranId(Integer origTranId) {
this.origTranId = origTranId;
}

@JsonProperty("amountValue")
public Integer getAmountValue() {
return amountValue;
}

@JsonProperty("amountValue")
public void setAmountValue(Integer amountValue) {
this.amountValue = amountValue;
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