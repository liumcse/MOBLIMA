package View.moviegoer;

import Controller.CineplexManager;
import Controller.IOController;
import Model.Movie;
import Model.Seat;
import Model.Showtime;
import View.View;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;
import java.util.*;

/**
 * Created by LiuMingyu on 6/11/17.
 * This is the class for movie view.
 */

public class MovieListing extends View {
    public MovieListing() {

    }

    @Override
    protected void start() {
        printHeader("Search or list movies");
        printMenu("1. Search movies",
                "2. List all movies",
                "3. List the top 5 movies",
                "4. Go back","");
        int choice = readChoice(1, 5);
        switch (choice) {
            case 1:
                searchMovie();
                break;
            case 2:
                displayMovieListing(0);
                break;
            case 3:
                displayMovieListing(1);
                break;
            case 4:
                destroy();
                break;
        }
    }

    private void searchMovie() {
        String input = readString("Enter the movie title:");
        ArrayList<Movie> searchResult = getMovieByTitle(input);
        if (searchResult == null || searchResult.isEmpty()) {
            printMenu("-> 0 result has been found.",
                    "Press ENTER to go back", "");
            readString();
            start();
        }
        else {
            printMenu("-> " + searchResult.size() + " results have been found:");
            int index = 0;
            for (Movie movie : searchResult) {
                System.out.println(++index + ". " + movie.getTitle() + " (" + movie.getMovieStatus().toString() + ")");
            }
            System.out.println(index + 1 + ". Go back");
            System.out.println();

            int choice = readChoice(1, index + 1);
            if (choice == index + 1) start();
            else displayMovieDetailMenu(searchResult.get(choice - 1));
        }
    }

    private void displayMovieListing(int option) {
        ArrayList<Movie> movieListing;

        switch (option) {
            case 0:
                movieListing = getMovieListing();
                break;
            default:
                movieListing = getTop5MovieListing();
        }

        printHeader("Movies");
        if (movieListing == null) {
            System.out.println("Movie listing is not available");
            start();
        }

        int index = 0;

        for (Movie movie : movieListing) {
            System.out.println(++index + ". " + movie.getTitle() + " (" + movie.getMovieStatus().toString() + ") " +getMovieRating(movie));
        }
        System.out.println(index + 1 + ". Go back");
        System.out.println();

        int choice = readChoice(1, index + 1);

        if (choice == index + 1) start();
        else displayMovieDetailMenu(movieListing.get(choice - 1));

    }

    private void displayMovieDetailMenu(Movie movie) {
        printHeader("Movie details");
        printMenu(movie.toString(),
                "1. Display showtime",
                "2. Display/write reviews",
                "3. Go back", "");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                // TODO movie.getShowtime()
                displayShowtimeMenu(movie);
                break;
            case 2:
                // TODO movie.getReview()
                intent(this, new ReviewView(movie));
                break;
            case 3:
                break;
        }
        displayMovieListing(0);
    }


    private void displayShowtimeMenu(Movie movie) {
        Date today = new Date();
        Date tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
        Date afterTomorrow = new Date(new Date().getTime() + 2* 24 * 60 * 60 * 1000);
        Date dateChosen;

        printMenu("1. " + formatTimeMMdd(today) + " (today)",
                "2. " + formatTimeMMdd(tomorrow),
                "3. " + formatTimeMMdd(afterTomorrow),
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

        printHeader("Showtime on " + formatTimeMMdd(dateChosen));

        ArrayList<Showtime> showtimeList = new ArrayList<>();
        Collections.sort(showtimeList, new Comparator<Showtime>() {
            @Override
            public int compare(Showtime o1, Showtime o2) {
                return o1.getCinema().getCineplex().toString().compareTo(o2.getCinema().getCineplex().toString());
            }
        });

        for (Showtime s : getMovieShowtime(movie)) {
            if (dateEquals(s.getTime(), dateChosen)) showtimeList.add(s);
        }

        if (showtimeList.isEmpty()) {
            printMenu("No showtime on that day.",
                    "Press ENTER to go back");
            readString();
            displayMovieDetailMenu(movie);
        }

        int index = 0;
        for (Showtime s : showtimeList) {
            System.out.println(++index + ": " + s);
        }

        System.out.println("Please choose a showtime (enter 0 to go back):");

        System.out.println();
        int choice = readChoice(0, showtimeList.size());
        if (choice == 0) displayMovieDetailMenu(movie);

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
                displayShowtimeMenu(showtime.getMovie());
                break;
        }


    }

    private void displayPrice(Showtime showtime) {
        double basePrice = showtime.getCinema().getBasePrice();
        Movie movie = showtime.getMovie();
        printHeader("Ticket price for " + movie.getTitle() + " (" + showtime.getCinema().getMovieType() + ")");
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
//        seats[4][3].bookSeat();
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
            return;
        }
        else if (showtime.getSeatAt(row, col).isBooked()) {
            System.out.println("The seat has been booked. Please choose another one.");
            displayBookSeatMenu(showtime);
        }
        else {
            // TODO BookingManager
            intent(this, new Booking(showtime.getSeatAt(row, col)));
        }
    }
}