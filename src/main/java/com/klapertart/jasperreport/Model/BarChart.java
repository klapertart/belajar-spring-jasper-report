package com.klapertart.jasperreport.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tritronik
 * @since 9/12/2024
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarChart {
    private String seriesName;
    private String category;
    private Integer value;
}
