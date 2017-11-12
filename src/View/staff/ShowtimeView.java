package View.staff;

import Controller.CineplexManager;
import Model.*;
import View.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;
import static Controller.IOController.printHeader;
import static Controller.IOController.readString;

public class ShowtimeView extends View {
    Movie movie;

    public ShowtimeView(Movie movie) {
        this.movie = movie;
    }

    @Override
    protected void start() {
        displayMenu();
    }

    private void displayMenu() {
        printHeader("Show time");
        // TODO bug here, what is there's no showtime?
        ArrayList<Model.Showtime> showtimeList = getMovieShowtime(movie);
        if (showtimeList == null || showtimeList.isEmpty()) {
            printMenu("No showtime on that day.",
                    "",
                    "1. Add a show time",
                    "2. Go back", "");
            int choice = readChoice(1, 2);
            if (choice == 1) {
                addShowtime();
            }
            else {
                destroy();
            }
        }
        else {
            Collections.sort(showtimeList, new Comparator<Model.Showtime>() {
                @Override
                public int compare(Model.Showtime o1, Model.Showtime o2) {
                    return o1.getCinema().getCineplex().toString().compareTo(o2.getCinema().getCineplex().toString());
                }
            });

            int index = 0;
            for (Model.Showtime s : showtimeList) printMenu(++index + ": " + s);

            printMenu((index + 1) + ". Go back",
                    "Please choose a showtime.",
                    "To add a showtime, enter 0:", "");

            int choice = readChoice(0, index + 1);
            if (choice == 0) addShowtime();
            else if (choice == index + 1) destroy();
            else {
                Showtime showtime = showtimeList.get(choice - 1);
                displayShowtimeDetailMenu(showtime);
            }
        }
    }

    private void displayShowtimeDetailMenu(Showtime showtime) {
        printHeader(showtime.toString());
        printMenu(showtime.getDetails(),
                "1. Modify cineplex/cinema",
                "2. Modify time",
                "3. Remove the showtime",
                "4. Go back", "");

        int choice = readChoice(1, 4);
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                if (askConfirm("Are you sure to remove the showtime?",
                        "Enter Y to confirm, N to cancel:")) {
                    try {
                        CineplexManager.removeShowtime(showtime);  // TODO does it work?
                        System.out.println("The showtime has been removed.");
                    } catch (IOException ex) {
                        System.out.println("Failed to remove showtime");
                    }
                    displayMenu();
                }
            case 4:
                displayMenu();
                break;
        }
    }

    // TODO modify here
    protected void addShowtime() {
        Cinema cinema;

        printHeader("Add showtime");

        // get cinema
        String input = readString("Enter cinema code (enter \"help\" to look up cinema code)");
        if (input.equals("help")) {
            intent(this, new CinemaList());
            displayMenu();
            return;
        }
        else cinema = getCinemaByCode(input);  // TODO may get null

        // get time
        Date time = readTimeMMddkkmm("Enter the time for the show",
                "Format: MM-DD HH:MM (e.g. 12-25 09:30)");

        // create showtime object
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setCinema(cinema);
        showtime.setTime(time);

        try {
            CineplexManager.addShowtime(showtime);
            System.out.println("Successfully added showtime.");
        } catch (IOException ex) {
            System.out.println("Failed to add showtime");
        }
        finally {
            displayMenu();
        }
    }
}
