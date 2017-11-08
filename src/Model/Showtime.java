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
    private Seat[] seats;

    // TODO modify this
    public Showtime(String hour, String minute, Cinema cinema) {
        this.hour = hour;
        this.minute = minute;
        this.cinema = cinema;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public void displaySeat() {

    }

    public Movie getMovie() {
        return movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Seat[] getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return cinema.getCineplex().toString() + ": " + hour + minute;
    }
}