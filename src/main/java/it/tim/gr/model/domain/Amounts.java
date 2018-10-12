package it.tim.gr.model.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by alongo on 02/05/18.
 */
@Getter
@AllArgsConstructor
public class Amounts {

    private List<Double> values;
    private Double defaultValue;
    private String promoInfo;

}
