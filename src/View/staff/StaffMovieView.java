package View.staff;

import Controller.CineplexManager;
import Model.Constant;
import Model.Movie;
import Model.Showtime;

import java.io.IOException;
import java.util.*;

public class StaffMovieView {
    Scanner sc;

    public StaffMovieView() {
        sc = new Scanner(System.in);
        displayMenu();
    }

    private void displayMenu() {
        ArrayList<Movie> movieListing;
        movieListing = CineplexManager.getMovieListing();

        if (movieListing == null) {
            System.out.println("Movie listing is not available");
            return;
        }  // TODO redundant?

        int index = 0;

        System.out.println("---Modify movie listing---");
        for (Movie movie : movieListing) {
            System.out.println(++index + ". " + movie.getTitle() + " (" + movie.getStatus().toString() + ")");
        }
        System.out.println(index + 1 + ". Go back");

        System.out.println("Please choose a movie to modify.");
        System.out.println("To list a new movie, enter 0.");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if (choice == index + 1) return;
        else if (choice == 0) {
            listNewMovie();
        }
        else displayMovieDetailMenu(movieListing.get(index - 1));
    }

    private void displayMovieDetailMenu(Movie movie) {
        System.out.println("---Movie details---");
        System.out.println(movie);

        int choice = -1;
        while (choice != 4) {
            System.out.println("1. Update movie details");
            System.out.println("2. Update showtime");
            System.out.println("3. Remove the listing");
            System.out.println("4. Go back");
            choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        displayShowtimeMenu(movie);
                        break;
                    case 3:
                        try {
                            CineplexManager.removeListing(movie);
                            System.out.println("Movie status has been set to End of showing.");
                            displayMenu();
                        }
                        catch (IOException ex) {
                            System.out.println("Failed to remove the listing.");
                        }
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid selection, try again:");
                        break;
                }
            }   catch (InputMismatchException ex) {
                System.out.println("Invalid selection, try again:");
                sc.nextLine();  // to flush the buffer
            }
        }


    }

    private void listNewMovie() {
        String title, director, synopsis;
        Constant.MovieRestriction movieRestriction;
        ArrayList<String> cast;
        Constant.Status status;

        System.out.println("---List new movie---");
        System.out.println("Enter the title:");
        title = sc.nextLine();

        while (true) {
            System.out.println("Choose the movie restriction, please enter one of the following:\n" +
                    "G, PG, PG13, NC16, M18, R21");
            switch (sc.nextLine().toUpperCase()) {
                case "G":
                    movieRestriction = Constant.MovieRestriction.G;
                    break;
                case "PG":
                    movieRestriction = Constant.MovieRestriction.PG;
                    break;
                case "PG13":
                    movieRestriction = Constant.MovieRestriction.PG13;
                    break;
                case "NC16":
                    movieRestriction = Constant.MovieRestriction.NC16;
                    break;
                case "M18":
                    movieRestriction = Constant.MovieRestriction.M18;
                    break;
                case "R21":
                    movieRestriction = Constant.MovieRestriction.R21;
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
            System.out.println("Enter movie status, please enter one of the following:\n" +
                    "Coming soon, Now showing, End of showing");
            switch (sc.nextLine().toLowerCase()) {
                case "coming soon":
                    status = Constant.Status.COMING_SOON;
                    break;
                case "now showing":
                    status = Constant.Status.NOW_SHOWING;
                    break;
                case "end of showing":
                    status = Constant.Status.END_OF_SHOWING;
                default:
                    System.out.println("Invalid input. Try again.");
                    continue;
            }
            break;
        }

        // create movie object
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setMovieRestriction(movieRestriction);
        movie.setDirector(director);
        movie.setSynopsis(synopsis);
        movie.setCast(cast);
        movie.setStatus(status);

        // write to file
        try {
            CineplexManager.addNewListing(movie);
            System.out.println("Successfully listed movie " + title);
            displayMenu();
        }
        catch (IOException ex) {
            System.out.println("Failed to list the movie.");
            displayMenu();
        }
    }

    private void displayShowtimeMenu(Movie movie) {
        ArrayList<Showtime> showtimeList = CineplexManager.getMovieShowtime(movie);
        Collections.sort(showtimeList, new Comparator<Showtime>() {
            @Override
            public int compare(Showtime o1, Showtime o2) {
                return o1.getCinema().getCineplex().toString().compareTo(o2.getCinema().getCineplex().toString());
            }
        });

        int index = 0;
        for (Showtime s : showtimeList) System.out.println(++index + ": " + s);

        System.out.println("Please choose a showtime (enter 0 to go back):");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if (choice == 0) return;

        Showtime showtime = showtimeList.get(choice - 1);
        displayShowtimeDetailMenu(showtime);

    }

    private void displayShowtimeDetailMenu(Showtime showtime) {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while (choice != 5) {
            System.out.println("---" + showtime + "---");
            System.out.println("1. Set cineplex");
            System.out.println("2. Set cinema");
            System.out.println("3. Set date and time");
            System.out.println("4. Set ticket price");
            System.out.println("5. Go back");
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid selection.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid selection.");
                sc.nextLine();
                continue;
            }
        }
    }
}
