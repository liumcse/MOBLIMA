package Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOController {
    public static int readChoice(int i, int j) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readChoice(i, j);
        }

        if (choice < i || choice > j) {
            System.out.println("Invalid input, try again.");
            return readChoice(i, j);
        }

        return choice;
    }

    public static void printMenu(String... menu) {
        for (String s : menu) {
            System.out.println(s);
        }
    }

    public static void printBookingDetail() {

    }
}
