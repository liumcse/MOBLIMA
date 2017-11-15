package Model;

import Model.Constant.*;

import java.io.Serializable;
import java.util.ArrayList;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;

/**
 * This class contains all information of a movie - including  its title, age restriction,
 * director, synopsis, cast, status and ticket sales.
 *
 * @version 1.0
 */

public class Movie implements Serializable {
    private String title;
    private AgeRestriction ageRestriction;
    private String director;
    private String synopsis;
    private ArrayList<String> cast;
    private MovieStatus movieStatus;
    private int sales;

    /**
     * Constructor, no parameter.
     */
    public Movie() {
        this.sales = 0;
    }

    /**
     * This method is used to set the title.
     * @param title This is the title to be assigned
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method is used to set age restriction.
     * @param ageRestriction this is the age restriction to be assigned
     */
    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    /**
     * This method is used to set synopsis.
     * @param synopsis this is the synopsis to be assigned
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * This method is used to set director.
     * @param director this is the director to be assigned
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * This method is used to set cast.
     * @param cast this is the cast to be assigned
     */
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * This method is used to set movie status.
     * @param movieStatus this is the movie status to be assigned
     */
    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    /**
     * This method is used to increment ticket sale by one.
     */
    public void incrementSales() {
        this.sales += 1;
    }

    /**
     * This method is to get the title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method is to get movie status.
     * @return movie status
     */
    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    /**
     * This method is to get age restriction.
     * @return age restriction
     */
    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    /**
     * This method is to get ticket sale.
     * @return ticket sale
     */
    public int getSales() {
        return sales;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder casts = new StringBuilder();
        double rating = getMovieRating(this);
        stringBuilder.append("Title:    ").append(getTitle()).append("\n");
        stringBuilder.append("Restrict: ").append(ageRestriction.toString()).append("\n");
        stringBuilder.append("Director: ").append(director).append("\n");
        stringBuilder.append("Synopsis: ").append("\"").append(synopsis).append("\"").append("\n");

        stringBuilder.append("Casts:    ");
        for (String s : cast) casts.append(s).append(", ");
        stringBuilder.append(addLinebreaks(casts.toString(), 50, 10));
        stringBuilder.append("\n");
        stringBuilder.append("Rating:   ");
        if (rating == 0.0) stringBuilder.append("No rating").append("\n");
        else stringBuilder.append(getMovieRating(this)).append("/5.0").append("\n");
        stringBuilder.append("Status:   ").append(movieStatus.toString()).append("\n");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        return director != null ? director.equals(movie.director) : movie.director == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (director != null ? director.hashCode() : 0);
        return result;
    }
}