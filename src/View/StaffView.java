package View;

import Controller.CineplexManager;
import View.staff.StaffMovieView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StaffView {
    Scanner sc;

    public StaffView() {
        // login
        sc = new Scanner(System.in);
        System.out.println("Please login before continuing.\n");
        login();

    }


    /**
     * Login
     */
    private void login() {
        Scanner sc = new Scanner(System.in);
        String username, password;
        System.out.println("Please enter your username: ");
        username = sc.nextLine();
        System.out.println("Please enter your password: ");
        password = sc.nextLine();

        if (CineplexManager.authentication(username, password)) {
            System.out.println("Login successful!");
            displayMenu();
        }
        else {
            System.out.println("Invalid username or password, please retry.");
            login();
        }
    }

    private void displayMenu() {
        int choice = -1;

        while (choice != 3) {
            System.out.println("---Staff---");
            System.out.println("1. Modify movie listing");
            System.out.println("2. Configure system settings");
            System.out.println("3. Logout");

            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        new StaffMovieView();
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("You have logged out.");
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
