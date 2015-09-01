package old.tests;

import org.apache.log4j.Logger;

import java.util.Arrays;

public class Split {

    private static Logger log = Logger.getLogger(Split.class);


    public static void main(String args[]) {
        String string01 = "Т-02-20 км 0+000, км17+300";
        String string02 = "С 030608 М-07-Пісочне км. 0+000-1+500";
        String string03 = "М-04 Знам'янка - Луганськ - Ізварине (на Волгоград через Дніпропетровськ, Донецьк) км 155+000 - км 168+000, км 184+000 - км 178+000";
        String string04 = "Н-15\n" +
                "км 122+840 - км 199+150\n";
        String string05 = "Н-20\n" +
                "км 5+050 - км 10+305\n" +
                "км 19+860 - км 25+630\n" +
                "км 30+463 - км 39+350\n" +
                "км 50+870 - км 86+200\n" +
                "км 5+050 - км 10+305\n" +
                "км 160+000 - км 197+800\n" +
                "км 197+800 - км 221+980";
        String string06 = "Т-07-37 Хуст-Шаян-Вишково-Буштино з під\"їздом до сан.Шаян км 0+000-23+200";
        String string07 = "Н-10 Стрий-Мамалига км  30+600-37+900, 52+300-54+100, ";
        String string08 = "О100714 /Устимівка - Гребінки - Узин/ - +/М-05/ - Пологи - Терезине /км 0+000 - км 11+400/";
        String string09 = "М-12 Стрий-Тернопіль-Кіровоград-Знам'янка (через Вінницю) км 579 - км 604, км 640 - км 680, км 703 - км 714, км 742 - км 746";
        String string10 = "Миколаїв-Бібрка (км 0+000-13+900, 13+900-21+900, 24+500-40+738, 44+747-52+960, 52+960-60+585, 63+398-76+038, 76+038-95+830, 97+863-114+532, 114+532-125+620, 130+590-161+561)";
        log.getAllAppenders();
        String string11 = "Н-08 Бориспіль-Дніпропетровськ-Запоріжжя                     км 122 - 136";
        try {
            splitString2(string10);
        } catch (Exception e) {
            log.error("Error running split string method", e);
            e.printStackTrace();
        }
        try {
            splitString3(string10);
        } catch (Exception e) {
            log.error("Error running split string method", e);
            e.printStackTrace();
        }
        splitString(string01);
//        splitString(string02);
//        splitString(string03);
//        splitString(string04);
//        splitString(string05);
//        splitString(string06);
//        splitString(string07);
//        splitString(string08);
//        splitString(string09);
//        splitString(string10);
//        splitString(string11);
    }

    public static void splitString(String string) {
        String tokens[] = null;
        String splitPattern = "км|(км)";
        tokens = string.split(splitPattern);
        System.out.println("Pattern: " + splitPattern + "\n");
        System.out.println("Our string: " + string + "\n");
        System.out.println("To tokens: ");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        }
        System.out.println("\n");
    }
    public static void splitString2(String string) {
        String tokens[] = null;
        String splitPattern = "\\(|км"+", ";
        tokens = string.split(splitPattern);
        System.out.println("Pattern: " + splitPattern + "\n");
        System.out.println("Our string: " + string + "\n");
        System.out.println("To tokens: ");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        }
        System.out.println("\n");
    }
    public static void splitString3(String string) {
        String tokens[] = null;
        String splitPattern = "\\(|км"+", " + "\\d\\)$";
        tokens = string.split(splitPattern);
        System.out.println("Pattern: " + splitPattern + "\n");
        System.out.println("Our string: " + string + "\n");
        System.out.println("To tokens: ");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(Arrays.asList(tokens[i]).toString().substring(1).replaceFirst("\\)", "").replace("км", "").replace(", ", "|").replace("]", "").replace(" ", ""));
        }
        System.out.println("\n");
    }
}
