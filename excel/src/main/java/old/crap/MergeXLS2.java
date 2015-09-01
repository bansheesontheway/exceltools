package old.crap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by tsv on 23.07.15.
 */
public class MergeXLS2 {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        ListXLSandXLSX listXLSandXLSX = new ListXLSandXLSX();
        String folder, ext;
        folder = "/home/tsv/temp/test-xlsx";
        ext = "xlsx";
        List<String> list = listXLSandXLSX.listFileArray(folder, ext);
        for (String string:list) {
            File file = new File(string);
            Workbook workbook = WorkbookFactory.create(file);
        }
    }
}
