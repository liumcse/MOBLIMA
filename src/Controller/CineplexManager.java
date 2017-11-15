package Controller;

import Model.*;

import java.io.IOException;
import java.util.*;

import static Model.Constant.*;
import static Controller.IOController.*;

/**
 * This class contains only static methods to read and manipulate from files.
 *
 * @version 1.0
 */

public class CineplexManager extends DataManager {
    /** Addresses of files */
    private static final String FILENAME_MOVIE = "res/data/movieListing.dat";  // location of movie.dat
    private static final String FILENAME_SHOWTIME = "res/data/showtime.dat";  // location of showtime.dat
    private static final String FILENAME_STAFFACCOUNT = "res/data/staffAccount.dat";  // location of staffAccount.dat
    private static final String FILENAME_CINEMALIST = "res/data/cinemaList.dat";  // location of cinemaList.dat
    private static final String FILENAME_REVIEWLIST = "res/data/reviewList.dat"; //location of reviewList.dat
    private static final String FILENAME_BOOKINGHISTORY = "res/data/bookingHistory.dat";  // location of cinema.dat
    private static final String FILENAME_HOLIDAY = "res/data/holidayList.dat";  // location of holiday.dat
    private static final String FILENAME_SYSTEM = "res/data/system.dat"; // location of system.dat

    private static ArrayList<Movie> movieListing;
    private static HashMap<Movie, ArrayList<Showtime>> movieShowtime;
    private static HashMap<Cineplex, ArrayList<Cinema>> cinemaList;
    private static HashMap<String, String> staffAccount;
    private static ArrayList<BookingHistory> bookingHistory;
    private static HashMap<Movie, ArrayList<Review>> reviewList;
    private static HashMap<String, Holiday> holidayList;
    private static HashMap<String, Boolean> system;

    /** Private constructor to suppress instantiation */
    private CineplexManager() { }

    /**
     * The method is to read necessary data from files and store it
     * @return true if there's no error, false if error occurs
     */
    public static boolean initialize() {
        try {
            readSystem();  // must not have class not found exception
            readStaffAccount();  // must not have class not found exception
            readCinemaList();  // may have class not found exception
            readMovieListing();  // may have class not found exception
            readMovieShowtime();  // may have class not found exception
            readReviewList();  // may have class not found exception
            readHolidayList();  // may have class not found exception
            readBookingHistory();  // may have class not found exception
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }

        return true;
    }

    /**
     * This method is to read movie listing (an {@code ArrayList<Movie>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readMovieListing() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_MOVIE) == null) movieListing = new ArrayList<>();
        else {
            movieListing = (ArrayList<Movie>) readSerializedObject(FILENAME_MOVIE);
            // sort listing by movie status
            Collections.sort(movieListing, Comparator.comparing(o -> o.getMovieStatus().toString()));
        }
    }

    /**
     * This method is to read movie showtime (a {@code HashMap<Movie, Arraylist<Showtime>>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readMovieShowtime() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_SHOWTIME) == null) movieShowtime = new HashMap<>();
        else movieShowtime = (HashMap<Movie, ArrayList<Showtime>>) readSerializedObject(FILENAME_SHOWTIME);
    }

    /**
     * This method is to read staff account (a {@code HashMap<String, String>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readStaffAccount() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_STAFFACCOUNT) == null) staffAccount = new HashMap<>();
        else staffAccount = (HashMap<String, String>) readSerializedObject(FILENAME_STAFFACCOUNT);
    }

    /**
     * This method is to read cinema list (a {@code HashMap<Cineplex, ArrayList<Cinema>>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readCinemaList() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_CINEMALIST) == null) cinemaList = new HashMap<>();
        else cinemaList = (HashMap<Cineplex, ArrayList<Cinema>>) readSerializedObject(FILENAME_CINEMALIST);
    }

    /**
     * This method is to read booking history (a {@code ArrayList<BookingHistory>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readBookingHistory() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_BOOKINGHISTORY) == null) bookingHistory = new ArrayList<>();
        else bookingHistory = (ArrayList<BookingHistory>) readSerializedObject(FILENAME_BOOKINGHISTORY);
    }

    /**
     * This method is to read review list (a {@code HashMap<Movie, ArrayList<Review>>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readReviewList() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_REVIEWLIST) == null) reviewList = new HashMap<>();
        else reviewList = (HashMap<Movie, ArrayList<Review>>) readSerializedObject(FILENAME_REVIEWLIST);
    }

    /**
     * This method is to read holiday list (a {@code HashMap<String, Holiday>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readHolidayList() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_HOLIDAY) == null) holidayList = new HashMap<>();
        else holidayList = (HashMap<String, Holiday>) readSerializedObject(FILENAME_HOLIDAY);
    }

    /**
     * This method is to read system (a {@code HashMap<String, Boolean>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readSystem() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_SYSTEM) == null) system = new HashMap<>();
        else system = (HashMap<String, Boolean>) readSerializedObject(FILENAME_SYSTEM);
    }

    /**
     * This method is to overwrite movie listing file.
     * @throws IOException when the file address is invalid
     */
    public static void updateMovieListing() throws IOException {
        writeSerializedObject(FILENAME_MOVIE, movieListing);
    }

    /**
     * This method is to overwrite showtime file.
     * @throws IOException when the file address is invalid
     */
    public static void updateShowtime() throws IOException {
        writeSerializedObject(FILENAME_SHOWTIME, movieShowtime);
    }

