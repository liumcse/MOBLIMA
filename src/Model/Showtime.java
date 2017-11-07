package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class Showtime implements Serializable {
    // TODO date
    // TODO time
    private String hour;
    private String minute;
    private Cinema cinema;

    // TODO modify this
    public Showtime(String hour, String minute, Cinema cinema) {
        this.hour = hour;
        this.minute = minute;
        this.cinema = cinema;
    }

    public void checkSeat() {

    }

    public void displayMenu() {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while (choice != 3) {
            System.out.println("---" + this + "---");
            System.out.println("1. Check seat availability");
            System.out.println("2. Book seat");
            System.out.println("3. Go back");

            choice = sc.nextInt();
        }
    }

    public Cinema getCinema() {
        return cinema;
    }

    @Override
    public String toString() {
        return cinema.getCineplex().toString() + ": " + hour + minute;
    }
}