package Model;

import java.io.Serializable;
import java.util.Date;

public class Showtime implements Serializable {
    // TODO date
    // TODO time
    private String hour;
    private String minute;
    private Cinema cinema;

    // TODO modify this
    public Showtime(String hour, String minute) {
        this.hour = hour;
    }

    public void checkSeat() {


    }

    public Cinema getCinema() {
        return cinema;
    }

    @Override
    public String toString() {
        return cinema.getCineplex().toString() + ": " + hour + minute;
    }
}