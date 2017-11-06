package Controller;

import Model.Constant;
import Model.Movie;

import java.util.ArrayList;

public class CineplexManager extends DataManager {
    private static final String FILENAME_MOVIE = "res/data/movie.dat";  // location of movie.dat file
    private ArrayList<Movie> movieListing;

    public CineplexManager() {
        // TODO
        movieListing = new ArrayList<>();
        readMovieListing();
    }

    private void readMovieListing() {
        movieListing = (ArrayList) readSerializedObject(FILENAME_MOVIE);
    }

    public ArrayList<Movie> getMovieListing() {
        readMovieListing();  // TODO redundant?
        return movieListing;
    }

    @Deprecated
    public void displayMovieListing() {
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
