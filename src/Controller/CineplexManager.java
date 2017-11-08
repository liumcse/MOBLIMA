package Controller;

import Model.Constant;
import Model.Movie;
import Model.Showtime;

import java.util.ArrayList;
import java.util.HashMap;

public class CineplexManager extends DataManager {
    private static final String FILENAME_MOVIE = "res/data/movie.dat";  // location of movie.dat
    private static final String FILENAME_SHOWTIME = "res/data/showtime.dat";  // location of showtime.dat
    private static final String FILENAME_STAFFACCOUNT = "res/data/staffAccount.dat";  // location of staffAccount.dat

    private static ArrayList<Movie> movieListing;
    private static HashMap<Movie, ArrayList<Showtime>> movieShowtime;
    private static HashMap<String, String> staffAccount;

    public CineplexManager() {
        /**
         * Since all methods are static, constructor can be left empty
         */
    }

    /**
     * Initialize, get all data from files
     * @return
     */
    public static boolean initialize() {
        movieListing = new ArrayList<>();
        movieShowtime = new HashMap<>();
        staffAccount = new HashMap<>();

        try {
            readMovieListing();
            readMovieShowtime();
            readStaffAccount();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }



    private static void readMovieListing() {
        movieListing = (ArrayList) readSerializedObject(FILENAME_MOVIE);
    }

    private static void readMovieShowtime() {
        movieShowtime = (HashMap<Movie, ArrayList<Showtime>>) readSerializedObject(FILENAME_SHOWTIME);
    }
    private static void readStaffAccount() {
        staffAccount = (HashMap<String, String>) readSerializedObject(FILENAME_STAFFACCOUNT);
    }

    public static ArrayList<Movie> getMovieListing() {
        return movieListing;
    }



    public static ArrayList<Showtime> getMovieShowtime(Movie m) {
        return movieShowtime.get(m);
    }

    public static boolean authentication (String username, String password) {
        if (staffAccount.get(username) == null) return false;  // username does not exist
        else return staffAccount.get(username).equals(password);  // password does not match
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
