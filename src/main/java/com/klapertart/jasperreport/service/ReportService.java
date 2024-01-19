package com.klapertart.jasperreport.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tritr
 * @since 1/18/2024
 */

@Service
public class ReportService {
    @Autowired
    private DataSource dataSource;

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    // PRODUCT REPORT

    public JasperPrint product() throws IOException, JRException {
        InputStream fileReport = new ClassPathResource("reports/product/product_list.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, getConnection());

        return jasperPrint;
    }
    public JasperPrint productParam(String name) throws IOException, JRException {
        InputStream fileReport = new ClassPathResource("reports/product/product_list_param.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);

        Map<String, Object> param = new HashMap<>();
        param.put("NAME", "%"+name+"%");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param, getConnection());

        return jasperPrint;
    }

    public JasperPrint productPieChart() throws IOException, JRException {
        InputStream fileReport = new ClassPathResource("reports/product/product_pie_chart.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, getConnection());

        return jasperPrint;
    }


    // NUTRITRION REPORT

    public JasperPrint nutrition() throws IOException, JRException {
        InputStream fileReport = new ClassPathResource("reports/nutrition/nutritionreport.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fileReport);

        Map<String, Object> params = new HashMap<>();
        params.put("firstName", "Abdillah");
        params.put("lastName", "Hamka");
        params.put("dob", "23/01/2023");
        params.put("age", 5);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        return jasperPrint;
    }

}
