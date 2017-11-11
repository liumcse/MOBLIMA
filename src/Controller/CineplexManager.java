package Controller;

import Model.*;

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
    private static final String FILENAME_BOOKINGHISTORY = "res/data/bookingHistory.dat";  // location of cinema.dat

    private static ArrayList<Movie> movieListing;
    private static HashMap<Movie, ArrayList<Showtime>> movieShowtime;
    private static HashMap<Cineplex, ArrayList<Cinema>> cinemaList;
    private static HashMap<String, String> staffAccount;
    private static ArrayList<BookingHistory> bookingHistory;
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
        cinemaList = new HashMap<>();
        bookingHistory = new ArrayList<>();
        reviewList = new HashMap<>();

        try {
            readMovieListing();
            readMovieShowtime();
            readStaffAccount();
            readCinemaList();
            readBookingHistory();
            readReviewList();
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

    private static void readBookingHistory() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_BOOKINGHISTORY) == null) bookingHistory = null;
        else bookingHistory = (ArrayList<BookingHistory>) readSerializedObject(FILENAME_BOOKINGHISTORY);
    }

    private static void readReviewList() throws IOException, ClassNotFoundException {
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

    private static void writeBookingHistory() throws IOException {
        writeSerializedObject(FILENAME_BOOKINGHISTORY, bookingHistory);
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

    public static ArrayList<BookingHistory> getBookingHistory() {
        return bookingHistory;
    }

    public static ArrayList<Review> getReviewList(Movie movie) { return reviewList.get(movie); }

    // TODO make it efficient
    public static Cinema getCinemaByCode(String code) {
        for (Cineplex cineplex : Cineplex.values()) {
            if (getCinemaList(cineplex) == null) continue;
            for (Cinema cinema : getCinemaList(cineplex)) {
                if (cinema.getCode().equals(code)) return cinema;
            }
        }
        return null;  // not found
    }

    // TODO make it efficient, and return multiple possible results
    public static ArrayList<Movie> getMovieByTitle(String title) {
        if (movieListing == null) return null;
        ArrayList<Movie> searchResult = new ArrayList<>();
        for (Movie movie: movieListing) {
            if (movie.getTitle().toUpperCase().contains(title.toUpperCase())) searchResult.add(movie);
        }
        return searchResult;
    }

    public static void addNewListing(Movie movie) throws IOException{
        movieListing.add(movie);
        writeMovieListing();
    }

    public static void addShowtime(Movie movie, Showtime showtime) throws IOException {
        if (movieShowtime.get(movie) == null) movieShowtime.put(movie, new ArrayList<>());
        movieShowtime.get(movie).add(showtime);
        writeShowtime();
    }

    public static void logBooking(BookingHistory record) throws IOException {
        bookingHistory.add(record);
        writeBookingHistory();
    }

    public static void overwriteShowtime() throws IOException {
        writeShowtime();
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
