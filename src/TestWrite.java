import Controller.DataManager;
import Model.Constant;
import Model.Movie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static Model.Constant.Status.COMMING_SOON;
import static Model.Constant.Status.NOW_SHOWING;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class TestWrite {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        ArrayList<Movie> arrayList = new ArrayList<>();

//        Scanner sc = new Scanner(System.in);
        Scanner sc = null;
        try {
            sc = new Scanner(new File("res/data/input_temp.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            System.out.println("Enter name");
            String title = sc.nextLine();

            System.out.println("Enter Status");
            Constant.Status status;
            switch (sc.next()) {
                case "COMMING_SOON":
                    status = COMMING_SOON;
                    break;
                default:
                    status = NOW_SHOWING;
                    break;
            }

            System.out.println("Enter synopsis:");
            sc.nextLine();
            String synopsis = sc.nextLine();

            System.out.println("Enter director:");
            String director = sc.nextLine();

            System.out.println("Enter casts:");
            String[] castArray = sc.nextLine().split(",");
            ArrayList<String> cast = new ArrayList<>();
            for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);

            Movie movie = new Movie(title);
            movie.setStatus(status);
            movie.setSynopsis(synopsis);
            movie.setDirector(director);
            movie.setCast(cast);

            arrayList.add(movie);
        }

        dataManager.writeSerializedObject("res/data/movie.dat", arrayList);
        System.out.println("Bye");
    }
}
