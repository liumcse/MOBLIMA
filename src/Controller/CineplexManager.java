package Controller;

import Model.Constant;
import Model.Movie;
import Model.Showtime;

import java.util.ArrayList;
import java.util.HashMap;

public class CineplexManager extends DataManager {
    private static final String FILENAME_MOVIE = "res/data/movie.dat";  // location of movie.dat file
    private static final String FILENAME_SHOWTIME = "res/data/showtime.dat";  // location of showtime.dat file
    private static ArrayList<Movie> movieListing;
    private static HashMap<Movie, ArrayList<Showtime>> movieShowtime;

    public CineplexManager() {
        // TODO
        movieListing = new ArrayList<>();
        readMovieListing();
    }

    private static void readMovieListing() {
        movieListing = (ArrayList) readSerializedObject(FILENAME_MOVIE);
    }

    public static ArrayList<Movie> getMovieListing() {
        readMovieListing();  // TODO redundant?
        return movieListing;
    }


    private static void readMovieShowtime() {
        movieShowtime = (HashMap<Movie, ArrayList<Showtime>>) readSerializedObject(FILENAME_SHOWTIME);
    }


    public static ArrayList<Showtime> getMovieShowtime(Movie m) {
        readMovieShowtime();
        return movieShowtime.get(m);
    }

    @Deprecated
    public static void displayMovieListing() {
        ArrayList<Movie> toDisplay = new ArrayList<>();

        // all the COMMING_SOON
        for (Movie movie : movieListing) {
            if (movie.getStatus() == Constant.Status.COMMING_SOON) toDisplay.add(movie);
        }

        // all the NOW_SHOWING
        for (Movie movie : movieListing) {
            if (movie.getStatus() == Constant.Status.NOW_SHOWING) toDisplay.add(movie);
        }

        for (Movie movie : toDisplay) {
            System.out.println(movie);
            System.out.println();
        }
    }

}