    /**
     * This method is to overwrite cinema list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateCinemaList() throws IOException {
        writeSerializedObject(FILENAME_CINEMALIST, cinemaList);
    }

    /**
     * This method is to overwrite booking history file.
     * @throws IOException when the file address is invalid
     */
    public static void updateBookingHistory() throws IOException {
        writeSerializedObject(FILENAME_BOOKINGHISTORY, bookingHistory);
    }

    /**
     * This method is to overwrite review list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateReviewList() throws IOException {
        writeSerializedObject(FILENAME_REVIEWLIST, reviewList);
    }

    /**
     * This method is to overwrite holiday list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateHolidayList() throws IOException {
        writeSerializedObject(FILENAME_HOLIDAY, holidayList);
    }

    /**
     * This method is to overwrite system file.
     * @throws IOException when the file address is invalid
     */
    public static void updateSystem() throws IOException {
        writeSerializedObject(FILENAME_SYSTEM, system);
    }

    /**
     * This method is to get movie listing (an {@code ArrayList<Movie>}).
     * @return the movie listing (an {@code ArrayList<Movie>})
     */
    public static ArrayList<Movie> getMovieListing() {
        return movieListing;
    }

    /**
     * This method is to get the top 5 ranking movie (an {@code ArrayList<Movie>}).
     * @return top 5 ranking by overall rating when orderBy is true, top 5 ranking by ticket sales when orderBy is false
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Movie> getTop5MovieListing() {
        boolean orderBy = system.get("movieOrder");
        ArrayList<Movie> top5 = (ArrayList<Movie>) movieListing.clone();
        if (orderBy) {  // order by overall ratings
            Collections.sort(top5, (o1, o2) -> Double.compare(getMovieRating(o2), getMovieRating(o1)));
        }
        else {  // order by ticket sales
            Collections.sort(top5, (o1, o2) -> Integer.compare(o2.getSales(), o1.getSales()));
        }

        while (top5.size() > 5) {
            top5.remove(5);
        }

        return top5;
    }

    /**
     * This method is to get showtime by movie.
     * @param movie the movie of the showtime
     * @return the showtime of the movie
     */
    public static ArrayList<Showtime> getMovieShowtime(Movie movie) {
        return movieShowtime.get(movie);
    }

    /**
     * This method is to get the cinema list (an {@code ArrayList<Cinema>}) by cineplex.
     * @param cineplex the cineplex
     * @return the cinema list
     */
    public static ArrayList<Cinema> getCinemaList(Cineplex cineplex) {
        return cinemaList.get(cineplex);
    }

    public static ArrayList<BookingHistory> getBookingHistory() {
        return bookingHistory;
    }

    public static ArrayList<Review> getReviewList(Movie movie) {
        return reviewList.get(movie);
    }

    public static HashMap<String, Holiday> getHolidayList() {
        return holidayList;
    }

    public static HashMap<String, Boolean> getSystem() { return system; }

    public static Cinema getCinemaByCode(String code) {
        for (Cineplex cineplex : Cineplex.values()) {
            if (getCinemaList(cineplex) == null) continue;
            for (Cinema cinema : getCinemaList(cineplex)) {
                if (cinema.getCode().equals(code)) return cinema;
            }
        }
        return null;  // not found
    }

    public static ArrayList<Movie> getMovieByTitle(String title) {
        ArrayList<Movie> searchResult = new ArrayList<>();
        for (Movie movie: movieListing) {
            if (movie.getTitle().toUpperCase().contains(title.toUpperCase())) searchResult.add(movie);
        }
        return searchResult;
    }

    public static double getMovieRating(Movie movie) {
        ArrayList<Review> reviewList = getReviewList(movie);
        if (reviewList == null || reviewList.isEmpty()) return 0;
        else {
            double sum = 0;
            for (Review review : reviewList) sum += review.getRating();
            return round(sum / reviewList.size(), 1);
        }
    }

    public static void addNewListing(Movie movie) throws IOException{
        movieListing.add(movie);
        updateMovieListing();
    }

    public static void addShowtime(Showtime showtime) throws IOException {
        Movie movie = showtime.getMovie();
        if (movieShowtime.get(movie) == null) movieShowtime.put(movie, new ArrayList<>());
        movieShowtime.get(movie).add(showtime);
        updateShowtime();
    }

    public static void logBooking(BookingHistory record) throws IOException {
        bookingHistory.add(record);
        updateBookingHistory();
    }

    public static void addNewReview(Movie movie, Review review) throws IOException {
        if(reviewList.get(movie) == null) reviewList.put(movie, new ArrayList<>());
        reviewList.get(movie).add(review);
        updateReviewList();
    }

    public static void addCinema(Cinema cinema) throws IOException {
        if (cinemaList.get(cinema.getCineplex()) == null) cinemaList.put(cinema.getCineplex(), new ArrayList<>());
        cinemaList.get(cinema.getCineplex()).add(cinema);
    }

    public static void addHoliday(String date, Holiday holiday) throws IOException {
        holidayList.put(date, holiday);
        updateHolidayList();
    }

    public static void removeListing(Movie movie) throws IOException {
        movie.setMovieStatus(MovieStatus.END_OF_SHOWING);
        updateMovieListing();
    }

    public static void removeShowtime(Showtime showtime) throws IOException {
        movieShowtime.get(showtime.getMovie()).remove(showtime);
        updateShowtime();
    }

    public static void removeAllShowtime(Movie movie) throws IOException {
        movieShowtime.remove(movie);
        updateShowtime();
    }

    public static boolean authentication (String username, String password) {
        if (staffAccount.get(username) == null) return false;  // username does not exist
        else return staffAccount.get(username).equals(password);  // password does not match
    }
}
