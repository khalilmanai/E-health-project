package com.example.app.controllers;

import com.example.app.models.Livraison;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public void exportToExcel(List<Livraison> livraisons, String filePath) {
        // Create a new Workbook
        Workbook workbook = new XSSFWorkbook();
        // Create a Sheet
        Sheet sheet = workbook.createSheet("Livraison");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nom");
        headerRow.createCell(1).setCellValue("Adresse");
        headerRow.createCell(2).setCellValue("numero telephone");

        // Populate data rows
        int rowNum = 1;
        for (Livraison livraison : livraisons) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(livraison.getNom());
            row.createCell(1).setCellValue(livraison.getAdresse());
            row.createCell(2).setCellValue(livraison.getNum_telephone());
        }

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel file has been created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the workbook
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
