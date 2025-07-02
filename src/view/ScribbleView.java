package view;

import java.util.Scanner;

public class ScribbleView {
    private final Scanner sc = new Scanner(System.in);

    public String askDate() {
        System.out.print("Enter date (yyyy-mm-dd): ");
        return sc.nextLine();
    }

    public String askScribble() {
        System.out.println("Start typing your thoughts (type 'END' on a new line to finish):");
        StringBuilder sb = new StringBuilder();
        String line;

        while (!(line = sc.nextLine()).equalsIgnoreCase("END")) {
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
