package Model;

import java.io.Serializable;
import java.util.regex.*;

public class Customer implements Serializable {
    private String name;
    private String mobile;
    private String email;
    private boolean isSeniorCitizen;
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private static boolean validate(String emailStr) {
        Matcher matcher = EMAIL_PATTERN.matcher(emailStr);
        return matcher.matches();
    }

    public Customer(String name, String mobile, String email, boolean isSeniorCitizen) {
        if(!validate(email))
            System.out.println("Invalid email input");
        else
            this.email = email;
        this.name = name;
        this.mobile = mobile;
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