package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class contains all information of one piece of review - including the
 * reviewing date, rating, reviewing content, reviewing {@code Movie} and reviewer's
 * name.
 *
 * @version 1.0
 */

public class Review implements Serializable {
    private final Date date;
    private final int rating;
    private final String content;
    private final Movie movie;
    private final String name;

    /** maximum rating the reviewer can give */
    private static final int maxRating = 5;

    /** minimum rating the reviewer can give */
    private static final int minRating = 1;

    /**
     * Constructor. It takes the movie, rating, content and reviewer's name.
     * @param movie the movie reviewed
     * @param rating the rating reviewer gives
     * @param content the content of the review
     * @param name the name of reviewer
     */
    public Review(Movie movie, int rating, String content, String name) {
        if(rating > maxRating) this.rating = maxRating;
        else if (rating < minRating) this.rating = minRating;
        else this.rating = rating;

        this.date = new Date();
        this.content = content;
        this.movie = movie;
        this.name = name;
    }

    /**
     * This method is to get the {@code Movie} reviewed.
     * @return the movie reviewed
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * This method is to get the content of the review.
     * @return the content of the review
     */
    public String getContent() {
        return content;
    }

    /**
     * This method is to get the rating reviewer gives.
     * @return the rating reviewer gives
     */
    public int getRating() {
        return rating;
    }

    /**
     * This method is to get the reviewer's name.
     * @return the reviewer's name
     */
    public String getName() { return name; }

    /**
     * This method is to get the time when the review is made.
     * @return the time when the review is made
     */
    public Date  getDate() { return date; }

}

