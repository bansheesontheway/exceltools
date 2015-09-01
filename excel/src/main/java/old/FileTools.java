package old;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;

public class FileTools {
    private static ArrayList<String> fileListing(String sourceDirectory) {
        File file = new File(sourceDirectory);
        String[] strings = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return dir.isDirectory();
            }
        });
        ArrayList<String> list = new ArrayList<String>();
        if (strings != null)
            for (int i = 0; i < strings.length; i++) {
                list.add(strings[i]);
            }
        Collections.sort(list);
        return list;
    }

    private static ArrayList<String> createFileListForEachDir(String foldername) {
        String extExcel2003 = "mp3";
        String extFLAC = "flac";
        /*creating filter for musical files*/
        FilenameFilter fileNameFilter = (dir, name) -> {
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
        };
        ArrayList<String> result = new ArrayList<>();
        File[] files = new File(foldername).listFiles(fileNameFilter);
        for (File file : files) {
            if (file.isFile()) {
                result.add(foldername + File.separator + file.getName());
            }
        }
        Collections.sort(result);
        return result;
    }
}
