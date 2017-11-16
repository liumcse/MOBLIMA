package View.staff;

import Controller.CineplexManager;
import Model.Cinema;
import View.View;

import java.io.IOException;
import java.util.ArrayList;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;
import static Model.Constant.*;

/**
 * This class represents the cinema list view.
 *
 * @version 1.0
 */
public class CinemaList extends View {
    private boolean help;

    /**
     * Allocates a {@code CinemaList} object and initializes it specified by {@code args}.
     * @param args the parameter that decides which menu to display.
     */
    CinemaList(String args) {
        help = args.equals("help");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        if (help) displayCinemaListMenu();
        else displayMenu();
    }

    /**
     * The method is to display the main menu of cinema list.
     */
    private void displayMenu() {
        printHeader("Configure cinemas");
        printMenu("1. List cinemas",
                "2. Add cinemas",
                "3. Go back", "");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                displayCinemaListMenu();
                break;
            case 2:
                addCinema();
                break;
            case 3:
                break;
        }
        destroy();
    }

    /**
     * This method is to display a list of cineplex.
     */
    private void displayCinemaListMenu() {
        int index = 0;
        for (Cineplex c : Cineplex.values()) {
            index++;
            System.out.println(index + ": " + c);
        }

        printMenu("Choose a cineplex to view cinema codes:");
        int choice = readChoice(1, index);
        displayCinemaList(Cineplex.values()[choice - 1]);
    }

    /**
     * This method is to display the cinema list specified by cineplex.
     * @param cineplex the cineplex of the cinema
     */
    private void displayCinemaList(Cineplex cineplex) {
        printHeader(cineplex.toString());
        ArrayList<Cinema> cinemaList = getCinemaList(cineplex);

        if (cinemaList == null) {
            printMenu("No cinema at the cineplex.",
                    "Press ENTER to go back", "");
            readString();
        }
        else {
            for (Cinema cinema : getCinemaList(cineplex)) {
                System.out.print(cinema + "(" + (cinema.is3D() ? "3D" : "Digital") + ") ");
            }
            System.out.println();
        }

        if (help) destroy();
        else displayMenu();
    }

    /**
     * This method is to add a cinema.
     */
    private void addCinema() {
        int index = 0;
        for (Cineplex c : Cineplex.values()) {
            index++;
            System.out.println(index + ": " + c);
        }

        printMenu("Choose a cineplex to add the cinema:");
        int choice = readChoice(1, index);
        Cineplex cineplex = Cineplex.values()[choice - 1];

        // isPlatinum
        boolean isPlatinum = askConfirm("Is this a platinum cinema?",
                "Enter Y for yes, N for no:");
        // movieType
        boolean is3D = askConfirm("Is this cinema for 3D movies?",
                "Enter Y for yes, N for no");
        // basePrice
        double basePrice = readDouble("What's the base price for the cinema?",
                "(Weekday price = base price * 1.2, senior citizens enjoy 50% off)",
                "You are advised to set a higher base price for 3D and platinum cinemas");
        // cinemaCode
        String code = readString("Enter the cinema code",
                "e.g. ABC (do not enter the same cinema code for two different cinemas)");

        // create cinema
        Cinema cinema = new Cinema(cineplex, isPlatinum, is3D, code, basePrice);
        try {
            CineplexManager.addCinema(cinema);
            System.out.println("Successfully added the cinema.");
        } catch (IOException ex) {
            System.out.println("Failed to add the cinema.");
        } finally {
            destroy();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void destroy() {
        if (getPrevView().getClass() == ShowtimeView.class) ((ShowtimeView)getPrevView()).addShowtime();
        else super.destroy();
    }
}
