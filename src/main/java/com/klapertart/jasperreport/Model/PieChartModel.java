package com.klapertart.jasperreport.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tritr
 * @since 3/13/2024
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PieChartModel {
    private String label;
    private Integer value;
}
