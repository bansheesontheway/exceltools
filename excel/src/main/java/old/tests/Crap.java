package old.tests;

import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Crap {
    public static void main(String[] args) {
//        String myString = "Hy my name is bob";
//        String[] words = myString.split(" ");
//        System.out.println(Arrays.toString(words));
        Pattern p = Pattern.compile(" ");
        String myString = "М-12 км 331+586, км 513+515";
        String myString2 = "Р-17 км 130+300- км 140+500";
        String myString3 = "С 030813 Моташівка-Маяки км 0+000-2+000";
        String myString4 = "М-04 Знам'янка - Луганськ - Ізварине (на Волгоград через Дніпропетровськ, Донецьк) км 88+000  - км 93+000, км 118+000 - км 125+000, км 140+000 - км 147+000";
        String myString5 = "Т 21-10 Шевченкове-Балаклія-Первомайський-Кегичівка      км 139- км 143";
        String myDelimiter = "[ .,?!]+";
        Pattern pattern = Pattern.compile(myDelimiter, Pattern.LITERAL);
        String[] words = p.split(myString);
        String[] result = pattern.split(myString);
        System.out.println(Arrays.toString(words));
        System.out.println(Arrays.toString(result));
        String[] tokens = myString.split(myDelimiter);
        String[] tokens2 = myString2.split(myDelimiter);
        String[] tokens3 = myString3.split(myDelimiter);
        String[] tokens4 = myString4.split(myDelimiter);
        String[] tokens5 = myString5.split(myDelimiter);
        System.out.println("+++: " + Arrays.asList(tokens));
        Arrays.asList(tokens).stream().forEach(s -> System.out.println(s));
        Arrays.asList(tokens2).stream().forEach(s -> System.out.println(s));
        Arrays.asList(tokens3).stream().forEach(s -> System.out.println(s));
        Arrays.asList(tokens4).stream().forEach(s -> System.out.println(s));
        Arrays.asList(tokens5).stream().forEach(s -> System.out.println(s));

        StringTokenizer st =new StringTokenizer("М-12 км 331+586, км 513+515");
        System.out.println("tokens count: " + st.countTokens());
        // iterate through st object to get more tokens from it while (st.hasMoreElements()) { String token = st.nextElement().toString(); System.out.println("token = " + token);}
    }


    public String[] split(String regex, int limit) {
        return Pattern.compile(regex).split((CharSequence) this, limit);
    }
}
