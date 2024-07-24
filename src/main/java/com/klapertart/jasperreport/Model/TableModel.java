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
public class TableModel {
    private String colTitle1;
    private String colTitle2;
    private String colTitle3;
    private String colTitle4;
    private String colTitle5;
    private String colTitle6;
    private String colValue1;
    private String colValue2;
    private String colValue3;
    private String colValue4;
    private String colValue5;
    private String colValue6;
}
