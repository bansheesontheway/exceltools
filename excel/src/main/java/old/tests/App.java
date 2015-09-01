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
import java.util.regex.Pattern;

public class App {
    static final DataFormatter DF = new DataFormatter();
//    public static String testfile = "C:\\test\\24.07.2015\\Миколаїв.xlsx";
    public static String testfile = "/home/tsv/temp/excel/crap.xlsx";
    public static String outputfile = "/home/tsv/temp/excel/crapfixed.xlsx";

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static void memoryUsed() {
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory is bytes: " + memory);
        System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
    }

    public static void main(String[] args) throws InvalidFormatException, IOException {
        ArrayList<String> firstColumn = new ArrayList<>();
        ArrayList<String> firstColumnSplitted = new ArrayList<>();
        InputStream fileInputStream = new FileInputStream(testfile);
        Map<String, String[]> data = new TreeMap<>();
        int counter = 0;
        Workbook wb = WorkbookFactory.create(fileInputStream);
        Sheet sheet = wb.getSheetAt(1);
        Row row;
        Cell cell;
        boolean isNull = false;
        do {
            try {
                row = sheet.getRow(counter);
                cell = row.getCell(0);
//                System.out.println(cell.toString());
                if (!(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
                    firstColumn.add(cell.toString());
                }
                printNonEmptyCell(cell);
                counter++;

            } catch (Exception e) {
                isNull = true;
            }
        } while (isNull != true);
        fileInputStream.close();
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println(firstColumn);
        openDocumentAndIterateThroughFirstColumn(testfile, firstColumnSplitted);
        System.out.println("====================================");
        System.out.println(firstColumn);
        ArrayList<String> splitted = new ArrayList<>();
        for (String s:firstColumn) {
            if ((Pattern.compile("\\d+\\+\\d+").matcher(s).find()==true)) {
                System.out.println("!!!!SPLIT:");
//                splitString2(s);
                String[] tmp = splitString2(s);
                splitted.addAll(Arrays.asList(tmp));
            }
        }
        System.out.println("==============");
        System.out.println(splitted);
        memoryUsed();
        writeExcel(splitted);
//        writeExcel(firstColumn);

//        openDocumentAndIterateThroughFirstColumn(testfile, firstColumn);
//        System.out.println("firstColumn normal:"+(firstColumn));
//        for (int i = 0; i < firstColumn.size(); i++) {
//            String splitPattern = "\\(|км" + ", " + "\\d\\)$";
//            String s = firstColumn.get(i);
//            System.out.println(s.split(splitPattern));

//            String tmp = DF.formatCellValue(cell);

//            for (int j = 0; j < firstColumn.size(); j++) {
//                System.out.println(Arrays.asList(firstColumn[j]).toString().substring(1).replaceFirst("\\)", "").replace("км", "").replace(", ", "|").replace("]", "").replace(" ", ""));
//                firstColumnSplitted.add(s.split(splitPattern));

//            }
//        }
//        System.out.println(Arrays.asList(firstColumnSplitted));

    }

    public static void writeFile (Workbook workbook, String filename) {
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(outputfile));
            workbook.write(out);
            out.close();

            System.out.println(new File(outputfile).getAbsoluteFile()+" written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeExcel (ArrayList arrayList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee Data");
        Map<String, ArrayList<String>> data = new TreeMap<>();
        Map<String, String[]> data2 = new TreeMap<>();
        int counter = 0;
        int counter2 = 0;
        for (Object anArrayList : arrayList) {
//            if (!(Pattern.compile("\\d+\\+\\d+").matcher(String.valueOf(anArrayList)).find()==true)) {
                String s = String.valueOf(anArrayList);
                data.put(String.valueOf(counter++), new ArrayList(Arrays.asList(new String[]{s})));
//            } else {
//                String s = String.valueOf(anArrayList);
//                data2.put(String.valueOf(counter2++), new String[]{s});
//            }
        }
        Row keyRow = sheet.createRow(0);
        int row = 0;
        int col = 0;
        for (String key : data.keySet()) {
            Row row2 = sheet.createRow(row++);
            keyRow.createCell(col).setCellValue(key);
            ArrayList<String> values = data.get(key);
            for (int i = 0; i < values.size(); i++) {
                Row inlayRow = sheet.getRow(i);
                if (inlayRow == null) {
                    inlayRow = sheet.createRow(i);
                }
                if (!Pattern.compile("\\d+\\+\\d+").matcher(values.get(i)).find()) {
                    inlayRow.createCell(col).setCellValue(values.get(i));
                } else {
                    inlayRow.createCell(col).setCellValue(values.get(i));
                }

            }
            col++;
        }

//        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
//        data.put("2", arrayList.get());
//        data.put("3", new Object[]{2, "Lokesh", "Gupta"});
//        data.put("4", new Object[]{3, "John", "Adwards"});
//        data.put("5", new Object[]{4, "Brian", "Schultz"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        int rownum2 = 0;


//        for (String key : keyset) {
//            Row row = sheet.createRow(rownum++);
//            Object[] objArr = data.get(key);
//            int cellnum = 0;
//            row.createCell(col).setCellValue(key);
//            ArrayList<String> values = data.get(key);
//            for (int i = 0; i < values.length; i++) {
//                Row r = sheet.getRow(i+1);
//                if (r == null) { r = sheet.createRow(i+1); }
//                r.createCell(col).setCellValue(values.(i));
//            }
//            col++;
//            }
//            for (int i = 0; i < objArr.length; i++) {
//                Object obj = objArr[i];
//                Cell cell = row.createCell(cellnum++);
////                if (obj instanceof String || Pattern.compile("\\d+\\+\\d+").matcher(String.valueOf(String.valueOf(obj))).find() == true) {
////                    cell.setCellValue((String) obj);
////                }
//                if (obj instanceof String) {
//                    cell.setCellValue((String) obj);
//                } else if (obj instanceof Integer)
//                    cell.setCellValue((Integer) obj);
//            }
//        }
//        try {
//            //Write the workbook in file system
//            FileOutputStream out = new FileOutputStream(new File("C:\\test\\howtodoinjava_demo.xlsx"));
//            workbook.write(out);
//            out.close();
//            System.out.println("C:" + File.separator + "test" + File.separator + "howtodoinjava_demo.xlsx written successfully on disk.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        writeFile(workbook, outputfile);
    }
    public static void printMapV2 (Map <?, ?> map) {
        StringBuilder sb = new StringBuilder(128);
        sb.append("{");
        for (Map.Entry<?,?> entry : map.entrySet()) {
            if (sb.length()>1) {
                sb.append(", ");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        sb.append("}");
        System.out.println(sb);
    }

    public static void printTreeMap(Map<String, String[]> treeMap) {
        System.out.print( "TreeMap: " );

        // Use an Iterator to traverse the mappings in the TreeMap.  Note
        // that the mappings are in sorted order (with respect to the keys).
        Iterator iterator = treeMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.print( "(" + entry.getKey() + ": " +
                    (entry.getValue()).toString() + "), " );
        }
        System.out.println();
    }

    public static String[] splitString2(String string) {
        String tokens[] = null;
//        String splitPattern = "\\(|км" + ", ";
        String splitPattern = "км|(км)";
        tokens = string.split(splitPattern);
        System.out.println("Pattern: " + splitPattern + "\n");
        System.out.println("Our string: " + string + "\n");
        System.out.println("To tokens: ");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        }
        System.out.println("\n");
        return tokens;
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
