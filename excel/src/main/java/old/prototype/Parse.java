package old.prototype;

import old.tests.App;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
* 01.09.15 
*/
public class Parse {
    private static final long MEGABYTE = 1024L * 1024L;
    public static String testfile = "/home/tsv/temp/excel/crap.xlsx";
    public static String outputfile = "/home/tsv/temp/excel/crapfixed.xlsx";

    public static void main(String[] args) {
        memoryUsed();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Employee Data");
        XSSFRow row;
        //This data needs to be written (Object[])
        Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
        empinfo.put("1", new Object[]{
                "EMP ID", "EMP NAME", "DESIGNATION"});
        empinfo.put("2", new Object[]{
                "tp01", "Gopal", "Technical Manager"});
        empinfo.put("3", new Object[]{
                "tp02", "Manisha", "Proof Reader"});
        empinfo.put("4", new Object[]{
                "tp03", "Masthan", "Technical Writer"});
        empinfo.put("5", new Object[]{
                "tp04", "Satish", "Technical Writer"});
        empinfo.put("6", new Object[]{
                "tp05", "Krishna", "Technical Writer"});
        //Iterate over data and write to sheet
        Set<String> keyid = empinfo.keySet();
        int rowid = 0;
        for (String key : keyid) {
            row = sheet.createRow(rowid++);
            Object[] objectArr = empinfo.get(key);
            int cellid = 0;
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }
        App.writeFile(workbook, outputfile);
    }

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public static void memoryUsed() {
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println(">>> memory used: " + bytesToMegabytes(memory) + " Mb.");
    }
}
