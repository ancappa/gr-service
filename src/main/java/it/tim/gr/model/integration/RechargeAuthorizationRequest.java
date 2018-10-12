package it.tim.gr.model.integration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by alongo on 13/04/18.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RechargeAuthorizationRequest {
      
      private String transactionIdLegacy;
}
