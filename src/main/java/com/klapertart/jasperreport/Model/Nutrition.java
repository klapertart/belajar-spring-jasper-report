package com.klapertart.jasperreport.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tritr
 * @since 1/22/2024
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Nutrition {
    private String nutritionName;
    private Integer total;
    private Integer goal;
    private String metric;
}
