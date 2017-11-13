package Controller;

import Controller.CineplexManager;
        import Controller.DataManager;
        import Model.Cinema;
        import Model.Movie;
        import Model.Showtime;
        import static Controller.CineplexManager.*;
        import static Model.Constant.*;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.HashMap;

public class TestWriteShowtime {
    public static void main(String[] args) {
        try {
            CineplexManager.initialize();

            ArrayList<Movie> movieArrayList = (ArrayList<Movie>) DataManager.readSerializedObject("res/data/movieListing.dat");

            for (Movie movie : movieArrayList) System.out.println(movie);

            HashMap<Movie, ArrayList<Showtime>> movieShowtime = new HashMap<>();

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd kk:mm");

            for (Movie movie : movieArrayList) {
                System.out.println(movie.getTitle() + ": input showtime");
                ArrayList<Showtime> tempArrayList = new ArrayList<>();
                Showtime tempShowtime1 = new Showtime();
                Showtime tempShowtime2 = new Showtime();

                Date time = ft.parse("2017-11-12 18:30");

                tempShowtime1.setMovie(movie);
                tempShowtime2.setMovie(movie);
                tempShowtime1.setCinema(new Cinema(Cineplex.TheCathay, true, MovieType.ThreeD, "ACS", 10));
                tempShowtime2.setCinema(new Cinema(Cineplex.TheCathay, true, MovieType.ThreeD, "ACS", 10));


                tempShowtime1.setTime(time);
                tempShowtime2.setTime(time);
                tempShowtime1.setMovie(movie);
                tempShowtime2.setMovie(movie);

                tempArrayList.add(tempShowtime1);
                tempArrayList.add(tempShowtime2);
                movieShowtime.put(movie, tempArrayList);
            }

            DataManager.writeSerializedObject("res/data/showtime.dat", movieShowtime);

            System.out.println("Finished");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("0: " + getMovieListing().get(0).getTitle());
        System.out.println("1: " + getMovieListing().get(1).getTitle());

    }
}
