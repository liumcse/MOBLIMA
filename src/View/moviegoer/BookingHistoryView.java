package View.moviegoer;

import Model.BookingHistory;
import View.View;

import java.util.ArrayList;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;


public class BookingHistoryView extends View {
    @Override
    protected void start() {
        printHeader("Booking history");
        ArrayList<BookingHistory> bookingHistory = getBookingHistory();

        if (bookingHistory.isEmpty()) {
            System.out.println("No history to show.\n");
        }
        for (BookingHistory record : bookingHistory) {
            System.out.println(record);
        }
        destroy();
    }
}
