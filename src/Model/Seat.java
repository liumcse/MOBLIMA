package Model;

import java.io.Serializable;

public class Seat implements Serializable{
    private int row;
    private int col;
    private Showtime showtime;
    private double price;
    private boolean booked;

    public Seat(int row, int col, Showtime showtime) {
        this.row = row;
        this.col = col;
        this.showtime = showtime;
        booked = false;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
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

    public String getSeat() {
        return String.valueOf((char)(65+row)) + (col+1);
    }

    public double getPrice() {
        return price;
    }

    public void setCol(int colNum) {
        col = colNum;
    }

    public void setRow(int rowNum) {
        row = rowNum;
    }

    public void setPrice(double price) {
        this.price = price;
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
