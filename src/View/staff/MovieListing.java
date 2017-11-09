package View.staff;

import Model.Constant.*;
import Model.Movie;
import Model.Showtime;
import View.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;
import static Model.Constant.AgeRestriction.*;
import static Model.Constant.Status.*;


public class MovieListing extends View {
    @Override
    protected void start() {
        ArrayList<Movie> movieListing;
        movieListing = getMovieListing();

        if (movieListing == null) {
            System.out.println("No movie.");
            return;
        }

        int index = 0;
        printHeader("Modify movie listing");
        for (Movie movie : movieListing) {
            System.out.println(++index + ". " + movie.getTitle() + " (" + movie.getStatus().toString() + ")");
        }
        System.out.println(index + 1 + ". Go back");

        printMenu("Please choose a movie to modify.",
                "To list a new movie, enter 0.");

        int choice = readChoice(0, index + 1);

        if (choice == index + 1) destroy();
        else if (choice == 0) {
            listNewMovie();
        }
        else displayMovieDetailMenu(movieListing.get(choice - 1));
    }

    private void listNewMovie() {
        String title, director, synopsis;
        AgeRestriction ageRestriction;
        ArrayList<String> cast;
        Status status;

        printHeader("List new movie");
        title = readString("Enter the title:");

        // set age restriction
        String input = readString("Choose the movie restriction, please enter one of the following:",
                "G, PG, PG13, NC16, M18, R21").toUpperCase();
        while (true) {
            switch (input) {
                case "G":
                    ageRestriction = G;
                    break;
                case "PG":
                    ageRestriction = PG;
                    break;
                case "PG13":
                    ageRestriction = PG13;
                    break;
                case "NC16":
                    ageRestriction = NC16;
                    break;
                case "M18":
                    ageRestriction = M18;
                    break;
                case "R21":
                    ageRestriction = R21;
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
                    input = readString().toUpperCase();
                    continue;
            }
            break;  // exit loop
        }

        director = readString("Enter director");
        synopsis = readString("Enter synopsis:");

        // set casts
        String[] castArray = readString("Enter casts, separate with semicolon(;)").split(";");
        cast = new ArrayList<>();
        for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);

        // set movie status
        input = readString("Enter movie status, please enter one of the following:",
                "Coming soon, Now showing, End of showing").toUpperCase();
        while (true) {
            switch (input) {
                case "COMING SOON":
                    status = COMING_SOON;
                    break;
                case "NOW SHOWING":
                    status = NOW_SHOWING;
                    break;
                case "END OF SHOWING":
                    status = END_OF_SHOWING;
                default:
                    System.out.println("Invalid input. Try again.");
                    input = readString("Enter movie status, please enter one of the following:",
                            "Coming soon, Now showing, End of showing").toUpperCase();
                    continue;
            }
            break;
        }

        // create movie object
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setAgeRestriction(ageRestriction);
        movie.setDirector(director);
        movie.setSynopsis(synopsis);
        movie.setCast(cast);
        movie.setStatus(status);

        // write to file
        try {
            addNewListing(movie);
            System.out.println("Successfully listed movie " + title);
        }
        catch (IOException ex) {
            System.out.println("Failed to list the movie.");
        }
        finally {
            start();
        }
    }

    private void displayMovieDetailMenu(Movie movie) {
        // TODO bug here, what is there's no showtime?
        ArrayList<Showtime> showtimeList = getMovieShowtime(movie);
        Collections.sort(showtimeList, new Comparator<Showtime>() {
            @Override
            public int compare(Showtime o1, Showtime o2) {
                return o1.getCinema().getCineplex().toString().compareTo(o2.getCinema().getCineplex().toString());
            }
        });

        int index = 0;
        for (Showtime s : showtimeList) System.out.println(++index + ": " + s);

        printMenu("Please choose a showtime (enter 0 to go back):");
        int choice = readChoice(0, index + 1);
        if (choice == 0) start();
        else {
            Showtime showtime = showtimeList.get(choice - 1);
            displayShowtimeDetailMenu(showtime);
        }
    }

    private void displayShowtimeDetailMenu(Showtime showtime) {
        printHeader(showtime.toString());
        printMenu("1. Set cineplex",
                "2. Set cinema",
                "3. Set date and time",
                "4. Set ticket price",
                "5. Go back");

        int choice = readChoice(1, 5);
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                displayMovieDetailMenu(showtime.getMovie());
                break;
        }
    }
}
