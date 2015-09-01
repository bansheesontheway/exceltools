package old.crap;

import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 23.07.15
 */
public class MergeExcelSheetsIntoOne {
    public static void main(String[] args) throws IOException {
        String folder = "/home/tsv/temp/test-xlsx";
        String resultFileName = "/home/tsv/temp/test-result/res.xlsx";
        File file = new File(resultFileName);
        List<String> list = listFileArray(folder);
        System.out.println(list.toString());
        mergeExcelFiles(file, list);
    }

    public static List<String> listFileArray(String folder) {

        File dir = new File(folder);
        if (!dir.isDirectory()) {
            System.out.println("Directory does not exists : " + folder);
        }

        // list out all the file name and filter by the extension
        String[] list = dir.list();

        List<String> fileNamesWithPaths = new ArrayList<String>();
        for (String file : list) {
            String temp = new StringBuffer(folder).append(File.separator).append(file).toString();
            fileNamesWithPaths.add(temp);
        }
        return fileNamesWithPaths;
    }

    public static void mergeExcelFiles(File file, List<String> list) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet(file.getName());

        for (String string : list) {
            XSSFWorkbook b = new XSSFWorkbook(string);
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

    protected static void writeFile(XSSFWorkbook book, File file) throws Exception {
        FileOutputStream out = new FileOutputStream(file);
        book.write(out);
        out.close();
    }

    private static void copySheets(XSSFWorkbook newWorkbook, XSSFSheet newSheet, XSSFSheet sheet) {
        copySheets(newWorkbook, newSheet, sheet, true);
    }

    private static void copySheets(XSSFWorkbook newWorkbook, XSSFSheet newSheet, XSSFSheet sheet, boolean copyStyle) {
        int newRownumber = newSheet.getLastRowNum() + 1;
        int maxColumnNum = 0;
        Map<Integer, XSSFCellStyle> styleMap = (copyStyle) ? new HashMap<Integer, XSSFCellStyle>() : null;

        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            XSSFRow srcRow = sheet.getRow(i);
            XSSFRow destRow = newSheet.createRow(i + newRownumber);
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

    public static void copyRow(XSSFWorkbook newWorkbook, XSSFSheet srcSheet, XSSFSheet destSheet, XSSFRow srcRow, XSSFRow destRow, Map<Integer, XSSFCellStyle> styleMap) {
        destRow.setHeight(srcRow.getHeight());
        for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
            XSSFCell oldCell = srcRow.getCell(j);
            XSSFCell newCell = destRow.getCell(j);
            if (oldCell != null) {
                if (newCell == null) {
                    newCell = destRow.createCell(j);
                }
                copyCell(newWorkbook, oldCell, newCell, styleMap);
            }
        }
    }

    public static void copyCell(XSSFWorkbook newWorkbook, XSSFCell oldCell, XSSFCell newCell, Map<Integer, XSSFCellStyle> styleMap) {
        if (styleMap != null) {
            int stHashCode = oldCell.getCellStyle().hashCode();
            XSSFCellStyle newCellStyle = styleMap.get(stHashCode);
            if (newCellStyle == null) {
                newCellStyle = newWorkbook.createCellStyle();
                newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
                styleMap.put(stHashCode, newCellStyle);
            }
            newCell.setCellStyle(newCellStyle);
        }
        switch (oldCell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getRichStringCellValue());
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case XSSFCell.CELL_TYPE_BLANK:
                newCell.setCellType(XSSFCell.CELL_TYPE_BLANK);
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            default:
                break;
        }
    }
}
