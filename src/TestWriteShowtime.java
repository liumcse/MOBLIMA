import Controller.DataManager;
import Model.Cinema;
import Model.Constant;
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
            Showtime tempShowtime1 = new Showtime("09", "30", new Cinema(Constant.Cineplex.TheCathay));
            Showtime tempShowtime2 = new Showtime("18", "30", new Cinema(Constant.Cineplex.WestMall));
            tempArrayList.add(tempShowtime1);
            tempArrayList.add(tempShowtime2);
            movieShowtime.put(movie, tempArrayList);
        }

        DataManager.writeSerializedObject("res/data/showtime.dat", movieShowtime);
    }
}
