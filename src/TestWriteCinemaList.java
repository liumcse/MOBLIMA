import static Controller.CineplexManager.*;

import Controller.DataManager;
import Model.Cinema;
import Model.Constant.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestWriteCinemaList {
    public static void main(String[] args) {
        HashMap<Cineplex, ArrayList<Cinema>> cinemaList = new HashMap<>();

        cinemaList.put(Cineplex.JEM, new ArrayList<>());
        cinemaList.put(Cineplex.TheCathay, new ArrayList<>());

        cinemaList.get(Cineplex.JEM).add(new Cinema(Cineplex.JEM,true, MovieType.ThreeD, "XYZ"));
        cinemaList.get(Cineplex.JEM).add(new Cinema(Cineplex.JEM,false, MovieType.Digital, "ACS"));

        cinemaList.get(Cineplex.TheCathay).add(new Cinema(Cineplex.TheCathay,true, MovieType.ThreeD, "SAD"));
        cinemaList.get(Cineplex.TheCathay).add(new Cinema(Cineplex.TheCathay,false, MovieType.Digital, "VSA"));

        try {
            DataManager.writeSerializedObject("res/data/cinemaList.dat", cinemaList);
            System.out.println("Success");
        }
        catch (IOException ex) {
            System.out.println("Fail");
        }
    }
}
