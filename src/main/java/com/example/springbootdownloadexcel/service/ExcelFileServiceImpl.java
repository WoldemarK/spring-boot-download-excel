package com.example.springbootdownloadexcel.service;

import com.example.springbootdownloadexcel.model.Contact;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ExcelFileServiceImpl implements ExcelFileService{
    @Override
    public ByteArrayInputStream export(List<Contact> contacts) {
        try(Workbook workbook = new XSSFWorkbook()){
            Sheet sheet = workbook.createSheet("Contacts");

            Row row = sheet.createRow(0);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Cell cell = row.createCell(0);
            cell.setCellValue("First Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Last Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Email");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Phone Number");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Address");
            cell.setCellStyle(headerCellStyle);


            for(int i = 0; i < contacts.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(contacts.get(i).getFirstName());
                dataRow.createCell(1).setCellValue(contacts.get(i).getLastName());
                dataRow.createCell(2).setCellValue(contacts.get(i).getEmail());
                dataRow.createCell(3).setCellValue(contacts.get(i).getPhoneNumber());
                dataRow.createCell(4).setCellValue(contacts.get(i).getAddress());
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            return null;
        }
    }
}
