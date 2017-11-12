package Model;

import java.io.Serializable;

public class Seat implements Serializable{
    private int row;
    private int col;
    private Showtime showtime;
    private boolean booked;

    public Seat(int row, int col, Showtime showtime) {
        this.row = row;
        this.col = col;
        this.showtime = showtime;
        booked = false;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean isBooked() {
        return booked;
    }

    public void bookSeat() {
        booked = true;
    }

    @Override
    public String toString() {
        if (!booked) return "[ ]";
        else return "[*]";
    }
}
