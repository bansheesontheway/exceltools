package old.crap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 23.07.15
 */
public class ArrayListSerialization {
    public static void main(String[] args) {
        ArrayList<FileInputStream> fileInputStreams = null;
        for (int i = 0; i < fileInputStreams.size(); i++) {
            FileInputStream fileInputStream = fileInputStreams.get(i);
            try {
                FileInputStream fis = new FileInputStream("/home/tsv/temp/test.xlsx");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
