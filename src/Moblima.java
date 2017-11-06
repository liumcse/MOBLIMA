import View.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Hello there, please make a selection:");
            System.out.println("1). I'm a moviegoer");
            System.out.println("2). I'm a staff");
            System.out.println("3). Exit application");

            Scanner sc = new Scanner(System.in);
            try {
                int choice = sc.nextInt();
                switch(choice) {
                    case 1:
                        new MovieGoerUI();
                        break;
                    case 2:
                        new StaffUI();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid selection.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid selection.");
                continue;
            }
        }
    }
}
