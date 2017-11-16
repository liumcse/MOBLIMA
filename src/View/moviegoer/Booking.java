package View.moviegoer;

import Model.*;
import View.View;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;

/**
 * This class represents booking view.
 *
 * @version 1.0
 */
public class Booking extends View {
    private Seat seat;
    private String ticketType;
    private double basePrice;
    private boolean bookingFinished;

    /**
     * Allocates a {@code Booking} object and initializes it specified with seat.
     * @param seat the seat to be booked
     */
    public Booking(Seat seat) {
        this.seat = seat;
        basePrice = seat.getShowtime().getCinema().getBasePrice();
        bookingFinished = false;
        computeBasePrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        if (bookingFinished) destroy();
        else displayMenu();
    }

    /**
     * This method is to display the main menu of booking.
     */
    private void displayMenu() {
        printHeader("Booking detail");
        printBookingDetail();
        printMenu("", "1. Proceed",
                "2. Go back", "");
        int choice = readChoice(1, 2);
        switch (choice) {
            case 1:
                promptCustomerInformation();
                break;
            case 2:
                destroy();
                break;
        }
    }

    /**
     * This method is to compute the base price taking weekend, weekday or holiday into consideration.
     */
    private void computeBasePrice() {
        Holiday holiday = getHoliday(seat.getShowtime().getTime());
        if (holiday != null) {
            double rate = holiday.getRate();
            basePrice = rate * basePrice;
            ticketType = holiday.getName();
        }
        else {
            if (isWeekend(seat.getShowtime().getTime())) {
                basePrice = basePrice * 1.2;
                ticketType = "Weekend";
            }
            else {
                ticketType = "Weekday";
            }
        }
    }

    /**
     * This method is to print the booking detail
     */
    private void printBookingDetail() {
        Showtime showtime = seat.getShowtime();
        Movie movie = showtime.getMovie();
        Cinema cinema = showtime.getCinema();

        System.out.println(movie.getTitle() + " (" + (showtime.getCinema().is3D() ? "3D" : "Digital") + ")");
        System.out.println(movie.getAgeRestriction());
        System.out.println("Cinema: " + cinema + " (" + cinema.getCineplex() + ")");
        System.out.println("Showing on " + formatTimeMMddkkmm(showtime.getTime()));
        System.out.println("Seat: Row " + (seat.getRow()+1) + " Col " + ((seat.getCol() > 8) ? seat.getCol() : (seat.getCol()+1)));
        System.out.println();
        System.out.println("Ticket type: " + ticketType);
        System.out.println("Ticket price: " + round(basePrice, 2) + " SGD (Excl. GST)");
    }

    /**
     * This method is to ask the user to enter their information from standard input.
     */
    private void promptCustomerInformation() {
        String name = readString("Please enter your name:");
        String mobile = readString("Please enter your mobile number:");
        String email = readEmail("Please enter your Email address:");
        boolean isSeniorCitizen = askConfirm("Are you a senior citizen?",
                                    "Enter Y if yes (validation will be done upon entering):");

        // Create customer object
        Customer customer = new Customer(name, mobile, email, isSeniorCitizen);

        // proceed to payment
        bookingFinished = true;
        intent(this, new Payment(customer, seat, basePrice));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void destroy() {
        ((MovieListing)(prevView.prevView)).start(seat.getShowtime().getMovie());
    }
}
