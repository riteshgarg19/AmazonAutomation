package amazonTestScript;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class ExcelReader
{
    public static void writeExcel(String filePath, String fileName, String sheetName, Object[][] writeData) {
        try {
            File file = new File(filePath + fileName);
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);
            int rownum = 0;
            for (Object[] writeDataArray : writeData)
            {
                Row row = sheet.createRow(rownum++);
                int cellnum = 0;
                for (Object obj : writeDataArray) {
                    Cell cell = row.createCell(cellnum++);
                    if(obj instanceof String)
                        cell.setCellValue((String)obj);
                    else if(obj instanceof Double)
                        cell.setCellValue((Double)obj);
                    else if(obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                }
            }
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
