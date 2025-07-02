package view;

import java.util.*;

public class ProductivityView {
    private final Scanner sc = new Scanner(System.in);

    public int getStartHour() {
        System.out.print("Enter your day start hour (24hr format, e.g., 7): ");
        return sc.nextInt();
    }

    public int getEndHour() {
        System.out.print("Enter your day end hour (24hr format, e.g., 22): ");
        return sc.nextInt();
    }

    public String getActivityForHour(String range) {
        sc.nextLine(); // clear buffer
        System.out.print("Activity during " + range + ": ");
        return sc.nextLine();
    }

    public String askDateToView() {
        System.out.print("Enter date to view report (yyyy-mm-dd): ");
        return sc.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
