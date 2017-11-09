package Controller;

import Model.Cinema;
import Model.Movie;
import Model.Seat;
import Model.Showtime;

import java.util.InputMismatchException;
import java.util.Scanner;

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

    public static void printMenu(String... menu) {
        for (String s : menu) {
            System.out.println(s);
        }
    }

    public static void printHeader(String header) {
        int length = 30;
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

    public static void printBookingDetail(Seat seat) {
        Showtime showtime = seat.getShowtime();
        Movie movie = showtime.getMovie();
        Cinema cinema = showtime.getCinema();

        System.out.println(movie.getTitle() + " (" + cinema.getMovieType() + ")");
        if (cinema.isPlatinum()) {
            System.out.println("Platinum cinema");
        }
        System.out.println(movie.getAgeRestriction());
        System.out.println("Showing on " + showtime.getTime());  // TODO format getTime()
        System.out.println(cinema);   // TODO format getCinema()

        System.out.println();
    }
}
