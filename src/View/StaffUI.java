package View;

import java.util.Scanner;

public class StaffUI {

    public StaffUI() {
        // login
        System.out.println("Please login before continuing.\n");
        login();


    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        String username, password;
        System.out.println("Please enter your username: ");
        username = sc.next();
        System.out.println("Please enter your password: ");
        password = sc.next();

        // authentication
        // TODO use DataManager to authenticate
//        if (username == "123" && password == "123") System.out.println("Login successful!");
//        else {
//            System.out.println("Invalid username or password, please retry.");
//            login();
//        }
    }
}
