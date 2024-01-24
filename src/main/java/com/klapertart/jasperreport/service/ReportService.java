package com.klapertart.jasperreport.service;

import com.klapertart.jasperreport.Model.Food;
import com.klapertart.jasperreport.Model.MacroNutrient;
import com.klapertart.jasperreport.Model.Nutrition;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        // load jasper file that was compiled
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
        // load raw jasper file
        InputStream fileReport = new ClassPathResource("reports/nutrition/nutritionreport.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(fileReport);

        InputStream fileFoodNutrition = new ClassPathResource("reports/nutrition/food_nutrition.jrxml").getInputStream();
        JasperReport jasperSRFoodNutrition = JasperCompileManager.compileReport(fileFoodNutrition);

        List<Nutrition> nutritionList = new ArrayList<>();
        nutritionList.add(Nutrition.builder().nutritionName("Sodium").total(220).goal(230).metric("mg").build());
        nutritionList.add(Nutrition.builder().nutritionName("Potassium").total(200).goal(350).metric("mg").build());
        nutritionList.add(Nutrition.builder().nutritionName("Calcium").total(62).goal(100).metric("%").build());
        nutritionList.add(Nutrition.builder().nutritionName("Iron").total(38).goal(100).metric("%").build());
        JRBeanCollectionDataSource nutritionDataSource = new JRBeanCollectionDataSource(nutritionList);

        List<MacroNutrient> macroNutrientList = new ArrayList<>();
        macroNutrientList.add(MacroNutrient.builder().macroNutrientName("Carbohydrates").macroNutrientValue(48).build());
        macroNutrientList.add(MacroNutrient.builder().macroNutrientName("Protein").macroNutrientValue(20).build());
        macroNutrientList.add(MacroNutrient.builder().macroNutrientName("Fat").macroNutrientValue(32).build());
        JRBeanCollectionDataSource macroNutrientDataSource = new JRBeanCollectionDataSource(macroNutrientList);

        List<Food> foodList = new ArrayList<>();
        foodList.add(Food.builder().foodName("banana").mealTime("breakfast").fat(0).carbohydrate(28).protein(1).build());
        foodList.add(Food.builder().foodName("avocado").mealTime("breakfast").fat(22).carbohydrate(13).protein(3).build());
        foodList.add(Food.builder().foodName("milk").mealTime("breakfast").fat(8).carbohydrate(12).protein(8).build());
        foodList.add(Food.builder().foodName("chicken").mealTime("lunch").fat(2).carbohydrate(0).protein(26).build());
        foodList.add(Food.builder().foodName("rice").mealTime("lunch").fat(0).carbohydrate(45).protein(26).build());
        JRBeanCollectionDataSource foodDataSource = new JRBeanCollectionDataSource(foodList);

        Map<String, Object> foodParameter = new HashMap<>();
        foodParameter.put("foodDataSet", foodDataSource);


        Map<String, Object> params = new HashMap<>();
        params.put("firstName", "Abdillah");
        params.put("lastName", "Hamka");
        params.put("dob", "23/01/2023");
        params.put("age", 5);
        params.put("nutritionDataSet", nutritionDataSource);
        params.put("macroNutrientDataSet", macroNutrientDataSource);
        params.put("foodParameter", foodParameter);
        params.put("foodReport", jasperSRFoodNutrition);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        return jasperPrint;
    }

}
