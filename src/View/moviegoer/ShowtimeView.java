package View.moviegoer;

import Controller.IOController;
import Model.Constant;
import Model.Movie;
import Model.Seat;
import Model.Showtime;
import View.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import static Controller.CineplexManager.getMovieShowtime;
import static Controller.IOController.*;

public class ShowtimeView extends View {
    private Movie movie;

    ShowtimeView(Movie movie) {
        this.movie = movie;
    }

    @Override
    protected void start() {
        displayMenu();
    }

    private void displayMenu() {
        Date today = new Date();
        Date tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
        Date afterTomorrow = new Date(new Date().getTime() + 2* 24 * 60 * 60 * 1000);
        Date dateChosen;

        if (movie.getMovieStatus().equals(Constant.MovieStatus.NOW_SHOWING)) {
            printMenu("1. " + formatTimeMMdd(today) + " (today)",
                    "2. " + formatTimeMMdd(tomorrow) + " (tomorrow)",
                    "3. " + formatTimeMMdd(afterTomorrow) + " (after tomorrow)",
                    "Please choose a date:", "");
            switch (readChoice(1, 3)) {
                case 1:
                    dateChosen = today;
                    break;
                case 2:
                    dateChosen = tomorrow;
                    break;
                default:
                    dateChosen = afterTomorrow;
                    break;
            }
        }
        else {
            printMenu("1. " + formatTimeMMdd(tomorrow),
                    "2. " + formatTimeMMdd(afterTomorrow),
                    "Please choose a date:", "");
            switch (readChoice(1, 2)) {
                case 1:
                    dateChosen = tomorrow;
                    break;
                default:
                    dateChosen = afterTomorrow;
                    break;
            }
        }

        printHeader("Showtime on " + formatTimeMMdd(dateChosen));

        ArrayList<Showtime> showtimeList = new ArrayList<>();
        showtimeList.sort(Comparator.comparing(o -> o.getCinema().getCineplex().toString()));

        if (getMovieShowtime(movie) != null) {
            for (Showtime s : getMovieShowtime(movie)) {
                if (dateEquals(s.getTime(), dateChosen)) showtimeList.add(s);
            }
        }

        if (showtimeList.isEmpty()) {
            printMenu("No showtime on that day.",
                    "Press ENTER to go back");
            readString();
            destroy();
            return;
        }

        int index = 0;
        for (Showtime s : showtimeList) {
            System.out.println(++index + ": " + s);
        }

        System.out.println("Please choose a showtime (enter 0 to go back):");

        System.out.println();
        int choice = readChoice(0, showtimeList.size());
        if (choice == 0) {
            destroy();
            return;
        }

        Showtime showtime = showtimeList.get(choice - 1);
        displayShowtimeDetailMenu(showtime);

    }

    private void displayShowtimeDetailMenu(Showtime showtime) {
        printHeader(showtime.toString());
        printMenu("1. Check seat availability",
                "2. Book seat",
                "3. Check price",
                "4. Go back", "");

        int choice = IOController.readChoice(1, 4);
        switch (choice) {
            case 1:
                displaySeat(showtime.getSeats());
                displayShowtimeDetailMenu(showtime);
                break;
            case 2:
                displaySeat(showtime.getSeats());
                displayBookSeatMenu(showtime);
                break;
            case 3:
                displayPrice(showtime);
                break;
            case 4:
                displayMenu();
                break;
        }


    }

    private void displayPrice(Showtime showtime) {
        double basePrice = showtime.getCinema().getBasePrice();
        Movie movie = showtime.getMovie();
        printHeader("Ticket price for " + movie.getTitle() + " (" + (showtime.getCinema().is3D() ? "3D" : "Digital") + ")");
        System.out.printf("\t\t\t\t\tWeekdays\t\tWeekends\n" +
                "Regular Citizens\t%.2f\t\t\t%.2f\n" +
                "Senior Citizens\t\t%.2f\t\t\t%.2f\n\n", basePrice, basePrice * 1.2, basePrice * 0.5, basePrice * 0.5 * 1.2);
        System.out.println("Note that price may change on holidays.");
        System.out.println("Please refer to the price when making payment.");
        System.out.println();
        readString("Press ENTER to go back");
        displayShowtimeDetailMenu(showtime);
    }

    private void displaySeat(Seat[][] seats) {
        System.out.println("                    -------Screen------");
        System.out.println("     1  2  3  4  5  6  7  8     9 10 11 12 13 14 15 16");
        for (int row = 0; row <= 8; row++) {
            System.out.print(row + 1 + "   ");
            for (int col = 0; col <= 16; col++) {
                if (seats[row][col] == null) System.out.print("   ");
                else System.out.print(seats[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void displayBookSeatMenu(Showtime showtime) {
        int row, col;

        System.out.println("Enter the row (1 - 9) of the seat:");
        row = IOController.readChoice(1, 9);
        System.out.println("Enter the column (1 - 16) of the seat:");
        col = IOController.readChoice(1, 16);

        if (showtime.getSeatAt(row, col) == null) {
            System.out.println("No such seat. Please choose another one.");
            displayBookSeatMenu(showtime);
        }
        else if (showtime.getSeatAt(row, col).isBooked()) {
            System.out.println("The seat has been booked. Please choose another one.");
            displayBookSeatMenu(showtime);
        }
        else {
            System.out.println(showtime.getMovie().getSales());
            intent(this, new Booking(showtime.getSeatAt(row, col)));
        }
    }

    @Override
    protected void destroy() {
        ((MovieListing)(getPrevView())).start(movie);
    }
}
