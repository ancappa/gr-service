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
"subSys",
"ipTerminale",
"login",
"tipoRicarica",
"referenceID",
"applicazione",
"metodoPagamento",
"codiceAutorizzazioneCdc",
"ultimeCifreCdc",
"dataScadenzaCdc",
"emailPaypal",
"transactionIDLegacy",
"rifCliente",
"tiidCliente",
"numLinea",
"dataOperazione",
"tipoCarta",
"importoRicarica",
"creditoResiduo",
"idAutorizzazionePagamento",
"shopTransID",
"dataAutorizzazionePagamento",
"gestorePagamento",
"sistemaPagamento",
"importoBonus",
"idPromo",
"codicePIN",
"statoPIN"
})
public class ReloadMessageBody {

@JsonProperty("subSys")
private String subSys;
@JsonProperty("ipTerminale")
private String ipTerminale;
@JsonProperty("login")
private String login;
@JsonProperty("tipoRicarica")
private String tipoRicarica;
@JsonProperty("referenceID")
private String referenceID;
@JsonProperty("applicazione")
private String applicazione;
@JsonProperty("metodoPagamento")
private String metodoPagamento;
@JsonProperty("codiceAutorizzazioneCdc")
private String codiceAutorizzazioneCdc;
@JsonProperty("ultimeCifreCdc")
private String ultimeCifreCdc;
@JsonProperty("dataScadenzaCdc")
private String dataScadenzaCdc;
@JsonProperty("emailPaypal")
private String emailPaypal;
@JsonProperty("transactionIDLegacy")
private String transactionIDLegacy;
@JsonProperty("rifCliente")
private String rifCliente;
@JsonProperty("tiidCliente")
private String tiidCliente;
@JsonProperty("numLinea")
private String numLinea;
@JsonProperty("dataOperazione")
private String dataOperazione;
@JsonProperty("tipoCarta")
private String tipoCarta;
@JsonProperty("importoRicarica")
private String importoRicarica;
@JsonProperty("creditoResiduo")
private String creditoResiduo;
@JsonProperty("idAutorizzazionePagamento")
private String idAutorizzazionePagamento;
@JsonProperty("shopTransID")
private String shopTransID;
@JsonProperty("dataAutorizzazionePagamento")
private String dataAutorizzazionePagamento;
@JsonProperty("gestorePagamento")
private String gestorePagamento;
@JsonProperty("sistemaPagamento")
private String sistemaPagamento;
@JsonProperty("importoBonus")
private String importoBonus;
@JsonProperty("idPromo")
private String idPromo;
@JsonProperty("codicePIN")
private String codicePIN;
@JsonProperty("statoPIN")
private String statoPIN;
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

@JsonProperty("ipTerminale")
public String getIpTerminale() {
return ipTerminale;
}

@JsonProperty("ipTerminale")
public void setIpTerminale(String ipTerminale) {
this.ipTerminale = ipTerminale;
}

@JsonProperty("login")
public String getLogin() {
return login;
}

@JsonProperty("login")
public void setLogin(String login) {
this.login = login;
}

@JsonProperty("tipoRicarica")
public String getTipoRicarica() {
return tipoRicarica;
}

@JsonProperty("tipoRicarica")
public void setTipoRicarica(String tipoRicarica) {
this.tipoRicarica = tipoRicarica;
}

@JsonProperty("referenceID")
public String getReferenceID() {
return referenceID;
}

@JsonProperty("referenceID")
public void setReferenceID(String referenceID) {
this.referenceID = referenceID;
}

@JsonProperty("applicazione")
public String getApplicazione() {
return applicazione;
}

@JsonProperty("applicazione")
public void setApplicazione(String applicazione) {
this.applicazione = applicazione;
}

@JsonProperty("metodoPagamento")
public String getMetodoPagamento() {
return metodoPagamento;
}

@JsonProperty("metodoPagamento")
public void setMetodoPagamento(String metodoPagamento) {
this.metodoPagamento = metodoPagamento;
}

@JsonProperty("codiceAutorizzazioneCdc")
public String getCodiceAutorizzazioneCdc() {
return codiceAutorizzazioneCdc;
}

@JsonProperty("codiceAutorizzazioneCdc")
public void setCodiceAutorizzazioneCdc(String codiceAutorizzazioneCdc) {
this.codiceAutorizzazioneCdc = codiceAutorizzazioneCdc;
}

@JsonProperty("ultimeCifreCdc")
public String getUltimeCifreCdc() {
return ultimeCifreCdc;
}

@JsonProperty("ultimeCifreCdc")
public void setUltimeCifreCdc(String ultimeCifreCdc) {
this.ultimeCifreCdc = ultimeCifreCdc;
}

@JsonProperty("dataScadenzaCdc")
public String getDataScadenzaCdc() {
return dataScadenzaCdc;
}

@JsonProperty("dataScadenzaCdc")
public void setDataScadenzaCdc(String dataScadenzaCdc) {
this.dataScadenzaCdc = dataScadenzaCdc;
}

@JsonProperty("emailPaypal")
public String getEmailPaypal() {
return emailPaypal;
}

@JsonProperty("emailPaypal")
public void setEmailPaypal(String emailPaypal) {
this.emailPaypal = emailPaypal;
}

@JsonProperty("transactionIDLegacy")
public String getTransactionIDLegacy() {
return transactionIDLegacy;
}

@JsonProperty("transactionIDLegacy")
public void setTransactionIDLegacy(String transactionIDLegacy) {
this.transactionIDLegacy = transactionIDLegacy;
}

@JsonProperty("rifCliente")
public String getRifCliente() {
return rifCliente;
}

@JsonProperty("rifCliente")
public void setRifCliente(String rifCliente) {
this.rifCliente = rifCliente;
}

@JsonProperty("tiidCliente")
public String getTiidCliente() {
return tiidCliente;
}

@JsonProperty("tiidCliente")
public void setTiidCliente(String tiidCliente) {
this.tiidCliente = tiidCliente;
}

@JsonProperty("numLinea")
public String getNumLinea() {
return numLinea;
}

@JsonProperty("numLinea")
public void setNumLinea(String numLinea) {
this.numLinea = numLinea;
}

@JsonProperty("dataOperazione")
public String getDataOperazione() {
return dataOperazione;
}

@JsonProperty("dataOperazione")
public void setDataOperazione(String dataOperazione) {
this.dataOperazione = dataOperazione;
}

@JsonProperty("tipoCarta")
public String getTipoCarta() {
return tipoCarta;
}

@JsonProperty("tipoCarta")
public void setTipoCarta(String tipoCarta) {
this.tipoCarta = tipoCarta;
}

@JsonProperty("importoRicarica")
public String getImportoRicarica() {
return importoRicarica;
}

@JsonProperty("importoRicarica")
public void setImportoRicarica(String importoRicarica) {
this.importoRicarica = importoRicarica;
}

@JsonProperty("creditoResiduo")
public String getCreditoResiduo() {
return creditoResiduo;
}

@JsonProperty("creditoResiduo")
public void setCreditoResiduo(String creditoResiduo) {
this.creditoResiduo = creditoResiduo;
}

@JsonProperty("idAutorizzazionePagamento")
public String getIdAutorizzazionePagamento() {
return idAutorizzazionePagamento;
}

@JsonProperty("idAutorizzazionePagamento")
public void setIdAutorizzazionePagamento(String idAutorizzazionePagamento) {
this.idAutorizzazionePagamento = idAutorizzazionePagamento;
}

@JsonProperty("shopTransID")
public String getShopTransID() {
return shopTransID;
}

@JsonProperty("shopTransID")
public void setShopTransID(String shopTransID) {
this.shopTransID = shopTransID;
}

@JsonProperty("dataAutorizzazionePagamento")
public String getDataAutorizzazionePagamento() {
return dataAutorizzazionePagamento;
}

@JsonProperty("dataAutorizzazionePagamento")
public void setDataAutorizzazionePagamento(String dataAutorizzazionePagamento) {
this.dataAutorizzazionePagamento = dataAutorizzazionePagamento;
}

@JsonProperty("gestorePagamento")
public String getGestorePagamento() {
return gestorePagamento;
}

@JsonProperty("gestorePagamento")
public void setGestorePagamento(String gestorePagamento) {
this.gestorePagamento = gestorePagamento;
}

@JsonProperty("sistemaPagamento")
public String getSistemaPagamento() {
return sistemaPagamento;
}

@JsonProperty("sistemaPagamento")
public void setSistemaPagamento(String sistemaPagamento) {
this.sistemaPagamento = sistemaPagamento;
}

@JsonProperty("importoBonus")
public String getImportoBonus() {
return importoBonus;
}

@JsonProperty("importoBonus")
public void setImportoBonus(String importoBonus) {
this.importoBonus = importoBonus;
}

@JsonProperty("idPromo")
public String getIdPromo() {
return idPromo;
}

@JsonProperty("idPromo")
public void setIdPromo(String idPromo) {
this.idPromo = idPromo;
}

@JsonProperty("codicePIN")
public String getCodicePIN() {
return codicePIN;
}

@JsonProperty("codicePIN")
public void setCodicePIN(String codicePIN) {
this.codicePIN = codicePIN;
}

@JsonProperty("statoPIN")
public String getStatoPIN() {
return statoPIN;
}

@JsonProperty("statoPIN")
public void setStatoPIN(String statoPIN) {
this.statoPIN = statoPIN;
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