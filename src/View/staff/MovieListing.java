package View.staff;

import Model.Constant.*;
import Model.Movie;
import View.View;
import java.io.IOException;
import java.util.ArrayList;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;
import static Model.Constant.MovieStatus.*;


public class MovieListing extends View {
    @Override
    protected void start() {
        displayMenu();
    }

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
            System.out.println(++index + ". " + movie.getTitle() + " (" + movie.getMovieStatus().toString() + ")");
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
        synopsis = readString("Enter synopsis:");

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

    private void updateMovieDetailsMenu(Movie movie) {
        printHeader("Modify movie details");
        printMenu("1. Change title",
                "2. Change age restriction",
                "3. Change director",
                "4. Change synopsis",
                "5. Change casts",
                "6. Switch status",
                "7. Apply changes",
                "8. Go back");

        boolean changed = false;
        int choice = -1;
        while (choice != 8) {
            choice = readChoice(1, 8);
            switch (choice) {
                case 1:
                    movie.setTitle(readString("Enter the title:"));
                    changed = true;
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
                    changed = true;
                    System.out.println("Age restriction changed. Apply changes or make another selection.");
                    break;
                case 3:
                    movie.setDirector(readString("Enter director:"));
                    changed = true;
                    System.out.println("Director changed. Apply changes or make another selection.");
                    break;
                case 4:
                    movie.setSynopsis(readString("Enter synopsis"));
                    changed = true;
                    System.out.println("Synopsis changed. Apply changes or make another selection.");
                    break;
                case 5:
                    String[] castArray = readString("Enter casts, separate with semicolon(;)").split(";");
                    ArrayList<String> cast = new ArrayList<>();
                    for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);
                    movie.setCast(cast);
                    changed = true;
                    System.out.println("Casts changed. Apply changes or make another selection.");
                    break;
                case 6:
                    if (movie.getMovieStatus().equals(NOW_SHOWING)) {
                        if (askConfirm("Are you sure to change the movie status to Coming Soon?",
                                "Enter Y to confirm, N to cancel:")) {
                            movie.setMovieStatus(COMING_SOON);
                        }
                    } else {
                        if (askConfirm("Are you sure to change the movie status to Now showing?",
                                "Enter Y to confirm, N to cancel:")) {
                            movie.setMovieStatus(NOW_SHOWING);
                        }
                    }
                    changed = true;
                    System.out.println("Movie status changed. Apply changes or make another selection.");
                    break;
                case 7:
                    try {
                        updateMovieListing();
                        System.out.println("Changes have been applied. Go back or make another selection.");
                        changed = false;
                    } catch (IOException ex) {
                        System.out.println("Failed to apply changes.");
                    }
                    break;
                case 8:
                    if (changed) {
                        if (askConfirm("Changes haven't been applied. Are you sure to go back?",
                                "Enter Y to discard changes, N to remain:")) {
                            break;
                        }
                        else {
                            System.out.println("Please enter your selection.");
                            continue;
                        }
                    }
                    break;
            }
        }
        displayMovieDetailMenu(movie);
    }

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
