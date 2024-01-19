package com.klapertart.jasperreport.controller;

import com.klapertart.jasperreport.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tritr
 * @since 1/19/2024
 */

@Controller
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Autowired
    private HttpServletResponse response;

    @GetMapping("/products")
    public void getProductReport() throws IOException, JRException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"product_list.pdf\"");
        JasperPrint jasperPrint = reportService.product();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
    @GetMapping("/products/param")
    public void getProductReportParam(@RequestParam("name") String name) throws IOException, JRException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"product_list.pdf\"");
        JasperPrint jasperPrint = reportService.productParam(name);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @GetMapping("/products/chart/pie")
    public void getProductPie() throws IOException, JRException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"product_pie_chart.pdf\"");
        JasperPrint jasperPrint = reportService.productPieChart();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }


    @GetMapping("/nutrition")
    public void getNutritionReport() throws IOException, JRException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"nutrition.pdf\"");
        JasperPrint jasperPrint = reportService.nutrition();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

}
