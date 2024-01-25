package com.klapertart.jasperreport.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tritr
 * @since 1/25/2024
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class XYLineChart {
    private String seriesName;
    private Double xvalue;
    private Double yvalue;
}
