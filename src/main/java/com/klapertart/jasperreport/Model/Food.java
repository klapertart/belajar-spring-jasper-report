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
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private String foodName;
    private String mealTime;
    private Integer fat;
    private Integer protein;
    private Integer carbohydrate;
}
