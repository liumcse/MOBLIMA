package View.moviegoer;


import Controller.CineplexManager;
import Model.BookingHistory;
import Model.Customer;
import Model.Seat;
import View.View;

import static Controller.IOController.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends View {
    private Seat seat;
    private Customer customer;
    private String TID;
    private double basePrice;
    private double GST;
    private double totalPrice;

    @Override
    protected void start() {
        displayMenu();
    }

    public Payment(Customer customer, Seat seat, double basePrice) {
        this.customer = customer;
        this.seat = seat;
        this.basePrice = basePrice;
        generateTID();
        computeTotalPrice();
    }

    private void generateTID() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(seat.getShowtime().getCinema().getCode());
        stringBuffer.append(new SimpleDateFormat("YYYYMMddhhmm").format(new Date().getTime()));  // TODO month is not correctly displayed
        TID = stringBuffer.toString();
    }

    private void computeTotalPrice() {
        if (customer.isSeniorCitizen()) basePrice /= 2;
        GST = round((basePrice + 2) * 0.07, 2);
        totalPrice = round(basePrice + 2 + GST, 2);  // TODO modify this
    }

    private void displayMenu() {
        printHeader("Payment");
        printMenu("Transaction ID: " + TID,
                "Ticket price: " + basePrice,
                "Booking fee: 2.0",
                "GST: " + GST,
                "Grand total: " + totalPrice,
                "");
        if (customer.isSeniorCitizen()) {
            printMenu("(50% off for senior citizen)", "");
        }

        printMenu("1. Confirm payment",
                "2. Cancel",
                "");

        int choice = readChoice(1, 2);
        switch (choice) {
            case 1:
                logBooking();
                break;
            case 2:
                destroy();
                break;
        }
    }

    private void logBooking() {
        try {
            seat.bookSeat();
            seat.getShowtime().getMovie().incrementSales();  // TODO BUG here, it's not working
            BookingHistory record = new BookingHistory(TID, customer, seat);
            CineplexManager.logBooking(record);
            CineplexManager.overwriteShowtime();
            CineplexManager.overwriteListing();
            System.out.println("Ticket sales is now " + seat.getShowtime().getMovie().getSales());
            System.out.println("Payment has been made. We wish you a great day!");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Payment failed.");
        }

        new MovieListing().start();
    }
}
