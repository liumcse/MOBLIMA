package View;

import View.moviegoer.BookingHistoryView;
import View.moviegoer.MovieListing;

import static Controller.IOController.*;

/**
 * This class represents the moviegoer view.
 *
 * @version 1.0
 */
public class MovieGoerView extends View {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        printHeader("Moviegoer");
        printMenu("Welcome, please make a selection:",
                "1. Search or list movies",
                "2. View booking history",
                "3. Go back","");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                intent(this, new MovieListing());
                break;
            case 2:
                intent(this, new BookingHistoryView());
                break;
            case 3:
                destroy();
                break;
        }
    }
}