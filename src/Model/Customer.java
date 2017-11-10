package Model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String mobile;
    private String email;
    private boolean isSeniorCitizen;

    public Customer(String name, String mobile, String email, boolean isSeniorCitizen) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.isSeniorCitizen = isSeniorCitizen;
    }

    public boolean isSeniorCitizen() {
        return isSeniorCitizen;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

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