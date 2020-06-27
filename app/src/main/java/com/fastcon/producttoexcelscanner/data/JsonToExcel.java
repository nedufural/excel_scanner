package com.fastcon.producttoexcelscanner.data;

import com.fastcon.producttoexcelscanner.data.entity.remote.RetrieveDataResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class JsonToExcel {
    public static void jsonToExcel(List<RetrieveDataResponse.Item> jsonString) throws Exception {


        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Report");

        int rowCount = 0;
        for (int i = 0; i < jsonString.size(); ++i) {

            String Barcode = jsonString.get(i).getBarcode();
            String productName = jsonString.get(i).getProductName();
            String description = jsonString.get(i).getDescription();
            String volume = jsonString.get(i).getVolume();

            Row row = sheet.createRow(++rowCount);
            Cell cell1 = row.createCell(1);
            cell1.setCellValue(Barcode);
            Cell cell2 = row.createCell(2);
            cell2.setCellValue(productName);
            Cell cell3 = row.createCell(3);
            cell3.setCellValue(description);
            Cell cell4 = row.createCell(4);
            cell3.setCellValue(volume);
            System.out.println(Barcode + "\t" + productName + "\t" + volume); //want to excel file for this three field
        }

        FileOutputStream outputStream = new FileOutputStream(new File(android.os.Environment.getExternalStorageDirectory(), "Report.xlsx"));
        workbook.write(outputStream);
        outputStream.close();
        System.out.println("saved!");
    }
}
