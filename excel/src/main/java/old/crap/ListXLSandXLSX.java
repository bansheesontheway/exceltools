package old.crap;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 23.07.15
 */
public class ListXLSandXLSX {

    private static final String FILE_DIR = "/home/tsv/temp";
    private static final String FILE_TEXT_EXT = ".xlsx";

    public static void main(String[] args) {
        new ListXLSandXLSX().listFile(FILE_DIR, FILE_TEXT_EXT);
    }

    public void listFile(String folder, String ext) {

        GenericExtFilter filter = new GenericExtFilter(ext);

        File dir = new File(folder);

        if (dir.isDirectory() == false) {
            System.out.println("Directory does not exists : " + FILE_DIR);
            return;
        }

        // list out all the file name and filter by the extension
        String[] list = dir.list(filter);

        if (list.length == 0) {
            System.out.println("no files end with : " + ext);
            return;
        }

        for (String file : list) {
            String temp = new StringBuffer(FILE_DIR).append(File.separator)
                    .append(file).toString();
            System.out.println("file : " + temp);
        }
    }


    public List<String> listFileArray(String folder, String ext) {

        GenericExtFilter filter = new GenericExtFilter(ext);
        File dir = new File(folder);
        if (!dir.isDirectory()) {
            System.out.println("Directory does not exists : " + FILE_DIR);
        }

        // list out all the file name and filter by the extension
//        ArrayList<String> fileArrayList = new ArrayList<>(Arrays.asList(dir.list()));
        String[] list = dir.list(filter);

        if (list.length == 0) {
            System.out.println("no files end with : " + ext);
        }

        List<String> fileNamesWithPaths = new ArrayList<String>();
        for (String file : list) {
            String temp = new StringBuffer(folder).append(File.separator).append(file).toString();
            fileNamesWithPaths.add(temp);
        }
        return fileNamesWithPaths;
    }

    public class GenericExtFilter implements FilenameFilter {

        private String ext;

        public GenericExtFilter(String ext) {
            this.ext = ext;
        }

        public boolean accept(File dir, String name) {
            return (name.endsWith(ext));
        }
    }

}
