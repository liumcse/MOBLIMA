package Controller;

import Model.Movie;

import java.io.IOException;

import static Controller.CineplexManager.*;

public class SuperSuper {
    public static void main(String[] args) throws IOException {
        initialize();
        Movie yaoshande = null;
        for (Movie movie : getMovieListing()) {
            if (movie.getTitle().equals("Coco")) yaoshande = movie;
        }

        getMovieListing().remove(yaoshande);

        updateMovieListing();
    }
}
