package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Controller.CineplexManager;
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
//    private ArrayList<Showtime> showtime;

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

    public ArrayList<Showtime> getShowtime() {
        return CineplexManager.getMovieShowtime(this);
    }

    public ArrayList<Review> getReviews() {
        // TODO complete method: getReviews()
        return null;
    }

    public void displayShowtime() {
        ArrayList<Showtime> showtime = getShowtime();
        Collections.sort(showtime, new Comparator<Showtime>() {
            @Override
            public int compare(Showtime o1, Showtime o2) {
                return o1.getCinema().getCineplex().toString().compareTo(o2.getCinema().getCineplex().toString());
            }
        });

        int index = 0;
        for (Showtime s : showtime) System.out.println(++index + ": " + s);

        System.out.println("Please choose a showtime (enter 0 to go back):");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice == 0) return;

        showtime.get(choice - 1).displayMenu();
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

    public double getRating() {
        // TODO Access control?
        // TODO complete this method
        return 5.0;
    }
}