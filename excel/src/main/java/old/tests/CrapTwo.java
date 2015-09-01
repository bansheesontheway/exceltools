package old.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 10.08.15
 */
public class CrapTwo {
    public static void main(String[] args) {
        String myString5 = "Т 21-10 Шевченкове-Балаклія-Первомайський-Кегичівка      км 139- км 143";
        String[] array = myString5.split(Pattern.quote("[ .,?!]+"));
//        String as = Arrays.asList(array).get(1);
        String temp[] = myString5.replaceAll("(^\\++)?(\\+)?(\\+*)?", "$2").split("\\+");
        System.out.println(Arrays.asList(temp));
        Arrays.asList(array).stream().forEach((String s) -> {
            System.out.println(s+"\n");
        });
    }

}
