package Model;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
    private Date date;
    private double rating;
    private String content;
    private Movie movie;
    private String name;

    private static final double maxRating = 5;
    private static final double minRating = 0;

    public Review(Movie movie, double rating, String content, String name) {
        if(rating > maxRating)
            this.rating = maxRating;
        else if (rating < minRating)
            this.rating = minRating;
        else
            this.rating = rating;

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

    public double getRating() {
        return rating;
    }

    public String getName() { return name; }

}

