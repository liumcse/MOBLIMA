import Controller.DataManager;
import Model.Movie;
import Model.Showtime;

import java.util.ArrayList;
import java.util.HashMap;

public class TestWriteShowtime {
    public static void main(String[] args) {
        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) DataManager.readSerializedObject("res/data/movie.dat");

        for (Movie movie : movieArrayList) System.out.println(movie);

        HashMap<Movie, ArrayList<Showtime>> movieShowtime = new HashMap<>();

        for (Movie movie : movieArrayList) {
            System.out.println(movie.getTitle() + ": input showtime");
            ArrayList<Showtime> tempArrayList = new ArrayList<>();
            tempArrayList.add(new Showtime());
            movieShowtime.put(movie, tempArrayList);
        }

        DataManager.writeSerializedObject("res/data/showtime.dat", movieShowtime);
    }
}
