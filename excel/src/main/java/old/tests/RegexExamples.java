package old.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 10.08.15
 */
public class RegexExamples {

    public static void main(String[] args) {
        String toSplit = "Т-02-02 км 7+490, км 201+429";
        Pattern pattern = Pattern.compile("\\d+\\+\\d+");
        Matcher matcher = pattern.matcher(toSplit);
//        String[] words = pattern.split(toSplit);
//        String[] words = pattern.
//        for (int i = 0, wordsLength = words.length; i < wordsLength; i++) {
//            String s = words[i];
//            System.out.println("Split using Pattern.split(): " + s);
//        }

//        if (matcher.matches()) {
//            System.out.println(matcher.group(0));
//        }

        String line = "Т-02-02 км 7+490, км 201+429";

        for (int j = 1; j > line.length();j++ ) {
            // For each match in the line, extract and print it.
            Matcher m = pattern.matcher(line);
//            m.find();
//            System.out.println(m.group(0));
            while (m.find()) {
                // Simplest method:
                // System.out.println(m.group(0));

                // Get the starting position of the text
                int start = m.start(0);
                // Get ending position
                int end = m.end(0);
                // Print whatever matched.
                // Use CharacterIterator.substring(offset, end);
                System.out.println(line.substring(start, end));
            }
        }
        // using Matcher.replaceFirst() and replaceAll() methods
//        pattern = Pattern.compile("1*2");
//        matcher = pattern.matcher("11234512678");
//        System.out.println("Using replaceAll: " + matcher.replaceAll("_"));
//        System.out.println("Using replaceFirst: " + matcher.replaceFirst("_"));
    }


}
