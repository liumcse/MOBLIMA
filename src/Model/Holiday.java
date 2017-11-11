package Model;

import static Controller.IOController.*;
import java.util.Date;

public class Holiday {
    private String name;
    private Date date;
    private double discount;

    public Holiday(String name, Date date, double discount) {
        this.name = name;
        this.date = date;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return formatTimeMMdd(date) + ": " + name;
    }
}
