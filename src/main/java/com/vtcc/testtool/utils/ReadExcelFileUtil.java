package com.vtcc.testtool.utils;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

public class ReadExcelFileUtil {
    public static void read(String filePath) throws Exception {
        // Đọc một file XSL.
//        FileInputStream inputStream = new FileInputStream(new File(filePath));
        OPCPackage pkg = OPCPackage.open(new File(filePath));

        // Đối tượng workbook cho file XSL.
//        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        XSSFWorkbook workbook = new XSSFWorkbook(pkg);

        // Lấy ra sheet đầu tiên từ workbook
//        HSSFSheet sheet = workbook.getSheetAt(0);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Lấy ra Iterator cho tất cả các dòng của sheet hiện tại.
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Lấy Iterator cho tất cả các cell của dòng hiện tại.
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if(cell.getColumnIndex() > 0){
                    cell.setCellValue(cell.getStringCellValue() + " updated");
                }
                System.out.print(cell.getStringCellValue() + " " + cell.getColumnIndex() + "&" + cell.getRowIndex());
                System.out.print("\t|\t");

            }
            System.out.println("");
        }

        // Ghi file
        FileOutputStream out = new FileOutputStream(new File("/Users/tranminhhai/Downloads/test_updated.xlsx"));
        workbook.write(out);
        out.close();
    }

    public static void main(String[] args) throws Exception{
        read("/Users/tranminhhai/Downloads/test.xlsx");
    }

}
