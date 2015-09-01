package old.crap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 * 23.07.15
 */
public class ReadExcelDemo {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream(new File("/home/tsv/temp/test/Донецьк.xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getLastRowNum() +1; i++) {
                Row row1 = sheet.getRow(i);
                Cell cell = row1.getCell(1);
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:
//                        System.out.println(cell.getStringCellValue());
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        System.out.println(cell.getStringCellValue());
//                }
                System.out.println(cell.getStringCellValue());
            }
            //Iterate through each rows one by one
//            Iterator<Row> rowIterator = sheet.iterator();
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
                //For each row, iterate through all the columns
//                Iterator<Cell> cellIterator = row.cellIterator();

//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    //Check the cell type and format accordingly
//                    switch (cell.getCellType()) {
//                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\n");
//                            break;
//                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + "\n");
//                            break;
//                    }
//                }
//                System.out.println("");
//            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
