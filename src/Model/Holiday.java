package Model;

import java.io.Serializable;
import java.util.Date;

import static Controller.IOController.*;

/**
 * This class contains all information of a holiday - including its name, date
 * and price rate.
 *
 * @version 1.0
 */

public class Holiday implements Serializable {
    private String name;
    private Date date;
    private double rate;

    /**
     * Allocates a {@code Holiday} object and initializes it specified by the name,
     * date and price rate.
     * @param name the name of the holiday
     * @param date the date of the holiday
     * @param rate the price rate of the holiday
     */
    public Holiday(String name, Date date, double rate) {
        this.name = name;
        this.date = date;
        this.rate = rate;
    }

    /**
     * This method is to get the name of the holiday.
     * @return the name of the holiday
     */
    public String getName() {
        return name;
    }

    /**
     * This method is to get the date of the holiday.
     * @return the date of the holiday
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method is to get the price rate of the holiday.
     * @return the price rate of the holiday
     */
    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return name + " (" + formatTimeMMdd(date) + ")";
    }
}
