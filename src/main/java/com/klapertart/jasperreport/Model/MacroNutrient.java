package com.klapertart.jasperreport.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author tritr
 * @since 1/22/2024
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MacroNutrient {
    private String macroNutrientName;
    private Integer macroNutrientValue;
}
