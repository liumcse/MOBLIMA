package Model;

import java.io.Serializable;

/**
 * This class contains all information of a customer - including its name,
 * mobile number, Email address and whether the customer is a senior citizen.
 *
 * @version 1.0
 */

public class Customer implements Serializable {
    private final String name;
    private final String mobile;
    private final String email;
    private final boolean isSeniorCitizen;

    /**
     * Allocates a {@code Customer} object and initializes it specified by its
     * name, mobile number, Email address and whether the customer is a senior
     * citizen.
     * @param name the name of the customer
     * @param mobile the mobile number of the customer
     * @param email the Email address of the customer
     * @param isSeniorCitizen true if the customer is a senior citizen, false if not
     */
    public Customer(String name, String mobile, String email, boolean isSeniorCitizen) {
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.isSeniorCitizen = isSeniorCitizen;
    }

    /**
     * This method is to get whether the customer is a senior citizen.
     * @return true if the customer is a senior citizen, false if not
     */
    public boolean isSeniorCitizen() {
        return isSeniorCitizen;
    }

    /**
     * This method is to get the name of the customer.
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * This method is to get the mobile number of the customer.
     * @return the mobile number of the customer
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method is to get the Email of the customer.
     * @return the Email of the customer
     */
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!name.equals(customer.name)) return false;
        if (!mobile.equals(customer.mobile)) return false;
        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + mobile.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}