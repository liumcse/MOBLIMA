package View.moviegoer;

import Model.*;
import View.View;

import static Controller.IOController.*;

public class Booking extends View{
    private Seat seat;

    public Booking(Seat seat) {
        this.seat = seat;
    }

    @Override
    protected void start() {
        printHeader("Booking detail");
        printBookingDetail(seat);
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



    private void printBookingDetail(Seat seat) {
        Showtime showtime = seat.getShowtime();
        Movie movie = showtime.getMovie();
        Cinema cinema = showtime.getCinema();

        System.out.println(movie.getTitle() + " (" + cinema.getMovieType() + ")");
        System.out.println(movie.getAgeRestriction());
        if (cinema.isPlatinum()) {
            System.out.println("Cinema: " + cinema + " (" + cinema.getCineplex() + ")");
        }
        System.out.println("Showing on " + formatTimeMMddkkmm(showtime.getTime()));
        System.out.println("Ticket cost: " + round(cinema.getBasePrice(), 2) + " SGD (Excl. GST)");
    }

    private void promptCustomerInformation() {
        String name = readString("Please enter your name:");
        String mobile = readString("Please enter your mobile number:");
        String email = readString("Please enter your Email address:");
        boolean isSeniorCitizen = askConfirm("Are you a senior citizen?",
                                    "Enter Y if yes (validation will be done upon entering):");

        // Create customer object
        Customer customer = new Customer(name, mobile, email, isSeniorCitizen);

        // proceed to payment
        intent(this, new Payment(customer, seat));
    }
}
