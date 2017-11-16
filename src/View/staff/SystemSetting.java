package View.staff;

import Controller.CineplexManager;
import Model.Cinema;
import Model.Holiday;
import View.View;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;

/**
 * This class represents the system setting view.
 *
 * @version 1.0
 */
public class SystemSetting extends View{
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * This method is to display the main menu of system setting.
     */
    private void displayMenu() {
        printHeader("System setting");
        printMenu("1. Configure ticket prices of cinemas",
                "2. Configure top 5 ranking schema",
                "3. Configure cinemas",
                "4. Configure holidays",
                "5. Go back",
                "");

        int choice = readChoice(1, 5);
        switch (choice) {
            case 1:
                configureTicket();
                break;
            case 2:
                configureTop5Ranking();
                break;
            case 3:
                intent(this, new CinemaList("configure"));
                break;
            case 4:
                configureHolidays();
                break;
            case 5:
                destroy();
                break;
        }
    }

    /**
     * This method is to configure the scheme of top 5 ranking of movies.
     */
    private void configureTop5Ranking() {
        printHeader("Configure top 5 ranking schema");
        boolean movieOrder = getSystem().get("movieOrder");

        if (askConfirm("Current top 5 ranking is ordered by " + (movieOrder ? "overall rating" : "ticket sales") + ",",
                "Change to order by " + (!movieOrder ? "overall rating" : "ticket sales") + "?",
                "Enter Y to confirm, N to cancel:")) {
            try {
                getSystem().put("movieOrder", !movieOrder);
                System.out.println("Successfully changed the setting.");
                System.out.println();
                updateSystem();
            } catch (IOException ex) {
                System.out.println("Failed to change the setting.");
                System.out.println();
            }
        }

        displayMenu();
    }

    /**
     * This method is to configure the base ticket price of cinemas.
     */
    private void configureTicket() {
        printHeader("Configure ticket prices of cinemas");
        Cinema cinema = null;

        // get cinema
        while (cinema == null) {
            String input = readString("Enter cinema code (enter \"help\" to look up cinema code)");
            if (input.equals("help")) {
                intent(this, new CinemaList("help"));
                displayMenu();
                return;
            } else {
                cinema = getCinemaByCode(input);
                if (cinema == null) System.out.println("Cinema code is invalid. Try again:");
            }
            System.out.println();
        }

        printHeader(cinema.isPlatinum() ? cinema.getCode() + " (Platinum)" : cinema.getCode());
        if (askConfirm("The ticket price of the cinema is " + cinema.getBasePrice() + ".",
                "Proceed to change?",
                "Enter Y to confirm, N to cancel:")) {
            double newPrice = readDouble("Enter the new ticket price:");

            cinema.setBasePrice(newPrice);
            try {
                updateCinemaList();
                System.out.println("Ticket price has been successfully changed.");
            } catch (IOException ex) {
                System.out.println("Failed to change ticket price.");
            }
        }

        displayMenu();
    }

    /**
     * This method is to display the menu of configuring holidays.
     */
    private void configureHolidays() {
        printHeader("Configure holidays");
        printMenu("1. List all holidays",
                "2. Add a holiday",
                "3. Go back", "");
        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                displayHolidayList();
                break;
            case 2:
                addHoliday();
                break;
            case 3:
                break;
        }

        displayMenu();
    }

    /**
     * This method is to display the list of holidays.
     */
    private void displayHolidayList() {
        printHeader("Holiday list");
        HashMap<String, Holiday> holidayList = getHolidayList();
        HashMap<Integer, Holiday> searchIndex = new HashMap<>();
        if (holidayList.isEmpty()) {
            printMenu("No holiday exists", "");
            readString("Press ENTER to go back");
            configureHolidays();
        }
        else {
            int index  = 0;
            for (String date : holidayList.keySet()) {
                System.out.println(++index + ". " + holidayList.get(date));
                searchIndex.put(index, holidayList.get(date));
            }
            System.out.println(++index + ". Go back");
            System.out.println();

            int choice = readChoice(1, index);
            if (choice == index) configureHolidays();
            else displayHolidayDetail(searchIndex.get(choice));
        }
    }

    /**
     * This method is to display the detail of the holiday and ask user whether
     * to remove the holiday.
     * @param holiday the holiday whose detail to be displayed
     */
    private void displayHolidayDetail(Holiday holiday) {
        printHeader(holiday.getName());
        printMenu(holiday.printDetail(), "");
        if (askConfirm("Enter Y if you want to delete the holiday",
                "Enter N to go back:")) {
            getHolidayList().remove(formatTimeMMdd(holiday.getDate()));
            try {
                updateHolidayList();
                System.out.println("Successfully deleted the holiday.");
            } catch (IOException e) {
                System.out.println("Failed to delete the holiday.");
            }
        }
        displayHolidayList();
    }

    /**
     * This method is to add a holiday.
     */
    private void addHoliday() {
        String name;
        Date date;
        double discount;

        name = readString("Enter the name of the holiday:").toLowerCase();
        date = readTimeMMdd("Enter the date of the holiday",
                "Format: MM-DD (e.g. 12-25)");
        discount = readDouble("Enter the price rate on that day:",
                "e.g. 0.7 stands for ticket price * 0.7");

        Holiday holiday = new Holiday(name, date, discount);

        try {
            CineplexManager.addHoliday(formatTimeMMdd(date), holiday);
            System.out.println("Successfully added the holiday.");
        } catch (IOException ex) {
            System.out.println("Failed to add the holiday.");
        }

        displayHolidayList();
    }
}
