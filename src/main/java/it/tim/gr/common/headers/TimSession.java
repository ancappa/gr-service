package it.tim.gr.common.headers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.tim.gr.util.JWTSIM;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimSession {
	private String tiid;
    private String userAccount;
    @JsonProperty("cf_piva")
    private String userReference;
    private String dcaCoockie;
    private String accountType;
    private List<JWTSIM> sim;
    
}
