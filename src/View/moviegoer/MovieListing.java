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
                "3. List the top 5 movies by ticket sales",
                "4. List the top 5 movies by overall ratings",
                "5. Go back","");
        int choice = readChoice(1, 5);
        switch (choice) {
            case 1:
                break;
            case 2:
                displayMovieListing(0);
                break;
            case 3:
                displayMovieListing(1);
                break;
            case 4:
                displayMovieListing(2);
                break;
            case 5:
                destroy();
                break;
        }
    }

    private void displayMovieListing(int option) {
        ArrayList<Movie> movieListing;
        if (option == 0) {  // list all movies
            movieListing = getMovieListing();

            printHeader("Movies");
            if (movieListing == null) {
                System.out.println("Movie listing is not available");
                return;
            }

            int index = 0;

            for (Movie movie : movieListing) {
                System.out.println(++index + ". " + movie.getTitle() + " (" + movie.getMovieStatus().toString() + ")");
            }
            System.out.println(index + 1 + ". Go back");
            System.out.println();

            int choice = readChoice(1, index + 1);

            if (choice == index + 1) return;
            else displayMovieDetailMenu(movieListing.get(choice - 1));
        }
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
                intent(this, new ReviewView(movie));
                // TODO movie.getReview()
                break;
            case 3:
                destroy();
                break;
        }
    }

    private void displayShowtimeMenu(Movie movie) {
        printHeader("Showtime");
        ArrayList<Showtime> showtimeList = CineplexManager.getMovieShowtime(movie);
        Collections.sort(showtimeList, new Comparator<Showtime>() {
            @Override
            public int compare(Showtime o1, Showtime o2) {
                return o1.getCinema().getCineplex().toString().compareTo(o2.getCinema().getCineplex().toString());
            }
        });

        int index = 0;
        for (Showtime s : showtimeList) System.out.println(++index + ": " + s);

        System.out.println("Please choose a showtime (enter 0 to go back):");

        System.out.println();
        int choice = readChoice(1, showtimeList.size());
        if (choice == 0) return;

        Showtime showtime = showtimeList.get(choice - 1);
        displayShowtimeDetailMenu(showtime);

    }

    private void displayShowtimeDetailMenu(Showtime showtime) {
        printHeader(showtime.toString());
        printMenu("1. Check seat availability",
                "2. Book seat",
                "3. Go back", "");
        int choice = IOController.readChoice(1, 3);
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
                displayShowtimeMenu(showtime.getMovie());
                break;
        }
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