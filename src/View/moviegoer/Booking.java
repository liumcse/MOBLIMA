package View.moviegoer;

import Model.Seat;
import View.View;

import static Controller.IOController.*;

public class Booking extends View{
    Seat seat;

    public Booking(Seat seat) {
        this.seat = seat;
    }

    @Override
    protected void start() {
        printHeader("Booking detail");
        printBookingDetail(seat);
        printMenu("1. Proceed",
                "2. Go back");
        int choice = readChoice(1, 2);
        switch (choice) {
            case 1:
                // TODO proceed booking
                break;
            case 2:
                destroy();
                break;
        }
    }
}
