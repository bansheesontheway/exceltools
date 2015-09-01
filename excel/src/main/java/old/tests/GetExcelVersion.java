package old.tests;

import java.io.*;

public class GetExcelVersion {
    public static void main(String[] args) throws Exception {
        File file01 = new File("/home/tsv/temp/Вінниця.xls");
        File file02 = new File("/home/tsv/temp/Дніпрпетровськ.xlsx");
        if (isBinaryFile(file01)==true) {
            System.out.println("excel 2003");
        } else {
            System.out.println("excel 2007");
        }
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
}
