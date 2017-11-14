package Controller;

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
        cinemaList.put(Cineplex.WestMall, new ArrayList<>());
        cinemaList.put(Cineplex.AMK_Hub, new ArrayList<>());

        cinemaList.get(Cineplex.JEM).add(new Cinema(Cineplex.JEM,true, true, "GBP", 10));
        cinemaList.get(Cineplex.JEM).add(new Cinema(Cineplex.JEM,false, true, "FRA", 10));
        cinemaList.get(Cineplex.JEM).add(new Cinema(Cineplex.JEM,false, false, "GER", 10));

        cinemaList.get(Cineplex.TheCathay).add(new Cinema(Cineplex.TheCathay,true, true, "CHN", 10));
        cinemaList.get(Cineplex.TheCathay).add(new Cinema(Cineplex.TheCathay,false, true, "JPN", 10));
        cinemaList.get(Cineplex.TheCathay).add(new Cinema(Cineplex.TheCathay,false, false, "KOR", 10));

        cinemaList.get(Cineplex.WestMall).add(new Cinema(Cineplex.WestMall,true, true, "USA", 10));
        cinemaList.get(Cineplex.WestMall).add(new Cinema(Cineplex.WestMall,false, true, "CAN", 10));
        cinemaList.get(Cineplex.WestMall).add(new Cinema(Cineplex.WestMall,false, false, "AUS", 10));

        cinemaList.get(Cineplex.AMK_Hub).add(new Cinema(Cineplex.AMK_Hub,true, true, "SGP", 10));
        cinemaList.get(Cineplex.AMK_Hub).add(new Cinema(Cineplex.AMK_Hub,false, true, "MYS", 10));
        cinemaList.get(Cineplex.AMK_Hub).add(new Cinema(Cineplex.AMK_Hub,false, false, "THA", 10));
        try {
            DataManager.writeSerializedObject("res/data/cinemaList.dat", cinemaList);
            System.out.println("Success");
        }
        catch (IOException ex) {
            System.out.println("Fail");
        }
    }
}
