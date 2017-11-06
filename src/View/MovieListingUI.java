package View;

import Controller.CineplexManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class MovieUI {
    Scanner sc;
    CineplexManager cineplexManager;

    public MovieUI() {
        sc = new Scanner(System.in);
        cineplexManager = new CineplexManager();
        displayMenu();
    }

    private void displayMenu() {
        int choice = 0;
        System.out.println("1. List all movies");
        System.out.println("2. List the top 5 movies by ticket sales");
        System.out.println("3. List the top 5 movies by overall ratings");
        System.out.println("4. Go back");

        while (choice != 4) {
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        listMovie(0);
                        break;
                    case 2:
                        listMovie(1);
                        break;
                    case 3:
                        listMovie(2);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid selection, try again:");
                        choice = sc.nextInt();
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid selection, try again:");
                choice = sc.nextInt();
            }
        }

    }

    private void listMovie(int option) {
        if (option == 0) {  // list all movies
//            cineplexManager.list
            // TODO
        }
    }
}