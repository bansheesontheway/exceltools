package old.excel2csv;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class CsvParser {

    ArrayList<Csv> csvList;

    public CsvParser() {
        csvList = new ArrayList<>();
    }

    public ArrayList<Csv> getCsvList() {
        return this.csvList;
    }

    public void printCsvParser() {
        for (Csv csv : this.csvList) {
            csv.printCsv();
            System.out.println("-------------------------");
        }
    }

    public void parse(String fileName, String sheetName) throws FileNotFoundException, IOException {
        InputStream fis;
        BufferedReader br;
        String lineSplit[];
        String line;
        int numOfLine = 0;
        CsvColumn csvColumn;

        // Create a new Csv class for this file
        Csv csv;
        csv = new Csv(fileName, sheetName);

        fis = new FileInputStream(fileName);
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        while ((line = br.readLine()) != null) {
            // Split the line using comma
            lineSplit = line.split(",");
            // Deal with the line
            if (numOfLine == 0) {
                // First line for name of attribute
                for (String attr : lineSplit) {
                    // Create a new CsvColumn for this attribute
                    csvColumn = new CsvColumn(attr.trim());
                    // Add this CsvColumn into the Csv
                    csv.addColumn(csvColumn);
                }
            }
            else {
                // Data lines here
                for (int i = 0; i < lineSplit.length; i++) {
                    // Add this value into the csvColumn
                    csv.getCsvColumnList().get(i).addValue(lineSplit[i].trim());
                }
            }
            numOfLine++;
        }

        // Set the final tuple number and add this csv into the list
        csv.setNumOfTuple(numOfLine - 1);
        this.csvList.add(csv);

        // Done with the file
        br.close();
    }
}
