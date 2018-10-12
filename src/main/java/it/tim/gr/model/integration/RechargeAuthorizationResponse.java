package it.tim.gr.model.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeAuthorizationResponse {
      
	  private String numLinea;
      private String statoUtenza;
      private String transactionIdLegacy;
}
