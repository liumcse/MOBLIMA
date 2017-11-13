package Model;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    private final Date date;
    private final int rating;
    private final String content;
    private final Movie movie;
    private final String name;

    private static final int maxRating = 5;
    private static final int minRating = 1;

    public Review(Movie movie, int rating, String content, String name) {
        if(rating > maxRating) this.rating = maxRating;
        else if (rating < minRating) this.rating = minRating;
        else this.rating = rating;

        this.date = new Date();
        this.content = content;
        this.movie = movie;
        this.name = name;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public String getName() { return name; }

    public Date  getDate() { return date; }

}

