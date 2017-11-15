package Model;

import java.io.Serializable;
import java.util.Date;

import static Controller.IOController.*;

/**
 * This class contains all information of a showtime - including its {@code Movie}, {@code Cinema},
 * {@code Date} and a 2D array of {@code Seat}.
 *
 * @version 1.0
 */
public class Showtime implements Serializable {
    private Movie movie;
    private Cinema cinema;
    private Date time;
    private Seat[][] seats;

    /** number of total columns */
    private final static int COLS = 17;
    /** number of total rows */
    private final static int ROWS = 9;

    /**
     * Constructor, no parameter. It initializes all seats.
     */
    public Showtime() {
        seats = new Seat[ROWS][COLS];
        initializeSeat();
    }

    /**
     * This method is to set time of the showtime.
     * @param time an {@code Data} object, the time of the showtime
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method is to set cinema of the showtime.
     * @param cinema the cinema of the showtime
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * This method is to set movie of the showtime.
     * @param movie the movie of the showtime
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * This method is to get the movie of the showtime.
     * @return the movie of the showtime
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * This method is to get the cinema of the showtime.
     * @return the cinema of the showtime
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * This method is to get the seat (a 2D array).
     * @return the seat (a 2D array) of the showtime
     */
    public Seat[][] getSeats() {
        return seats;
    }

    /**
     * This method is to get the seat at specified position.
     * @param row the row number of the seat
     * @param col the column number of the seat
     * @return the seat at ({@code row}, {@code col})
     */
    public Seat getSeatAt(int row, int col) {
        if (row < 1 || row > 9 || col < 1 || col > 16) return null;

        if (col >= 9) col++;

        return seats[row - 1][col - 1];
    }

    /**
     * This method is to get the time of the showtime.
     * @return the time of the showtime
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method is to initialize the object in {@code Seats}. This method should be
     * called when the {@code Showtime} class is created.
     */
    private void initializeSeat() {
        for (int row = 0; row <= 3; row++) {
            for (int col = 2; col <= 16; col++) {
                if (col == 8) continue;
                seats[row][col] = new Seat(row, col, this);
            }
        }

        for (int row = 4; row <= 7; row++) {
            for (int col = 0; col <= 16; col++) {
                if (col == 8) continue;
                seats[row][col] = new Seat(row, col, this);
            }
        }

        for (int col = 0; col <= 16; col++) {
            if (col == 8 || col == 9 || col == 10) continue;
            seats[8][col] = new Seat(8, col, this);
        }
    }

    /**
     * This method is to get a {@code String} of details of the showtime
     * @return a {@code String} of details of the showtime
     */
    public String getDetails() {
        return "Cineplex: " + cinema.getCineplex() + "\n" +
                "Cinema: " + cinema.toString() + "\n" +
                "Time: " + time.toString() + "\n";
    }



    @Override
    public String toString() {
        return cinema.getCineplex().toString() + ": " + formatTimeMMddkkmm(time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Showtime showtime = (Showtime) o;

        if (!movie.equals(showtime.movie)) return false;
        if (!cinema.equals(showtime.cinema)) return false;
        return formatTimeMMddkkmm(time).equals(formatTimeMMddkkmm(showtime.time));
    }

    @Override
    public int hashCode() {
        int result = movie.hashCode();
        result = 31 * result + cinema.hashCode();
        result = 31 * result + formatTimeMMddkkmm(time).hashCode();
        return result;
    }
}
