package Model;

import java.util.Date;

public class Review {
    private Date date;
    private double rating;
    private String content;

    private static final double maxRating = 5;
    private static final double minRating = 1;

    public Review(int movieId, int rating, String content) {
        if(rating > maxRating)
            this.rating = maxRating;
        else if (rating < minRating)
            this.rating = minRating;
        else
            this.rating = rating;

        this.content = content;
        this.date = new Date();

    }

    public Review(int movieId, double rating, Date date, String content) {
        if(rating > maxRating)
            this.rating = maxRating;
        else if (rating < minRating)
            this.rating = minRating;
        else
            this.rating = rating;

        this.date = date;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public double getRating() {
        return rating;
    }

    public String getStars() {
        StringBuffer stars = new StringBuffer();
        for(int i = 0; i < rating; i++)
            stars.append("★");
        for(int i = 0; i < maxRating-rating; i++)
            stars.append("☆");

        return stars.toString();
    }
}

