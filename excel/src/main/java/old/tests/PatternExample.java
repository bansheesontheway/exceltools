package old.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 10.08.15
 */
public class PatternExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(\\d+\\+\\d+)");
        Matcher matcher = pattern.matcher("MxxY");
        String str = "Т-02-02 км 7+490, км 201+429";
        System.out.println("Input String matches regex - " + str.matches("(\\d+\\+\\d+)"));
        // bad regular expression
//        pattern = Pattern.compile("*xx*");
    }

}
