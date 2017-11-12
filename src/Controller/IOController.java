package Controller;

import Model.Constant.*;
import Model.Holiday;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Controller.CineplexManager.*;

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

    public static double readDouble(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        double output;

        try {
            output = sc.nextDouble();
            return output;
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readDouble(message);
        }
    }

    public static String readEmail(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Pattern EMAIL_PATTERN = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = EMAIL_PATTERN.matcher(input);
        if (matcher.matches()) {
            return input;
        }
        else {
            System.out.println("Invalid Email address, try again.");
            return readEmail(message);
        }
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

    public static Date readTimeMMddkkmm(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        try {
            String input = readString(message);
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Try again.");
            return readTimeMMddkkmm(message);
        }
    }

    public static Date readTimeMMdd(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String input = readString(message);
            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Try again.");
            return readTimeMMdd(message);
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

    public static String formatTimeMMddkkmm(Date time) {
        return new SimpleDateFormat("MMMM dd, kk:mm").format(time);
    }

    public static String formatTimeMMdd(Date time) {
        return new SimpleDateFormat("MMMM, dd").format(time);
    }

    public static boolean isWeekend(Date time) {
        String whatDay = new SimpleDateFormat("EEEE").format(time);
        if (whatDay.equals("Saturday") || whatDay.equals("Sunday")) return true;
        else return false;
    }

    public static Holiday getHoliday(Date time) {
        HashMap<String, Holiday> holidayList = getHolidayList();
        return holidayList.get(formatTimeMMdd(time));
    }

    public static boolean dateEquals(Date d1, Date d2) {
        return formatTimeMMdd(d1).equals(formatTimeMMdd(d2));
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
