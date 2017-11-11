package Model;

import static Controller.IOController.*;

import java.io.Serializable;
import java.util.Date;

public class Holiday implements Serializable {
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
        return name + " (" + formatTimeMMdd(date) + ")";
    }
}
