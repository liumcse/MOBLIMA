package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;
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

    public void displayMenu() {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while (choice != 3) {
            System.out.println("---" + this + "---");
            System.out.println("1. Check seat availability");
            System.out.println("2. Book seat");
            System.out.println("3. Go back");
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid selection.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid selection.");
                sc.nextLine();
                continue;
            }
        }
    }

    public void checkSeat() {

    }

    public Cinema getCinema() {
        return cinema;
    }

    @Override
    public String toString() {
        return cinema.getCineplex().toString() + ": " + hour + minute;
    }
}