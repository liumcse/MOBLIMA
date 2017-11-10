package Model;

import java.io.Serializable;

/**
 * Created by LiuMingyu on 6/11/17.
 */

public class BookingHistory implements Serializable {
    private String TID;
    private Customer customer;

    public BookingHistory(String TID, Customer customer) {
        this.TID = TID;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return TID + "\n" +
                "Name: " + customer.getName() + "\n" +
                "Mobile: " + customer.getMobile() + "\n" +
                "Email " + customer.getEmail() + "\n";
    }
}
