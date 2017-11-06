package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MovieGoerUI {
    Scanner sc;
    public MovieGoerUI() {
        sc = new Scanner(System.in);
        displayMenu();
    }

    private void displayMenu() {
        int choice = 0;

        while (choice != 3) {
            System.out.println("---Moviegoer---");
            System.out.println("Welcome, please make a selection:");
            System.out.println("1. List movies");
            System.out.println("2. View booking history");
            System.out.println("3. Go back");
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        new MovieListing();
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