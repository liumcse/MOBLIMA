package View.moviegoer;

import Model.BookingHistory;
import View.View;
import java.util.ArrayList;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;

/**
 * This class represents the booking history view.
 *
 * @version 1.0
 */
public class BookingHistoryView extends View {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * This method is to display the main menu of booking history.
     */
    private void displayMenu() {
        printHeader("Booking history");
        ArrayList<BookingHistory> bookingHistory = getBookingHistory();

        if (bookingHistory == null || bookingHistory.isEmpty()) {
            readString("No history to show.",
                    "Press ENTER to go back.", "");
        }
        else {
            for (BookingHistory record : bookingHistory) {
                System.out.println(record);
            }
            readString("Press ENTER to go back.", "");
        }

        destroy();
    }
}
