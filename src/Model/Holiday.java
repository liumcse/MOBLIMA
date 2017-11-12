package Model;

import java.io.Serializable;
import java.util.Date;

import static Controller.IOController.*;

public class Holiday implements Serializable {
    private String name;
    private Date date;
    private double rate;

    public Holiday(String name, Date date, double rate) {
        this.name = name;
        this.date = date;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return name + " (" + formatTimeMMdd(date) + ")";
    }
}
