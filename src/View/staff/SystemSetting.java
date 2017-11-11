package View.staff;

import static Controller.IOController.*;
import static Controller.CineplexManager.*;

import Controller.CineplexManager;
import Model.Holiday;
import View.View;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class SystemSetting extends View{
    @Override
    protected void start() {
        displayMenu();
    }

    private void displayMenu() {
        printHeader("System setting");
        printMenu("1. Configure ticket prices.",
                "2. Configure holidays.",
                "3. Go back",
                "");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                break;
            case 2:
                configureHolidays();
                break;
            case 3:
                destroy();
                break;
        }
    }

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

    private void displayHolidayList() {
        printHeader("Holiday list");
        HashMap<Date, Holiday> holidayList = getHolidayList();
        if (holidayList.isEmpty()) {
            printMenu("No holiday exists.");
            readString("Press ENTER to go back.");
            configureHolidays();
        }
        else {
            int index  = 0;
            for (Date date : holidayList.keySet()) {
                System.out.println(++index + ". " + holidayList.get(date));
            }
            System.out.println(++index + ". Go back");

            int choice = readChoice(1, index);
            if (choice == index) configureHolidays();
            else displayHolidayDetail();
        }
    }

    private void displayHolidayDetail() {

    }

    private void addHoliday() {
        String name;
        Date date;
        double discount;

        name = readString("Enter the name of the holiday:");
        date = readTimeMMdd("Enter the date of the holiday",
                "Format: MM-DD (e.g. 12-25)");
        discount = readDouble("Enter the discount on that day:",
                "e.g. 30 stands for 30% off");

        Holiday holiday = new Holiday(name, date, discount);

        try {
            CineplexManager.addHoliday(holiday);
            System.out.println("Successfully added the holiday.");
        } catch (IOException ex) {
            System.out.println("Failed to add the holiday.");
        }

        displayHolidayList();
    }
}
