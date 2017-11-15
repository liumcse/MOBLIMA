package Controller;

import Model.Constant.*;
import Model.Holiday;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Controller.CineplexManager.*;

/**
 * This class handles all input from standard input as well as some formatting methods.
 *
 * @version 1.0
 */
public class IOController {
    /**
     * This method is to read an integer from standard input whose value should be in a
     * certain range.
     * @param i the lower bound (inclusive) of the input
     * @param j the upper bound (inclusive) of the input
     * @return the input from standard input with specified range
     */
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

    /**
     * The method is to read a {@code String} from standard input.
     * @param message the message to be shown to the user
     * @return the input from standard input
     */
    public static String readString(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * This method is to read a {@code double} from standard input.
     * @param message the message to be shown to the user
     * @return the input from standard input
     */
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

    /**
     * This method is to generate multiple spaces with given size.
     * @param size the number of spaces to be generated
     * @return the spaces generated
     */
    public static String generateSpaces(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    /**
     * This method is to add line breaks for a {@code String} whose length exceeds a certain value
     * as well as adding spaces to the second line onwards.
     * @param input the String to be formatted
     * @param maxLineLength the maximum length a line can be
     * @param lengthOfSpace the number of spaces to be added to the second line onwards
     * @return the formatted String
     */
    public static String addLinebreaks(String input, int maxLineLength, int lengthOfSpace) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                output.append("\n");
                for (int i = 0; i < lengthOfSpace; i++) output.append(" ");
                lineLen = 0;
            }
            output.append(word).append(" ");
            lineLen += word.length();
        }
        return output.toString();
    }

    /**
     * This method is to read an Email address from standard input.
     * @param message the message to be shown to the user
     * @return the input from standard input with Email format
     */
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

    /**
     * This method is to map the {@code String} to respective {@code AgeRestriction}.
     * @param input the {@code String} to be mapped
     * @return the {@code AgeRestriction} the input mapped to
     */
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

    /**
     * This method is to map the {@code String} to respective {@code MovieStatus}.
     * @param input the {@code String} to be mapped
     * @return the {@code MovieStatus} the input mapped to
     */
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

    /**
     * This method is read a {@code String} with format MM-dd kk:mm from standard input and transform it to be a {@code Date}.
     * @param message the message to be shown to the user
     * @return the {@code Date} after formatting
     */
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

    /**
     * This method is read a {@code String} with format MM-dd from standard input and transform it to be a {@code Date}.
     * @param message the message to be shown to the user
     * @return the {@code Date} after formatting
     */
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

    /**
     * This method is to ask user for confirmation from standard input.
     * @param message the message to be shown to the user
     * @return true if the input from standard input is Y, false otherwise
     */
    public static boolean askConfirm(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().toUpperCase().equals("Y")) return true;
        else return false;
    }

    /**
     * This method is to print specified {@code String} to standard output.
     * @param menu the menu message to be written to standard output
     */
    public static void printMenu(String... menu) {
        for (String s : menu) {
            System.out.println(s);
        }
    }

    /**
     * This method is to print specified {@code String} to standard output.
     * @param header the header message to be written to standard output
     */
    public static void printHeader(String header) {
        int length = 65;
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

    /**
     * This method is to format a {@code Date} to a {@code String} with format MMMM dd, kk:mm.
     * @param time the {@code Date} to be formatted
     * @return the {@code String} formatted
     */
    public static String formatTimeMMddkkmm(Date time) {
        return new SimpleDateFormat("MMMM dd, kk:mm").format(time);
    }

    /**
     * This method is to format a {@code Date} to a {@code String} with format MMMM dd.
     * @param time the {@code Date} to be formatted
     * @return the {@code String} formatted
     */
    public static String formatTimeMMdd(Date time) {
        return new SimpleDateFormat("MMMM, dd").format(time);
    }

    /**
     * This method is to test whether a {@code Date} is a weekend
     * @param time the {@code Date} to be tested
     * @return true if the {@code Date} is a weekend, false otherwise
     */
    public static boolean isWeekend(Date time) {
        String whatDay = new SimpleDateFormat("EEEE").format(time);
        if (whatDay.equals("Saturday") || whatDay.equals("Sunday")) return true;
        else return false;
    }

    /**
     * This method is to test whether two {@code Date} equals in month and date
     * @param d1 the first {@code Date} to be compared
     * @param d2 the second {@code Date} to be compared
     * @return true if they equals in month and date, false otherwise
     */
    public static boolean dateEquals(Date d1, Date d2) {
        return formatTimeMMdd(d1).equals(formatTimeMMdd(d2));
    }


    /**
     * This method is used to round a double value to a specified decimal place.
     * @param value the value to be rounded
     * @param places the number of decimal places of the result
     * @return the result after rounding
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
