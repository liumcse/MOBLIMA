package Controller;

import Model.Constant;
import Model.Movie;
import Model.Showtime;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        } catch (EOFException ex) {
            return true;
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }
    }



    private static void readMovieListing() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_MOVIE) == null) movieListing = null;
        else {
            movieListing = (ArrayList) readSerializedObject(FILENAME_MOVIE);
            Collections.sort(movieListing, new Comparator<Movie>() {  // sort listing by movie status
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o1.getStatus().toString().compareTo(o2.getStatus().toString());
                }
            });
        }
    }

    private static void readMovieShowtime() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_SHOWTIME) == null) movieShowtime = null;
        else movieShowtime = (HashMap<Movie, ArrayList<Showtime>>) readSerializedObject(FILENAME_SHOWTIME);
    }
    private static void readStaffAccount() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_STAFFACCOUNT) == null) staffAccount = null;
        else staffAccount = (HashMap<String, String>) readSerializedObject(FILENAME_STAFFACCOUNT);
    }

    private static void writeMovieListing() throws IOException {
        DataManager.writeSerializedObject(FILENAME_MOVIE, movieListing);
    }

    public static ArrayList<Movie> getMovieListing() {
        return movieListing;
    }

    public static ArrayList<Showtime> getMovieShowtime(Movie m) {
        return movieShowtime.get(m);
    }

    public static void addNewListing(Movie movie) throws IOException{
        movieListing.add(movie);
        writeMovieListing();
    }

    public static void removeListing(Movie movie) throws IOException{
        movieListing.get(movieListing.indexOf(movie)).setStatus(Constant.Status.END_OF_SHOWING);
        writeMovieListing();
    }

    public static boolean authentication (String username, String password) {
        if (staffAccount.get(username) == null) return false;  // username does not exist
        else return staffAccount.get(username).equals(password);  // password does not match
    }

    @Deprecated
    public static void displayMovieListing() {
        ArrayList<Movie> toDisplay = new ArrayList<>();

        // all the COMING_SOON
        for (Movie movie : movieListing) {
            if (movie.getStatus() == Constant.Status.COMING_SOON) toDisplay.add(movie);
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
