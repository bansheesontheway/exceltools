package old;

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

public class MainApp {
    public static final String INPUTDIR = "C:\\test\\24.07.2015\\";

    public static void main(String[] args) throws IOException, InvalidFormatException {
        ArrayList<String> filelist = createFileListForEachDir(INPUTDIR);
        Collections.sort(filelist);
        System.out.println(Arrays.asList(filelist));
        /*for (String s : filelist) {
            File f = new File(s);
            if (isExcel2003(f)==true) {
                System.out.println(s+" > 2003");
                System.out.println(f.getName());
                listContentsOfFirstColumn2003(f);
            } else {
                System.out.println(s + " --> more than 2007");
                System.out.println(f.getName());
                listContentsOfFirstColumn2007(f);
            }
        }*/
        for (String s : filelist) {
            File file = new File(s);
            String extension = s.substring(s.lastIndexOf(".") + 1, s.length());
            String excel2003 = "xls";
            if (excel2003.equals(extension)) {
                System.out.println(s + " > 2003");
                System.out.println(file.getName());
                readXls(s);
            } else {
                System.out.println(s + " --> more than 2007");
                System.out.println(file.getName());
                listContentsOfFirstColumn2007(file);
            }
        }
    }

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
