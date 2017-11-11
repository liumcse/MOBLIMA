package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Model.Constant.*;

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
//    private ArrayList<ShowtimeView> showtime;

    public Movie() {
        // empty constructor
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

    public void setSales(int sales) {
        this.sales = sales;
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
        stringBuilder.append(getTitle() + "\n");
        stringBuilder.append(ageRestriction.toString() + "\n");
        stringBuilder.append("Director: " + director + "\n");
        stringBuilder.append("Synopsis: " + synopsis + "\n");

        stringBuilder.append("Casts: ");
        for (String s : cast) stringBuilder.append(s + ", ");
        stringBuilder.append("\n");

        stringBuilder.append("MovieStatus: " + movieStatus.toString() + "\n");

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