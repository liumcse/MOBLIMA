import Controller.CineplexManager;
import Controller.DataManager;
import Model.Constant;
import Model.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class TestWriteMovie {
    public static void main(String[] args) {
        ArrayList<Movie> movieList = new ArrayList<>();
        CineplexManager.initialize();

        Scanner sc = null;
        try {
            sc = new Scanner(new File("res/data/input_temp.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        sc = new Scanner(System.in);

        String title, director, synopsis;
        Constant.AgeRestriction ageRestriction;
        ArrayList<String> cast;
        Constant.MovieStatus movieStatus;
        while (sc.hasNextLine()) {
            System.out.println("---List new movie---");
            System.out.println("Enter the title:");
            title = sc.nextLine();

            while (true) {
                System.out.println("Choose the movie restriction, please enter one of the following:\n" +
                        "G, PG, PG13, NC16, M18, R21");
                switch (sc.nextLine().toUpperCase()) {
                    case "G":
                        ageRestriction = Constant.AgeRestriction.G;
                        break;
                    case "PG":
                        ageRestriction = Constant.AgeRestriction.PG;
                        break;
                    case "PG13":
                        ageRestriction = Constant.AgeRestriction.PG13;
                        break;
                    case "NC16":
                        ageRestriction = Constant.AgeRestriction.NC16;
                        break;
                    case "M18":
                        ageRestriction = Constant.AgeRestriction.M18;
                        break;
                    case "R21":
                        ageRestriction = Constant.AgeRestriction.R21;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                        continue;
                }
                break;
            }

            System.out.println("Enter director");
            director = sc.nextLine();

            System.out.println("Enter synopsis:");
            synopsis = sc.nextLine();

            System.out.println("Enter casts, separate with semicolon(;)");
            String[] castArray = sc.nextLine().split(";");
            cast = new ArrayList<>();
            for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);

            while (true) {
                System.out.println("Enter movie movieStatus, please enter one of the following:\n" +
                        "Coming soon, Now showing, End of showing");
                switch (sc.nextLine().toLowerCase()) {
                    case "coming soon":
                        movieStatus = Constant.MovieStatus.COMING_SOON;
                        break;
                    case "now showing":
                        movieStatus = Constant.MovieStatus.NOW_SHOWING;
                        break;
                    case "end of showing":
                        movieStatus = Constant.MovieStatus.END_OF_SHOWING;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                        continue;
                }
                break;
            }

            // create movie object
            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setAgeRestriction(ageRestriction);
            movie.setDirector(director);
            movie.setSynopsis(synopsis);
            movie.setCast(cast);
            movie.setMovieStatus(movieStatus);
            movieList.add(movie);
            // write to file
            try {
                DataManager.writeSerializedObject("res/data/movieListing.dat", movieList);
                System.out.println("Success");
            } catch (IOException ex) {
                System.out.println("Failed to list new movie. Please check the file integrity.");
            }
        }
    }
}
