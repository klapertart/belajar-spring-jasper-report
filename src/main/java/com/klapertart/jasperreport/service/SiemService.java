package com.klapertart.jasperreport.service;

import com.klapertart.jasperreport.Model.BarChartModel;
import com.klapertart.jasperreport.Model.LineChart;
import com.klapertart.jasperreport.Model.PieChartModel;
import com.klapertart.jasperreport.Model.TableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tritr
 * @since 3/13/2024
 */

@Service
public class SiemService {

    public JasperPrint publicIpReputation() throws IOException, JRException {
        //------------- load raw jasper file
        InputStream fileReport = new ClassPathResource("reports/siem/ip_reputation.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(fileReport);


        //------------- IP Reputation Pie Chart
        InputStream filePieReport = new ClassPathResource("reports/siem/pie_chart.jrxml").getInputStream();
        JasperReport jasperPieReport = JasperCompileManager.compileReport(filePieReport);

        List<PieChartModel> pieIpReputationList = new ArrayList<>();
        pieIpReputationList.add(PieChartModel.builder().label("Whitelisted").value(100).build());
        pieIpReputationList.add(PieChartModel.builder().label("Blacklisted").value(0).build());
        JRBeanCollectionDataSource chartValue = new JRBeanCollectionDataSource(pieIpReputationList);

        Map<String, Object> pieParameter = new HashMap<>();
        pieParameter.put("title", "IP Reputation");
        pieParameter.put("chartParameter", chartValue);


        //------------- Website Blacklisted Bar Chart
        InputStream fileWebsiteBarReport = new ClassPathResource("reports/siem/bar_chart.jrxml").getInputStream();
        JasperReport jasperWebsiteBarReport = JasperCompileManager.compileReport(fileWebsiteBarReport);

        List<BarChartModel> barChartsLis = new ArrayList<>();
        barChartsLis.add(BarChartModel.builder().label("Spamhaus-SBL").category("Spamhaus-SBL").value(2d).build());
        barChartsLis.add(BarChartModel.builder().label("Spamhaus-XBL").category("Spamhaus-XBL").value(6d).build());
        barChartsLis.add(BarChartModel.builder().label("Total").category("Total").value(8d).build());
        JRBeanCollectionDataSource websiteBarSource = new JRBeanCollectionDataSource(barChartsLis);

        Map<String, Object> websiteBarParameter = new HashMap<>();
        websiteBarParameter.put("title", "Blacklisted by Website");
        websiteBarParameter.put("chartParameter", websiteBarSource);


        //------------- Daily Blacklisted IP Address Bar Chart
        InputStream fileDailyBarReport = new ClassPathResource("reports/siem/bar_chart.jrxml").getInputStream();
        JasperReport jasperDailyBarReport = JasperCompileManager.compileReport(fileDailyBarReport);

        List<BarChartModel> dailyBarChartsLis = new ArrayList<>();
        dailyBarChartsLis.add(BarChartModel.builder().category("28/02").label("Non Blacklisted").value(114d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("29/02").label("Non Blacklisted").value(114d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("01/03").label("Non Blacklisted").value(114d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("02/03").label("Non Blacklisted").value(114d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("03/03").label("Non Blacklisted").value(114d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("04/03").label("Non Blacklisted").value(114d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("05/03").label("Non Blacklisted").value(114d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("28/02").label("Blacklisted").value(0d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("29/02").label("Blacklisted").value(0d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("01/03").label("Blacklisted").value(50d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("02/03").label("Blacklisted").value(0d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("03/03").label("Blacklisted").value(40d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("04/03").label("Blacklisted").value(0d).build());
        dailyBarChartsLis.add(BarChartModel.builder().category("05/03").label("Blacklisted").value(0d).build());
        JRBeanCollectionDataSource dailyBarSource = new JRBeanCollectionDataSource(dailyBarChartsLis);

        Map<String, Object> dailyBarParameter = new HashMap<>();
        dailyBarParameter.put("title", "Daily Blacklisted IP Address");
        dailyBarParameter.put("chartParameter", dailyBarSource);


        //------------- Website Blacklisted Bar Chart
        InputStream fileBlacklistedListReport = new ClassPathResource("reports/siem/table.jrxml").getInputStream();
        JasperReport jasperBlaclistedReport = JasperCompileManager.compileReport(fileBlacklistedListReport);

        List<TableModel> blacklistedList = new ArrayList<>();
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.109")
                .colValue3("Backup Fail Over")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.101")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        blacklistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("BLACKLISTED")
                .colValue6("N/A")
                .build());
        JRBeanCollectionDataSource blacklistedTableSource = new JRBeanCollectionDataSource(blacklistedList);

        Map<String, Object> tableParameter = new HashMap<>();
        tableParameter.put("title", "Blacklisted IP Address List");
        tableParameter.put("chartParameter", blacklistedTableSource);


        //------------- Whitelisted IP Address Table
        InputStream fileWhitelistedListReport = new ClassPathResource("reports/siem/table.jrxml").getInputStream();
        JasperReport jasperWhitelistedReport = JasperCompileManager.compileReport(fileWhitelistedListReport);

        List<TableModel> whitelistedList = new ArrayList<>();
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.109")
                .colValue3("Backup Fail Over")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.101")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("N/A")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        whitelistedList.add(TableModel.builder()
                .colTitle1("Node Name")
                .colTitle2("IP Address")
                .colTitle3("IP Role")
                .colTitle4("Last Update")
                .colTitle5("Spamhaus-SBL")
                .colTitle6("Spamhaus-XBL")
                .colValue1("FML-UDP-FE-01")
                .colValue2("114.125.161.103")
                .colValue3("Backup")
                .colValue4("2024-03-15 07:00:19")
                .colValue5("WHITELISTED")
                .colValue6("N/A")
                .build());
        JRBeanCollectionDataSource whitelistedTableSource = new JRBeanCollectionDataSource(whitelistedList);

        Map<String, Object> tableWhitelistedParameter = new HashMap<>();
        tableWhitelistedParameter.put("title", "Whitelisted IP Address List");
        tableWhitelistedParameter.put("chartParameter", whitelistedTableSource);


        //------------- Set Report Params
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Public IP Reputation");
        params.put("period", "2024/01/02 - 2024/02/02");
        params.put("author", "system");
        params.put("pieReport", jasperPieReport);
        params.put("pieParameter", pieParameter);
        params.put("websiteBarReport", jasperWebsiteBarReport);
        params.put("websiteBarParameter", websiteBarParameter);
        params.put("dailyBarReport", jasperDailyBarReport);
        params.put("dailyBarParameter", dailyBarParameter);
        params.put("blacklistedIpAddressReport", jasperBlaclistedReport);
        params.put("blacklistedIpAddressParameter", tableParameter);
        params.put("whitelistedIpAddressReport", jasperWhitelistedReport);
        params.put("whitelistedIpAddressParameter", tableWhitelistedParameter);

        return JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
    }
}
