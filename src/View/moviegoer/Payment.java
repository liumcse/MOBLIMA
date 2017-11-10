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

    public Payment(Customer customer, Seat seat) {
        this.customer = customer;
        this.seat = seat;
        this.basePrice = round(seat.getShowtime().getCinema().getBasePrice(), 2);
        generateTID();
        computeTotalPrice();
    }

    private void generateTID() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(seat.getShowtime().getCinema().getCode());
        stringBuffer.append(new SimpleDateFormat("YYYYMMDDhhmm").format(new Date().getTime()));
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
                "",
                "1. Confirm payment",
                "2. Cancel");
        if (customer.isSeniorCitizen()) {
            printMenu("(50% off for senior citizen)");
        }

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
        BookingHistory record = new BookingHistory(TID, customer);
        try {
            CineplexManager.logBooking(record);
            System.out.println("Payment has been made. We wish you a great day!");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Payment failed.");
        }
    }
}
