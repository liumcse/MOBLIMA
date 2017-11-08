import Controller.CineplexManager;
import View.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main function is here.
 */

public class Moblima {
    public static void main(String[] args) {
        // initialize CineplexManager
        boolean initialized = CineplexManager.initialize();
        if (!initialized) {
            System.out.println("Error: failed to read data, application terminating...");
            return;
        }

        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while (choice != 3) {
            System.out.println("Welcome to MOBLIMA, please make a selection:");
            System.out.println("1. I'm a moviegoer");
            System.out.println("2. I'm a staff");
            System.out.println("3. Exit application");

            try {
                choice = sc.nextInt();
                switch(choice) {
                    case 1:
                        new MovieGoerView();
                        break;
                    case 2:
                        new StaffView();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid selection.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid selection.");
                sc.nextLine();  // to flush the buffer
                continue;
            }
        }
    }
}
