package old.crap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.joda.time.LocalDate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class App {
//    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) throws IOException, InvalidFormatException {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Enter directory path: \n");
        String path = "/home/tsv/temp/test";
//        System.out.println("Enter extension: \n");
//        String fileext = input.next();
        String fileext = "xls";
        System.out.println("Current date: " + getLocalCurrentDate());
//        new ListXLSandXLSX().listFile(path, fileext);
        List<String> what = new ListXLSandXLSX().listFileArray(path, fileext);
//        System.out.println(Arrays.asList(what));

        /*for (String string : what) {
            File file = new File(string);
            System.out.println("Current file: "+string);
            listContentsOfFirstColumn(file);
        }*/
        File file = new File("/home/tsv/temp/test/Вінниця.xls");
//        System.out.println(extractExcelContentByColumnIndex(0, file).toString());
        for (String str : what ){
            System.out.println("current file: " + str);
            File file1 = new File(str);
            System.out.println((extractExcelContentByColumnIndex(0, file1)).toString());
        }

    }

    private static String getLocalCurrentDate() {

//        if (logger.isDebugEnabled()) {
//            logger.debug("getLocalCurrentDate() is executed!");
//        }

        LocalDate date = new LocalDate();
        return date.toString();

    }

    private static String listContentsOfFirstColumn(File file) throws IOException, InvalidFormatException {
//        XSSFWorkbook workbook = null;
        Workbook wb = WorkbookFactory.create(file);
//        try {
//            wb = new XSSFWorkbook(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }

        //Get first/desired sheet from the workbook
        Sheet sheet = wb.getSheetAt(0);
//        do {
            for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
                Row row1 = sheet.getRow(i);
                Cell cell = row1.getCell(0);
                if (cell.getCellType()==Cell.CELL_TYPE_NUMERIC) {
                    System.out.println(cell.getNumericCellValue());
                } else if (cell.getCellType()==Cell.CELL_TYPE_STRING) {
                    System.out.println(cell.getStringCellValue());
                } else if (cell.getCellType()==Cell.CELL_TYPE_BLANK) {
                    System.out.println("!empty cell");
                } else if (cell.getCellType()==Cell.CELL_TYPE_ERROR) {
                    System.out.println("!error cell");
                } else if (cell.getCellType()==Cell.CELL_TYPE_FORMULA) {
                    System.out.println(cell.getArrayFormulaRange());
                } else {
                    System.out.println("Error! for fuck sake!");
                }
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:
//                        System.out.println(cell.getStringCellValue());
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:
//                        System.out.println(cell.getStringCellValue());
//                }
//                if (cell.getStringCellValue() != null) {
//                    continue;
//                }
//                System.out.println(cell.getStringCellValue());
            }

//        } while (workbook.getNumberOfSheets() != 0);

        return null;
    }
    private long poiGetCellValue(XSSFCell cell){
        long x;
        if(cell.getCellType() == 0)
            x = (long)cell.getNumericCellValue();
        else if(cell.getCellType() == 1)
            x = Long.parseLong(cell.getStringCellValue());
        else
            x = -1;
        return x;
    }

    public static ArrayList<String> extractExcelContentByColumnIndex(int columnIndex, File file){
        ArrayList<String> columndata = null;
        try {
//            File f = new File(file);
            FileInputStream ios = new FileInputStream(file);
//            XSSFWorkbook workbook = new XSSFWorkbook(ios);
            Workbook wb = WorkbookFactory.create(ios);
//            XSSFSheet sheet = workbook.getSheetAt(0);
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            columndata = new ArrayList<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if(row.getRowNum() > 0){ //To filter column headings
                        if(cell.getColumnIndex() == columnIndex){// To match column index
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    columndata.add(cell.getNumericCellValue()+"");
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    columndata.add(cell.getStringCellValue());
                                    break;
                            }
                        }
                    }
                }
            }
            ios.close();
            System.out.println(columndata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columndata;
    }
}
