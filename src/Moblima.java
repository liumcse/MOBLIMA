import Controller.CineplexManager;
import View.*;

import static Controller.IOController.*;

/**
 * Main function is here.
 */

public class Moblima extends View {

    @Override
    protected void start() {
        // initialize CineplexManager
        boolean initialized = CineplexManager.initialize();
        if (!initialized) {
            System.out.println("Error: failed to read data, please check file integrity.");
            System.out.println("Application terminating...");
            return;
        }

        printHeader("MOvie Booking and LIsting Management Application (MOBLIMA)");
        printMenu("Welcome to MOBLIMA, please make a selection:",
                "1. I'm a moviegoer",
                "2. I'm a staff",
                "3. Exit application","");

        int choice = readChoice(1, 3);

        switch(choice) {
            case 1:
                intent(this, new MovieGoerView());
                break;
            case 2:
                intent(this, new StaffView());
                break;
            case 3:
                System.out.println("Bye, hope to see you again!");
                destroy();
                break;
            default:
                System.out.println("Invalid selection.");
        }
    }

    public static void main(String[] args) {
        new Moblima().start();
    }
}
