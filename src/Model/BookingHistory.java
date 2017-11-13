package Model;

import java.io.Serializable;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class BookingHistory implements Serializable {
    private final String TID;
    private final Customer customer;
    private final Seat seat;

    public BookingHistory(String TID, Customer customer, Seat seat) {
        this.TID = TID;
        this.customer = customer;
        this.seat = seat;
    }

    @Override
    public String toString() {
        return TID + "\n" +
                "Name: " + customer.getName() + "\n" +
                "Mobile: " + customer.getMobile() + "\n" +
                "Email: " + customer.getEmail() + "\n" +
                "Movie: " + seat.getShowtime().getMovie().getTitle() + "\n" +
                seat.getShowtime().getDetails() +
                "Seat: Row " + (seat.getRow()+1) + " Col " + ((seat.getCol() > 8) ? seat.getCol() : (seat.getCol()+1)) + "\n";
    }
}
