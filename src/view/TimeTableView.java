package view;

import java.util.Scanner;

public class TimeTableView {
    private final Scanner sc = new Scanner(System.in);

    public String askDay() {
        System.out.print("Enter day (e.g., Monday): ");
        return sc.nextLine().trim();
    }

    public String askSlot() {
        System.out.print("Enter time slot (e.g., 9-10): ");
        return sc.nextLine().trim();
    }

    public String askSubject() {
        System.out.print("Enter subject: ");
        return sc.nextLine().trim();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void displayTable(String[] days, String[] slots, String[][] table) {
        System.out.printf("%-10s", "Time/Day");
        for (String day : days) {
            System.out.printf("%-15s", day);
        }
        System.out.println();

        for (int j = 0; j < slots.length; j++) {
            System.out.printf("%-10s", slots[j]);
            for (int i = 0; i < days.length; i++) {
                System.out.printf("%-15s", table[i][j]);
            }
            System.out.println();
        }
    }

    public String askSubjectForSlot(String slot) {
        System.out.print("Enter subject for " + slot + ": ");
        return sc.nextLine().trim();
    }

    public int askOption() {
        System.out.print("Choose an option: ");
        return sc.nextInt();
    }

    public int askStartHour() {
        System.out.print("Enter start hour of the day (24hr format, e.g., 7): ");
        return sc.nextInt();
    }

    public int askEndHour() {
        System.out.print("Enter end hour of the day (24hr format, e.g., 17): ");
        return sc.nextInt();
    }

}
