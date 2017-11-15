package Model;

import java.io.Serializable;

/**
 * This class contains all information of a seat - including its row number, column number,
 * {@code Showtime} and whether the it is booked.
 *
 * @version 1.0
 */
public class Seat implements Serializable{
    private final int row;
    private final int col;
    private final Showtime showtime;
    private boolean booked;

    /**
     * Allocates a {@code Seat} object and initializes it specified by its row number, column
     * number and showtime.
     * @param row This it the row number of the seat
     * @param col This is the column number of the seat
     * @param showtime This is the showtime of the seat
     */
    public Seat(int row, int col, Showtime showtime) {
        this.row = row;
        this.col = col;
        this.showtime = showtime;
        booked = false;
    }

    /**
     * This method is to get the showtime}.
     * @return showtime of the seat
     */
    public Showtime getShowtime() {
        return showtime;
    }

    /**
     * This method is to get column number.
     * @return column number
     */
    public int getCol() {
        return col;
    }

    /**
     * This method is to get row number.
     * @return row number
     */
    public int getRow() {
        return row;
    }

    /**
     * This method is to get a boolean value which indicates whether the seat is booked.
     * @return true if the seat is booked, false if the seat is not booked
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * This method is to book the seat by setting {@code booked} as true
     */
    public void bookSeat() {
        booked = true;
    }

    @Override
    public String toString() {
        if (!booked) return "[ ]";
        else return "[*]";
    }
}
