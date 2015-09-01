package old.excel2csv;

import java.util.ArrayList;

public class Csv {
    String fileName;
    String sheetName;
    int numOfTuple;
    ArrayList<CsvColumn> csvColumnList;

    public Csv(String fileName, String sheetName) {
        this.fileName = fileName;
        this.sheetName = sheetName;
        this.numOfTuple = 0;
        this.csvColumnList = new ArrayList<>();
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public int getNumOfTuple() {
        return this.numOfTuple;
    }

    public ArrayList<CsvColumn> getCsvColumnList() {
        return this.csvColumnList;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public void setSheetName(String name) {
        this.sheetName = name;
    }

    public void setNumOfTuple(int num) {
        this.numOfTuple = num;
    }

    public void addColumn(CsvColumn column) {
        this.csvColumnList.add(column);
    }

    public void printCsv() {
        System.out.println("fileName=" + this.fileName);
        System.out.println("sheetName=" + this.sheetName);
        System.out.println("numOfTuple=" + this.numOfTuple);
        for (CsvColumn csvColumn : this.csvColumnList) {
            csvColumn.printCsvColumn();
        }
    }

}
