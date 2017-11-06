package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Model.Constant.Status;

/**
 * Represent a movie with details.
 */

public class Movie implements Serializable {
    private String title;
    private Status status;
    private String synopsis;
    private String director;
    private ArrayList<String> cast;
    private double rating;  // TODO can we delete this? put rating in Review class
    private ArrayList<Review> reviews;

    public Movie(String title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }


    public ArrayList<Review> getReviews() {
        // TODO complete method: getReviews()
        return null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Title: " + title + "\n");
        stringBuilder.append("Director: " + director + "\n");
        stringBuilder.append("Synopsis: " + synopsis + "\n");

        stringBuilder.append("Casts: ");
        for (String s : cast) stringBuilder.append(s + ", ");
        stringBuilder.append("\n");

        stringBuilder.append("Rating: " + getRating() + "\n");

        stringBuilder.append("Status: " + status.toString() + "\n");

        return stringBuilder.toString();
    }

    public double getRating() {
        // TODO Access control?
        // TODO complete this method
        return 5.0;
    }
}
