package controller;

import model.TypingTextDAO;
import view.TypingSpeedView;

import java.util.List;

public class TypingSpeedController {
    private final TypingTextDAO dao = new TypingTextDAO();
    private final TypingSpeedView view = new TypingSpeedView();

    public void run() {
        int option = view.showMenuAndGetChoice();
        List<String> samples = dao.getAllSamples();

        String selectedText = "";

        if (option == 1) {
            if (samples.isEmpty()) {
                view.showMessage("No sample texts available.");
                return;
            }
            view.displayTexts(samples);
            int choice = view.getTextChoice();
            if (choice < 1 || choice > samples.size()) {
                view.showMessage("Invalid choice.");
                return;
            }
            selectedText = samples.get(choice - 1);
        } else if (option == 2) {
            selectedText = view.getUserTextInput();
            dao.addUserSample(selectedText);
            view.showMessage("Text added to DB!");
        } else {
            view.showMessage("Invalid option.");
            return;
        }

        view.showMessage("\nYour text:\n" + selectedText);
        view.waitForStart();

        long start = System.currentTimeMillis();
        String userInput = view.getTypedInput();
        long end = System.currentTimeMillis();

        long timeTaken = (end - start) / 1000;
        int wordCount = userInput.trim().split("\\s+").length;
        double wpm = (wordCount * 60.0) / timeTaken;

        view.showResults(timeTaken, wpm);
    }
}
