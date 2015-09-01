package old.tests;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class App {
    static final DataFormatter DF = new DataFormatter();
    public static String testfile = "C:\\test\\24.07.2015\\Миколаїв.xlsx";

    public static void main(String[] args) throws InvalidFormatException, IOException {
        ArrayList<String> firstColumn = new ArrayList<>();
        ArrayList<String> firstColumnSplitted = new ArrayList<>();
        openDocumentAndIterateThroughFirstColumn(testfile, firstColumn);
        System.out.println(Arrays.asList("firstColumn normal" + firstColumn));
        for (int i = 0; i < firstColumn.size(); i++) {
            String splitPattern = "\\(|км" + ", " + "\\d\\)$";
            String s = firstColumn.get(i);
            System.out.println(s.split(splitPattern));

//            String tmp = DF.formatCellValue(cell);

//            for (int j = 0; j < firstColumn.size(); j++) {
//                System.out.println(Arrays.asList(firstColumn[j]).toString().substring(1).replaceFirst("\\)", "").replace("км", "").replace(", ", "|").replace("]", "").replace(" ", ""));
//                firstColumnSplitted.add(s.split(splitPattern));

//            }
        }
//        System.out.println(Arrays.asList(firstColumnSplitted));

    }

    public static void splitString2(String string) {
//        String tokens[] = null;
        String splitPattern = "\\(|км" + ", ";
//        tokens = string.split(splitPattern);
//        System.out.println("Pattern: " + splitPattern + "\n");
//        System.out.println("Our string: " + string + "\n");
//        System.out.println("To tokens: ");
//        for (int i = 0; i < tokens.length; i++) {
//            System.out.println(tokens[i]);
//        }
//        System.out.println("\n");
    }

    public static void splitString3(String string) {
        String tokens[] = null;
        String splitPattern = "\\(|км" + ", " + "\\d\\)$";
        tokens = string.split(splitPattern);
        System.out.println("Pattern: " + splitPattern + "\n");
        System.out.println("Our string: " + string + "\n");
        System.out.println("To tokens: ");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(Arrays.asList(tokens[i]).toString().substring(1).replaceFirst("\\)", "").replace("км", "").replace(", ", "|").replace("]", "").replace(" ", ""));
        }
        System.out.println("\n");
    }

    public static void openDocumentAndIterateThroughFirstColumn(String testfile, ArrayList arrayList) throws IOException, InvalidFormatException {
        InputStream fileInputStream = new FileInputStream(testfile);
        int counter = 0;
        Workbook wb = WorkbookFactory.create(fileInputStream);
        Sheet sheet = wb.getSheetAt(0);
        Row row;
        Cell cell;
        boolean isNull = false;
        do {
            try {
                row = sheet.getRow(counter);
                cell = row.getCell(0);
//                System.out.println(cell.toString());
                if (!(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
                    arrayList.add(cell.toString());
                }
                printNonEmptyCell(cell);
                counter++;
            } catch (Exception e) {
                isNull = true;
            }
        } while (isNull != true);
        fileInputStream.close();
    }

    public static void printNonEmptyCell(Cell cell) {
        if (!(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
            System.out.println(cell.toString());
        }
    }

    /*
    * Strategies for each town separately
    * pain in the ass
    */
    public static void strategyVinnitsya() {

    }

    public static void strategyVolin() {

    }

    public static void strategyDnipropetrovsk() {

    }

    public static void strategyDonetsk() {

    }

    public static void strategyZhitomir() {

    }

    public static void strategyZakarpattya() {

    }

    public static void strategyZaporizhya() {

    }

    public static void strategyIvanoFrankivsk() {

    }

    public static void strategyKyiv() {

    }

    public static void strategyKirovograd() {

    }

    public static void strategyLuhansk() {

    }

    public static void strategyLviv() {

    }

    public static void strategyMikolayiv() {

    }

    public static void strategyOdesa() {

    }

    public static void strategyPoltava() {

    }

    public static void strategyRivne() {

    }

    public static void strategySumi() {

    }

    public static void strategyTernopil() {

    }

    public static void strategyKharkiv() {

    }

    public static void strategyKherson() {

    }

    public static void strategyKhmelnitskiy() {

    }

    public static void strategyCherkasi() {

    }

    public static void strategyChernivtsi() {

    }

    public static void strategyChernihiv() {

    }

//    public static void main(String[] args) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream(new File(testfile));
//        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);
//        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
//        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
//        Iterator rowIterator = hssfSheet.iterator();
//        while(rowIterator.hasNext()) {
//
//        }
//
//    }

    private static void listContentsOfFirstColumn2003Cell(File file) throws IOException, InvalidFormatException {
//        HSSFWorkbook myWorkBook = new HSSFWorkbook(f);
//        Sheet sheet = wb.getSheetAt(0);
    }

    public static void readXls(String fileName) {
        try {
            FileInputStream myInput = new FileInputStream(fileName);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            //Iterator colIter = mySheet.colIterator();
            Iterator rowIter = mySheet.rowIterator();
            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                Vector cellStoreVector = new Vector();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    cellStoreVector.addElement(myCell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String listContentsOfFirstColumn2003(File file) throws IOException, InvalidFormatException {
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
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Row row1 = sheet.getRow(i);
            Cell cell = row1.getCell(0);
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                System.out.println(cell.getNumericCellValue());
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                System.out.println(cell.getStringCellValue());
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                System.out.println("!empty cell");
            } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                System.out.println("!error cell");
            } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
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

    private static String listContentsOfFirstColumn2007(File file) throws IOException, InvalidFormatException {
//        XSSFWorkbook workbook = null;
        String filename = file.getName();
//        OPCPackage pkg = OPCPackage.open(new File(filename));
//        Workbook wb = WorkbookFactory.create(file);
        XSSFWorkbook wb = new XSSFWorkbook(file);
//        try {
//            wb = new XSSFWorkbook(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidFormatException e) {
//            e.printStackTrace();
//        }

        //Get first/desired sheet from the workbook
        XSSFSheet xssfSheet = wb.getSheetAt(0);
//        Sheet sheet = wb.getSheetAt(0);
//        do {
        for (int i = 1; i < xssfSheet.getLastRowNum() + 1; i++) {
            Row row1 = xssfSheet.getRow(i);
            Cell cell = row1.getCell(0);
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                System.out.println(cell.getNumericCellValue());
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                System.out.println(cell.getStringCellValue());
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                System.out.println("!empty cell");
            } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                System.out.println("!error cell");
            } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
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
//        pkg.close();
        return null;
    }

    public static boolean isBinaryFile(File f) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(f, "r");
        long n = raf.readInt();
        raf.close();
        if (n == 0x504B0304)
            return true;
        else
            return false;
    }

    public static boolean isExcel2003(File f) {
        String extExcel2003 = "xls";
        String extFLAC = "xlsx";
        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.lastIndexOf('.') > 0) {
                    // get last index for '.' char
                    int lastIndex = name.lastIndexOf('.');
                    // get extension
                    String str = name.substring(lastIndex);
                    // match path name extension
                    if (str.equals(".xls")) {
                        return true;
                    }
                }
                return false;
            }
        };
        return true;
    }

    private static ArrayList<String> createFileListForEachDir(String foldername) {
        String extExcel2003 = "xls";
        String extFLAC = "xlsx";
        /*creating filter for musical files*/
        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.lastIndexOf('.') > 0) {
                    // get last index for '.' char
                    int lastIndex = name.lastIndexOf('.');
                    // get extension
                    String str = name.substring(lastIndex);
                    // match path name extension
                    if (str.equals(".xls") || str.equals(".xlsx")) {
                        return true;
                    }
                }
                return false;
            }
        };
        ArrayList<String> result = new ArrayList<>();
        File[] files = new File(foldername).listFiles(fileNameFilter);
        for (File file : files) {
            if (file.isFile()) {
                result.add(foldername + "/" + file.getName());
            }
        }
        Collections.sort(result);
        return result;
    }
}
