package View.staff;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;
import static Model.Constant.*;

import Model.Cinema;
import View.View;

public class CinemaList extends View {
    @Override
    protected void start() {
        displayMenu();
    }

    private void displayMenu() {
        int index = 0;
        for (Cineplex c : Cineplex.values()) {
            index++;
            System.out.println(index + ": " + c);
        }

        printMenu("Choose a cineplex to view cinema codes:");
        int choice = readChoice(1, index);
        displayCinemaList(Cineplex.values()[choice - 1]);
    }

    private void displayCinemaList(Cineplex cineplex) {
        printHeader(cineplex.toString());
        for (Cinema cinema : getCinemaList(cineplex)) {
            System.out.print(cinema + " ");
        }
        System.out.println();
        destroy();
    }

    @Override
    protected void destroy() {
        // TODO bug here
        if (prevView.getClass() == ShowtimeView.class) ((ShowtimeView)prevView).addShowtime();
    }
}
