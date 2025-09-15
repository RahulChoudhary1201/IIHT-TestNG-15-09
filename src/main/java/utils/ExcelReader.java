package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] getDataFromExcel(String sheetName) throws FileNotFoundException {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/testData/testData15.xlsx");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Workbook workbook = getWorkBook(file, fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            Object[][] data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = (row != null) ? row.getCell(j) : null;
                    assert cell != null;
                    data[i - 1][j] = cell.toString();
                }
            }
            workbook.close();
            return data;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private static Workbook getWorkBook(File file, FileInputStream inputStream) throws IOException {
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else if (fileName.endsWith(".xls")) {
            return new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("Unsupported file format. Only .xlsx and .xls files are supported.");
        }
    }

}
