package View.staff;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;
import View.View;

public class Login extends View {
    @Override
    protected void start() {
        System.out.println("Please login to access staff system.");

        String username = readString("Please enter your username: ");
        String password = readString("Please enter your password: ");

        if (authentication(username, password)) {
            System.out.println("Login successful!");
            displayMenu();
        }
        else {
            System.out.println("Invalid username or password, please retry.");
            start();
        }
    }

    private void displayMenu() {
        printHeader("Staff");
        printMenu("1. Modify movie listing",
                "2. Configure system settings",
                "3. Logout");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                intent(this, new MovieListing());
                break;
            case 2:
                intent(this, new SystemSetting());
                break;
            case 3:
                System.out.println("You have logged out.");
                destroy();
                break;
        }
    }

}
