package Model;

import java.io.Serializable;
import java.util.Calendar;


public class Showtime implements Serializable {
    // TODO date
    // TODO time
    private Movie movie;
    private String hour;    // TODO change this to real hour/minute instead of string
    private String minute;
    private Cinema cinema;
    private Calendar time;
    private Seat[][] seats;

    private final static int COLS = 17;
    private final static int ROWS = 9;

    // TODO modify this
    public Showtime(String hour, String minute, Cinema cinema) {
        this.hour = hour;
        this.minute = minute;
        this.cinema = cinema;

        seats = new Seat[ROWS][COLS];

        initializeSeat(seats);
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public Seat getSeatAt(int row, int col) {
        if (row < 1 || row > 9 || col < 1 || col > 16) return null;

        if (col >= 9) col++;

        return seats[row - 1][col - 1];
    }

    private void initializeSeat(Seat[][] seats) {
        for (int row = 0; row <= 3; row++) {
            for (int col = 2; col <= 16; col++) {
                if (col == 8) continue;
                seats[row][col] = new Seat(row, col);
            }
        }

        for (int row = 4; row <= 7; row++) {
            for (int col = 0; col <= 16; col++) {
                if (col == 8) continue;
                seats[row][col] = new Seat(row, col);
            }
        }

        for (int col = 0; col <= 16; col++) {
            if (col == 8 || col == 9 || col == 10) continue;
            seats[8][col] = new Seat(8, col);
        }
    }

    @Override
    public String toString() {
        return cinema.getCineplex().toString() + ": " + hour + minute;
    }
}