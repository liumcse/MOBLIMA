package View;

import View.moviegoer.MoviegoerMovieView;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the user interface for moviegoer.
 */

public class MovieGoerView {
    Scanner sc;
    public MovieGoerView() {
        sc = new Scanner(System.in);
        displayMenu();
    }

    private void displayMenu() {
        int choice = -1;

        while (choice != 3) {
            System.out.println("---Moviegoer---");
            System.out.println("Welcome, please make a selection:");
            System.out.println("1. Search or list movies");
            System.out.println("2. View booking history");
            System.out.println("3. Go back");
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        new MoviegoerMovieView();
                        break;
                    case 2:
                        // TODO
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid selection, try again:");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid selection, try again:");
                sc.nextLine();  // to flush the buffer
            }
        }
    }
}