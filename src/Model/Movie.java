package Model;

import java.util.LinkedList;

public class Movie {
    private String title;
    private Status movieStatus;
    private String synopsis;
    private String director;
    private LinkedList<String> casters;
    private double overallRating;
    private LinkedList<Review> reviews;
}
