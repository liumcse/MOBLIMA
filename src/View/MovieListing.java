package View;

import Controller.CineplexManager;
import Model.Movie;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class MovieListing {
    Scanner sc;
    CineplexManager cineplexManager;

    public MovieListing() {
        sc = new Scanner(System.in);
        cineplexManager = new CineplexManager();
        displayMenu();
    }

    private void displayMenu() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("---Movie listing menu---");
            System.out.println("1. Search movies");
            System.out.println("2. List all movies");
            System.out.println("3. List the top 5 movies by ticket sales");
            System.out.println("4. List the top 5 movies by overall ratings");
            System.out.println("5. Go back");
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        listMovie(0);
                        break;
                    case 3:
                        listMovie(1);
                        break;
                    case 4:
                        listMovie(2);
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid selection, try again:");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid selection, try again:");
                sc.nextLine();  // to flush the buffer
            }
        }

    }

    private void listMovie(int option) {
        if (option == 0) {  // list all movies
            ArrayList<Movie> movieList = cineplexManager.getMovieListing();
            int i = 1;

            System.out.println("---Movies---");
            for (Movie movie : movieList) {
                System.out.println(i + ". " + movie.getTitle() + " (" + movie.getStatus().toString() + ")");
                i++;
            }
            System.out.println(i + ". Go back");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            if (choice == i) return;
            else movieOptions(movieList.get(i - 2));
        }
    }

    private void movieOptions(Movie movie) {
        System.out.println("---Movie details---");
        System.out.println(movie);

        int choice = 0;
        while (choice != 4) {
            System.out.println("1. Display showtimes");
            System.out.println("2. Display reviews");
            System.out.println("3. Write reviews");
            System.out.println("4. Go back");
            try {
                switch (choice) {
                    case 1:
                        // TODO movie.getShowtime()
                        break;
                    case 2:
                        // TODO movie.getReview()
                        break;
                    case 3:
                        // TODO writeReview()
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid selection, try again:");
                        break;
                }
            }   catch (InputMismatchException ex) {
                System.out.println("Invalid selection, try again:");
                sc.nextLine();  // to flush the buffer
            }
        }
    }
}