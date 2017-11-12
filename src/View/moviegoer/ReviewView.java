package View.moviegoer;

import Controller.CineplexManager;
import Model.Movie;
import Model.Review;
import View.View;

import java.io.IOException;
import java.util.ArrayList;

import static Controller.CineplexManager.*;
import static Controller.IOController.*;
import static Model.Constant.MovieStatus.*;

public class ReviewView extends View{
    private Movie movie;

    public ReviewView(Movie movie) {
        this.movie = movie;
    }

    protected void start(){
        displayMenu();
    }

    private void displayMenu() {
        printHeader("Review");
        if (movie.getMovieStatus() == COMING_SOON) {
            readString("Not allowed to comment on coming soon movies.",
                    "Press ENTER to go back.",
                    "\n");
            destroy();
        }
        printMenu("1. Write a review",
                "2. View all reviews",
                "3. Go back", "");
        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                addReview();
                break;
            case 2:
                listReview();
                break;
            case 3:
                destroy();
                break;
        }
    }

    private void addReview(){
        printHeader("Write Review:");
        String name = readString("Please enter your name:");
        printMenu("Please enter your rating: (integer between 1 ~ 5)");
        int rating = readChoice(1, 5);
        String content = readString("Please enter your comments:");
        Review review = new Review(this.movie, rating, content, name);

        try {
            addNewReview(movie, review);
            System.out.println("Successfully created review for " + movie.getTitle());
        }
        catch (IOException ex) {
            System.out.println("Failed to create review for " + movie.getTitle());
        }
        finally {
            start();
        }
    }

    private void listReview(){
        printHeader("Reviews for " + movie.getTitle());
        ArrayList<Review> reviewList = CineplexManager.getReviewList(movie);

        if (reviewList != null){
            int index = 0;
            for (Review r : reviewList) {
                System.out.println(++index + " Reviewer's name: " + r.getName());
                System.out.println("  Date: " + formatTimeMMddkkmm(r.getDate()));
                System.out.println("  Reviewer's rating: " + r.getRating());
                System.out.println("  Comments: " + r.getContent());
                System.out.println();
            }
        }
        else{
            System.out.println("Currently no review.");
        }
        readString("Press ENTER to go back.", "");
        start();
    }
}
