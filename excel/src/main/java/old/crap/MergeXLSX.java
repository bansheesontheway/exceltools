package old.crap;

import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.util.*;

/**
 * Created by tsv on 23.07.15.
 */
public class MergeXLSX {
    public static void main(String[] args) throws IOException {
        File resultFile = new File("/home/tsv/temp/test-result/result.xlsx");
        String folder = ("/home/tsv/temp/test-xlsx");
        System.out.println(listFileArray(folder));
        List<FileInputStream> fileInputStreams = listFileArray(folder);
        mergeExcelFiles(resultFile, listFileArray(folder));
    }
    public static List<FileInputStream> listFileArray(String folder) throws FileNotFoundException {

        File dir = new File(folder);
        if (!dir.isDirectory()) {
            System.out.println("Directory does not exists : " + folder);
        }

        // list out all the file name and filter by the extension
//        ArrayList<String> fileArrayList = new ArrayList<>(Arrays.asList(dir.list()));
        String[] list = dir.list();

        List<FileInputStream> fileNamesWithPaths = null;
        for (String file : list) {
            String temp = new StringBuffer(folder).append(File.separator).append(file).toString();
            File file1 = new File(temp);
            FileInputStream fileInputStream = new FileInputStream(file1);
            fileNamesWithPaths.addAll((Collection<? extends FileInputStream>) fileInputStream);
        }
        return fileNamesWithPaths;
    }
    public static void mergeExcelFiles(File file, List<FileInputStream> list) throws IOException {
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet(file.getName());

        for (FileInputStream fin : list) {
            HSSFWorkbook b = new HSSFWorkbook(fin);
            for (int i = 0; i < b.getNumberOfSheets(); i++) {
                copySheets(book, sheet, b.getSheetAt(i));
            }
        }

        try {
            writeFile(book, file);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected static void writeFile(HSSFWorkbook book, File file) throws Exception {
        FileOutputStream out = new FileOutputStream(file);
        book.write(out);
        out.close();
    }

    private static void copySheets(HSSFWorkbook newWorkbook, HSSFSheet newSheet, HSSFSheet sheet) {
        copySheets(newWorkbook, newSheet, sheet, true);
    }

    private static void copySheets(HSSFWorkbook newWorkbook, HSSFSheet newSheet, HSSFSheet sheet, boolean copyStyle) {
        int newRownumber = newSheet.getLastRowNum() + 1;
        int maxColumnNum = 0;
        Map<Integer, HSSFCellStyle> styleMap = (copyStyle) ? new HashMap<Integer, HSSFCellStyle>() : null;

        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            HSSFRow srcRow = sheet.getRow(i);
            HSSFRow destRow = newSheet.createRow(i + newRownumber);
            if (srcRow != null) {
                copyRow(newWorkbook, sheet, newSheet, srcRow, destRow, styleMap);
                if (srcRow.getLastCellNum() > maxColumnNum) {
                    maxColumnNum = srcRow.getLastCellNum();
                }
            }
        }
        for (int i = 0; i <= maxColumnNum; i++) {
            newSheet.setColumnWidth(i, sheet.getColumnWidth(i));
        }
    }

    public static void copyRow(HSSFWorkbook newWorkbook, HSSFSheet srcSheet, HSSFSheet destSheet, HSSFRow srcRow, HSSFRow destRow, Map<Integer, HSSFCellStyle> styleMap) {
        destRow.setHeight(srcRow.getHeight());
        for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
            HSSFCell oldCell = srcRow.getCell(j);
            HSSFCell newCell = destRow.getCell(j);
            if (oldCell != null) {
                if (newCell == null) {
                    newCell = destRow.createCell(j);
                }
                copyCell(newWorkbook, oldCell, newCell, styleMap);
            }
        }
    }

    public static void copyCell(HSSFWorkbook newWorkbook, HSSFCell oldCell, HSSFCell newCell, Map<Integer, HSSFCellStyle> styleMap) {
        if (styleMap != null) {
            int stHashCode = oldCell.getCellStyle().hashCode();
            HSSFCellStyle newCellStyle = styleMap.get(stHashCode);
            if (newCellStyle == null) {
                newCellStyle = newWorkbook.createCellStyle();
                newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
                styleMap.put(stHashCode, newCellStyle);
            }
            newCell.setCellStyle(newCellStyle);
        }
        switch (oldCell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getRichStringCellValue());
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                newCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            default:
                break;
        }
    }
}
