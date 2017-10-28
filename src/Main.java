import View.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Hello there, please make a selection:");
            System.out.println("1). I'm a customer");
            System.out.println("2). I'm a staff");
            System.out.println("3). Exit application");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    new CustomerUI();
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
    }
}
