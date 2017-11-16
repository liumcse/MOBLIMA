package View;

import View.staff.MovieListing;
import View.staff.SystemSetting;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;

/**
 * This class represents the staff view.
 *
 * @version 1.0
 */
public class StaffView extends View {
    private boolean loggedIn;

    /**
     * Allocates a {@code StaffView} object and initializes it.
     */
    public StaffView() {
        loggedIn = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        if (!loggedIn) login();
        else displayMenu();
    }

    /**
     * This method is to prompt user to login by entering username and password
     */
    private void login() {
        System.out.println("Please login to access staff system.");

        String username = readString("Please enter your username: ");
        String password = readString("Please enter your password: ");

        if (authentication(username, password)) {
            loggedIn = true;
            System.out.println("Login successful!");
            displayMenu();
        }
        else {
            System.out.println("Invalid username or password.");
            destroy();
        }
    }

    private void displayMenu() {
        printHeader("Staff");
        printMenu("Welcome, please make a selection:",
                "1. Modify movie listing",
                "2. Configure system settings",
                "3. Logout", "");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                intent(this, new MovieListing());
                break;
            case 2:
                intent(this, new SystemSetting());
                break;
            case 3:
                loggedIn = false;
                System.out.println("You have logged out.");
                destroy();
                break;
        }
    }

}
