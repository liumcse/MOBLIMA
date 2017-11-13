package Model;

import Model.Constant.*;

import java.io.Serializable;
import java.util.ArrayList;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;

/**
 * Represent a movie with details.
 */

public class Movie implements Serializable {
    private String title;
    private AgeRestriction ageRestriction;
    private String director;
    private String synopsis;
    private ArrayList<String> cast;
    private MovieStatus movieStatus;
    private int sales;

    public Movie() {
        this.sales = 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
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

    public void setMovieStatus(MovieStatus movieStatus) {
        this.movieStatus = movieStatus;
    }

    public void incrementSales() {
        this.sales += 1;
    }

    public String getTitle() {
        return title;
    }

    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

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