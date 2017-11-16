package View.staff;

import Model.Constant.*;
import Model.Movie;
import View.View;
import java.io.IOException;
import java.util.ArrayList;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;
import static Model.Constant.MovieStatus.*;

/**
 * The class represents the movie listing view.
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
     * This method is to display the main menu of movie listing.
     */
    private void displayMenu() {
        ArrayList<Movie> movieListing;
        movieListing = getMovieListing();

        int index = 0;
        printHeader("Modify movie listing");
        if (movieListing.isEmpty()) {
            printMenu("No movie.",
                    "1. List a new movie",
                    "2. Go back", "");
            int choice = readChoice(1, 2);
            if (choice == 1) addMovieListing();
            else destroy();
            return;
        }

        for (Movie movie : movieListing) {
            System.out.println(++index + ". " + movie.getTitle() + generateSpaces(49 - movie.getTitle().length())+ "(" + movie.getMovieStatus().toString() + ")");
        }
        System.out.println(index + 1 + ". Go back");

        printMenu("Please choose a movie to modify.",
                "To list a new movie, enter 0:", "");

        int choice = readChoice(0, index + 1);

        if (choice == index + 1) destroy();
        else if (choice == 0) {
            addMovieListing();
        }
        else displayMovieDetailMenu(movieListing.get(choice - 1));
    }

    /**
     * This method is to add a movie listing.
     */
    private void addMovieListing() {
        String title, director, synopsis;
        AgeRestriction ageRestriction = null;
        ArrayList<String> cast;
        MovieStatus movieStatus = null;

        printHeader("Add movie listing");
        title = readString("Enter the title:");

        // set age restriction
        while (ageRestriction == null) {
            String input = readString("Choose the movie restriction, please enter one of the following:",
                    "G, PG, PG13, NC16, M18, R21").toUpperCase();
            ageRestriction = readAgeRestriction(input);
        }

        director = readString("Enter director:");
        synopsis = addLinebreaks(readString("Enter synopsis:"), 50, 14);

        // set casts
        String[] castArray = readString("Enter casts, separate with semicolon(;)").split(";");
        cast = new ArrayList<>();
        for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);

        // set movie movieStatus
        while(movieStatus == null) {
            String input = readString("Enter movie movieStatus, please enter one of the following:",
                    "Coming soon, Now showing, End of showing").toUpperCase();
            movieStatus = readMovieStatus(input);
        }

        // create movie object
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setAgeRestriction(ageRestriction);
        movie.setDirector(director);
        movie.setSynopsis(synopsis);
        movie.setCast(cast);
        movie.setMovieStatus(movieStatus);

        // write to file
        try {
            addNewListing(movie);
            System.out.println("Successfully listed movie " + title);
        }
        catch (IOException ex) {
            System.out.println("Failed to list the movie.");
        }
        finally {
            displayMenu();
        }
    }

    /**
     * This method is to display the menu of movie detail and other operations.
     * @param movie the movie whose detail to be displayed
     */
    private void displayMovieDetailMenu(Movie movie) {
        printHeader("Movie details");
        printMenu(movie.toString(),
                "1. Update movie details",
                "2. Remove the listing",
                "3. Add/drop showtime",
                "4. Go back");

        int choice = readChoice(1, 4);
        switch (choice) {
            case 1:
                updateMovieDetailsMenu(movie);
                break;
            case 2:
                removeListingMenu(movie);
                break;
            case 3:
               intent(this, new ShowtimeView(movie));
                break;
            case 4:
                displayMenu();
        }
    }

    /**
     * This method is to display the menu of updating movie detail.
     * @param movie the movie whose detail to be updated
     */
    private void updateMovieDetailsMenu(Movie movie) {
        printHeader("Modify movie details");
        printMenu("1. Change title",
                "2. Change age restriction",
                "3. Change director",
                "4. Change synopsis",
                "5. Change casts",
                "6. Switch status",
                "7. Go back");

        int choice = -1;
        while (choice != 7) {
            choice = readChoice(1, 7);
            switch (choice) {
                case 1:
                    movie.setTitle(readString("Enter the title:"));
                    System.out.println("Title changed. Please make another selection.");
                    break;
                case 2:
                    AgeRestriction ageRestriction = null;
                    while (ageRestriction == null) {
                        String input = readString("Choose the movie restriction, please enter one of the following:",
                                "G, PG, PG13, NC16, M18, R21").toUpperCase();
                        ageRestriction = readAgeRestriction(input);
                    }
                    movie.setAgeRestriction(ageRestriction);
                    System.out.println("Age restriction changed.");
                    break;
                case 3:
                    movie.setDirector(readString("Enter director:"));
                    System.out.println("Director changed.");
                    break;
                case 4:
                    movie.setSynopsis(addLinebreaks(readString("Enter synopsis"), 50, 10));
                    System.out.println("Synopsis changed.");
                    break;
                case 5:
                    String[] castArray = readString("Enter casts, separate with semicolon(;)").split(";");
                    ArrayList<String> cast = new ArrayList<>();
                    for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);
                    movie.setCast(cast);
                    System.out.println("Casts changed.");
                    break;
                case 6:
                    if (movie.getMovieStatus().equals(NOW_SHOWING)) {
                        if (askConfirm("Are you sure to change the movie status to Coming Soon?",
                                "Enter Y to confirm, N to cancel:")) {
                            movie.setMovieStatus(COMING_SOON);
                            System.out.println("Movie status changed.");
                        }
                        else {
                            System.out.println("Movie status remains.");
                        }
                    } else {
                        if (askConfirm("Are you sure to change the movie status to Now showing?",
                                "Enter Y to confirm, N to cancel:")) {
                            movie.setMovieStatus(NOW_SHOWING);
                            System.out.println("Movie status changed.");
                        }
                        else {
                            System.out.println("Movie status remains.");
                        }
                    }
                    break;
                case 7:
                    try {
                        updateMovieListing();
                        printMenu("Changes have been applied.",
                                "Press ENTER to go back.");
                        readString();
                    } catch (IOException ex) {
                        printMenu("Failed to apply changes.",
                                "Press ENTER to go back.");
                        readString();
                    }
                    break;
            }
        }
        displayMovieDetailMenu(movie);
    }

    /**
     * This method is to remove a movie listing.
     * @param movie the movie to be removed.
     */
    private void removeListingMenu(Movie movie) {
        if (askConfirm("Are you sure to remove the listing?",
                "All its showtime will be removed as well.",
                "Enter Y to confirm, N to cancel:")) {
            try {
                removeListing(movie);
                removeAllShowtime(movie);
                System.out.println("The listing has been removed.");
            } catch (IOException ex) {
                System.out.println("Failed to remove listing");
            }
        }

        displayMenu();
    }

}
