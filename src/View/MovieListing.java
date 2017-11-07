package View;

import Controller.CineplexManager;
import Model.Movie;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by LiuMingyu on 6/11/17.
 * This is the interface when user wants to see movie listing.
 */

public class MovieListing {
    Scanner sc;

    public MovieListing() {
        sc = new Scanner(System.in);
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
                        displayMovieList(0);
                        break;
                    case 3:
                        displayMovieList(1);
                        break;
                    case 4:
                        displayMovieList(2);
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

    private void displayMovieList(int option) {
        if (option == 0) {  // list all movies
            ArrayList<Movie> movieList = CineplexManager.getMovieListing();
            int i = 0;

            System.out.println("---Movies---");
            for (Movie movie : movieList) {
                System.out.println(++i + ". " + movie.getTitle() + " (" + movie.getStatus().toString() + ")");
            }
            System.out.println(i + 1 + ". Go back");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            if (choice == i + 1) return;
            else displayMovieOptions(movieList.get(i - 1));
        }
    }

    private void displayMovieOptions(Movie movie) {
        System.out.println("---Movie details---");
        System.out.println(movie);

        int choice = 0;
        while (choice != 4) {
            System.out.println("1. Display showtimes");
            System.out.println("2. Display reviews");
            System.out.println("3. Write reviews");
            System.out.println("4. Go back");
            choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        // TODO movie.getShowtime()
                        movie.displayShowtime();
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