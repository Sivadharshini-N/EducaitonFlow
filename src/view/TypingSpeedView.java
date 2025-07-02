package view;

import java.util.Scanner;

public class TypingSpeedView {
    private final Scanner sc = new Scanner(System.in);

    public int showMenuAndGetChoice() {
        System.out.println("\n1. Use a sample text\n2. Add your own text\nChoose option: ");
        return sc.nextInt();
    }

    public void displayTexts(java.util.List<String> texts) {
        int i = 1;
        for (String text : texts) {
            System.out.println(i + ". " + text);
            i++;
        }
    }

    public int getTextChoice() {
        System.out.print("Choose a text number: ");
        return sc.nextInt();
    }

    public String getUserTextInput() {
        sc.nextLine(); // consume leftover newline
        System.out.println("Enter your sample text: ");
        return sc.nextLine();
    }

    public void waitForStart() {
        System.out.println("\nPress enter to start timer...");
        sc.nextLine();
    }

    public String getTypedInput() {
        return sc.nextLine();
    }

    public void showResults(long timeTaken, double wpm) {
        System.out.println("\n--- Results ---");
        System.out.println("Time: " + timeTaken + " seconds");
        System.out.println("WPM: " + wpm);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
