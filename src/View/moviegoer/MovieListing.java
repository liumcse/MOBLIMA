package View.moviegoer;

import Model.Constant;
import Model.Movie;
import View.View;
import java.util.*;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;

/**
 * This class represents the movie view.
 *
 * @version 1.0
 */

public class MovieListing extends View {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * This method is to go to movie detail menu of specified {@code Movie}.
     * @param movie the movie whose detail to be displayed
     */
    protected void start(Movie movie) {
        displayMovieDetailMenu(movie);
    }

    /**
     * This method is to display the main menu.
     */
    private void displayMenu() {
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
                displayMovieListing(false);
                break;
            case 3:
                displayMovieListing(true);
                break;
            case 4:
                break;
        }

        destroy();
    }

    /**
     * This method is to search a movie and display the result of all matching titles.
     */
    private void searchMovie() {
        String input = readString("Enter the movie title:");
        ArrayList<Movie> searchResult = getMovieByTitle(input);
        if (searchResult.isEmpty()) {
            printMenu("-> 0 result has been found.",
                    "Press ENTER to go back", "");
            readString();
            displayMenu();
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

    /**
     * This method is to display the movie listing.
     * @param topFive display top 5 ranking movie if {@code topFive} is true
     */
    private void displayMovieListing(boolean topFive) {
        ArrayList<Movie> movieListing;

        if (topFive) movieListing = getTop5MovieListing();
        else movieListing = getMovieListing();

        printHeader("Movies");
        if (movieListing.isEmpty()) {
            System.out.println("Movie listing is not available");
            displayMenu();
        }

        int index = 0;

        if (!topFive || getSystem().get("movieOrder")) {  // show movie rating
            for (Movie movie : movieListing) {
                if (movie.getMovieStatus().equals(Constant.MovieStatus.END_OF_SHOWING)) continue;
                System.out.println(++index + ". " + movie.getTitle() + generateSpaces(37 - movie.getTitle().length())
                        + "(" + movie.getMovieStatus().toString() + ") " +
                        "[" + (getMovieRating(movie) == 0.0 ? "No rating" : getMovieRating(movie)) + "]");
            }
        }
        else {
            for (Movie movie : movieListing) {  // show ticket sales
                if (movie.getMovieStatus().equals(Constant.MovieStatus.END_OF_SHOWING)) continue;
                System.out.println(++index + ". " + movie.getTitle() + " \t\t\t\t(" + movie.getMovieStatus().toString() + ") " +
                        "[" + (movie.getSales() == 0 ? "No sale" : movie.getSales()) + "]");
            }
        }

        System.out.println(index + 1 + ". Go back");
        System.out.println();

        int choice = readChoice(1, index + 1);

        if (choice == index + 1) start();
        else displayMovieDetailMenu(movieListing.get(choice - 1));

    }

    /**
     * This method is to display the detail of the movie and other operations.
     * @param movie the movie whose detail to be displayed
     */
    private void displayMovieDetailMenu(Movie movie) {
        printHeader("Movie details");
        printMenu(movie.toString(),
                "1. Display showtime",
                "2. Display/write reviews",
                "3. Go back", "");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                intent(this, new ShowtimeView(movie));
                break;
            case 2:
                intent(this, new ReviewView(movie));
                break;
            case 3:
                break;
        }
        displayMovieListing(false);
    }
}