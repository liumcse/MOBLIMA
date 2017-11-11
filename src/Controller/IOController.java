package Controller;

import Model.Constant.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * IOController class to handle all the input from users and some output
 */
public class IOController {
    public static int readChoice(int i, int j) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readChoice(i, j);
        }

        if (choice < i || choice > j) {
            System.out.println("Invalid input, try again.");
            return readChoice(i, j);
        }

        return choice;
    }

    public static String readString(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static AgeRestriction readAgeRestriction(String input) {
        switch (input.toUpperCase()) {
            case "G":
                return AgeRestriction.G;
            case "PG":
                return AgeRestriction.PG;
            case "PG13":
                return AgeRestriction.PG13;
            case "NC16":
                return AgeRestriction.NC16;
            case "M18":
                return AgeRestriction.M18;
            case "R21":
                return AgeRestriction.R21;
            default:
                return null;
        }
    }

    public static MovieStatus readMovieStatus(String input) {
        switch (input.toUpperCase()) {
            case "COMING SOON":
                return MovieStatus.COMING_SOON;
            case "NOW SHOWING":
                return MovieStatus.NOW_SHOWING;
            case "END OF SHOWING":
                return MovieStatus.END_OF_SHOWING;
            default:
                return null;
        }
    }

    // TODO month is not correctly displayed
    public static Date readTime(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-DD kk:mm");
        try {
            String input = readString(message);
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Try again.");
            return readTime(message);
        }
    }

    public static boolean askConfirm(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().toUpperCase().equals("Y")) return true;
        else return false;
    }

    public static void printMenu(String... menu) {
        for (String s : menu) {
            System.out.println(s);
        }
    }

    public static void printHeader(String header) {
        int length = 50;
        for (int i = 0; i < length; i++) System.out.print("-");
        System.out.println();

        int indent = (length - header.length()) / 2;
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.print(header);
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println();

        for (int i = 0; i < length; i++) System.out.print("-");
        System.out.println();
    }

    // TODO month is not correctly formatted
    public static String formatTime(Date time) {
        return new SimpleDateFormat("MM-DD kk:mm").format(time).toString();
    }

    /**
     * This method is used to round a double value to a specified decimal place
     * @param value
     * @param places
     * @return
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
