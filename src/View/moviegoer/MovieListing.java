package View.moviegoer;

import Model.Constant;
import Model.Movie;
import View.View;
import java.util.*;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;

/**
 * Created by LiuMingyu on 6/11/17.
 * This is the class for movie view.
 */

public class MovieListing extends View {
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * Directly go to movie detail menu
     * @param movie
     */

    protected void start(Movie movie) {
        displayMovieDetailMenu(movie);
    }

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
                intent(this, new ShowtimeView(movie));
                break;
            case 2:
                // TODO movie.getReview()
                intent(this, new ReviewView(movie));
                break;
            case 3:
                break;
        }
        displayMovieListing(false);  // TODO where does it go back to?
    }
}