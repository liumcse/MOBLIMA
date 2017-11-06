import Controller.DataManager;
import Model.Constant;
import Model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static Model.Constant.Status.COMMING_SOON;
import static Model.Constant.Status.NOW_SHOWING;
import static Model.Constant.Status.PREVIEW;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class Test {
    /*
    private String title;
    private Status movieStatus;
    private String synopsis;
    private String director;
    private ArrayList<String> cast;
    private double overallRating;
    private ArrayList<Review> reviews;
     */
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        ArrayList<Movie> arrayList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter name");
            String title = sc.nextLine();

            if (title.equals("fuck")) {
                System.out.println("Bye. Start to write.");
                break;
            }

            System.out.println("Enter Status");
            Constant.Status status;
            switch (sc.next()) {
                case "COMMING_SOON":
                    status = COMMING_SOON;
                    break;
                case "PREVIEW":
                    status = PREVIEW;
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
            String[] castArray = sc.nextLine().split(", ");
            ArrayList<String> cast = new ArrayList<>();
            for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);

            Movie movie = new Movie(title);
            movie.setMovieStatus(status);
            movie.setSynopsis(synopsis);
            movie.setCast(cast);

            arrayList.add(movie);
        }

        dataManager.writeSerializedObject("res/data/movie.dat", arrayList);
    }
}
