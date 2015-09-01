package old.tests;

/**
 * 10.08.15
 */
public class StringMatcher {
    public static void main(String[] args) {
        String pattern = ".*[^u]\\d+\\..*";
        String line = "Т 21-10 Шевченкове-Балаклія-Первомайський-Кегичівка      км 139- км 143";
        if (line.matches(pattern)) {
            System.out.println(line + " matches \"" + pattern + "\"");
        } else {
            System.out.println("NO MATCH");
        }
    }
}
