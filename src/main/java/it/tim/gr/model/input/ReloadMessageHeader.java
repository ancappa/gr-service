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
"sourceSystem",
"channel",
"interactionDate-Date",
"interactionDate-Time",
"sessionID",
"businessID",
"transactionID",
"messageID",
"APIGW_requestID",
"resubmitted"
})
public class ReloadMessageHeader {

@JsonProperty("sourceSystem")
private String sourceSystem;
@JsonProperty("channel")
private String channel;
@JsonProperty("interactionDate-Date")
private String interactionDateDate;
@JsonProperty("interactionDate-Time")
private String interactionDateTime;
@JsonProperty("sessionID")
private String sessionID;
@JsonProperty("businessID")
private String businessID;
@JsonProperty("transactionID")
private String transactionID;
@JsonProperty("messageID")
private String messageID;
@JsonProperty("APIGW_requestID")
private String aPIGWRequestID;
@JsonProperty("resubmitted")
private String resubmitted;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("sourceSystem")
public String getSourceSystem() {
return sourceSystem;
}

@JsonProperty("sourceSystem")
public void setSourceSystem(String sourceSystem) {
this.sourceSystem = sourceSystem;
}

@JsonProperty("channel")
public String getChannel() {
return channel;
}

@JsonProperty("channel")
public void setChannel(String channel) {
this.channel = channel;
}

@JsonProperty("interactionDate-Date")
public String getInteractionDateDate() {
return interactionDateDate;
}

@JsonProperty("interactionDate-Date")
public void setInteractionDateDate(String interactionDateDate) {
this.interactionDateDate = interactionDateDate;
}

@JsonProperty("interactionDate-Time")
public String getInteractionDateTime() {
return interactionDateTime;
}

@JsonProperty("interactionDate-Time")
public void setInteractionDateTime(String interactionDateTime) {
this.interactionDateTime = interactionDateTime;
}

@JsonProperty("sessionID")
public String getSessionID() {
return sessionID;
}

@JsonProperty("sessionID")
public void setSessionID(String sessionID) {
this.sessionID = sessionID;
}

@JsonProperty("businessID")
public String getBusinessID() {
return businessID;
}

@JsonProperty("businessID")
public void setBusinessID(String businessID) {
this.businessID = businessID;
}

@JsonProperty("transactionID")
public String getTransactionID() {
return transactionID;
}

@JsonProperty("transactionID")
public void setTransactionID(String transactionID) {
this.transactionID = transactionID;
}

@JsonProperty("messageID")
public String getMessageID() {
return messageID;
}

@JsonProperty("messageID")
public void setMessageID(String messageID) {
this.messageID = messageID;
}

@JsonProperty("APIGW_requestID")
public String getAPIGWRequestID() {
return aPIGWRequestID;
}

@JsonProperty("APIGW_requestID")
public void setAPIGWRequestID(String aPIGWRequestID) {
this.aPIGWRequestID = aPIGWRequestID;
}

@JsonProperty("resubmitted")
public String getResubmitted() {
return resubmitted;
}

@JsonProperty("resubmitted")
public void setResubmitted(String resubmitted) {
this.resubmitted = resubmitted;
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