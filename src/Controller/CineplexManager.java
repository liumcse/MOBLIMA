package Controller;

import Model.Cinema;
import Model.Movie;
import Model.Review;
import Model.Showtime;
import static Model.Constant.*;


import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class CineplexManager extends DataManager {
    private static final String FILENAME_MOVIE = "res/data/movieListing.dat";  // location of movie.dat
    private static final String FILENAME_SHOWTIME = "res/data/showtime.dat";  // location of showtime.dat
    private static final String FILENAME_STAFFACCOUNT = "res/data/staffAccount.dat";  // location of staffAccount.dat
    private static final String FILENAME_CINEMALIST = "res/data/cinemaList.dat";  // location of cinemaList.dat
    private static final String FILENAME_REVIEWLIST = "res/data/reviewList.dat"; //location of reviewList.dat

    private static ArrayList<Movie> movieListing;
    private static HashMap<Movie, ArrayList<Showtime>> movieShowtime;
    private static HashMap<Cineplex, ArrayList<Cinema>> cinemaList;
    private static HashMap<String, String> staffAccount;
    private static HashMap<Movie, ArrayList<Review>> reviewList;

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
        reviewList = new HashMap<>();

        try {
            readMovieListing();
            readMovieShowtime();
            readStaffAccount();
            readCinemaList();
            readReview();
            return true;
        } catch (EOFException ex) {
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
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
                    return o1.getMovieStatus().toString().compareTo(o2.getMovieStatus().toString());
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

    private static void readCinemaList() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_CINEMALIST) == null) cinemaList = null;
        else cinemaList = (HashMap<Cineplex, ArrayList<Cinema>>) readSerializedObject(FILENAME_CINEMALIST);
    }

    private static void readReview() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_REVIEWLIST) == null) reviewList = null;
        else reviewList = (HashMap<Movie, ArrayList<Review>>) readSerializedObject(FILENAME_REVIEWLIST);
    }

    private static void writeMovieListing() throws IOException {
        writeSerializedObject(FILENAME_MOVIE, movieListing);
    }

    private static void writeShowtime() throws IOException {
        writeSerializedObject(FILENAME_SHOWTIME, movieShowtime);
    }

    private static void writeCinemaList() throws IOException {
        writeSerializedObject(FILENAME_CINEMALIST, cinemaList);
    }

    private static void writeReviewList() throws IOException {
        writeSerializedObject(FILENAME_REVIEWLIST, reviewList);
    }

    public static ArrayList<Movie> getMovieListing() {
        return movieListing;
    }

    public static ArrayList<Showtime> getMovieShowtime(Movie movie) {
        return movieShowtime.get(movie);
    }

    public static ArrayList<Cinema> getCinemaList(Cineplex cinplex) {
        return cinemaList.get(cinplex);
    }

    public static ArrayList<Review> getReviewList(Movie movie) { return reviewList.get(movie); }

    // TODO make it efficient
    public static Cinema getCinemaByCode(String code) {
        for (Cineplex cineplex : Cineplex.values()) {
            for (Cinema cinema : getCinemaList(cineplex)) {
                if (cinema.getCode().equals(code)) return cinema;
            }
        }
        return null;  // not found
    }

    public static void addNewListing(Movie movie) throws IOException{
        movieListing.add(movie);
        writeMovieListing();
    }

    public static void addNewReview(Movie movie, Review review) throws IOException{
        try {
            if(reviewList.get(movie) == null) reviewList.put(movie, new ArrayList<>());
            reviewList.get(movie).add(review);
            writeReviewList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO redundant?
    public static void overwriteListing() throws IOException {
        writeMovieListing();
    }

    public static void removeListing(Movie movie) throws IOException {
        movie.setMovieStatus(MovieStatus.END_OF_SHOWING);
        writeMovieListing();
    }

    public static void removeShowtime(Showtime showtime) throws IOException {
        movieShowtime.get(showtime.getMovie()).remove(showtime);
        writeShowtime();
    }

    public static boolean authentication (String username, String password) {
        if (staffAccount.get(username) == null) return false;  // username does not exist
        else return staffAccount.get(username).equals(password);  // password does not match
    }

}
