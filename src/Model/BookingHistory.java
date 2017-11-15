package Model;

import java.io.Serializable;

/**
 * This class contains all information of the booking history - including its
 * TID (Transaction ID), customer who made the booking and the seat booked.
 *
 * @version 1.0
 */
public class BookingHistory implements Serializable {
    private final String TID;
    private final Customer customer;
    private final Seat seat;

    /**
     * Allocates a {@code BookingHistory} object and initializes it specified by
     * its its TID (Transaction ID), customer who made the booking and the seat booked.
     * @param TID Transaction ID
     * @param customer customer who made the booking
     * @param seat the seat booked
     */
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
