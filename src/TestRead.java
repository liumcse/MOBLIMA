import Controller.DataManager;
import Model.Movie;

import java.util.ArrayList;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class TestRead {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        ArrayList<Movie> arrayList = new ArrayList<>();

        arrayList = (ArrayList) dataManager.readSerializedObject("res/data/movie.dat");

        for (Movie movie : arrayList) System.out.println(movie);
    }
}
