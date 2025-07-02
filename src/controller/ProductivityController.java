package controller;

import model.ProductivityModel;
import view.ProductivityView;

import java.time.LocalDate;
import java.util.*;

public class ProductivityController {
    private final ProductivityModel model = new ProductivityModel();
    private final ProductivityView view = new ProductivityView();

    private final Set<String> productiveKeywords = Set.of("study", "read", "project", "college", "research", "practice", "learn", "exercise", "assignment", "revise");
    private final Set<String> nonProductiveKeywords = Set.of("reel", "scroll", "nap", "sleep", "game", "movie", "series", "idle", "chat", "wander", "meme");

    private boolean isProductive(String activity) {
        String text = activity.toLowerCase();
        for (String word : productiveKeywords) {
            if (text.contains(word)) return true;
        }
        for (String word : nonProductiveKeywords) {
            if (text.contains(word)) return false;
        }
        return false; // Default to non-productive if unknown
    }

    public void run() {
        int startHour = view.getStartHour();
        int endHour = view.getEndHour();
        String date = LocalDate.now().toString();

        List<String> hourRanges = new ArrayList<>();
        List<String> activities = new ArrayList<>();
        List<Boolean> productivityFlags = new ArrayList<>();

        int productive = 0, nonProductive = 0;

        for (int hour = startHour; hour < endHour; hour++) {
            String range = hour + "-" + (hour + 1);
            hourRanges.add(range);
            String activity = view.getActivityForHour(range);
            activities.add(activity);
            boolean isProd = isProductive(activity);
            productivityFlags.add(isProd);

            if (isProd) productive++;
            else nonProductive++;
        }

        double percent = (productive * 100.0) / (productive + nonProductive);
        model.saveHourlyLogs(date, hourRanges, activities, productivityFlags);
        model.saveSummary(date, productive, nonProductive, percent);
        view.showMessage("\nAuto-classified productivity report saved successfully!\n");
    }


    public void showPeakProductiveDay() {
        String result = model.getPeakProductiveDay();
        view.showMessage(result);
    }


    public void showReportForDate() {
        String date = view.askDateToView();
        String report = model.getHourlyReportByDate(date);
        if (report.isEmpty()) {
            view.showMessage("No data found for this date.");
        } else {
            view.showMessage(report);
        }
    }
}
